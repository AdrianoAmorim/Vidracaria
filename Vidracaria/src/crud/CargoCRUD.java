package crud;

import database.SQLite;
import domain.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class CargoCRUD {

    // INSERT 
    public void inserirCargo(Cargo cargo) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO cargo(codCargo, descricaoCargo) "
                    + "VALUES (?, ?)");
            
            stmt.setInt(1, cargo.getCodigoCargo());
            stmt.setString(2, cargo.getDescricaoCargo());
            
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Cargo cadastrado com sucesso!");
        } catch (SQLException erroInserirCargo) {
            System.out.println(erroInserirCargo.getMessage());
        }
    }

    // UPDATE
    public void atualizarCargo(Cargo cargo) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE cargo SET descricaoCargo = ? "
                    + " WHERE codCargo = ?;");
            
            stmt.setString(1, cargo.getDescricaoCargo());
            stmt.setInt(2, cargo.getCodigoCargo());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarCargo) {
            System.out.println(erroAtualizarCargo.getMessage());
        }
    }

    // SELECT
    public ArrayList<Cargo> consultarCargo() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cargo> listaCargos = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codCargo, descricaoCargo FROM cargo;");
            result = stmt.executeQuery();
            while (result.next()) {
                Cargo cargo = new Cargo();
                cargo.setCodigoCargo(result.getInt("codCargo"));
                cargo.setDescricaoCargo(result.getString("descricaoCargo"));

                listaCargos.add(cargo);
                stmt.close();
                conn.close();
            }
            return listaCargos;
        } catch (SQLException erroConsultarCargo) {
            System.out.println(erroConsultarCargo.getMessage());
            return listaCargos;
        }
    }

    // DELETE
    public void deletarCargoo(Cargo cargo) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM cargo WHERE codCargo = ?;");
            stmt.setInt(1, cargo.getCodigoCargo());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarCargo) {
            System.out.println(erroDeletarCargo.getMessage());
        }
    }
}
