package controller;

import domain.TipoPagamento;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TipoPagamentoController {

    public boolean validarTipoPagamento(TipoPagamento tipoPagamento) {
        if (String.valueOf(tipoPagamento.getCodTipoPagamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de pagamento é obrigatório!");
            return false;
        } else if (tipoPagamento.getDescricaoTipoPagamento().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descricao do tipo de pagamento é obrigatório!");
            return false;
        }
        return true;
    }
}
