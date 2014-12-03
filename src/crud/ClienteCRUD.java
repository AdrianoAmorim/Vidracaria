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

            if (result.next()) {
                increment = result.getInt(1);
            }

            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodCliente) {
            JOptionPane.showMessageDialog(null, erroIncrementCodCliente.getMessage());
        }
        return increment + 1;
    }

    public void inserirCliente(Cliente cliente, Endereco enderecoCliente) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO cliente(codCliente, tipoCliente, cpf, cnpj, "
                    + "inscricaoEstadual, nome, rg, telFixo, telCel, email, status)"
                    + "  VALUES (?,?,?,?,?,?,?,?,?,?,?);");

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
            stmt.setBoolean(11, cliente.isStatus());

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
        if (!tipoCliente.isEmpty()) {
            sql += "AND tipoCliente = '" + tipoCliente + "' ;";
        }
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
                cliente.setCnpj(result.getString("cnpj"));
                cliente.setInscricaoEstadual(result.getString("inscricaoEstadual"));
                cliente.setTelFixo(result.getString("telFixo"));
                cliente.setTelCel(result.getString("telCel"));
                cliente.setEmail(result.getString("email"));
                cliente.setStatus(result.getBoolean("status"));
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

    public Cliente consultarCliente(String nome, int codigoCliente) {

        PreparedStatement stmt;
        ResultSet result;
        Cliente cliente = new Cliente();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT c.codCliente, c.tipoCliente, c.nome, c.cpf, c.rg, c.cnpj, "
                    + "c.inscricaoEstadual, c.telFixo, c.telCel, c.email, c.status, c.situacao, "
                    + "ec.logradouro, ec.numero, ec.complemento, ec.bairro, ec.cep, ec.cidade, "
                    + "ec.uf FROM cliente c NATURAL INNER JOIN enderecoCliente ec "
                    + "WHERE c.nome = '" + nome + "' OR c.codigoCliente = " + codigoCliente + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                cliente.setCodCliente(result.getInt("codCliente"));
                cliente.setTipoCliente(result.getString("tipoCliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setRg(result.getString("rg"));
                cliente.setCnpj(result.getString("cnpj"));
                cliente.setInscricaoEstadual(result.getString("inscricaoEstadual"));
                cliente.setTelFixo(result.getString("telFixo"));
                cliente.setTelCel(result.getString("telCel"));
                cliente.setEmail(result.getString("email"));
                cliente.setStatus(result.getBoolean("status"));
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

            stmt = conn.prepareStatement("UPDATE cliente SET tipoCliente = ?, cpf = ?, cnpj = ?, inscricaoEstadual = ?, "
                    + "nome = ?, rg = ?, telFixo = ?, telCel = ?, email = ?, status = ?"
                    + "WHERE codCliente = ?;");

            stmt.setString(1, cliente.getTipoCliente());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getCnpj());
            stmt.setString(4, cliente.getInscricaoEstadual());
            stmt.setString(5, cliente.getNome());
            stmt.setString(6, cliente.getRg());
            stmt.setString(7, cliente.getTelFixo());
            stmt.setString(8, cliente.getTelCel());
            stmt.setString(9, cliente.getEmail());
            stmt.setBoolean(10, cliente.isStatus());
            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoCliente SET logradouro = ?, "
                    + "numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, uf = ? "
                    + "WHERE codCliente = ?;");

            stmt.setString(1, cliente.getLogradouro());
            stmt.setString(2, cliente.getNumero());
            stmt.setString(3, cliente.getComplemento());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getUf());
            stmt.setInt(8, cliente.getCodCliente());

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
