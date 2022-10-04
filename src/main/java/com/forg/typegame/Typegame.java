package com.forg.typegame;

import java.awt.Container;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;

public class Typegame {

    private List<String> arr = new ArrayList<>();
    private ArrayList<Word> wordsArray = new ArrayList<>();
    private JFrame frame = new JFrame("TextArea");
    private DefaultStyledDocument doc = new DefaultStyledDocument(); 
    private JTextPane textPane = new JTextPane(doc);
    private SimpleAttributeSet attribs = new SimpleAttributeSet(); 

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
                System.exit(i);
            }
        }
        Font font = new Font("Ariel", Font.BOLD, 22);
        Container container = frame.getContentPane();
        container.add(textPane);
        attribs.addAttribute(StyleConstants.ColorConstants.Foreground,Color.black); 
        textPane.setFont(font);
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(new Color(255, 0, 102));
        textPane.setEditable(false);
        textPane.setMargin(new Insets(5,7,5,7));
        frame.setSize(830, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //doc.setCharacterAttributes(4,6,attribs,true); 
    }
}
