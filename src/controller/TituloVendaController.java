
package controller;

import domain.TituloVenda;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
public class TituloVendaController {

    public boolean validarTituloVenda(TituloVenda tituloVenda) {
        if (String.valueOf(tituloVenda.getCodTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do titulo é obrigatório!");
            return false;
        } else if (String.valueOf(tituloVenda.getCodVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da Venda é obrigatório!");
            return false;
        } else if (String.valueOf(tituloVenda.getVencimentoTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do Vencimento do Titulo é obrigatório!");
            return false;
        } else if (String.valueOf(tituloVenda.getValorTitulo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do Valor do Titulo é obrigatório!");
            return false;
        }
        return true;
    }

}
