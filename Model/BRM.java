package PracticeAfterLearn.Chuong2.Bai4.Model;

public class BRM {
    /**
     * Thêm lớp BookReaderManagerment trong đó một Reader sẽ được phép mượn
     * tối đa 5 đầu Book khác nhau, mỗi đầu Book tối đa 3 cuốn. Ghi rõ tình trạng hiện
     * thời của Book khi mượn.
     */
    private Reader reader;
    private Book book;
    private int numOfBorrow;
    private String state;
    private int total;

    public BRM(Reader reader) {
        this.reader = reader;
    }

    public BRM(Book book) {
        this.book = book;
    }

    public BRM(Reader reader, Book book, int numOfBorrow, String state, int total) {
        this.reader = reader;
        this.book = book;
        this.numOfBorrow = numOfBorrow;
        this.state = state;
        this.total = total;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNumOfBorrow() {
        return numOfBorrow;
    }

    public void setNumOfBorrow(int numOfBorrow) {
        this.numOfBorrow = numOfBorrow;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BRM{" +
                "readerID=" + reader.getReaderID() +
                ", readerName=" + reader.getFullName() +
                ", bookID=" + book.getBookID() +
                ", bookName=" + book.getBookName() +
                ", numOfBorrow=" + numOfBorrow +
                ", state=" + state +
                ", total=" + total +
                '}';
    }
}
