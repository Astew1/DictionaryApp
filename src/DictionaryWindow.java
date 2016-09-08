import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 12/31/15.
 */
public class DictionaryWindow{

    private DictionaryTrie dict;
    private JTextField inputField;
    private JButton submitbutton, suggestWordsInConsole;
    private JTextArea outputField;

    public DictionaryWindow(JPanel pan, DictionaryTrie dict){
        this.dict = dict;

        ActionListener spellcheck = (ActionEvent e) -> updateOutputField(inputField.getText(), spellcheck(inputField.getText()));
        ActionListener suggest = (ActionEvent e) -> dict.printList(dict.suggestCompletions(inputField.getText()));

        inputField = new JTextField(20);
        inputField.setSize(100, 30);
        inputField.setLocation(100, 100);
        inputField.addActionListener(spellcheck);
        inputField.addActionListener(suggest);
        
        submitbutton = new JButton("Spellcheck");
        submitbutton.addActionListener(spellcheck);
        submitbutton.setLocation(210, 100);
        submitbutton.setSize(45, 15);

        suggestWordsInConsole = new JButton("Suggest Words In Console");
        suggestWordsInConsole.addActionListener(suggest);
        suggestWordsInConsole.setLocation(210, 200);
        suggestWordsInConsole.setSize(80, 15);

        outputField = new JTextArea(1, 30);
        outputField.setFocusable(false);
        outputField.setLocation(190, 140);



        pan.add(inputField);
        pan.add(submitbutton);
        pan.add(suggestWordsInConsole);
        pan.add(outputField);

    }

    public void draw(Graphics2D g2){

    }

    public JTextField getTextField(){return inputField;}

    public boolean spellcheck(String s){
        return dict.search(s.toLowerCase());
    }
    public void updateOutputField(String s, boolean word){
        if(word)
            outputField.setText("\"" + s + "\" is a word.");
        else
            outputField.setText("\"" + s + "\" is not a word.");
    }


}
