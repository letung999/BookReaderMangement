package PracticeAfterLearn.Chuong2.Bai4.Controller;

import PracticeAfterLearn.Chuong2.Bai4.Model.BRM;
import PracticeAfterLearn.Chuong2.Bai4.Model.Book;
import PracticeAfterLearn.Chuong2.Bai4.Model.Reader;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    /**
     * writeFile:
     */
    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param book
     * @param fileName int bookID, String bookName, String author, String specialization, String publishYear, int quantity
     */
    public void writeBookToFile(Book book, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(book.getBookID() + "|" + book.getBookName() + "|" + book.getAuthor() + "|" + book.getSpecialization()
                + "|" + book.getPublishYear() + "|" + book.getQuantity());
        closeFileAfterWrite(fileName);
    }

    /**
     * @param reader
     * @param fileName int readerID, String fullName, String address, String phoneNumber
     */
    public void writeReaderToFile(Reader reader, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(reader.getReaderID() + "|" + reader.getFullName() + "|" + reader.getAddress() + "|" + reader.getPhoneNumber());
        closeFileAfterWrite(fileName);
    }

    /**
     * Reader reader, Book book, int numOfBorrow, int state, int total
     *
     * @param brm
     * @param fileName
     */
    public void writeBRMToFile(BRM brm, String fileName) {
        openFileToWrite(fileName);
        printWriter.println(brm.getReader().getReaderID() + "|" + brm.getBook().getBookID() + "|" +
                brm.getNumOfBorrow() + "|" + brm.getState());
        closeFileAfterWrite(fileName);
    }
    /**
     * read
     */
    public void openFileToRead(String fileName){
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeFileAfterRead(String fileName){
        scanner.close();
    }

    public ArrayList<Reader> readReaderFromFile( String fileName){
        ArrayList<Reader> readers = new ArrayList<>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Reader reader = convertDataFromReader(data);
            readers.add(reader);
        }
        closeFileAfterRead(fileName);
        return readers;
    }

    public ArrayList<Book> readBookFromFile(String fileName){
        ArrayList books = new ArrayList();
        openFileToRead(fileName);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Book book = convertDataFromBook(data);
            books.add(book);
        }
        closeFileAfterRead(fileName);
        return books;
    }

    public ArrayList<BRM> readBrmFromFile( String fileName){
        var readers = readReaderFromFile("READER1.DAT");
        var books = readBookFromFile("BOOK1.DAT");
        ArrayList<BRM> brms = new ArrayList<>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            BRM brm = convertDataFromBRM(data, readers, books);
            brms.add(brm);
        }
        closeFileAfterRead(fileName);
        return brms;
    }

    /**
     * Reader reader, Book book, int numOfBorrow, String state, int total
     * @param data
     * @param readers
     * @param books
     * @return
     */
    private BRM convertDataFromBRM(String data, ArrayList<Reader> readers, ArrayList<Book> books) {
        String[] datas = data.split("\\|");
        BRM brm = new BRM(getReader(readers,Integer.parseInt(datas[0])),
                getBook(books,Integer.parseInt(datas[1])),Integer.parseInt(datas[2]), datas[3],0);
        return brm;
    }

    /**
     * int bookID, String bookName, String author, String specialization, String publishYear, int quantity
     * @param data
     * @return
     */

    private Book convertDataFromBook(String data) {
        String[] datas = data.split("\\|");
        Book book = new Book(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], Integer.parseInt(datas[5]));
        return book;
    }

    /**
     * int readerID, String fullName, String address, String phoneNumber
     * @param data
     * @return
     */

    private Reader convertDataFromReader(String data) {
        String[] datas = data.split("\\|");
        Reader reader = new Reader(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
        return reader;
    }


    public void UpdateFile(ArrayList<BRM> brms, String brmFileName) {
        File file = new File(brmFileName);
        if(file.exists()){
            file.delete();
        }
        for (var brm:brms) {
            openFileToWrite(brmFileName);
            printWriter.println(brm.getReader().getReaderID() + "|" + brm.getBook().getBookID() + "|" +
                    brm.getNumOfBorrow() + "|" + brm.getState());
            closeFileAfterWrite(brmFileName);
        }
    }
    private static Book getBook(ArrayList<Book> books, int bookID) {
        for (int i = 0; i < books.size(); ++i) {
            if (bookID == books.get(i).getBookID()) {
                return books.get(i);
            }
        }
        return null;
    }

    private static Reader getReader(ArrayList<Reader> readers, int readerID) {
        for (int i = 0; i < readers.size(); ++i) {
            if (readerID == readers.get(i).getReaderID()) {
                return readers.get(i);
            }
        }
        return null;
    }
}
