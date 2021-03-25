package com.project.lingo.Presentation.dto;

import com.project.lingo.Domain.Feedback;

import java.sql.Timestamp;

public class AttemptDto {
    private long gameId;
    private char firstLetter;
    private int lengthWord;
    private Timestamp created;

    public AttemptDto(long gameId, char firstLetter, int lengthWord) {
        this.gameId = gameId;
        this.firstLetter = firstLetter;
        this.lengthWord = lengthWord;
        this.created = new Timestamp(System.currentTimeMillis());
    }

}
