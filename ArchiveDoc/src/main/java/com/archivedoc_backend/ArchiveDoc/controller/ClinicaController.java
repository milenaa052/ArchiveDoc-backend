package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.ClinicaRequestDTO;
import com.archivedoc_backend.ArchiveDoc.model.Clinica;
import com.archivedoc_backend.ArchiveDoc.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Object> save(@RequestBody ClinicaRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(400).body("Email já cadastrado!");
        }

        if (clinicaRepository.existsByEmail(dto.email())) {
            System.out.println("Email já cadastrado: " + dto.email());
            return ResponseEntity.status(400).body("Email já cadastrado!");
        }

        Clinica clinica = new Clinica();
        clinica.setNome(dto.nome());
        clinica.setEmail(dto.email());
        clinica.setSenha(dto.senha());

        clinicaRepository.save(clinica);
        return ResponseEntity.status(200).body("Cadastro realizado com sucesso!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Clinica> partialUpdate(@PathVariable Integer id, @RequestBody ClinicaRequestDTO dto) {
        Clinica clinica = this.clinicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clinica não encontrada."));

        if (dto.nome() != null) {
            clinica.setNome(dto.nome());
        }

        if (dto.email() != null) {
            clinica.setEmail(dto.email());
        }

        if (dto.senha() != null) {
            clinica.setSenha(dto.senha());
        }

        this.clinicaRepository.save(clinica);
        return ResponseEntity.ok(clinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinica> update(@PathVariable Integer id, @RequestBody ClinicaRequestDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(428).build();
        }

        Clinica clinica = this.clinicaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clinica não encontrada."));

        clinica.setNome(dto.nome());
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
