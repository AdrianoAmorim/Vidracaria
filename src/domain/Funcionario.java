
package domain;


/**
 *
 * @author rafael
 */
public class Funcionario {
    
    protected int codFuncionario;
    protected int codCargo;
    protected int codEmpresa;
    protected String nomeFuncionario;
    protected Double salarioFuncionario;
    
    public int getCodFuncionario() {
        return codFuncionario;
    }
    
    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }
    
    public int getCodCargo() {
        return codCargo;
    }
    
    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }
    
    public int getCodEmpresa() {
        return codEmpresa;
    }
    
    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
    
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    
    public Double getSalarioFuncionario() {
        return salarioFuncionario;        
    }
    
    public void setSalarioFuncionario(Double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
}
