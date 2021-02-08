package com.project.lingo.Data.dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FilterFileDao implements FilterFile {
    @Override
    public ArrayList<String> getList() {
        ArrayList<String> filteredArrayList = new ArrayList<>();
        try {
            Scanner input = new Scanner(new File("src/main/resources/static/basiswoorden-gekeurd-gefilterd.txt"));
            while (input.hasNextLine()) {
                String word = input.nextLine();
                filteredArrayList.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filteredArrayList;
    }
}
