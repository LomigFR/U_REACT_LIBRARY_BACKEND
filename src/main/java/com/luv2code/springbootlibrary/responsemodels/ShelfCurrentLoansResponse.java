package com.luv2code.springbootlibrary.responsemodels;

import com.luv2code.springbootlibrary.entity.Book;
import lombok.Data;

/**
 * @author Guillaume COLLET
 */
@Data
public class ShelfCurrentLoansResponse {
    public ShelfCurrentLoansResponse(Book book, int daysLeft){
        this.book= book;
        this.daysLeft=daysLeft;
    }

    private Book book;

    private int daysLeft;
}
