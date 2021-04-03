package com.project.wordGenerator.application;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class WordService implements IWordService{
    private IFilterFileService filterFileService;
    private Random rand;

    public WordService(IFilterFileService filterFileService) {
        this.filterFileService = filterFileService;
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean validate(String word) {
        return filterFileService.getFilteredList().contains(word);
    }

    @Override
    public String getWord(int length) {
        List<String> list = filterFileService.getFilteredList();
        String word = "";
        while (true) {
            if (word.length() != length)
                word = list.get(this.rand.nextInt(list.size()));
            else
                break;
        }
        return word;
    }

}
