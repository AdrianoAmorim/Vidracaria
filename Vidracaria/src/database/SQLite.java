
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

    public SQLite() throws SQLException {
        prepararTabelas(conn);
    }

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:projeto.db");
            System.out.println("Conectado com sucesso !");
        } catch (SQLException erroCon) {
            System.err.println(erroCon.getMessage());
            System.exit(0);
        } catch (ClassNotFoundException erroConexao) {
            System.out.println(erroConexao.getMessage());
        }
        return conn;
    }

    private void prepararTabelas(Connection conn) throws SQLException {
        try {
            Statement stmt = conn.createStatement();

            String empresa = "CREATE  TABLE IF NOT EXISTS Empresa ("
                    + "  codEmpresa   INT          NOT NULL,"
                    + "  cnpjEmpresa  VARCHAR(14)  NOT NULL,"
                    + "  razaoSocial  VARCHAR(45)  NOT NULL,"
                    + "  nomeFantasia VARCHAR(45)  NOT NULL,"
                    + "  "
                    + "  CONSTRAINT pk_empresa "
                    + "    PRIMARY KEY(codEmpresa),"
                    + ""
                    + "  CONSTRAINT un_cnpj_empresa "
                    + "    UNIQUE(cnpjEmpresa)"
                    + "  );";
            stmt.executeUpdate(empresa);
            stmt.close();

            String cargo = "CREATE  TABLE IF NOT EXISTS Cargo ("
                    + "  codCargo       INT         NOT NULL,"
                    + "  descricaoCargo VARCHAR(45) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_cargo "
                    + "    PRIMARY KEY (codCargo),"
                    + ""
                    + "  CONSTRAINT un_descricaoCargo_cargo "
                    + "    UNIQUE (descricaoCargo)"
                    + "  );";
            stmt.executeUpdate(cargo);
            stmt.close();

            String funcionario = "CREATE  TABLE IF NOT EXISTS Funcionario ("
                    + "  codFuncionario     INT          NOT NULL,"
                    + "  codCargo           INT          NOT NULL,"
                    + "  codEmpresa         INT          NOT NULL,"
                    + "  nomeFuncionario    VARCHAR(45)  NOT NULL,"
                    + "  salarioFuncionario NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_funcionario "
                    + "    PRIMARY KEY (codFuncionario),"
                    + ""
                    + "  CONSTRAINT fk_cargo_funcionario "
                    + "    FOREIGN KEY (codCargo)"
                    + "    REFERENCES Cargo (codCargo),"
                    + ""
                    + "  CONSTRAINT fk_empresa_funcionario "
                    + "    FOREIGN KEY (codEmpresa)"
                    + "    REFERENCES Empresa (codEmpresa)"
                    + "  );";
            stmt.executeUpdate(funcionario);
            stmt.close();

            String tipoRenda = "CREATE  TABLE IF NOT EXISTS TipoRenda ("
                    + "  codTipoRenda       INT          NOT NULL,"
                    + "  descricaoTipoRenda VARCHAR(45)  NOT NULL,"
                    + "  totalRenda         NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_tipoRenda "
                    + "    PRIMARY KEY (codTipoRenda)"
                    + "  );";
            stmt.executeUpdate(tipoRenda);
            stmt.close();

            String renda = "CREATE  TABLE IF NOT EXISTS Renda ("
                    + "  codEmpresa INT     NOT NULL,"
                    + "  codTipoRenda  INT  NOT NULL,"
                    + "  mesAno     CHAR(6) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_renda "
                    + "    PRIMARY KEY (codTipoRenda, mesAno, codEmpresa),"
                    + ""
                    + "  CONSTRAINT fk_empresa_renda "
                    + "    FOREIGN KEY (codEmpresa)"
                    + "    REFERENCES empresa (codEmpresa),"
                    + ""
                    + "  CONSTRAINT fk_empresaRenda "
                    + "    FOREIGN KEY (codEmpresa) "
                    + "    REFERENCES Empresa (codEmpresa),"
                    + ""
                    + "  CONSTRAINT fk_tipoRenda_renda "
                    + "    FOREIGN KEY (codTipoRenda)"
                    + "    REFERENCES TipoRenda (codTipoRenda)"
                    + "  );";
            stmt.executeUpdate(renda);
            stmt.close();

            String tipoDespesa = "CREATE  TABLE IF NOT EXISTS TipoDespesa ("
                    + "  codTipoDespesa   INT          NOT NULL,"
                    + "  descricaoDespesa VARCHAR(45)  NOT NULL,"
                    + "  totalDespesa     NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_tipoDespesa "
                    + "    PRIMARY KEY (codTipoDespesa)"
                    + "  );";
            stmt.executeQuery(tipoDespesa);
            stmt.close();

            String despesa = "CREATE  TABLE IF NOT EXISTS Despesa ("
                    + "  codEmpresa     INT     NOT NULL,"
                    + "  codTipoDespesa INT     NOT NULL,"
                    + "  mesAno         CHAR(6) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_despesa"
                    + "    PRIMARY KEY (codEmpresa, codTipoDespesa, mesAno),"
                    + ""
                    + "  CONSTRAINT fk_empresa_despesa "
                    + "    FOREIGN KEY (codEmpresa)"
                    + "    REFERENCES Empresa (codEmpresa),"
                    + ""
                    + "  CONSTRAINT fk_tipoDespesa_despesa "
                    + "    FOREIGN KEY (codTipoDespesa)"
                    + "    REFERENCES TipoDespesa (codTipoDespesa)"
                    + "  );";
            stmt.executeUpdate(despesa);
            stmt.close();

            String cliente = "CREATE  TABLE IF NOT EXISTS Cliente ("
                    + "  codCliente      INT         NOT NULL,"
                    + "  cpfCliente      CHAR(11)    NOT NULL,"
                    + "  nomeCliente     VARCHAR(45) NOT NULL,"
                    + "  rgCliente       VARCHAR(10) NOT NULL,"
                    + "  enderecoCliente VARCHAR(45) NOT NULL,"
                    + "  telResidencial  VARCHAR(20) NULL,"
                    + "  telCelular      VARCHAR(20) NULL,"
                    + ""
                    + "  CONSTRAINT pk_cliente "
                    + "    PRIMARY KEY (codCliente)"
                    + "  );";
            stmt.executeUpdate(cliente);
            stmt.close();

            String parcelamento = "CREATE  TABLE IF NOT EXISTS Parcelamento ("
                    + "  codParcelamento       INT         NOT NULL,"
                    + "  descricaoParcelamento VARCHAR(45) NOT NULL,"
                    + "  quantidadeParcelas    INT         NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_parcelamento "
                    + "  PRIMARY KEY (codParcelamento)"
                    + "  );";
            stmt.executeUpdate(parcelamento);
            stmt.close();

            String venda = "CREATE  TABLE IF NOT EXISTS Venda ("
                    + "  codVenda        INT          NOT NULL,"
                    + "  codTipoRenda    INT          NOT NULL,"
                    + "  codCliente      INT          NOT NULL,"
                    + "  codParcelamento INT          NOT NULL,"
                    + "  totalVenda      NUMERIC(6,2) NOT NULL,"
                    + "  dataVenda       DATE         NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_venda "
                    + "    PRIMARY KEY (codVenda),"
                    + ""
                    + "  CONSTRAINT fk_tipoRenda_venda "
                    + "    FOREIGN KEY (codTipoRenda)"
                    + "    REFERENCES TipoRenda (codTipoRenda),"
                    + ""
                    + "  CONSTRAINT fk_cliente_venda "
                    + "    FOREIGN KEY (codCliente)"
                    + "    REFERENCES Cliente (codCliente),"
                    + ""
                    + "  CONSTRAINT fk_parcelamento_venda "
                    + "    FOREIGN KEY (codParcelamento)"
                    + "    REFERENCES Parcelamento (codParcelamento)"
                    + "  );";
            stmt.executeUpdate(venda);
            stmt.close();

            String produto = "CREATE  TABLE IF NOT EXISTS Produto ("
                    + "  codProduto INT          NOT NULL,"
                    + "  descricaoProduto  VARCHAR(45)  NOT NULL,"
                    + "  unidadeMedida     CHAR(6)      NOT NULL,"
                    + "  quantidadeEstoque NUMERIC(6, 2) NOT NULL,"
                    + "  quantidadeMinima  NUMERIC(6, 2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_produto "
                    + "    PRIMARY KEY(codProduto)"
                    + "  );";
            stmt.executeUpdate(produto);
            stmt.close();

            String produtoVendido = "CREATE  TABLE IF NOT EXISTS ProdutoVendido ("
                    + "  codProduto        INT          NOT NULL,"
                    + "  codVenda          INT          NOT NULL,"
                    + "  quantidadeProduto NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_produtoVendido "
                    + "    PRIMARY KEY (codProduto, codVenda),"
                    + ""
                    + "  CONSTRAINT fk_produto_produtoVendido "
                    + "    FOREIGN KEY (codProduto)"
                    + "    REFERENCES Produto (codProduto),"
                    + ""
                    + "  CONSTRAINT fk_venda_produtoVendido "
                    + "    FOREIGN KEY (codVenda)"
                    + "    REFERENCES venda (codVenda)"
                    + "  );";
            stmt.executeUpdate(produtoVendido);
            stmt.close();

            String fornecedor = "CREATE  TABLE IF NOT EXISTS Fornecedor ("
                    + "  codFornecedor  INT         NOT NULL,"
                    + "  cnpjFornecedor CHAR(14)    NOT NULL,"
                    + "  nomeFornecedor VARCHAR(45) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_fornecedor "
                    + "    PRIMARY KEY (codFornecedor)"
                    + "  );";
            stmt.executeUpdate(fornecedor);
            stmt.close();

            String compra = "CREATE  TABLE IF NOT EXISTS Compra ("
                    + "  codCompra      INT  NOT NULL,"
                    + "  codFornecedor  INT  NOT NULL,"
                    + "  codTipoDespesa INT  NOT NULL,"
                    + "  dataCompra     DATE NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_compra "
                    + "    PRIMARY KEY (codCompra),"
                    + ""
                    + "  CONSTRAINT fk_fornecedor_compra "
                    + "    FOREIGN KEY (codFornecedor)"
                    + "    REFERENCES Fornecedor (codFornecedor),"
                    + ""
                    + "  CONSTRAINT fk_tipoDespesa_compra "
                    + "+   FOREIGN KEY (codTipoDespesa)"
                    + "    REFERENCES TipoDespesa (codTipoDespesa)"
                    + "  );";
            stmt.executeUpdate(compra);
            stmt.close();

            String produtoComprado = "CREATE  TABLE IF NOT EXISTS ProdutoComprado ("
                    + "  codProduto        INT          NOT NULL,"
                    + "  codCompra         INT          NOT NULL,"
                    + "  quantidadeProduto NUMERIC(6,2) NOT NULL,"
                    + "  precoCusto        NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_ProdutoComprado "
                    + "    PRIMARY KEY (codProduto, codCompra),"
                    + ""
                    + "  CONSTRAINT fk_produto_produtoComprado "
                    + "    FOREIGN KEY (codProduto)"
                    + "    REFERENCES Produto (codProduto),"
                    + ""
                    + "  CONSTRAINT fk_compra_produtoComprado "
                    + "    FOREIGN KEY (codCompra)"
                    + "    REFERENCES Compra (codCompra)"
                    + "  );";
            stmt.executeUpdate(produtoComprado);
            stmt.close();

            String tipoPagamento = "CREATE  TABLE IF NOT EXISTS TipoPagamento ("
                    + "  codTipoPagamento        INT         NOT NULL,"
                    + "  descricaoTipoPagamento VARCHAR(45) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_TipoPagamento "
                    + "    PRIMARY KEY (codTipoPagamento)"
                    + "  );";
            stmt.executeUpdate(tipoPagamento);
            stmt.close();

            String tituloCompra = "CREATE  TABLE IF NOT EXISTS TituloCompra ("
                    + "  codTitulo        INT          NOT NULL,"
                    + "  codCompra        INT          NOT NULL,"
                    + "  vencimentoTitulo DATE(45)     NOT NULL,"
                    + "  valorTitulo      NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_tituloCompra "
                    + "    PRIMARY KEY (codTitulo),"
                    + ""
                    + "  CONSTRAINT fk_compra_tituloCompra "
                    + "    FOREIGN KEY (codCompra)"
                    + "    REFERENCES Compra (codCompra)"
                    + "  );";
            stmt.executeUpdate(tituloCompra);
            stmt.close();

            String tituloVenda = "CREATE  TABLE IF NOT EXISTS TituloVenda ("
                    + "  codTitulo        INT          NOT NULL,"
                    + "  codVenda         INT          NOT NULL,"
                    + "  vencimentoTitulo DATE         NOT NULL,"
                    + "  valorTitulo      NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_tituloVenda "
                    + "    PRIMARY KEY (codTitulo),"
                    + ""
                    + "  CONSTRAINT fk_venda_titulo "
                    + "    FOREIGN KEY (codVenda)"
                    + "    REFERENCES venda (codVenda)"
                    + "  );";
            stmt.executeUpdate(tituloVenda);
            stmt.close();

            String pagamentoTitulo = "CREATE  TABLE IF NOT EXISTS PagamentoTitulo ("
                    + "  codPagamento     INT          NOT NULL,"
                    + "  codTitulo        INT          NOT NULL,"
                    + "  codTipoPagamento INT          NOT NULL,"
                    + "  valorPagamento   NUMERIC(6,2) NOT NULL,"
                    + "  dataPagamento    DATE         NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_pagamentoTitulo "
                    + "    PRIMARY KEY (codPagamento),"
                    + ""
                    + "  CONSTRAINT fk_tituloCompra_pagamentoTitulo "
                    + "    FOREIGN KEY (codTitulo)"
                    + "    REFERENCES TituloCompra (codTitulo),"
                    + ""
                    + "  CONSTRAINT fk_tituloVenda_pagamentoTitulo "
                    + "    FOREIGN KEY (codTitulo)"
                    + "    REFERENCES TituloVenda (codTitulo)"
                    + "  );";
            stmt.executeUpdate(pagamentoTitulo);
            stmt.close();

            String alteracaoPreco = "CREATE  TABLE IF NOT EXISTS AlteracaoPreco ("
                    + "  codAlteracao   INT          NOT NULL,"
                    + "  codProduto     INT          NOT NULL,"
                    + "  codFuncionario INT          NOT NULL,"
                    + "  dataAlteracao  DATE         NOT NULl,"
                    + "  precoVenda     NUMERIC(6,2) NOT NULL,"
                    + ""
                    + "  CONSTRAINT pk_alteracaoPreco "
                    + "    PRIMARY KEY (codAlteracao),"
                    + ""
                    + "  CONSTRAINT fk_produto_alteracaoPreco "
                    + "    FOREIGN KEY (codProduto)"
                    + "    REFERENCES Produto (codProduto),"
                    + ""
                    + "  CONSTRAINT fk_funcionario_alterarPreco "
                    + "    FOREIGN KEY (codFuncionario)"
                    + "    REFERENCES Funcionario (codFuncionario)"
                    + "  );";
            stmt.executeUpdate(alteracaoPreco);
            stmt.close();

        } catch (SQLException erroTabelas) {
            System.out.println(erroTabelas.getSQLState());
            System.exit(0);
        }
    }
}
