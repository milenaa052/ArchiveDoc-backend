package com.archivedoc_backend.ArchiveDoc.repository;

import com.archivedoc_backend.ArchiveDoc.model.Sessoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessoesRepository extends JpaRepository<Sessoes, Integer> {
}