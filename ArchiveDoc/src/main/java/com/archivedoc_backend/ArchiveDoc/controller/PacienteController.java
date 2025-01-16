package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.PacienteRequestDTO;
import com.archivedoc_backend.ArchiveDoc.model.Paciente;
import com.archivedoc_backend.ArchiveDoc.model.Psicologo;
import com.archivedoc_backend.ArchiveDoc.repository.PacienteRepository;
import com.archivedoc_backend.ArchiveDoc.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @GetMapping
    public ResponseEntity<List<Paciente>> findAll() {
        List<Paciente> paciente = this.pacienteRepository.findAll();
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/{id}")
    public Paciente findById(@PathVariable Integer id) {
        return this.pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));
    }

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody PacienteRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Psicologo psicologo = this.psicologoRepository.findById(dto.psicologoId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));

        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setPsicologo(psicologo);
        paciente.setConvenio(dto.convenio());

        this.pacienteRepository.save(paciente);
        return ResponseEntity.ok(paciente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> partialUpdate(@PathVariable Integer id, @RequestBody PacienteRequestDTO dto) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));

        if(dto.nome() != null) {
            paciente.setNome(dto.nome());
        }

        if(dto.cpf() != null) {
            paciente.setCpf(dto.cpf());
        }

        if(dto.psicologoId() != null) {
            Psicologo psicologo = this.psicologoRepository.findById(dto.psicologoId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Psicólogo não encontrado."));
            paciente.setPsicologo(psicologo);
        }

        if(dto.convenio() != null) {
            paciente.setConvenio(dto.convenio());
        }

        this.pacienteRepository.save(paciente);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Integer id, @RequestBody PacienteRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));

        Psicologo psicologo = this.psicologoRepository.findById(dto.psicologoId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));

        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setPsicologo(psicologo);
        paciente.setConvenio(dto.convenio());

        this.pacienteRepository.save(paciente);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));

        this.pacienteRepository.delete(paciente);
        return ResponseEntity.noContent().build();
    }
}
