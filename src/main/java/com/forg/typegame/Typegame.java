package com.forg.typegame;

import java.awt.Container;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
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

    public void init() {
        
        try {
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
            prepareAndShowGUI();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void prepareAndShowGUI(){
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

        textArea.addKeyListener(this);
        textArea.setBackground(new Color(255, 0, 102));
        textArea.setForeground(new Color(255, 0, 102));
        textArea.setCaretColor(new Color(255, 0, 102));

        textPane.setFont(font);
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(new Color(255, 0, 102));
        textPane.setEditable(false);
        textPane.setMargin(new Insets(5,7,5,7));

        frame.setSize(830, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        textArea.grabFocus();

    }

    public void addAttribute(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){       
            if(currentCharIndex > 0) currentCharIndex--;
            attribs.addAttribute(StyleConstants.ColorConstants.Foreground,Color.white);
            doc.setCharacterAttributes(currentCharIndex,1,attribs,true); 
        }else{

        if(e.getKeyChar() == charArray.get(currentCharIndex)){
            attribs.addAttribute(StyleConstants.ColorConstants.Foreground,Color.green);
            doc.setCharacterAttributes(currentCharIndex,1,attribs,true); 
        } else{
            attribs.addAttribute(StyleConstants.ColorConstants.Foreground,Color.red);
            doc.setCharacterAttributes(currentCharIndex,1,attribs,true);
        }
        currentCharIndex++;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
