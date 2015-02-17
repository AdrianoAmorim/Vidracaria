package crud;

import database.SQLite;
import domain.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rafael
 */
public class FornecedorCRUD {

    public int ultimoIncrementFornecedor() {

        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            int increment = 0;

            // conta as linhas da tabela
            String sql = "SELECT COUNT(codFornecedor) FROM fornecedor";

            stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int linhas = result.getInt(1);

                // quando a tabela está vazia
                if (linhas == 0) {
                    sql = "SELECT last_value FROM fornecedor_codFornecedor_seq";

                    stmt = conn.prepareStatement(sql);
                    result = stmt.executeQuery();

                    if (result.next()) {
                        increment = result.getInt(1);
                        // retorna somente o last_value
                        return increment;
                    }
                } else {
                    // quando a tabela não está vazia
                    sql = "SELECT last_value FROM fornecedor_codFornecedor_seq";

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
        } catch (SQLException erroIncrementCodFornecedor) {
            JOptionPane.showMessageDialog(null, erroIncrementCodFornecedor.getMessage());
        }
        // em caso de erros não tratados
        return 0;
    }

    // INSERT 
    public boolean inserirFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("INSERT INTO fornecedor(cnpj, nome,"
                    + "telFixo, telCel, email, site, vendedor, ramal, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);");

            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getTelFixo());
            stmt.setString(4, fornecedor.getTelCel());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getSite());
            stmt.setString(7, fornecedor.getVendedor());
            stmt.setString(8, fornecedor.getRamal());
            stmt.setBoolean(9, fornecedor.getStatus());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("INSERT INTO enderecoFornecedor(logradouro, numero, "
                    + "complemento, bairro, cep, cidade, uf) "
                    + "VALUES (?,?,?,?,?,?,?);");

            stmt.setString(1, fornecedor.getLogradouro());
            stmt.setInt(2, fornecedor.getNumero());
            stmt.setString(3, fornecedor.getComplemento());
            stmt.setString(4, fornecedor.getBairro());
            stmt.setString(5, fornecedor.getCep());
            stmt.setString(6, fornecedor.getCidade());
            stmt.setString(7, fornecedor.getUf());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
            return true;
        } catch (SQLException erroInserirFornecedor) {
            JOptionPane.showMessageDialog(null, erroInserirFornecedor.getMessage());
            return false;
        }
    }

    // UPDATE
    public boolean atualizarFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("UPDATE fornecedor SET cnpj = ?, nome = ?, "
                    + "telFixo = ?, telCel = ?, email = ?, site = ?, vendedor = ?, "
                    + "ramal = ?, status = ? WHERE codFornecedor = ?;");

            stmt.setString(1, fornecedor.getCnpj());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getTelFixo());
            stmt.setString(4, fornecedor.getTelCel());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getSite());
            stmt.setString(7, fornecedor.getVendedor());
            stmt.setString(8, fornecedor.getRamal());
            stmt.setBoolean(9, fornecedor.getStatus());
            stmt.setInt(10, fornecedor.getCodFornecedor());

            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE enderecoFornecedor SET logradouro = ?, "
                    + "numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, uf = ? "
                    + "WHERE codFornecedor = ?;");

            stmt.setString(1, fornecedor.getLogradouro());
            stmt.setInt(2, fornecedor.getNumero());
            stmt.setString(3, fornecedor.getComplemento());
            stmt.setString(4, fornecedor.getBairro());
            stmt.setString(5, fornecedor.getCep());
            stmt.setString(6, fornecedor.getCidade());
            stmt.setString(7, fornecedor.getUf());
            stmt.setInt(8, fornecedor.getCodFornecedor());

            stmt.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!");
            return true;
        } catch (SQLException erroAtualizarFornecedor) {
            JOptionPane.showMessageDialog(null, erroAtualizarFornecedor.getMessage());
            return false;
        }
    }

    public String prepararQueryPesquisarFornecedor(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT f.codFornecedor, f.cnpj, f.nome, f.telFixo, "
                + "f.telCel, f.email, f.site, f.vendedor, f.ramal, "
                + "f.status, ef.logradouro, ef.numero, ef.complemento, "
                + "ef.bairro, ef.cep, ef.cidade, ef.uf FROM fornecedor f "
                + "NATURAL INNER JOIN enderecoFornecedor ef ";

        args[0].setName("f.codFornecedor");
        args[1].setName("f.nome");
        args[2].setName("f.cnpj");

        // percorre os JTextFields até encontrar um preenchido
        for (int i = 0; i < tam; i++) {
            // quando encontrar um JTextField não vazio (preenchido)
            //Acrescentei o desmascarar pq tava entrando sempre o cnpj
            if (!args[i].getText().isEmpty()) {
                // incrementa a query de acordo com o nome e conteúdo do JTExtField
                if (args[i].getName().equalsIgnoreCase("f.codFornecedor")) {
                    sql += "WHERE " + args[i].getName() + " = " + Integer.parseInt(args[i].getText().trim()) + " ";
                } else {
                    sql += "WHERE " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%' ";
                }

                /*
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
                 */
            }
        }
        sql += ";";
        // instrução para burlar o erro do compilador - nunca chega até aqui
        return sql;
    }

    public ArrayList<Fornecedor> consultarFornecedores(JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarFornecedor(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setCodFornecedor(result.getInt("codFornecedor"));
                fornecedor.setCnpj(result.getString("cnpj"));
                fornecedor.setNome(result.getString("nome"));
                fornecedor.setTelFixo(result.getString("telFixo"));
                fornecedor.setTelCel(result.getString("telCel"));
                fornecedor.setEmail(result.getString("email"));
                fornecedor.setSite(result.getString("site"));
                fornecedor.setVendedor(result.getString("vendedor"));
                fornecedor.setRamal(result.getString("ramal"));
                fornecedor.setStatus(result.getBoolean("status"));
                fornecedor.setLogradouro(result.getString("logradouro"));
                fornecedor.setNumero(result.getInt("numero"));
                fornecedor.setComplemento(result.getString("complemento"));
                fornecedor.setBairro(result.getString("bairro"));
                fornecedor.setCep(result.getString("cep"));
                fornecedor.setCidade(result.getString("cidade"));
                fornecedor.setUf(result.getString("uf"));

                listaFornecedores.add(fornecedor);
            }
            stmt.close();
            conn.close();

            return listaFornecedores;
        } catch (SQLException erroConsultarFornecedor) {
            System.out.println(erroConsultarFornecedor.getMessage());
            return listaFornecedores;
        }
    }

    public Fornecedor consultarFornecedor(String nome, int codigo) {
        Fornecedor fornecedor = new Fornecedor();
        try (Connection conn = new SQLite().conectar()) {
            PreparedStatement stmt;
            ResultSet result;

            stmt = conn.prepareStatement("SELECT f.codFornecedor, f.cnpj, f.nome,"
                    + "f.telFixo, f.telCel, f.email, f.site, f.vendedor, f.ramal, "
                    + "f.status, ef.logradouro, ef.numero, ef.complemento, ef.bairro, "
                    + "ef.cep, ef.cidade, ef.uf FROM fornecedor f CROSS JOIN enderecoFornecedor ef "
                    + "WHERE f.codFornecedor = ef.codFornecedor "
                    + "AND f.nome = '" + nome + "' OR f.codFornecedor = " + codigo +";");

            result = stmt.executeQuery();

            while (result.next()) {
                fornecedor.setCodFornecedor(result.getInt("codFornecedor"));
                fornecedor.setCnpj(result.getString("cnpj"));
                fornecedor.setNome(result.getString("nome"));
                fornecedor.setTelFixo(result.getString("telFixo"));
                fornecedor.setTelCel(result.getString("telCel"));
                fornecedor.setEmail(result.getString("email"));
                fornecedor.setSite(result.getString("site"));
                fornecedor.setVendedor(result.getString("vendedor"));
                fornecedor.setRamal(result.getString("ramal"));
                fornecedor.setStatus(result.getBoolean("status"));
                fornecedor.setLogradouro(result.getString("logradouro"));
                fornecedor.setNumero(result.getInt("numero"));
                fornecedor.setComplemento(result.getString("complemento"));
                fornecedor.setBairro(result.getString("bairro"));
                fornecedor.setCep(result.getString("cep"));
                fornecedor.setCidade(result.getString("cidade"));
                fornecedor.setUf(result.getString("uf"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException erroConsultarFuncPorNome) {
            JOptionPane.showMessageDialog(null, erroConsultarFuncPorNome.getMessage());
        }
        return fornecedor;
    }
}
