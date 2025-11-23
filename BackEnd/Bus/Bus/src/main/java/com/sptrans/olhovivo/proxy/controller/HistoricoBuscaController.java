package com.sptrans.olhovivo.proxy.controller;

import com.sptrans.olhovivo.proxy.model.HistoricoBusca;
import com.sptrans.olhovivo.proxy.repository.HistoricoBuscaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoBuscaController {

    private final HistoricoBuscaRepository repository;

    public HistoricoBuscaController(HistoricoBuscaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<HistoricoBusca> listar() {
        return repository.findAll();
    }

    @PostMapping
    public HistoricoBusca salvar(@RequestBody HistoricoBusca historico) {
        return repository.save(historico);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
