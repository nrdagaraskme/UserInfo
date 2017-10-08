package com.liftoff.register;

import com.liftoff.user.User;
import com.liftoff.util.UserUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by nrdagar on 07/10/17.
 */
@Service
public class RegistrationService {


    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private static final String REGISTER_FILE_NAME = "user";


    public boolean registerUser(User user) throws InvalidFormatException {

        try {
            UserUtil.saveUser(REGISTER_FILE_NAME,user);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
