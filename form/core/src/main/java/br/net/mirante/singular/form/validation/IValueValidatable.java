package br.net.mirante.singular.form.validation;

import br.net.mirante.singular.form.mform.MInstancia;

public interface IValueValidatable<T> {

    T getValue();

    MInstancia getInstance();

    boolean isValid();

    void setDefaultLevel(ValidationErrorLevel level);

    void error(IValidationError error);

    IValidationError error(String msg);

    void error(ValidationErrorLevel level, IValidationError error);

    IValidationError error(ValidationErrorLevel level, String msg);
}