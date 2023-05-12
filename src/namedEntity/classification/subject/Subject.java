package namedEntity.classification.subject;

import namedEntity.NamedEntity;

import java.util.ArrayList;

public abstract class Subject {

    private String name;
    private SubjectType type;
    private ArrayList<NamedEntity> entities;

    public Subject(String name, SubjectType type) {
        this.name = name;
        this.type = type;
        entities = new ArrayList<>();
    }

    public String getSubjectName() {
        return this.name;
    }

    public void addEntity(NamedEntity entity) {
        this.entities.add(entity);
    }

    public SubjectType getType() {
        return this.type;
    }

    public ArrayList<NamedEntity> getEntities() {
        return this.entities;
    }
}
