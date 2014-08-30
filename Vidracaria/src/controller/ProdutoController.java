/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import domain.Produto;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ProdutoController {
    
    public boolean validarProduto(Produto produto) {
        if(produto.getDescricaoProduto().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descrição do produto é obrigatório.");
            return false;
        } else if(produto.getPrecoCusto().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do preço de custo do produto é obrigatório.");
            return false;
        } else if(produto.getPrecoVenda().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do preço de venda do produto é obrigatório.");
            return false;
        } else {
            return true;        
        }
    }
}