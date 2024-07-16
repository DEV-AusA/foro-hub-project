package com.devausa.foro_hub_project.controller;

import com.devausa.foro_hub_project.dto.TopicDataDetail;
import com.devausa.foro_hub_project.dto.TopicDataList;
import com.devausa.foro_hub_project.dto.TopicDataRegister;
import com.devausa.foro_hub_project.dto.TopicDataUpdate;
import com.devausa.foro_hub_project.model.Topic;
import com.devausa.foro_hub_project.repository.TopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "Bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(
            @RequestBody
            @Valid
            TopicDataRegister datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var topico = new Topic(datosRegistroTopico);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDataDetail(topico));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDataList>> datosListaTopicos(
            @PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {

        var page = topicoRepository.findAll(pageable).map(TopicDataList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid TopicDataUpdate datosActualizarTopico) {
        var topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new TopicDataDetail(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topic topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDataDetail(topico));
    }
}
