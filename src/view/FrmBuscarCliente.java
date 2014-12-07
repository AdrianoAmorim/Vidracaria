package view;

import crud.ClienteCRUD;
import domain.Cliente;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adriano
 */
public class FrmBuscarCliente extends javax.swing.JDialog {

    Cliente cliente = new Cliente();

    /**
     * Creates new form FrmBuscarCliente
     */
    public FrmBuscarCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBuscarCliente = new javax.swing.JTable();
        rgTipoClienteBusca = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfCodCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfNomeCliente = new javax.swing.JTextField();
        lblBuscaClienteDoc1 = new javax.swing.JLabel();
        tfCpfCliente = new javax.swing.JTextField();
        lblBuscaClienteDoc2 = new javax.swing.JLabel();
        tfRgCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfResidencialCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfCelularCliente = new javax.swing.JTextField();
        rbTipoFisicaCliente = new javax.swing.JRadioButton();
        rbTipoJuridicaCliente = new javax.swing.JRadioButton();
        lblTipoBuscaCliente = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Clientes");
        setResizable(false);

        tbBuscarCliente.setBackground(new java.awt.Color(204, 204, 255));
        tbBuscarCliente.setBorder(null);
        tbBuscarCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Nome", "CPF", "RG", "Residencial", "Celular"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBuscarClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBuscarCliente);
        if (tbBuscarCliente.getColumnModel().getColumnCount() > 0) {
            tbBuscarCliente.getColumnModel().getColumn(0).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbBuscarCliente.getColumnModel().getColumn(1).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbBuscarCliente.getColumnModel().getColumn(2).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(2).setPreferredWidth(80);
            tbBuscarCliente.getColumnModel().getColumn(3).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbBuscarCliente.getColumnModel().getColumn(4).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(4).setPreferredWidth(70);
            tbBuscarCliente.getColumnModel().getColumn(5).setResizable(false);
            tbBuscarCliente.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        rgTipoClienteBusca.setBackground(new java.awt.Color(204, 204, 255));
        rgTipoClienteBusca.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 69, 139));
        jLabel7.setText("Código:");

        tfCodCliente.setBackground(new java.awt.Color(204, 255, 204));
        tfCodCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfCodCliente.setForeground(new java.awt.Color(255, 0, 0));
        tfCodCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfCodCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfCodClienteCaretUpdate(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 69, 139));
        jLabel1.setText("Nome:");

        tfNomeCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfNomeCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfNomeCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfNomeClienteCaretUpdate(evt);
            }
        });

        lblBuscaClienteDoc1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBuscaClienteDoc1.setForeground(new java.awt.Color(0, 69, 139));
        lblBuscaClienteDoc1.setText("CPF:");

        tfCpfCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfCpfCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfCpfCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfCpfClienteCaretUpdate(evt);
            }
        });

        lblBuscaClienteDoc2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBuscaClienteDoc2.setForeground(new java.awt.Color(0, 69, 139));
        lblBuscaClienteDoc2.setText("RG:");

        tfRgCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfRgCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfRgCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfRgClienteCaretUpdate(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 69, 139));
        jLabel6.setText("Residencial:");

        tfResidencialCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfResidencialCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfResidencialCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfResidencialClienteCaretUpdate(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 69, 139));
        jLabel5.setText("Celular:");

        tfCelularCliente.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfCelularCliente.setPreferredSize(new java.awt.Dimension(0, 0));
        tfCelularCliente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfCelularClienteCaretUpdate(evt);
            }
        });

        buttonGroup1.add(rbTipoFisicaCliente);
        rbTipoFisicaCliente.setText("Física");
        rbTipoFisicaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTipoFisicaClienteActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbTipoJuridicaCliente);
        rbTipoJuridicaCliente.setText("Juridica");
        rbTipoJuridicaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTipoJuridicaClienteActionPerformed(evt);
            }
        });

        lblTipoBuscaCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipoBuscaCliente.setForeground(new java.awt.Color(0, 69, 139));
        lblTipoBuscaCliente.setText("Tipo:");

        javax.swing.GroupLayout rgTipoClienteBuscaLayout = new javax.swing.GroupLayout(rgTipoClienteBusca);
        rgTipoClienteBusca.setLayout(rgTipoClienteBuscaLayout);
        rgTipoClienteBuscaLayout.setHorizontalGroup(
            rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rgTipoClienteBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rgTipoClienteBuscaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfResidencialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rgTipoClienteBuscaLayout.createSequentialGroup()
                        .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lblBuscaClienteDoc1)
                            .addComponent(lblTipoBuscaCliente))
                        .addGap(29, 29, 29)
                        .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(rgTipoClienteBuscaLayout.createSequentialGroup()
                                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCpfCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(tfCodCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblBuscaClienteDoc2)))
                            .addComponent(rbTipoFisicaCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbTipoJuridicaCliente)
                            .addComponent(tfRgCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        rgTipoClienteBuscaLayout.setVerticalGroup(
            rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rgTipoClienteBuscaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTipoFisicaCliente)
                    .addComponent(rbTipoJuridicaCliente)
                    .addComponent(lblTipoBuscaCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscaClienteDoc2)
                    .addComponent(tfRgCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscaClienteDoc1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tfCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rgTipoClienteBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(tfResidencialCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.setBorder(null);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Opções de Pesquisa");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(rgTipoClienteBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rgTipoClienteBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarClienteCaretUpdate() {
        ClienteCRUD clienteCRUD = new ClienteCRUD();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String tipoCliente;

        if (rbTipoJuridicaCliente.isSelected()) {
            tipoCliente = "J";
        } else {
            tipoCliente = "F";
        }

        listaClientes = clienteCRUD.consultarCliente(tipoCliente, tfCodCliente, tfNomeCliente,
                tfCpfCliente, tfRgCliente, tfResidencialCliente, tfCelularCliente);

        DefaultTableModel modelo = (DefaultTableModel) tbBuscarCliente.getModel();
        modelo.setRowCount(0);

        for (Cliente cliente : listaClientes) {
            if (cliente.getTipoCliente().equalsIgnoreCase("F")) {
                modelo.addRow(new Object[]{cliente.getCodCliente(), cliente.getNome(), cliente.getCpf(),
                    cliente.getRg(), cliente.getTelFixo(), cliente.getTelCel()});
            } else if (cliente.getTipoCliente().equalsIgnoreCase("J")) {
                modelo.addRow(new Object[]{cliente.getCodCliente(), cliente.getNome(), cliente.getCnpj(),
                    cliente.getInscricaoEstadual(), cliente.getTelFixo(), cliente.getTelCel()});
            }
        }
    }

    private void tfCodClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCodClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfCodClienteCaretUpdate

    private void tfNomeClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfNomeClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfNomeClienteCaretUpdate

    private void tfCpfClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCpfClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfCpfClienteCaretUpdate

    private void tfRgClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfRgClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfRgClienteCaretUpdate

    private void tfResidencialClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfResidencialClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfResidencialClienteCaretUpdate

    private void tfCelularClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfCelularClienteCaretUpdate
        this.pesquisarClienteCaretUpdate();
    }//GEN-LAST:event_tfCelularClienteCaretUpdate

    private void tbBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBuscarClienteMouseClicked
        DefaultTableModel modelo = (DefaultTableModel) tbBuscarCliente.getModel();
        ClienteCRUD clienteCrud = new ClienteCRUD();

        this.cliente = clienteCrud.consultarCliente(modelo.getValueAt(tbBuscarCliente.getSelectedRow(), 1).toString(), 0);
        this.dispose();
    }//GEN-LAST:event_tbBuscarClienteMouseClicked

    private void rbTipoJuridicaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTipoJuridicaClienteActionPerformed
        tbBuscarCliente.getColumnModel().getColumn(2).setHeaderValue("CNPJ");
        tbBuscarCliente.getColumnModel().getColumn(3).setHeaderValue("Inscrição Estadual");
        tbBuscarCliente.getTableHeader().resizeAndRepaint();
        lblBuscaClienteDoc1.setText("CNPJ:");
        lblBuscaClienteDoc2.setText("Inscricao Estadual:");
    }//GEN-LAST:event_rbTipoJuridicaClienteActionPerformed

    private void rbTipoFisicaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTipoFisicaClienteActionPerformed
        tbBuscarCliente.getColumnModel().getColumn(2).setHeaderValue("CPF");
        tbBuscarCliente.getColumnModel().getColumn(3).setHeaderValue("RG");
        tbBuscarCliente.getTableHeader().resizeAndRepaint();
        lblBuscaClienteDoc1.setText("CPF:");
        lblBuscaClienteDoc2.setText("RG:");
    }//GEN-LAST:event_rbTipoFisicaClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscaClienteDoc1;
    private javax.swing.JLabel lblBuscaClienteDoc2;
    private javax.swing.JLabel lblTipoBuscaCliente;
    private javax.swing.JRadioButton rbTipoFisicaCliente;
    private javax.swing.JRadioButton rbTipoJuridicaCliente;
    private javax.swing.JPanel rgTipoClienteBusca;
    private javax.swing.JTable tbBuscarCliente;
    private javax.swing.JTextField tfCelularCliente;
    private javax.swing.JTextField tfCodCliente;
    private javax.swing.JTextField tfCpfCliente;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfResidencialCliente;
    private javax.swing.JTextField tfRgCliente;
    // End of variables declaration//GEN-END:variables
}
