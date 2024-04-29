package com.raf.userservice.domain;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Admin")
public class Admin extends User{


}
