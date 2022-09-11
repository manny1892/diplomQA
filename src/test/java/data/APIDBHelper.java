package data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

import static io.restassured.RestAssured.given;


public class APIDBHelper {
    @SneakyThrows
    public static void clearDB() {

        val deleteOrder = "DELETE FROM order_entity;";
        val deletePayment = "DELETE FROM payment_entity;";
        val deleteCredit = "DELETE FROM credit_request_entity;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            runner.update(conn, deleteOrder);
            runner.update(conn, deletePayment);
            runner.update(conn, deleteCredit);

        }
    }

    @SneakyThrows
    public static String getCreditStatusDB() {
        val status = "SELECT status FROM credit_request_entity;";
        val runner = new QueryRunner();
        String creditStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            creditStatus = runner.query(conn, status, new ScalarHandler<>());
        }

        return creditStatus;
    }

    @SneakyThrows
    public static String getPaymentStatusDB() {
        val sql = "SELECT status FROM payment_entity;";
        val runner = new QueryRunner();
        String payStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            payStatus = runner.query(conn, sql, new ScalarHandler<>());
        }

        return payStatus;
    }

    @SneakyThrows
    public static long getPaymentCount() {
        val sql = "SELECT COUNT(id) as count FROM payment_entity;";
        val runner = new QueryRunner();
        long payCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            payCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return payCount;
    }

    @SneakyThrows
    public static long getCreditCount() {
        val sql = "SELECT COUNT(id) as count FROM credit_request_entity;";
        val runner = new QueryRunner();
        long creditCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            creditCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return creditCount;
    }

    @SneakyThrows
    public static long getOrderCount() {
        val sql = "SELECT COUNT(id) as count FROM order_entity;";
        val runner = new QueryRunner();
        long orderCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass"
                );
        ) {
            orderCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return orderCount;
    }

    private static final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void buyApprovedPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    public static void buyDeclinedPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidDeclinedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    public static void buyApprovedCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    public static void buyDeclinedCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidDeclinedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    public static void buyEmptyPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getEmptyCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedPayCardAndRandomInvalidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndRandomInvalidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedPayCardAndEmptyOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndEmptyOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyRandomCardNumberAndValidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getRandomCardNumberAndValidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyEmptyCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getEmptyCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedCreditCardAndRandomInvalidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndRandomInvalidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedCreditCardAndEmptyOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndEmptyOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyRandomCreditCardNumberAndValidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getRandomCardNumberAndValidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }
}
