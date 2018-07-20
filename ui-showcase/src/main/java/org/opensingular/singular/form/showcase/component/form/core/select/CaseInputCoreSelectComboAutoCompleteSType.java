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

package org.opensingular.singular.form.showcase.component.form.core.select;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.provider.SSimpleProviderListBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewAutoComplete;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;
import org.opensingular.singular.form.showcase.component.form.core.select.form.SIPlanet;
import org.opensingular.singular.form.showcase.component.form.core.select.form.STPlanet;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Auto-completar
 */
/*hidden*/@CaseItem(componentName = "Select", subCaseName = "Auto-completar", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "ComboAutoComplete")
public class CaseInputCoreSelectComboAutoCompleteSType extends STypeComposite<SIComposite> {

    public STypeString myAuthor;
    public STypeString myHero;
    public STPlanet    myPlanet;
    public STypeString email;

    private static final String[] HEROES = {"Adam Strange", "Agent Liberty", "Air Wave", "Animal Man", "Aqualad", "Aquaman", "Arsenal", "Artemis of Bana-Mighdall", "The Atom", "Atom Smasher", "Aurakles", "Azrael", "Aztec", "Bart Allen", "Barry Allen", "Batgirl", "Batman", "Beast Boy", "Black Canary", "Blue Beetle", "Booster Gold", "Bronze Tiger", "Captain Marvel Jr.", "Cassandra Cain", "Cyborg", "Comedian", "Captain Metropolis", "Captain Marvel (Shazam)", "Calendar Man", "Catwoman", "Dan the Dyna-Mite", "Damian Wayne", "Dick Grayson", "Donna Troy", "Dr. Fate", "Dr. Manhattan", "Elongated Man", "Fire", "Firestorm", "Felicity Smoak", "The Flash", "Ferro Lad", "Gangbuster", "Green Arrow (Oliver Queen)", "Green Arrow (Connor Hawke)", "Guardian", "Guy Gardner", "Green Lantern", "Gypsy", "Hal Jordan", "Hawkman", "Hawkgirl", "Hank Hall", "Hooded Justice", "Hourman", "Huntress", "Ice", "Icemaiden", "Impulse", "Iris West", "Jason Todd", "Jericho", "Jezebelle", "John Stewart", "Johnny Quick", "Jonah Hex", "Judomaster", "Kamandi", "Karate Kid", "Katana", "Kate Spencer", "Kyle Rayner", "Kid Flash", "Kid Flash (Iris West)", "Kid Flash (Wally West)", "Kent Shakespeare", "Lagoon Boy", "Liberty Belle", "Manhunter", "Martian Manhunter", "Mary Marvel", "Mas y Menos", "Metamorpho", "Misfit", "Mister America (Jeffrey Graves)", "Mister America (Tex Thompson)", "Mr. Terrific", "Miss Martian", "Nightwing", "Nite Owl", "Nite Owl II", "Night Star", "Oracle", "Orin", "Owlman", "Owlwoman", "Ozymandias", "Pantha", "Phantom Lady", "Plastic Man", "Poison Ivy", "Power Girl", "The Question", "Raven", "Ray", "Red Arrow", "Red Tornado", "Robin", "Robotman", "Rocket", "Rocket Red", "Rorschach", "Roy Harper", "Sandy Hawkins", "Seven Soldiers of Victory", "Shining Knight", "Silk Spectre", "Solomon Grundy", "Spectre", "Speedy", "Starfire", "Stargirl", "Starman", "Star-Spangled Kid", "Static", "Stephanie Brown", "S.T.R.I.P.E.", "Superboy (Kal-El)", "Supergirl", "Superman", "Tiger", "Tim Drake", "Terry McGinnis", "Terra", "Tex Thompson", "TNT", "Vigilante", "Vixen", "Wally West", "Wildcat", "Wonder Girl", "Wonder Woman"};
    private static final String[] AUTHORS = {"Abgar Renault", "Adélia Prado", "Adolfo Caminha", "Adriana Falcão", "Adriana Lisboa", "Afonso Arinos", "Affonso Romano de Sant'Anna", "Afrânio Peixoto", "Alaíde Lisboa", "Alberto de Oliveira", "Alberto Mussa", "Alcântara Machado", "Alceu Wamosy", "Alcides Maia", "Alcione Sortica", "Aldo Novak", "Alphonsus de Guimarães", "Aluísio Azevedo", "Alvarenga Peixoto", "Álvares de Azevedo", "Ana Cristina Cesar", "Aníbal Beça", "Aníbal Machado", "Antônio Bezerra", "Antônio Callado", "Antônio Sales", "Araripe Júnior", "Artur Azevedo", "Antonio Cícero", "Arnaldo Antunes", "Arnaldo Damasceno Vieira", "Arthur Ramos", "Augusto de Campos", "Augusto dos Anjos", "Autran Dourado", "Ariano Suassuna", "Basílio da Gama", "Benjamin Sanches", "Bento Teixeira", "Bernadette Lyra", "Botelho de Oliveira", "Bruna Lombardi", "Caio Fernando Abreu", "Capistrano de Abreu", "Carlos de Laet", "Carlos Drummond de Andrade", "Carlos Heitor Cony", "Carlos Henrique Schroeder", "Casimiro de Abreu", "Cassiano Ricardo", "Castro Alves", "Catulo da Paixão Cearense", "Cecília Meireles", "Celso Sisto", "César Leal", "Chico Buarque", "Chico César", "Clarice Lispector", "Clarice Pacheco", "Cléo Martins", "Cornélio Pena", "Cruz e Sousa", "Dalcídio Jurandir", "Dalton Trevisan", "Darcy Ribeiro", "Dau Bastos", "Décio Pignatari", "Deoscoredes M. dos Santos", "Dias Gomes", "Dionélio Machado", "Domingos Pellegrini", "Drauzio Varella", "Edison Carneiro", "Elisa Lispector", "Elisa Lucinda", "Elly Herkenhoff", "Eneida de Moraes", "Érico Veríssimo", "Euclides da Cunha", "Fagundes Varela", "Fernando Bonassi", "Fernando Sabino", "Fernando Gabeira", "Ferreira Gullar", "Francisca Júlia", "Fran Dotti do Prado", "Gabriel Chalita", "Gesiel Júnior", "Gilberto Freyre", "Gilka Machado", "Gonçalves de Magalhães", "Gonçalves Dias", "Graciliano Ramos", "Graça Aranha", "Gregório de Matos Guerra", "Gustavo Reiz", "Gustavo Barroso", "Haroldo Maranhão", "Harry Laus", "Hélio Melo", "Hélio Pellegrino", "Hilda Hilst", "Huberto Rohden", "Ignácio de Loyola Brandão", "Inglês de Sousa", "Ivan Sant'anna", "Izomar Camargo Guilherme", "Josué Guimarães", "João Aguiar", "João do Rio", "João Cabral de Melo Neto", "João Gilberto Noll", "João Guimarães Rosa", "João Paulo Cotrim", "João Simões Lopes Neto", "João Ubaldo Ribeiro", "Jorge Amado", "José de Alencar", "Jorge Fernando dos Santos", "José Leon Machado", "José J. Veiga", "José Lins do Rego", "Julio Cezar Ribeiro Vaugham", "Jô Soares", "Juvenal Galeno", "Leonardo de Moraes", "Leonardo Mota", "Leo Vaz", "Lima Barreto", "Livia Garcia-Roza", "Lourenço Mutarelli", "Luis Eduardo Matta", "Luís Fernando Veríssimo", "Luiz Bacellar", "Luiz Alfredo Garcia Roza", "Lygia Fagundes Telles", "Lya Luft", "Maciel Monteiro", "Machado de Assis", "Manuel Bandeira", "Manuel de Barros", "Márcio Souza", "Márcio Vassallo", "Maria José Dupré", "Maria Teresa Elisa Böbel", "Mário de Andrade", "Mário Faustino", "Mário Quintana", "Mário Ribeiro da Cruz", "Max Martins", "Menotti del Picchia", "Michel Melamed", "Miguel M. Abrahão", "Miguel Jorge (escritor)", "Miguel Marvilla", "Millôr Fernandes", "Milton Hatoum", "Mino Carta", "Moacir Japiassu", "Moacyr Scliar", "Monteiro Lobato", "Moreira Campos", "Murilo Mendes", "Murilo Rubião", "Nelson Hoffmann", "Nelson Rodrigues", "Nina Rodrigues", "Nic Nilson", "Otto Lara Resende", "Otto Maria Carpeaux", "Oswald de Andrade", "Paulo Coelho", "Paulo Freire", "Paulo Leminski", "Paulo Lins", "Paulo Mendes Campos", "Paulo Pontes", "Paulo Querido", "Pedro Bandeira", "Pedro Gil-Pedro", "Pedro Nava", "Plínio Marcos", "Qorpo Santo", "Rachel de Queiroz", "Raduan Nassar", "Raul Bopp", "Raul de Leoni", "Raul Pompéia", "Raul Lody", "Regina Echeverria", "Reginaldo Prandi", "Renard Perez", "Renata Palottini", "Renato Pacheco", "Renato Tapado", "Roberto Drummond", "Rodolfo Teófilo", "Ronaldo Cagiano Barbosa", "Rubem Alves", "Rubem Braga", "Rubem Fonseca", "Rui Barbosa", "Ruth Rocha", "Santiago Nazarian", "Sérgio Buarque de Hollanda", "Sérgio Jockyman", "Sérgio Sant'Anna", "Silviano Santiago", "Sousândrade", "Thales de Andrade", "Vicente Cechelero", "Victor Louis Stutz", "Vinicius de Moraes", "Waldo Vieira", "Zacarias Martins", "Zélia Gattai", "Ziraldo"};
    private static final String[] DOMAINS = {"@gmail.com", "@hotmail.com", "@yahoo.com"};

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        myAuthor = this.addFieldString("myAuthor");
        myHero   = this.addFieldString("myHero");
        myPlanet = this.addField("myPlanet", STPlanet.class);
        email    = this.addFieldString("email");

