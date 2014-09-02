package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafael
 */
public class Funcionario {
    
    protected int codigoFuncionario;
    protected int codigoCargo;
    protected String nomeFuncionario;
    protected Double salarioFuncionario;
    
    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }
    
    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }
    
    public int getCodigoCargo() {
        return codigoCargo;
    }
    
    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
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
