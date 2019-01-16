package com.saad.youssif.arabiclawyer.Model;

public class SittingDB {
    String sitting_issue_num,client_name,opponent_name,brol_num,delay_date,judgment;
    int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSitting_issue_num() {
        return sitting_issue_num;
    }

    public void setSitting_issue_num(String sitting_issue_num) {
        this.sitting_issue_num = sitting_issue_num;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getOpponent_name() {
        return opponent_name;
    }

    public void setOpponent_name(String opponent_name) {
        this.opponent_name = opponent_name;
    }

    public String getBrol_num() {
        return brol_num;
    }

    public void setBrol_num(String brol_num) {
        this.brol_num = brol_num;
    }

    public String getDelay_date() {
        return delay_date;
    }

    public void setDelay_date(String delay_date) {
        this.delay_date = delay_date;
    }

    public String getJudgment() {
        return judgment;
    }

    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }
}
