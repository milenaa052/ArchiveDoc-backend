package com.archivedoc_backend.ArchiveDoc.controller;

import com.archivedoc_backend.ArchiveDoc.dto.GuiasRequestDTO;
import com.archivedoc_backend.ArchiveDoc.model.Guias;
import com.archivedoc_backend.ArchiveDoc.model.Paciente;
import com.archivedoc_backend.ArchiveDoc.repository.GuiasRepository;
import com.archivedoc_backend.ArchiveDoc.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guias")
public class GuiasController {
    @Autowired
    private GuiasRepository guiasRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<List<Guias>> findAll() {
        List<Guias> guias = this.guiasRepository.findAll();
        return ResponseEntity.ok(guias);
    }

    @GetMapping("/{id}")
    public Guias findById(@PathVariable Integer id) {
        return this.guiasRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));
    }

    @PostMapping
    public ResponseEntity<Guias> save(@RequestBody GuiasRequestDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(428).build();
        }

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));

        Guias guias = new Guias();
        guias.setNumeroGuia(dto.numeroGuia());
        guias.setDataPedido(dto.dataPedido());
        guias.setDataLiberacao(dto.dataLiberacao());
        guias.setTipoSessao(dto.tipoSessao());
        guias.setQuantidadeSessoes(dto.quantidadeSessoes());
        guias.setStatus(dto.status());
        guias.setDataPrimeiraSessao(dto.dataPrimeiraSessao());
        guias.setPaciente(paciente);

        this.guiasRepository.save(guias);
        return ResponseEntity.ok(guias);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Guias> partialUpdate(@PathVariable Integer id, @RequestBody GuiasRequestDTO dto) {
        Guias guias = this.guiasRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada"));

        if(dto.numeroGuia() != null) {
            guias.setNumeroGuia(dto.numeroGuia());
        }

        if(dto.dataPedido() != null) {
            guias.setDataPedido(dto.dataPedido());
        }

        if(dto.dataLiberacao() != null) {
            guias.setDataLiberacao(dto.dataLiberacao());
        }

        if(dto.tipoSessao() != null) {
            guias.setTipoSessao(dto.tipoSessao());
        }

        if(dto.quantidadeSessoes() != null) {
            guias.setQuantidadeSessoes(dto.quantidadeSessoes());
        }

        if(dto.status() != null) {
            guias.setStatus(dto.status());
        }

        if(dto.dataPrimeiraSessao() != null) {
            guias.setDataPrimeiraSessao(dto.dataPrimeiraSessao());
        }

        if(dto.pacienteId() != null) {
            Paciente paciente = this.pacienteRepository.findById(dto.pacienteId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Paciente não encontrado."));
            guias.setPaciente(paciente);
        }

        this.guiasRepository.save(guias);
        return ResponseEntity.ok(guias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guias> uptade(@PathVariable Integer id, @RequestBody GuiasRequestDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(428).build();
        }

        Guias guias = guiasRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Paciente não encontrado."));


        guias.setNumeroGuia(dto.numeroGuia());
        guias.setDataPedido(dto.dataPedido());
        guias.setDataLiberacao(dto.dataLiberacao());
        guias.setTipoSessao(dto.tipoSessao());
        guias.setQuantidadeSessoes(dto.quantidadeSessoes());
        guias.setStatus(dto.status());
        guias.setDataPrimeiraSessao(dto.dataPrimeiraSessao());
        guias.setPaciente(paciente);

        this.guiasRepository.save(guias);
        return ResponseEntity.ok(guias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Guias guias = guiasRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guia não encontrada."));

        this.guiasRepository.delete(guias);
        return ResponseEntity.noContent().build();
    }

}
