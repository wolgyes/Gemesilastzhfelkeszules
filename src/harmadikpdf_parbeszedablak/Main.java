package harmadikpdf_parbeszedablak;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static ArrayList<App> apps = new ArrayList<>();

    public static void main(String[] args) {
        App base_app = new App();
        apps.add(base_app);

        ColorTest colorTest = new ColorTest();
        apps.add(colorTest);

        FileTest fileTest = new FileTest();
        apps.add(fileTest);

        for (App app : apps) {
            System.out.println();
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

class ColorTest extends App implements ActionListener {
    private JButton btszin = new JButton("Szín kiválasztása");
    @Override
    public void setup (){
        jFrame.add(btszin);
        setDefaults("Színteszt", new FlowLayout(), new int[]{400,150});
        btszin.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Color color = JColorChooser.showDialog(jFrame, "A gomb színe", btszin.getBackground());
        if(color != null)btszin.setBackground(color);
    }
}

class FileTest extends App implements ActionListener{
    private JButton bt = new JButton("File kiválasztása");
    private JFileChooser fc = new JFileChooser();
    private JLabel lbDir = new JLabel("Path: ");
    private JLabel lbFile = new JLabel("File: ");
    private JLabel lbRelDir = new JLabel("RelPath: ");
    @Override
    public void setup(){

        jFrame.add(bt);
        jFrame.add(lbDir);
        jFrame.add(lbRelDir);
        jFrame.add(lbFile);

        bt.setBounds(10, 1, 100, 20);
        lbDir.setBounds(10, 50, 800, 20);
        lbRelDir.setBounds(10, 70, 800, 20);
        lbFile.setBounds(10, 90, 800, 20);

        setDefaults("Fájl kiválasztás", null, new int[]{400,150});

        bt.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt)
            if(fc.showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
                File sf = fc.getSelectedFile();
                if(sf != null){

                    lbDir.setText("Path: " + fc.getCurrentDirectory().getAbsolutePath());
                    lbRelDir.setText("Realtive path: " + fc.getCurrentDirectory().getPath());
                    lbFile.setText("File: " + sf.getName());
                }
            }
    }
}