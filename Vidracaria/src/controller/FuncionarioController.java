package controller;

import domain.Funcionario;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class FuncionarioController {

    public boolean validarFuncionario(Funcionario funcionario) {
        if (String.valueOf(funcionario.getCodFuncionario()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do funcionario é obrigatório.");
            return false;
        } else if (String.valueOf(funcionario.getCodEmpresa()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo da empresa é obrigatório.");
            return false;
        } else if (String.valueOf(funcionario.getCodCargo()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do codigo do cargo é obrigatório.");
            return false;
        } else if (funcionario.getNomeFuncionario().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do nome do funcionario é obrigatório.");
            return false;
        } else if (String.valueOf(funcionario.getSalarioFuncionario()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preenchimento do salario do funcionario é obrigatório.");
            return false;
        }
        return true;
    }
}
