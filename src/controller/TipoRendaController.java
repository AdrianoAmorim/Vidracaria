
package controller;

import domain.TipoRenda;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TipoRendaController {
    
        public boolean validarTipoRenda(TipoRenda tipoRenda){
       if(String.valueOf(tipoRenda.getCodTipoRenda()).isEmpty()){
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do tipo de renda é obrigatório!");
            return false;
       } else if (tipoRenda.getDescricaoTipoRenda().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descricao do tipo de renda é obrigatório!");
            return false;
        }else if (tipoRenda.getTotalRenda().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "O Preenchimento do total da renda é obrigatório!");
            return false;
        }
        return true; 
    }
    
}
