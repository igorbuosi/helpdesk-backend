package com.helpdesk.helpdek.domain;

import com.helpdesk.helpdek.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente") // um cliente para muitos chamados
    private List<Chamado> chamados = new ArrayList<>();


    //lista de chamados nao esta nos construtores pois na hora que formos instanciar um novo clinete nao passaremos o chamado
    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
