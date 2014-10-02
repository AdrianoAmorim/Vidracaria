
package domain;

/**
 *
 * @author rafael
 */
public class Compra {
    protected int codCompra;
    protected int codFornecedor;
    protected int codTipoDespesa;
    protected String dataCompra;
    
    public int getCodCompra() {
        return codCompra;
    }
    
    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }
    
    public int getCodFornecedor() {
        return codFornecedor;
    }
    
    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }
    
    public int getCodTipoDespesa() {
        return codTipoDespesa;
    }
    
    public void setCodTipoDespesa(int codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }
    
    public String getDataCompra() {
        return dataCompra;
    }
    
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
}
