
package domain;

/**
 *
 * @author rafael
 */
public class AlteracaoPreco {
    protected int codAlteracaoPreco;
    protected int codProduto;
    protected int codFuncionario;
    protected String dataAlteracao;
    protected Double precoVenda;
    
    public int getCodAlteracaoPreco() {
        return codAlteracaoPreco;
    }
    
    public void setCodAlteracaoPreco(int codAlteracaoPreco) {
        this.codAlteracaoPreco = codAlteracaoPreco;
    }
    
    public int getCodProduto() {
        return codProduto;
    }
    
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    public int getCodFuncionario() {
        return codFuncionario;
    }
    
    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }
    
    public String getDataAlteracao() {
        return dataAlteracao;
    }
    
    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
    public Double getPrecoVenda() {
        return precoVenda;                
    }
    
    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
