
package domain;

/**
 *
 * @author rafael
 */
public class Empresa extends Endereco {
    protected int codEmpresa;
    protected String cnpjEmpresa;
    protected String razaoSocial;
    protected String nomeFantasia;
    protected String logradouro;
    protected String numero;
    protected String complemento;
    protected String bairro;
    protected String cep;
    protected String cidade;
    protected String uf;

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    
    
    
}
