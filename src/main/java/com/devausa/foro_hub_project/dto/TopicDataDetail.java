package com.devausa.foro_hub_project.dto;

import com.devausa.foro_hub_project.model.Course;
import com.devausa.foro_hub_project.model.Topic;

import java.time.LocalDateTime;

public record TopicDataDetail(String titulo, String mensaje, LocalDateTime fechaCreacion, Course curso) {
    public TopicDataDetail(Topic topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getCurso());
    }
}
