/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

        Connection conn = new SQLite().conectar();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO tipoPagamento(codigoTipoPagamento, descricaoPagamento)"
                    + " VALUES (?, ?)");
            stmt.setInt(1, tipoPagamento.getCodigoTipoPagamento());
            stmt.setString(2, tipoPagamento.getDescricaoPagamento());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Tipo de pagamento cadastrado com sucesso!");
        } catch (SQLException erroInserirTipoPagamento) {
            System.out.println(erroInserirTipoPagamento.getMessage());
        }
    }

    public ArrayList<TipoPagamento> consultarTipoPagamento() {

        Connection conn = new SQLite().conectar();
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<TipoPagamento> listaTiposPagamento = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT codigoTipoPagamento, descricaoPagamento FROM tipoPagamento;");
            result = stmt.executeQuery();
            while (result.next()) {
                TipoPagamento tipoPagamento = new TipoPagamento();
                tipoPagamento.setCodigoTipoPagamento(result.getInt("codigoTipoPagamento"));
                tipoPagamento.setDescricaoPagamento(result.getString("descricaoPagamento"));

                listaTiposPagamento.add(tipoPagamento);
                stmt.close();
                conn.close();
            }
            return listaTiposPagamento;
        } catch (SQLException erroConsultarTipoPagamento) {
            System.out.println(erroConsultarTipoPagamento.getMessage());
            return listaTiposPagamento;
        }
    }

    public void atualizarTipoPagamento(TipoPagamento tipoPagamento) {

        Connection conn = new SQLite().conectar();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE tipoPagamento SET descricaoPagamento = ?"
                    + " WHERE codigoTipoPagamento = ?;");
            stmt.setString(1, tipoPagamento.getDescricaoPagamento());
            stmt.setInt(2, tipoPagamento.getCodigoTipoPagamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarTipoPagamento) {
            System.out.println(erroAtualizarTipoPagamento.getMessage());
        }
    }

    public void deletarTipoPagamento(TipoPagamento tipoPagamento) {

        Connection conn = new SQLite().conectar();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM tipoPagamento WHERE codigoTipoPagamento = ?;");
            stmt.setInt(1, tipoPagamento.getCodigoTipoPagamento());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarTipoPagamento) {
            System.out.println(erroDeletarTipoPagamento.getMessage());
        }
    }

}
