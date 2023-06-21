package com.helpdesk.helpdek.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Chamado> chamados = new ArrayList<>();


    //lista de chamados nao esta nos construtores pois na hora que formos instanciar um novo clinete nao passaremos o chamado
    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
