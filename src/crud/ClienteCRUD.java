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
import view.FrmPrincipal;

/**
 *
 * @author debian
 */
public class ClienteCRUD {

    public int ultimoIncrementCliente() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codCliente) FROM cliente";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM cliente_codCliente_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM cliente_codCliente_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1) + 1;
                        // retorna o last_value + 1
                        return increment;
                    }
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodCliente) {
            JOptionPane.showMessageDialog(null, erroIncrementCodCliente.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    public boolean inserirCliente(Cliente cliente) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO cliente(tipoCliente, cpf, cnpj, "
                    + "inscricaoEstadual, nome, rg, telFixo, telCel, email, status)"
                    + "  VALUES (?,?,?,?,?,?,?,?,?,?);");

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

            System.out.println(stmt);

            stmt = conn.prepareStatement("INSERT INTO enderecoCliente(logradouro, numero, "
                    + "complemento, bairro, cep, cidade, uf) "
                    + "VALUES (?,?,?,?,?,?,?);");

            stmt.setString(1, cliente.getLogradouro());
            stmt.setInt(2, cliente.getNumero());
            stmt.setString(3, cliente.getComplemento());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getUf());

            stmt.executeUpdate();

            System.out.println(stmt);

            conn.commit();
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirCliente) {
            JOptionPane.showMessageDialog(null, erroInserirCliente.getMessage());
            return false;
        }
    }

    public String prepararQueryPesquisarClientes(String tipoCliente, JTextField... args) {

        String sql = "SELECT c.codCliente, c.tipoCliente, c.nome, c.cpf, c.rg, c.cnpj, "
                + "c.inscricaoEstadual, c.telFixo, c.telCel, c.email, c.status, "
                + "ec.logradouro, ec.numero, ec.complemento, ec.bairro, ec.cep, ec.cidade, "
                + "ec.uf FROM cliente c CROSS JOIN enderecoCliente ec "
                + "WHERE c.codCliente = ec.codCliente AND c.tipoCliente = '" + tipoCliente + "' ";

        args[0].setName("c.codCliente");
        args[1].setName("c.nome");
        if (tipoCliente.equalsIgnoreCase("F")) {
            args[2].setName("c.cpf");
            args[3].setName("c.rg");
        } else if (tipoCliente.equalsIgnoreCase("J")) {
            args[2].setName("c.cnpj");
            args[3].setName("c.inscricaoestadual");
        }
        args[4].setName("c.telfixo");
        args[5].setName("c.telcel");

        // percorre todos os JTextFields
        for (int i = 0; i < args.length; i++) {
            if (args[i].getName().equalsIgnoreCase("c.codCliente") && !FrmPrincipal.desmascarar(args[i].getText()).trim().isEmpty()) {
                // caso o parametro seja o codCliente é necessário usar (Cast) e comparação exata (=)
                sql += "AND " + args[i].getName() + " = " + Integer.parseInt(args[i].getText().trim()) + " ";
            } else if (!FrmPrincipal.desmascarar(args[i].getText()).trim().isEmpty()) {
                // demais parametros não usam cast e são comparados por aproximação (%LIKE%)
                sql += "AND " + args[i].getName() + " LIKE '%" + FrmPrincipal.desmascarar(args[i].getText()).trim() + "%' ";
            }
        }

        sql += ";";
        System.out.println(sql);

        return sql;
    }

    public ArrayList<Cliente> consultarCliente(String tipoCliente, JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement(prepararQueryPesquisarClientes(tipoCliente, args));

            result = stmt.executeQuery();

            while (result.next()) {
                Cliente cliente = new Cliente();

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
                cliente.setNumero(result.getInt("numero"));
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
                    + "c.inscricaoEstadual, c.telFixo, c.telCel, c.email, c.status, "
                    + "ec.logradouro, ec.numero, ec.complemento, ec.bairro, ec.cep, ec.cidade, "
                    + "ec.uf FROM cliente c NATURAL INNER JOIN enderecoCliente ec "
                    + "WHERE c.nome = '" + nome + "' OR c.codCliente = " + codigoCliente + ";");

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
                cliente.setNumero(result.getInt("numero"));
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

    public boolean atualizarCliente(Cliente cliente) {

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
            stmt.setInt(11, cliente.getCodCliente());
            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoCliente SET logradouro = ?, "
                    + "numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, uf = ? "
                    + "WHERE codCliente = ?;");

            stmt.setString(1, cliente.getLogradouro());
            stmt.setInt(2, cliente.getNumero());
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
            return true;
        } catch (SQLException erroAtualizarCliente) {
            JOptionPane.showMessageDialog(null, erroAtualizarCliente.getMessage());
            return false;
        }
    }

}
