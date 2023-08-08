package com.restful.booker.restfulbookerinfo;

import com.restful.booker.constants.EndPoint;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.SerenityRest.then;

@RunWith(SerenityRunner.class)
public class RestFulCurd extends TestBase {
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
    int id = 2625;

    @Test
    public void verifyUserShouldAbleToLogin() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        SerenityRest.given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer" + token)
                .when()
                .body(authPojo)
                .post(EndPoint.GET_TOKEN)
                .then().log().all().statusCode(201);
    }

    @Test
    public void verifyCreateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstname);
        restPojo.setLastname(lastname);
        restPojo.setTotalPrice(totalPrice);
        restPojo.setDepositPaid(depositPaid);
        BookingDates bookingD = new BookingDates();
        bookingD.setCheckIn(checkIn);
        bookingD.setCheckOut(checkOut);
        restPojo.setBookingDates(bookingD);
        restPojo.setAdditionalNeeds(additionalNeeds);
        SerenityRest.given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer" + token)
                .when()
                .body(restPojo)
                .post(EndPoint.CREATE_BOOKING)
                .then().log().all().statusCode(201);

    }

    @Test
    public void getAllBookingId() {
        SerenityRest.given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then().statusCode(200);

    }

    @Test
    public void getBookingIdsWithFirstName() {
        SerenityRest.given()
                .basePath("/booking?firstname=John")
                .header("Content-Type", "application/json")
                .when()
                .get();
        then().statusCode(200);

    }

    @Test
    public void updateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstname);
        restPojo.setLastname(lastname);
        restPojo.setTotalPrice(totalPrice);
        restPojo.setDepositPaid(depositPaid);
        BookingDates bookingD = new BookingDates();
        bookingD.setCheckIn(checkIn);
        bookingD.setCheckOut(checkOut);
        restPojo.setBookingDates(bookingD);
        restPojo.setAdditionalNeeds(additionalNeeds);
        SerenityRest.given()
                .headers("Content-Type", "applic2567ation/json", "Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .body(restPojo)
                .put(EndPoint.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(200);

    }

    @Test
    public void partialUpdateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("janu");
        restPojo.setLastname("joshi");
        SerenityRest.given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", id)
                .when()
                .body(restPojo)
                .patch(EndPoint.UPDATE_BOOKING_BY_ID);
        then().log().all().statusCode(200);


    }

    @Test
    public void VerifyUserDeleteBookingSuccessfully() {
        SerenityRest.given()
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .basePath(RestAssured.basePath + "/94")
                .when()
                .body("")
                .delete(EndPoint.DELETE_BOOKING_BY_ID)
                .then().statusCode(201);
    }
}













