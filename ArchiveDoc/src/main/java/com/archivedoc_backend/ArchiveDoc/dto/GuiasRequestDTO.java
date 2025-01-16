package com.archivedoc_backend.ArchiveDoc.dto;

import com.archivedoc_backend.ArchiveDoc.model.Paciente;

import java.util.Date;

public record GuiasRequestDTO(Integer numeroGuia, Date dataPedido, Date dataLiberacao, String tipoSessao, Integer quantidadeSessoes, Boolean status, Date dataPrimeiraSessao, Integer pacienteId) {
}
