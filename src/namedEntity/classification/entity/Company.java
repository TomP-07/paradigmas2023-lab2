package namedEntity.classification.entity;

import namedEntity.NamedEntity;

public class Company extends NamedEntity {

    public Company(String name) {
        super(name, EntityType.COMPANY, 1);

    }
}
