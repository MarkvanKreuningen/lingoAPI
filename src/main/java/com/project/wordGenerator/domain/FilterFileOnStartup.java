package com.project.wordGenerator.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class FilterFileOnStartup {
    @PostConstruct
    public void filterFileOnStartup() throws IOException {
        File file = new File("src/main/resources/static/basiswoorden-gekeurd-gefilterd.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             Scanner input = new Scanner(new File("src/main/resources/static/basiswoorden-gekeurd.txt"))) {
            String pattern = "^[a-z]{5,7}+$";
            while (input.hasNextLine()) {
                String word = input.nextLine();
                if (Pattern.matches(pattern, word)) {
                    writer.append(word);
                    writer.append("\n");
                }
            }
        }

    }
}