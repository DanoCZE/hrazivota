package hravjave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Playground extends JPanel implements ActionListener, KeyListener, MouseListener {
    
    MainWindow window;
    protected Timer timer;
    private int col, row, w, s;
    private Color zive;
    private Color mrtve;
    private Color zmena_zive;
    private Color zmena_mrtve;
    private Boolean presetEn;
    private int preset;
    private Boolean presetFail;
    private Boolean drawing;
    private Boolean velikost;
    
    ArrayList<ArrayList<Cell>> bunky;
    
    public Playground(MainWindow window, int w, int s, Color z, Color m, Color zz, Color zm) {
        this.window = window;
        this.w = w;
        this.s = s;
        this.zive = z;
        this.mrtve = m;
        this.zmena_zive = zz;
        this.zmena_mrtve = zm;
        this.presetEn = false;
        this.preset = 0;
        this.presetFail = false;
        this.drawing = false;
        this.velikost = false;
        
        this.setSize(new Dimension(600, 600));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addMouseListener(this);
        timer = new Timer(this.s, this);
        timer.start();
        this.col = this.getWidth()/this.w;
        this.row = this.getHeight()/this.w;
        if (this.presetEn) {
            setPres(this.preset);
        } else {
            generate();
        }
    }
    
    public Playground(MainWindow window, int w, int s) {
        this(window, w, s, Color.BLACK, Color.WHITE, Color.DARK_GRAY, Color.LIGHT_GRAY);
    }
    
    public Playground(MainWindow window, Color z, Color m, Color zz, Color zm) {
        this(window, 10, 100, z,m,zz,zm);
    }

    public Playground(MainWindow window) {
        this(window, 10, 100, Color.BLACK, Color.WHITE, Color.DARK_GRAY, Color.LIGHT_GRAY);
    }
    
    public int getCol() {
        return this.col;
    }
    
    
    public int getRow() {
        return this.row;
    }
    
    public int getW() {
        return this.w;
    }
    
    public int getS() {
        return this.s;
    }
    
    public Color getZ() {
        return this.zive;
    }
    
    public Color getM() {
        return this.mrtve;
    }
    
    public Color getZZ() {
        return this.zmena_zive;
    }
    
    public Color getZM() {
        return this.zmena_mrtve;
    }
    
    public Boolean getDrawing() {
        return this.drawing;
    }
    
    public Boolean getVelikost() {
        return this.velikost;
    }
    
    public void setVelikost(Boolean v) {
        this.velikost = v;
    }
    
    public void setDrawing(Boolean on) {
        this.drawing = on;
        for(int i=0;i<col;i++) {
            for(int j=0;j<row;j++) {
                bunky.get(i).get(j).setZmena(false);
            }
        }
        this.repaint();
    }
    
    public void setPresetEn(Boolean on) {
        this.presetEn = on;
    }
    
    public Boolean getPresetEn() {
        return this.presetEn;
    }
    
    public void setZ(Color z) {
        this.zive = z;
    }
    public void setM(Color m) {
        this.mrtve = m;
    }
    
    public void setZZ(Color zz) {
        this.zmena_zive = zz;
    }
    
    public void setZM(Color zm) {
        this.zmena_mrtve = zm;
    }
    
    public void setW(int w) {
        if (w>=5 && w<=35) {
            this.w = w;
        }
        this.col = this.getWidth()/this.w;
        this.row = this.getHeight()/this.w;
        if (this.presetEn) {
            setPres(this.preset);
        } else if(this.drawing) {
            clear();
        }else {
            generate();
        }
    }
    public void setS(int s) {
        if (s>=100 && s<=1900) {
            this.s = s;
            this.timer.setDelay(this.s);
        }
    }
    
    private void getLive() {
        int celk=0;
        int aktivnich=0;
        for(int i=0;i<col;i++) {
            for(int j=0;j<row;j++) {
                if(bunky.get(i).get(j).getStatus() == 1) {
                    celk++;
                }
                if(bunky.get(i).get(j).getZmena()) {
                    aktivnich++;
                }
            }
        }
        window.setText(celk, aktivnich, this.presetFail);
    }
    
    public void generate() {
        this.presetEn = false;
        bunky = new ArrayList<>();
        for (int i=0;i<col;i++) {
            this.bunky.add(new ArrayList<>());
            for (int j=0;j<row;j++) {
                if (Math.random()*2 > 1) {
                    //new Cell(this,new Pozice(i,j), true)>
                    this.bunky.get(i).add(new Cell(this, 1, i, j));
                } else {
                    this.bunky.get(i).add(new Cell(this, 0, i, j));
                }
                
            }
        }
        this.repaint();
    }
    
    public void setPres(int id) {
        this.preset = id;
        if (this.presetEn == true) {
            this.clear();

            // Soubor

            String fileName = null;
            int j=0;

            switch(this.preset) {
                case 0: 
                    fileName = "blinker.txt";
                    break;
                case 1: 
                    fileName = "toad.txt";
                    break;
                case 2: 
                    fileName = "beacon.txt";
                    break;
                case 3: 
                    fileName = "pulsar.txt";
                    break;
                case 4: 
                    fileName = "gun.txt";
                    break;
                case 5: 
                    fileName = "glider.txt";
                    break;
                case 6: 
                    fileName = "lwss.txt";
                    break;
            }
            String line = null;

            try {
                FileReader fileReader = new FileReader(fileName);

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                    for(int i = 0; i < line.length(); i++)
                    {
                       char c = line.charAt(i);
                       if (i<col&&j<row) {
                           this.presetFail = false;
                            bunky.get(i).get(j).setStatus((c == '1')?1:0);
                       } else {
                           this.presetFail = true;
                       }

                    }
                    j++;
                }   

                bufferedReader.close();         
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file '" + 
                    fileName + "'");                
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + fileName + "'");
            }
        }
        this.repaint();
    }
    
    public void ulozit() {
        JFileChooser fileChooser = new JFileChooser();
    int retval = fileChooser.showSaveDialog(this);
    if (retval == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      if (file == null) {
        return;
      }
      if (!file.getName().toLowerCase().endsWith(".txt")) {
        file = new File(file.getParentFile(), file.getName() + ".txt");
      }
      try {
        FileWriter data = new FileWriter(fileChooser.getSelectedFile(),false);
        for (int i=0;i<col;i++) {
            for(int j=0;j<row;j++) {
                data.append((bunky.get(i).get(j).getStatus() >0)?'1':'0');
            }
            data.append('\n');
        }
        data.flush();
        data.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    }
    
    public void nacist() {
        this.clear();
        String line = null;
        int j=0;
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File soubor = fileChooser.getSelectedFile();
            try {
                FileReader fileReader = new FileReader(soubor);

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                    for(int i = 0; i < line.length(); i++)
                    {
                       char c = line.charAt(i);
                       if (i<col&&j<row) {
                           this.presetFail = false;
                            bunky.get(i).get(j).setStatus((c == '1')?1:0);
                       } else {
                           this.presetFail = true;
                       }

                    }
                    j++;
                }   

                bufferedReader.close();         
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                    "Unable to open file '" + 
                    soubor.getName() + "'");                
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + soubor.getName() + "'");
            }
        }
        this.repaint();
    }
    
    public void nextGen() {
        ArrayList<ArrayList<Cell>> dalsi = copy(this.bunky);
        
        timer.setDelay(this.s);
        
        for(int c=0;c<col;c++) {
            for (int r=0;r<row;r++) {
                int sousedu=0;
                for (int x=-1;x<=1;x++) {
                    for (int y=-1;y<=1;y++) {
                        sousedu += bunky.get((c+x+col)%col).get((r+y+row)%row).getStatus();
                    }
                }
                
                sousedu -= bunky.get(c).get(r).getStatus();
                
                if ((bunky.get(c).get(r).getStatus() > 0) && (sousedu < 2)) {
                    dalsi.get(c).get(r).setStatus(0);
                    dalsi.get(c).get(r).setZmena(true);
                } else if ((bunky.get(c).get(r).getStatus() > 0) && (sousedu > 3)) {
                    dalsi.get(c).get(r).setStatus(0);
                    dalsi.get(c).get(r).setZmena(true);
                } else if ((bunky.get(c).get(r).getStatus() <= 0) && (sousedu == 3)) {
                    dalsi.get(c).get(r).setStatus(1);
                    dalsi.get(c).get(r).setZmena(true);
                } else {
                    dalsi.get(c).get(r).setZmena(false);
                }
                
            }
        }
        
        this.bunky = copy(dalsi);
        
        getLive();
        this.repaint();
    }
    
    public void clear() {
        bunky = new ArrayList<>();
        for (int i=0;i<col;i++) {
            this.bunky.add(new ArrayList<>());
            for (int j=0;j<row;j++) {
                this.bunky.get(i).add(new Cell(this, 0, i, j));
            }
        }
        this.repaint();
    }
    
    public ArrayList<ArrayList<Cell>> copy(ArrayList<ArrayList<Cell>> origo) {
        ArrayList<ArrayList<Cell>> novy = new ArrayList<>();
        for (int i=0;i<col;i++) {
            novy.add(new ArrayList<>());
            for (int j=0;j<row;j++) {
                novy.get(i).add(new Cell(this, origo.get(i).get(j).getStatus(), i, j, origo.get(i).get(j).getZmena()));
            }
        }
        return novy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextGen();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.drawing) {
            
            int x =  e.getX()/w;
            int y = e.getY()/w;
            if(e.getButton() == 1) {
                if (this.velikost) {
                    for (int i=-1;i<=1;i++) {
                        for (int j=-1;j<=1;j++) {
                            bunky.get((x+i+col)%col).get((y+j+row)%row).setStatus(1);
                        }
                    }
                } else {
                    bunky.get(x).get(y).setStatus(1);
                }
            }
            if(e.getButton() == 3) {
                
                if (this.velikost) {
                    for (int i=-1;i<=1;i++) {
                        for (int j=-1;j<=1;j++) {
                            bunky.get((x+i+col)%col).get((y+j+row)%row).setStatus(0);
                        }
                    }
                } else {
                    bunky.get(x).get(y).setStatus(0);
                }
                
            }
            if(e.getButton() == 2) {
                this.velikost = !this.velikost;
                window.setVel(this.velikost);
            }
            
            this.repaint();
            
        } else {
            if(timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
    
    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr); //To change body of generated methods, choose Tools | Templates.  
        Graphics2D g = (Graphics2D) gr;
        Area area;
        for(int i=0;i<col;i++) {
            for(int j=0;j<row;j++) {
                if(bunky.get(i).get(j).getStatus() > 0) {
                    if (bunky.get(i).get(j).getZmena() == true) {
                        g.setColor(this.zmena_zive);
                    } else {
                        g.setColor(this.zive);
                    }
                } else {
                    if (bunky.get(i).get(j).getZmena() == true) {
                        g.setColor(this.zmena_mrtve);
                    } else {
                        g.setColor(this.mrtve);
                    }
                }
                area = new Area(
                            new Rectangle2D.Double(
                                (i)*w,
                                (j)*w,
                                w*0.8, w*0.8));
                g.fill(area);
                
            }
        }
        
    }
    
    public void startStop() {
        if(timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }
    
}
