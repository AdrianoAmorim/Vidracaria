package crud;

import database.SQLite;
import domain.Parcelamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ParcelamentoCRUD {

    // INSERT 
    public void inserirParcelamento(Parcelamento parcelamento) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO parcelamento(codParcelamento, descricaoParcelamento, "
                    + "quantidadeParcelas VALUES (?,?,?);");

            stmt.setInt(1, parcelamento.getCodParcelamento());
            stmt.setString(2, parcelamento.getDescricaoParcelamento());
            stmt.setInt(3, parcelamento.getQuantidadeParcelas());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Pagamento cadastrado com sucesso!");
        } catch (SQLException erroInserirParcelamento) {
            System.out.println(erroInserirParcelamento.getMessage());
        }
    }

    // UPDATE
    public void atualizarParcelamento(Parcelamento parcelamento) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE parcelamento SET descricaoParcelamento = ?, quantidadeParcelas = ? "
                    + "WHERE codParcelamento = ?;");

            stmt.setString(1, parcelamento.getDescricaoParcelamento());
            stmt.setInt(2, parcelamento.getQuantidadeParcelas());
            stmt.setInt(3, parcelamento.getCodParcelamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarParcelamento) {
            System.out.println(erroAtualizarParcelamento.getMessage());
        }
    }

    // SELECT
    public ArrayList<Parcelamento> consultarParcelamento() {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList<Parcelamento> listaParcelamento = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT codParcelamento, descricaoParcelamento, quantidadeParcelas "
                    + "FROM parcelamento;");

            result = stmt.executeQuery();
            while (result.next()) {
                Parcelamento parcelamento = new Parcelamento();

                parcelamento.setCodParcelamento(result.getInt("codParcelamento"));
                parcelamento.setDescricaoParcelamento(result.getString("descricaoParcelamento"));
                parcelamento.setQuantidadeParcelas(result.getInt("quantidadeParcelas"));

                listaParcelamento.add(parcelamento);

                stmt.close();
                conn.close();
            }
            return listaParcelamento;
        } catch (SQLException erroConsultarParcelamento) {
            System.out.println(erroConsultarParcelamento.getMessage());
            return listaParcelamento;
        }
    }

    // SELECT (Quantidade de parcelas)
    public int consultarQuantidadeParcelas(String descricaoParcelamento) {

        PreparedStatement stmt;
        ResultSet result;

        Parcelamento parcelamento = new Parcelamento();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT quantidadeParcelas FROM parcelamento WHERE descricaoParcelamento = '" + descricaoParcelamento + "';");

            result = stmt.executeQuery();
            if(result.next()) {
                parcelamento.setQuantidadeParcelas(result.getInt("quantidadeParcelas"));

                stmt.close();
                conn.close();
            }
        } catch (SQLException erroConsultarQuantidadeParcelas) {
            System.out.println(erroConsultarQuantidadeParcelas.getMessage());
        }
        return parcelamento.getQuantidadeParcelas();
    }

    // DELETE
    public void deletarParcelamento(Parcelamento parcelamento) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM parcelamento WHERE codParcelamento = ?");

            stmt.setInt(1, parcelamento.getCodParcelamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarParcelamento) {
            System.out.println(erroDeletarParcelamento.getMessage());
        }

    }
}
