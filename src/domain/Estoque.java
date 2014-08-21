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
public class Estoque {

    protected int codigoProduto;
    protected Double quantidadeAtual;
    protected Double quantidadeMinima;

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public boolean setCodigoProduto(int codigoProduto) {
        if (Integer.toString(codigoProduto).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo do produto inválido !", "AVISO", 2);
            return false;
        }
        this.codigoProduto = codigoProduto;
        return true;
    }

    public Double getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public boolean setQuantidadeAtual(Double quantidadeAtual) {
        if (quantidadeAtual.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade atual inválida !", "AVISO", 2);
            return false;
        }
        this.quantidadeAtual = quantidadeAtual;
        return true;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public boolean setQuantidadeMinima(Double quantidadeMinima) {
        if (quantidadeMinima.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade mínima inválida !", "AVISO", 2);
            return false;
        }
        this.quantidadeMinima = quantidadeMinima;
        return true;
    }
}
