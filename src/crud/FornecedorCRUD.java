package crud;

import database.SQLite;
import domain.Endereco;
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
    public void inserirFornecedor(Fornecedor fornecedor, Endereco enderecoFornecedor) {

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

            stmt.setInt(1, enderecoFornecedor.getCod());
            stmt.setString(2, enderecoFornecedor.getLogradouro());
            stmt.setString(3, enderecoFornecedor.getNumero());
            stmt.setString(4, enderecoFornecedor.getComplemento());
            stmt.setString(5, enderecoFornecedor.getBairro());
            stmt.setString(6, enderecoFornecedor.getCep());
            stmt.setString(7, enderecoFornecedor.getCidade());
            stmt.setString(8, enderecoFornecedor.getUf());

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
            stmt = conn.prepareStatement("UPDATE fornecedor SET cnpjFornecedor = ?, nomeFornecedor = ? "
                    + "WHERE codFornecedor = ?;");

            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setInt(3, fornecedor.getCodFornecedor());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarFornecedor) {
            System.out.println(erroAtualizarFornecedor.getMessage());
        }
    }

    public String prepararQueryPesquisarFornecedor(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT codFornecedor, cnpj, nome, telFixo, telCel, email, "
                + "site, vendedor, ramal, status FROM fornecedor ";

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
                Fornecedor novoFornecedor = new Fornecedor();

                novoFornecedor.setCodFornecedor(result.getInt("codFornecedor"));
                novoFornecedor.setCnpj(result.getString("cnpj"));
                novoFornecedor.setNome(result.getString("nome"));
                novoFornecedor.setTelFixo(result.getString("telFixo"));
                novoFornecedor.setTelCel(result.getString("telCel"));
                novoFornecedor.setEmail(result.getString("email"));

                listaFornecedores.add(novoFornecedor);
            }
            stmt.close();

            return listaFornecedores;
        } catch (SQLException erroConsultarFornecedor) {
            System.out.println(erroConsultarFornecedor.getMessage());
            return listaFornecedores;
        }
    }

    // "DELETE"
    public void desativarFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE codFornecedor = ?;");

            stmt.setInt(1, fornecedor.getCodFornecedor());

            stmt.executeUpdate();
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso!");
        } catch (SQLException erroDeletarFornecedor) {
            System.out.println(erroDeletarFornecedor.getMessage());
        }
    }

}
