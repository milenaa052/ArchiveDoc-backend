package com.archivedoc_backend.ArchiveDoc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Psicologo")
public class Psicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPsicologo;

    private String nome;

    private String crp;

    @OneToMany(mappedBy = "psicologo")
    @JsonBackReference
    private List<Paciente> pacientes;

    public int getIdPsicologo() {
        return idPsicologo;
    }

    public void setIdPsicologo(int idPsicologo) {
        this.idPsicologo = idPsicologo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Psicologo psicologo = (Psicologo) o;
        return idPsicologo == psicologo.idPsicologo && Objects.equals(nome, psicologo.nome) && Objects.equals(crp, psicologo.crp) && Objects.equals(pacientes, psicologo.pacientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPsicologo, nome, crp, pacientes);
    }
}