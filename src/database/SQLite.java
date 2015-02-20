package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author debian
 */
public class SQLite {

    public Connection conn = conectar();

    public Connection conectar() {
        try {
            // conexão com SQLite
            // Class.forName("org.sqlite.JDBC");
            //conn = DriverManager.getConnection("jdbc:sqlite:bd_projeto.sqlite");

            // conexão com PostgreSQL
            Class.forName("org.postgresql.Driver");

            String user = "postgres";
            String pass = "mudar@senha";
            String url = "jdbc:postgresql://localhost:5433/vidracaria";

            Connection conn = DriverManager.getConnection(url , user , pass);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return conn;
    }
}
