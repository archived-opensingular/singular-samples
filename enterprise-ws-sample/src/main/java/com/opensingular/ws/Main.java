package com.opensingular.ws;

import com.opensingular.ws.requirement.Requirementsample;
import com.opensingular.ws.requirement.Requirementsample.DadosPessoais.DocumentacaoComprobatoria;
import com.opensingular.ws.requirement.Requirementsample.DadosPessoais.Documentos;
import com.opensingular.ws.requirement.Requirementsample.DadosPessoais.FotoDoCachorro;
import com.opensingular.ws.requirement.Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompleto;
import com.opensingular.ws.requirement.RequirementsampleResponse;
import com.opensingular.ws.service.RequerimentService;
import com.opensingular.ws.service.UploadService;
import com.opensingular.ws.upload.AttachmentResponse;

import java.math.BigInteger;
import java.util.List;

public class Main {

    private static final UploadService uploadService = new UploadService();
    private static final RequerimentService requerimentService = new RequerimentService();

    public static void main(String[] args) {
        AttachmentResponse docResponse = uploadService.uploadFile("doc.png");
        AttachmentResponse dogResponse = uploadService.uploadFile("dog.jpg");
        AttachmentResponse infoResponse = uploadService.uploadFile("info.txt");

        Requirementsample sample = new Requirementsample();
        Requirementsample.DadosPessoais dadosPessoais = new Requirementsample.DadosPessoais();
        dadosPessoais.setNomeCompleto("Fulano de Teste");
        dadosPessoais.setNomeMae("Nome da Mãe");
        dadosPessoais.setNomePai("Nome do pai");
        dadosPessoais.setTelefone("61999998888");
        dadosPessoais.setBrasileiro(true);
        dadosPessoais.setListEnderecos(preencheDadosEndereco());
        dadosPessoais.setDocumentos(preencheDadosDocumentos(docResponse));
        dadosPessoais.setFotoDoCachorro(preencheDadosCachorro(dogResponse, dadosPessoais));
        dadosPessoais.setDocumentacaoComprobatoria(preencheDadosDocumentacaoComprobatoria(infoResponse));
        sample.setDadosPessoais(dadosPessoais);

        RequirementsampleResponse send = requerimentService.send(sample);
        System.out.println("ID " + send.getId());
        System.out.println("Code " + send.getCode());
        System.out.println("Message " + send.getMessage());
        send.getError().forEach(error -> {
            System.out.println("Error label " + error.getLabel());
            System.out.println("Error message " + error.getMessage());
            System.out.println("Error path " + error.getPath());
        });
    }

    private static Requirementsample.DadosPessoais.ListEnderecos preencheDadosEndereco() {
        Requirementsample.DadosPessoais.ListEnderecos listEnderecos = new Requirementsample.DadosPessoais.ListEnderecos();
        List<EnderecoCompleto> enderecos = listEnderecos.getEnderecoCompleto();
        EnderecoCompleto enderecoCompleto = new EnderecoCompleto();
        enderecoCompleto.setBairro("Asa Norte");
        enderecoCompleto.setCep("99999-888");
        enderecoCompleto.setCidade("Brasília");
        enderecoCompleto.setComplemento("Lote 9");
        EnderecoCompleto.Estado estado = new EnderecoCompleto.Estado();
        estado.setNome("Distrito Federal");
        estado.setSigla("DF");
        enderecoCompleto.setEstado(estado);
        enderecoCompleto.setLogradouro("SQN");
        enderecoCompleto.setNumero("5");
        enderecoCompleto.setPais("Brasil");
        enderecos.add(enderecoCompleto);
        return listEnderecos;

    }

    private static DocumentacaoComprobatoria preencheDadosDocumentacaoComprobatoria(AttachmentResponse infoResponse) {
        DocumentacaoComprobatoria documentacaoComprobatoria = new DocumentacaoComprobatoria();
        List<DocumentacaoComprobatoria.Documento> documentos = documentacaoComprobatoria.getDocumento();
        DocumentacaoComprobatoria.Documento doc = new DocumentacaoComprobatoria.Documento();

        doc.setFileId(infoResponse.getFileId());
        doc.setFileSize(BigInteger.valueOf(infoResponse.getFileSize()));
        doc.setHashSHA1(infoResponse.getHashSHA1());
        doc.setName(infoResponse.getName());

        documentos.add(doc);
        return documentacaoComprobatoria;
    }

    private static Documentos preencheDadosDocumentos(AttachmentResponse docResponse) {
        Documentos documentos = new Documentos();
        List<Documentos.Documento> documentoList = documentos.getDocumento();

        Documentos.Documento doc = new Documentos.Documento();
        doc.setFileId(docResponse.getFileId());
        doc.setFileSize(BigInteger.valueOf(docResponse.getFileSize()));
        doc.setHashSHA1(docResponse.getHashSHA1());
        doc.setName(docResponse.getName());

        documentoList.add(doc);
        return documentos;
    }

    private static FotoDoCachorro preencheDadosCachorro(AttachmentResponse dogResponse, Requirementsample.DadosPessoais dadosPessoais) {
        dadosPessoais.setNaoTenhoFotoCachorro(true);
        FotoDoCachorro fotoDoCachorro = new FotoDoCachorro();
        fotoDoCachorro.setFileId(dogResponse.getFileId());
        fotoDoCachorro.setFileSize(BigInteger.valueOf(dogResponse.getFileSize()));
        fotoDoCachorro.setHashSHA1(dogResponse.getHashSHA1());
        fotoDoCachorro.setName(dogResponse.getName());

        return fotoDoCachorro;
    }
}
