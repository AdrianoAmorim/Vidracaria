package controller;

import domain.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ClienteController {

    public boolean validarAtributos(Cliente cliente) {
        if (String.valueOf(cliente.getCodCliente()).isEmpty()) {
            // codigo do cliente
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do cliente é obrigatório");
            return false;
        } else if (cliente.getTipoCliente().isEmpty()) {
            // tipo do cliente
            JOptionPane.showMessageDialog(null, "O preenchimento do tipo do cliente é obrigatório");
            return false;
        } else if (cliente.getNome().isEmpty()) {
            // nome do cliente
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do cliente é obrigatório");
            return false;
        }

        // validações específicadas pelo tipo do cliente
        if (cliente.getTipoCliente().equalsIgnoreCase("F")) {
            // pessoa física
            if (cliente.getCpf().isEmpty()) {
                // CPF
                JOptionPane.showMessageDialog(null, "O preenchimento do CPF é obrigatório");
                return false;
            } else if (cliente.getRg().isEmpty()) {
                // RG
                JOptionPane.showMessageDialog(null, "O preenchimento do RG é obrigatório");
                return false;
            }
        } else {
            // pessoa jurídica
            if (cliente.getCnpj().isEmpty()) {
                // CNPJ
                JOptionPane.showMessageDialog(null, "O preenchimento do CNPJ é obrigatório");
                return false;
            } else if (cliente.getInscricaoEstadual().isEmpty()) {
                // Inscrição Estadual
                JOptionPane.showMessageDialog(null, "O preenchimento da Inscrição Estadual é obrigatório");
                return false;
            }
        }

        // formas de contato
        if (cliente.getTelCel().isEmpty() && cliente.getTelFixo().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento de ao menos um telefone para contato é obrigatório");
            return false;
        }

        // se chegar até aqui, todos os atributos obrigatórios são válidos
        return true;
    }
}
