package com.liftoff.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nrdagar on 07/10/17.
 */

   @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
   @JsonIgnoreProperties(ignoreUnknown = true)
   public class User {
        final String password;
        final String email;

        @JsonCreator
        public User( String email, String password) {

            this.email = email;
            this.password = password;
        }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
