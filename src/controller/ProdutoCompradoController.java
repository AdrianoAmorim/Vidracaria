package controller;

import domain.ProdutoComprado;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ProdutoCompradoController {

    public boolean validarProdutoComprado(ProdutoComprado produtoComprado) {
        if (String.valueOf(produtoComprado.getCodCompra()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da compra é obrigatório.");
            return false;
        } else if (String.valueOf(produtoComprado.getCodProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do produto é obrigatório.");
            return false;
        } else if (String.valueOf(produtoComprado.getPrecoCusto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do preco de custo é obrigatório.");
            return false;
        } else if (String.valueOf(produtoComprado.getQuantidadeProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da quantidade do produto é obrigatório.");
            return false;
        }
        return true;
    }
}
