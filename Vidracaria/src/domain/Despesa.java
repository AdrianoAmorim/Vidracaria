/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author rafael
 */
public class Despesa {
    
    protected int codigoDespesa;
    protected String descricaoDespesa;
    protected Double custoDespesa;

    public int getCodigoDespesa() {
        return codigoDespesa;
    }

    public void setCodigoDespesa(int codigoDespesa) {
        this.codigoDespesa = codigoDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }

    public Double getCustoDespesa() {
        return custoDespesa;
    }

    public void setCustoDespesa(Double custoDespesa) {
        this.custoDespesa = custoDespesa;
    }
    
    
    
}
