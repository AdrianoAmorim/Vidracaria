/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
       } else if (tipoDespesa.getDescricaoDespesa().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descricao do tipo de despesa é obrigatório!");
            return false;
        }else if (tipoDespesa.getTotalDespesa().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "O Preenchimento do total da despesa é obrigatório!");
            return false;
        }
        return true; 
    }
    
    
    
}
