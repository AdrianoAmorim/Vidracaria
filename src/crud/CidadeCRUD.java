package crud;

import database.SQLite;
import domain.Cidade;
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
public class CidadeCRUD {

    // SELECT
    public ArrayList<Cidade> consultarCidade(String uf) {
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cidade> listaCidades = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT c.codCidade, c.nome, c.codEstado "
                    + "FROM cidade c CROSS JOIN estado e "
                    + "WHERE c.codEstado = e.codEstado "
                    + "ORDER BY c.nome;");
                    //+ "AND e.uf = '" + uf + "';");
            
            result = stmt.executeQuery();
            
            while(result.next()) {
                Cidade cidade = new Cidade();
                cidade.setCodCidade(result.getInt("codCidade"));
                cidade.setNome(result.getString("nome"));
                cidade.setCodEstado(result.getInt("codEstado"));
                
                listaCidades.add(cidade);
            }
        } catch (SQLException erroConsultarCidade) {
            JOptionPane.showMessageDialog(null, erroConsultarCidade.getMessage());
        }
        return listaCidades;
    }

}
