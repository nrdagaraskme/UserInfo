package com.liftoff.user;

import com.liftoff.util.UserUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by nrdagar on 08/10/17.
 */

@Service
public class GetAllContactsService {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/";

    private static final String REGISTER_FILE_NAME = "user";

    public List<Contact> getAllContacts(String userID){

        try {

         return    UserUtil.getAllContacts(REGISTER_FILE_NAME, userID);
        }catch (IOException | InvalidFormatException e){

        }
        return null;

    }

    public List<Contact> getContactByName(String userID , String name){

        try {

            return   UserUtil.getContactByName(REGISTER_FILE_NAME, userID,name);
        }catch (IOException | InvalidFormatException e){

        }
        return null;

    }


    public List<Contact> getContactByemail(String userID , String email){

        try {

            return   UserUtil.getContactByEmail(REGISTER_FILE_NAME, userID,email);
        }catch (IOException | InvalidFormatException e){

        }
        return null;

    }

    public List<Contact> getContactByPhone(String userID , String phone){

        try {

            return   UserUtil.getContactByPhone(REGISTER_FILE_NAME, userID,phone);
        }catch (IOException | InvalidFormatException e){

        }
        return null;

    }




}
