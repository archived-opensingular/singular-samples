package org.opensingular.singular.form;

import br.net.opensingular.singular.form.PathReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.PathReader;
import org.opensingular.form.SDictionary;
import org.opensingular.form.SFormUtil;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;

@RunWith(Parameterized.class)
public class TestMFormUtil extends TestCaseForm {

    public TestMFormUtil(TestFormConfig testFormConfig) {
        super(testFormConfig);
    }

    @Test
    public void testGenerateUserFriendlyName() {
        assertEquals("Dados pessoais", SFormUtil.generateUserFriendlyName("dadosPessoais"));
        assertEquals("Informacoes de contato", SFormUtil.generateUserFriendlyName("informacoesDeContato"));
        assertEquals("Nome", SFormUtil.generateUserFriendlyName("nome"));
        assertEquals("Endereco comercial", SFormUtil.generateUserFriendlyName("endereco-comercial"));
        assertEquals("URL origem", SFormUtil.generateUserFriendlyName("URLOrigem"));
        assertEquals("ABCDEF", SFormUtil.generateUserFriendlyName("ABCDEF"));
    }

    @Test
    public void testValidacaoNomeSimples() {
        testarNomeInvalido(" sss ");
        testarNomeInvalido("sss ");
        testarNomeInvalido(" ss");
        testarNomeInvalido("1ss");
        testarNomeInvalido("*ss");
        testarNomeInvalido("@ss");
        testarNomeInvalido("ss.xx");
        testarNomeInvalido("sã1");
        SFormUtil.validateSimpleName("long");
        SFormUtil.validateSimpleName("int");
        SFormUtil.validateSimpleName("ss");
        SFormUtil.validateSimpleName("s1");
    }

    private static void testarNomeInvalido(String nome) {
        try {
            SFormUtil.validateSimpleName(nome);
            Assert.fail("O nome deveria ser invalido");
        } catch (RuntimeException e) {
            if (!e.getMessage().contains("válido") || !(e.getMessage().charAt(0) == '\'')) {
                throw e;

            }
        }
    }

