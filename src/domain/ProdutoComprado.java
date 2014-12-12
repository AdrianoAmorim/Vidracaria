
package domain;

/**
 *
 * @author rafael
 */
public class ProdutoComprado {
    protected int codProduto;
    protected int codDespesa;
    protected int codCompra;
    protected Double quantidadeProduto;
    protected Double precoCusto;

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public int getCodDespesa() {
        return codDespesa;
    }

    public void setCodDespesa(int codDespesa) {
        this.codDespesa = codDespesa;
    }

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }
    
}