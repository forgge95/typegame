package com.forg.typegame;

import java.awt.Container;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

public class Typegame implements KeyListener{

    private List<String> arr = new ArrayList<>();
    private ArrayList<Word> wordsArray = new ArrayList<>();
    private ArrayList<Character> charArray = new ArrayList<>();

    private JFrame frame = new JFrame("TextArea");
    private DefaultStyledDocument doc = new DefaultStyledDocument(); 
    private JTextPane textPane = new JTextPane(doc);
    private SimpleAttributeSet attribs = new SimpleAttributeSet(); 
    private JTextArea textArea = new JTextArea();

    private int currentCharIndex = 0;

    //Initialisation method for GUI
    public void init() {
        try {
            arraySetUp();
            prepareAndShowGUI();
            while(true){
                textArea.requestFocusInWindow();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    //Setting up the GUI
    public void prepareAndShowGUI(){     
        addPaneComponents();
        frame.setSize(830, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void addPaneComponents(){
        //Setting up words in document for future display
        for (int i = 0; i < 150; i++) {
            try {
                doc.insertString(doc.getLength(), wordsArray.get(i) + " ", attribs);
            } catch (BadLocationException e) {
                System.out.println(e);
                System.exit(0);
            }
        }
        
        Font font = new Font("Ariel", Font.BOLD, 22);
        Container container = frame.getContentPane();
        
        container.add(textArea, BorderLayout.PAGE_END);
        container.add(textPane, BorderLayout.CENTER);

        //Setting up text area where input is read
        textArea.addKeyListener(this);
        textArea.setBackground(new Color(105,105,105));
        textArea.setForeground(new Color(105,105,105));
        textArea.setCaretColor(new Color(105,105,105));
        textArea.setSelectedTextColor(new Color(105,105,105));
        textArea.setSelectionColor(new Color(105,105,105));

        //Setting up text area where words are displayed
        textPane.setFont(font);
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(new Color(105,105,105));
        textPane.setEditable(false);
        textPane.setMargin(new Insets(5,7,5,7));

        StyledDocument styledDoc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        styledDoc.setParagraphAttributes(0, doc.getLength(), center, false);
        //TODO: Make a button to activate timer and start the game 
    }

    //Setting up the arrays that contain words and characters
    public void arraySetUp() throws IOException{
        Path path = Paths.get("src\\main\\java\\resources\\wordslist.txt");
        arr = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String word : arr) {
            if(word!=null && !word.isEmpty()){
                wordsArray.add(new Word(word.trim().toLowerCase()));
            }
        }
        Collections.shuffle(wordsArray);
        for (int i = 0; i < 150; i++) {
            for (char ch : wordsArray.get(i).getWordCharArray()) {
                charArray.add(ch);
            }
            charArray.add(' ');
        }
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){       
            if(currentCharIndex > 0) currentCharIndex--;
            attribs.addAttribute(StyleConstants.ColorConstants.Background,new Color(105,105,105));
            doc.setCharacterAttributes(currentCharIndex,1,attribs,true); 
        }else {
            if(e.getKeyChar() == charArray.get(currentCharIndex)){
                attribs.addAttribute(StyleConstants.ColorConstants.Background,Color.green);
                doc.setCharacterAttributes(currentCharIndex,1,attribs,true);
            } else{
                attribs.addAttribute(StyleConstants.ColorConstants.Background,Color.red);
                doc.setCharacterAttributes(currentCharIndex,1,attribs,true);
            }
            currentCharIndex++;
        }
    }
    public void pointsCounter(){
        //TODO: implement points counter that will display 
        //      number of errors and WPM at the end of the game
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
