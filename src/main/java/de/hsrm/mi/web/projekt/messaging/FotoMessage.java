package de.hsrm.mi.web.projekt.messaging;



public class FotoMessage {
    public static final String FOTO_GESPEICHERT = "fotoGespeichert"; 
    public static final String FOTO_GELOESCHT = "fotoGeloescht";
    private String operation;
    private long id;
    public FotoMessage() {}
    public FotoMessage(String op, long id) { 
        this.operation = op;
        this.id = id;
    }
        
    public void setOperation(String op){
        this.operation = op;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getOperation(){
        return this.operation;
    }
    public long getId(){
        return this.id;
    }


    @Override
    public String toString() {
    return FOTO_GESPEICHERT+ FOTO_GELOESCHT+ " " + operation +" "+id;
}
    

    
}