    @Test
    public void testResolverTipoCampo() {
        SDictionary    dicionario = createTestDictionary();
        PackageBuilder pb = dicionario.createNewPackage("teste");

        STypeComposite<SIComposite>      tipoBloco = pb.createCompositeType("bloco");
        STypeInteger integer1 = tipoBloco.addFieldInteger("integer1");
        STypeString string1 = tipoBloco.addFieldString("string1");
        STypeComposite<?> tipoSubBloco = tipoBloco.addFieldComposite("subBloco");
        STypeInteger integer2 = tipoSubBloco.addFieldInteger("integer2");
        STypeString tipoString2 = pb.createType("string2", STypeString.class);
        STypeList<STypeString, SIString> tipoListaString2 = tipoBloco.addFieldListOf("enderecos", tipoString2);
        STypeString tipoString3 = pb.createType("string3", STypeString.class);
        STypeList<STypeString, SIString> tipoListaString3 = tipoBloco.addFieldListOf("nomes", tipoString3);
        STypeList<STypeComposite<SIComposite>, SIComposite> listaSubBloco2 = tipoBloco.addFieldListOfComposite("listaSubBloco2", "coisa");
        STypeInteger tipoQtd = listaSubBloco2.getElementsType().addFieldInteger("qtd");

        assertTipoResultante(tipoBloco, "integer1", integer1);
        assertTipoResultante(tipoBloco, "integer1", dicionario.getType("teste.bloco.integer1"));
        assertTipoResultante(tipoBloco, "integer1", dicionario.getType(STypeInteger.class));
        assertTipoResultante(tipoBloco, "integer1", string1, false);
        assertTipoResultante(tipoBloco, "integer1", dicionario.getType("teste.bloco.string1"), false);
        assertTipoResultante(tipoBloco, "integer1", integer2, false);
        assertTipoResultante(tipoBloco, "integer1", tipoQtd, false);
        assertTipoResultante(tipoBloco, "string1", string1);

        assertTipoResultanteException(tipoBloco, "integerX", "Não existe o campo 'integerX'");
        assertTipoResultanteException(tipoBloco, "integer1.a", "Não se aplica um path a um tipo simples");
        assertTipoResultanteException(tipoBloco, "integer1[0]", "Não se aplica um path a um tipo simples");
        assertTipoResultanteException(integer1, "a", "Não se aplica um path a um tipo simples");
        assertTipoResultanteException(integer1, "[0]", "Não se aplica um path a um tipo simples");

        assertTipoResultante(tipoBloco, "subBloco", tipoSubBloco);
        assertTipoResultante(tipoBloco, "subBloco", dicionario.getType(STypeComposite.class));
        assertTipoResultante(tipoBloco, "subBloco.integer2", integer2);

        assertTipoResultanteException(tipoBloco, "integerX", "Não existe o campo 'integerX'");
        assertTipoResultanteException(tipoBloco, "[0]", "Índice de lista não se aplica a um tipo composto");

        assertTipoResultante(tipoBloco, "enderecos", tipoListaString2);
        assertTipoResultante(tipoBloco, "enderecos", dicionario.getType(STypeList.class));
        assertTipoResultante(tipoBloco, "enderecos[1]", tipoString2);
        assertTipoResultante(tipoBloco, "enderecos[4]", dicionario.getType(STypeString.class));
        assertTipoResultante(tipoBloco, "nomes", tipoListaString3);
        assertTipoResultante(tipoBloco, "nomes", dicionario.getType(STypeList.class));
        assertTipoResultante(tipoBloco, "nomes[20]", dicionario.getType(STypeString.class));
        assertTipoResultante(tipoBloco, "nomes[20]", dicionario.getType(STypeInteger.class), false);
        assertTipoResultante(tipoBloco, "nomes[60]", tipoListaString3.getElementsType());

        assertTipoResultante(tipoBloco, "listaSubBloco2", listaSubBloco2);
        assertTipoResultante(tipoBloco, "listaSubBloco2", dicionario.getType(STypeList.class));
        assertTipoResultante(tipoBloco, "listaSubBloco2[1]", listaSubBloco2.getElementsType());
        assertTipoResultante(tipoBloco, "listaSubBloco2[1].qtd", tipoQtd);
        assertTipoResultante(tipoBloco, "listaSubBloco2[1].qtd", dicionario.getType(STypeInteger.class));

        assertTipoResultante(listaSubBloco2, "[1].qtd", tipoQtd);
        assertTipoResultante(tipoListaString2, "[1]", dicionario.getType(STypeString.class));

        assertTipoResultanteException(tipoBloco, "listaSubBloco2.a", "Não se aplica a um tipo lista");
        assertTipoResultanteException(listaSubBloco2, "a", "Não se aplica a um tipo lista");
        assertTipoResultanteException(listaSubBloco2, "[1][1]", "Índice de lista não se aplica a um tipo composto");
        assertTipoResultanteException(tipoListaString2, "a", "Não se aplica a um tipo lista");
        assertTipoResultanteException(tipoListaString2, "[1][1]", "Não se aplica um path a um tipo simples");
    }

    private static void assertTipoResultanteException(SType<?> pontoOrigem, String path, String msgExceptionEsperada) {
        assertException(() -> SFormUtil.resolveFieldType(pontoOrigem, new PathReader(path)), msgExceptionEsperada);

    }

    private static void assertTipoResultante(SType<?> pontoOrigem, String path, SType<?> tipoEsperado) {
        assertTipoResultante(pontoOrigem, path, tipoEsperado, true);
    }
    private static void assertTipoResultante(SType<?> pontoOrigem, String path, SType<?> tipoEsperado, boolean temQueSerCompativel) {
        SType<?> tipoResultado = SFormUtil.resolveFieldType(pontoOrigem, new PathReader(path));
        if (tipoResultado.isTypeOf(tipoEsperado)) {
            if (!temQueSerCompativel) {
                fail("No path '" + path + "' foi encontrado o resultado '" + tipoResultado.getName() + "', o que não deveria ser o caso");
            }
        } else if (temQueSerCompativel) {
            fail("No path '" + path + "' foi encontrado o resultado '" + tipoResultado.getName() + "', mas era esperado '"
                + tipoEsperado.getName() + "'");
        }
    }
}