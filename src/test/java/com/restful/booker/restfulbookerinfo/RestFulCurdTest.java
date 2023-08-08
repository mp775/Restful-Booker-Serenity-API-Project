package com.restful.booker.restfulbookerinfo;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class RestFulCurdTest extends  TestBase {

    static String token = "44f28f09c797d4c";
    static String firstname = "niva" + TestUtils.getRandomValue();
    static String lastname = "patel" + TestUtils.getRandomValue();
    static String totalPrice = "2000";
    static boolean depositPaid = true;
    static String additionalNeeds = "breakfast";
    static String checkIn = "12-12-2023";
    static String checkOut = "12-15-2023";
    static String username = "admin";
    static String password = "password123";
    @Steps
    RestfullBookerSteps restfullBookerSteps;

    @Title("This test will login")
    @Test
    public void test001() {
        restfullBookerSteps.verifyUserShouldAbleToLogin(username, password);
    }

    @Title("this test will create new booking")
    @Test
    public void test002() {
        restfullBookerSteps.createNewUser(firstname, lastname, totalPrice, checkIn, checkOut, additionalNeeds);
    }

    @Title("This test will get all booking id ")
    @Test
    public void test003() {
        restfullBookerSteps.getAllBookingId();
    }

    @Title("This test will get firstname by booking id ")
    @Test
    public void test004() {
        restfullBookerSteps.getBookingIdsWithFirstName();
    }

    @Title("This test will update booking id ")
    @Test
    public void test005() {
        restfullBookerSteps.updateBooking(firstname, lastname, totalPrice, checkIn, checkOut, additionalNeeds);
    }

    @Title("This test will update booking firstname and lastname ")
    @Test
    public void test006() {
        restfullBookerSteps.partialUpdateBooking(firstname, lastname);

    }

    @Title("delete the booking by id ")
    @Test
    public void test007() {
        restfullBookerSteps.VerifyUserDeleteBookingSuccessfully();


    }
    }