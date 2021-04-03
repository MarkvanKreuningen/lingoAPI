package com.project.wordGenerator.application;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordService implements IWordService{
    private IFilterFileService filterFileService;

    public WordService(IFilterFileService filterFileService){
        this.filterFileService = filterFileService;
    }

    @Override
    public boolean validate(String word) {
        return filterFileService.getFilteredList().contains(word);
    }

    @Override
    public String getWord(int length) {
        List<String> list = filterFileService.getFilteredList();
        Random random = new Random();
        String word = "";
        while (true) {
            if (word.length() != length)
                word = list.get(random.nextInt(list.size()));
            else
                break;
        }
        return word;
    }

}
