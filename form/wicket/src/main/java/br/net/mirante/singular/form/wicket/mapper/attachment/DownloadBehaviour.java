package br.net.mirante.singular.form.wicket.mapper.attachment;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Component;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import br.net.mirante.singular.form.mform.MInstancia;
import br.net.mirante.singular.form.mform.SDocument;
import br.net.mirante.singular.form.mform.core.attachment.IAttachmentPersistenceHandler;
import br.net.mirante.singular.form.mform.core.attachment.IAttachmentRef;
import br.net.mirante.singular.form.mform.core.attachment.MIAttachment;

//TODO: Test when we have a persistent and a temporary one
@SuppressWarnings("serial")
public class DownloadBehaviour extends Behavior implements IResourceListener {
    transient protected WebWrapper w = new WebWrapper();
    private Component component;
    transient private MInstancia instance;

    public DownloadBehaviour(MInstancia instance) {
        this.instance = instance;
    }

    public void setWebWrapper(WebWrapper w) {
        this.w = w;
    }

    @Override
    public void bind(Component component) {
        this.component = component;
    }

    @Override
    public void onResourceRequested() {
        try {
            handleRequest((MIAttachment) instance, instance.getDocument());
        } catch (IOException e) {
            throw new AbortWithHttpErrorCodeException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private void handleRequest(MIAttachment attachment, SDocument document) throws IOException {
        ServletWebRequest request = w.request();
        IRequestParameters parameters = request.getRequestParameters();
        StringValue id = parameters.getParameterValue("fileId");
        StringValue name = parameters.getParameterValue("fileName");
        if (id.isEmpty() || name.isEmpty()) {
            writeFileFromPersistent(attachment, document);
        } else {
            writeFileFromTemporary(document, id.toString(), name.toString());
        }
    }

    private void writeFileFromPersistent(MIAttachment attachment, SDocument document) throws IOException {
        IAttachmentPersistenceHandler handler = document.getAttachmentPersistenceHandler();
        IAttachmentRef data = handler.getAttachment(attachment.getFileId());
        writeFileToResponse(attachment.getFileName(), data, w.response());
    }

    private void writeFileFromTemporary(SDocument document, String fileId, String fileName) throws IOException {
        IAttachmentPersistenceHandler handler = document.getAttachmentPersistenceHandler();
        IAttachmentRef data = handler.getAttachment(fileId);
        writeFileToResponse(fileName, data, w.response());
    }

    private void writeFileToResponse(String fileName, IAttachmentRef data, WebResponse response) throws IOException {
        setHeader(fileName, response);
        response.getOutputStream().write(data.getContentAsByteArray());
    }

    private void setHeader(String fileName, WebResponse response) {
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Content-disposition", "attachment; filename=" + fileName);
    }

    public String getUrl() {
        return component.urlFor(this, IResourceListener.INTERFACE, new PageParameters()).toString();
    }
}