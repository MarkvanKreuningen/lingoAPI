package com.project.wordGenerator.application;

import org.springframework.stereotype.Service;

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
}
