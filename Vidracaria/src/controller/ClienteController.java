/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ClienteController {

    public boolean validarAtributos(Cliente cliente) {
        if (cliente.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do cliente é obrigatório");
            return false;
        } else if (cliente.getCpf().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do CPF do cliente é obrigatório");
            return false;
        } else if(cliente.getRg().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do RG do cliente é obrigatório)");
            return false;
        } else if(cliente.getEndereco().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do endereço do cliente é obrigatório)");
            return false;
        } else {
            return true;
        }
    }
}