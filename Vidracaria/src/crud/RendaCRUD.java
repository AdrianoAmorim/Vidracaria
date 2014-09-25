
package crud;

import database.SQLite;
import domain.Renda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class RendaCRUD {

    // INSERT 
    public void inserirRenda(Renda renda) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO renda(codEmpresa, codTipoRenda, mesAno) "
                    + "VALUES (?,?,?)");

            stmt.setInt(1, renda.getCodEmpresa());
            stmt.setInt(2, renda.getCodTipoRenda());
            stmt.setString(3, renda.getMesAno());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Renda cadastrada com sucesso!");
        } catch (SQLException erroInserirRenda) {
            System.out.println(erroInserirRenda.getMessage());
        }
    }

    // UPDATE
    public void atualizarRenda(Renda renda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE renda SET codEmpresa = ?, codTipoRenda = ?, mesAno = ? "
                    + "WHERE codEmpresa = ?, codTipoRenda = ?, mesAno = ?;");

            stmt.setInt(1, renda.getCodEmpresa());
            stmt.setInt(2, renda.getCodTipoRenda());
            stmt.setString(3, renda.getMesAno());
            stmt.setInt(4, renda.getCodEmpresa());
            stmt.setInt(5, renda.getCodTipoRenda());
            stmt.setString(6, renda.getMesAno());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarRenda) {
            System.out.println(erroAtualizarRenda.getMessage());
        }
    }

    // SELECT
    public ArrayList<Renda> consultarRenda() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Renda> listaRendas = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codEmpresa, codTipoRenda, mesAno FROM renda;");
            result = stmt.executeQuery();
            while (result.next()) {
                Renda renda = new Renda();
                renda.setCodEmpresa(result.getInt("codEmpresa"));
                renda.setCodTipoRenda(result.getInt("codTipoRenda"));
                renda.setMesAno(result.getString("mesAno"));

                listaRendas.add(renda);
                stmt.close();
                conn.close();
            }
            return listaRendas;
        } catch (SQLException erroConsultarRendas) {
            System.out.println(erroConsultarRendas.getMessage());
            return listaRendas;
        }
    }

    // DELETE
    public void deletarRenda(Renda renda) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM renda WHERE codRenda = ?, codTipoRenda = ?, mesAno = ?;");

            stmt.setInt(1, renda.getCodEmpresa());
            stmt.setInt(2, renda.getCodTipoRenda());
            stmt.setString(3, renda.getMesAno());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarRenda) {
            System.out.println(erroDeletarRenda.getMessage());
        }
    }
}
