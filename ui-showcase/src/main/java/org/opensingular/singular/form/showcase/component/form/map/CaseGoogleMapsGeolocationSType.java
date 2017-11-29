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

package org.opensingular.singular.form.showcase.component.form.map;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.util.STypeLatitudeLongitude;
import org.opensingular.form.view.SViewCurrentLocation;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

/**
 * Nesse exemplo a posição é informada apenas de maneira automática pela geolocation API do browser.
 * Essa API exige que a aplicação esteja em HTTPS.
 */
@CaseItem(componentName = "Google Maps Geolocation", group = Group.MAPS, subCaseName = "Geolocation API")
@SInfoType(spackage = CaseMapsPackage.class, name = "GoogleMapsGeo")
public class CaseGoogleMapsGeolocationSType extends STypeComposite<SIComposite> {

    public STypeLatitudeLongitude coordenada;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        coordenada = this.addField("coordenada", STypeLatitudeLongitude.class);
        //@destacar
        coordenada.withView(new SViewCurrentLocation().disableUserLocationSelection());
        coordenada
                .asAtr().required();
    }
}
