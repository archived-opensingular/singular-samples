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

package org.opensingular.sample.studio.entity;

import javax.annotation.Nullable;
import javax.persistence.AttributeConverter;

import org.opensingular.form.SInstance;
import org.opensingular.form.persistence.relational.RelationalColumnConverter;

public class SimNaoConverter implements AttributeConverter<Boolean, Character>, RelationalColumnConverter {
	public Character convertToDatabaseColumn(Boolean attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute ? 'S' : 'N';
	}

    @Nullable
	public Boolean convertToEntityAttribute(Character dbData) {
		if (dbData == null) {
			return null;
		}
		if (Character.valueOf('S').equals(Character.toUpperCase(dbData))) {
			return Boolean.TRUE;
		}
		if (Character.valueOf('N').equals(Character.toUpperCase(dbData))) {
			return Boolean.FALSE;
		}
		return null;
	}

	public Object toRelationalColumn(SInstance fromInstance) {
		Object value = fromInstance.getValue();
		if (value == null) {
			return null;
		}
		return convertToDatabaseColumn((Boolean) value);
	}

	public void fromRelationalColumn(Object dbData, SInstance toInstance) {
		if (dbData == null) {
			toInstance.clearInstance();
		} else if (dbData instanceof String) {
			toInstance.setValue(convertToEntityAttribute(((String) dbData).charAt(0)));
		} else {
			toInstance.setValue(convertToEntityAttribute((Character) dbData));
		}
	}
}
