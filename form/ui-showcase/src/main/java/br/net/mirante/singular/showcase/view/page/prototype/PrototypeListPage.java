package br.net.mirante.singular.showcase.view.page.prototype;

import org.wicketstuff.annotation.mount.MountPath;

import br.net.mirante.singular.showcase.view.template.Content;
import br.net.mirante.singular.showcase.view.template.Template;

@MountPath("prototype/list")
public class PrototypeListPage extends Template {

    @Override
    protected Content getContent(String id) {
        return new PrototypeListContent(id);
    }
}
