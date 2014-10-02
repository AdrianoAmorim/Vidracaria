
package domain;

/**
 *
 * @author debian
 */
public class Produto {

    private int codProduto;
    private String descricaoProduto;
    private String unidadeMedida;
    private Double quantidadeEstoque;
    private Double precoVenda;


    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    
    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public Double getPrecoVenda() {
        return precoVenda;
    }
     
    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
}