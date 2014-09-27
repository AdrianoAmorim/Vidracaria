
package crud;

import database.SQLite;
import domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author debian
 */
public class ProdutoCRUD {

    // metodo para controlar, pelo Java, a função de Auto Increment do Produto.
    public int retornarIncrement() {
        int increment = 0;

        PreparedStatement stmt;
        ResultSet res;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT max(codProduto) FROM produto;");
            res = stmt.executeQuery();
            while (res.next()) {
                // guarda o valor atual do codigoProduto + 1
                increment = res.getInt("max(codProduto)") + 1;
            }
            stmt.close();
        } catch (SQLException erroReturnIncrement) {
            System.out.println(erroReturnIncrement.getMessage());
        }
        return increment;
    }

    public void inserirProduto(Produto produto) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produto(codProduto, descricaoProduto, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, produto.getCodProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setString(3, produto.getUnidadeMedida());
            stmt.setDouble(4, produto.getQuantidadeEstoque());
            stmt.setDouble(5, produto.getPrecoVenda());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erroInserirProduto) {
            System.out.println(erroInserirProduto.getMessage());
        }
    }

    public ArrayList<Produto> consultarProduto() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricaoProduto, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda FROM produto;");

            result = stmt.executeQuery();

            while (result.next()) {
                Produto produto = new Produto();
                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));

                listaProdutos.add(produto);
            }
            stmt.close();

            return listaProdutos;
        } catch (SQLException erroConsultarProduto) {
            System.out.println(erroConsultarProduto.getMessage());
            return listaProdutos;
        }
    }

    public Produto consultarCodigoProduto(String codProduto) {

        PreparedStatement stmt;
        ResultSet result;
        Produto produto = new Produto();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricaoProduto, unidadeMedida "
                    + " quantidadeEstoque, precoVenda FROM produto WHERE codProduto = '" + codProduto + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));

            }
            stmt.close();
        } catch (SQLException erroConsultarCodigoProduto) {
            System.out.println(erroConsultarCodigoProduto.getMessage());
        }
        return produto;
    }

    public Produto consultarNomeProduto(String descricaoProduto) {

        PreparedStatement stmt;
        ResultSet result;
        Produto produto = new Produto();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricaoProduto, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda FROM produto WHERE descricaoProduto = '" + descricaoProduto + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
            }
            stmt.close();
        } catch (SQLException erroConsultarCodigoProduto) {
            System.out.println(erroConsultarCodigoProduto.getMessage());
        }
        return produto;
    }

    public void atualizarProduto(Produto produto) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produto SET descricaoProduto = ?, unidadeMedida = ?, quantidadeEstoque = ?, "
                    + "precoVenda = ? WHERE codProduto = ?;");

            stmt.setString(1, produto.getDescricaoProduto());
            stmt.setString(2, produto.getUnidadeMedida());
            stmt.setDouble(3, produto.getQuantidadeEstoque());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getCodProduto());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException erroAtualizarProduto) {
            System.out.println(erroAtualizarProduto.getMessage());
        }
    }

    public void deletarProduto(Produto produto) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM produto WHERE codProduto = ?;");
            stmt.setInt(1, produto.getCodProduto());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
        } catch (SQLException erroDeletarProduto) {
            System.out.println(erroDeletarProduto.getMessage());
        }
    }

}
