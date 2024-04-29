package com.raf.notifikacijeService.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String content;
    private LocalDate timeofsending;
    @ManyToOne
    private MailType mailType;

    private Long receiverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getTimeofsending() {
        return timeofsending;
    }

    public void setTimeofsending(LocalDate timeofsending) {
        this.timeofsending = timeofsending;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
