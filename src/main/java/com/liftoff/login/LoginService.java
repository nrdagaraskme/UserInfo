package com.liftoff.login;

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
public class LoginService {


    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String REGISTER_FILE_NAME = "user";



    public boolean validateUser(User user) throws InvalidFormatException, IOException {


        return UserUtil.validateUser(REGISTER_FILE_NAME, user);


    }


}
