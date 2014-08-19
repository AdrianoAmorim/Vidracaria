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

    private String codigoVenda;
    private String codigoProduto;
    private String codigoCliente;
    private String codigoTipoPagamento;
    private Double quantidadeProduto;
    private String dataVenda;

    public String getCodigoVenda() {
        return codigoVenda;
    }

    public boolean setCodigoVenda(String codigoVenda) {
        if (codigoVenda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo da venda inválido !", "AVISO", 2);
            return false;
        }
        this.codigoVenda = codigoVenda;
        return true;
    }

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

    public String getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public boolean setCodigoTipoPagamento(String codigoTipoPagamento) {
        if (codigoTipoPagamento.isEmpty()) {
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
