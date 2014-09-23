package crud;

import database.SQLite;
import domain.ProdutoComprado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ProdutoCompradoCRUD {

    // INSERT 
    public void inserirProdutoComprado(ProdutoComprado produtoComprado) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produtoComprado(codProduto, codCompra, quantidadeProduto, precoCusto) "
                    + "VALUES (?,?,?,?);");

            stmt.setInt(1, produtoComprado.getCodProduto());
            stmt.setInt(2, produtoComprado.getCodCompra());
            stmt.setDouble(3, produtoComprado.getQuantidadeProduto());
            stmt.setDouble(4, produtoComprado.getPrecoCusto());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Produto Comprado cadastrado com sucesso!");
        } catch (SQLException erroInserirProdutoComprado) {
            System.out.println(erroInserirProdutoComprado.getMessage());
        }
    }

    // UPDATE
    public void atualizarProdutoComprado(ProdutoComprado produtoComprado) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produtoComprado SET quantidadeProduto = ?, precoCusto = ? "
                    + "WHERE codProduto = ? AND codCompra = ?;");

            stmt.setDouble(1, produtoComprado.getQuantidadeProduto());
            stmt.setDouble(2, produtoComprado.getPrecoCusto());
            stmt.setInt(3, produtoComprado.getCodProduto());
            stmt.setInt(4, produtoComprado.getCodCompra());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarProdutoComprado) {
            System.out.println(erroAtualizarProdutoComprado.getMessage());
        }
    }

    // SELECT
    public ArrayList<ProdutoComprado> consultarProdutoComprado() {        
        
        PreparedStatement stmt;
        ResultSet result;

        ArrayList<ProdutoComprado> listaProdutoComprado = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, codCompra, quantidadeProduto, precoCusto "
                    + "FROM produtoComprado;");

            result = stmt.executeQuery();
            while (result.next()) {
                ProdutoComprado produtoComprado = new ProdutoComprado();

                produtoComprado.setCodProduto(result.getInt("codProduto"));
                produtoComprado.setCodCompra(result.getInt("codCompra"));
                produtoComprado.setQuantidadeProduto(result.getDouble("quantidadeProduto"));
                produtoComprado.setPrecoCusto(result.getDouble("precoCusto"));
 
                listaProdutoComprado.add(produtoComprado);

                stmt.close();
                conn.close();
            }
            return listaProdutoComprado;
        } catch (SQLException erroConsultarProdutoComprado) {
            System.out.println(erroConsultarProdutoComprado.getMessage());
            return listaProdutoComprado;
        }
    }

    // DELETE
    public void deletarProdutoComprado(ProdutoComprado produtoComprado) {
        
        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM produtoComprado WHERE codProduto = ? AND codCompra = ?;");

            stmt.setInt(1, produtoComprado.getCodProduto());
            stmt.setInt(2, produtoComprado.getCodCompra());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarProdutoComprado) {
            System.out.println(erroDeletarProdutoComprado.getMessage());
        }

    }

}
