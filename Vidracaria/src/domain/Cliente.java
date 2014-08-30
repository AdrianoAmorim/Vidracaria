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
public class Cliente {

    protected String cpf;
    protected String nome;
    protected String rg;
    protected String endereco;
    protected String telResidencial;
    protected String telCelular;

    public boolean setCpf(String cpf) {
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF inválido !", "AVISO", 2);
            return false;
        }
        this.cpf = cpf;
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean setNome(String nome) {
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido !", "AVISO", 2);
            return false;
        }
        this.nome = nome;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public boolean setRg(String rg) {
        if (rg.isEmpty()) {
            JOptionPane.showMessageDialog(null, "RG inválido !", "AVISO", 2);
            return false;
        }
        this.rg = rg;
        return true;
    }

    public String getRg() {
        return rg;
    }

    public boolean setEndereco(String endereco) {
        if (endereco.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Endereço inválido !", "AVISO", 2);
            return false;
        }
        this.endereco = endereco;
        return true;
    }

    public String getEndereco() {
        return endereco;
    }

    public boolean setTelResidencial(String telResidencial) {
        this.telResidencial = telResidencial;
        return true;
    }

    public String getTelResidencial() {
        return telResidencial;
    }

    public boolean setTelCelular(String telCelular) {
        this.telCelular = telCelular;
        return true;
    }

    public String getTelCelular() {
        return telCelular;
    }

}
