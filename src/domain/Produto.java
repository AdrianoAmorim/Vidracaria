
package domain;

/**
 *
 * @author debian
 */
public class Produto {

    private int codProduto;
    private String descricao;
    private String unidadeMedida;
    private Double quantidadeEstoque;
    private Double precoVenda;
    private int status;


    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
