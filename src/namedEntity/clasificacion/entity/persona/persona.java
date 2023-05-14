package namedEntity.clasificacion.entity.persona;

import namedEntity.NamedEntity;

public class persona extends NamedEntity {
    private String  ID;
    
    public persona(String name,String category, int frequency,String ID) { 

        super(name,category, frequency);
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
    
}
