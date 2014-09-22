
package controller;

import domain.AlteracaoPreco;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class AlteracaoPrecoController {
    public boolean validarAlteracaoPreco(AlteracaoPreco alteracaoPreco) {
        if(String.valueOf(alteracaoPreco.getCodAlteracaoPreco()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da alteracao é obrigatório.");
            return false;
        }
        else if(String.valueOf(alteracaoPreco.getCodProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do produto é obrigatório.");
            return false;
        }
        else if(String.valueOf(alteracaoPreco.getCodFuncionario()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do funcionário é obrigatório.");
            return false;
        }
        else if(alteracaoPreco.getDataAlteracao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da data da alteração é obrigatório.");
            return false;
        }
        else if(String.valueOf(alteracaoPreco.getPrecoVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da alteração do preço é obrigatório.");
            return false;
        }
        return true;
    }
            
    
}
