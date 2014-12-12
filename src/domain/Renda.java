
package domain;

/**
 *
 * @author rafael
 */
public class Renda {
    protected int codEmpresa;
    protected int codTipoRenda;
    protected String mesAno;
    
    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getCodTipoRenda() {
        return codTipoRenda;
    }

    public void setCodTipoRenda(int codTipoRenda) {
        this.codTipoRenda = codTipoRenda;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

}
