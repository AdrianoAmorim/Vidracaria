package controller;

import domain.Cargo;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class CargoController {

    public boolean validarCargo(Cargo cargo) {
        if (String.valueOf(cargo.getCodigoCargo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do cargo é obrigatório.");
            return false;
        } else if (cargo.getDescricaoCargo().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descrição do cargo é obrigatório.");
            return false;
        }
        return true;
    }
}
