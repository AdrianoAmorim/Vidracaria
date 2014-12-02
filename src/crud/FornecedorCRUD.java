package crud;

import database.SQLite;
import domain.Fornecedor;
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
public class FornecedorCRUD {

    // INSERT 
    public void inserirFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO fornecedor(codFornecedor, cnpj, nome,"
                    + "telFixo, telCel, email, site, vendedor, ramal, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);");

            stmt.setInt(1, fornecedor.getCodFornecedor());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getNome());
            stmt.setString(4, fornecedor.getTelFixo());
            stmt.setString(5, fornecedor.getTelCel());
            stmt.setString(6, fornecedor.getEmail());
            stmt.setString(7, fornecedor.getSite());
            stmt.setString(8, fornecedor.getVendedor());
            stmt.setString(9, fornecedor.getRamal());
            stmt.setInt(10, fornecedor.getStatus());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO enderecoFornecedor(codFornecedor, logradouro, numero, "
                    + "complemento, bairro, cep, cidade, uf) "
                    + "VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, fornecedor.getCod());
            stmt.setString(2, fornecedor.getLogradouro());
            stmt.setString(3, fornecedor.getNumero());
            stmt.setString(4, fornecedor.getComplemento());
            stmt.setString(5, fornecedor.getBairro());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getCidade());
            stmt.setString(8, fornecedor.getUf());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
        } catch (SQLException erroInserirFornecedor) {
            System.out.println(erroInserirFornecedor.getMessage());
        }
    }

    // UPDATE
    public void atualizarFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("UPDATE fornecedor SET cnpj = ?, nome = ?, "
                    + "telFixo = ?, telCel = ?, email = ?, site = ?, vendedor = ?, "
                    + "ramal = ?, status = ? WHERE codFornecedor = ?;");

            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getTelFixo());
            stmt.setString(4, fornecedor.getTelCel());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getSite());
            stmt.setString(7, fornecedor.getVendedor());
            stmt.setString(8, fornecedor.getRamal());
            stmt.setInt(9, fornecedor.getStatus());
            stmt.setInt(10, fornecedor.getCodFornecedor());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoFornecedor SET logradouro = ?, "
                    + "numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, uf = ? "
                    + "WHERE codFornecedor = ?;");

            stmt.setString(1, fornecedor.getLogradouro());
            stmt.setString(2, fornecedor.getNumero());
            stmt.setString(3, fornecedor.getComplemento());
            stmt.setString(4, fornecedor.getBairro());
            stmt.setString(5, fornecedor.getCep());
            stmt.setString(6, fornecedor.getCidade());
            stmt.setString(7, fornecedor.getUf());
            stmt.setInt(8, fornecedor.getCodFornecedor());

            stmt.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarFornecedor) {
            System.out.println(erroAtualizarFornecedor.getMessage());
        }
    }

    public String prepararQueryPesquisarFornecedor(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT f.codFornecedor, f.cnpj, f.nome, f.telFixo, "
                + "f.telCel, f.email, f.site, f.vendedor, f.ramal, "
                + "f.status, ef.logradouro, ef.numero, ef.complemento, "
                + "ef.bairro, ef.cep, ef.cidade, ef.uf FROM fornecedor f "
                + "NATURAL INNER JOIN enderecoFornecedor ef ";

        args[0].setName("codFornecedor");
        args[1].setName("nome");
        args[2].setName("cnpj");

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
                        sql += "AND " + args[j].getName() + " LIKE '%" + args[j].getText().trim() + "%'";

                        // retorna a query montada
                        return sql;
                    }
                }
            }
        }
        // instrução para burlar o erro do compilador - nunca chega até aqui
        return sql;
    }

    public ArrayList<Fornecedor> consultarFornecedores(JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarFornecedor(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setCodFornecedor(result.getInt("codFornecedor"));
                fornecedor.setCnpj(result.getString("cnpj"));
                fornecedor.setNome(result.getString("nome"));
                fornecedor.setTelFixo(result.getString("telFixo"));
                fornecedor.setTelCel(result.getString("telCel"));
                fornecedor.setEmail(result.getString("email"));
                fornecedor.setSite(result.getString("site"));
                fornecedor.setVendedor(result.getString("vendedor"));
                fornecedor.setRamal(result.getString("ramal"));
                fornecedor.setStatus(result.getInt("status"));
                fornecedor.setLogradouro(result.getString("logradouro"));
                fornecedor.setNumero(result.getString("numero"));
                fornecedor.setComplemento(result.getString("complemento"));
                fornecedor.setBairro(result.getString("bairro"));
                fornecedor.setCep(result.getString("cep"));
                fornecedor.setCidade(result.getString("cidade"));
                fornecedor.setUf(result.getString("uf"));

                listaFornecedores.add(fornecedor);
            }
            stmt.close();

            return listaFornecedores;
        } catch (SQLException erroConsultarFornecedor) {
            System.out.println(erroConsultarFornecedor.getMessage());
            return listaFornecedores;
        }
    }

    public Fornecedor consultarFornecedorPorNome(String nome) {
        Fornecedor fornecedor = new Fornecedor();
        PreparedStatement stmt;
        ResultSet result;

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT f.codFornecedor, f.cnpj, f.nome,"
                    + "f.telFixo, f.telCel, f.email, f.site, f.vendedor, f.ramal, "
                    + "f.status, ef.logradouro, ef.numero, ef.complemento, ef.bairro, "
                    + "ef.cep, ef.cidade, ef.uf FROM fornecedor f NATURAL INNER JOIN enderecoFornecedor ef WHERE nome = '" + nome + "';");

            result = stmt.executeQuery();

            while (result.next()) {
                fornecedor.setCodFornecedor(result.getInt("codFornecedor"));
                fornecedor.setCnpj(result.getString("cnpj"));
                fornecedor.setNome(result.getString("nome"));
                fornecedor.setTelFixo(result.getString("telFixo"));
                fornecedor.setTelCel(result.getString("telCel"));
                fornecedor.setEmail(result.getString("email"));
                fornecedor.setSite(result.getString("site"));
                fornecedor.setVendedor(result.getString("vendedor"));
                fornecedor.setRamal(result.getString("ramal"));
                fornecedor.setStatus(result.getInt("status"));
                fornecedor.setLogradouro(result.getString("logradouro"));
                fornecedor.setNumero(result.getString("numero"));
                fornecedor.setComplemento(result.getString("complemento"));
                fornecedor.setBairro(result.getString("bairro"));
                fornecedor.setCep(result.getString("cep"));
                fornecedor.setCidade(result.getString("cidade"));
                fornecedor.setUf(result.getString("uf"));
            }
        } catch (SQLException erroConsultarFuncPorNome) {
            JOptionPane.showMessageDialog(null, erroConsultarFuncPorNome.getMessage());
        }
        return fornecedor;
    }
}
