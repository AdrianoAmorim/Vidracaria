package domain;

/**
 *
 * @author rafael
 */
public class Compra {

    protected int codCompra;
    protected int codDespesa;
    protected int codParcelamento;
    protected int codFornecedor;
    protected String data;
    protected Double totalBruto;
    protected Double desconto;
    protected Double totalLiquido;

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public int getCodDespesa() {
        return codDespesa;
    }

    public void setCodDespesa(int codDespesa) {
        this.codDespesa = codDespesa;
    }

    public int getCodParcelamento() {
        return codParcelamento;
    }

    public void setCodParcelamento(int codParcelamento) {
        this.codParcelamento = codParcelamento;
    }

    public int getCodFornecedor() {
        return codFornecedor;
    }

    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(Double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTotalLiquido() {
        return totalLiquido;
    }

    public void setTotalLiquido(Double totalLiquido) {
        this.totalLiquido = totalLiquido;
    }

}