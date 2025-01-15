package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.SessoesRequestDTO;
import com.archivedoc_backend.ArchiveDoc.model.Guias;
import com.archivedoc_backend.ArchiveDoc.model.Sessoes;
import com.archivedoc_backend.ArchiveDoc.repository.GuiasRepository;
import com.archivedoc_backend.ArchiveDoc.repository.SessoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessoesController {
    @Autowired
    private SessoesRepository sessoesRepository;

    @Autowired
    private GuiasRepository guiasRepository;

    @GetMapping
    public ResponseEntity<List<Sessoes>> findAll() {
        List<Sessoes> sessoes = this.sessoesRepository.findAll();
        return ResponseEntity.ok(sessoes);
    }

    @GetMapping("/{id}")
    public Sessoes findById(@PathVariable Integer id) {
        return this.sessoesRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Sessão não encontrada."));
    }

    @PostMapping
    public ResponseEntity<Sessoes> save(@RequestBody SessoesRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Guias guia = this.guiasRepository.findById(dto.guiaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));

       Sessoes sessoes = new Sessoes();
        sessoes.setGuiaId(guia);
        sessoes.setDataSessao(dto.dataSessao());

        this.sessoesRepository.save(sessoes);
        return ResponseEntity.ok(sessoes);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sessoes> partialUpdate(@PathVariable Integer id, @RequestBody SessoesRequestDTO dto) {
        Sessoes sessoes = this.sessoesRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Sessão não encontrada."));

        Guias guia = this.guiasRepository.findById(dto.guiaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));

        if(dto != null) {
            sessoes.setGuiaId(guia);
            sessoes.setDataSessao(dto.dataSessao());
        }

        this.sessoesRepository.save(sessoes);
        return ResponseEntity.ok(sessoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessoes> update(@PathVariable Integer id, @RequestBody SessoesRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Sessoes sessoes = this.sessoesRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Sessão não encontrada."));

        Guias guia = this.guiasRepository.findById(dto.guiaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));

        sessoes.setGuiaId(guia);
        sessoes.setDataSessao(dto.dataSessao());

        this.sessoesRepository.save(sessoes);
        return ResponseEntity.ok(sessoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Sessoes sessoes = this.sessoesRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Sessão não encontrada."));

        this.sessoesRepository.delete(sessoes);
        return ResponseEntity.noContent().build();
    }
}
