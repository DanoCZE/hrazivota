package hravjave;

public class Cell {
    
    Playground pg;
    int status; // Status buňky - žije = true
    Boolean zmena; // Předchozí status buňky - změna = true
    int sila;
    int x;
    int y;
    
    public Cell() {
        this(new Playground(new MainWindow()), 0, 0,0,false);
    }
    
    public Cell(Playground pg, int status) {
        this(pg,status,0,0,false);
    }
    
    public Cell(Playground pg, int status, int x, int y) {
        this(pg,status,x,y,false);
    }
    
    public Cell(Playground pg, int status, int x, int y, Boolean zmena) {
        this.pg = pg;
        this.status = status;
        this.x = x;
        this.y = y;
        this.zmena = zmena;
    }

    public Playground getPg() {
        return this.pg;
    }
    
    public void setPg(Playground pg) {
        this.pg = pg;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getSila() {
        return this.sila;
    }

    public Boolean getZmena() {
        return zmena;
    }

    public void setZmena(Boolean zmena) {
        this.zmena = zmena;
    }
    
}
