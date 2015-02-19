package view;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author rafael
 */
public class FrmPrincipalTbVendaListener implements TableModelListener {

    JTable tabela;

    public FrmPrincipalTbVendaListener(JTable tabela) {
        this.tabela = tabela;
    }


    // FALTA ESPECIFICAR QUAL CAMPO FOI EDITADO
    @Override
    public void tableChanged(TableModelEvent e) {
        try {
            for (int i = 0; i < tabela.getRowCount(); i++) {
                Object qtdProduto = tabela.getValueAt(1, 1);
                Object precoVenda = tabela.getValueAt(1, 2);
                Double totalLinha = ((Double.valueOf(qtdProduto.toString())) * (Double.valueOf(precoVenda.toString())));
                tabela.setValueAt(totalLinha, 1, 3);
           }
        } catch (NullPointerException erroTotalProduto) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular o total do Produto.\n"
                    + "Verifique se os campos de quantidade e preço estão devidamente preenchidos.");
        }
    }

}
