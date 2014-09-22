
package domain;

/**
 *
 * @author rafael
 */
public class TipoDespesa {
    protected int codTipoDespesa;
    protected String descricaoDespesa;
    protected Double totalDespesa;
    
    public int getCodTipoDespesa() {
        return codTipoDespesa;
    }
    
    public void setCodTipoDespesa(int codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }
    
    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }
    
    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }
    
    public Double getTotalDespesa() {
        return totalDespesa;
    }
    
    public void setTotalDespesa(Double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }
}

