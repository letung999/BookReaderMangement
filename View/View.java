package PracticeAfterLearn.Chuong2.Bai4.View;

import PracticeAfterLearn.Chuong2.Bai4.Controller.ControllerUtility;
import PracticeAfterLearn.Chuong2.Bai4.Controller.DataController;
import PracticeAfterLearn.Chuong2.Bai4.Model.BRM;
import PracticeAfterLearn.Chuong2.Bai4.Model.Book;
import PracticeAfterLearn.Chuong2.Bai4.Model.Reader;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        var bookFileName = "BOOK1.DAT";
        var readerFileName = "READER1.DAT";
        var brmFileName = "BRM1.DAT";
        var dataController = new DataController();
        var controllerUtility = new ControllerUtility();
        var books = new ArrayList<Book>();
        var readers = new ArrayList<Reader>();
        var brms = new ArrayList<BRM>();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("************************MENU*************************");
            System.out.println("1.Add a book In file");
            System.out.println("2.Show Information the book In file");
            System.out.println("3.Add a reader In file");
            System.out.println("4.Show Information the reader In file");
            System.out.println("5.Controller Information Book Reader Management In file");
            System.out.println("6.Show Information Book Reader Management In File");
            System.out.println("7.Sort And Search Information");
            System.out.println("0.exist");
            System.out.println("you choose!");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    System.out.println("exist");
                    break;
                }
                case 1: {
                    checkBookIDAscending(dataController, bookFileName);
                    String bookName, author, specialization, publishYear;
                    int quantity, sp;
                    System.out.println("Input bookName");
                    bookName = scanner.nextLine();
                    System.out.println("Input author");
                    author = scanner.nextLine();
                    String[] spes = {"Science", "Art", "Economic", "IT"};
                    do {
                        System.out.println("1.Science\n2.Art\n3.Economic\n4.IT");
                        sp = scanner.nextInt();
                        if (sp < 1 || sp > 4) {
                            System.out.println("no Option in list specialization, please choose again");
                        } else {
                            specialization = spes[sp - 1];
                            break;
                        }

                    } while (true);
                    System.out.println("Input publishYear");
                    scanner.nextLine();
                    publishYear = scanner.nextLine();
                    System.out.println("Input quantity");
                    quantity = scanner.nextInt();
                    Book book = new Book(0, bookName, author, specialization, publishYear, quantity);
                    dataController.writeBookToFile(book, bookFileName);
                    break;
                }
                case 2: {
                    books = dataController.readBookFromFile(bookFileName);
                    System.out.println("***************Information Book In List***************");
                    showInformation(books);
                    break;
                }
                case 3: {
                    /**
                     * int readerID, String fullName, String address, String phoneNumber
                     */
                    checkReaderIDAscending(dataController, readerFileName);
                    String fullName, address, phoneNumber;
                    System.out.println("Input fullName");
                    fullName = scanner.nextLine();
                    System.out.println("Input Adress");
                    address = scanner.nextLine();
                    do {
                        System.out.println("Input phoneNumber");
                        phoneNumber = scanner.nextLine();

                    } while (!phoneNumber.matches("\\d{10}"));
                    Reader reader = new Reader(0, fullName, address, phoneNumber);
                    dataController.writeReaderToFile(reader, readerFileName);
                    break;
                }
                case 4: {
                    readers = dataController.readReaderFromFile(readerFileName);
                    System.out.println("***********************Information Reader In File **********************");
                    showInformation(readers);
                    break;
                }
                case 5: {
                    /**
                     * Reader reader, Book book, int numOfBorrow, String state, int total
                     */
                    readers = dataController.readReaderFromFile(readerFileName);
                    books = dataController.readBookFromFile(bookFileName);
                    brms = dataController.readBrmFromFile(brmFileName);
                    int readerID, bookID;
                    boolean AllowReader = false;
                    boolean AllowBook = false;
                    do {
                        System.out.println("***************************Information Reader In File**************************");
                        showInformation(readers);
                        System.out.println("Input readerID, Input 0 to break");
                        readerID = scanner.nextInt();
                        if (readerID == 0) {
                            break;
                        }
                        AllowReader = checkAllowReader(brms, readerID);
                        if (AllowReader == false) {
                            System.out.println("Over 15 books to borrow");
                        } else {
                            break;
                        }
                    } while (true);
                    do {
                        System.out.println("***************************Information Book In File**************************");
                        showInformation(books);
                        System.out.println("Input bookID, Input 0 to break");
                        bookID = scanner.nextInt();
                        if (bookID == 0) {
                            break;
                        }
                        AllowBook = checkAllowBook(brms, bookID, readerID);
                        if (AllowBook == false) {
                            System.out.println("Over 3 book for A Reader, please choose the other books");
                        } else {
                            break;
                        }

                    } while (true);
                    int total = getTotal(readerID, bookID, brms);
                    do {
                        System.out.println("Input numOfBorrow ( borrowed: " + total + ")");
                        int numOfBorrow = scanner.nextInt();
                        if ((total + numOfBorrow >= 1) && (total + numOfBorrow <= 3)) {
                            total += numOfBorrow;
                            break;
                        } else {
                            System.out.println("Over 3 books");
                        }

                    } while (true);
                    System.out.println("Input State");
                    scanner.nextLine();
                    String state = scanner.nextLine();
                    Reader currentReader = getReader(readers, readerID);
                    Book currentBook = getBook(books, bookID);
                    BRM brm = new BRM(currentReader, currentBook, total, state, 0);
                    brms = ControllerUtility.upDateFile(brm, brms);
                    dataController.UpdateFile(brms, brmFileName);
                    showInformation(brms);
                    break;
                }
                case 6: {
                    brms = dataController.readBrmFromFile(brmFileName);
                    brms = controllerUtility.getTotal(brms);
                    int choose;
                    do {
                        System.out.println("***************MENU***************");
                        System.out.println("1.Sort By Name Reader");
                        System.out.println("2.Sort By Total Of Borrow");
                        System.out.println("3.Search By Name Reader");
                        System.out.println("0.return main menu");
                        System.out.println("your option");
                        choose = scanner.nextInt();
                        if(choose == 0){
                            break;
                        }
                        switch (choose){
                            case 1:{
                                controllerUtility.SortByName(brms);
                                showInformation(brms);
                                break;
                            }
                            case 2:{
                                controllerUtility.SortByTotalOfBorrow(brms);
                                showInformation(brms);
                                break;
                            }
                            case 3:{
                                System.out.println("Input Name To Search");
                                scanner.nextLine();
                                String key = scanner.nextLine();
                                var result = new ArrayList<BRM>();
                                result = controllerUtility.searchName(key, brms);
                                if(result.size() == 0){
                                    System.out.println("No name to find in list");
                                }
                                else {
                                    showInformation(result);
                                }
                                break;
                            }
                        }
                    }while (choose != 0);
                    break;
                }
            }
        } while (option != 0);
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

    private static int getTotal(int readerID, int bookID, ArrayList<BRM> brms) {
        for (var x : brms) {
            if (x.getBook().getBookID() == bookID && x.getReader().getReaderID() == readerID) {
                return x.getNumOfBorrow();
            }
        }
        return 0;
    }

    private static boolean checkAllowBook(ArrayList<BRM> brms, int bookID, int readerID) {
        for (var x : brms) {
            if (x.getReader().getReaderID() == readerID &&
                    x.getBook().getBookID() == bookID && x.getNumOfBorrow() >= 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkAllowReader(ArrayList<BRM> brms, int readerID) {
        int count = 0;
        for (var x : brms) {
            if (x.getReader().getReaderID() == readerID) {
                count += x.getNumOfBorrow();
            }
        }
        if (count >= 15) {
            return false;
        }
        return true;
    }

    private static void checkReaderIDAscending(DataController dataController, String readerFileName) {
        var readers = new ArrayList<Reader>();
        readers = dataController.readReaderFromFile(readerFileName);
        if (readers.size() == 0) {
            /**
             * donothing
             */
        } else {
            Reader.setId(readers.get(readers.size() - 1).getReaderID() + 1);
        }
    }

    private static void checkBookIDAscending(DataController dataController, String bookFileName) {
        var books = new ArrayList<Book>();
        books = dataController.readBookFromFile(bookFileName);
        if (books.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Book.setId(books.get(books.size() - 1).getBookID() + 1);
        }
    }

    public static void showInformation(ArrayList list) {
        for (var x : list) {
            System.out.println(x);
        }
    }
}
