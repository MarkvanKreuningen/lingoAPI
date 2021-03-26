package com.project.lingo.Presentation.dto;

import com.project.lingo.Domain.Feedback;

import java.sql.Timestamp;

public class AttemptDto {
    private int turn;
    private long gameId;
    private String feedback;
    private Timestamp timestamp;

    public AttemptDto(int turn, long gameId, String feedback, Timestamp timestamp) {
        this.turn = turn;
        this.gameId = gameId;
        this.feedback = feedback;
        this.timestamp = timestamp;
    }
}
