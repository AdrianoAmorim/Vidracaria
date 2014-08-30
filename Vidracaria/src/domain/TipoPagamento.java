/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author debian
 */
public class TipoPagamento {

    private int codigoTipoPagamento;
    private String descricaoPagamento;
    private int parcelas;

    public int getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public void setCodigoTipoPagamento(int codigoTipoPagamento) {
        this.codigoTipoPagamento = codigoTipoPagamento;
    }

    public String getDescricaoPagamento() {
        return descricaoPagamento;
    }

    public void setDescricaoPagamento(String descricaoPagamento) {
        this.descricaoPagamento = descricaoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

}
