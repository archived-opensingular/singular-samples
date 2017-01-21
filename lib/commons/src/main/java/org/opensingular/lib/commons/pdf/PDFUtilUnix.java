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

package org.opensingular.lib.commons.pdf;

import org.opensingular.lib.commons.util.TempFileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe utilitária para a manipulação de PDF's em sistemas Linux/Unix.
 * Essa versão sobre escreve as chamadas nativas da classe
 * {@link PDFUtil} com as equivalentes para o sistema Linux/Unix.
 */
public class PDFUtilUnix extends PDFUtil {

    /**
     * Instancia um novo objeto do tipo PDFUtilUnix.
     */
    private PDFUtilUnix() {}

    /**
     * Retorna o valor atual do atributo {@link #instance}.
     *
     * @return O valor atual do atributo.
     */
    public static PDFUtil getInstance() {
        if (instance == null) {
            instance = new PDFUtilUnix();
        }
        return instance;
    }

    /**
     * Dividi as páginas de um PDF ao meio, gerando duas páginas para cada.
     *
     * @param pdf o PDF.
     * @return O arquivo PDF gerado.
     * @throws IOException          Caso ocorra um problema de IO.
     * @throws InterruptedException Caso ocorra um problema de sincronismo.
     */
    @Override
    public File splitPDF(File pdf) throws IOException, InterruptedException {
        if (getWkhtml2pdfHomeOpt() == null) {
            return null;
        } else if (pdf == null || !pdf.exists()) {
            getLogger().error("splitPDF: PDF file not found");
            return null;
        }

        File tempFolder = pdf.getParentFile();
        File pdfFile    = new File(tempFolder, "splited.pdf");

        List<String> commandAndArgs = new ArrayList<>(0);
        commandAndArgs.add(getExecFile(null, "runner"));
        commandAndArgs.add(getExecFile(null, "pdfsplit"));
        commandAndArgs.add("-p2x1a4");
        commandAndArgs.add(pdf.getAbsolutePath());
        commandAndArgs.add(pdfFile.getAbsolutePath());

        getLogger().info(commandAndArgs.toString());

        ProcessBuilder pb      = new ProcessBuilder(commandAndArgs);
        Process        process = pb.start();
        boolean success = runProcess(process);
        if (success && pdfFile.exists()) {
            return pdfFile;
        }
        return null;
    }

    private boolean runProcess(Process process) throws InterruptedException, IOException {
        process.waitFor();

        boolean success = true;
        try (BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String  line    = outReader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    success = false;
                }
                line = outReader.readLine();
            }

