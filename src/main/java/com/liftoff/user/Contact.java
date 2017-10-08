package com.liftoff.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by nrdagar on 08/10/17.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Contact {

    public final String firstName;
    public final String lastNmae;
    public final String email;
    public final String phoneNo;


    public Contact(String firstName , String lastNmae , String email , String phoneNo){

        this.firstName = firstName;
        this.lastNmae = lastNmae;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastNmae() {
        return lastNmae;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
