
package crud;

import database.SQLite;
import domain.TipoDespesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
public class TipoDespesaCRUD {

    public void inserirTipoDespesa(TipoDespesa tipoDespesa) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO TipoDespesa(codTipoDespesa, descricaoTipoDespesa, totalDespesa)"
                    + " VALUES (?, ?,?);");
            stmt.setInt(1, tipoDespesa.getCodTipoDespesa());
            stmt.setString(2, tipoDespesa.getDescricaoTipoDespesa());
            stmt.setDouble(3, tipoDespesa.getTotalDespesa());
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Tipo de despesa cadastrado com sucesso!");
        } catch (SQLException erroInserirTipoDespesa) {
            System.out.println(erroInserirTipoDespesa.getMessage());
        }
    }

    public ArrayList<TipoDespesa> consultarTipoDespesa() {
        PreparedStatement stm;
        ResultSet rs;
        ArrayList<TipoDespesa> listaTipoDespesa = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("SELECT codTipoDespesa, descricaoTipoDespesa, totalDespesa FROM TipoDespesa;");
            rs = stm.executeQuery();

            while (rs.next()) {
                TipoDespesa tipoDespesa = new TipoDespesa();
                tipoDespesa.setCodTipoDespesa(rs.getInt("codTipoDespesa"));
                tipoDespesa.setDescricaoTipoDespesa(rs.getString("descricaoTipoDespesa"));
                tipoDespesa.setTotalDespesa(rs.getDouble("totalDespesa"));

                listaTipoDespesa.add(tipoDespesa);

            }
            stm.close();
            return listaTipoDespesa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o tipo da Despesa.");
            return listaTipoDespesa;
        }

    }

    public void atualizarTipoDespesa(TipoDespesa tipoDespesa) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("UPDATE TipoDespesa SET descricaoTipoDespesa = ?, totalDespesa = ? WHERE codTipoDespesa = ?;");
            stm.setString(1, tipoDespesa.getDescricaoTipoDespesa());
            stm.setDouble(2, tipoDespesa.getTotalDespesa());
            stm.setInt(3, tipoDespesa.getCodTipoDespesa());

            stm.executeUpdate();
            stm.close();
            System.out.println("Tipo de despesa Alterada com sucesso!");
        } catch (SQLException erroAlterarTipoDespesa) {
            System.out.println(erroAlterarTipoDespesa.getMessage());
        }
    }

    public void deletarTipoDespesa(TipoDespesa tipoDespesa) {
        PreparedStatement stm;

        try (Connection conn = new SQLite().conectar()) {
            stm = conn.prepareStatement("DELETE FROM TipoDespesa WHERE codTipoDespesa = ? ;");

            stm.setInt(1, tipoDespesa.getCodTipoDespesa());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar Tipo de despesa.");
        }

    }

}
