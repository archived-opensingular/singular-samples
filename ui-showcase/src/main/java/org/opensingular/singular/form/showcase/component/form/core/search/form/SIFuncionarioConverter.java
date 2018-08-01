package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.converter.SInstanceConverter;

public class SIFuncionarioConverter implements SInstanceConverter<Funcionario, SIFuncionario> {
    @Override
    public void fillInstance(SIFuncionario siFuncionario, Funcionario funcionario) {
        siFuncionario.nome().setValue(funcionario.getNome());
        siFuncionario.funcao().setValue(funcionario.getFuncao());
        siFuncionario.idade().setValue(funcionario.getIdade());
    }

    @Override
    public Funcionario toObject(SIFuncionario siFuncionario) {
        return new Funcionario(siFuncionario.nome().getValue(), siFuncionario.funcao().getValue(),
                siFuncionario.idade().getValue());
    }
}
