package com.luv2code.springbootlibrary.requestmodels;

import lombok.Data;

/**
 * @author Guillaume COLLET
 */
@Data
public class PaymentInfoRequest {

    private int amount;
    private String currency;
    private String receiptEmail;
}
