package namedEntity.classification.entity;

import namedEntity.NamedEntity;

public class Person extends NamedEntity {

    private String lastName;

    public Person(String name, String lastName) {
        super(name, EntityType.PERSON, 1);
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
