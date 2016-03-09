package br.net.mirante.singular.form.mform;

import java.util.*;
import java.util.function.Consumer;

import static com.google.common.collect.Lists.newArrayList;

public class MTypeRecursiveSpliterator implements Spliterator<SType<?>> {
    private final Deque<SType<?>> deque = new ArrayDeque<>();
    public MTypeRecursiveSpliterator(SType<?> root, boolean includeRoot) {
        if (includeRoot)
            this.deque.add(root);

        Collection possibleTypes = MTypes.containedTypes(root);
        Collection<SType> newTypes = newArrayList();
        do {
            addTypesNotYetPresent(possibleTypes, newTypes);
            Collection<SType> childTypes = createNewPossibleTypesToInspect(newTypes);
            newTypes = newArrayList();
            possibleTypes = childTypes;
        }while(!possibleTypes.isEmpty());
    }

    private Collection<SType> createNewPossibleTypesToInspect(Collection<SType> newTypes) {
        Collection<SType> childTypes = newArrayList();
        for(SType t: newTypes){
            childTypes.addAll(MTypes.containedTypes(t));
        }
        return childTypes;
    }

    private void addTypesNotYetPresent(Collection<SType> possibleTypes, Collection<SType> newTypes) {
        for(SType  t: possibleTypes){
            if(!this.deque.contains(t)){
                this.deque.add(t);
                newTypes.add(t);
            }
        }
    }

    @Override
    public boolean tryAdvance(Consumer<? super SType<?>> action) {
        if (deque.isEmpty())
            return false;

        final SType<?> node = deque.removeFirst();
        action.accept(node);
        return true;
    }
    @Override
    public Spliterator<SType<?>> trySplit() {
        return new MTypeRecursiveSpliterator(deque.removeFirst(), true);
    }
    @Override
    public long estimateSize() {
        return getExactSizeIfKnown();
    }
    @Override
    public int characteristics() {
        return Spliterator.NONNULL | Spliterator.DISTINCT;
    }
}