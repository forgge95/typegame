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
import java.awt.*;

public class Typegame {

    private List<String> arr = new ArrayList<>();
    private ArrayList<Word> wordsArray = new ArrayList<>();
    private JFrame frame = new JFrame("TextArea");
    private JTextArea tArea = new JTextArea();
    //private JButton button = new JButton("Click");
    private JScrollPane pane = new JScrollPane(tArea);
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
            //System.out.println(wordsArray);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void prepareAndShowGUI()
    {
        Font font = new Font("Ariel", Font.BOLD, 20);
        Container container = frame.getContentPane();
        pane.setBackground(new Color(0,55,55));
        container.add(pane);
        tArea.setBackground(Color.DARK_GRAY);
        tArea.setForeground(Color.WHITE);
        tArea.setLineWrap(true);
        tArea.setWrapStyleWord(true) ;
        tArea.setFont(font);
        tArea.setMargin(new Insets(5,7,5,7));
        tArea.setEditable(false);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < 150; i++) {
            tArea.append(wordsArray.get(i) + " ");
        }
        frame.setVisible(true);
    }
}
