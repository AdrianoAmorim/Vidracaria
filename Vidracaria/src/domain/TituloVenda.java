
package domain;

/**
 *
 * @author rafael
 */
public class TituloVenda {
    protected int codTitulo;
    protected int codVenda;
    protected String vencimentoTitulo;
    protected Double valorTitulo;
    
       
    public int getCodTitulo() {
        return codTitulo;
    }
    
    public void setCodTitulo(int codTitulo) {
        this.codTitulo = codTitulo;
    }
    
    public int getCodCompra() {
        return codVenda;
    }
    
    public void setCodCompra(int codVenda) {
        this.codVenda = codVenda;
    }
    
    public String getVencimentoTitulo() {
        return vencimentoTitulo;
    }
    
    public void setVencimentoTitulo(String vencimentoTitulo) {
        this.vencimentoTitulo = vencimentoTitulo;
    }
    
    public Double getValorTitulo() {
        return valorTitulo;
    }
    
    public void setValorTitulo(Double valorTitulo) {
        this.valorTitulo = valorTitulo;
    }
}
 
