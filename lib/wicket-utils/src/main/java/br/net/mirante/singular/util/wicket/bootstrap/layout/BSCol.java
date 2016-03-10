package br.net.mirante.singular.util.wicket.bootstrap.layout;

import org.apache.wicket.behavior.Behavior;

public class BSCol extends BSContainer<BSCol> implements IBSGridCol<BSCol> {

    public BSCol(String id) {
        super(id);
        add(newBSGridColBehavior());
    }

    @Override
    public BSCol add(Behavior... behaviors) {
        return (BSCol) super.add(behaviors);
    }
}
