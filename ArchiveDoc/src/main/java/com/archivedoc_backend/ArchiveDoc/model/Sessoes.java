package com.archivedoc_backend.ArchiveDoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Sessoes")
public class Sessoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSessao;

    @ManyToOne
    @JoinColumn(name = "guiaId", referencedColumnName = "idGuia")
    @JsonIgnore
    private Guias guiaId;

    private Date dataSessao;

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Guias getGuiaId() {
        return guiaId;
    }

    public void setGuiaId(Guias guiaId) {
        this.guiaId = guiaId;
    }

    public Date getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(Date dataSessao) {
        this.dataSessao = dataSessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessoes sessoes = (Sessoes) o;
        return idSessao == sessoes.idSessao && Objects.equals(guiaId, sessoes.guiaId) && Objects.equals(dataSessao, sessoes.dataSessao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSessao, guiaId, dataSessao);
    }
}