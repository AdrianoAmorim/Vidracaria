package controller;

import domain.PagamentoTitulo;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class PagamentoTituloController {

    public boolean validarPagamentoTitulo(PagamentoTitulo pagamentoTitulo) {
        if (String.valueOf(pagamentoTitulo.getCodPagamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do pagamento é obrigatório.");
            return false;
        } else if (String.valueOf(pagamentoTitulo.getCodTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do titulo é obrigatório.");
            return false;
        } else if (String.valueOf(pagamentoTitulo.getCodTipoPagamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de pagamento é obrigatório.");
            return false;
        }
        else if(String.valueOf(pagamentoTitulo.getValorPagamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do valor do pagamento é obrigatório.");
            return false;
        }
        else if(pagamentoTitulo.getDataPagamento().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da data de pagamento é obrigatório.");
            return false;
        }
        return true;
    }

}
