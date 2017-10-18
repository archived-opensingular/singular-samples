/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.form.exemplos.notificacaosimplificada.dao;

import org.opensingular.form.exemplos.notificacaosimplificada.domain.CategoriaRegulatoriaMedicamento;
import org.opensingular.form.exemplos.notificacaosimplificada.domain.EmbalagemPrimariaBasica;
import org.opensingular.form.exemplos.notificacaosimplificada.domain.EmbalagemSecundaria;
import org.opensingular.form.exemplos.notificacaosimplificada.domain.LinhaCbpf;
import org.opensingular.form.exemplos.notificacaosimplificada.domain.Substancia;
import org.opensingular.form.exemplos.notificacaosimplificada.spring.NotificaoSimplificadaSpringConfiguration;
import org.fest.assertions.api.Assertions;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificaoSimplificadaSpringConfigurationTest.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class VocabularioControladoDAOTest {

    @Inject
    private VocabularioControladoDAO vocabularioControladoDAO;

    @Test
    @Transactional
    public void findByDescricaoForEmbalagemSecundaria() throws Exception {
        final List<EmbalagemSecundaria> result = vocabularioControladoDAO.findByDescricao(EmbalagemSecundaria.class, null);
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    @Transactional
    public void findByDescricaoForEmbalagemPrimaria() throws Exception {
        final List<EmbalagemPrimariaBasica> result = vocabularioControladoDAO.findByDescricao(EmbalagemPrimariaBasica.class, null);
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    @Transactional
    public void findByDescricaoForSubstancia() throws Exception {
        final List<Substancia> result = vocabularioControladoDAO.findByDescricao(Substancia.class, null);
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    @Transactional
    public void listCategoriasRegulatoriasMedicamentoDinamizado() throws Exception {
        final List<CategoriaRegulatoriaMedicamento> result = vocabularioControladoDAO.listCategoriasRegulatoriasMedicamentoDinamizado(null);
        Assert.assertThat("Não foi possivel encontrar os tipos de medicamento dinamizado", result, IsCollectionWithSize.hasSize(3));
    }

    @Test
    @Transactional
    public void listarLinhasProducaoDinamizado() throws Exception {
        final List<LinhaCbpf> result = vocabularioControladoDAO.listarLinhasProducaoDinamizado(null);
        Assert.assertThat("Não foi possivel encontrar os linhas de producao para dinamizado", result, IsCollectionWithSize.hasSize(4));
    }
}