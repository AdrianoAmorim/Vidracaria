/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.swing.JOptionPane;

/**
 *
 * @author debian
 */
public class Produto {

    private String codigoProduto;
    private String descricaoProduto;
    private Double precoCusto;
    private Double precoVenda;
    private String unidadeMedida;

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public boolean setCodigoProduto(String codigoProduto) {
        if (codigoProduto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo do produto inválido !", "AVISO", 2);
            return false;
        }
        this.codigoProduto = codigoProduto;
        return true;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public boolean setDescricaoProduto(String descricaoProduto) {
        if (descricaoProduto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição do produto inválida !", "AVISO", 2);
            return false;
        }
        this.descricaoProduto = descricaoProduto;
        return true;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public boolean setPrecoCusto(Double precoCusto) {
        if (precoCusto.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preco de custo do produto inválido !", "AVISO", 2);
            return false;
        }
        this.precoCusto = precoCusto;
        return true;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public boolean setPrecoVenda(Double precoVenda) {
        if (precoVenda.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preco de venda do produto inválido !", "AVISO", 2);
            return false;
        }
        this.precoVenda = precoVenda;
        return true;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public boolean setUnidadeMedida(String unidadeMedida) {
        if (unidadeMedida.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Unidade de medida do produto inválida !", "AVISO", 2);
            return false;
        }
        this.unidadeMedida = unidadeMedida;
        return true;
    }
}
