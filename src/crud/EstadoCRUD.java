package crud;

import database.SQLite;
import domain.Estado;
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
public class EstadoCRUD {

    // SELECT
    public ArrayList<Estado> consultarEstado() {
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Estado> listaEstados = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codEstado, nome, uf FROM estado;");
            
            result = stmt.executeQuery();
            
            while(result.next()) {
                Estado estado = new Estado();
                
                estado.setCodEstado(result.getInt("codEstado"));
                estado.setNome(result.getString("nome"));
                estado.setUf(result.getString("uf"));
                
                listaEstados.add(estado);
            }
        } catch (SQLException erroConsultarEstado) {
            JOptionPane.showMessageDialog(null, erroConsultarEstado.getMessage());
        }
        return listaEstados;
    }

}
