package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.PsicologoRequestDTO;
import com.archivedoc_backend.ArchiveDoc.model.Paciente;
import com.archivedoc_backend.ArchiveDoc.model.Psicologo;
import com.archivedoc_backend.ArchiveDoc.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/psicologo")
public class PsicologoController {
    @Autowired
    private PsicologoRepository psicologoRepository;

    @GetMapping
    public ResponseEntity<List<Psicologo>> findAll() {
        List<Psicologo> psicologo = this.psicologoRepository.findAll();
        return ResponseEntity.ok(psicologo);
    }

    @GetMapping("/{id}")
    public Psicologo findById(@PathVariable Integer id) {
        return this.psicologoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));
    }

    @PostMapping
    public ResponseEntity<Psicologo> save(@RequestBody PsicologoRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Psicologo psicologo = new Psicologo();
        psicologo.setNome(dto.nome());
        psicologo.setCrp(dto.crp());

        this.psicologoRepository.save(psicologo);
        return ResponseEntity.ok(psicologo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Psicologo> partialUpdate(@PathVariable Integer id, @RequestBody PsicologoRequestDTO dto) {
        Psicologo psicologo = this.psicologoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));

        if(dto.nome() != null) {
            psicologo.setNome(dto.nome());
        }

        if(dto.crp() != null) {
            psicologo.setCrp(dto.crp());
        }

        this.psicologoRepository.save(psicologo);
        return ResponseEntity.ok(psicologo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psicologo> update(@PathVariable Integer id, @RequestBody PsicologoRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Psicologo psicologo = this.psicologoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));

        psicologo.setNome(dto.nome());
        psicologo.setCrp(dto.crp());

        this.psicologoRepository.save(psicologo);
        return  ResponseEntity.ok(psicologo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Psicologo psicologo = this.psicologoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Psicólogo não encontrado."));

        this.psicologoRepository.delete(psicologo);
        return ResponseEntity.noContent().build();
    }
}
