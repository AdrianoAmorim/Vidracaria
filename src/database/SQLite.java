/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author debian
 */
public class SQLite {

    public Connection conn = conectar();

    public SQLite() {
        prepararTabelas(conn);
    }

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Conectado com sucesso !");
        } catch (SQLException erroCon) {
            System.err.println(erroCon.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException erroConexao) {
            System.out.println(erroConexao.getMessage());
        }
        return conn;
    }

    private void prepararTabelas(Connection conn) {
        try {
            Statement stmt = conn.createStatement();

 // Descomentar caso o database seja modificado, para recriar as tabelas
            //String deletar = "DROP TABLE IF EXISTS cliente;";
            //stmt.execute(deletar);
            //deletar = "DROP TABLE IF EXISTS produto;";
            //stmt.execute(deletar);
            //deletar = "DROP TABLE IF EXISTS venda;";
            //stmt.execute(deletar);
            //deletar = "DROP TABLE IF EXISTS tipoPagamento;";
            //stmt.execute(deletar);
            //deletar = "DROP TABLE IF EXISTS estoque;";
            //stmt.execute(deletar);
            
            String cliente = "CREATE TABLE IF NOT EXISTS cliente("
                    + "cpf            CHAR(11)     NOT NULL,"
                    + "nome           VARCHAR(30)  NOT NULL,"
                    + "rg             VARCHAR(10)  NOT NULL,"
                    + "endereco       VARCHAR(256) NOT NULL,"
                    + "telResidencial VARCHAR(20)      NULL,"
                    + "telCelular     VARCHAR(20)      NULL,"
                    + ""
                    + "CONSTRAINT pk_cliente"
                    + "  PRIMARY KEY(cpf)"
                    + ");";
            stmt.executeUpdate(cliente);
            stmt.close();

            String produto = "CREATE TABLE IF NOT EXISTS produto("
                    + "codigoProduto    INTEGER     AUTO INCREMENT,"
                    + "descricaoProduto VARCHAR(50) NOT NULL,"
                    + "precoCusto       DOUBLE      NOT NULL,"
                    + "precoVenda       DOUBLE      NOT NULL,"
                    + "unidadeMedida    CHAR(6)     NOT NULL,"
                    + ""
                    + "CONSTRAINT pk_produto"
                    + "  PRIMARY KEY(codigoProduto)"
                    + ");";
            stmt.executeUpdate(produto);
            stmt.close();

            String estoque = "CREATE TABLE IF NOT EXISTS estoque("
                    + "codigoProduto    INTEGER NOT NULL,"
                    + "quantidadeAtual  DOUBLE  NOT NULL,"
                    + "quantidadeMinima DOUBLE  NOT NULL,"
                    + ""
                    + "CONSTRAINT pk_estoque"
                    + "  PRIMARY KEY (codigoProduto),"
                    + ""
                    + "CONSTRAINT fk_produto_venda"
                    + "  FOREIGN KEY (codigoProduto)"
                    + "  REFERENCES produto (codigoProduto)"
                    + ");";

            stmt.executeUpdate(estoque);
            stmt.close();

            String pagamento = "CREATE TABLE IF NOT EXISTS tipoPagamento("
                    + "codigoTipoPagamento INTEGER     AUTO INCREMENT,"
                    + "descricaoPagamento  VARCHAR(30) NOT NULL,"
                    + ""
                    + "CONSTRAINT pk_tipoPagemento"
                    + "  PRIMARY KEY (codigoTipoPagamento)"
                    + ");";
            stmt.executeUpdate(pagamento);
            stmt.close();

            String venda = "CREATE TABLE IF NOT EXISTS venda("
                    + "codigoVenda         INTEGER  NOT NULL,"
                    + "codigoProduto       INTEGER  NOT NULL,"
                    + "quantidadeProduto   DOUBLE   NOT NULL,"
                    + "codigoCliente       CHAR(11) NOT NULL,"
                    + "codigoTipoPagamento INTEGER  NOT NULL,"
                    + "dataVenda           CHAR(10) NOT NULL,"
                    + ""
                    + "CONSTRAINT pk_venda"
                    + "  PRIMARY KEY(codigoVenda)"
                    + ""
                    + "CONSTRAINT fk_produto_venda"
                    + "  FOREIGN KEY(codigoProduto)"
                    + "  REFERENCES produto(codigoProduto)"
                    + ""
                    + "CONSTRAINT fk_cliente_venda"
                    + "  FOREIGN KEY(codigoCliente)"
                    + "  REFERENCES cliente(cpf)"
                    + ""
                    + "CONSTRAINT fk_tipoPagamento_venda"
                    + "  FOREIGN KEY(codigoTipoPagamento)"
                    + "  REFERENCES tipoPagamento(codigoTipoPagamento)"
                    + ");";
            stmt.executeUpdate(venda);
            stmt.close();
            conn.close();
            System.out.println("Tabelas criadas com sucesso !");
        } catch (SQLException erroTabelas) {
            System.out.println(erroTabelas.getSQLState());
            System.exit(0);
        }
    }

}
