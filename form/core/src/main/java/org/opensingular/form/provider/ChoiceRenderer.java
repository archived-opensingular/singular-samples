package org.opensingular.form.provider;

import java.io.Serializable;

public interface ChoiceRenderer<E extends Serializable> extends Serializable {

    String getIdValue(E option);

    String getDisplayValue(E option);

}