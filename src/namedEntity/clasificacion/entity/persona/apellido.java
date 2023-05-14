package namedEntity.clasificacion.entity.persona;

// import namedEntity.NamedEntity;
public class apellido extends persona{
    // forma canónica, origen
    private String forma_canonica;
    private String origen;
    public apellido(String name,String category, int frequency,String ID,String forma_canonica,String origen) { 
        super(name,category, frequency,ID);
        this.forma_canonica = forma_canonica;
        this.origen = origen;
    }
    public String getForma_canonica() {
        return forma_canonica;
    }
    public String getOrigen() {
        return origen;
    }
    

    
}
