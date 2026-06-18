package com.raf.notifikacijeService.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CancelClientNotification implements Serializable {

    private String email;
    private String username;
    private String gymName;
    private String typeOfWorkoutName;
    private String date;
    private String start;
    private String end;
    private Long receiverId;

    @Override
    public String toString(){
        return "Postovani " + username + ",\n\n" +
                "Vas termin za " + typeOfWorkoutName + " u teretani " + gymName + " za " + date + " u " + start + "h je otkazan.\n\n" +
                "Vidimo se drugom prilikom!\n\n" +
                "Vas ZakazivanjeServis";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getTypeOfWorkoutName() {
        return typeOfWorkoutName;
    }

    public void setTypeOfWorkoutName(String typeOfWorkoutName) {
        this.typeOfWorkoutName = typeOfWorkoutName;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
