
package domain;

/**
 *
 * @author rafael
 */
public class TituloCompra {
    protected int codTitulo;
    protected int codCompra;
    protected String vencimentoTitulo;
    protected Double valorTitulo;
    
    public int getCodTitulo() {
        return codTitulo;
    }
    
    public void setCodTitulo(int codTitulo) {
        this.codTitulo = codTitulo;
    }
    
    public int getCodCompra() {
        return codCompra;
    }
    
    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
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

