
package crud;

import database.SQLite;
import domain.TipoRenda;
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
public class TipoRendaCRUD {

        public void inserirTipoRenda(TipoRenda tipoRenda) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO tipoRenda(codTipoRenda, descricaoTipoRenda, totalRenda)"
                    + " VALUES (?, ?,?);");
            stmt.setInt(1, tipoRenda.getCodTipoRenda());
            stmt.setString(2, tipoRenda.getDescricaoTipoRenda());
            stmt.setDouble(3, tipoRenda.getTotalRenda());
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Tipo de renda cadastrado com sucesso!");
        } catch (SQLException erroInserirTipoRenda) {
            System.out.println(erroInserirTipoRenda.getMessage());
        }
    }

    public ArrayList<TipoRenda> consultarTipoRenda() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<TipoRenda> listaTipoRenda = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("SELECT codTipoRenda, descricaoTipoRenda, totalRenda FROM TipoRenda;");
            rs = stm.executeQuery();

            while (rs.next()) {
                TipoRenda tipoRenda = new TipoRenda();
                tipoRenda.setCodTipoRenda(rs.getInt("codTipoRenda"));
                tipoRenda.setDescricaoTipoRenda(rs.getString("descricaoRenda"));
                tipoRenda.setTotalRenda(rs.getDouble("totalRenda"));

                listaTipoRenda.add(tipoRenda);

            }
            stm.close();
            return listaTipoRenda;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o tipo da Renda.");
            return listaTipoRenda;
        }

    }

    public void atualizarTipoRenda(TipoRenda tipoRenda) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("UPDATE TipoRenda SET descricaoTipoRenda = ?, totalRenda = ? WHERE codTipoRenda = ?;");
            stm.setString(1, tipoRenda.getDescricaoTipoRenda());
            stm.setDouble(2, tipoRenda.getTotalRenda());
            stm.setInt(3, tipoRenda.getCodTipoRenda());

            stm.executeUpdate();
            stm.close();
            System.out.println("Tipo de renda alterada com sucesso!");
        } catch (SQLException erroAlterarTipoRenda) {
            System.out.println(erroAlterarTipoRenda.getMessage());
        }
    }

    public void deletarTipoRenda(TipoRenda tipoRenda) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("DELETE FROM TipoRenda WHERE codTipoRenda = ? ;");

            stm.setInt(1, tipoRenda.getCodTipoRenda());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar tipo de renda.");
        }

    }

}
