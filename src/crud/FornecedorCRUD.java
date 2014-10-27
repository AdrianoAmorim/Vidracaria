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

    // SELECT
    public ArrayList<Fornecedor> consultarFornecedor() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codFornecedor, cnpjFornecedor, nomeFornecedor FROM fornecedor;");

            result = stmt.executeQuery();
            while (result.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodFornecedor(fornecedor.getCodFornecedor());
                fornecedor.setCnpj(fornecedor.getCnpj());
                fornecedor.setNome(fornecedor.getNome());

                listaFornecedor.add(fornecedor);

                stmt.close();
                conn.close();
            }
            return listaFornecedor;
        } catch (SQLException erroConsultarFornecedor) {
            System.out.println(erroConsultarFornecedor.getMessage());
            return listaFornecedor;
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
