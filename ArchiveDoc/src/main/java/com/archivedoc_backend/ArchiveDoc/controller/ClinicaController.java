package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.ClinicaRequestDTO;
import com.archivedoc.ArchiveDoc.model.Clinica;
import com.archivedoc_backend.ArchiveDoc.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @GetMapping
    public ResponseEntity<List<Clinica>> findAll() {
        List<Clinica> clinica = this.clinicaRepository.findAll();
        return ResponseEntity.ok(clinica);
    }

    @GetMapping("/{id}")
    public Clinica findById(@PathVariable Integer id) {
        return this.clinicaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clinica não encontrada."));
    }

    @PostMapping
    public ResponseEntity<Clinica> save(@RequestBody ClinicaRequestDTO dto) {
        if(dto.email().isEmpty() || dto.senha().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Clinica clinica = new Clinica();
        clinica.setEmail(dto.email());
        clinica.setSenha(dto.senha());

        this.clinicaRepository.save(clinica);
        return ResponseEntity.ok(clinica);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Clinica> partialUpdate(@PathVariable Integer id, @RequestBody ClinicaRequestDTO dto) {
        Clinica clinica = this.clinicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clinica não encontrada."));

        if (dto.email().isEmpty()) {
            clinica.setEmail(dto.email());
        }

        if (dto.senha().isEmpty()) {
            clinica.setSenha(dto.senha());
        }

        this.clinicaRepository.save(clinica);
        return ResponseEntity.ok(clinica);
    }

    @PutMapping
    public ResponseEntity<Clinica> update(@PathVariable Integer id, @RequestBody ClinicaRequestDTO dto) {
        if (dto.email().isEmpty() || dto.senha().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Clinica clinica = this.clinicaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clinica não encontrada."));

        clinica.setEmail(dto.email());
        clinica.setSenha(dto.senha());

        this.clinicaRepository.save(clinica);
        return ResponseEntity.ok(clinica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Clinica clinica = this.clinicaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clinica não encontrada."));

        this.clinicaRepository.delete(clinica);
        return ResponseEntity.noContent().build();
    }
}
