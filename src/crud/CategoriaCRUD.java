package crud;

import database.SQLite;
import domain.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class CategoriaCRUD {

    // INSERT 
    public void inserirCategoria(Categoria categoria) {

        PreparedStatement stmt;

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("INSERT INTO categoria(codCategoria, descricao) "
                    + "VALUES (?, ?)");

            stmt.setInt(1, categoria.getCodCategoria());
            stmt.setString(2, categoria.getDescricao());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Categoria cadastrada com sucesso!");
        } catch (SQLException erroInserirCategoria) {
            System.out.println(erroInserirCategoria.getMessage());
        }
    }

    // SELECT
    public ArrayList<Categoria> consultarCategoria() {
        PreparedStatement stmt;
        ResultSet result;
        ArrayList<Categoria> listaCategoria = new ArrayList<>();

        try (Connection conn = new SQLite().conectar()) {
            String sql = "SELECT codCategoria, descricao FROM categoria;";

            stmt = conn.prepareStatement(sql);

            result = stmt.executeQuery();

            while (result.next()) {
                Categoria categoria = new Categoria();

                categoria.setCodCategoria(result.getInt("codCategoria"));
                categoria.setDescricao(result.getString("descricao"));

                listaCategoria.add(categoria);
            }

            stmt.close();

            return listaCategoria;
        } catch (SQLException erroConsultarCargo) {
            System.out.println(erroConsultarCargo.getMessage());
            return listaCategoria;
        }
    }
    
       // SELECT
    public Categoria consultarCategoria(int codCategoria) {
        PreparedStatement stmt;
        ResultSet result;
        Categoria categoria = new Categoria();

        try (Connection conn = new SQLite().conectar()) {
            stmt = conn.prepareStatement("SELECT codcategoria, descricao"
                    + "FROM categoria WHERE codCategoria = " + codCategoria + ";");

            result = stmt.executeQuery();
            while (result.next()) {
                categoria.setCodCategoria(result.getInt("codCategoria"));
                categoria.setDescricao(result.getString("descricao"));
            }
            stmt.close();
        } catch (SQLException erroConsultarCodigoProduto) {
            System.out.println(erroConsultarCodigoProduto.getMessage());
        }
        return categoria;
    }


    // SELECT (por nome)
    public Categoria consultarNomeCategoria(String descricao) {

        PreparedStatement stmt;
        ResultSet result;
        Categoria categoria = new Categoria();

        try (Connection conn = new SQLite().conectar()) {

            stmt = conn.prepareStatement("SELECT codCategoria, descricao FROM categoria "
                    + "WHERE descricao LIKE '%" + descricao + "%';");

            result = stmt.executeQuery();
            if (result.next()) {
                categoria.setCodCategoria(result.getInt("codCategoria"));
                categoria.setDescricao(result.getString("descricao"));
            }
            stmt.close();
            return categoria;
        } catch (SQLException erroConsultarCategoria) {
            System.out.println(erroConsultarCategoria.getMessage());
            return categoria;
        }

    }
}
