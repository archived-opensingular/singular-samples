package br.net.mirante.singular.view.page.showcase;

import br.net.mirante.singular.util.wicket.util.WicketUtils;
import br.net.mirante.singular.view.page.form.crud.CrudPage;
import br.net.mirante.singular.view.template.Content;
import br.net.mirante.singular.view.template.Template;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import static br.net.mirante.singular.util.wicket.util.WicketUtils.$m;

@MountPath("showcase/component")
@SuppressWarnings("serial")
public class ComponentPage extends Template {

    private String componentName;

    public ComponentPage(PageParameters parameters) {
        this.componentName = parameters.get("cn").toString();
        if (componentName == null) {
            throw new RestartResponseAtInterceptPageException(getApplication().getHomePage());
        }
    }

    @Override
    protected Content getContent(String id) {
        return new ComponentContent(id, $m.ofValue(componentName));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("$('#_menuItemShowCase').addClass('active');"));
    }
}