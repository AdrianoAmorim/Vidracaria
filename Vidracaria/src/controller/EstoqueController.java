/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Estoque;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class EstoqueController {

    public boolean validarEstoque(Estoque estoque) {
        if (estoque.getQuantidadeAtual().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade do produto em estoque é obrigatório.");
            return false;
        } else if (estoque.getQuantidadeMinima().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade mínima em estoque é obrigatório.");
            return false;
        } else {
            return true;
        }
    }
}
