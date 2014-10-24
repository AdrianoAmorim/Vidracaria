
package controller;

import domain.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ClienteController {

    public boolean validarAtributos(Cliente cliente) {
        if(String.valueOf(cliente.getCodCliente()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do cliente é obrigatório");
            return false;
        }
        else if (cliente.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do cliente é obrigatório");
            return false;
        } else if (cliente.getCpf().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do CPF do cliente é obrigatório");
            return false;
        } else if(cliente.getRg().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do RG do cliente é obrigatório)");
            return false;
        } else {
            return true;
        }
    }
}