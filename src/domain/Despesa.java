
package domain;

/**
 *
 * @author rafael
 */
public class Despesa {

    protected int codEmpresa;
    protected int codTipoDespesa;
    protected String mesAno;

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getCodTipoDespesa() {
        return codTipoDespesa;
    }

    public void setCodTipoDespesa(int codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }
}
