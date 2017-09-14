package org.opensingular.sample.studio.entity;

import javax.persistence.AttributeConverter;

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

	public Object toRelationalColumn(Object attribute) {
		return convertToDatabaseColumn((Boolean) attribute);
	}

	public Object fromRelationalColumn(Object dbData) {
		if (dbData == null) {
			return null;
		} else if (dbData instanceof String) {
			return convertToEntityAttribute(((String) dbData).charAt(0));
		}
		return convertToEntityAttribute((Character) dbData);
	}
}
