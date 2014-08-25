/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import database.SQLite;
import domain.Cliente;
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
public class ClienteCRUD {

    Connection conn = new SQLite().conectar();

    public void inserirCliente(Cliente cliente) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO cliente(cpf,nome,rg,endereco,telResidencial,telCelular)"
                    + " VALUES (?,?,?,?,?,?);");

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelResidencial());
            stmt.setString(6, cliente.getTelCelular());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException erroInserirCliente) {
            System.out.println(erroInserirCliente.getMessage());
        }
    }

    public ArrayList<Cliente> consultarCliente() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT cpf, nome, rg, endereco, telResidencial, telCelular"
                    + " FROM cliente;");
            result = stmt.executeQuery();
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setEndereco(result.getString("endereco"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));

                listaClientes.add(cliente);
            }
            stmt.close();
            return listaClientes;
        } catch (SQLException erroConsultarCliente) {
            System.out.println(erroConsultarCliente.getMessage());
            return listaClientes;
        }
    }

    public Cliente consultarCpfCliente(String cpf) {

        Cliente cliente = new Cliente();
        PreparedStatement stmt;
        ResultSet result;

        try {
            stmt = conn.prepareStatement("SELECT cpf, nome, rg, endereco, telResidencial, telCelular"
                    + " FROM cliente WHERE cpf = '" + cpf + "';");

            result = stmt.executeQuery();
            while (result.next()) {

                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setEndereco(result.getString("endereco"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));

            }
            stmt.close();
        } catch (SQLException erroConsultarCpfCliente) {
            System.out.println(erroConsultarCpfCliente.getMessage());
        }
        return cliente;
    }

    public Cliente consultarNomeCliente(String nome) {

        Cliente cliente = new Cliente();
        PreparedStatement stmt;
        ResultSet result;

        try {
            stmt = conn.prepareStatement("SELECT cpf, nome, rg, endereco, telResidencial, telCelular"
                    + " FROM cliente WHERE nome = '" + nome + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setEndereco(result.getString("endereco"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));
            }
            stmt.close();
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return cliente;
    }

    public void atualizarCliente(Cliente cliente) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, endereco = ?, rg = ?, telResidencial = ?, telCelular = ?"
                    + " WHERE cpf = ?;");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getTelResidencial());
            stmt.setString(5, cliente.getTelCelular());
            stmt.setString(6, cliente.getCpf());

            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException erroAtualizarCliente) {
            System.out.println(erroAtualizarCliente.getMessage());
        }
    }

    public void deletarCliente(Cliente cliente) {

        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM cliente WHERE nome = ? AND cpf = ?;");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());

            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
        } catch (SQLException erroDeletarCliente) {
            System.out.println(erroDeletarCliente.getMessage());
        }
    }

}
