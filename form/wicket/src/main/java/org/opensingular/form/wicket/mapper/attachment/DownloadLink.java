/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.form.wicket.mapper.attachment;

import static org.opensingular.lib.wicket.util.util.Shortcuts.$b;
import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import org.apache.wicket.model.Model;
import org.opensingular.form.type.core.attachment.SIAttachment;
import org.opensingular.lib.wicket.util.model.IReadOnlyModel;
import org.opensingular.lib.wicket.util.util.WicketUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de link para utilização em conjunto dom {@link DownloadSupportedBehavior}
 * para disponibilizar links de download de um único uso.
 */
public class DownloadLink extends Link<Void> {


    private static final String       FILE_REGEX_PATTERN   = ".*\\.(.*)";
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("pdf", "jpg", "gif", "png");

    private IModel<SIAttachment>      model;
    private IModel<Boolean>           openInNewTabIfIsBrowserFriendly;
    private DownloadSupportedBehavior downloadSupportedBehaviour;

    public DownloadLink(String id, IModel<SIAttachment> model, DownloadSupportedBehavior downloadSupportedBehaviour) {
        this(id, model, downloadSupportedBehaviour, Model.of(Boolean.TRUE));
    }

    public DownloadLink(String id, IModel<SIAttachment> model, DownloadSupportedBehavior downloadSupportedBehaviour
            , IModel<Boolean> openInNewTabIfIsBrowserFriendly) {
        super(id);
        this.model = model;
        this.downloadSupportedBehaviour = downloadSupportedBehaviour;
        this.openInNewTabIfIsBrowserFriendly = openInNewTabIfIsBrowserFriendly;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    public void configureBody() {
        this.setBody($m.property(model, "fileName"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        if ($m.property(model, "fileName") != null) {
            tag.getAttributes().put("title", $m.property(model, "fileName"));
        }
    }

    private static String jsStringOrNull(String s) {
        return (s == null) ? "null" : "'" + JavaScriptUtils.escapeQuotes(s) + "'";
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        this.add($b.attr("onclick",
                (IReadOnlyModel<String>) () -> "DownloadSupportedBehavior.ajaxDownload(" +
                        jsStringOrNull(downloadSupportedBehaviour.getUrl()) + "," +
                        jsStringOrNull(model.getObject().getFileId()) + "," +
                        jsStringOrNull(model.getObject().getFileName()) + "," +
                        openInNewTabIfIsBrowserFriendly.getObject() +
                        ");" +
                        "return false;"));
        configureBody();
        add(WicketUtils.$b.attr("title", $m.ofValue(model.getObject().getFileName())));
        if (openInNewTabIfIsBrowserFriendly.getObject()
                && isContentTypeBrowserFriendly(model.getObject().getFileName())) {
            add($b.attr("target", "_blank"));
        }
        setEnabled(isFileAssigned());
    }

    protected boolean isFileAssigned() {
        return (model.getObject() != null) && (model.getObject().getFileId() != null);
    }

    @Override
    public void onClick() {
    }

    private boolean isContentTypeBrowserFriendly(String filename) {
        return filename != null && isContentTypeBrowserFriendly(Pattern.compile(FILE_REGEX_PATTERN).matcher(filename));
    }

    private boolean isContentTypeBrowserFriendly(Matcher matcher) {
        return matcher.matches() && SUPPORTED_EXTENSIONS.contains(matcher.group(1));
    }

}
