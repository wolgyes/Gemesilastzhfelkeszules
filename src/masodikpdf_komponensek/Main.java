package masodikpdf_komponensek;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class Main {
    static ArrayList<App> apps = new ArrayList<>();

    public static void main(String[] args) {
        App base_app = new App();
        apps.add(base_app);

        GombokApp gombokApp = new GombokApp();
        apps.add(gombokApp);

        TippelosJatek tippelosJatek = new TippelosJatek();
        apps.add(tippelosJatek);

        PenzValto penzValto = new PenzValto();
        apps.add(penzValto);

        SzinesApp szinesApp = new SzinesApp();
        apps.add(szinesApp);

        RajzolosApp rajzolosApp = new RajzolosApp();
        apps.add(rajzolosApp);

        KepMegjelenites kepMegjelenites = new KepMegjelenites();
        apps.add(kepMegjelenites);


        for (App app : apps) {
            if (app.getClass().getName().split("\\.")[1].equals(args[0])) {
                try {
                    app.setup();
                } catch (Exception ignored) {
                }
            }

        }
    }
}

class App {
    JFrame jFrame;

    public App() {
        jFrame = new JFrame();
    }

    void setup() {
        setDefaults("Ures app", new FlowLayout(), new int[]{300,70});
    }

    public void setDefaults(String title, LayoutManager layout, int[] width_height) {
        jFrame.setTitle(title);
        jFrame.setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width_height[0], width_height[1]);
        jFrame.setVisible(true);
    }
}
class GombokApp extends App implements ActionListener {

