package com.opensingular.ws.service;

import com.opensingular.ws.Main;
import com.opensingular.ws.upload.AttachmentResponse;
import com.opensingular.ws.upload.UploadEndpointWs;
import com.opensingular.ws.upload.Uploadws;
import org.apache.commons.io.IOUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.File;
import java.net.URL;

public class UploadService {

    private static final Uploadws uploadws = new Uploadws();

    public AttachmentResponse uploadFile(String filename){
        try {
            UploadEndpointWs uploadWsPort = uploadws.getUploadWsPort();
            URL url = IOUtils.resourceToURL(filename, Main.class.getClassLoader());
            File file = new File(url.toURI());
            DataHandler dataHandler = new DataHandler(new FileDataSource(file));
            return uploadWsPort.fileUpload(filename, dataHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
