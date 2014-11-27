package crud;

import database.SQLite;
import domain.Endereco;
import domain.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rafael
 */
public class FuncionarioCRUD {

    public int incrementCodFuncionario() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;

        try {
            stmt = conn.prepareStatement("SELECT MAX(codFuncionario) FROM funcionario;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodFuncionario) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do funcionario");
        }
        return increment + 1;
    }

    // INSERT 
    public void inserirFuncionario(Funcionario funcionario, Endereco enderecoFuncionario) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO funcionario(codFuncionario, codCargo, codEmpresa, "
                    + " nome, telFixo, telCel, salario, status) VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, funcionario.getCodFuncionario());
            stmt.setInt(2, funcionario.getCodEmpresa());
            stmt.setInt(3, funcionario.getCodCargo());
            stmt.setString(4, funcionario.getNomeFuncionario());
            stmt.setString(5, funcionario.getTelFixo());
            stmt.setString(6, funcionario.getTelCel());
            stmt.setDouble(7, funcionario.getSalarioFuncionario());
            stmt.setInt(8, funcionario.getAtivo());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO enderecoFuncionario(codFuncionario, codCargo, codEmpresa,"
                    + "logradouro, numero, complemento, bairro, cep, cidade, uf) VALUES (?,?,?,?,?,?,?,?,?,?);");

            stmt.setInt(1, enderecoFuncionario.getCod());
            stmt.setInt(2, enderecoFuncionario.getCodCargo());
            stmt.setInt(3, enderecoFuncionario.getCodEmpresa());
            stmt.setString(4, enderecoFuncionario.getLogradouro());
            stmt.setString(5, enderecoFuncionario.getNumero());
            stmt.setString(6, enderecoFuncionario.getComplemento());
            stmt.setString(7, enderecoFuncionario.getBairro());
            stmt.setString(8, enderecoFuncionario.getCep());
            stmt.setString(9, enderecoFuncionario.getCidade());
            stmt.setString(10, enderecoFuncionario.getUf());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
        } catch (SQLException erroInserirFuncionario) {
            System.out.println(erroInserirFuncionario.getMessage());
        }
    }

    // UPDATE
    public void atualizarFuncionario(Funcionario funcionario) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE funcionario SET codEmpresa = ?, codCargo = ?, "
                    + "nomeFuncionario = ?, salarioFuncionario = ? WHERE codFuncionario = ?;");

            stmt.setInt(1, funcionario.getCodEmpresa());
            stmt.setInt(2, funcionario.getCodCargo());
            stmt.setString(3, funcionario.getNomeFuncionario());
            stmt.setDouble(4, funcionario.getSalarioFuncionario());
            stmt.setInt(5, funcionario.getCodFuncionario());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Alteração efetuada com sucesso !");
        } catch (SQLException erroAtualizarFuncionario) {
            System.out.println(erroAtualizarFuncionario.getMessage());
        }
    }

    public String prepararQueryPesquisarFuncionarios(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT f.codFuncionario, f.codCargo, f.codEmpresa, f.nome, f.telFixo, f.telCel,"
                + "f.salario, f.status, ef.logradouro, ef.numero, ef.complemento, ef.bairro, ef.cep, ef.cidade, ef.uf"
                + " FROM funcionario f NATURAL INNER JOIN enderecoFuncionario ef ";

        args[0].setName("codFuncionario");
        args[1].setName("nome");
        args[2].setName("codCargo");

        // percorre os JTextFields até encontrar um preenchido
        for (int i = 0; i < tam; i++) {
            // quando encontrar um JTextField não vazio (preenchido)
            if (!args[i].getText().isEmpty()) {
                // incrementa a query de acordo com o nome e conteúdo do JTExtField
                sql += "WHERE " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%'";
                // percorre novamente o vetor em busca de outro JTextField preenchido
                for (int j = 0; j < tam; j++) {
                    // quando encontrar um JTextField preenchido (que não seja o encontrado anteriormente)
                    if (!args[j].getText().isEmpty() && (!args[j].getText().equals(args[i].getText()))) {
                        // incrementa a query de acordo com o nome e conteúdo do JTextField
                        sql += "AND " + args[j].getName() + " LIKE '%" + args[j].getText().trim() + "%';";
                    }
                }
            }
        }
        return sql;
    }

    // SELECT
    public ArrayList<Funcionario> consultarFuncionario(JTextField... args) {
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarFuncionarios(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setCodFuncionario(result.getInt("codFuncionario"));
                funcionario.setCodCargo(result.getInt("codCargo"));
                funcionario.setCodEmpresa(result.getInt("codEmpresa"));
                funcionario.setNomeFuncionario(result.getString("nome"));
                funcionario.setTelFixo(result.getString("telFixo"));
                funcionario.setTelCel(result.getString("telCel"));
                funcionario.setSalarioFuncionario(result.getDouble("salario"));
                funcionario.setAtivo(result.getInt("status"));
                funcionario.setLogradouro(result.getString("logradouro"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setBairro(result.getString("bairro"));
                funcionario.setCep(result.getString("cep"));
                funcionario.setCidade(result.getString("cidade"));
                funcionario.setUf(result.getString("uf"));

                listaFuncionarios.add(funcionario);
            }
            stmt.close();

            return listaFuncionarios;
        } catch (SQLException erroConsultarFuncionario) {
            System.out.println(erroConsultarFuncionario.getMessage());
            return listaFuncionarios;
        }
    }

    public Funcionario consultarFuncionarioPorNome(String nome) {
        Funcionario funcionario = new Funcionario();
        PreparedStatement stmt;
        ResultSet result;

        String sql = "SELECT f.codFuncionario, f.codCargo, f.codEmpresa, f.nome, f.telFixo, f.telCel,"
                + "f.salario, f.status, ef.logradouro, ef.numero, ef.complemento, ef.bairro, ef.cep, ef.cidade, ef.uf"
                + " FROM funcionario f NATURAL INNER JOIN enderecoFuncionario ef WHERE f.nome = '" + nome + "';";

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            while (result.next()) {
                funcionario.setCodFuncionario(result.getInt("codFuncionario"));
                funcionario.setCodCargo(result.getInt("codCargo"));
                funcionario.setCodEmpresa(result.getInt("codEmpresa"));
                funcionario.setNomeFuncionario(result.getString("nome"));
                funcionario.setTelFixo(result.getString("telFixo"));
                funcionario.setTelCel(result.getString("telCel"));
                funcionario.setSalarioFuncionario(result.getDouble("salario"));
                funcionario.setAtivo(result.getInt("status"));
                funcionario.setLogradouro(result.getString("logradouro"));
                funcionario.setNumero(result.getString("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setBairro(result.getString("bairro"));
                funcionario.setCep(result.getString("cep"));
                funcionario.setCidade(result.getString("cidade"));
                funcionario.setUf(result.getString("uf"));
            }
        } catch (SQLException erroConsultarFuncPorNome) {
            JOptionPane.showMessageDialog(null, erroConsultarFuncPorNome.getMessage());
        }
        return funcionario;
    }
}
