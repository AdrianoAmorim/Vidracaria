/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import database.SQLite;
import domain.Cliente;
import domain.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author debian
 */
public class EstoqueCRUD {

    Connection conn = new SQLite().conectar();

    public void inserirEstoque(Estoque estoque) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO estoque(codigoProduto, quantidadeAtual, quantidadeMinima)"
                    + " VALUES (?,?,?);");

            stmt.setString(1, estoque.getCodigoProduto());
            stmt.setDouble(2, estoque.getQuantidadeAtual());
            stmt.setDouble(3, estoque.getQuantidadeMinima());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Estoque cadastrado com sucesso!");
        } catch (SQLException erroInserirEstoque) {
            System.out.println(erroInserirEstoque.getMessage());
        }
    }

    public ArrayList<Estoque> consultarEstoque() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Estoque> listaEstoque = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT codigoProduto, quantidadeAtual, quantidadeMinima FROM estoque;");
            result = stmt.executeQuery();
            while (result.next()) {
                Estoque estoque = new Estoque();
                estoque.setCodigoProduto(result.getString("codigoProduto"));
                estoque.setQuantidadeAtual(result.getDouble("quantidadeAtual"));
                estoque.setQuantidadeMinima(result.getDouble("quantidadeMinima"));

                listaEstoque.add(estoque);
            }
            stmt.close();
            return listaEstoque;
        } catch (SQLException erroConsultarEstoque) {
            System.out.println(erroConsultarEstoque.getMessage());
            return listaEstoque;
        }
    }

    public void atualizarEstoque(Estoque estoque) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE estpque SET quantidadeAtual = ?, quantidadeMinima = ?"
                    + " WHERE codigoProduto = ?;");

            stmt.setDouble(1, estoque.getQuantidadeAtual());
            stmt.setDouble(2, estoque.getQuantidadeMinima());
            stmt.setString(3, estoque.getCodigoProduto());

            stmt.executeUpdate();
            stmt.close();
            
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarEstoque) {
            System.out.println(erroAtualizarEstoque.getMessage());
        }
    }

    public void deletarEstoque(Estoque estoque) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM estoque WHERE codigoProduto = ?");
            stmt.setString(1, estoque.getCodigoProduto());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException erroDeletarEstoque) {
            System.out.println(erroDeletarEstoque.getMessage());
        }
    }
}
