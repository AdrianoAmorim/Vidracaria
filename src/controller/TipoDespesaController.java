
package controller;

import domain.TipoDespesa;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
public class TipoDespesaController {
    
    public boolean validarTipoDespesa(TipoDespesa tipoDespesa){
       if(String.valueOf(tipoDespesa.getCodTipoDespesa()).isEmpty()){
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de despesa é obrigatório!");
            return false;
       } else if (tipoDespesa.getDescricaoTipoDespesa().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descricao do tipo de despesa é obrigatório!");
            return false;
        }else if (tipoDespesa.getTotalDespesa().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "O Preenchimento do total da despesa é obrigatório!");
            return false;
        }
        return true; 
    }
    
    
    
}
