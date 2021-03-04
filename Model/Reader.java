package PracticeAfterLearn.Chuong2.Bai4.Model;

public class Reader {
    /**
     * Thông tin về Reader (readerID, fullName, address, phoneNumber), readerID là
     * một số nguyên có 8 chữ số, tự động tăng, phoneNumber là số có độ dài là 10.
     */
    private static int id = 1000000000;
    private int readerID;
    private String fullName;
    private String address;
    private String phoneNumber;

    public Reader(int readerID) {
        this.readerID = readerID;
    }

    public Reader(int readerID, String fullName, String address, String phoneNumber) {
        if (readerID == 0) {
            this.readerID = id;
        } else {
            this.readerID = readerID;
        }
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Reader.id = id;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerID=" + readerID +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
