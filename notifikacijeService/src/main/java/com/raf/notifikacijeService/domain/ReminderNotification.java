package com.raf.notifikacijeService.domain;



import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class ReminderNotification implements Serializable {
    private String email;
    private String gymName;
    private String typeOfWorkoutName;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private Long receiverId;

    @Override
    public String toString(){
        return "Postovani, \n\n" +
                "Podsetnik za trening: " + typeOfWorkoutName + " u teretani " + gymName + " za " + date + " u " + start + "h.\n\n" +
                "Ovo je povratna poruka!\n\n" +
                "Vas ZakazivanjeServis";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
