package com.liftoff.user;

import com.liftoff.util.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by nrdagar on 07/10/17.
 */

@RestController
public class UserController {



    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    GetAllContactsService getAllContactsService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * @api {post} /uploadContacts/
     *
     * @apiGroup User
     *
     * @apiDescription  uploads contacts for a particular user
     *
     * @apiName uploadContacts
     *
     * @apiParam {header} Authorization token should be there
     *
     * @apiParam {String} FILE Mandatory file is requried for uploading contacts
     *
     * @apiSuccess {String} Successfully uploaded message will be there.
     *
     *
     * @apiError {String} Please login first!!!!
     *
     * @apiError {String} Please select a file!
     *
     */


    @CrossOrigin
    @RequestMapping(value = "/uploadContacts"  , method =  RequestMethod.POST)
    public ResponseEntity<String> uploadContacts(@RequestHeader(value="Authorization") String authID , @RequestParam(name = "file") MultipartFile uploadfile) {

        logger.info("Single file upload!");
        if(!UserCache.isValidUser(authID))
            return new ResponseEntity<String>("Please login first!!!!", HttpStatus.OK);

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

            fileUploadService.saveUploadedFiles(uploadfile,UserCache.getUserIDFromToken(authID));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }


    /**
     * @api {post} /getAllContactsByUserID/
     *
     * @apiGroup User
     *
     * @apiDescription  get all contacts for a particular user
     *
     * @apiName getAllContactsByUserID
     *
     * @apiParam {header} Mandatory Authorization token should be there
     *
     *
     * @apiSuccess {Json} Array of all contacts
     *
     *
     * @apiError {String} Please login first!!!!
     *
     *
     */

    @CrossOrigin
    @RequestMapping(value = "/getAllContactsByUserID"  , method =  RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getAllContactsByUserID(@RequestHeader(value="Authorization") String authID ) {

        logger.info("Get all contacts");
        if(!UserCache.isValidUser(authID))
            return null;

        List<Contact>  contacts = getAllContactsService.getAllContacts(UserCache.getUserIDFromToken(authID));
        return contacts;

    }


    /**
     * @api {post} /getContactByName/
     *
     * @apiGroup User
     *
     * @apiDescription  get all contacts for a particular user By Name
     *
     * @apiName getContactByName
     *
     * @apiParam {header}  Mandatory Authorization token should be there
     *
     * @apiParam {String} Mandatory first Name
     *
     * @apiParam {String} Mandatory last Name
     *
     * @apiSuccess {Json} Array of all contacts
     *
     *
     * @apiError {String} Please login first!!!!
     *
     *
     */

    @CrossOrigin
    @RequestMapping(value = "/getContactByName"  , method =  RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getContactByName(@RequestHeader(value="Authorization") String authID ,
                                          @RequestParam(value = "firstName", required = true) String firstName,
                                          @RequestParam(value = "lastName", required = true) String lastName) {

        logger.info("Get all contacts by Name");
        if(!UserCache.isValidUser(authID))
            return null;

        List<Contact>  contacts = getAllContactsService.getContactByName(UserCache.getUserIDFromToken(authID),firstName+lastName);
        return contacts;

    }


    /**
     * @api {post} /getContactByemail/
     *
     * @apiGroup User
     *
     * @apiDescription  get all contacts for a particular user By email
     *
     * @apiName getContactByemail
     *
     * @apiParam {header}  Mandatory Authorization token should be there
     *
     * @apiParam {String} Mandatory Email
     *
     *
     * @apiSuccess {Json} Array of all contacts
     *
     *
     * @apiError {String} Please login first!!!!
     *
     *
     */


    @CrossOrigin
    @RequestMapping(value = "/getContactByemail"  , method =  RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getContactByemail(@RequestHeader(value="Authorization") String authID ,
                                          @RequestParam(value = "email", required = true) String email) {

        logger.info("Get all contacts by email");
        if(!UserCache.isValidUser(authID))
            return null;

        List<Contact>  contacts = getAllContactsService.getContactByemail(UserCache.getUserIDFromToken(authID),email);
        return contacts;

    }


    /**
     * @api {post} /getContactByPhone/
     *
     * @apiGroup User
     *
     * @apiDescription  get all contacts for a particular user By phone
     *
     * @apiName getContactByPhone
     *
     * @apiParam {header}  Mandatory Authorization token should be there
     *
     * @apiParam {String} Mandatory phone
     *
     *
     * @apiSuccess {Json} Array of all contacts
     *
     *
     * @apiError {String} Please login first!!!!
     *
     *
     */


    @CrossOrigin
    @RequestMapping(value = "/getContactByPhone"  , method =  RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getContactByphone(@RequestHeader(value="Authorization") String authID ,
                                           @RequestParam(value = "phone", required = true) String phone) {

        logger.info("Get all contacts by phone");
        if(!UserCache.isValidUser(authID))
            return null;

        List<Contact>  contacts = getAllContactsService.getContactByPhone(UserCache.getUserIDFromToken(authID),phone);
        return contacts;

    }


}

