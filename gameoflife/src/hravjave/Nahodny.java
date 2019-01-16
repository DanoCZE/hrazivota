package hravjave;

public class Nahodny {
    
    private int w;
    private int h;
    private int x;
    private int y;
    
    public Nahodny(int col, int row) {
        this.w = col;
        this.h = row;
        this.x = generateX();
        this.y = generateY();
    }
    
    private int generateX() {
        return (int)Math.floor(Math.random()*this.w+1);
    }
    
    private int generateY() {
        return (int)Math.floor(Math.random()*this.h+1);
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
