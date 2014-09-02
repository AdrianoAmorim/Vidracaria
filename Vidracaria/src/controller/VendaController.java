/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Venda;

import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class VendaController {

    public boolean validarVenda(Venda venda) {
        if (venda.getDataVenda().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da data da venda é obrigatório.");
            return false;
        } else if (venda.getQuantidadeProduto().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade vendida é obrigatório.");
            return false;
        } else {
            return true;
        }
    }
}

