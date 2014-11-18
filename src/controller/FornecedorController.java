package controller;

import domain.Fornecedor;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class FornecedorController {

    public boolean validarFornecedor(Fornecedor fornecedor) {
        if (String.valueOf(fornecedor.getCodFornecedor()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do fornecedor é obrigatório.");
            return false;
        } else if (fornecedor.getCnpj().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do CNPJ do fornecedor é obrigatório.");
            return false;
        } else if (fornecedor.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do fornecedor é obrigatório.");
            return false;
        }
        return true;
    }
}
