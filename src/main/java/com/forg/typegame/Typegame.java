package com.forg.typegame;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Typegame {

    List<String> arr = new ArrayList<>();
    ArrayList<Word> wordsArray = new ArrayList<>();
   public void init() {
    try {
        Path path = Paths.get("src\\main\\java\\resources\\wordslist.txt");
        arr = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String word : arr) {
            if(word!=null && !word.isEmpty()){
                wordsArray.add(new Word(word.trim().toLowerCase()));
            }
        }
        System.out.println(wordsArray);        
    } catch (Exception e) {
        System.out.println(e);
    }
   } 
}
