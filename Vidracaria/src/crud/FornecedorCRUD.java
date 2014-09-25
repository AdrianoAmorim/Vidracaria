package crud;

import database.SQLite;
import domain.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class FornecedorCRUD {

    // INSERT 
    public void inserirFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO fornecedor(codFornecedor, cnpjFornecedor, nomeFornecedor) "
                    + "VALUES (?,?,?);");

            stmt.setInt(1, fornecedor.getCodFornecedor());
            stmt.setString(2, fornecedor.getCnpjFornecedor());
            stmt.setString(3, fornecedor.getNomeFornecedor());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Empresa cadastrada com sucesso!");
        } catch (SQLException erroInserirFornecedor) {
            System.out.println(erroInserirFornecedor.getMessage());
        }
    }

    // UPDATE
    public void atualizarFornecedor(Fornecedor fornecedor) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE fornecedor SET cnpjFornecedor = ?, nomeFornecedor = ? "
                    + "WHERE codCompra = ?;");

            stmt.setString(1, fornecedor.getCnpjFornecedor());
            stmt.setString(2, fornecedor.getNomeFornecedor());
            stmt.setInt(3, fornecedor.getCodFornecedor());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarFornecedor) {
            System.out.println(erroAtualizarFornecedor.getMessage());
        }
    }

    // SELECT
    public ArrayList<Fornecedor> consultarFornecedor() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codFornecedor, cnpjFornecedor, nomeFornecedor FROM empresa;");

            result = stmt.executeQuery();
            while (result.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodFornecedor(fornecedor.getCodFornecedor());
                fornecedor.setCnpjFornecedor(fornecedor.getCnpjFornecedor());
                fornecedor.setNomeFornecedor(fornecedor.getNomeFornecedor());

                listaFornecedor.add(fornecedor);
                
                stmt.close();
                conn.close();
            }
            return listaFornecedor;
        } catch (SQLException erroConsultarFornecedor) {
            System.out.println(erroConsultarFornecedor.getMessage());
            return listaFornecedor;
        }
    }

    // DELETE
    public void deletarEmpresa(Fornecedor fornecedor) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE codFornecedor = ?;");

            stmt.setInt(1, fornecedor.getCodFornecedor());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarEmpresa) {
            System.out.println(erroDeletarEmpresa.getMessage());
        }
    }

}