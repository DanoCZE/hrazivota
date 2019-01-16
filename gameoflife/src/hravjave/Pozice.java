package hravjave;

public class Pozice {

    private int sirka;
    private int vyska;
    private int x;
    private int y;
    
    public Pozice(int w, int h, int x, int y) {
        this.sirka = w;
        this.vyska = h;
        this.x = x;
        this.y = y;
    }
    
    public Pozice(int x, int y) {
        this(600,600,x,y);
    }
       
    public Pozice(Playground pg) {
        this(new Nahodny(pg.getCol(),pg.getRow()).getX(), new Nahodny(pg.getCol(),pg.getRow()).getY());
    }
    
    public Boolean setX(int x) {
        if (x < this.sirka && x > 0) {
            this.x = x;
            return true;
        }
        return false;
    }

    public Boolean setY(int y) {
        if (y < this.vyska && y >0) {
            this.y = y;
            return true;
        }
        return false;
    }

    public Boolean setSirka(int sirka) {
        if(sirka > 0) {
            this.sirka = sirka;
            return true;
        }
        return false;
    }

    public Boolean setVyska(int vyska) {
        if(vyska > 0) {
            this.vyska = vyska;
            return true;
        }
        return false;
    }

    public int getSirka() {
        return sirka;
    }

    public int getVyska() {
        return vyska;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    
    
    
}
