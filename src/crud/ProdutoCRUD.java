
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import database.SQLite;
import domain.Produto;
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
public class ProdutoCRUD {

    Connection conn = new SQLite().conectar();

    // metodo para controlar, pelo Java, a função de Auto Increment do Produto.
    public int retornarIncrement() {
        int increment = 0;

        ResultSet res;
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement("SELECT max(codigoProduto) FROM produto;");
            res = stmt.executeQuery();
            if (res.next()) {
                // guarda o valor atual do codigoProduto + 1
                increment = res.getInt("max(codigoProduto)") + 1;
            }
        } catch (SQLException erroReturnIncrement) {
            System.out.println(erroReturnIncrement.getMessage());
        }
        return increment;
    }

    public void inserirProduto(Produto produto) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO produto(codigoProduto, descricaoProduto, precoCusto, precoVenda, unidadeMedida)"
                    + " VALUES (?,?,?,?,?)");
            stmt.setInt(1, produto.getCodigoProduto());
            stmt.setString(2, produto.getDescricaoProduto());
            stmt.setDouble(3, produto.getPrecoCusto());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setString(5, produto.getUnidadeMedida());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erroInserirProduto) {
            System.out.println(erroInserirProduto.getMessage());
        }
    }

    public ArrayList<Produto> consultarProduto() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT codigoProduto, descricaoProduto, precoCusto, precoVenda, unidadeMedida"
                    + " FROM produto;");
            result = stmt.executeQuery();
            while (result.next()) {
                Produto produto = new Produto();
                produto.setCodigoProduto(result.getInt("codigoProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setPrecoCusto(result.getDouble("precoCusto"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));

                listaProdutos.add(produto);
            }
            stmt.close();
            return listaProdutos;
        } catch (SQLException erroConsultarProduto) {
            System.out.println(erroConsultarProduto.getMessage());
            return listaProdutos;
        }
    }

    public Produto consultarCodigoProduto(String codigo) {

        Produto produto = new Produto();
        PreparedStatement stmt;
        ResultSet result;

        try {
            stmt = conn.prepareStatement("SELECT codigoProduto, descricaoProduto, precoCusto, precoVenda, unidadeMedida"
                    + " FROM produto WHERE codigoProduto = '" + codigo + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                produto.setCodigoProduto(result.getInt("codigoProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setPrecoCusto(result.getDouble("precoCusto"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
            }
            stmt.close();
        } catch (SQLException erroConsultarCodigoProduto) {
            System.out.println(erroConsultarCodigoProduto.getMessage());
        }
        return produto;
    }

    public Produto consultarNomeProduto(String nome) {

        Produto produto = new Produto();
        PreparedStatement stmt;
        ResultSet result;

        try {
            stmt = conn.prepareStatement("SELECT codigoProduto, descricaoProduto, precoCusto, precoVenda, unidadeMedida"
                    + " FROM produto WHERE descricaoProduto = '" + nome + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                produto.setCodigoProduto(result.getInt("codigoProduto"));
                produto.setDescricaoProduto(result.getString("descricaoProduto"));
                produto.setPrecoCusto(result.getDouble("precoCusto"));
                produto.setPrecoVenda(result.getDouble("precoVenda"));
                produto.setUnidadeMedida(result.getString("unidadeMedida"));
            }
            stmt.close();
        } catch (SQLException erroConsultarCodigoProduto) {
            System.out.println(erroConsultarCodigoProduto.getMessage());
        }
        return produto;
    }

    public void atualizarProduto(Produto produto) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE produto SET descricaoProduto = ?, precoCusto = ?, precoVenda = ?, unidadeMedida = ?"
                    + " WHERE codigoProduto = ?;");
            stmt.setString(1, produto.getDescricaoProduto());
            stmt.setDouble(2, produto.getPrecoCusto());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setString(4, produto.getUnidadeMedida());
            stmt.setInt(5, produto.getCodigoProduto());

            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException erroAtualizarProduto) {
            System.out.println(erroAtualizarProduto.getMessage());
        }
    }

    public void deletarProduto(Produto produto) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM produto WHERE codigoProduto = ?;");
            stmt.setInt(1, produto.getCodigoProduto());

            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
        } catch (SQLException erroDeletarProduto) {
            System.out.println(erroDeletarProduto.getMessage());
        }
    }

}
