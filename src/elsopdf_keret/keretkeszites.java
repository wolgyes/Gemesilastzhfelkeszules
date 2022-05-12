package elsopdf_keret;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class keretkeszites {
    public static void main(String[] args) {
        Keret k = new Keret();

        if (args.length == 0) {
            k.Ablak(false);
        }
        else {
            if (args[0].equals("lista") ){
                k.Ablak(true);

            } else if (args[0].equals("tabla")){
                k.Ablak(false);
            }
            else {
                k.Ablak(false);
            }
        }

    }
}
class Keret extends JFrame{
    static boolean lista;
    public void Ablak(boolean lista){
        Keret.lista = lista;

        setTitle("Az első ablakom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<String> sorok = new ArrayList<>();

        sorok.add("as234da - s31235da - sd552a");
        sorok.add("as6da - s3da12sda - sda23");
        sorok.add("as324da - s3asd3da - sd2333a");
        sorok.add("a6543sda - s3asdeda - sda55");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("asda566 - s3a613a - s6da");
        sorok.add("as345da - s3ddcda - s123da");
        sorok.add("as75345da - s3123da - sda5123");

        DefaultListModel<String> lm = new DefaultListModel<>();
        JList<String> jl = new JList<>(lm);
        JScrollPane scroll = new JScrollPane(jl);

        ArrayList<JLabel> minisorlist = new ArrayList<>();

        for (int i = 0; i < sorok.size(); i++) {

            StringTokenizer token = new StringTokenizer(sorok.get(i), "-");

            minisorlist.add(new JLabel(" " + token.nextElement().toString()));
            minisorlist.add(new JLabel(token.nextElement().toString()));
            minisorlist.add(new JLabel(token.nextElement().toString()));

            int num = 1;
            for (JLabel label : minisorlist) {
                label.setOpaque(true);
                label.setBackground(Color.getHSBColor(i*10,i*30,i*40));

                if (num == 1){
                    label.setHorizontalAlignment(SwingConstants.LEFT);

                } else if (num == 2) {
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                else {
                    label.setHorizontalAlignment(SwingConstants.RIGHT);
                }
                num ++;

                if (lista){
                    lm.addElement(label.getText());
                    add(scroll);
                }
                else{
                    add(label);
                }
            }
            minisorlist.clear();

        }
        if (lista){
            scroll.setPreferredSize(new Dimension(300, 200));
        }
        else{
            setLayout(new GridLayout(sorok.size(), 3, 30, 0));
            pack();
        }


        setSize(700, 360);
        setVisible(true);

    }
}