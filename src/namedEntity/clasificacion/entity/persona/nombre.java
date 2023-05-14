package namedEntity.clasificacion.entity.persona;

public class nombre extends persona{
    // forma canónica, origen, formas alternativas
    private String forma_canonica;
    private String origen;
    private String formas_alternativas;
    public nombre(String name,String category, int frequency,String ID,String forma_canonica,String origen,String formas_alternativas) { 
        super(name,category, frequency,ID);
        this.forma_canonica = forma_canonica;
        this.origen = origen;
        this.formas_alternativas = formas_alternativas;
    }
    public String getForma_canonica() {
        return forma_canonica;
    }
    public String getOrigen() {
        return origen;
    }
    public String getFormas_alternativas() {
        return formas_alternativas;
    }
    
}
