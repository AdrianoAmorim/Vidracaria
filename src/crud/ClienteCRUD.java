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

    public String prepararQueryPesquisarClientes(String tipoCliente, JTextField... args) {
        int tam = args.length;

        String sql = "SELECT c.codCliente, c.tipoCliente, c.nome, c.cpf, c.rg, c.cnpj, "
                + "c.inscricaoEstadual, c.telFixo, c.telCel, c.email, c.status, c.situacao, "
                + "ec.logradouro, ec.numero, ec.complemento, ec.bairro, ec.cep, ec.cidade, "
                + "ec.uf FROM cliente c NATURAL INNER JOIN enderecoCliente ec ";

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
                        sql += "AND " + args[j].getName() + " LIKE '%" + args[j].getText().trim() + "%';";
                    }
                }
            }
        }
        sql += "AND tipoCliente = '" + tipoCliente + "' ;";
        return sql;
    }

    public ArrayList<Cliente> consultarCliente(String tipoCliente, JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarClientes(tipoCliente, args));

            result = stmt.executeQuery();

            while (result.next()) {
                Cliente cliente = new Cliente();

                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setTelFixo(result.getString("telFixo"));
                cliente.setTelCel(result.getString("telCel"));
                cliente.setEmail(result.getString("email"));
                cliente.setStatus(result.getInt("status"));
                cliente.setSituacao(result.getInt("situacao"));
                cliente.setLogradouro(result.getString("logradouro"));
                cliente.setNumero(result.getString("numero"));
                cliente.setComplemento(result.getString("complemento"));
                cliente.setBairro(result.getString("bairro"));
                cliente.setCep(result.getString("cep"));
                cliente.setCidade(result.getString("cidade"));
                cliente.setUf(result.getString("uf"));

                listaClientes.add(cliente);
            }
            stmt.close();

            return listaClientes;
        } catch (SQLException erroConsultarCliente) {
            System.out.println(erroConsultarCliente.getMessage());
            return listaClientes;
        }
    }

    public Cliente consultarNomeCliente(String nome) {

        PreparedStatement stmt;
        ResultSet result;
        Cliente cliente = new Cliente();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT c.codCliente, c.tipoCliente, c.nome, c.cpf, c.rg, c.cnpj, "
                    + "c.inscricaoEstadual, c.telFixo, c.telCel, c.email, c.status, c.situacao, "
                    + "ec.logradouro, ec.numero, ec.complemento, ec.bairro, ec.cep, ec.cidade, "
                    + "ec.uf FROM cliente c NATURAL INNER JOIN enderecoCliente ec "
                    + "WHERE c.nome = '" + nome + "';");

            result = stmt.executeQuery();
            while (result.next()) {
                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setTipoCliente(result.getString("tipoCliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setTelFixo(result.getString("telFixo"));
                cliente.setTelCel(result.getString("telCel"));
                cliente.setEmail(result.getString("email"));
                cliente.setStatus(result.getInt("status"));
                cliente.setSituacao(result.getInt("situacao"));
                cliente.setLogradouro(result.getString("logradouro"));
                cliente.setNumero(result.getString("numero"));
                cliente.setComplemento(result.getString("complemento"));
                cliente.setBairro(result.getString("bairro"));
                cliente.setCep(result.getString("cep"));
                cliente.setCidade(result.getString("cidade"));
                cliente.setUf(result.getString("uf"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroConsultarNomeCliente) {
            System.out.println(erroConsultarNomeCliente.getMessage());
        }
        return cliente;
    }

    public void atualizarCliente(Cliente cliente) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {

            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("UPDATE cliente SET tipoCliente = '" + cliente.getTipoCliente() + "', "
                    + "cpf = '" + cliente.getCpf() + "', cnpj = '" + cliente.getCnpj() + "', "
                    + "inscricaoEstadual = '" + cliente.getInscricaoEstadual() + "' , nome = '" + cliente.getNome() + "', "
                    + "rg = '" + cliente.getRg() + "', telFixo = '" + cliente.getTelFixo() + "', "
                    + "telCel = '" + cliente.getTelCel() + "', email = '" + cliente.getEmail() + "', "
                    + "status = " + cliente.getStatus() + ", situacao = " + cliente.getSituacao() + " "
                    + "WHERE codCliente = " + cliente.getCodCliente() + ";");

            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoCliente SET logradouro = '" + cliente.getLogradouro() + "', "
                    + "numero = " + cliente.getNumero() + ", complemento = '" + cliente.getComplemento() + "', "
                    + "bairro = '" + cliente.getBairro() + "', cep = '" + cliente.getCep() + "', "
                    + "cidade = '" + cliente.getCidade() + "', uf = '" + cliente.getUf() + "' "
                    + "WHERE codCliente = " + cliente.getCodCliente() + ";");

            stmt.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            
        } catch (SQLException erroAtualizarCliente) {
            JOptionPane.showMessageDialog(null, erroAtualizarCliente.getMessage());
        }
    }

}
