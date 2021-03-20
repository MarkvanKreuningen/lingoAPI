package com.project.lingo.Data.dao;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class FilterFileOnStartup {
    @PostConstruct
    public void filterFileOnStartup() {
        try {
            Scanner input = new Scanner(new File("src/main/resources/static/basiswoorden-gekeurd.txt"));
            File file = new File("src/main/resources/static/basiswoorden-gekeurd-gefilterd.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String pattern = "^\\p{Alpha}{5,11}";
            while (input.hasNextLine()) {
                String word = input.nextLine();
                if (Pattern.matches(pattern, word)) {
                    writer.append(word);
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}