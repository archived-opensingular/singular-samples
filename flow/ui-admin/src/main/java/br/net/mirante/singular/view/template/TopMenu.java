package br.net.mirante.singular.view.template;

import static br.net.mirante.singular.util.wicket.util.WicketUtils.$b;
import static br.net.mirante.singular.util.wicket.util.WicketUtils.$m;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.UrlUtils;

import br.net.mirante.singular.service.UIAdminFacade;
import br.net.mirante.singular.wicket.UIAdminSession;

public class TopMenu extends Panel {

    @Inject
    private UIAdminFacade uiAdminFacade;
    
    private boolean withSideBar;

    public TopMenu(String id, boolean withSideBar) {
        super(id);
        this.withSideBar = withSideBar;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        queue(new WebMarkupContainer("sideBarToggle").setVisible(withSideBar));
        queue(new Label("nome", $m.ofValue(UIAdminSession.get().getUser().getSimpleName())));

        WebMarkupContainer avatar = new WebMarkupContainer("codrh");
        avatar.add($b.attr("src", Optional.ofNullable(StringUtils.trimToNull(uiAdminFacade.getUserAvatar()))
            .orElse(UrlUtils.rewriteToContextRelative("resources/admin/layout/img/avatar.png", getRequestCycle())).replace("{0}", UIAdminSession.get().getUserId())));
        queue(avatar);
        
        WebMarkupContainer logout = new WebMarkupContainer("logout");
        Optional<String> logoutHref = Optional.ofNullable(StringUtils.trimToNull(uiAdminFacade.getLogoutUrl()));
        logoutHref.ifPresent(href -> logout.add($b.attr("href", href)));
        queue(logout);
    }
}
