package controller;

import domain.Empresa;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class EmpresaController {

    public boolean validarEmpresa(Empresa empresa) {
        if (String.valueOf(empresa.getCodEmpresa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da mpresa é obrigatório");
            return false;
        } else if (empresa.getCnpjEmpresa().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do cnpj da empresa é obrigatório");
            return false;
        } else if (empresa.getRazaoSocial().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento da razao social da empresa é obrigatório");
            return false;
        } else if (empresa.getNomeFantasia().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome fantasia da empresa é obrigtório");
            return false;
        }
        return true;
    }
}
