package crud;

import database.SQLite;
import domain.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class EmpresaCRUD {

    // INSERT 
    public void inserirEmpresa(Empresa empresa) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO empresa(codEmpresa, cnpjEmpresa, razaoSocial, nomeFantasia) "
                    + "VALUES (?,?,?,?);");

            stmt.setInt(1, empresa.getCodEmpresa());
            stmt.setString(2, empresa.getCnpjEmpresa());
            stmt.setString(3, empresa.getRazaoSocial());
            stmt.setString(4, empresa.getNomeFantasia());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Empresa cadastrada com sucesso!");
        } catch (SQLException erroInserirEmpresa) {
            System.out.println(erroInserirEmpresa.getMessage());
        }
    }

    // UPDATE
    public void atualizarEmpresa(Empresa empresa) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE empresa SET cnpjEmpresa = ?, razaoSocial = ?, nomeFantasia = ? "
                    + "WHERE codCompra = ?;");

            stmt.setString(1, empresa.getCnpjEmpresa());
            stmt.setString(2, empresa.getNomeFantasia());
            stmt.setString(3, empresa.getNomeFantasia());
            stmt.setInt(4, empresa.getCodEmpresa());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarEmpresa) {
            System.out.println(erroAtualizarEmpresa.getMessage());
        }
    }

    // SELECT
    public ArrayList<Empresa> consultarEmpresa() {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Empresa> listaEmpresas = new ArrayList<>();
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codEmpresa, cnpjEmpresa, razaoSocial, nomeFantasia FROM empresa;");
            
            result = stmt.executeQuery();
            while (result.next()) {
                Empresa empresa = new Empresa();
                empresa.setCodEmpresa(result.getInt("codEmpresa"));
                empresa.setCnpjEmpresa(result.getString("cnpjEmpresa"));
                empresa.setRazaoSocial(result.getString("razaoSocial"));
                empresa.setNomeFantasia(result.getString("nomeFantasia"));

                listaEmpresas.add(empresa);
                stmt.close();
                conn.close();
            }
            return listaEmpresas;
        } catch (SQLException erroConsultarEmpresas) {
            System.out.println(erroConsultarEmpresas.getMessage());
            return listaEmpresas;
        }
    }

    // DELETE
    public void deletarEmpresa(Empresa empresa) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM empresa WHERE codEmpresa = ?;");

            stmt.setInt(1, empresa.getCodEmpresa());
 
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarEmpresa) {
            System.out.println(erroDeletarEmpresa.getMessage());
        }
    }
}
