package br.net.mirante.singular.test;

import br.net.mirante.singular.definicao.InstanciaPeticao;
import br.net.mirante.singular.definicao.Peticao;
import br.net.mirante.singular.flow.core.ExecuteWaitingTasksJob;
import br.net.mirante.singular.flow.core.MBPM;
import br.net.mirante.singular.flow.core.ProcessDefinitionCache;
import br.net.mirante.singular.flow.core.ProcessInstance;
import br.net.mirante.singular.flow.core.SingularFlowException;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import javax.transaction.Transactional;
import java.io.Serializable;

import static br.net.mirante.singular.definicao.Peticao.PeticaoTask.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PeticaoTest extends TestSupport {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        assertNotNull(mbpmBean);
        MBPM.setConf(mbpmBean);

    }

    @After
    public void tearDown() {
        ProcessDefinitionCache.invalidateAll();
    }

    @Test
    public void testeCriarInstanciaPeticao() {

        InstanciaPeticao id = startInstance();
        InstanciaPeticao id2 = MBPM.findProcessInstance(id.getFullId());

        assertEqualsInstance(id, id2);
    }

    @Test
    public void executeTransitionWithoutTransitionName() {
        thrown.expect(SingularFlowException.class);

        InstanciaPeticao instanciaPeticao = startInstance();
        instanciaPeticao.executeTransition();
    }


    @Test
    public void executeHappyPath() {
        InstanciaPeticao ip = startInstance();
        ip.executeTransition(Peticao.APROVAR_TECNICO);
        ip.executeTransition(Peticao.APROVAR_GERENTE);
        ip.executeTransition(Peticao.PUBLICAR);

        assertCurrentTaskName(PUBLICADO.getName(), ip);
    }

    @Test
    public void grantApplication() {
        InstanciaPeticao ip = startInstance();
        ip.executeTransition(Peticao.APROVAR_TECNICO);
        ip.executeTransition(Peticao.DEFERIR);

        assertCurrentTaskName(DEFERIDO.getName(), ip);
    }

    @Test
    public void rejectApplication() {
        InstanciaPeticao ip = startInstance();
        ip.executeTransition(Peticao.INDEFERIR);

        assertCurrentTaskName(INDEFERIDO.getName(), ip);
    }

    @Test
    public void fuzzyFlow() {
        InstanciaPeticao ip = startInstance();
        ip.executeTransition(Peticao.APROVAR_TECNICO);
        ip.executeTransition(Peticao.SOLICITAR_AJUSTE_ANALISE);
        ip.executeTransition(Peticao.COLOCAR_EM_EXIGENCIA);
        ip.executeTransition(Peticao.CUMPRIR_EXIGENCIA);
        ip.executeTransition(Peticao.APROVAR_TECNICO);
        ip.executeTransition(Peticao.APROVAR_GERENTE);
        ip.executeTransition(Peticao.PUBLICAR);

        assertCurrentTaskName(PUBLICADO.getName(), ip);
    }

    @Test
    public void expirarAprovaGerente() {
        InstanciaPeticao ip = startInstance();
        System.out.println("Id - " + ip.getId());
        ip.executeTransition(Peticao.APROVAR_TECNICO);

        new ExecuteWaitingTasksJob(null).run();

        assertCurrentTaskName(AGUARDANDO_PUBLICACAO.getName(), ip);
    }

    @Test
    @Transactional(Transactional.TxType.NEVER)
    public void zTest() {
        inspecionarDB();
        System.out.println("");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////                               MÉTODOS UTILITÁRIOS                                       ////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private InstanciaPeticao startInstance() {
        InstanciaPeticao id = new InstanciaPeticao();
        id.start();
        return id;
    }

    private void assertEqualsInstance(ProcessInstance instance1, ProcessInstance instance2) {
        Serializable cod1 = instance1.getEntity().getCod();
        Serializable cod2 = instance2.getEntity().getCod();

        assertEquals("As instâncias de processo são diferentes", cod1, cod2);
    }

    private void assertCurrentTaskName(String expectedCurrentTaskName, InstanciaPeticao instanciaPeticao) {
        assertEquals("Situação diferente do esperado",
                expectedCurrentTaskName,
                instanciaPeticao.getCurrentTaskName());
    }
}