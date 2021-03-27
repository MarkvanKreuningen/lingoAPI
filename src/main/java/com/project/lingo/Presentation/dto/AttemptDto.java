package com.project.lingo.Presentation.dto;

import com.project.lingo.Domain.Feedback;

import java.sql.Timestamp;

//Last update 27-03-2021
//Added getters and setters for exception :
//com.fasterxml.jackson.databind.JsonMappingException:
//No serializer found for class dtos.MyDtoNoAccessors
//and no properties discovered to create BeanSerializer
//(to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
//solution from:https://www.baeldung.com/jackson-jsonmappingexception
public class AttemptDto {
    private int turn;
    private long gameId;
    private String feedback;
    private Timestamp timestamp;
    private int round;

    public AttemptDto(int turn, long gameId, String feedback, Timestamp timestamp, int round) {
        this.turn = turn;
        this.gameId = gameId;
        this.feedback = feedback;
        this.timestamp = timestamp;
        this.round = round;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
