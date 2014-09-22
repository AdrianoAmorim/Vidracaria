
package controller;

import domain.Despesa;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class DespesaController {
    
    public boolean validarDespesa(Despesa despesa) {
        if(String.valueOf(despesa.getCodEmpresa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da empresa é obrigatório.");
            return false;
        }
        else if(String.valueOf(despesa.getCodTipoDespesa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de despesa é obrigatório.");
            return false;                   
        }
        else if(despesa.getMesAno().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do mes/ano da despesa é obrigatório.");
            return false;
        }
        return true;
    }    
}
