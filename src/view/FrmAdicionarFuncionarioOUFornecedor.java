package view;

import crud.FornecedorCRUD;
import crud.FuncionarioCRUD;
import domain.Fornecedor;
import domain.Funcionario;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adriano
 */
public class FrmAdicionarFuncionarioOUFornecedor extends javax.swing.JDialog {

    Fornecedor fornecedor = new Fornecedor();
    Funcionario funcionario = new Funcionario();
    int indice = 0;

    /**
     * Creates new form FrmBuscarFornecedor
     */
    public FrmAdicionarFuncionarioOUFornecedor(java.awt.Frame parent, boolean modal, int indice) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        this.indice = indice;

        if (indice == 2) {
           lblFuncOuForn.setText("Cargo:");
           this.setTitle("Pesquisar Funcionario");
        } else if (indice == 3) {
            lblFuncOuForn.setText("Cargo:");
            this.setTitle("Pesquisar Vendedor");
        }
    }   
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgTipoClienteBusca = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfBuscarFuncionarioCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfBuscarFuncionarioNome = new javax.swing.JTextField();
        lblFuncOuForn = new javax.swing.JLabel();
        tfBuscarFuncionarioCargo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBuscaFuncionarioResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Fornecedor");

        rgTipoClienteBusca.setBackground(new java.awt.Color(204, 204, 255));
        rgTipoClienteBusca.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 69, 139));
        jLabel7.setText("Código:");

        tfBuscarFuncionarioCodigo.setBackground(new java.awt.Color(204, 255, 204));
        tfBuscarFuncionarioCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfBuscarFuncionarioCodigo.setForeground(new java.awt.Color(255, 0, 0));
        tfBuscarFuncionarioCodigo.setPreferredSize(new java.awt.Dimension(0, 0));
        tfBuscarFuncionarioCodigo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfBuscarFuncionarioCodigoCaretUpdate(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 69, 139));
        jLabel1.setText("Nome:");

        tfBuscarFuncionarioNome.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfBuscarFuncionarioNome.setPreferredSize(new java.awt.Dimension(0, 0));
        tfBuscarFuncionarioNome.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfBuscarFuncionarioNomeCaretUpdate(evt);
            }
        });

        lblFuncOuForn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFuncOuForn.setForeground(new java.awt.Color(0, 69, 139));
        lblFuncOuForn.setText("CNPJ:");

        tfBuscarFuncionarioCargo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfBuscarFuncionarioCargo.setPreferredSize(new java.awt.Dimension(0, 0));
        tfBuscarFuncionarioCargo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfBuscarFuncionarioCargoCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout rgTipoClienteBuscaLayout = new javax.swing.GroupLayout(rgTipoClienteBusca);
        rgTipoClienteBusca.setLayout(rgTipoClienteBuscaLayout);
        rgTipoClienteBuscaLayout.setHorizontalGroup(
            rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rgTipoClienteBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lblFuncOuForn)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfBuscarFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBuscarFuncionarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBuscarFuncionarioCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        rgTipoClienteBuscaLayout.setVerticalGroup(
            rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rgTipoClienteBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBuscarFuncionarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBuscarFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBuscarFuncionarioCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFuncOuForn))
                .addContainerGap())
        );

        tbBuscaFuncionarioResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Celular", "Fixo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBuscaFuncionarioResult.setPreferredSize(new java.awt.Dimension(200, 64));
        tbBuscaFuncionarioResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBuscaFuncionarioResultMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBuscaFuncionarioResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rgTipoClienteBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rgTipoClienteBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Funcionario> pesquisarFuncionarioCaretUpdate(JTextField campo) {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

        if (!campo.getText().isEmpty()) {
            if (lblFuncOuForn.getText().equalsIgnoreCase("Cargo:")) {
                FuncionarioCRUD funcionarioCRUD = new FuncionarioCRUD();
                listaFuncionarios = funcionarioCRUD.consultarFuncionario(tfBuscarFuncionarioCodigo,
                        tfBuscarFuncionarioNome, tfBuscarFuncionarioCargo);
            }
        }
        return listaFuncionarios;
    }

    private ArrayList<Fornecedor> pesquisarFornecedorCaretUpdate(JTextField campo) {
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();
        if (!campo.getText().isEmpty()) {
            if (lblFuncOuForn.getText().equalsIgnoreCase("CNPJ:")) {
                FornecedorCRUD fornecedorCRUD = new FornecedorCRUD();

                listaFornecedores = fornecedorCRUD.consultarFornecedores(tfBuscarFuncionarioCodigo,
                        tfBuscarFuncionarioNome, tfBuscarFuncionarioCargo);
            }
        }
        return listaFornecedores;
    }

    private void tfBuscarFuncionarioCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfBuscarFuncionarioCodigoCaretUpdate
        DefaultTableModel tabelaModelo = (DefaultTableModel) tbBuscaFuncionarioResult.getModel();
        tabelaModelo.setRowCount(0);

        if (lblFuncOuForn.getText().equalsIgnoreCase("Cargo:")) {
            for (Funcionario funcionario : this.pesquisarFuncionarioCaretUpdate(tfBuscarFuncionarioCodigo)) {
                tabelaModelo.addRow(new Object[]{funcionario.getCodFuncionario(), funcionario.getNomeFuncionario(),
                    funcionario.getTelCel(), funcionario.getTelFixo()});
            }
        } else if (lblFuncOuForn.getText().equalsIgnoreCase("CNPJ:")) {
            for (Fornecedor fornecedor : this.pesquisarFornecedorCaretUpdate(tfBuscarFuncionarioCodigo)) {
                tabelaModelo.addRow(new Object[]{fornecedor.getCodFornecedor(), fornecedor.getNome(),
                    fornecedor.getTelCel(), fornecedor.getTelFixo()});
            }
        }
    }//GEN-LAST:event_tfBuscarFuncionarioCodigoCaretUpdate

    private void tfBuscarFuncionarioNomeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfBuscarFuncionarioNomeCaretUpdate
        DefaultTableModel tabelaModelo = (DefaultTableModel) tbBuscaFuncionarioResult.getModel();
        tabelaModelo.setRowCount(0);

        if (lblFuncOuForn.getText().equalsIgnoreCase("Cargo:")) {
            for (Funcionario funcionario : this.pesquisarFuncionarioCaretUpdate(tfBuscarFuncionarioNome)) {
                tabelaModelo.addRow(new Object[]{funcionario.getCodFuncionario(), funcionario.getNomeFuncionario(),
                    funcionario.getTelCel(), funcionario.getTelFixo()});
            }
        } else if (lblFuncOuForn.getText().equalsIgnoreCase("CNPJ:")) {
            for (Fornecedor fornecedor : this.pesquisarFornecedorCaretUpdate(tfBuscarFuncionarioNome)) {
                tabelaModelo.addRow(new Object[]{fornecedor.getCodFornecedor(), fornecedor.getNome(),
                    fornecedor.getTelCel(), fornecedor.getTelFixo()});
            }
        }
    }//GEN-LAST:event_tfBuscarFuncionarioNomeCaretUpdate

    private void tfBuscarFuncionarioCargoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfBuscarFuncionarioCargoCaretUpdate
        DefaultTableModel tabelaModelo = (DefaultTableModel) tbBuscaFuncionarioResult.getModel();
        tabelaModelo.setRowCount(0);

        if (lblFuncOuForn.getText().equalsIgnoreCase("Cargo:")) {
            for (Funcionario funcionario : this.pesquisarFuncionarioCaretUpdate(tfBuscarFuncionarioCargo)) {
                tabelaModelo.addRow(new Object[]{funcionario.getCodFuncionario(), funcionario.getNomeFuncionario(),
                    funcionario.getTelCel(), funcionario.getTelFixo()});
            }
        } else if (lblFuncOuForn.getText().equalsIgnoreCase("CNPJ:")) {
            for (Fornecedor fornecedor : this.pesquisarFornecedorCaretUpdate(tfBuscarFuncionarioCargo)) {
                tabelaModelo.addRow(new Object[]{fornecedor.getCodFornecedor(), fornecedor.getNome(),
                    fornecedor.getTelCel(), fornecedor.getTelFixo()});
            }
        }
    }//GEN-LAST:event_tfBuscarFuncionarioCargoCaretUpdate

    private void tbBuscaFuncionarioResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBuscaFuncionarioResultMouseClicked
        if (lblFuncOuForn.getText().equalsIgnoreCase("Cargo:")) {
            DefaultTableModel modelo = (DefaultTableModel) tbBuscaFuncionarioResult.getModel();
            FuncionarioCRUD funcionarioCRUD = new FuncionarioCRUD();

            this.funcionario = funcionarioCRUD.consultarFuncionario(modelo.getValueAt(tbBuscaFuncionarioResult.getSelectedRow(), 1).toString(), 0);
            this.dispose();
        } else if (lblFuncOuForn.getText().equalsIgnoreCase("CNPJ:")) {
            DefaultTableModel modelo = (DefaultTableModel) tbBuscaFuncionarioResult.getModel();
            FornecedorCRUD fornecedorCRUD = new FornecedorCRUD();

            this.fornecedor = fornecedorCRUD.consultarFornecedorPorNome(modelo.getValueAt(tbBuscaFuncionarioResult.getSelectedRow(), 1).toString());
            this.dispose();
        }
    }//GEN-LAST:event_tbBuscaFuncionarioResultMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarFuncionarioOUFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarFuncionarioOUFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarFuncionarioOUFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdicionarFuncionarioOUFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdicionarFuncionarioOUFornecedor dialog = new FrmAdicionarFuncionarioOUFornecedor(new javax.swing.JFrame(), true, 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFuncOuForn;
    private javax.swing.JPanel rgTipoClienteBusca;
    private javax.swing.JTable tbBuscaFuncionarioResult;
    private javax.swing.JTextField tfBuscarFuncionarioCargo;
    private javax.swing.JTextField tfBuscarFuncionarioCodigo;
    private javax.swing.JTextField tfBuscarFuncionarioNome;
    // End of variables declaration//GEN-END:variables
}
