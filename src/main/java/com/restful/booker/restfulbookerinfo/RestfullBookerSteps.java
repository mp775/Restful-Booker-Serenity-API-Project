package com.restful.booker.restfulbookerinfo;

import com.restful.booker.constants.EndPoint;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;
import static net.serenitybdd.rest.SerenityRest.then;

public class RestfullBookerSteps {
    static String token = "44f28f09c797d4c";
    @Step(" creating credential : Username:{0}, password:{1} ")
    public String verifyUserShouldAbleToLogin(String username,String password) {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(authPojo)
                .post(EndPoint.GET_TOKEN)
                .then().log().all().statusCode(201)
                .extract().path("token");
    }

    @Step("creating new booking : firstname {0},lastname {1},totalPrice {2},depositPaid {3},,Checkin {4},Checkout {5},BookingDate {6},BookingDates {7},AdditionalNeeds {8}")
    public ValidatableResponse createNewUser (String firstname, String lastname, String totalPrice, String checkIn, String checkOut, String additionalNeeds) {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstname);
        restPojo.setLastname(lastname);
        restPojo.setTotalPrice(totalPrice);
        restPojo.setDepositPaid(true);
        BookingDates bookingD = new BookingDates();
        bookingD.setCheckIn(checkIn);
        bookingD.setCheckOut(checkOut);
        restPojo.setBookingDates(bookingD);
        restPojo.setAdditionalNeeds(additionalNeeds);
        return given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer" + token)
                .when()
                .body(restPojo)
                .post(EndPoint.CREATE_BOOKING)
                .then().log().all().statusCode(201);

    }

    @Step("Get all Booking")
    public void getAllBookingId() {
        SerenityRest.given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then().statusCode(200);

    }

    @Step("Get Booking id with firstname ")
    public void getBookingIdsWithFirstName() {
        SerenityRest.given()
                .basePath("/booking?firstname=John")
                .header("Content-Type", "application/json")
                .when()
                .get();
        then().statusCode(200);

    }

    @Step("creating new booking : firstname {0},lastname {1},totalPrice {2},depositPaid {3},Booking {4},Checkin {5},Checkout {6},BookingDate {7},BookingDates {8},AdditionalNeeds {9}")

    public void updateBooking(String firstname, String lastname, String totalPrice, String checkIn, String checkOut, String additionalNeeds) {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstname);
        restPojo.setLastname(lastname);
        restPojo.setTotalPrice(totalPrice);
        restPojo.setDepositPaid(true);
        BookingDates bookingD = new BookingDates();
        bookingD.setCheckIn(checkIn);
        bookingD.setCheckOut(checkOut);
        restPojo.setBookingDates(bookingD);
        restPojo.setAdditionalNeeds(additionalNeeds);
        int id = 2625;
        SerenityRest.given()
                .headers("Content-Type", "applic2567ation/json", "Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .body(restPojo)
                .put(EndPoint.UPDATE_BOOKING_BY_ID)
                .then().log().all().statusCode(200);

    }

    @Step("PartialUpdate booking")
    public void partialUpdateBooking(String firstname, String lastname) {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname(firstname);
        restPojo.setLastname(lastname);
        int id = 2625;
        SerenityRest.given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", id)
                .when()
                .body(restPojo)
                .patch(EndPoint.UPDATE_BOOKING_BY_ID);
        then().log().all().statusCode(200);


    }

    @Step("Delete the Booking")
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