    @Override
    public void setup() {
        setDefaults("Penzvalto", new FlowLayout(), new int[]{400, 400});
        jFrame.add(new Button("asd"));
        jFrame.add(new Button("cica"));
        jFrame.add(new Button("cica2"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
class TippelosJatek extends App implements ActionListener {
    Button tipp_bt = new Button("Tippeles");
    TextField tipp_tb = new TextField("Tipp helye");
    Label tipp_lb = new Label("Usson be valami szamot 1-5 kozott");

    @Override
    public void setup() {
        setDefaults("Penzvalto", new FlowLayout(), new int[]{400, 400});

        jFrame.add(tipp_bt);
        jFrame.add(tipp_tb);
        jFrame.add(tipp_lb);

        jFrame.pack();

        //Eseményfigyelők
        tipp_bt.addActionListener(this);
    }

    public void tippButtonPushed() {
        int random = new Random().nextInt(5) + 1;
        try {
            if (random == Integer.parseInt(tipp_tb.getText())) {
                System.out.println("Sikeres tipp");
                System.out.println("A szam: " + random);
            }
        } catch (Exception e) {
            System.out.println("Nem szamot utott be!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(tipp_bt)) {
            tippButtonPushed();
        }
    }
}
class PenzValto extends App implements KeyListener, ItemListener, ActionListener {

    TextField valto_tb = new TextField("                         ");
    Label valto_lb = new Label("Valtas: ");
    JComboBox<String> valto_penznem_cb = new JComboBox<>();
    Label valto_res_lb = new Label(" Result                                        ");


    NumberFormat france = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    NumberFormat us = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public void setup() {
        setDefaults("Penzvalto", new FlowLayout(), new int[]{400, 400});

        jFrame.add(valto_lb);
        jFrame.add(valto_tb);
        jFrame.add(valto_penznem_cb);
        jFrame.add(valto_res_lb);

        valto_tb.setBounds(23,321,500,10);
        valto_tb.setText("");

        valto_penznem_cb.addItem(france.getCurrency().getCurrencyCode());
        valto_penznem_cb.addItem(us.getCurrency().getCurrencyCode());

        jFrame.pack();

        //Eseményfigyelők
        valto_penznem_cb.addActionListener(this);
        valto_tb.addKeyListener(this);
    }

    public void penzvaltas() {
        try {
            if (valto_penznem_cb.getSelectedItem() == france.getCurrency().getCurrencyCode()) {
                valto_res_lb.setText(" = " + france.format(Double.parseDouble(valto_tb.getText()) / 380));
            } else if (valto_penznem_cb.getSelectedItem() == us.getCurrency().getCurrencyCode()) {
                valto_res_lb.setText(" = " + us.format(Double.parseDouble(valto_tb.getText()) / 360));
            }
        } catch (Exception ignored) {}
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource().equals(valto_tb) || e.getSource().equals(valto_penznem_cb)) {
            penzvaltas();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(valto_tb) || e.getSource().equals(valto_penznem_cb)) {
            penzvaltas();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(valto_tb) || e.getSource().equals(valto_penznem_cb)) {
            penzvaltas();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(valto_tb) || e.getSource().equals(valto_penznem_cb)) {
            penzvaltas();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(valto_tb) || e.getSource().equals(valto_penznem_cb)) {
            penzvaltas();
        }
    }
}
class SzinesApp extends App implements ActionListener {
    Button piros_bt = new Button("Piros");
    Button sarga_bt = new Button("Sarga");
    Button zold_bt = new Button("Zold");

    Label szenizenivalo_label = new Label("            ");

    Button exit_bt = new Button("Exit");
    @Override
    public void setup() {
        setDefaults("Penzvalto", new FlowLayout(), new int[]{400, 400});
        jFrame.add(piros_bt);
        jFrame.add(sarga_bt);
        jFrame.add(zold_bt);
        jFrame.add(exit_bt);

        jFrame.add(szenizenivalo_label);

        //Esemenykezelo
        piros_bt.addActionListener(this);
        sarga_bt.addActionListener(this);
        zold_bt.addActionListener(this);
        exit_bt.addActionListener(this);

        jFrame.pack();
    }
    public void szinez(Color color){
        szenizenivalo_label.setBackground(color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (piros_bt.equals(source)) {
            szinez(Color.RED);
        } else if (sarga_bt.equals(source)) {
            szinez(Color.YELLOW);
        } else if (zold_bt.equals(source)) {
            szinez(Color.GREEN);
        } else if (exit_bt.equals(source)) {
            System.exit(0);
        }
    }
}
class RajzolosApp extends App {

    class GimesiRajzok extends JPanel {

        @Override
        protected void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            gr.setColor( Color.yellow );
            gr.fillRect( 100, 100, 200, 150 );
            gr.setColor( Color.blue );
            for(int i=1; i<10; i++)
                gr.drawRect(100, 100, i*20, i*10 );
            gr.setColor( Color.green );
            gr.drawOval(300, 300, 100, 100);gr.setColor( Color.red );
            gr.drawLine(250, 250, 100, 500);

        }

        //so our panel is the corerct size when pack() is called on Jframe
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(jFrame.getWidth(), jFrame.getHeight());
        }
    }
    class ViktorSzovegRajz extends JPanel {

        @Override
        protected void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            gr.setColor(Color.ORANGE);
            gr.setFont(new Font ("Courier New", Font.BOLD | Font.ITALIC, 24));
            gr.drawString("Viktor", 300, 300);
        }

        //so our panel is the corerct size when pack() is called on Jframe
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(jFrame.getWidth(), jFrame.getHeight());
        }
    }
    @Override
    public void setup() {
        setDefaults("Penzvalto", new FlowLayout(), new int[]{500, 500});
        jFrame.setLocation(1200,200);


        GimesiRajzok rajz1 = new GimesiRajzok();
        jFrame.add(rajz1);
        jFrame.remove(rajz1);
        jFrame.add(new ViktorSzovegRajz());
    }
}
class KepMegjelenites extends App implements ActionListener {

    boolean vertical = true;
    JPanel totalpanel = new JPanel();
    Button button = new Button("Change");
    public JLabel getImage(String imagePath){
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return new JLabel(new ImageIcon(resize(image, 200, 200)));
        } catch (Exception ignored) {}
        return null;
    }

    public void setVertical(){
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            panel.add(getImage("cicas/cica" + (i+1) + ".jpg"));
            panel.setSize(200,200);
            totalpanel.add(panel);
            panel.setLocation(0,i*200);
        }

        jFrame.setContentPane(totalpanel);
        setDefaults("KepAllo", null, new int[]{200,700});
        button.setLocation(60,610);
    }

    private void setHorizontal() {
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            panel.add(getImage("cicas/cica" + (i+1) + ".jpg"));
            panel.setSize(200,200);
            totalpanel.add(panel);
            panel.setLocation(i*200,0);
        }

        jFrame.setContentPane(totalpanel);
        setDefaults("KepFekvo", null, new int[]{700,220});
        button.setLocation(610,60);
    }

    @Override
    public void setup() {
        jFrame.setLocation(600, 200);
        totalpanel.setLayout(null);
        totalpanel.setLocation(0,0);

        setVertical();

        setDefaults("KepAllo", null, new int[]{200,700});

        jFrame.add(button);
        button.setSize(80,40);
        button.setLocation(60,610);
        button.addActionListener(this);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vertical){
            vertical = false;
            setHorizontal();
        }
        else {
            vertical = true;
            setVertical();
        }
    }


}






