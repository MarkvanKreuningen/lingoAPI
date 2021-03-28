package com.project.lingo.Presentation.dto;

import java.sql.Timestamp;

//Last update 27-03-2021
//Added getters and setters for exception :
//com.fasterxml.jackson.databind.JsonMappingException:
//No serializer found for class dtos.MyDtoNoAccessors
//and no properties discovered to create BeanSerializer
//(to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
//solution from:https://www.baeldung.com/jackson-jsonmappingexception
public class WordDto {
    private long gameId;
    private char firstLetter;
    private int lengthWord;
    private Timestamp created;
    private int round;


    public WordDto(long gameId, char firstLetter, int lengthWord, int round) {
        this.gameId = gameId;
        this.firstLetter = firstLetter;
        this.lengthWord = lengthWord;
        this.created = new Timestamp(System.currentTimeMillis());
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getLengthWord() {
        return lengthWord;
    }

    public void setLengthWord(int lengthWord) {
        this.lengthWord = lengthWord;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
