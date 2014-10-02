package domain;

/**
 *
 * @author rafael
 */
public class TipoRenda {

    protected int codTipoRenda;
    protected String descricaoTipoRenda;
    protected Double totalRenda;

    public int getCodTipoRenda() {
        return codTipoRenda;
    }

    public void setCodTipoRenda(int codTipoRenda) {
        this.codTipoRenda = codTipoRenda;
    }

    public String getDescricaoTipoRenda() {
        return descricaoTipoRenda;
    }

    public void setDescricaoTipoRenda(String descricaoTipoRenda) {
        this.descricaoTipoRenda = descricaoTipoRenda;
    }

    public Double getTotalRenda() {
        return totalRenda;
    }

    public void setTotalRenda(Double totalRenda) {
        this.totalRenda = totalRenda;
    }

}
