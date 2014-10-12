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
        } else if (fornecedor.getCnpjFornecedor().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do CNPJ do fornecedor é obrigatório.");
            return false;
        } else if (fornecedor.getNomeFornecedor().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do fornecedor é obrigatório.");
            return false;
        }
        else if(fornecedor.getEndereco().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do endereço do fornecedor � obrigat�rio.");
            return false;
        }
        else if(fornecedor.getBairro().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do bairro do fornecedor � obrigat�rio.");
            return false;
        }
        else if(fornecedor.getUf().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da UF do fornecedor � obrigat�rio.");
            return false;
        }
        return true;
    }
}
