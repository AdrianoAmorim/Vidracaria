
package domain;

/**
 *
 * @author rafael
 */
public class PagamentoTitulo {
    protected int codPagamento;
    protected int codTitulo;
    protected int codTipoPagamento;
    protected Double valorPagamento;
    protected String dataPagamento;
    
    public int getCodPagamento() {
        return codPagamento;
    }
    
    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }
    
    public int getCodTitulo() {
        return codTitulo;
    }
    
    public void setCodTitulo(int codTitulo) {
        this.codTitulo = codTitulo;
    }
    
    public int getCodTipoPagamento() {
        return codTipoPagamento;
    }
    
    public void setCodTipoPagamento(int codTipoPagamento) {
        this.codTipoPagamento = codTipoPagamento;
    }
    
    public Double getValorPagamento() {
        return valorPagamento;
    }
    
    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
   
    public String getDataPagamento() {
        return dataPagamento;
    }
    
    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
