
package crud;

import database.SQLite;
import domain.Renda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class RendaCRUD {
    
        // INSERT 
    public void inserirRenda(Renda renda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO renda(codProduto, codVenda, quantidadeProduto) "
                    + "VALUES (?,?,?);");

            stmt.setInt(1, produtoVendido.getCodProduto());
            stmt.setInt(2, produtoVendido.getCodVenda());
            stmt.setDouble(3, produtoVendido.getQuantidadeProduto());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Produto Comprado cadastrado com sucesso!");
        } catch (SQLException erroInserirProdutoVendido) {
            System.out.println(erroInserirProdutoVendido.getMessage());
        }
    }

    // UPDATE
    public void atualizarProdutoVendido(ProdutoVendido produtoVendido) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produtoVendido SET quantidadeProduto = ? "
                    + "WHERE codProduto = ? AND codVenda = ?;");

            stmt.setDouble(1, produtoVendido.getQuantidadeProduto());
            stmt.setInt(2, produtoVendido.getCodProduto());
            stmt.setInt(3, produtoVendido.getCodVenda());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarProdutoVendido) {
            System.out.println(erroAtualizarProdutoVendido.getMessage());
        }
    }

    // SELECT
    public ArrayList<ProdutoVendido> consultarProdutoVendido() {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList<ProdutoVendido> listaProdutoVendido = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, codCompra, quantidadeProduto "
                    + "FROM produtoVendido;");

            result = stmt.executeQuery();
            while (result.next()) {
                ProdutoVendido produtoVendido = new ProdutoVendido();

                produtoVendido.setCodProduto(result.getInt("codProduto"));
                produtoVendido.setCodVenda(result.getInt("codCompra"));
                produtoVendido.setQuantidadeProduto(result.getDouble("quantidadeProduto"));
                
                listaProdutoVendido.add(produtoVendido);

                stmt.close();
                conn.close();
            }
            return listaProdutoVendido;
        } catch (SQLException erroConsultarProdutoVendido) {
            System.out.println(erroConsultarProdutoVendido.getMessage());
            return listaProdutoVendido;
        }
    }

    // DELETE
    public void deletarProdutoVendido(ProdutoVendido produtoVendido) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM produtoVendido WHERE codProduto = ? AND codCompra = ?;");

            stmt.setInt(1, produtoVendido.getCodProduto());
            stmt.setInt(2, produtoVendido.getCodVenda());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarProdutoVendido) {
            System.out.println(erroDeletarProdutoVendido.getMessage());
        }

    }
    
}
