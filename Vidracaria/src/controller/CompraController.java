package controller;

import domain.Compra;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class CompraController {

    public boolean validarCompra(Compra compra) {
        if (String.valueOf(compra.getCodCompra()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da compra é obrigatório.");
            return false;
        } else if (String.valueOf(compra.getCodFornecedor()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do fornecedor é obrigatório.");
            return false;
        } else if (String.valueOf(compra.getCodTipoDespesa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de despesa é obrigatório.");
            return false;
        } else if (compra.getDataCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da data da compra é obrigatório.");
            return false;
        }
        return true;
    }

}
