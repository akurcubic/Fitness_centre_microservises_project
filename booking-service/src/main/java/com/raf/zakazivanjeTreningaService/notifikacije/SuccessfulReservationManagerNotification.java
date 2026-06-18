package com.raf.zakazivanjeTreningaService.notifikacije;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class SuccessfulReservationManagerNotification implements Serializable {
    private String email;
    private String username;
    private String typeOfWorkoutName;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private Long receiverId;
    //broj treninga treba dodati

    @Override
    public String toString(){
        return "Postovani," + "\n" +
                "Klijent " + username + " je rezervisao trening " + typeOfWorkoutName + " u " + start + "h, " + date + ".\n" +
                "Srdacan pozdrav,\n" +
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

    public String getTypeOfWorkoutName() {
        return typeOfWorkoutName;
    }

    public void setTypeOfWorkoutName(String typeOfWorkoutName) {
        this.typeOfWorkoutName = typeOfWorkoutName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
