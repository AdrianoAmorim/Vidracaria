package domain;

/**
 *
 * @author rafael
 */
public class Venda {

    protected int codVenda;
    protected int codTipoRenda;
    protected int codCliente;
    protected int codParcelamento;
    protected Double totalVenda;
    protected Double totalDesconto;
    protected String dataVenda;

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public int getCodTipoRenda() {
        return codTipoRenda;
    }

    public void setCodTipoRenda(int codTipoRenda) {
        this.codTipoRenda = codTipoRenda;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodParcelamento() {
        return codParcelamento;
    }

    public void setCodParcelamento(int codParcelamento) {
        this.codParcelamento = codParcelamento;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Double getTotalDesconto() {
        return totalDesconto;
    }

    public void setTotalDesconto(Double totalDesconto) {
        this.totalDesconto = totalDesconto;
    }
   
    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

}
