package controller;

import domain.Parcelamento;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ParcelamentoController {

    public boolean validarParcelamento(Parcelamento parcelamento) {
        if (String.valueOf(parcelamento.getCodParcelamento()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do parcelamento é obrigatório.");
            return false;
        } else if (parcelamento.getDescricaoParcelamento().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descrição do parcelamento é obrigatório.");
            return false;
        } else if (String.valueOf(parcelamento.getQuantidadeParcelas()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade de parcelas é obrigatório.");
            return false;
        }
        return true;
    }

}
