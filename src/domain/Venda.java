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
public class Venda {

    private int codigoVenda;
    private int codigoProduto;
    private String codigoCliente;
    private int codigoTipoPagamento;
    private Double quantidadeProduto;
    private String dataVenda;

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public boolean setCodigoVenda(int codigoVenda) {
        if (Integer.toString(codigoVenda).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo da venda inválido !", "AVISO", 2);
            return false;
        }
        this.codigoVenda = codigoVenda;
        return true;
    }

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

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public boolean setQuantidadeProduto(Double quantidadeProduto) {
        if (Double.toString(quantidadeProduto).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade do produto inválido !", "AVISO", 2);
            return false;
        }
        this.quantidadeProduto = quantidadeProduto;
        return true;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public boolean setCodigoCliente(String codigoCliente) {
        if (codigoCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo do cliente inválido !", "AVISO", 2);
            return false;
        }
        this.codigoCliente = codigoCliente;
        return true;
    }

    public int getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public boolean setCodigoTipoPagamento(int codigoTipoPagamento) {
        if (Integer.toString(codigoTipoPagamento).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo do tipo do pagamento inválido !", "AVISO", 2);
            return false;
        }
        this.codigoTipoPagamento = codigoTipoPagamento;
        return true;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public boolean setDataVenda(String dataVenda) {
        if (dataVenda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data da venda inválida !", "AVISO", 2);
            return false;
        }
        this.dataVenda = dataVenda;
        return true;
    }

}
