package org.opensingular.form.provider;

import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.util.transformer.SCompositeListBuilder;
import org.opensingular.form.util.transformer.Value;

import java.util.ArrayList;
import java.util.List;

public interface STextQueryProvider extends TextQueryProvider<Value.Content, SIComposite> {

    @Override
    default List<Value.Content> load(SIComposite ins, String query) {
        final SCompositeListBuilder builder = new SCompositeListBuilder((STypeComposite<SIComposite>) ins.getType(), ins);
        fill(builder, query);
        final List<Value.Content> listMap = new ArrayList<>();
        builder.getList().forEach(i -> listMap.add(Value.dehydrate(i)));
        return listMap;
    }

    void fill(SCompositeListBuilder builder, String query);

}