            line = errReader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    success = false;
                    getLogger().error(line);
                }
                line = errReader.readLine();
            }
        }
        return success;
    }

    /**
     * Converte o código HTML em um arquivo PDF com o cabeçalho e rodapé especificados.
     *
     * @param unsafeHtml             o código HTML.
     * @param header           o código HTML do cabeçalho.
     * @param footer           o código HTML do rodapé.
     * @param additionalConfig configurações adicionais.
     * @return O arquivo PDF gerado.
     * @throws IOException          Caso ocorra um problema de IO.
     * @throws InterruptedException Caso ocorra um problema de sincronismo.
     */
    @Override
    public File convertHTML2PDF(String rawHtml, String rawHeader, String rawFooter, List<String> additionalConfig)
            throws IOException, InterruptedException {
        if (getWkhtml2pdfHomeOpt() == null) {
            return null;
        }

        final String html   = safeWrapHtml(rawHtml);
        final String header = safeWrapHtml(rawHeader);
        final String footer = safeWrapHtml(rawFooter);

        File tempLock   = File.createTempFile("SINGULAR-", UUID.randomUUID().toString());
        File tempFolder = new File(tempLock.getParentFile(), tempLock.getName().concat("-DIR"));
        if (!tempFolder.mkdir()) {
            getLogger().error("convertHTML2PDF: temp folder not found");
            return null;
        }

        File htmlFile = new File(tempFolder, "temp.html");
        File pdfFile  = new File(tempFolder, "temp.pdf");
        File jarFile  = new File(tempFolder, "temp.jar");

        TempFileUtils.deleteOrException(htmlFile, getClass());

        if (! htmlFile.createNewFile()) {
            return null;
        }
        writeToFile(htmlFile, html);

        List<String> commandAndArgs = new ArrayList<>(0);
        commandAndArgs.add(getExecFile("bin", "wkhtmltopdf"));

        if (additionalConfig != null) {
            commandAndArgs.addAll(additionalConfig);
        } else {
            addDefaultPDFCommandArgs(commandAndArgs);
        }

        if (header != null) {
            File headerFile = new File(tempFolder, "header.html");
            writeToFile(headerFile, header);
            commandAndArgs.add("--header-html");
            commandAndArgs.add("file://".concat(headerFile.getAbsolutePath()));
            addDefaultHeaderCommandArgs(commandAndArgs);
        }

        if (footer != null) {
            File footerFile = new File(tempFolder, "footer.html");
            writeToFile(footerFile, footer);
            commandAndArgs.add("--footer-html");
            commandAndArgs.add("file://".concat(footerFile.getAbsolutePath()));
            addDefaultFooterCommandArgs(commandAndArgs);
        }

        commandAndArgs.add("--cookie-jar");
        commandAndArgs.add(jarFile.getAbsolutePath());
        commandAndArgs.add("file://".concat(htmlFile.getAbsolutePath()));
        commandAndArgs.add(pdfFile.getAbsolutePath());

        ProcessBuilder pb = new ProcessBuilder(commandAndArgs);
        pb.environment().put("LD_LIBRARY_PATH", getWkhtml2pdfHomeOpt().getAbsolutePath());
        Process process = pb.start();
        process.waitFor();
        return generateFile(process, pdfFile);
    }

    private void writeToFile(File destination, String content) throws IOException {
        try(FileWriter fw = new FileWriter(destination)) {
             fw.write(content);
        }
    }

    /**
     * Converte o código HTML em um arquivo PNG.
     *
     * @param html             o código HTML.
     * @param additionalConfig configurações adicionais.
     * @return O arquivo PNG gerado.
     * @throws IOException          Caso ocorra um problema de IO.
     * @throws InterruptedException Caso ocorra um problema de sincronismo.
     */
    @Override
    public File convertHTML2PNG(String html, List<String> additionalConfig) throws IOException, InterruptedException {
        if (getWkhtml2pdfHomeOpt() == null) {
            return null;
        }

        File tempLock   = File.createTempFile("SINGULAR-", UUID.randomUUID().toString());
        File tempFolder = new File(tempLock.getParentFile(), tempLock.getName().concat("-DIR"));
        if (!tempFolder.mkdir()) {
            getLogger().error("convertHTML2PNG: temp folder not found");
            return null;
        }

        File htmlFile = new File(tempFolder, "temp.html");
        File pngFile  = new File(tempFolder, "temp.png");
        File jarFile  = new File(tempFolder, "temp.jar");

        TempFileUtils.deleteOrException(htmlFile, getClass());

        if (htmlFile.createNewFile()) {
            writeToFile(htmlFile, html);

            List<String> commandAndArgs = new ArrayList<>(0);
            commandAndArgs.add(getExecFile("bin", "wkhtmltoimage"));

            if (additionalConfig != null) {
                commandAndArgs.addAll(additionalConfig);
            } else {
                addDefaultPNGCommandArgs(commandAndArgs);
            }

            commandAndArgs.add("--cookie-jar");
            commandAndArgs.add(jarFile.getAbsolutePath());
            commandAndArgs.add("file://".concat(htmlFile.getAbsolutePath()));
            commandAndArgs.add(pngFile.getAbsolutePath());

            ProcessBuilder pb = new ProcessBuilder(commandAndArgs);
            pb.environment().put("LD_LIBRARY_PATH", getWkhtml2pdfHome().getAbsolutePath());
            Process process = pb.start();
            process.waitFor();
            return generateFile(process, pngFile);
        }

        return null;
    }

    /**
     * Gera o arquivo através do processo especificado.
     *
     * @param process o processo especificado.
     * @param file    o arquivo a ser criado.
     * @return o arquivo criado.
     * @throws IOException
     */
    private File generateFile(Process process, File file) throws IOException {

        boolean done    = false;
        boolean success = true;
        try(BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line = outReader.readLine();
            while (line != null) {
                getLogger().info(line);
                line = outReader.readLine();
            }
            line = errReader.readLine();
            while (line != null) {
                if (line.startsWith("Done")) {
                    done = true;
                } else if (line.startsWith("Warning:")) {
                    getLogger().warn(line);
                } else if (line.startsWith("Error:")) {
                    success = false;
                    getLogger().error(line);
                } else {
                    getLogger().info(line);
                }
                line = errReader.readLine();
            }
        }
        if (done && success && file.exists()) {
            return file;
        } else {
            getLogger().error(String.format("done:%b success:%b file.exists():%b", done, success, file.exists()));
        }

        return null;
    }
}