package com.project.wordGenerator.application;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class WordService implements IWordService{
    private IFilterFileService filterFileService;
    private Random rand = SecureRandom.getInstanceStrong();

    public WordService(IFilterFileService filterFileService) throws NoSuchAlgorithmException {
        this.filterFileService = filterFileService;
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
