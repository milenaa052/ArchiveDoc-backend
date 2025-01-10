package com.archivedoc_backend.ArchiveDoc.repository;

import com.archivedoc_backend.ArchiveDoc.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}