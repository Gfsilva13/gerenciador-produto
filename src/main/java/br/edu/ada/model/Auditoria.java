package br.edu.ada.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Auditoria extends PanacheEntity {

    private String mensagem;

    protected Auditoria(){

    }

    public Auditoria(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
