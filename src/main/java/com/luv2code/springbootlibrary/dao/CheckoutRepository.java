package com.luv2code.springbootlibrary.dao;

import com.luv2code.springbootlibrary.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Guillaume COLLET
 */
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByUserEmailAndBookId(String userEMail, Long bookid);

    List<Checkout> findBooksByUserEmail(String userEmail);
}
