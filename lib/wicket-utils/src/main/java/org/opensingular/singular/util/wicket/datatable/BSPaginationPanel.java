/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.util.wicket.datatable;

import static org.opensingular.singular.util.wicket.util.WicketUtils.$b;
import static org.opensingular.singular.util.wicket.util.WicketUtils.$m;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.IPageableItems;
import org.apache.wicket.markup.html.panel.Panel;

public class BSPaginationPanel extends Panel {

    private final IPageableItems pageable;

    public <P extends Component & IPageableItems> BSPaginationPanel(String id, P pageable) {
        super(id);
        this.pageable = pageable;

        add(new OffsetLink("previous", -1));

        add(new FirstLink("first"));
        add(new WebMarkupContainer("firstEllipse")
            .add($b.visibleIf($m.get(() -> (getCurrentPage() - getMiddlePagesRadius()) > 1))));

        add(new ListView<Long>("pages", $m.get(() -> getMiddlePagesRange())) {
            @Override
            protected void populateItem(ListItem<Long> item) {
                item
                    .add(new PageNavLink("page") {
                        @Override
                        protected long getTargetPage() {
                            return item.getModelObject();
                        }
                    }.setBody($m.get(() -> 1 + item.getModelObject())))
                    .add($b.classAppender("active", $m.get(() -> item.getModelObject() == getCurrentPage())));
            }
        });

        add(new WebMarkupContainer("lastEllipse")
            .add($b.visibleIf($m.get(() -> (getCurrentPage() + getMiddlePagesRadius()) < getLastPage() - 1))));
        add(new LastLink("last"));

        add(new OffsetLink("next", +1));
    }

    @SuppressWarnings("unchecked")
    public <P extends Component & IPageableItems> P getPageable() {
        return (P) pageable;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "nav");
    }

    private int getMiddlePagesRadius() {
        return 2;
    }
    private List<Long> getMiddlePagesRange() {
        long rangeLength = Math.min(1 + (getMiddlePagesRadius() * 2), getPageCount());
        List<Long> list = new ArrayList<>();
        long start = Math.min(Math.max(0, getCurrentPage() - getMiddlePagesRadius()), getPageCount() - rangeLength);
        for (long i = 0; i < rangeLength; i++)
            list.add(start++);
        return list;
    }

    private long getLastPage() {
        return getPageCount() - 1;
    }
    private long getPageCount() {
        return getPageable().getPageCount();
    }
    private long getCurrentPage() {
        return getPageable().getCurrentPage();
    }

    private final class LastLink extends PageNavLink {
        private LastLink(String id) {
            super(id);
            setBody($m.get(() -> getLastPage() + 1));
        }
        @Override
        protected long getTargetPage() {
            return getLastPage();
        }
        @Override
        protected boolean isLinkVisible() {
            return !getMiddlePagesRange().contains(getTargetPage());
        }
    }

    private final class FirstLink extends PageNavLink {
        private FirstLink(String id) {
            super(id);
        }
        @Override
        protected long getTargetPage() {
            return 0;
        }
        @Override
        protected boolean isLinkVisible() {
            return !getMiddlePagesRange().contains(getTargetPage());
        }
    }

    private final class OffsetLink extends PageNavLink {
        private final int pageDelta;
        private OffsetLink(String id, int pageDelta) {
            super(id);
            this.pageDelta = pageDelta;
        }
        @Override
        protected long getTargetPage() {
            return getPageable().getCurrentPage() + pageDelta;
        }
        @Override
        public boolean isEnabledInHierarchy() {
            return super.isEnabledInHierarchy() && (getTargetPage() >= 0) && (getTargetPage() <= getLastPage());
        }
    }

    private abstract class PageNavLink extends AjaxLink<Void> {
        private PageNavLink(String id) {
            super(id);
        }
        protected abstract long getTargetPage();
        protected boolean isLinkVisible() {
            return true;
        }
        @Override
        public void onClick(AjaxRequestTarget target) {
            if (isEnabledInHierarchy()) {
                getPageable().setCurrentPage(getTargetPage());
                target.add(getPageable());
            }
        }
        @Override
        protected void onConfigure() {
            super.onConfigure();
            setVisible(isLinkVisible());
            setEnabled(getCurrentPage() != getTargetPage());
        }
    }

}