package controller;

import domain.TituloCompra;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TituloCompraController {

    public boolean validarTituloCompra(TituloCompra tituloCompra) {
        if (String.valueOf(tituloCompra.getCodTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do titulo da compra é obrigatório.");
            return false;
        } else if (String.valueOf(tituloCompra.getCodCompra()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da compra é obrigatório.");
            return false;
        } else if (tituloCompra.getVencimentoTitulo().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do vencimento do titulo é obrigatório.");
            return false;
        } else if (String.valueOf(tituloCompra.getValorTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do valor do tiulo é obrigatório.");
            return false;
        }
        return true;
    }

}
