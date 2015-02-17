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

    public int ultimoIncrementProduto() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codProduto) FROM produto";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM produto_codProduto_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM produto_codProduto_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1) + 1;
                        // retorna o last_value + 1
                        return increment;
                    }
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodProdutor) {
            JOptionPane.showMessageDialog(null, erroIncrementCodProdutor.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    // INSERT
    public boolean inserirProduto(Produto produto) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produto(codCategoria, descricao, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda, status) VALUES (?,?,?,?,?,?)");

            stmt.setInt(1, produto.getCodCategoria());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getUnidadeMedida());
            stmt.setDouble(4, produto.getQuantidadeEstoque());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setBoolean(6, produto.isStatus());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirProduto) {
            JOptionPane.showMessageDialog(null, erroInserirProduto.getMessage());
            return false;
        }
    }

    public String prepararQueryPesquisarProduto(String categoria, JTextField... args) {
        int tam = args.length;

        String sql = "SELECT p.codProduto, p.codCategoria, p.unidadeMedida, p.quantidadeEstoque, p.descricao, p.precoVenda, p.status "
                + "FROM produto p CROSS JOIN categoria c WHERE p.codCategoria = c.codCategoria "
                + "AND c.descricao = '" + categoria + "' ";

        args[0].setName("p.codProduto");
        args[1].setName("p.descricao");

        // percorre todos os JTextFields
        for (int i = 0; i < args.length; i++) {
            if (args[i].getName().equalsIgnoreCase("p.codProduto") && !args[i].getText().trim().isEmpty()) {
                // caso o parametro seja o codCliente é necessário usar (Cast) e comparação exata (=)
                sql += "AND " + args[i].getName() + " = " + Integer.parseInt(args[i].getText().trim()) + " ";
            } else if (!args[i].getText().isEmpty()) {
                // demais parametros não usam cast e são comparados por aproximação (%LIKE%)
                sql += "AND " + args[i].getName() + " LIKE '%" + args[i].getText() + "%' ";
            }
        }

        sql += ";";
        return sql;
    }

    // SELECT
    public ArrayList<Produto> consultarProdutos(String categoria, JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement(prepararQueryPesquisarProduto(categoria, args));

            result = stmt.executeQuery();

            while (result.next()) {
                Produto produto = new Produto();

                produto.setCodProduto(result.getInt("codProduto"));
                produto.setCodCategoria(result.getInt("codCategoria"));
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

    public ArrayList<Produto> consultarProdutos() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            String sql = "SELECT codProduto, codCategoria, unidadeMedida, quantidadeEstoque, descricao, precoVenda, status "
                    + "FROM produto ";

            stmt = conn.prepareStatement(sql);

            result = stmt.executeQuery();

            while (result.next()) {
                Produto produto = new Produto();

                produto.setCodProduto(result.getInt("codProduto"));
                produto.setCodCategoria(result.getInt("codCategoria"));
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
            stmt = conn.prepareStatement("SELECT p.codProduto, p.codcategoria, p.descricao, "
                    + "p.unidadeMedida, p.quantidadeEstoque, p.precoVenda, p.status "
                    + "FROM produto p CROSS JOIN categoria c WHERE p.codCategoria = c.codCategoria "
                    + "AND p.codProduto = " + codProduto + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                produto.setCodProduto(result.getInt("codProduto"));
                produto.setCodCategoria(result.getInt("codCategoria"));
                produto.setDescricao(result.getString("descricao"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
                produto.setQuantidadeEstoque(result.getDouble("quantidadeEstoque"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
                produto.setStatus(result.getBoolean("status"));
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
