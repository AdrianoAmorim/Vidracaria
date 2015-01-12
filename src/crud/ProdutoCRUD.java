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
import view.FrmPrincipal;

/**
 *
 * @author debian
 */
public class ProdutoCRUD {

    public int incrementCodProduto(String operacao) {
        PreparedStatement stmt = null;
        Connection conn = new SQLite().conectar();
        int increment = 0;

        try {
            String sql = "";

            if (operacao.equalsIgnoreCase("inicializar")) {
                // seleciona o valor do próximo cliente a ser cadastrado
                sql = "SELECT last_value FROM produto_codProduto_seq;";
            } else if (operacao.equalsIgnoreCase("incrementar")) {
                // incrementa o codigo do próximo cliente
                sql = "select nextval('produto_codProduto_seq');";
            }

            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                increment = result.getInt(1);
                return increment;
            }

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodFuncionario) {
            JOptionPane.showMessageDialog(null, erroIncrementCodFuncionario.getMessage());
        }
        return increment;
    }

    // INSERT
    public void inserirProduto(Produto produto) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO produto(codProduto, codCategoria, descricao, unidadeMedida, "
                    + "quantidadeEstoque, precoVenda, status) VALUES (?,?,?,?,?,?,?)");

            stmt.setInt(1, produto.getCodProduto());
            stmt.setInt(2, produto.getCodCategoria());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getUnidadeMedida());
            stmt.setDouble(5, produto.getQuantidadeEstoque());
            stmt.setDouble(6, produto.getPrecoVenda());
            stmt.setBoolean(7, produto.isStatus());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erroInserirProduto) {
            System.out.println(erroInserirProduto.getMessage());
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
            } else if (!FrmPrincipal.desmascarar(args[i].getText()).trim().isEmpty()) {
                // demais parametros não usam cast e são comparados por aproximação (%LIKE%)
                sql += "AND " + args[i].getName() + " LIKE '%" + FrmPrincipal.desmascarar(args[i].getText()).trim() + "%' ";
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