        myAuthor.autocompleteOf(AUTHORS)
                .asAtr().label("Autor Favorito");

        myHero.autocompleteOf(HEROES)
                .asAtr().label("Herói Favorito");

        // Auto Complete padrão com opções pré-definidas para tipos compostos.
        //@destacar
        myPlanet.autocomplete()
                .id(myPlanet.name)
                .display("Nome: ${name}, Posição: ${position}, Diâmetro(em Km): ${diameter}")
                .simpleProvider(this::populateProvider);
        myPlanet.withInitListener(this::myPlanetInitListener)
                .withView(SViewAutoComplete::new)
                .asAtr()
                .dependsOn(myHero)
                .enabled(si -> si.findNearestValue(myHero).isPresent())
                .label("Planeta de Origem");

        // Auto Complete com opções dinâmicas baseadas nos valores informados.
        email.asAtr().label("Email");
        //@destacar
        email.lazyAutocompleteOf(String.class)
                .selfIdAndDisplay()
                .filteredProvider(this::emailProvider);
    }

    private void populateProvider(SSimpleProviderListBuilder builder) {
        builder.add().set(myPlanet.name, "Mercury").set(myPlanet.position, 1).set(myPlanet.diameter, 4879);
        builder.add().set(myPlanet.name, "Venus").set(myPlanet.position, 2).set(myPlanet.diameter, 12104);
        builder.add().set(myPlanet.name, "Earth").set(myPlanet.position, 3).set(myPlanet.diameter, 12756);
        builder.add().set(myPlanet.name, "Mars").set(myPlanet.position, 4).set(myPlanet.diameter, 6792);
        builder.add().set(myPlanet.name, "Jupiter").set(myPlanet.position, 5).set(myPlanet.diameter, 142984);
        builder.add().set(myPlanet.name, "Saturn").set(myPlanet.position, 6).set(myPlanet.diameter, 120536);
        builder.add().set(myPlanet.name, "Uranus").set(myPlanet.position, 7).set(myPlanet.diameter, 51118);
        builder.add().set(myPlanet.name, "Neptune").set(myPlanet.position, 8).set(myPlanet.diameter, 4952);
    }

    private void myPlanetInitListener(SIPlanet siPlanet) {
        siPlanet.name().setValue("Mercury");
        siPlanet.position().setValue(1);
        siPlanet.diameter().setValue(1);
    }

    private List<String> emailProvider(SIString siString, String query) {
        List<String> list = new ArrayList<>();
        for (String domain : DOMAINS) {
            if (query != null) {
                list.add(query + domain);
            } else {
                list.add(domain);
            }
        }
        return list;
    }

}
