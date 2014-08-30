/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import database.SQLite;
import domain.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author debian
 */
public class VendaCRUD {

    Connection conn = new SQLite().conectar();

    // metodo para controlar, pelo Java, a função de Auto Increment da Venda.
    public int retornarIncrement() {
        int increment = 0;

        ResultSet res;
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement("SELECT max(codigoVenda) FROM venda;");
            res = stmt.executeQuery();
            if (res.next()) {
                // guarda o valor atual do codigoProduto + 1
                increment = res.getInt("max(codigoVenda)") + 1;
            }
        } catch (SQLException erroReturnIncrement) {
            System.out.println(erroReturnIncrement.getMessage());
        }
        return increment;
    }

    public void inserirVenda(Venda venda) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO venda(codigoVenda, codigoProduto, quantidadeProduto, codigoCliente, codigoTipoPagamento, dataVenda)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, venda.getCodigoVenda());
            stmt.setInt(2, venda.getCodigoProduto());
            stmt.setDouble(3, venda.getQuantidadeProduto());
            stmt.setString(4, venda.getCodigoCliente());
            stmt.setInt(5, venda.getCodigoTipoPagamento());
            stmt.setString(6, venda.getDataVenda());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Venda cadastrada com sucesso!");
        } catch (SQLException erroInserirVenda) {
            System.out.println(erroInserirVenda.getMessage());
        }
    }

    public ArrayList<Venda> consultarVenda() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Venda> listaVendas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT codigoVenda, codigoProduto, quantidadeProduto, codigoCliente, codigoTipoPagamento, dataVenda FROM venda;");
            result = stmt.executeQuery();
            while (result.next()) {
                Venda venda = new Venda();
                venda.setCodigoVenda(result.getInt("codigoVenda"));
                venda.setCodigoProduto(result.getInt("codigoProduto"));
                venda.setQuantidadeProduto(result.getDouble("quantidadeProduto"));
                venda.setCodigoCliente(result.getString("codigoCliente"));
                venda.setCodigoTipoPagamento(result.getInt("codigoTipoPagamento"));
                venda.setDataVenda(result.getString("dataVenda"));

                listaVendas.add(venda);
                stmt.close();
            }
            return listaVendas;
        } catch (SQLException erroConsultarVenda) {
            System.out.println(erroConsultarVenda.getMessage());
            return listaVendas;
        }
    }

    public void atualizarVenda(Venda venda) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE venda SET codigoProduto = ?, quantidadeProduto = ?, codigoCliente = ?, codigoTipoPagamento = ?, dataVenda = ?"
                    + " WHERE codigoVenda = ?;");
            stmt.setInt(1, venda.getCodigoProduto());
            stmt.setDouble(2, venda.getQuantidadeProduto());
            stmt.setString(3, venda.getCodigoCliente());
            stmt.setInt(4, venda.getCodigoTipoPagamento());
            stmt.setString(5, venda.getDataVenda());
            stmt.setInt(6, venda.getCodigoVenda());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarVenda) {
            System.out.println(erroAtualizarVenda.getMessage());
        }
    }

    public void deletarVenda(Venda venda) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM venda WHERE codigoVenda = ?;");
            stmt.setInt(1, venda.getCodigoVenda());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException erroDeletarVenda) {
            System.out.println(erroDeletarVenda.getMessage());
        }
    }

}
