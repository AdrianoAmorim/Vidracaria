package controller;

import domain.ProdutoVendido;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ProdutoVendidoController {

    public boolean validarProdutoVendido(ProdutoVendido produtoVendido) {
        if (String.valueOf(produtoVendido.getQuantidadeProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade vendida é obrigatório.");
            return false;
        } else if (String.valueOf(produtoVendido.getPrecoVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do preco de venda é obrigatório.");
            return false;
        }
        
        return true;
    }
}
