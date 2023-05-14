package namedEntity.clasificacion.multiple;

import namedEntity.clasificacion.entity.persona.Persona;
import namedEntity.clasificacion.tema.cultura.Musica;

public class PersonaconMusica extends Persona implements Musica {
    public PersonaconMusica(String name ,String category, int frequency,String ID) 
    {
        super(name,category, frequency,ID);
      
    }

    @Override
    public String getCultura() {
       return "Musica";
    }

    @Override
    public String getTema() {
        return "Musica";
    }

    @Override
    public String getMusica() {
       return "Persona";
    }
    
    
}
