
package crud;

import database.SQLite;
import domain.PagamentoTitulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class PagamentoTituloCRUD {

    // INSERT 
    public void inserirPagamentoTitulo(PagamentoTitulo pagamentoTitulo) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO pagamentoTitulo(codPagamento, codTitulo, codTipoPagamento, "
                    + "valorPagamento, dataPagamento VALUES (?,?,?,?,?);");

            stmt.setInt(1, pagamentoTitulo.getCodPagamento());
            stmt.setInt(2, pagamentoTitulo.getCodTitulo());
            stmt.setInt(3, pagamentoTitulo.getCodTipoPagamento());
            stmt.setDouble(4, pagamentoTitulo.getValorPagamento());
            stmt.setString(5, pagamentoTitulo.getDataPagamento());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Pagamento cadastrado com sucesso!");
        } catch (SQLException erroInserirPagamentoTitulo) {
            System.out.println(erroInserirPagamentoTitulo.getMessage());
        }
    }

    // UPDATE
    public void atualizarPagamentoTitulo(PagamentoTitulo pagamentoTitulo) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE pagamentoTitulo SET codTitulo = ?, codTipoPagamento = ?, "
                    + " valorPagamento = ?, dataPagamento = ? WHERE codPagamento = ?;");

            stmt.setInt(1, pagamentoTitulo.getCodTitulo());
            stmt.setInt(2, pagamentoTitulo.getCodTipoPagamento());
            stmt.setDouble(3, pagamentoTitulo.getValorPagamento());
            stmt.setString(4, pagamentoTitulo.getDataPagamento());
            stmt.setInt(5, pagamentoTitulo.getCodPagamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarPagamentoTitulo) {
            System.out.println(erroAtualizarPagamentoTitulo.getMessage());
        }
    }

    // SELECT
    public ArrayList<PagamentoTitulo> consultarPagamentoTitulo() {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList<PagamentoTitulo> listaPagamentoTitulos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codPagamento, codTitulo, codTipoPagamento, valorPagamento, "
                    + "dataPagamento FROM pagamentoTitulo;");

            result = stmt.executeQuery();
            while (result.next()) {
                PagamentoTitulo pagamentoTitulo = new PagamentoTitulo();

                pagamentoTitulo.setCodPagamento(result.getInt("codPagamento"));
                pagamentoTitulo.setCodTitulo(result.getInt("codTitulo"));
                pagamentoTitulo.setCodTipoPagamento(result.getInt("codTipoPagamento"));
                pagamentoTitulo.setDataPagamento(result.getString("dataPagamento"));
                pagamentoTitulo.setValorPagamento(result.getDouble("valorPagamento"));

                listaPagamentoTitulos.add(pagamentoTitulo);

                stmt.close();
                conn.close();
            }
            return listaPagamentoTitulos;
        } catch (SQLException erroConsultarPagamentoTitulo) {
            System.out.println(erroConsultarPagamentoTitulo.getMessage());
            return listaPagamentoTitulos;
        }
    }

    // DELETE
    public void deletarPagamentoTitulo(PagamentoTitulo pagamentoTitulo) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM pagamentoTitulo WHERE codPagamento = ?;");

            stmt.setInt(1, pagamentoTitulo.getCodPagamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarFuncionario) {
            System.out.println(erroDeletarFuncionario.getMessage());
        }

    }
}
