package crud;

import database.SQLite;
import domain.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author rafael
 */
public class CargoCRUD {

    // INSERT 
    public void inserirCargo(Cargo cargo) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO cargo(codCargo, descricaoCargo) "
                    + "VALUES (?, ?)");

            stmt.setInt(1, cargo.getCodigoCargo());
            stmt.setString(2, cargo.getDescricaoCargo());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Cargo cadastrado com sucesso!");
        } catch (SQLException erroInserirCargo) {
            System.out.println(erroInserirCargo.getMessage());
        }
    }

    // UPDATE
    public void atualizarCargo(Cargo cargo) {

        PreparedStatement stmt;
        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("UPDATE cargo SET descricaoCargo = ? "
                    + " WHERE codCargo = ?;");

            stmt.setString(1, cargo.getDescricaoCargo());
            stmt.setInt(2, cargo.getCodigoCargo());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (SQLException erroAtualizarCargo) {
            System.out.println(erroAtualizarCargo.getMessage());
        }
    }

    public String prepararQueryPesquisarCargos(JTextField... args) {
        int tam = args.length;

        String sql = "SELECT codCargo, descricao FROM cargo ";

        args[0].setName("codCargo");
        args[1].setName("descricao");

        // percorre os JTextFields até encontrar um preenchido
        for (int i = 0; i < tam; i++) {
            // quando encontrar um JTextField não vazio (preenchido)
            if (!args[i].getText().isEmpty()) {
                // incrementa a query de acordo com o nome e conteúdo do JTExtField
                if (args[i].getName().equalsIgnoreCase("codCargo")) {
                    sql += "WHERE " + args[i].getName() + " = " + Integer.parseInt(args[i].getText().trim()) + " ";
                } else {
                    sql += "WHERE " + args[i].getName() + " LIKE '%" + args[i].getText().trim() + "%' ";
                }
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

    // SELECT
    public ArrayList<Cargo> consultarCargo(JTextField... args) {

        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Cargo> listaCargos = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {

            int i = 0;

            stmt = conn.prepareStatement(prepararQueryPesquisarCargos(args));

            result = stmt.executeQuery();

            while (result.next()) {
                Cargo cargo = new Cargo();

                cargo.setCodigoCargo(result.getInt("codCargo"));
                cargo.setDescricaoCargo(result.getString("descricao"));

                listaCargos.add(cargo);
            }

            stmt.close();

            return listaCargos;
        } catch (SQLException erroConsultarCargo) {
            System.out.println(erroConsultarCargo.getMessage());
            return listaCargos;
        }
    }
}
