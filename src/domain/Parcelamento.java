
package domain;

/**
 *
 * @author rafael
 */
public class Parcelamento {
    protected int codParcelamento;
    protected String descricaoParcelamento;
    protected int quantidadeParcelas;
    
    public int getCodParcelamento() {
        return codParcelamento;
    }
   
    public void setCodParcelamento(int codParcelamento) {
        this.codParcelamento = codParcelamento;
    }
    
    public String getDescricaoParcelamento() {
        return descricaoParcelamento;
    }
    
    public void setDescricaoParcelamento(String descricaoParcelamento) {
        this.descricaoParcelamento = descricaoParcelamento;
    }
    
    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }
    
    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}
