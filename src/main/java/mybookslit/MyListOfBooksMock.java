package mybookslit;

import java.util.*;

/**
 * Created by Csongor on 3/27/2017.
 */
public class MyListOfBooksMock {

    private static MyListOfBooksMock b;

    public static MyListOfBooksMock getInstance(){
        if (b == null){
            b = new MyListOfBooksMock();
        }

        return b;
    }

    private List<BookListBean> bookList = new ArrayList<BookListBean>();
    private int id;


    public void addItem (String value){
        id++;
        bookList.add(new BookListBean(id, value));
    }

    public void printList(){
        for(ListIterator<BookListBean> iter = bookList.listIterator(); iter.hasNext();){
            BookListBean element = iter.next();
        }
    }

    public List getList(){
        return bookList;
    }
}
