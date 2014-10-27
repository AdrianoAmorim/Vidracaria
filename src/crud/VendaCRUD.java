package crud;

import database.SQLite;
import domain.ProdutoVendido;
import domain.Venda;
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
public class VendaCRUD {

    public int incrementCodVenda() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;

        try {
            stmt = conn.prepareStatement("SELECT MAX(codVenda) FROM venda;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodVenda) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo da venda");
        }
        return increment + 1;
    }

    public void inserirVenda(Venda venda, ArrayList<ProdutoVendido> listaProdutosVendidos) {

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO venda(codVenda, codRenda, codParcelamento, "
                    + "codCliente, data, desconto, totalBruto, totalLiquido, descricao) "
                    + "VALUES(?,?,?,?,?,?,?,?,?);");

            stmt.setInt(1, venda.getCodVenda());
            stmt.setInt(2, venda.getCodRenda());
            stmt.setInt(3, venda.getCodParcelamento());
            stmt.setInt(4, venda.getCodCliente());
            stmt.setString(5, venda.getDataVenda());
            stmt.setDouble(6, venda.getTotalDesconto());
            stmt.setDouble(7, venda.getTotalBruto());
            stmt.setDouble(8, venda.getTotalLiquido());
            stmt.setString(9, venda.getDescricao());

            stmt.executeUpdate();

            for (ProdutoVendido produtoVendido : listaProdutosVendidos) {

                stmt = conn.prepareStatement("INSERT INTO produtoVendido(codVenda, codProduto, codRenda, "
                        + "quantidade, precoVenda) "
                        + "VALUES (?,?,?,?,?);");

                stmt.setInt(1, venda.getCodVenda());
                stmt.setInt(2, produtoVendido.getCodProduto());
                stmt.setInt(3, produtoVendido.getCodRenda());
                stmt.setDouble(4, produtoVendido.getQuantidadeProduto());
                stmt.setDouble(5, produtoVendido.getPrecoVenda());

                stmt.executeUpdate();
                
                stmt = conn.prepareStatement("UPDATE produto SET quantidadeEstoque = quantidadeEstoque -" + produtoVendido.getQuantidadeProduto());
                stmt.executeUpdate();
                
            }

            stmt.close();
            conn.commit();
            conn.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        } catch (SQLException erroTransacaoVenda) {
            JOptionPane.showMessageDialog(null, erroTransacaoVenda.getMessage());
        }

    }

    public ArrayList<Venda> consultarVenda() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Venda> listaVendas = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codVenda, codTipoRenda, codCliente, codParcelamento, totalVenda, dataVenda FROM venda;");
            result = stmt.executeQuery();
            while (result.next()) {
                Venda venda = new Venda();
                stmt.setInt(1, venda.getCodVenda());
                stmt.setInt(2, venda.getCodRenda());
                stmt.setInt(3, venda.getCodParcelamento());
                stmt.setInt(4, venda.getCodCliente());
                stmt.setString(5, venda.getDataVenda());
                stmt.setDouble(6, venda.getTotalBruto());
                stmt.setDouble(7, venda.getTotalLiquido());

                listaVendas.add(venda);
                stmt.close();
                conn.close();
            }
            return listaVendas;
        } catch (SQLException erroConsultarVenda) {
            System.out.println(erroConsultarVenda.getMessage());
            return listaVendas;
        }
    }

    public void atualizarVenda(Venda venda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE venda SET codTipoRenda = ?, codCliente = ?, codParcelamento = ?, totalVenda = ?, "
                    + "dataVenda = ? WHERE codVenda = ?;");

            stmt.setInt(1, venda.getCodVenda());
            stmt.setInt(2, venda.getCodRenda());
            stmt.setInt(3, venda.getCodParcelamento());
            stmt.setInt(4, venda.getCodCliente());
            stmt.setString(5, venda.getDataVenda());
            stmt.setDouble(6, venda.getTotalBruto());
            stmt.setDouble(7, venda.getTotalLiquido());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarVenda) {
            System.out.println(erroAtualizarVenda.getMessage());
        }
    }

    public void deletarVenda(Venda venda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM venda WHERE codVenda = ?;");
            stmt.setInt(1, venda.getCodVenda());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarVenda) {
            System.out.println(erroDeletarVenda.getMessage());
        }
    }

}
