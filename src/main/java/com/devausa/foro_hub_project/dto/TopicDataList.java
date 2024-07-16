package com.devausa.foro_hub_project.dto;

import com.devausa.foro_hub_project.model.Course;
import com.devausa.foro_hub_project.model.Topic;

public record TopicDataList(Long id, String titulo, String mensaje, Course curso) {
    public TopicDataList(Topic topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}