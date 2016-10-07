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
package org.opensingular.flow.core.renderer.bpmn.view.config;

import org.opensingular.flow.core.renderer.bpmn.view.DataObjectNodeStyle;
import org.opensingular.flow.core.renderer.bpmn.view.DataObjectType;
import org.opensingular.flow.core.renderer.toolkit.optionhandler.EnumValueAnnotation;
import org.opensingular.flow.core.renderer.toolkit.optionhandler.Label;

/**
 * Configuration class for DataObjectNodeStyle option pane.
 *
 * This is only needed for the sample application to provide an easy way to configure the option pane. Customer applications
 * will likely provide their own property configuration framework and won't need this part of the library
 */
@Label("DataObject Node")
public class DataObjectNodeStyleConfiguration extends NodeStyleConfiguration<DataObjectNodeStyle> {
  //region Properties

  @Label("Data Object Type")
  @EnumValueAnnotation(label = "None", value = "NONE")
  @EnumValueAnnotation(label = "Input", value = "INPUT")
  @EnumValueAnnotation(label = "Output", value = "OUTPUT")
  public final DataObjectType getType() {
    return getStyleTemplate().getType();
  }

  @Label("Data Object Type")
  @EnumValueAnnotation(label = "None", value = "NONE")
  @EnumValueAnnotation(label = "Input", value = "INPUT")
  @EnumValueAnnotation(label = "Output", value = "OUTPUT")
  public final void setType( DataObjectType value ) {
    getStyleTemplate().setType(value);
  }

  @Label("Collection")
  public final boolean isCollection() {
    return getStyleTemplate().isCollection();
  }

  @Label("Collection")
  public final void setCollection( boolean value ) {
    getStyleTemplate().setCollection(value);
  }

  @Override
  protected DataObjectNodeStyle createDefault() {
    return new DataObjectNodeStyle();
  }
}