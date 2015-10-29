package br.net.mirante.singular.form.mform;

public class TestLeitorPath extends TestCaseForm {

    public void testGeral() {
        assertPath("a", "a");
        assertPath("a.b", "a", "b");
        assertPath("a.b.c", "a", "b", "c");
        assertPathException(".a.b", "inválido na posição 0");
        assertPathException("a..b", "inválido na posição 2");
        assertPathException("a.b..", "inválido na posição 4");

        assertPathException("a%%", "0 : Não é um nome de campo válido");
        assertPathException(" a", "0 : Não é um nome de campo válido");

        assertPath("[0]", 0);
        assertPath("[0100]", 100);
        assertPath("[0][1]", 0, 1);
        assertPath("a[0].b.c[1]", "a", 0, "b", "c", 1);

        assertPathException("[]", "inválido na posição 0");
        assertPathException("[a]", "inválido na posição 1");
        assertPathException("[ 0]", "inválido na posição 1");
        assertPathException("[1 0]", "inválido na posição 2");
        assertPathException("[1]b", "inválido na posição 3");
        assertPathException("a.[0]", "inválido na posição 2");
        assertPathException("a.[0", "inválido na posição 2");
    }

    private static void assertPathException(String path, String trechoMsgEsperada) {
        assertException(() -> {
            LeitorPath leitor = new LeitorPath(path);
            while (!leitor.isEmpty()) {
                leitor = leitor.proximo();
            }
        } , trechoMsgEsperada);

    }

    private static void assertPath(String path, Object... resultadoEsperado) {
        LeitorPath leitor = new LeitorPath(path);

        for (int i = 0; i < resultadoEsperado.length; i++) {
            Object esperado = resultadoEsperado[i];
            if (leitor.isEmpty()) {
                fail("O leitor terminou antes do esperado. Faltou o resultado de indice [" + i + "]=" + esperado);
            }
            if (esperado instanceof Integer) {
                assertTrue(leitor.isIndice());
                assertEquals(esperado, leitor.getIndice());
            } else {
                assertFalse(leitor.isIndice());
                assertEquals(esperado, leitor.getTrecho());
            }
            assertEquals((i + 1 == resultadoEsperado.length), leitor.isUltimo());
            leitor = leitor.proximo();
        }
        if (!leitor.isEmpty()) {
            fail("Ainda há item no leitor para ler: " + leitor.getTrecho());
        }
        final LeitorPath leitor2 = leitor;
        assertException(() -> leitor2.isIndice(), "Leitura já está no fim");
        assertException(() -> leitor2.getTrecho(), "Leitura já está no fim");
        assertException(() -> leitor2.proximo(), "Leitura já está no fim");

    }
}
