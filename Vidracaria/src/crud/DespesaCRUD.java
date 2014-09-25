package crud;

import database.SQLite;
import domain.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class DespesaCRUD {

    // INSERT 
    public void inserirDespesa(Despesa despesa) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO despesa(codEmpresa, codTipoDespesa, mesAno) "
                    + "VALUES (?,?,?)");

            stmt.setInt(1, despesa.getCodEmpresa());
            stmt.setInt(2, despesa.getCodTipoDespesa());
            stmt.setString(3, despesa.getMesAno());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Despesa cadastrada com sucesso!");
        } catch (SQLException erroInserirDespesa) {
            System.out.println(erroInserirDespesa.getMessage());
        }
    }

    // UPDATE
    public void atualizarDespesa(Despesa despesa) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE despesa SET codEmpresa = ?, codTipoDespesa = ?, mesAno = ? "
                    + "WHERE codEmpresa = ?, codTipoDespesa = ?, mesAno = ? ;");

            stmt.setInt(1, despesa.getCodEmpresa());
            stmt.setInt(2, despesa.getCodTipoDespesa());
            stmt.setString(3, despesa.getMesAno());
            stmt.setInt(4, despesa.getCodEmpresa());
            stmt.setInt(5, despesa.getCodTipoDespesa());
            stmt.setString(6, despesa.getMesAno());


            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarDespesa) {
            System.out.println(erroAtualizarDespesa.getMessage());
        }
    }

    // SELECT
    public ArrayList<Despesa> consultarDespesa() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Despesa> listaDespesas = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codEmpresa, codTipoDespesa, mesAno FROM despesa;");
            result = stmt.executeQuery();
            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setCodEmpresa(result.getInt("codEmpresa"));
                despesa.setCodTipoDespesa(result.getInt("codTipoDespesa"));
                despesa.setMesAno(result.getString("mesAno"));

                listaDespesas.add(despesa);
                stmt.close();
                conn.close();
            }
            return listaDespesas;
        } catch (SQLException erroConsultarDespesas) {
            System.out.println(erroConsultarDespesas.getMessage());
            return listaDespesas;
        }
    }

    // DELETE
    public void deletarDespesa(Despesa despesa) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM despesa WHERE codEmpresa = ?, codTipoDespesa = ?, mesAno = ?;");
            
            stmt.setInt(1, despesa.getCodEmpresa());
            stmt.setInt(2, despesa.getCodTipoDespesa());
            stmt.setString(3, despesa.getMesAno());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarDespesa) {
            System.out.println(erroDeletarDespesa.getMessage());
        }
    }
}
