package crud;

import database.SQLite;
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

    public int ultimoIncrementFuncionario() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codFuncionario) FROM funcionario";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM funcionario_codFuncionario_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM funcionario_codFuncionario_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1) + 1;
                        // retorna o last_value + 1
                        return increment;
                    }
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodFuncioario) {
            JOptionPane.showMessageDialog(null, erroIncrementCodFuncioario.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    // INSERT 
    public Boolean inserirFuncionario(Funcionario funcionario) {
        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO funcionario(codCargo, codEmpresa, "
                    + "nome, telFixo, telCel, cpf, rg, dtnascimento, salario, status, email) "
                    + "VALUES (?,?,?,?,?,?,?, TO_DATE(? , 'DDMMYYYY'),?,?,?);");

            stmt.setInt(1, funcionario.getCodCargo());
            stmt.setInt(2, funcionario.getCodEmpresa());
            stmt.setString(3, funcionario.getNomeFuncionario());
            stmt.setString(4, funcionario.getTelFixo());
            stmt.setString(5, funcionario.getTelCel());
            stmt.setString(6, funcionario.getCpf());
            stmt.setString(7, funcionario.getRg());
            stmt.setString(8, funcionario.getDtNascimento());
            stmt.setDouble(9, funcionario.getSalarioFuncionario());
            stmt.setBoolean(10, funcionario.getAtivo());
            stmt.setString(11, funcionario.getEmail());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO enderecoFuncionario(codEmpresa,"
                    + "logradouro, numero, complemento, bairro, cep, cidade, uf) VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, funcionario.getCodEmpresa());
            stmt.setString(2, funcionario.getLogradouro());
            stmt.setInt(3, funcionario.getNumero());
            stmt.setString(4, funcionario.getComplemento());
            stmt.setString(5, funcionario.getBairro());
            stmt.setString(6, funcionario.getCep());
            stmt.setString(7, funcionario.getCidade());
            stmt.setString(8, funcionario.getUf());

            stmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirFuncionario) {
            JOptionPane.showMessageDialog(null, erroInserirFuncionario.getMessage());
            return false;
        }
    }

    // UPDATE
    public boolean atualizarFuncionario(Funcionario funcionario) {

        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        try {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("UPDATE funcionario SET codEmpresa = ?, codCargo = ?, "
                    + "nome = ?, telFixo = ?, telCel = ?, cpf = ?, rg = ?, "
                    + "dtnascimento = TO_DATE(? , 'DDMMYYYY'), salario = ?, "
                    + "status = ? , email = ? WHERE codFuncionario = ?;");

            stmt.setInt(1, funcionario.getCodEmpresa());
            stmt.setInt(2, funcionario.getCodCargo());
            stmt.setString(3, funcionario.getNomeFuncionario());
            stmt.setString(4, funcionario.getTelFixo());
            stmt.setString(5, funcionario.getTelCel());
            stmt.setString(6, funcionario.getCpf());
            stmt.setString(7, funcionario.getRg());
            stmt.setString(8, funcionario.getDtNascimento());
            stmt.setDouble(9, funcionario.getSalarioFuncionario());
            stmt.setBoolean(10, funcionario.getAtivo());
            stmt.setString(11, funcionario.getEmail());
            stmt.setInt(12, funcionario.getCodFuncionario());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoFuncionario SET codEmpresa = ?,"
                    + "logradouro = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, uf = ? "
                    + "WHERE codFuncionario = ?;");

            stmt.setInt(1, funcionario.getCodEmpresa());
            stmt.setString(2, funcionario.getLogradouro());
            stmt.setInt(3, funcionario.getNumero());
            stmt.setString(4, funcionario.getComplemento());
            stmt.setString(5, funcionario.getBairro());
            stmt.setString(6, funcionario.getCep());
            stmt.setString(7, funcionario.getCidade());
            stmt.setString(8, funcionario.getUf());
            stmt.setInt(9, funcionario.getCodFuncionario());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Alteração efetuada com sucesso !");
            return true;
        } catch (SQLException erroAtualizarFuncionario) {
            JOptionPane.showMessageDialog(null, erroAtualizarFuncionario.getMessage());
            return false;
        }
    }

    public String prepararQueryPesquisarFuncionarios(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT f.codFuncionario, f.codCargo, f.codEmpresa, f.nome, f.telFixo, f.telCel, "
                + "f.cpf, f.rg, TO_CHAR(f.dtnascimento, 'ddMMyyyy') AS dtnascimento, f.salario, f.status, f.email, "
                + "ef.logradouro, ef.numero, ef.complemento, ef.bairro, ef.cep, ef.cidade, ef.uf "
                + "FROM funcionario f CROSS JOIN enderecoFuncionario ef "
                + "WHERE f.codFuncionario = ef.codFuncionario ";

        args[0].setName("f.codFuncionario");
        args[1].setName("f.nome");
        args[2].setName("f.codCargo");

        // percorre todos os JTextFields
        for (int i = 0; i < args.length; i++) {
            // se os campos não estiverem vazios
            if (!args[i].getText().trim().isEmpty()) {
                // caso o parametro seja o codFuncionario ou codCargo 
                if (args[i].getName().equalsIgnoreCase("f.codFuncionario") || args[i].getName().equalsIgnoreCase("f.codCargo")) {
                    // é necessário usar (Cast) e comparação exata (=)
                    sql += "AND " + args[i].getName() + " = " + args[i].getText().trim() + " ";
                } else {
                    // demais parametros não usam cast e são comparados por aproximação (%LIKE%)
                    sql += "AND " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%' ";
                }
            }
        }

        sql += ";";
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
                funcionario.setDtNascimento(result.getString("dtnascimento"));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setRg(result.getString("rg"));
                funcionario.setSalarioFuncionario(result.getDouble("salario"));
                funcionario.setAtivo(result.getBoolean("status"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setLogradouro(result.getString("logradouro"));
                funcionario.setNumero(result.getInt("numero"));
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
            JOptionPane.showMessageDialog(null, erroConsultarFuncionario.getMessage());
            return listaFuncionarios;
        }
    }

    public Funcionario consultarFuncionario(String nome, int codigoFuncionario) {
        Funcionario funcionario = new Funcionario();
        PreparedStatement stmt;
        ResultSet result;

        String sql = "SELECT f.codFuncionario, f.codCargo, f.codEmpresa, f.nome, f.telFixo, f.telCel, f.email, "
                + "f.cpf, f.rg, TO_CHAR(f.dtnascimento, 'ddMMyyyy') AS dtnascimento, f.salario, f.status, ef.logradouro, ef.numero, ef.complemento, "
                + "ef.bairro, ef.cep, ef.cidade, ef.uf "
                + "FROM funcionario f CROSS JOIN enderecoFuncionario ef "
                + "WHERE f.codFuncionario = ef.codFuncionario "
                + "AND f.nome = '" + nome + "' OR f.codFuncionario = " + codigoFuncionario + " ";

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
                funcionario.setEmail(result.getString("email"));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setRg(result.getString("rg"));
                funcionario.setDtNascimento(result.getString("dtnascimento"));
                funcionario.setSalarioFuncionario(result.getDouble("salario"));
                funcionario.setAtivo(result.getBoolean("status"));
                funcionario.setLogradouro(result.getString("logradouro"));
                funcionario.setNumero(result.getInt("numero"));
                funcionario.setComplemento(result.getString("complemento"));
                funcionario.setBairro(result.getString("bairro"));
                funcionario.setCep(result.getString("cep"));
                funcionario.setCidade(result.getString("cidade"));
                funcionario.setUf(result.getString("uf"));
            }
            stmt.close();            
        } catch (SQLException erroConsultarFuncPorNome) {
            JOptionPane.showMessageDialog(null, erroConsultarFuncPorNome.getMessage());
        }
        return funcionario;
    }
}
