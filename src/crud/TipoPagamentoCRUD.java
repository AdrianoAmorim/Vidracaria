package crud;

import database.SQLite;
import domain.TipoPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author debian
 */
public class TipoPagamentoCRUD {

    public void inserirTipoPagamento(TipoPagamento tipoPagamento) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO tipoPagamento(codTipoPagamento, descricao)"
                    + " VALUES (?, ?)");
            stmt.setInt(1, tipoPagamento.getCodTipoPagamento());
            stmt.setString(2, tipoPagamento.getDescricaoTipoPagamento());
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Tipo de pagamento cadastrado com sucesso!");
        } catch (SQLException erroInserirTipoPagamento) {
            System.out.println(erroInserirTipoPagamento.getMessage());
        }
    }

    public TipoPagamento consultarTipoPagamento(int codTipoPagamento) {

        PreparedStatement stmt;
        ResultSet result;
        TipoPagamento tipoPagamento = new TipoPagamento();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codTipoPagamento, descricao FROM tipoPagamento "
                    + "WHERE codTipoPagamento = " + codTipoPagamento + ";");

            result = stmt.executeQuery();

            while (result.next()) {
                tipoPagamento.setCodTipoPagamento(result.getInt("codTipoPagamento"));
                tipoPagamento.setDescricaoTipoPagamento(result.getString("descricao"));
            }

            stmt.close();
            conn.close();

            return tipoPagamento;
        } catch (SQLException erroConsultarTipoPagamento) {
            JOptionPane.showMessageDialog(null, erroConsultarTipoPagamento.getMessage());
            return tipoPagamento;
        }
    }

    public ArrayList<TipoPagamento> consultarTipoPagamento() {

        PreparedStatement stmt;
        ResultSet result;

        ArrayList listaTipoPagamento = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codTipoPagamento, descricao FROM tipoPagamento;");

            result = stmt.executeQuery();

            while (result.next()) {
                TipoPagamento tipoPagamento = new TipoPagamento();

                tipoPagamento.setCodTipoPagamento(result.getInt("codTipoPagamento"));
                tipoPagamento.setDescricaoTipoPagamento(result.getString("descricao"));

                listaTipoPagamento.add(listaTipoPagamento);
            }

            stmt.close();
            conn.close();

            return listaTipoPagamento;
        } catch (SQLException erroConsultarTipoPagamento) {
            JOptionPane.showMessageDialog(null, erroConsultarTipoPagamento.getMessage());
            return listaTipoPagamento;
        }
    }

    public void atualizarTipoPagamento(TipoPagamento tipoPagamento) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE tipoPagamento SET descricaoTipoPagamento = ?"
                    + " WHERE codTipoPagamento = ?;");
            stmt.setString(1, tipoPagamento.getDescricaoTipoPagamento());
            stmt.setInt(2, tipoPagamento.getCodTipoPagamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarTipoPagamento) {
            System.out.println(erroAtualizarTipoPagamento.getMessage());
        }
    }

}
