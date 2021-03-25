package com.project.lingo.Presentation.dto;

import java.sql.Timestamp;

public class WordDto {
    private long gameId;
    private char firstLetter;
    private int lengthWord;
    private Timestamp created;

    public WordDto(long gameId, char firstLetter, int lengthWord) {
        this.gameId = gameId;
        this.firstLetter = firstLetter;
        this.lengthWord = lengthWord;
        this.created = new Timestamp(System.currentTimeMillis());
    }
}
