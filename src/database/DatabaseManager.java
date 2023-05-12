package database;

import namedEntity.NamedEntity;
import namedEntity.classification.entity.*;
import namedEntity.classification.subject.Unknown;
import namedEntity.classification.subject.*;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {


    private static Map<String, NamedEntityData> data = new HashMap<>();

    static {
        data.put("Microsoft", new NamedEntityData(EntityType.COMPANY, SubjectType.TECHNOLOGY));
        data.put("Apple", new NamedEntityData(EntityType.COMPANY, SubjectType.TECHNOLOGY));
        data.put("Google", new NamedEntityData(EntityType.COMPANY, SubjectType.TECHNOLOGY));
        data.put("Musk", new NamedEntityData(EntityType.PERSON, SubjectType.TECHNOLOGY));
        data.put("Biden", new NamedEntityData(EntityType.PERSON, SubjectType.POLITICS));
        data.put("Trump", new NamedEntityData(EntityType.PERSON, SubjectType.POLITICS));
        data.put("Messi", new NamedEntityData(EntityType.PERSON, SubjectType.SPORTS));
        data.put("Federer", new NamedEntityData(EntityType.PERSON, SubjectType.SPORTS));
        data.put("USA", new NamedEntityData(EntityType.COUNTRY, SubjectType.POLITICS));
        data.put("Russia", new NamedEntityData(EntityType.COUNTRY, SubjectType.POLITICS));
        data.put("Amazon", new NamedEntityData(EntityType.COMPANY, SubjectType.TECHNOLOGY));
        data.put("Tesla", new NamedEntityData(EntityType.COMPANY, SubjectType.TECHNOLOGY));
    }

    public static NamedEntityData getNamedEntityData(String name) {
        return data.get(name);
    }

    public static NamedEntity buildNamedEntity(String name, NamedEntityData data) {
        if (data != null) {
            switch (data.getEntityType()) {
                case PERSON -> {
                    return new Person(name, "");
                }
                case COMPANY -> {
                    return new Company(name);
                }
                case PLACE -> {
                    return new Place(name);
                }
                case UNKNOWN -> {
                    return new namedEntity.classification.entity.Unknown(name);
                }
                case COUNTRY -> {
                    return new Country(name);
                }
            }
        }
        return new namedEntity.classification.entity.Unknown(name);
    }

    public static Subject buildSubject(NamedEntityData data) {
        if (data != null) {
            switch (data.getSubjectType()) {
                case SPORTS -> {
                    return new Sports();
                }
                case CULTURE -> {
                    return new Culture();
                }
                case POLITICS -> {
                    return new Politics();
                }
                case TECHNOLOGY -> {
                    return new Technology();
                }
                case UNKNOWN -> {
                    return new Unknown();
                }
            }
        }
        return new Unknown();
    }
}
