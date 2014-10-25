/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProdutoController;
import crud.ProdutoCRUD;
import domain.Produto;

/**
 *
 * @author Adriano
 */
public class FrmCadastroProduto extends javax.swing.JDialog {

    /**
     * Creates new form FrmCadastroProduto
     */
    public FrmCadastroProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel20 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnProdutoAlterar = new javax.swing.JButton();
        btnProdutoCadastrar = new javax.swing.JButton();
        tfProdutoDescricao = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        tfProdutoPrecoVenda = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        tfProdutoQuantidade = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        tfProdutoPrecoCusto = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        cbUnidadeMedida = new javax.swing.JComboBox();
        tfProdutoCodigo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel20.setBackground(new java.awt.Color(153, 153, 255));
        jPanel20.setBorder(null);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Dados do Produto");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setBorder(null);

        btnProdutoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/AlterarPq.png"))); // NOI18N
        btnProdutoAlterar.setToolTipText("Alterar");
        btnProdutoAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProdutoAlterar.setEnabled(false);

        btnProdutoCadastrar.setBackground(new java.awt.Color(102, 0, 102));
        btnProdutoCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProdutoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastroPqn.png"))); // NOI18N
        btnProdutoCadastrar.setToolTipText("Cadastrar");
        btnProdutoCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProdutoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutoCadastrarActionPerformed(evt);
            }
        });

        tfProdutoDescricao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 69, 139));
        jLabel54.setText("Descrição:");

        tfProdutoPrecoVenda.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 69, 139));
        jLabel56.setText("Preço de venda:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 69, 139));
        jLabel57.setText("Quantidade:");

        tfProdutoQuantidade.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 69, 139));
        jLabel55.setText("Preço de custo:");

        tfProdutoPrecoCusto.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 69, 139));
        jLabel58.setText("Unidade de Medida:");

        cbUnidadeMedida.setBackground(new java.awt.Color(204, 204, 255));
        cbUnidadeMedida.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbUnidadeMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M²", "Un" }));
        cbUnidadeMedida.setToolTipText("");

        tfProdutoCodigo.setBackground(new java.awt.Color(204, 255, 204));
        tfProdutoCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfProdutoCodigo.setForeground(new java.awt.Color(255, 0, 0));
        tfProdutoCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfProdutoCodigo.setBorder(null);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 69, 139));
        jLabel21.setText("Código:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfProdutoPrecoCusto, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(tfProdutoCodigo))))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfProdutoPrecoVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(tfProdutoQuantidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProdutoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnProdutoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfProdutoDescricao))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(tfProdutoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(tfProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(tfProdutoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addComponent(tfProdutoPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(cbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(tfProdutoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnProdutoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProdutoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProdutoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoCadastrarActionPerformed
       Produto produto = new Produto();
       ProdutoController produtoController = new ProdutoController();
       
       produto.setCodProduto(Integer.parseInt(tfProdutoCodigo.getText()));
       produto.setDescricaoProduto(tfProdutoDescricao.getText());
       produto.setPrecoVenda(Double.parseDouble(tfProdutoPrecoVenda.getText()));
       produto.setQuantidadeEstoque(Double.parseDouble(tfProdutoQuantidade.getText()));
       produto.setUnidadeMedida(cbUnidadeMedida.getSelectedItem().toString());
       
       if(produtoController.validarProduto(produto)) {
           ProdutoCRUD produtoCRUD = new ProdutoCRUD();
           
           produtoCRUD.inserirProduto(produto);          
       }
       
       // limpa os campos do formulário
       FrmPrincipal.limparCampos(tfProdutoCodigo, tfProdutoDescricao, tfProdutoPrecoCusto,
               tfProdutoPrecoVenda, tfProdutoQuantidade);       
    }//GEN-LAST:event_btnProdutoCadastrarActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProdutoAlterar;
    private javax.swing.JButton btnProdutoCadastrar;
    private javax.swing.JComboBox cbUnidadeMedida;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JTextField tfProdutoCodigo;
    private javax.swing.JTextField tfProdutoDescricao;
    private javax.swing.JTextField tfProdutoPrecoCusto;
    private javax.swing.JTextField tfProdutoPrecoVenda;
    private javax.swing.JTextField tfProdutoQuantidade;
    // End of variables declaration//GEN-END:variables
}