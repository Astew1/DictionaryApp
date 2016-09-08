
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Alex on 12/31/15.
 */
public class Main extends JPanel implements MouseListener {

    DictionaryTrie dict;
    DictionaryWindow window;
    JTextField tfield;

    public static void main(String[] args) {

//        JFrame frame = new JFrame("DictionaryApp");
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(1000, 1000));
//
//        JPanel pan = new Main();
//
//
//        frame.add(pan);
//
//        frame.pack();
//        frame.setVisible(true);
        new Main();



    }

    public Main(){

        this.dict = new DictionaryTrie("/usr/share/dict/words");
        JFrame fme = new JFrame("DictionaryApp");
        JPanel jpn = new JPanel();
        fme.setPreferredSize(new Dimension(700, 700));
        fme.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

///*          --MOVED to DictionaryTrie constructor--
//
////        int count = 0;
//        File dictionary = new File("/usr/share/dict/words");
//        try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                dict.insert(line);
////                count++;
//            }
//        }
//        catch(Exception e){}
////        System.out.println(count);
//    */

        window = new DictionaryWindow(jpn, dict);

        fme.add(jpn);
        fme.pack();
        fme.setVisible(true);
//        fme.requestFocusInWindow();

        tfield = window.getTextField();

        Timer t = new Timer(500, (ActionEvent e)->{
            revalidate();
            repaint();
        });
        t.start();

    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;




    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
