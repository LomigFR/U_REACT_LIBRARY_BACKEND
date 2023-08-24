package com.luv2code.springbootlibrary.requestmodels;

import lombok.Data;

import java.util.Optional;

/**
 * @author Guillaume COLLET
 */

@Data
public class ReviewRequest {

    private double rating;

    private Long bookId;

    /**
     * A review description is not mandatory when you give stars to a book,
     * so we use Optional type;
     */
    private Optional<String> reviewDescription;
}
