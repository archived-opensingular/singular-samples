package org.opensingular.sample.studio.entity;

import javax.persistence.AttributeConverter;


public class SimNaoConverter implements AttributeConverter<Boolean, Character> {
    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return null;
        }
        if (Boolean.TRUE.equals(attribute)) {
            return 'S';
        } else {
            return 'N';
        }
    }

    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        if (dbData == null) {
            return null;
        }
        if (Character.valueOf('S').equals(dbData) || Character.valueOf('s').equals(dbData)) {
            return Boolean.TRUE;
        }
        if (Character.valueOf('N').equals(dbData) || Character.valueOf('n').equals(dbData)) {
            return Boolean.FALSE;
        }
        return null;
    }
}
