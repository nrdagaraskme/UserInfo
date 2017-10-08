package com.liftoff.register;

import com.liftoff.user.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nrdagar on 07/10/17.
 */

@RestController
public class RegisterController {

    @Autowired
    RegistrationService registrationService;


    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * @api {post} /registerUser/
     *
     * @apiGroup Register
     *
     * @apiDescription Register a new User
     *
     * @apiName registerUser
     *
     * @apiParam {String} email  Mandatory email id of the user.
     * @apiParam {String} password   Mandatory password of the user.
     *
     * @apiSuccess {String} Registered Successfully
     *
     * @apiError Some error occured.Please try again..
     *
     */

    @CrossOrigin
    @RequestMapping(value="/registerUser", method= RequestMethod.POST ,produces = "text/plain")
    public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password) {

        logger.info("In the Registeration");
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
            return null;
         User user = new User(email,password);
        boolean addUser = false;
        try {
             addUser = registrationService.registerUser(user);
        }catch (InvalidFormatException e){

        }
        if(addUser)
            return new ResponseEntity<String>("Registered Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Some error occured.Please try again", HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
