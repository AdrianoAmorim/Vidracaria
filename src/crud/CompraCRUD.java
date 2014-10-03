package crud;

import database.SQLite;
import domain.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class CompraCRUD {

    // INSERT 
    public void inserirCompra(Compra compra) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO compra(codCompra, codFornecedor, codTipoDespesa, dataCompra) "
                    + "VALUES (?,?,?,?)");

            stmt.setInt(1, compra.getCodCompra());
            stmt.setInt(2, compra.getCodFornecedor());
            stmt.setInt(3, compra.getCodTipoDespesa());
            stmt.setString(4, compra.getDataCompra());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Compra cadastrada com sucesso!");
        } catch (SQLException erroInserirCompra) {
            System.out.println(erroInserirCompra.getMessage());
        }
    }

    // UPDATE
    public void atualizarCompra(Compra compra) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE compra SET codFornecedor = ?, codTipoDespesa = ?, "
                    + "dataCompra = ? WHERE codCompra = ?;");

            stmt.setInt(1, compra.getCodFornecedor());
            stmt.setInt(2, compra.getCodTipoDespesa());
            stmt.setString(3, compra.getDataCompra());
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
                compra.setCodTipoDespesa(result.getInt("codTipoDespesa"));
                compra.setDataCompra(result.getString("dataCompra"));

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
