package crud;

import database.SQLite;
import domain.TipoPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<TipoPagamento> consultarTipoPagamento() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<TipoPagamento> listaTiposPagamento = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codTipoPagamento, descricao FROM tipoPagamento;");
            result = stmt.executeQuery();

            while (result.next()) {
                TipoPagamento tipoPagamento = new TipoPagamento();
                tipoPagamento.setCodTipoPagamento(result.getInt("codTipoPagamento"));
                tipoPagamento.setDescricaoTipoPagamento(result.getString("descricao"));

                listaTiposPagamento.add(tipoPagamento);
            }
            stmt.close();
            conn.close();
            
            return listaTiposPagamento;
        } catch (SQLException erroConsultarTipoPagamento) {
            System.out.println(erroConsultarTipoPagamento.getMessage());
            return listaTiposPagamento;
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
