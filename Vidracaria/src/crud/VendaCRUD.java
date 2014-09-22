package crud;

import database.SQLite;
import domain.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author debian
 */
public class VendaCRUD {

    // metodo para controlar, pelo Java, a função de Auto Increment da Venda.
    public int retornarIncrement() {
        int increment = 0;

        PreparedStatement stmt;
        ResultSet res;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT max(codVenda) FROM venda;");
            res = stmt.executeQuery();
            if (res.next()) {
                // guarda o valor atual do codigoProduto + 1
                increment = res.getInt("max(codVenda)") + 1;
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroReturnIncrement) {
            System.out.println(erroReturnIncrement.getMessage());
        }
        return increment;
    }

    public void inserirVenda(Venda venda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO venda(codVenda, codTipoRenda, codCliente, codParcelamento, totalVenda, dataVenda) "
                    + "VALUES(?,?,?,?,?,?);");
            stmt.setInt(1, venda.getCodVenda());
            stmt.setInt(2, venda.getCodTipoRenda());
            stmt.setInt(3, venda.getCodCliente());
            stmt.setInt(4, venda.getCodParcelamento());
            stmt.setDouble(5, venda.getTotalVenda());
            stmt.setString(6, venda.getDataVenda());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Venda cadastrada com sucesso!");
        } catch (SQLException erroInserirVenda) {
            System.out.println(erroInserirVenda.getMessage());
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
                venda.setCodVenda(result.getInt("codVenda"));
                venda.setCodTipoRenda(result.getInt("codTipoRenda"));
                venda.setCodCliente(result.getInt("codCliente"));
                venda.setCodParcelamento(result.getInt("codParcelamento"));
                venda.setTotalVenda(result.getDouble("totalVenda"));
                venda.setDataVenda(result.getString("dataVenda"));

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

            stmt.setInt(1, venda.getCodTipoRenda());
            stmt.setInt(2, venda.getCodCliente());
            stmt.setInt(3, venda.getCodParcelamento());
            stmt.setDouble(4, venda.getTotalVenda());
            stmt.setString(5, venda.getDataVenda());
            stmt.setInt(6, venda.getCodVenda());

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
