package namedEntity.clasificacion.multiple;

import namedEntity.clasificacion.entity.Evento;
import namedEntity.clasificacion.tema.cultura.Cine;

public class EventoconCine extends Evento implements Cine{
    public EventoconCine(String name,String category, int frequency,String forma_canonica,String fecha,String recurrente){
        super(name,category, frequency,forma_canonica,fecha,recurrente);
    }
        
    @Override
    public String getCultura() { 
        return "Cine";
    }
    @Override
    public String getTema() {
        return "Cine";
    }
    @Override
    public String getCine() {
        return "Evento";
    }
    

}
    


