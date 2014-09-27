package crud;

import database.SQLite;
import domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author debian
 */
public class ClienteCRUD {

    public int incrementCodCliente() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;
        
        try {
            stmt = conn.prepareStatement("SELECT MAX(codCliente) FROM cliente;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);
        } catch (SQLException erroIncrementCodCliente) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do cliente");
        }
        return increment + 1;
    }

    public void inserirCliente(Cliente cliente) {

        PreparedStatement stmt;

        Connection conn = new SQLite().conectar();

        try {
            stmt = conn.prepareStatement("INSERT INTO cliente(codCliente, cpfCliente, nomeCliente, rgCliente, "
                    + "enderecoCliente, telResidencial, telCelular) "
                    + "VALUES (?,?,?,?,?,?,?);");

            stmt.setInt(1, cliente.getCodCliente());
            stmt.setString(2, cliente.getCpfCliente());
            stmt.setString(3, cliente.getNomeCliente());
            stmt.setString(4, cliente.getRgCliente());
            stmt.setString(5, cliente.getEnderecoCliente());
            stmt.setString(6, cliente.getTelResidencial());
            stmt.setString(7, cliente.getTelCelular());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException erroInserirCliente) {
            System.out.println(erroInserirCliente.getErrorCode());
        }
    }

    public ArrayList<Cliente> consultarTodosCliente() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codCliente, cpfCliente, nomeCliente, rgCliente, "
                    + "enderecoCliente, telResidencial, telCelular FROM cliente;");
            result = stmt.executeQuery();
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setNomeCliente(result.getString("nomeCliente"));
                cliente.setCpfCliente(result.getString("cpfCliente"));
                cliente.setRgCliente(result.getString("rgCliente"));
                cliente.setEnderecoCliente(result.getString("enderecoCliente"));
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

//Consulta o cliente passando todos ou um parametro
    public ArrayList<Cliente> consultarCliente(Cliente cliente) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listCliente = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            // FALTA IMPLEMENTAR OS IFS para pesquisar utilizando todos os campos
            stmt = conn.prepareStatement("SELECT codCliente, nomeCliente, cpfCliente, rgCliente, enderecoCliente, telResidencial, "
                    + "telCelular FROM cliente WHERE CodCliente = ?;");

            stmt.setInt(1, cliente.getCodCliente());

            result = stmt.executeQuery();

            while (result.next()) {
                // reseta os valores do objeto cliente
                cliente = new Cliente();

                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setNomeCliente(result.getString("nomeCliente"));
                cliente.setCpfCliente(result.getString("cpfCliente"));
                cliente.setRgCliente(result.getString("rgCliente"));
                cliente.setEnderecoCliente(result.getString("enderecoCliente"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));

                listCliente.add(cliente);
            }
            stmt.close();
            result.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Consulta Detalhada de Clientes.");
        }

        return listCliente;
    }

    public Cliente consultarCpfCliente(String cpfCliente) {

        PreparedStatement stmt;
        ResultSet result;
        Cliente cliente = new Cliente();

        try {
            try (Connection conn = new SQLite().conectar()) {
                stmt = conn.prepareStatement("SELECT codCliente, cpfCliente, nomeCliente, rgCliente, "
                        + "enderecoCliente, telResidencial, telCelular FROM cliente WHERE cpf = '" + cpfCliente + "';");

                result = stmt.executeQuery();
                while (result.next()) {

                    cliente.setCodCliente(result.getInt("codCliente"));
                    cliente.setNomeCliente(result.getString("nomeCliente"));
                    cliente.setCpfCliente(result.getString("cpfCliente"));
                    cliente.setRgCliente(result.getString("rgCliente"));
                    cliente.setEnderecoCliente(result.getString("enderecoCliente"));
                    cliente.setTelResidencial(result.getString("telResidencial"));
                    cliente.setTelCelular(result.getString("telCelular"));
                }
                stmt.close();
            }
        } catch (SQLException erroConsultarCpfCliente) {
            System.out.println(erroConsultarCpfCliente.getMessage());
        }
        return cliente;
    }

    public ArrayList<Cliente> consultarCpfClienteAprox(String cpfCliente) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listCliente = new ArrayList<>();

        try {
            try (Connection conn = new SQLite().conectar()) {
                stmt = conn.prepareStatement("SELECT codCliente, cpfCliente, nomeCliente, rgCliente, "
                        + "enderecoCliente, telResidencial, telCelular FROM cliente WHERE cpfCliente LIKE '" + cpfCliente + "%';");

                result = stmt.executeQuery();
                while (result.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(result.getInt("codcliente"));
                    cliente.setNomeCliente(result.getString("nomeCliente"));
                    cliente.setCpfCliente(result.getString("cpfCliente"));
                    cliente.setRgCliente(result.getString("rgCliente"));
                    cliente.setEnderecoCliente(result.getString("enderecoCliente"));
                    cliente.setTelResidencial(result.getString("telResidencial"));
                    cliente.setTelCelular(result.getString("telCelular"));
                    listCliente.add(cliente);
                }
                stmt.close();
            }
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return listCliente;
    }

    public Cliente consultarNomeCliente(String nomeCliente) {

        PreparedStatement stmt;
        ResultSet result;
        Cliente cliente = new Cliente();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codcliente, cpfCliente, nomeCliente, rgCliente, "
                    + "enderecoCliente, telResidencial, telCelular FROM cliente WHERE nome = '" + nomeCliente + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setNomeCliente(result.getString("nomeCliente"));
                cliente.setCpfCliente(result.getString("cpfCliente"));
                cliente.setRgCliente(result.getString("rgCliente"));
                cliente.setEnderecoCliente(result.getString("enderecoCliente"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));
            }
            stmt.close();
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return cliente;
    }

    public ArrayList<Cliente> consultarNomeClienteAprox(String nomeCliente) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listCliente = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codCliente, cpfCliente, nomeCliente, rgCliente, "
                    + "enderecoCliente, telResidencial, telCelular FROM cliente WHERE nome LIKE '" + nomeCliente + "%';");

            result = stmt.executeQuery();
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setNomeCliente(result.getString("nomeCilente"));
                cliente.setCpfCliente(result.getString("cpfCliente"));
                cliente.setRgCliente(result.getString("rgCliente"));
                cliente.setEnderecoCliente(result.getString("enderecoCliente"));
                cliente.setTelResidencial(result.getString("telResidencial"));
                cliente.setTelCelular(result.getString("telCelular"));
                listCliente.add(cliente);
            }
            stmt.close();
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return listCliente;
    }

    public void atualizarCliente(Cliente cliente) {

        PreparedStatement stmt;
        ArrayList<Cliente> listCliente = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE cliente SET nomeCliente = ?, cpfCliente = ?, rgCliente = ?, "
                    + "enderecoCliente = ?, telResidencial = ?, telCelular = ? WHERE codCliente = ?;");

            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCpfCliente());
            stmt.setString(3, cliente.getRgCliente());
            stmt.setString(4, cliente.getEnderecoCliente());
            stmt.setString(5, cliente.getTelResidencial());
            stmt.setString(6, cliente.getTelCelular());
            stmt.setInt(7, cliente.getCodCliente());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException erroAtualizarCliente) {
            System.out.println(erroAtualizarCliente.getMessage());
        }
    }

    public void deletarCliente(Cliente cliente) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM cliente WHERE codCliente = ?;");
            stmt.setInt(1, cliente.getCodCliente());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
        } catch (SQLException erroDeletarCliente) {
            System.out.println(erroDeletarCliente.getMessage());
        }
    }

}
