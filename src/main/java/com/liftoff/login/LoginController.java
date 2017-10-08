package com.liftoff.login;

import com.liftoff.user.User;
import com.liftoff.util.UserCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by nrdagar on 07/10/17.
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * @api {post} /loginUser/
     *
     * @apiDescription  for logging a user
     *
     * @apiGroup Login
     *
     * @apiName loginUser
     *
     * @apiParam {String} email  Mandatory email id of the user.
     * @apiParam {String} password   Mandatory password of the user.
     *
     * @apiSuccess {header} status will be success
     *
     * @apiSuccess {header} Authorization header token will be there
     *
     * @apiError {String} bad INPUT!!!!
     *
     */

    @CrossOrigin
    @RequestMapping(value="/loginUser", method= RequestMethod.POST ,produces = "text/plain")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {

        logger.info("In the login");
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
            return null;
        User user = new User(email,password);
        HttpHeaders headers = new HttpHeaders();
        boolean validUser = false;
        try {
            validUser = loginService.validateUser(user);
            if(!validUser)
                return new ResponseEntity<String>("Invalid INPUT!!!!", HttpStatus.OK);

            logger.info("User is authenticated ");
            headers.set("Authorization", UserCache.getToken(user.getEmail()));
            headers.set("status", "success");
            ResponseEntity<String> response = new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            return response;
        }catch (InvalidFormatException  | IOException er ){

        }

        return new ResponseEntity<String>("Some Problem occured.Please try again" , HttpStatus.INTERNAL_SERVER_ERROR);



    }



}
