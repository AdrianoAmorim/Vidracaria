package crud;

import database.SQLite;
import domain.ProdutoComprado;
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
public class ProdutoCompradoCRUD {

    // INSERT 
    public boolean inserirProdutoComprado(ProdutoComprado produtoComprado) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO ProdutoComprado(codProduto, codCompra, quantidade, precoCusto) "
                    + "VALUES (?,?,?,?);");

            stmt.setInt(1, produtoComprado.getCodProduto());
            stmt.setInt(2, produtoComprado.getCodCompra());
            stmt.setDouble(3, produtoComprado.getQuantidadeProduto());
            stmt.setDouble(4, produtoComprado.getPrecoCusto());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Produto Comprado cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirProdutoComprado) {
            System.out.println(erroInserirProdutoComprado.getMessage());
            return false;
        }
    }

    // UPDATE
    public void atualizarProdutoComprado(ProdutoComprado produtoComprado) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produtoComprado SET quantidade = ?, precoCusto = ? "
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
    public ArrayList<ProdutoComprado> consultarProdutoComprado(int codCompra) {
        ArrayList<ProdutoComprado> listaProdutoComprado = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            ResultSet result;

            stmt = conn.prepareStatement("SELECT codDespesa, codEmpresa, "
                    + "codProduto, quantidade, precoCusto FROM produtoComprado "
                    + "WHERE codCompra = " + codCompra + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                ProdutoComprado produtoComprado = new ProdutoComprado();

                produtoComprado.setCodDespesa(result.getInt("codDespesa"));
                produtoComprado.setCodEmpresa(result.getInt("codEmpresa"));
                produtoComprado.setCodProduto(result.getInt("codProduto"));
                produtoComprado.setQuantidadeProduto(result.getDouble("quantidade"));
                produtoComprado.setPrecoCusto(result.getDouble("precoCusto"));

                listaProdutoComprado.add(produtoComprado);
            }

            stmt.close();
            conn.close();

            return listaProdutoComprado;
        } catch (SQLException erroConsultarProdutoComprado) {
            JOptionPane.showMessageDialog(null, erroConsultarProdutoComprado.getMessage());
            return listaProdutoComprado;
        }
    }

    // DELETE
    public void deletarProdutoComprado(ProdutoComprado produtoComprado) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM produtoComprado WHERE codCompra = ?;");

            stmt.setInt(1, produtoComprado.getCodCompra());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarProdutoComprado) {
            System.out.println(erroDeletarProdutoComprado.getMessage());
        }

    }

}
