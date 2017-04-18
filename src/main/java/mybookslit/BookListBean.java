package mybookslit;

/**
 * Created by Csongor on 3/26/2017.
 */
public class BookListBean {

    private int id;
    private String bookName;

    BookListBean(int id, String value){
        this.id = id;
        this.bookName = value;
    }

    public int getId(){
        return id;
    }

    public void setId( int id){
        this.id = id;
    }

    public String getBookName(){
        return bookName;
    }

    public void setbookName (String bookName){
        this.bookName = bookName;
    }
}
