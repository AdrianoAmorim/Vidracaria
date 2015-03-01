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
 * @author rafael
 */
public class VendaCRUD {

    public int ultimoIncrementVenda() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codVenda) FROM venda";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM venda_codVenda_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM venda_codVenda_seq";

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
        } catch (SQLException erroIncrementCodVenda) {
            JOptionPane.showMessageDialog(null, erroIncrementCodVenda.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    public boolean inserirVenda(Venda venda, ArrayList<ProdutoVendido> listaProdutosVendidos) {

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO venda(codVenda, codRenda, codEmpresa,"
                    + "codParcelamento, codCliente, codFuncionario, data, desconto, totalBruto, totalLiquido, descricao) "
                    + "VALUES(?,?,?,?,?,?,TO_DATE(? , 'DDMMYYYY'),?,?,?,?);");

            stmt.setInt(1, venda.getCodVenda());
            stmt.setInt(2, venda.getCodRenda());
            stmt.setInt(3, venda.getCodEmpresa());
            stmt.setInt(4, venda.getCodParcelamento());
            stmt.setInt(5, venda.getCodCliente());
            stmt.setInt(6, venda.getCodVendedor());
            stmt.setString(7, venda.getDataVenda());
            stmt.setDouble(8, venda.getTotalDesconto());
            stmt.setDouble(9, venda.getTotalBruto());
            stmt.setDouble(10, venda.getTotalLiquido());
            stmt.setString(11, venda.getDescricao());

            stmt.executeUpdate();

            for (ProdutoVendido produtoVendido : listaProdutosVendidos) {

                stmt = conn.prepareStatement("INSERT INTO produtoVendido(codVenda, codRenda, codEmpresa, "
                        + "codProduto, quantidade, precoVenda) "
                        + "VALUES (?,?,?,?,?,?);");

                stmt.setInt(1, venda.getCodVenda());
                stmt.setInt(2, produtoVendido.getCodRenda());
                stmt.setInt(3, produtoVendido.getCodEmpresa());
                stmt.setInt(4, produtoVendido.getCodProduto());
                stmt.setDouble(5, produtoVendido.getQuantidadeProduto());
                stmt.setDouble(6, produtoVendido.getPrecoVenda());

                stmt.executeUpdate();

                stmt = conn.prepareStatement("UPDATE produto SET quantidadeEstoque = quantidadeEstoque - "
                        + "" + produtoVendido.getQuantidadeProduto() + " WHERE codProduto = " + produtoVendido.getCodProduto());

                stmt.executeUpdate();

            }
            stmt.close();

            conn.commit();
            conn.setAutoCommit(true);

            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
            return true;
        } catch (SQLException erroTransacaoVenda) {
            JOptionPane.showMessageDialog(null, erroTransacaoVenda.getMessage());
            return false;
        }

    }

    public Venda consultarVenda(int codigoVenda) {

        PreparedStatement stmt;
        ResultSet result;

        Venda venda = new Venda();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT * FROM venda "
                    + "WHERE codVenda = " + codigoVenda + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                stmt.setInt(1, venda.getCodVenda());
                stmt.setInt(2, venda.getCodRenda());
                stmt.setInt(3, venda.getCodEmpresa());
                stmt.setInt(4, venda.getCodParcelamento());
                stmt.setInt(5, venda.getCodCliente());
                stmt.setInt(6, venda.getCodVendedor());
                stmt.setString(7, venda.getDataVenda());
                stmt.setDouble(8, venda.getTotalDesconto());
                stmt.setString(9, venda.getDescricao());
                stmt.setDouble(10, venda.getTotalBruto());
                stmt.setDouble(11, venda.getTotalLiquido());

                stmt.close();
                conn.close();
            }
            return venda;
        } catch (SQLException erroConsultarVenda) {
            JOptionPane.showMessageDialog(null, erroConsultarVenda.getMessage());
            return venda;
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
}
