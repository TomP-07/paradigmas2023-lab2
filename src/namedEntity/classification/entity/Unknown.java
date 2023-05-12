package namedEntity.classification.entity;

import namedEntity.NamedEntity;

public class Unknown extends NamedEntity {

    public Unknown(String name) {
        super(name, EntityType.UNKNOWN, 1);
    }
}
