package org.opensingular.singular.form.showcase.studio.persistence.repository;

import org.opensingular.form.SFormUtil;
import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.singular.form.showcase.studio.persistence.form.SPackageStudioPersistenceForm;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeEndereco;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;

@Named
public class EnderecoFormRepository extends SpringFormPersistenceInMemory<STypeComposite<SIComposite>, SIComposite> {
    @Inject
    public EnderecoFormRepository(@Named("showcaseDocumentFactory") SDocumentFactory documentFactory) {
        super(documentFactory);
        setTypeFullName(SFormUtil.getTypeName(STypeEndereco.class));
        setPackageClasses(Collections.singletonList(SPackageStudioPersistenceForm.class));
    }
}