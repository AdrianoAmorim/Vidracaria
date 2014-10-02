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
    public int incrementCodProduto() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;

        try {
            stmt = conn.prepareStatement("SELECT MAX(codProduto) FROM produto;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodProduto) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do produto");
        }
        return increment + 1;
    }

    // INSERT
    public void inserirProduto(Produto produto) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produto(codProduto, descricaoProduto, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda) VALUES (?,?,?,?,?)");

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

    // SELECT
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

    // SELECT CODIGO
    // PROVISÓRIO - ATÉ MONTAR A QUERY COM SELECT CONDICIONAL
    public Produto consultarCodigoProduto(int codProduto) {

        PreparedStatement stmt;
        ResultSet result;
        Produto produto = new Produto();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricaoProduto, unidadeMedida, "
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

    // SELECT NOME
    // PROVISÓRIO - ATÉ MONTAR A QUERY COM SELECT CONDICIONAL
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
        } catch (SQLException erroConsultarDescricaoProduto) {
            System.out.println(erroConsultarDescricaoProduto.getMessage());
        }
        return produto;
    }

    
    // UPDATE
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

    // DELETE
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