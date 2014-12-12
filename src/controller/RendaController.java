
package controller;

import domain.Renda;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class RendaController {
    
    public boolean validarRenda(Renda renda) {
        if(String.valueOf(renda.getCodEmpresa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da empresa é obrigatório.");
            return false;
        }
        else if(String.valueOf(renda.getCodTipoRenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo da renda é obrigatório.");
            return false;
        }
        else if(renda.getMesAno().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do mes/ano é obrigatório.");
            return false;
        }
        return true;
    }
}
