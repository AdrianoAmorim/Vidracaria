package crud;

import database.SQLite;
import domain.ProdutoVendido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ProdutoVendidoCRUD {

    // INSERT 
    public boolean inserirProdutoVendido(ProdutoVendido produtoVendido) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produtoVendido(codProduto, codVenda, quantidade, precoVenda) "
                    + "VALUES (?,?,?,?);");

            stmt.setInt(1, produtoVendido.getCodProduto());
            stmt.setInt(2, produtoVendido.getCodVenda());
            stmt.setDouble(3, produtoVendido.getQuantidadeProduto());
            stmt.setDouble(4, produtoVendido.getPrecoVenda());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Produto Vendido cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirProdutoVendido) {
            System.out.println(erroInserirProdutoVendido.getMessage());
            return false;
        }
    }

    // UPDATE
    public void atualizarProdutoVendido(ProdutoVendido produtoVendido) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produtoVendido, SET quantidade = ?, precoVenda = ?"
                    + "WHERE codProduto = ? AND codVenda = ?;");

            stmt.setDouble(1, produtoVendido.getQuantidadeProduto());
            stmt.setDouble(2, produtoVendido.getPrecoVenda());
            stmt.setInt(3, produtoVendido.getCodProduto());
            stmt.setInt(4, produtoVendido.getCodVenda());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarProdutoVendido) {
            System.out.println(erroAtualizarProdutoVendido.getMessage());
        }
    }

    // SELECT
    public ArrayList<ProdutoVendido> consultarProdutoVendido(int codVenda) {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList<ProdutoVendido> listaProdutoVendido = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codRenda, codEmpresa, codProduto, "
                    + "quantidade, precoVenda FROM produtoVendido "
                    + "WHERE codVenda = " + codVenda + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                ProdutoVendido produtoVendido = new ProdutoVendido();

                produtoVendido.setCodRenda(result.getInt("codRenda"));
                produtoVendido.setCodEmpresa(result.getInt("codEmpresa"));
                produtoVendido.setCodProduto(result.getInt("codProduto"));
                produtoVendido.setQuantidadeProduto(result.getDouble("quantidade"));
                produtoVendido.setPrecoVenda(result.getDouble("precoVenda"));

                listaProdutoVendido.add(produtoVendido);
            }
            stmt.close();
            conn.close();

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
