package com.devausa.foro_hub_project.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s) {
        super(s);
    }
}
