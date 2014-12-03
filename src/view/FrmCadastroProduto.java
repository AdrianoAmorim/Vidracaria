package view;

import controller.ClienteController;
import controller.ProdutoController;
import crud.ProdutoCRUD;
import domain.Produto;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Adriano
 */
public class FrmCadastroProduto extends javax.swing.JDialog {

    Produto produto = new Produto();

    /**
     * Creates new form FrmCadastroProduto
     */
    public FrmCadastroProduto(java.awt.Frame parent, boolean modal, boolean op) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        pnlProdutoOpBusca.setVisible(op);
        pnlProdutoTituloOpBusca.setVisible(op);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rgProdutoStatus = new javax.swing.ButtonGroup();
        jPanel20 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        tfProdutoDescricao = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        tfProdutoPrecoVenda = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        tfProdutoQuantidade = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        cbUnidadeMedida = new javax.swing.JComboBox();
        tfProdutoCodigo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnProdutoAlterar = new javax.swing.JButton();
        btnProdutoCadastrar = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        cbProdutoCategoria = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        rbProdutoStatusAtiv = new javax.swing.JRadioButton();
        rbProdutoStatusInat = new javax.swing.JRadioButton();
        pnlProdutoOpBusca = new javax.swing.JPanel();
        tfBuscaProdutoOpCod = new javax.swing.JTextField();
        tfBuscaProdutoOpDescricao = new javax.swing.JTextField();
        cbBuscaProdutoOpCategoria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCadastroProdutoResultConsulta = new javax.swing.JList();
        pnlProdutoTituloOpBusca = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();

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
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 69, 139));
        jLabel21.setText("Código:");

        btnProdutoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/AlterarPq.png"))); // NOI18N
        btnProdutoAlterar.setToolTipText("Alterar");
        btnProdutoAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProdutoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutoAlterarActionPerformed(evt);
            }
        });

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

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 69, 139));
        jLabel59.setText("Categoria:");

        cbProdutoCategoria.setBackground(new java.awt.Color(204, 204, 255));
        cbProdutoCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbProdutoCategoria.setToolTipText("");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 69, 139));
        jLabel60.setText("Status:");

        rgProdutoStatus.add(rbProdutoStatusAtiv);
        rbProdutoStatusAtiv.setText("Ativo");
        rbProdutoStatusAtiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProdutoStatusAtivActionPerformed(evt);
            }
        });

        rgProdutoStatus.add(rbProdutoStatusInat);
        rbProdutoStatusInat.setText("Inativo");
        rbProdutoStatusInat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProdutoStatusInatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnProdutoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnProdutoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel59)
                                .addGap(41, 41, 41))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addGap(43, 43, 43))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel56)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfProdutoDescricao)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbProdutoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfProdutoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfProdutoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbProdutoStatusInat)
                            .addComponent(rbProdutoStatusAtiv))
                        .addGap(97, 97, 97)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(tfProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(tfProdutoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(tfProdutoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(tfProdutoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(cbProdutoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58)
                            .addComponent(cbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(rbProdutoStatusAtiv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbProdutoStatusInat)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProdutoAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProdutoCadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pnlProdutoOpBusca.setBackground(new java.awt.Color(204, 204, 255));

        tfBuscaProdutoOpCod.setBackground(new java.awt.Color(204, 255, 204));
        tfBuscaProdutoOpCod.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfBuscaProdutoOpCod.setForeground(java.awt.Color.lightGray);
        tfBuscaProdutoOpCod.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfBuscaProdutoOpCodCaretUpdate(evt);
            }
        });

        tfBuscaProdutoOpDescricao.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tfBuscaProdutoOpDescricao.setForeground(java.awt.Color.lightGray);
        tfBuscaProdutoOpDescricao.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfBuscaProdutoOpDescricao.setToolTipText("");
        tfBuscaProdutoOpDescricao.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfBuscaProdutoOpDescricaoCaretUpdate(evt);
            }
        });

        cbBuscaProdutoOpCategoria.setBackground(new java.awt.Color(204, 204, 255));
        cbBuscaProdutoOpCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbBuscaProdutoOpCategoria.setToolTipText("");

        jlCadastroProdutoResultConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlCadastroProdutoResultConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlCadastroProdutoResultConsulta);

        javax.swing.GroupLayout pnlProdutoOpBuscaLayout = new javax.swing.GroupLayout(pnlProdutoOpBusca);
        pnlProdutoOpBusca.setLayout(pnlProdutoOpBuscaLayout);
        pnlProdutoOpBuscaLayout.setHorizontalGroup(
            pnlProdutoOpBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoOpBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProdutoOpBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfBuscaProdutoOpDescricao)
                    .addGroup(pnlProdutoOpBuscaLayout.createSequentialGroup()
                        .addComponent(tfBuscaProdutoOpCod, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbBuscaProdutoOpCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProdutoOpBuscaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProdutoOpBuscaLayout.setVerticalGroup(
            pnlProdutoOpBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoOpBuscaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(tfBuscaProdutoOpCod, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfBuscaProdutoOpDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbBuscaProdutoOpCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnlProdutoTituloOpBusca.setBackground(new java.awt.Color(153, 153, 255));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Opções");

        javax.swing.GroupLayout pnlProdutoTituloOpBuscaLayout = new javax.swing.GroupLayout(pnlProdutoTituloOpBusca);
        pnlProdutoTituloOpBusca.setLayout(pnlProdutoTituloOpBuscaLayout);
        pnlProdutoTituloOpBuscaLayout.setHorizontalGroup(
            pnlProdutoTituloOpBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoTituloOpBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProdutoTituloOpBuscaLayout.setVerticalGroup(
            pnlProdutoTituloOpBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoTituloOpBuscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProdutoOpBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlProdutoTituloOpBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlProdutoTituloOpBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProdutoOpBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setBounds(0, 0, 1034, 575);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Produto> pesquisarProdutoCaretUpdate(JTextField campo) {
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        if (!campo.getText().isEmpty()) {
            listaProdutos = produtoCRUD.consultarProdutos(tfBuscaProdutoOpCod, tfBuscaProdutoOpDescricao);
        }
        return listaProdutos;
    }

    private void btnProdutoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoCadastrarActionPerformed
        Produto produto = new Produto();
        ProdutoController produtoController = new ProdutoController();

        produto.setCodProduto(Integer.parseInt(tfProdutoCodigo.getText()));
        produto.setDescricao(tfProdutoDescricao.getText());
        produto.setPrecoVenda(Double.parseDouble(tfProdutoPrecoVenda.getText()));
        produto.setQuantidadeEstoque(Double.parseDouble(tfProdutoQuantidade.getText()));
        produto.setUnidadeMedida(cbUnidadeMedida.getSelectedItem().toString());

        // define o status do produto
        if (rbProdutoStatusAtiv.isSelected()) {
            // ativo
            produto.setStatus(true);
        } else if (rbProdutoStatusInat.isSelected()) {
            // inativo
            produto.setStatus(false);
        }

        if (produtoController.validarProduto(produto)) {
            ProdutoCRUD produtoCRUD = new ProdutoCRUD();

            produtoCRUD.inserirProduto(produto);
        }

        // limpa os campos do formulário
        FrmPrincipal.limparCampos(tfProdutoCodigo, tfProdutoDescricao,
                tfProdutoPrecoVenda, tfProdutoQuantidade);
    }//GEN-LAST:event_btnProdutoCadastrarActionPerformed

    private void tfBuscaProdutoOpCodCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfBuscaProdutoOpCodCaretUpdate
        DefaultListModel listModelo = new DefaultListModel();
        jlCadastroProdutoResultConsulta.setModel(listModelo);

        listModelo.removeAllElements();

        if (!tfBuscaProdutoOpCod.getText().isEmpty()) {
            for (Produto produto : this.pesquisarProdutoCaretUpdate(tfBuscaProdutoOpCod)) {
                listModelo.addElement(produto.getDescricao());
            }
        }
    }//GEN-LAST:event_tfBuscaProdutoOpCodCaretUpdate

    private void tfBuscaProdutoOpDescricaoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfBuscaProdutoOpDescricaoCaretUpdate
        DefaultListModel listModelo = new DefaultListModel();
        jlCadastroProdutoResultConsulta.setModel(listModelo);

        listModelo.removeAllElements();

        if (!tfBuscaProdutoOpDescricao.getText().isEmpty()) {
            for (Produto produto : this.pesquisarProdutoCaretUpdate(tfBuscaProdutoOpDescricao)) {
                listModelo.addElement(produto.getDescricao());
            }
        }
    }//GEN-LAST:event_tfBuscaProdutoOpDescricaoCaretUpdate

    private void jlCadastroProdutoResultConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCadastroProdutoResultConsultaMouseClicked
        ProdutoCRUD produtoCRUD = new ProdutoCRUD();

        this.produto = produtoCRUD.consultarProdutos(tfBuscaProdutoOpCod, tfBuscaProdutoOpDescricao).get(0);

        tfProdutoCodigo.setText(String.valueOf(produto.getCodProduto()));
        tfProdutoDescricao.setText(produto.getDescricao());
        tfProdutoPrecoVenda.setText(String.valueOf(produto.getPrecoVenda()));
        tfProdutoQuantidade.setText(String.valueOf(produto.getQuantidadeEstoque()));
        
        // define o status do produto
        if (produto.isStatus()) {
            // inativo
            rbProdutoStatusInat.setSelected(true);
        }
        else {
            // ativo
            rbProdutoStatusAtiv.setSelected(true);
        }
                
    }//GEN-LAST:event_jlCadastroProdutoResultConsultaMouseClicked

    private void rbProdutoStatusAtivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProdutoStatusAtivActionPerformed
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja ativar o produto?");
        if (op == 0) {
            // opção "sim"
        } else {
            rbProdutoStatusInat.setSelected(true);
        }
    }//GEN-LAST:event_rbProdutoStatusAtivActionPerformed

    private void rbProdutoStatusInatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProdutoStatusInatActionPerformed
        int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja desativar o produto?");
        if (op == 0) {
            // opção "sim"
        } else {
            rbProdutoStatusAtiv.setSelected(true);
        }
    }//GEN-LAST:event_rbProdutoStatusInatActionPerformed

    private void btnProdutoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoAlterarActionPerformed
        Produto produto = new Produto();
        ProdutoController produtoController = new ProdutoController();

        produto.setDescricao(tfProdutoDescricao.getText());
        produto.setPrecoVenda(Double.parseDouble(tfProdutoPrecoVenda.getText()));
        produto.setCodProduto(Integer.parseInt(tfProdutoCodigo.getText()));
        produto.setQuantidadeEstoque(Double.parseDouble(tfProdutoQuantidade.getText()));
        produto.setUnidadeMedida(cbUnidadeMedida.getSelectedItem().toString());

        // define o status do produto
        if (rbProdutoStatusAtiv.isSelected()) {
            // ativo
            produto.setStatus(true);
        } else if (rbProdutoStatusInat.isSelected()) {
            // inativo
            produto.setStatus(false);
        }

        if (produtoController.validarProduto(produto)) {
            ProdutoCRUD produtoCRUD = new ProdutoCRUD();

            produtoCRUD.atualizarProduto(produto);
        }
        
        // limpa os campos do formulario
        FrmPrincipal.limparCampos(tfProdutoCodigo, tfProdutoDescricao, 
                tfProdutoPrecoVenda, tfProdutoQuantidade);
        
        // reseta os radio buttons
        rbProdutoStatusAtiv.setSelected(false);
        rbProdutoStatusInat.setSelected(false);
    }//GEN-LAST:event_btnProdutoAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProdutoAlterar;
    private javax.swing.JButton btnProdutoCadastrar;
    private javax.swing.JComboBox cbBuscaProdutoOpCategoria;
    private javax.swing.JComboBox cbProdutoCategoria;
    private javax.swing.JComboBox cbUnidadeMedida;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jlCadastroProdutoResultConsulta;
    private javax.swing.JPanel pnlProdutoOpBusca;
    private javax.swing.JPanel pnlProdutoTituloOpBusca;
    private javax.swing.JRadioButton rbProdutoStatusAtiv;
    private javax.swing.JRadioButton rbProdutoStatusInat;
    private javax.swing.ButtonGroup rgProdutoStatus;
    private javax.swing.JTextField tfBuscaProdutoOpCod;
    private javax.swing.JTextField tfBuscaProdutoOpDescricao;
    private javax.swing.JTextField tfProdutoCodigo;
    private javax.swing.JTextField tfProdutoDescricao;
    private javax.swing.JTextField tfProdutoPrecoVenda;
    private javax.swing.JTextField tfProdutoQuantidade;
    // End of variables declaration//GEN-END:variables
}
