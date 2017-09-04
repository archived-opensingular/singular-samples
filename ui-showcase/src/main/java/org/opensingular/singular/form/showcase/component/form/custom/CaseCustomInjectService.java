package org.opensingular.singular.form.showcase.component.form.custom;

import org.springframework.stereotype.Service;

//@destacar
@Service
public class CaseCustomInjectService {

    private final static String[] OPTIONS = new String[]{"Banana", "Maça", "Morango", "Cajá"};

    public String[] fruitsOptions(){
        return OPTIONS;
    }
}
