/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.TipoPagamento;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TipoPagamentoController {

    public boolean validarTipoPagamento(TipoPagamento tipoPagamento) {
        if (tipoPagamento.getCodigoTipoPagamento() == 1) {
            if (String.valueOf(tipoPagamento.getParcelas()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "O preenchimento do tipo da quantidade de parcelas é obrigatório!");
                return false;
            }
        }
        return true;
    }
}
