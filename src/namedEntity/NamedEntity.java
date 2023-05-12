package namedEntity;


/*Esta clase modela la nocion de entidad nombrada*/

import namedEntity.classification.entity.EntityType;
import namedEntity.classification.subject.Subject;

public abstract class NamedEntity {
    String name;

    private EntityType type;
    private Subject subject;
    int frequency;

    public NamedEntity(String name, EntityType type, int frequency) {
        this.name = name;
        this.type = type;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getType() {
        return type;
    }

    public boolean hasSubject() {
        return this.subject != null;
    }

    public String getSubject() {
        return name;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void incFrequency() {
        this.frequency++;
    }

    @Override
    public String toString() {
        return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
    }

    public void prettyPrint() {
        System.out.println(this.getName() + " " + this.getFrequency() + " " + this.subject.getSubjectName());
    }


}



