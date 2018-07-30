package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.media.video.Video;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSControls;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSFormGroup;
import org.opensingular.lib.wicket.util.bootstrap.layout.IBSGridCol;
import org.opensingular.lib.wicket.util.model.IMappingModel;

import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

public class VideoMapper implements IWicketComponentMapper {
    @Override
    public void buildView(WicketBuildContext ctx) {
        final IMappingModel<String> labelModel = IMappingModel.of(ctx.getModel()).map(it -> it.asAtr().getLabel());
        switch (ctx.getViewMode()) {
            case EDIT:
                ctx.getContainer()
                        .appendComponent(id -> new BSFormGroup(id, IBSGridCol.BSGridSize.MD)
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
