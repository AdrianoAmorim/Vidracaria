
package controller;

import domain.Produto;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ProdutoController {
    
    public boolean validarProduto(Produto produto) {
        if(String.valueOf(produto.getCodProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do produto é obrigatório.");
            return false;
        }
        if(produto.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da descrição do produto é obrigatório.");
            return false;
        } else if(produto.getUnidadeMedida().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da unidade de medida do produto é obrigatório.");
            return false;
        } else if(produto.getQuantidadeEstoque().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade em estoque é obrigatório.");
            return false;
        } else if (produto.getPrecoVenda().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do preco de venda é obrigatório.");
            return false;
        } else {
            return true;        
        }
    }
}