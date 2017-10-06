package org.opensingular.sample.studio.entity;

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
