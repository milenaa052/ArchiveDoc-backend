package com.archivedoc_backend.ArchiveDoc.dto;

import com.archivedoc_backend.ArchiveDoc.model.Guias;

import java.util.Date;

public record SessoesRequestDTO(Integer guiaId, Date dataSessao) {
}
