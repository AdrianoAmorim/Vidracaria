package crud;

import database.SQLite;
import domain.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class FuncionarioCRUD {

        public int incrementCodFuncionario() {
        PreparedStatement stmt;
        Connection conn = new SQLite().conectar();
        int increment = 0;
        
        try {
            stmt = conn.prepareStatement("SELECT MAX(codFuncionario) FROM funcionario;");
            ResultSet result = stmt.executeQuery();
            increment = result.getInt(1);
            
            stmt.close();
            conn.close();
        } catch (SQLException erroIncrementCodFuncionario) {
            JOptionPane.showMessageDialog(null, "Erro ao incrementar o codigo do funcionario");
        }
        return increment + 1;
    }
        
    // INSERT 
    public void inserirFuncionario(Funcionario funcionario) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO funcionario(codFuncionario, codCargo, codEmpresa, "
                    + " nomeFuncionario, salarioFuncionario) VALUES (?,?,?,?,?);");

            stmt.setInt(1, funcionario.getCodFuncionario());
            stmt.setInt(2, funcionario.getCodEmpresa());
            stmt.setInt(3, funcionario.getCodCargo());
            stmt.setString(4, funcionario.getNomeFuncionario());
            stmt.setDouble(5, funcionario.getSalarioFuncionario());

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
        } catch (SQLException erroInserirFuncionario) {
            System.out.println(erroInserirFuncionario.getMessage());
        }
    }

    // UPDATE
    public void atualizarFornecedor(Funcionario funcionario) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE funcionario SET codEmpresa = ?, codCargo = ?, "
                    + "nomeFuncionario = ?, salarioFuncionario = ? WHERE codFuncionario = ?;");

            stmt.setInt(1, funcionario.getCodEmpresa());
            stmt.setInt(2, funcionario.getCodCargo());
            stmt.setString(3, funcionario.getNomeFuncionario());
            stmt.setDouble(4, funcionario.getSalarioFuncionario());
            stmt.setInt(5, funcionario.getCodFuncionario());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarFuncionario) {
            System.out.println(erroAtualizarFuncionario.getMessage());
        }
    }

    // SELECT
    public ArrayList<Funcionario> consultarFuncionario() {

        PreparedStatement stmt;
        ResultSet result;
        
        ArrayList<Funcionario> listaFuncionario = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codFuncionario, codEmpresa, codCargo, nomeFuncionario, salarioFuncionario "
                    + "FROM funcionario;");

            result = stmt.executeQuery();
            while (result.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setCodFuncionario(result.getInt("codFuncionario"));
                funcionario.setCodEmpresa(result.getInt("codEmpresa"));
                funcionario.setCodCargo(result.getInt("codCargo"));
                funcionario.setNomeFuncionario(result.getString("nomeFuncionario"));
                funcionario.setSalarioFuncionario(result.getDouble("salarioFuncionario"));

                listaFuncionario.add(funcionario);

                stmt.close();
                conn.close();
            }
            return listaFuncionario;
        } catch (SQLException erroConsultarFuncionario) {
            System.out.println(erroConsultarFuncionario.getMessage());
            return listaFuncionario;
        }
    }

    // DELETE
    public void deletarFuncionario(Funcionario funcionario) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("DELETE FROM funcionario WHERE codFuncionario = ?;");

            stmt.setInt(1, funcionario.getCodFuncionario());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException erroDeletarFuncionario) {
            System.out.println(erroDeletarFuncionario.getMessage());
        }
    }

}
