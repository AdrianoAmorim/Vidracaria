
package domain;

/**
 *
 * @author rafael
 */
public class TipoDespesa {
    protected int codTipoDespesa;
    protected String descricaoTipoDespesa;
    protected Double totalDespesa;
    
    public int getCodTipoDespesa() {
        return codTipoDespesa;
    }
    
    public void setCodTipoDespesa(int codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }
    
    public String getDescricaoTipoDespesa() {
        return descricaoTipoDespesa;
    }
    
    public void setDescricaoTipoDespesa(String descricaoTipoDespesa) {
        this.descricaoTipoDespesa = descricaoTipoDespesa;
    }
    
    public Double getTotalDespesa() {
        return totalDespesa;
    }
    
    public void setTotalDespesa(Double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }
}

