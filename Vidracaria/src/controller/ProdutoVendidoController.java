package controller;

import domain.ProdutoVendido;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ProdutoVendidoController {

    public boolean validarProdutoVendido(ProdutoVendido produtoVendido) {
        if (String.valueOf(produtoVendido.getCodProduto()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do código do produto é obrigatório");
            return false;
        } else if (String.valueOf(produtoVendido.getCodVenda()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da venda é obrigatório.");
            return false;
        }
        return true;
    }
}
