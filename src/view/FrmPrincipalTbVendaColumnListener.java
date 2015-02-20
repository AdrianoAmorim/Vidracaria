package view;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author rafael
 */
public class FrmPrincipalTbVendaColumnListener implements ListSelectionListener {

    JTable tabela;

    public FrmPrincipalTbVendaColumnListener(JTable tabela) {
        this.tabela = tabela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if ((tabela.getSelectedColumn() == 1) || (tabela.getSelectedColumn() == 2)) {
            Double qtdProduto = Double.parseDouble(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            Double precoVenda = Double.parseDouble(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
            Double totalLinha = qtdProduto * precoVenda;
            tabela.getModel().setValueAt(totalLinha, tabela.getSelectedRow(), 3);
        }
    }

}
