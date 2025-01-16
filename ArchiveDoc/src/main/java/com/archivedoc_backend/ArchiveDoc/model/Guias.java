package com.archivedoc_backend.ArchiveDoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Guias")
public class Guias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGuia;

    private int numeroGuia;

    private Date dataPedido;

    private Date dataLiberacao;

    private String tipoSessao;

    private int quantidadeSessoes;

    private Boolean status;

    private Date dataPrimeiraSessao;

    @OneToMany(mappedBy = "guiaId")
    private List<Sessoes> sessoes;

    @ManyToOne
    @JoinColumn(name = "pacienteId", referencedColumnName = "idPaciente")
    @JsonIgnore
    private Paciente paciente;

    public int getIdGuia() {
        return idGuia;
    }

    public void setIdGuia(int idGuia) {
        this.idGuia = idGuia;
    }

    public int getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(int numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public int getQuantidadeSessoes() {
        return quantidadeSessoes;
    }

    public void setQuantidadeSessoes(int quantidadeSessoes) {
        this.quantidadeSessoes = quantidadeSessoes;
    }

    public String getTipoSessao() {
        return tipoSessao;
    }

    public void setTipoSessao(String tipoSessao) {
        this.tipoSessao = tipoSessao;
    }

    public Date getDataPrimeiraSessao() {
        return dataPrimeiraSessao;
    }

    public void setDataPrimeiraSessao(Date dataPrimeiraSessao) {
        this.dataPrimeiraSessao = dataPrimeiraSessao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Sessoes> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessoes> sessoes) {
        this.sessoes = sessoes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guias guias = (Guias) o;
        return idGuia == guias.idGuia && numeroGuia == guias.numeroGuia && quantidadeSessoes == guias.quantidadeSessoes && Objects.equals(dataPedido, guias.dataPedido) && Objects.equals(dataLiberacao, guias.dataLiberacao) && Objects.equals(tipoSessao, guias.tipoSessao) && Objects.equals(status, guias.status) && Objects.equals(dataPrimeiraSessao, guias.dataPrimeiraSessao) && Objects.equals(sessoes, guias.sessoes) && Objects.equals(paciente, guias.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGuia, numeroGuia, dataPedido, dataLiberacao, tipoSessao, quantidadeSessoes, status, dataPrimeiraSessao, sessoes, paciente);
    }
}