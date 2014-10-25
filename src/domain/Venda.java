package domain;

/**
 *
 * @author rafael
 */
public class Venda {

    protected int codVenda;
    protected int codRenda;
    protected int codParcelamento;
    protected int codCliente;
    protected String dataVenda;
    protected Double totalDesconto;
    protected String descricao;
    protected Double totalBruto;
    protected Double totalLiquido;

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
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

    public int getCodRenda() {
        return codRenda;
    }

    public void setCodRenda(int codRenda) {
        this.codRenda = codRenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(Double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Double getTotalLiquido() {
        return totalLiquido;
    }

    public void setTotalLiquido(Double totalLiquido) {
        this.totalLiquido = totalLiquido;
    }

}
