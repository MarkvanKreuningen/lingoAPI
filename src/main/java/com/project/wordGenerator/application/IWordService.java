package com.project.wordGenerator.application;

import org.springframework.stereotype.Service;

@Service
public interface IWordService {
    boolean validate(String word);
}
