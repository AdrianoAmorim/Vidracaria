package crud;

import database.SQLite;
import domain.Compra;
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
public class CompraCRUD {

    public int ultimoIncrementCompra() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codCompra) FROM compra";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM compra_codCompra_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM compra_codCompra_seq";

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
        } catch (SQLException erroIncrementCodCompra) {
            JOptionPane.showMessageDialog(null, erroIncrementCodCompra.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    // INSERT 
    public boolean inserirCompra(Compra compra, ArrayList<ProdutoComprado> listaProdutosComprados) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO compra(codCompra, codDespesa, codParcelamento, "
                    + "codFornecedor, data, totalBruto, desconto, totalLiquido) "
                    + " VALUES (?,?,?,?,?,?,?,?)");

            stmt.setInt(1, compra.getCodCompra());
            stmt.setInt(2, compra.getCodDespesa());
            stmt.setInt(3, compra.getCodParcelamento());
            stmt.setInt(4, compra.getCodFornecedor());
            stmt.setString(5, compra.getData());
            stmt.setDouble(6, compra.getTotalBruto());
            stmt.setDouble(7, compra.getDesconto());
            stmt.setDouble(8, compra.getTotalLiquido());

            stmt.executeUpdate();

            for (ProdutoComprado produtoComprado : listaProdutosComprados) {

                stmt = conn.prepareStatement("INSERT INTO produtoComprado(codCompra, codDespesa, codProduto, "
                        + "quantidade, precoCusto) VALUES (?,?,?,?,?);");

                stmt.setInt(1, produtoComprado.getCodCompra());
                stmt.setInt(2, produtoComprado.getCodDespesa());
                stmt.setInt(3, produtoComprado.getCodProduto());
                stmt.setDouble(4, produtoComprado.getQuantidadeProduto());
                stmt.setDouble(5, produtoComprado.getPrecoCusto());

                stmt.executeUpdate();

                stmt = conn.prepareStatement("UPDATE produto SET quantidadeEstoque = quantidadeEstoque + "
                        + produtoComprado.getQuantidadeProduto() + " WHERE codProduto = " + produtoComprado.getCodProduto());

                stmt.executeUpdate();

                conn.commit();
            }

            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!");
            return true;
        } catch (SQLException erroInserirCompra) {
            JOptionPane.showMessageDialog(null, erroInserirCompra.getMessage());
            return false;
        }
    }

    // UPDATE
    public void atualizarCompra(Compra compra) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE compra SET codFornecedor = ?, codTipoDespesa = ?, "
                    + "dataCompra = ? WHERE codCompra = ?;");

            stmt.setInt(1, compra.getCodFornecedor());
            stmt.setInt(2, compra.getCodDespesa());
            stmt.setString(3, compra.getData());
            stmt.setInt(4, compra.getCodCompra());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarCompra) {
            System.out.println(erroAtualizarCompra.getMessage());
        }
    }

    // SELECT
    public ArrayList<Compra> consultarCompra() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Compra> listaCompras = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codCompra, codFornecedor, codTipoDespesa, dataCompra FROM compra;");
            result = stmt.executeQuery();
            while (result.next()) {
                Compra compra = new Compra();
                compra.setCodCompra(result.getInt("codCompra"));
                compra.setCodFornecedor(result.getInt("codFornecedor"));
                compra.setCodDespesa(result.getInt("codTipoDespesa"));
                compra.setData(result.getString("dataCompra"));

                listaCompras.add(compra);
                stmt.close();
                conn.close();
            }
            return listaCompras;
        } catch (SQLException erroConsultarCompras) {
            System.out.println(erroConsultarCompras.getMessage());
            return listaCompras;
        }
    }

    // DELETE
    public void deletarCompra(Compra compra) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM compra WHERE codCompra = ?;");

            stmt.setInt(1, compra.getCodCompra());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarCompra) {
            System.out.println(erroDeletarCompra.getMessage());
        }
    }

}
