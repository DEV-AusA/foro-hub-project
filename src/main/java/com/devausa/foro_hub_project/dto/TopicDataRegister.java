package com.devausa.foro_hub_project.dto;

import com.devausa.foro_hub_project.model.Course;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDataRegister(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @Valid
        Course curso
) {
}