package PracticeAfterLearn.Chuong2.Bai4.Model;

public class Book {
    /**
     * Thông tin về Book gồm các thuộc tính (bookID, bookName, author,
     * specialization, publishYear, quantity) – trong đó specialization có thể là: Science,
     * Art, Economic, IT. bookID là một số nguyên có 6 chữ số, tự động tăng
     */
    private static int id = 1000000;
    private int bookID;
    private String bookName;
    private String author;
    private String specialization;
    private String publishYear;
    private int quantity;

    public Book(int bookID) {
        this.bookID = bookID;
    }

    public Book(int bookID, String bookName, String author, String specialization, String publishYear, int quantity) {
        if(bookID == 0){
            this.bookID = id;
        }
        else {
            this.bookID = bookID;
        }
        this.bookName = bookName;
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Book.id = id;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return this.bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", specialization='" + specialization + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
