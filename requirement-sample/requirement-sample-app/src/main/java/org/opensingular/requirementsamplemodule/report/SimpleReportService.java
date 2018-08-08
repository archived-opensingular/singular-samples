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

package org.opensingular.requirementsamplemodule.report;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.opensingular.requirementsamplemodule.report.filter.STypeSimpleReportFilter;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class SimpleReportService {

    private static List<SimpleDTO> list = new ArrayList<>();

    static  {
        int count = 0;

        list.add(new SimpleDTO(count++,"Afeganistão","Cabul","Ásia"));
        list.add(new SimpleDTO(count++,"África do Sul","Pretória","África"));
        list.add(new SimpleDTO(count++,"Albânia","Tirana","Europa"));
        list.add(new SimpleDTO(count++,"Alemanha","Berlim","Europa"));
        list.add(new SimpleDTO(count++,"Andorra","Andorra-a-Velha","Europa"));
        list.add(new SimpleDTO(count++,"Angola","Luanda","África"));
        list.add(new SimpleDTO(count++,"Antiga e Barbuda","São João","América"));
        list.add(new SimpleDTO(count++,"Arábia Saudita","Riade","Ásia"));
        list.add(new SimpleDTO(count++,"Argélia","Argel","África"));
        list.add(new SimpleDTO(count++,"Argentina","Buenos Aires","América"));
        list.add(new SimpleDTO(count++,"Arménia","Erevã","Ásia"));
        list.add(new SimpleDTO(count++,"Austrália","Camberra","Oceania"));
        list.add(new SimpleDTO(count++,"Áustria","Viena","Europa"));
        list.add(new SimpleDTO(count++,"Azerbaijão","Bacu","Ásia"));
        list.add(new SimpleDTO(count++,"Bahamas","Nassau","América"));
        list.add(new SimpleDTO(count++,"Bangladexe","Daca","Ásia"));
        list.add(new SimpleDTO(count++,"Barbados","Bridgetown","América"));
        list.add(new SimpleDTO(count++,"Barém","Manama","Ásia"));
        list.add(new SimpleDTO(count++,"Bélgica","Bruxelas","Europa"));
        list.add(new SimpleDTO(count++,"Belize","Belmopã","América"));
        list.add(new SimpleDTO(count++,"Benim","Porto Novo","África"));
        list.add(new SimpleDTO(count++,"Bielorrússia","Minsque","Europa"));
        list.add(new SimpleDTO(count++,"Bolívia","Sucre","América"));
        list.add(new SimpleDTO(count++,"Bósnia e Herzegovina","Saraievo","Europa"));
        list.add(new SimpleDTO(count++,"Botsuana","Gaborone","África"));
        list.add(new SimpleDTO(count++,"Brasil","Brasília","América"));
        list.add(new SimpleDTO(count++,"Brunei","Bandar Seri Begauã","Ásia"));
        list.add(new SimpleDTO(count++,"Bulgária","Sófia","Europa"));
        list.add(new SimpleDTO(count++,"Burquina Faso","Uagadugu","África"));
        list.add(new SimpleDTO(count++,"Burúndi","Bujumbura","África"));
        list.add(new SimpleDTO(count++,"Butão","Timbu","Ásia"));
        list.add(new SimpleDTO(count++,"Cabo Verde","Praia","África"));
        list.add(new SimpleDTO(count++,"Camarões","Iaundé","África"));
        list.add(new SimpleDTO(count++,"Camboja","Pnom Pene","Ásia"));
        list.add(new SimpleDTO(count++,"Canadá","Otava","América"));
        list.add(new SimpleDTO(count++,"Catar","Doa","Ásia"));
        list.add(new SimpleDTO(count++,"Cazaquistão","Astana","Ásia"));
        list.add(new SimpleDTO(count++,"Chade","Jamena","África"));
        list.add(new SimpleDTO(count++,"Chile","Santiago","América"));
        list.add(new SimpleDTO(count++,"China","Pequim","Ásia"));
        list.add(new SimpleDTO(count++,"Chipre","Nicósia","Europa"));
        list.add(new SimpleDTO(count++,"Colômbia","Bogotá","América"));
        list.add(new SimpleDTO(count++,"Comores","Moroni","África"));
        list.add(new SimpleDTO(count++,"Congo-Brazzaville","Brazavile","África"));
        list.add(new SimpleDTO(count++,"Coreia do Norte","Pionguiangue","Ásia"));
        list.add(new SimpleDTO(count++,"Coreia do Sul","Seul","Ásia"));
        list.add(new SimpleDTO(count++,"Cosovo","Pristina","Europa"));
        list.add(new SimpleDTO(count++,"Costa do Marfim","Iamussucro","África"));
        list.add(new SimpleDTO(count++,"Costa Rica","São José","América"));
        list.add(new SimpleDTO(count++,"Croácia","Zagrebe","Europa"));
        list.add(new SimpleDTO(count++,"Cuaite","Cidade do Cuaite","Ásia"));
        list.add(new SimpleDTO(count++,"Cuba","Havana","América"));
        list.add(new SimpleDTO(count++,"Dinamarca","Copenhaga","Europa"));
        list.add(new SimpleDTO(count++,"Dominica","Roseau","América"));
        list.add(new SimpleDTO(count++,"Egito","Cairo","África"));
        list.add(new SimpleDTO(count++,"Emirados Árabes Unidos","Abu Dabi","Ásia"));
        list.add(new SimpleDTO(count++,"Equador","Quito","América"));
        list.add(new SimpleDTO(count++,"Eritreia","Asmara","África"));
        list.add(new SimpleDTO(count++,"Eslováquia","Bratislava","Europa"));
        list.add(new SimpleDTO(count++,"Eslovénia","Liubliana","Europa"));
        list.add(new SimpleDTO(count++,"Espanha","Madrid","Europa"));
        list.add(new SimpleDTO(count++,"Estado da Palestina","Jerusalém Oriental","Ásia"));
        list.add(new SimpleDTO(count++,"Estados Unidos","Washington, D.C.","América"));
        list.add(new SimpleDTO(count++,"Estónia","Talim","Europa"));
        list.add(new SimpleDTO(count++,"Etiópia","Adis Abeba","África"));
        list.add(new SimpleDTO(count++,"Fiji","Suva","Oceania"));
        list.add(new SimpleDTO(count++,"Filipinas","Manila","Ásia"));
        list.add(new SimpleDTO(count++,"Finlândia","Helsínquia","Europa"));
        list.add(new SimpleDTO(count++,"França","Paris","Europa"));
        list.add(new SimpleDTO(count++,"Gabão","Libreville","África"));
        list.add(new SimpleDTO(count++,"Gâmbia","Banjul","África"));
        list.add(new SimpleDTO(count++,"Gana","Acra","África"));
        list.add(new SimpleDTO(count++,"Geórgia","Tebilíssi","Ásia"));
        list.add(new SimpleDTO(count++,"Granada","São Jorge","América"));
        list.add(new SimpleDTO(count++,"Grécia","Atenas","Europa"));
        list.add(new SimpleDTO(count++,"Guatemala","Cidade da Guatemala","América"));
        list.add(new SimpleDTO(count++,"Guiana","Georgetown","América"));
        list.add(new SimpleDTO(count++,"Guiné","Conacri","África"));
        list.add(new SimpleDTO(count++,"Guiné Equatorial","Malabo","África"));
        list.add(new SimpleDTO(count++,"Guiné-Bissau","Bissau","África"));
        list.add(new SimpleDTO(count++,"Haiti","Porto Príncipe","América"));
        list.add(new SimpleDTO(count++,"Honduras","Tegucigalpa","América"));
        list.add(new SimpleDTO(count++,"Hungria","Budapeste","Europa"));
        list.add(new SimpleDTO(count++,"Iémen","Saná","Ásia"));
        list.add(new SimpleDTO(count++,"Ilhas Marechal","Majuro","Oceania"));
        list.add(new SimpleDTO(count++,"Índia","Nova Déli","Ásia"));
        list.add(new SimpleDTO(count++,"Indonésia","Jacarta","Ásia"));
        list.add(new SimpleDTO(count++,"Irão","Teerão","Ásia"));
        list.add(new SimpleDTO(count++,"Iraque","Bagdade","Ásia"));
        list.add(new SimpleDTO(count++,"Irlanda","Dublim","Europa"));
        list.add(new SimpleDTO(count++,"Islândia","Reiquiavique","Europa"));
        list.add(new SimpleDTO(count++,"Israel","Jerusalém","Ásia"));
        list.add(new SimpleDTO(count++,"Itália","Roma","Europa"));
        list.add(new SimpleDTO(count++,"Jamaica","Kingston","América"));
        list.add(new SimpleDTO(count++,"Japão","Tóquio","Ásia"));
        list.add(new SimpleDTO(count++,"Jibuti","Jibuti","África"));
        list.add(new SimpleDTO(count++,"Jordânia","Amã","Ásia"));
        list.add(new SimpleDTO(count++,"Laus","Vienciana","Ásia"));
        list.add(new SimpleDTO(count++,"Lesoto","Maseru","África"));
        list.add(new SimpleDTO(count++,"Letónia","Riga","Europa"));
        list.add(new SimpleDTO(count++,"Líbano","Beirute","Ásia"));
        list.add(new SimpleDTO(count++,"Libéria","Monróvia","África"));
        list.add(new SimpleDTO(count++,"Líbia","Trípoli","África"));
        list.add(new SimpleDTO(count++,"Listenstaine","Vaduz","Europa"));
        list.add(new SimpleDTO(count++,"Lituânia","Vílnius","Europa"));
        list.add(new SimpleDTO(count++,"Luxemburgo","Luxemburgo","Europa"));
        list.add(new SimpleDTO(count++,"Macedónia","Escópia","Europa"));
        list.add(new SimpleDTO(count++,"Madagáscar","Antananarivo","África"));
        list.add(new SimpleDTO(count++,"Malásia","Cuala Lumpur","Ásia"));
        list.add(new SimpleDTO(count++,"Maláui","Lilôngue","África"));
        list.add(new SimpleDTO(count++,"Maldivas","Malé","Ásia"));
        list.add(new SimpleDTO(count++,"Mali","Bamaco","África"));
        list.add(new SimpleDTO(count++,"Malta","Valeta","Europa"));
        list.add(new SimpleDTO(count++,"Marrocos","Rebate","África"));
        list.add(new SimpleDTO(count++,"Maurícia","Porto Luís","África"));
        list.add(new SimpleDTO(count++,"Mauritânia","Nuaquechote","África"));
        list.add(new SimpleDTO(count++,"México","Cidade do México","América"));
        list.add(new SimpleDTO(count++,"Mianmar","Nepiedó","Ásia"));
        list.add(new SimpleDTO(count++,"Micronésia","Paliquir","Oceania"));
        list.add(new SimpleDTO(count++,"Moçambique","Maputo","África"));
        list.add(new SimpleDTO(count++,"Moldávia","Quixinau","Europa"));
        list.add(new SimpleDTO(count++,"Mónaco","Mónaco","Europa"));
        list.add(new SimpleDTO(count++,"Mongólia","Ulã Bator","Ásia"));
        list.add(new SimpleDTO(count++,"Montenegro","Podgoritsa","Europa"));
        list.add(new SimpleDTO(count++,"Namíbia","Vinduque","África"));
        list.add(new SimpleDTO(count++,"Nauru","Iarém","Oceania"));
        list.add(new SimpleDTO(count++,"Nepal","Catmandu","Ásia"));
        list.add(new SimpleDTO(count++,"Nicarágua","Manágua","América"));
        list.add(new SimpleDTO(count++,"Níger","Niamei","África"));
        list.add(new SimpleDTO(count++,"Nigéria","Abuja","África"));
        list.add(new SimpleDTO(count++,"Noruega","Oslo","Europa"));
        list.add(new SimpleDTO(count++,"Nova Zelândia","Wellington","Oceania"));
        list.add(new SimpleDTO(count++,"Omã","Mascate","Ásia"));
        list.add(new SimpleDTO(count++,"Países Baixos","Amesterdão","Europa"));
        list.add(new SimpleDTO(count++,"Palau","Ngerulmud","Oceania"));
        list.add(new SimpleDTO(count++,"Panamá","Cidade do Panamá","América"));
        list.add(new SimpleDTO(count++,"Papua Nova Guiné","Porto Moresby","Oceania"));
        list.add(new SimpleDTO(count++,"Paquistão","Islamabade","Ásia"));
        list.add(new SimpleDTO(count++,"Paraguai","Assunção","América"));
        list.add(new SimpleDTO(count++,"Peru","Lima","América"));
        list.add(new SimpleDTO(count++,"Polónia","Varsóvia","Europa"));
        list.add(new SimpleDTO(count++,"Portugal","Lisboa","Europa"));
        list.add(new SimpleDTO(count++,"Quénia","Nairóbi","África"));
        list.add(new SimpleDTO(count++,"Quirguistão","Bisqueque","Ásia"));
        list.add(new SimpleDTO(count++,"Quiribáti","Taraua do Sul","Oceania"));
        list.add(new SimpleDTO(count++,"Reino Unido","Londres","Europa"));
        list.add(new SimpleDTO(count++,"República Centro-Africana","Bangui","África"));
        list.add(new SimpleDTO(count++,"República Checa","Praga","Europa"));
        list.add(new SimpleDTO(count++,"República Democrática do Congo","Quinxassa","África"));
        list.add(new SimpleDTO(count++,"República Dominicana","São Domingos","América"));
        list.add(new SimpleDTO(count++,"Roménia","Bucareste","Europa"));
        list.add(new SimpleDTO(count++,"Ruanda","Quigali","África"));
        list.add(new SimpleDTO(count++,"Rússia","Moscovo","Europa"));
        list.add(new SimpleDTO(count++,"Salomão","Honiara","Oceania"));
        list.add(new SimpleDTO(count++,"Salvador","São Salvador","América"));
        list.add(new SimpleDTO(count++,"Samoa","Apia","Oceania"));
        list.add(new SimpleDTO(count++,"Santa Lúcia","Castries","América"));
        list.add(new SimpleDTO(count++,"São Cristóvão e Neves","Basseterre","América"));
        list.add(new SimpleDTO(count++,"São Marinho","São Marinho","Europa"));
        list.add(new SimpleDTO(count++,"São Tomé e Príncipe","São Tomé","África"));
        list.add(new SimpleDTO(count++,"São Vicente e Granadinas","Kingstown","América"));
        list.add(new SimpleDTO(count++,"Seicheles","Vitória","África"));
        list.add(new SimpleDTO(count++,"Senegal","Dacar","África"));
        list.add(new SimpleDTO(count++,"Serra Leoa","Freetown","África"));
        list.add(new SimpleDTO(count++,"Sérvia","Belgrado","Europa"));
        list.add(new SimpleDTO(count++,"Singapura","Singapura","Ásia"));
        list.add(new SimpleDTO(count++,"Síria","Damasco","Ásia"));
        list.add(new SimpleDTO(count++,"Somália","Mogadíscio","África"));
        list.add(new SimpleDTO(count++,"Sri Lanca","Sri Jaiavardenapura-Cota","Ásia"));
        list.add(new SimpleDTO(count++,"Suazilândia","Lobamba","África"));
        list.add(new SimpleDTO(count++,"Sudão","Cartum","África"));
        list.add(new SimpleDTO(count++,"Sudão do Sul","Juba","África"));
        list.add(new SimpleDTO(count++,"Suécia","Estocolmo","Europa"));
        list.add(new SimpleDTO(count++,"Suíça","Berna","Europa"));
        list.add(new SimpleDTO(count++,"Suriname","Paramaribo","América"));
        list.add(new SimpleDTO(count++,"Tailândia","Banguecoque","Ásia"));
        list.add(new SimpleDTO(count++,"Taiuã","Taipé","Ásia"));
        list.add(new SimpleDTO(count++,"Tajiquistão","Duchambé","Ásia"));
        list.add(new SimpleDTO(count++,"Tanzânia","Dodoma","África"));
        list.add(new SimpleDTO(count++,"Timor-Leste","Díli","Oceania"));
        list.add(new SimpleDTO(count++,"Togo","Lomé","África"));
        list.add(new SimpleDTO(count++,"Tonga","Nucualofa","Oceania"));
        list.add(new SimpleDTO(count++,"Trindade e Tobago","Porto de Espanha","América"));
        list.add(new SimpleDTO(count++,"Tunísia","Tunes","África"));
        list.add(new SimpleDTO(count++,"Turcomenistão","Asgabate","Ásia"));
        list.add(new SimpleDTO(count++,"Turquia","Ancara","Ásia"));
        list.add(new SimpleDTO(count++,"Tuvalu","Funafuti","Oceania"));
        list.add(new SimpleDTO(count++,"Ucrânia","Quieve","Europa"));
        list.add(new SimpleDTO(count++,"Uganda","Campala","África"));
        list.add(new SimpleDTO(count++,"Uruguai","Montevideu","América"));
        list.add(new SimpleDTO(count++,"Usbequistão","Tasquente","Ásia"));
        list.add(new SimpleDTO(count++,"Vanuatu","Porto Vila","Oceania"));
        list.add(new SimpleDTO(count++,"Vaticano","Vaticano","Europa"));
        list.add(new SimpleDTO(count++,"Venezuela","Caracas","América"));
        list.add(new SimpleDTO(count++,"Vietname","Hanói","Ásia"));
        list.add(new SimpleDTO(count++,"Zâmbia","Lusaca","África"));
        list.add(new SimpleDTO(count++,"Zimbábue","Harare","África"));
    }

    public Iterable<? extends SimpleDTO> listSimpleData(SInstance instance) {
        STypeSimpleReportFilter filterType = (STypeSimpleReportFilter) instance.getType();
        SIComposite filtro = (SIComposite) instance;
        String nomePais = filtro.getField(filterType.nomePais).getValue();
        String nomeCapital = filtro.getField(filterType.nomeCapital).getValue();

        return list.stream().filter(p ->{
            boolean keep =  true;
            if (nomePais != null){
                keep &= p.getNome().toLowerCase().contains(nomePais);
            }
            if (nomeCapital != null){
                keep &= p.getDescricao().toLowerCase().contains(nomeCapital);
            }
            return keep;
        }).collect(Collectors.toList());

    }
}
