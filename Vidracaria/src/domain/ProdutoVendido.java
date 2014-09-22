
package domain;

/**
 *
 * @author rafael
 */
public class ProdutoVendido {
    protected int codProduto;
    protected int codVenda;
    protected Double quantidadeProduto;
    
    public int getCodProduto() {
        return codProduto;
    }
    
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    public int getCodVenda() {
        return codVenda;
    }
    
    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }
    
    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }
    
    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}
