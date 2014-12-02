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

    public int incrementCodCompra() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;

        try {
            stmt = conn.prepareStatement("SELECT MAX(codCompra) FROM compra;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodCompra) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do compra");
        }
        return increment + 1;
    }

    // INSERT 
    public void inserirCompra(Compra compra, ArrayList<ProdutoComprado> listaProdutosComprados) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement("INSERT INTO compra(codCompra, codDespesa, codParcelamento, "
                    + "codFornecedor, data, descricao, totalBruto, desconto, totalLiquido) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, compra.getCodCompra());
            stmt.setInt(2, compra.getCodDespesa());
            stmt.setInt(3, compra.getCodParcelamento());
            stmt.setInt(4, compra.getCodFornecedor());
            stmt.setString(5, compra.getData());
            stmt.setString(6, compra.getDescricao());
            stmt.setDouble(7, compra.getTotalBruto());
            stmt.setDouble(8, compra.getDesconto());
            stmt.setDouble(9, compra.getTotalLiquido());

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
            }
            
            stmt.close();
            conn.commit();
            conn.setAutoCommit(true);

            JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!");
        } catch (SQLException erroInserirCompra) {
            JOptionPane.showMessageDialog(null, erroInserirCompra.getMessage());
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
