package controller;

import domain.Venda;

import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class VendaController {

    public boolean validarVenda(Venda venda) {
        if (String.valueOf(venda.getCodVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da venda é obrigatório.");
            return false;
        } else if (String.valueOf(venda.getCodTipoRenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de renda é obrigatório.");
            return false;
        } else if (String.valueOf(venda.getCodCliente()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do cliente é obrigatório.");
            return false;
        } else if (String.valueOf(venda.getCodParcelamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do parcelamento é obrigatório.");
            return false;
        } else if (String.valueOf(venda.getTotalVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do total da venda é obrigatório.");
            return false;
        } else if (venda.getDataVenda().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da data da venda é obrigatório.");
            return false;
        }
        return true;
    }
}
