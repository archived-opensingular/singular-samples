package org.opensingular.singular.form.wicket.mapper.attachment.list;

import org.opensingular.form.SIList;
import org.opensingular.form.type.core.attachment.SIAttachment;
import org.opensingular.singular.form.wicket.WicketBuildContext;
import org.opensingular.singular.form.wicket.mapper.AbstractListaMapper;
import org.apache.wicket.model.IModel;

public class AttachmentListMapper extends AbstractListaMapper {

    public final static String MULTIPLE_HIDDEN_UPLOAD_FIELD_ID = "uploadField";
    private final static String CLICK_DELEGATE_SCRIPT_TEMPLATE = "$('#%s').on('click', function(){$('#%s').click();});";

    @Override
    public void buildView(WicketBuildContext ctx) {
        ctx.getContainer().appendTag("div",
                new FileListUploadPanel("up-list",
                        (IModel<SIList<SIAttachment>>) ctx.getModel(), ctx));

    }

}