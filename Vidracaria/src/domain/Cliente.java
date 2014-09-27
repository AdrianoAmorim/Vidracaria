
package domain;

/**
 *
 * @author debian
 */
public class Cliente {

    protected int codCliente;
    protected String cpfCliente;
    protected String nomeCliente;
    protected String rgCliente;
    protected String enderecoCliente;
    protected String telResidencial;
    protected String telCelular;

    public Cliente() {
        codCliente = 0;
        cpfCliente = "";
        nomeCliente = "";
        rgCliente = "";
        enderecoCliente = "";
        telResidencial = "";
        telCelular = "";
    }
    
    public int getCodCliente() {
        return codCliente;
    }
    
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelResidencial() {
        return telResidencial;
    }

    public void setTelResidencial(String telResidencial) {
        this.telResidencial = telResidencial;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }
    
}