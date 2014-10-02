
package domain;

/**
 *
 * @author rafael
 */
public class Fornecedor {
    protected int codFornecedor;
    protected String cnpjFornecedor;
    protected String nomeFornecedor;
    
    public int getCodFornecedor() {
        return codFornecedor;
    }
     
    public void setCodFornecedor(int codFornecedor) {
        this.codFornecedor = codFornecedor;
    }
    
    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }
    
    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }
    
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}
