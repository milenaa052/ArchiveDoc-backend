package com.archivedoc_backend.ArchiveDoc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente;

    private String nome;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "psicologoId", referencedColumnName = "idPsicologo")
    @JsonIgnore
    private Psicologo psicologo;

    private String convenio;

    @OneToMany(mappedBy = "paciente")
    private List<Guias> guias;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public List<Guias> getGuias() {
        return guias;
    }

    public void setGuias(List<Guias> guias) {
        this.guias = guias;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return idPaciente == paciente.idPaciente && Objects.equals(nome, paciente.nome) && Objects.equals(cpf, paciente.cpf) && Objects.equals(psicologo, paciente.psicologo) && Objects.equals(convenio, paciente.convenio) && Objects.equals(guias, paciente.guias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaciente, nome, cpf, psicologo, convenio, guias);
    }
}