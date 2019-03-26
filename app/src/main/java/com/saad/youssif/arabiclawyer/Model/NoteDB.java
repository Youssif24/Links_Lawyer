package com.saad.youssif.arabiclawyer.Model;

public class NoteDB {

    String court_name;
    String client;
    String opponent;
    String prev_sitting;
    String decision;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getPrev_sitting() {
        return prev_sitting;
    }

    public void setPrev_sitting(String prev_sitting) {
        this.prev_sitting = prev_sitting;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }


}
