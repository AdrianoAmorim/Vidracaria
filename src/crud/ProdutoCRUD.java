package crud;

import database.SQLite;
import domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
            stmt = conn.prepareStatement("INSERT INTO produto(codProduto, descricao, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda, status) VALUES (?,?,?,?,?,?)");

            stmt.setInt(1, produto.getCodProduto());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getUnidadeMedida());
            stmt.setDouble(4, produto.getQuantidadeEstoque());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setBoolean(6, produto.isStatus());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erroInserirProduto) {
            System.out.println(erroInserirProduto.getMessage());
        }
    }

    public String prepararQueryPesquisarProduto(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT codProduto, unidadeMedida, quantidadeEstoque, descricao, precoVenda, status "
                + "FROM produto ";

        args[0].setName("codProduto");
        args[1].setName("descricao");

        // percorre os JTextFields até encontrar um preenchido
        for (int i = 0; i < tam; i++) {
            // quando encontrar um JTextField não vazio (preenchido)
            if (!args[i].getText().isEmpty()) {
                // incrementa a query de acordo com o nome e conteúdo do JTExtField
                if (args[i].getName().equalsIgnoreCase("codProduto")) {
                    sql += "WHERE " + args[i].getName() + " = " + Integer.parseInt(args[i].getText().trim()) + " ";
                } else {
                    sql += "WHERE " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%' ";
                }

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

    // SELECT
    public ArrayList<Produto> consultarProdutos(JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement(prepararQueryPesquisarProduto(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Produto produto = new Produto();

                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricao(result.getString("descricao"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
                produto.setStatus(result.getBoolean("status"));

                listaProdutos.add(produto);
            }
            stmt.close();

            return listaProdutos;
        } catch (SQLException erroConsultarProduto) {
            System.out.println(erroConsultarProduto.getMessage());
            return listaProdutos;
        }
    }

    // SELECT
    public Produto consultarCodigoProduto(int codProduto) {

        PreparedStatement stmt;
        ResultSet result;
        Produto produto = new Produto();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricao, unidadeMedida,"
                    + " quantidadeEstoque, precoVenda FROM produto WHERE codProduto = " + codProduto + ";");

            result = stmt.executeQuery();
            while (result.next()) {

                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricao(result.getString("descricao"));
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
    public ArrayList<Produto> consultarNomeProduto(String descricao) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listProduto = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codProduto, descricao, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda FROM produto WHERE descricao LIKE '%" + descricao + "%';");

            result = stmt.executeQuery();
            while (result.next()) {
                Produto produto = new Produto();
                produto.setCodProduto(result.getInt("codProduto"));
                produto.setDescricao(result.getString("descricao"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));

                listProduto.add(produto);
            }
            stmt.close();
            return listProduto;
        } catch (SQLException erroConsultarDescricaoProduto) {
            System.out.println(erroConsultarDescricaoProduto.getMessage());
            return listProduto;
        }

    }

    // UPDATE
    public void atualizarProduto(Produto produto) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE produto SET descricao = ?, unidadeMedida = ?, quantidadeEstoque = ?, "
                    + "precoVenda = ?, status = ? WHERE codProduto = ?;");

            stmt.setString(1, produto.getDescricao());
            stmt.setString(2, produto.getUnidadeMedida());
            stmt.setDouble(3, produto.getQuantidadeEstoque());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setBoolean(5, produto.isStatus());
            stmt.setInt(6, produto.getCodProduto());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException erroAtualizarProduto) {
            System.out.println(erroAtualizarProduto.getMessage());
        }
    }
}
