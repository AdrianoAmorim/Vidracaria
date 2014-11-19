package crud;

import database.SQLite;
import domain.Cliente;
import domain.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodCliente) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do cliente");
        }
        return increment + 1;
    }

    public void inserirCliente(Cliente cliente, Endereco enderecoCliente) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO cliente(codCliente, tipoCliente, cpf, cnpj, "
                    + "inscricaoEstadual, nome, rg, telFixo, telCel, email, status, situacao)"
                    + "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");

            stmt.setInt(1, cliente.getCodCliente());
            stmt.setString(2, cliente.getTipoCliente());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getCnpj());
            stmt.setString(5, cliente.getInscricaoEstadual());
            stmt.setString(6, cliente.getNome());
            stmt.setString(7, cliente.getRg());
            stmt.setString(8, cliente.getTelFixo());
            stmt.setString(9, cliente.getTelCel());
            stmt.setString(10, cliente.getEmail());
            stmt.setInt(11, cliente.getStatus());
            stmt.setInt(12, cliente.getSituacao());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO enderecoCliente(codCliente, logradouro, numero, "
                    + "complemento, bairro, cep, cidade, uf) "
                    + "VALUES (?,?,?,?,?,?,?,?);");

            stmt.setInt(1, enderecoCliente.getCod());
            stmt.setString(2, enderecoCliente.getLogradouro());
            stmt.setString(3, enderecoCliente.getNumero());
            stmt.setString(4, enderecoCliente.getComplemento());
            stmt.setString(5, enderecoCliente.getBairro());
            stmt.setString(6, enderecoCliente.getCep());
            stmt.setString(7, enderecoCliente.getCidade());
            stmt.setString(8, enderecoCliente.getUf());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException erroInserirCliente) {
            JOptionPane.showMessageDialog(null, erroInserirCliente.getMessage());
        }
    }

    public String prepararQueryPesquisarClientes(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT codCliente, tipoCliente, nome, cpf, rg, cnpj, "
                + "inscricaoEstadual, telFixo, telCel, email, status, situacao "
                + "FROM cliente ";

        args[0].setName("codCliente");
        args[1].setName("nome");
        args[2].setName("cpf");
        args[3].setName("rg");
        args[4].setName("telFixo");
        args[5].setName("telCel");

        // percorre os JTextFields até encontrar um preenchido
        for (int i = 0; i < tam; i++) {
            // quando encontrar um JTextField não vazio (preenchido)
            if (!args[i].getText().isEmpty()) {
                // incrementa a query de acordo com o nome e conteúdo do JTExtField
                sql += "WHERE " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%'";
                // percorre novamente o vetor em busca de outro JTextField preenchido
                for (int j = 0; j < tam; j++) {
                    // quando encontrar um JTextField preenchido (que não seja o encontrado anteriormente)
                    if (!args[j].getText().isEmpty() && (!args[j].getText().equals(args[i].getText()))) {
                        // incrementa a query de acordo com o nome e conteúdo do JTextField
                        sql += "AND " + args[j].getName() + " LIKE '%" + args[j].getText().trim() + "%'";

                        // retorna a query montada
                        return sql;
                    }
                }
            }
        }
        // instrução para burlar o erro do compilador - nunca chega até aqui
        return sql;
    }

    public ArrayList<Cliente> consultarCliente(JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarClientes(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Cliente novoCliente = new Cliente();

                novoCliente.setCodCliente(result.getInt("codCliente"));
                novoCliente.setNome(result.getString("nome"));
                novoCliente.setCpf(result.getString("cpf"));
                novoCliente.setRg(result.getString("rg"));
                novoCliente.setTelFixo(result.getString("telFixo"));
                novoCliente.setTelCel(result.getString("telCel"));
                novoCliente.setEmail(result.getString("email"));
                novoCliente.setStatus(result.getInt("status"));
                novoCliente.setSituacao(result.getInt("situacao"));

                listaClientes.add(novoCliente);
            }
            stmt.close();

            return listaClientes;
        } catch (SQLException erroConsultarCliente) {
            System.out.println(erroConsultarCliente.getMessage());
            return listaClientes;
        }
    }

//Consulta o cliente passando todos ou um parametro/*
    /*public ArrayList<Cliente> consultarCliente(Cliente cliente) {

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
     } catch (SQLException erroConsultarCpfCliente) {
     System.out.println(erroConsultarCpfCliente.getMessage());
     }
     return listCliente;
     }*/
    public Cliente consultarNomeCliente(String nome) {

        PreparedStatement stmt;
        ResultSet result;
        Cliente cliente = new Cliente();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codCliente, tipoCliente, cpf, cnpj, inscricaoEstadual,"
                    + "nome, rg, telFixo, telCel, status, email FROM cliente WHERE nome = '" + nome + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                cliente.setCodCliente(result.getInt("codCliente"));
                // TIPO NÃO IMPLEMENTADO
                cliente.setTipoCliente(result.getString("tipoCliente"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setCnpj(result.getString("cnpj"));
                cliente.setInscricaoEstadual(result.getString("inscricaoEstadual"));
                cliente.setNome(result.getString("nome"));
                cliente.setRg(result.getString("rg"));
                cliente.setTelFixo(result.getString("telFixo"));
                cliente.setTelCel(result.getString("telCel"));
                cliente.setStatus(result.getInt("status"));
                cliente.setEmail(result.getString("email"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return cliente;
    }

    /*    public ArrayList<Cliente> consultarNomeClienteAprox(String nomeCliente) {

     PreparedStatement stmt;
     ResultSet result;
     ArrayList<Cliente> listCliente = new ArrayList<>();

     try (Connection conn = new SQLite().conectar()) {
     stmt = conn.prepareStatement("SELECT codCliente, cpfCliente, nomeCliente, rgCliente, "
     + "enderecoCliente, telResidencial, telCelular FROM cliente WHERE nomeCliente LIKE '" + nomeCliente + "%';");

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
     }*/
}
