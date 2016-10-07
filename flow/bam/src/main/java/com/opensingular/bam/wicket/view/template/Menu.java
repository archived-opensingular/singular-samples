/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.wicket.view.template;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.opensingular.bam.dto.MenuItemDTO;
import com.opensingular.bam.service.FlowMetadataFacade;
import com.opensingular.bam.service.UIAdminFacade;
import com.opensingular.bam.wicket.UIAdminSession;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import org.opensingular.flow.core.dto.IMenuItemDTO;
import org.opensingular.flow.persistence.entity.Dashboard;
import org.opensingular.lib.wicket.util.util.WicketUtils;

public class Menu extends Panel {

    @Inject
    private UIAdminFacade uiAdminFacade;

    @Inject
    private FlowMetadataFacade flowMetadataFacade;

    public Menu(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        queue(new WebMarkupContainer("dashboard").add(
                WicketUtils.$b.attr("href", "dashboard")));
        queueCustomDashboards();
        queue(mountCategories());
    }

    private void queueCustomDashboards() {
        List<Dashboard> dashboards = getAuthorizedDashboards(uiAdminFacade.retrieveCustomDashboards());

        RepeatingView repeatingView = new RepeatingView("customDashboards");
        for (Dashboard dashboard : dashboards) {
            repeatingView.add(
                    new WebMarkupContainer(repeatingView.newChildId()).add(
                            new WebMarkupContainer("link")
                                    .add(new Label("customDashboardLabel", dashboard.getName()))
                                    .add(WicketUtils.$b.attr("href", "dashboard"
                                            .concat("?").concat(Content.CUSTOM_DASHBOARD_COD_PARAM)
                                            .concat("=").concat(dashboard.getCod().toString())))
                    )
                            .setMarkupId(String.format("_dashboardMenu_%d", dashboard.getCod()))
                            .setOutputMarkupId(true)
            );
        }

        queue(repeatingView);
    }

    private List<Dashboard> getAuthorizedDashboards(List<Dashboard> dashboards) {
        return dashboards.stream()
                .filter(dashboard -> !flowMetadataFacade.getAuthorizedPortlets(dashboard, getUserId()).isEmpty())
                .collect(Collectors.toList());
    }

    protected String getUserId() {
        return UIAdminSession.get().getUserId();
    }

    private MarkupContainer mountCategories() {
        return new Fragment("categoriesContainer", "categoriesFragment", this).add(createCategoriesMenu());
    }

    private RepeatingView createCategoriesMenu() {
        final List<MenuItemDTO> categories = uiAdminFacade.retrieveAllCategoriesWithAcces(UIAdminSession.get().getUserId());
        final RepeatingView categoriesMenu = new RepeatingView("categories");
        for (IMenuItemDTO item : categories) {
            final WebMarkupContainer categoryMenu = new WebMarkupContainer(categoriesMenu.newChildId());
            categoryMenu.setOutputMarkupId(true);
            categoryMenu.setMarkupId(String.format("_categoryMenu_%d", item.getId()));
            categoryMenu.add(new Label("categoryLabel", item.getName())).add(createDefinitionsMenu(item.getItens()));
            categoriesMenu.add(categoryMenu);
        }
        return categoriesMenu;
    }

    private RepeatingView createDefinitionsMenu(List<IMenuItemDTO> definitions) {
        final RepeatingView definitionsMenu = new RepeatingView("definitions");
        for (IMenuItemDTO item : definitions) {
            final WebMarkupContainer definitionMenu = new WebMarkupContainer(definitionsMenu.newChildId());
            definitionMenu.setOutputMarkupId(true);
            definitionMenu.setMarkupId(String.format("_definitionMenu_%d", item.getId()));
            definitionMenu.add(new WebMarkupContainer("link")
                    .add(new Label("counter", item.getCounter()).setVisible(false))
                    .add(new Label("definitionLabel", item.getName()))
                    .add(WicketUtils.$b.attr("href", (item.getCode() == null ? "#"
                            : "dashboard"
                            .concat("?").concat(Content.PROCESS_DEFINITION_COD_PARAM)
                            .concat("=").concat(item.getCode())))));
            definitionsMenu.add(definitionMenu);
        }
        return definitionsMenu;
    }
}
