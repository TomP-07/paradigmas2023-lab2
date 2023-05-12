package database;

import namedEntity.classification.entity.EntityType;
import namedEntity.classification.subject.SubjectType;

public class NamedEntityData {

    private EntityType entityType;
    private SubjectType subjectType;

    public NamedEntityData(EntityType entityType, SubjectType subjectType) {
        this.entityType = entityType;
        this.subjectType = subjectType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }
}
