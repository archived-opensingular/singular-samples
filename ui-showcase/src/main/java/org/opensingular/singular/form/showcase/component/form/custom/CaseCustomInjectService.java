package org.opensingular.singular.form.showcase.component.form.custom;

import org.springframework.stereotype.Service;

@Service
public class CaseCustomInjectService {

    public String[] fruitsOptions(){
        String[] strings = {"Banana", "Maça", "Morango", "Cajá"};
        return strings;
    }
}
