package com.archivedoc_backend.ArchiveDoc.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Clinica")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClinica;

    private String email;

    private String senha;

    public int getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clinica clinica = (Clinica) o;
        return idClinica == clinica.idClinica && Objects.equals(email, clinica.email) && Objects.equals(senha, clinica.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClinica, email, senha);
    }
}