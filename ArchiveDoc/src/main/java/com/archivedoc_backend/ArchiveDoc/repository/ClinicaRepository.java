package com.archivedoc_backend.ArchiveDoc.repository;

import com.archivedoc_backend.ArchiveDoc.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
    boolean existsByEmail(String email);
}
