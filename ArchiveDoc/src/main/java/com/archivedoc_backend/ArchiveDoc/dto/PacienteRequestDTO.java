package com.archivedoc_backend.ArchiveDoc.dto;

import com.archivedoc_backend.ArchiveDoc.model.Psicologo;

public record PacienteRequestDTO(String nome, String cpf, Psicologo psicologo, String convenio) {
}
