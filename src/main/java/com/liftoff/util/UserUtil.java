package com.liftoff.util;

import com.liftoff.user.Contact;
import com.liftoff.user.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by nrdagar on 07/10/17.
 */
public class UserUtil {

    public static boolean saveUser(final String fileName , User user) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet spreadsheet = workbook.getSheet("users");
        Row row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);
        writeUserData(user, row);
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return true;
    }


    public static boolean saveContactsByUser(final String fileName ,final String userID) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet spreadsheet = workbook.getSheet("contacts");
        if(spreadsheet == null)
            spreadsheet = workbook.createSheet("contacts");
        writeContactsData(userID, spreadsheet);
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return true;
    }



    public static boolean validateUser(final String fileName , User user) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("users");
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            String email = cell.getStringCellValue();
            cell = cellIterator.next();
            String password = cell.getStringCellValue();
            if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                UUID uuid = UUID.randomUUID();
                UserCache.addUser(email,uuid.toString());
                UserCache.addUserInSession(uuid.toString());
                return true;
            }

        }
        inputStream.close();
        return false;
    }


    public static List<Contact> getAllContacts(final String fileName , final String userID) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("contacts");
        Iterator<Row> rowIterator = sheet.iterator();
        List<Contact> contacts = new ArrayList<Contact>();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Cell cell  = row.getCell(row.getLastCellNum()-1);
            if (cell.getStringCellValue().equals(userID)) {

                Iterator<Cell> cellIterator = row.cellIterator();
                cell = cellIterator.next();
                String firstName = cell.getStringCellValue();
                cell = cellIterator.next();
                String lastName = cell.getStringCellValue();
                cell = cellIterator.next();
                String email = cell.getStringCellValue();
                cell = cellIterator.next();
                String phone = cell.getStringCellValue();
                Contact contact = new Contact(firstName,lastName,email,phone);
                contacts.add(contact);
            }

        }
        inputStream.close();
        return contacts;
    }


    public static List<Contact> getContactByName(final String fileName , final String userID , final String name) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("contacts");
        Iterator<Row> rowIterator = sheet.iterator();
        List<Contact> contacts = new ArrayList<Contact>();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Cell cell  = row.getCell(row.getLastCellNum()-1);
            if (cell.getStringCellValue().equals(userID)) {

                Iterator<Cell> cellIterator = row.cellIterator();
                cell = cellIterator.next();
                String firstName = cell.getStringCellValue();
                cell = cellIterator.next();
                String lastName = cell.getStringCellValue();
                String fullName = firstName + lastName;
                cell = cellIterator.next();
                String email = cell.getStringCellValue();
                cell = cellIterator.next();
                String phone = cell.getStringCellValue();
                if(fullName.equals(name)) {
                    Contact contact = new Contact(firstName, lastName, email, phone);
                    contacts.add(contact);
                }
            }

        }
        inputStream.close();
        return contacts;
    }

    public static List<Contact> getContactByEmail(final String fileName , final String userID , final String searchEmail) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("contacts");
        Iterator<Row> rowIterator = sheet.iterator();
        List<Contact> contacts = new ArrayList<Contact>();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Cell cell  = row.getCell(row.getLastCellNum()-1);
            if (cell.getStringCellValue().equals(userID)) {

                Iterator<Cell> cellIterator = row.cellIterator();
                cell = cellIterator.next();
                String firstName = cell.getStringCellValue();
                cell = cellIterator.next();
                String lastName = cell.getStringCellValue();
                cell = cellIterator.next();
                String email = cell.getStringCellValue();
                cell = cellIterator.next();
                String phone = cell.getStringCellValue();
                if(searchEmail.equals(email)) {
                    Contact contact = new Contact(firstName, lastName, email, phone);
                    contacts.add(contact);
                }
            }

        }
        inputStream.close();
        return contacts;
    }

    public static List<Contact> getContactByPhone(final String fileName , final String userID , final String searchPhone) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf(fileName) + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("contacts");
        Iterator<Row> rowIterator = sheet.iterator();
        List<Contact> contacts = new ArrayList<Contact>();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Cell cell  = row.getCell(row.getLastCellNum()-1);
            if (cell.getStringCellValue().equals(userID)) {

                Iterator<Cell> cellIterator = row.cellIterator();
                cell = cellIterator.next();
                String firstName = cell.getStringCellValue();
                cell = cellIterator.next();
                String lastName = cell.getStringCellValue();
                cell = cellIterator.next();
                String email = cell.getStringCellValue();
                cell = cellIterator.next();
                String phone = cell.getStringCellValue();
                if(searchPhone.equals(phone)) {
                    Contact contact = new Contact(firstName, lastName, email, phone);
                    contacts.add(contact);
                }
            }

        }
        inputStream.close();
        return contacts;
    }


    public static XSSFWorkbook addUser(User user) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.getSheet("users");
        if(spreadsheet == null)
            spreadsheet = workbook.createSheet("users");
            XSSFRow row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);
            writeUserData(user, row);
        return workbook;
    }

    private static void writeUserData(User user , Row row){

        Cell cell = row.createCell(0);
        cell.setCellValue(user.getEmail());

        cell = row.createCell(1);
        cell.setCellValue(user.getPassword());
    }

    private static void writeContactsData(String userID ,Sheet spreadsheet ) throws IOException, InvalidFormatException {


        List<Contact> contacts = getContactsData(userID);
        contacts.remove(0);
        for(Contact contact : contacts) {

            Row row = spreadsheet.createRow(spreadsheet.getLastRowNum() + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(contact.getFirstName());
            cell = row.createCell(1);
            cell.setCellValue(contact.getLastNmae());
            cell = row.createCell(2);
            cell.setCellValue(contact.getEmail());
            cell = row.createCell(3);
            cell.setCellValue(contact.getPhoneNo());
            cell = row.createCell(4);
            cell.setCellValue(userID);

        }
    }

    private static List<Contact> getContactsData(String userID) throws IOException, InvalidFormatException {

        File file = new File(String.valueOf("temp") + ".xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<Contact> contacts =  new ArrayList<Contact>();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            if(row == null)
                break;
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = null;
            if(cellIterator.hasNext())
             cell = cellIterator.next();
            if(cell.getStringCellValue().equals(""))
                break;
            String firstName = cell.getStringCellValue();
            if(cellIterator.hasNext())
            cell = cellIterator.next();
            String lastName = cell.getStringCellValue();
            if(cellIterator.hasNext())
            cell = cellIterator.next();
            String email = cell.getStringCellValue();
            if(cellIterator.hasNext())
            cell = cellIterator.next();
            String phone = null;
            if(Cell.CELL_TYPE_NUMERIC == cell.getCellType())
             phone = String.valueOf(cell.getNumericCellValue());
            else
                phone = String.valueOf(cell.getStringCellValue());
            Contact contact = new Contact(firstName,lastName,email,phone);
            contacts.add(contact);

        }
        inputStream.close();
        return contacts;



    }


}
