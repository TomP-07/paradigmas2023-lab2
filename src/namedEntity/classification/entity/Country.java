package namedEntity.classification.entity;

import namedEntity.NamedEntity;

public class Country extends NamedEntity {

    public Country(String name) {
        super(name, EntityType.COUNTRY, 1);

    }
}
