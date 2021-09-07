package frontend;

import java.awt.print.Book;

public class Books {
    String bookID;
    String bookName;
    String bookPrice;
    String bookEdition;
    String bookPublishDate;
    String bookPublisher;
    String bookStatus;
    public Books(String bookID,String bookName, String bookPrice, String bookEdition,String bookPublishDate, String bookPublisher, String bookStatus){
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookEdition  = bookEdition;
        this.bookPublishDate = bookPublishDate;
        this.bookPublisher = bookPublisher;
        this.bookStatus = bookStatus;

    }

    public String getBookID(){
        return this.bookID;
    }
    public String getBookName(){
        return this.bookName;
    }
    public String getBookPrice(){
        return this.bookPrice;
    }
    public String getBookEdition(){
        return this.bookEdition;
    }
    public String getBookPublishDate(){
        return this.bookPublishDate;
    }
    public String getBookPublisher(){
        return this.bookPublisher;
    }
    public String getBookStatus(){
        return this.bookStatus;
    }
}
