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
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:bd_projeto.sqlite");
        } catch (SQLException erroCon) {
            System.err.println(erroCon.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException erroConexao) {
            System.out.println(erroConexao.getMessage());
        }
        return conn;
    }
}
