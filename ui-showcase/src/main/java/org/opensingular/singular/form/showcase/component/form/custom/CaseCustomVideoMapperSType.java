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

package org.opensingular.singular.form.showcase.component.form.custom;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.media.video.Video;
import org.apache.wicket.validation.validator.UrlValidator;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSControls;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSFormGroup;
import org.opensingular.lib.wicket.util.bootstrap.layout.IBSGridCol.BSGridSize;
import org.opensingular.lib.wicket.util.model.IMappingModel;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

/**
 * Custom String Mapper
 */
@CaseItem(componentName = "Custom Mapper", subCaseName = "Vídeo", group = Group.CUSTOM)
@SInfoType(spackage = CaseCustomPackage.class, name = "Video")
public class CaseCustomVideoMapperSType extends STypeComposite<SIComposite> {

    public STypeString video;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        video = this.addFieldString("video");
        video
            .addInstanceValidator(v -> {
                if (!new UrlValidator().isValid(v.getInstance().getValue())) {
                    v.error("URL inválida");
                }
            })
            //@destacar
            .withCustomMapper(new VideoMapper())
            .asAtr().label("Vídeo").required(true);
    }

    private static final class VideoMapper implements IWicketComponentMapper {
        @Override
        public void buildView(WicketBuildContext ctx) {
            final IMappingModel<String> labelModel = IMappingModel.of(ctx.getModel()).map(it -> it.asAtr().getLabel());
            switch (ctx.getViewMode()) {
                case EDIT:
                    ctx.getContainer()
                        .appendComponent(id -> new BSFormGroup(id, BSGridSize.MD)
                            .appendControls(12, controlsId -> new BSControls(controlsId)
                                .appendLabel(new Label("label", labelModel))
                                .appendInputText(ctx.configure(this, new TextField<>("url", ctx.getValueModel())))
                                .appendFeedback(ctx.createFeedbackCompactPanel("feedback"))
                                .appendHelpBlock($m.ofValue("<br> Exemplos: "
                                    + "<ul>"
                                    + " <li>http://techslides.com/demos/sample-videos/small.mp4</li>"
                                    + " <li>http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4</li>"
                                    + "</ul>"), false)));
                    break;
                case READ_ONLY:
                    Video video = new Video("video", ctx.getModel().getObject().getValueWithDefault(String.class));
                    video.setWidth(320);
                    ctx.getContainer().appendTag("video", video);
                    break;
            }
        }
    }
}
