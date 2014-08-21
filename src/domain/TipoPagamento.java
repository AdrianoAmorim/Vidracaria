/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.JOptionPane;

/**
 *
 * @author debian
 */
public class TipoPagamento {

    private int codigoTipoPagamento;
    private String descricaoPagamento;

    public int getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public boolean setCodigoTipoPagamento(int codigoTipoPagamento) {
        if (Integer.toString(codigoTipoPagamento).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo do tipo de pagamento inválido !", "AVISO", 2);
            return false;
        }
        this.codigoTipoPagamento = codigoTipoPagamento;
        return true;
    }

    public String getDescricaoPagamento() {
        return descricaoPagamento;
    }

    public boolean setDescricaoPagamento(String descricaoPagamento) {
        if (descricaoPagamento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição do tipo de pagamento inválida !", "AVISO", 2);
            return false;
        }
        this.descricaoPagamento = descricaoPagamento;
        return true;
    }

}
