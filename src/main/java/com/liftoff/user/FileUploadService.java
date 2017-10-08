package com.liftoff.user;

import com.liftoff.util.UserUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nrdagar on 08/10/17.
 */

@Service
public class FileUploadService {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/";

    private static final String REGISTER_FILE_NAME = "user";


    public void  saveUploadedFiles(MultipartFile file , String userID) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER+ "temp.xlsx");
        Files.write(path, bytes);
        try {
            UserUtil.saveContactsByUser(REGISTER_FILE_NAME, userID);

        }catch (IOException | InvalidFormatException e){

        }

    }
}
