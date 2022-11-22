import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class RestUsersTest {

    @Test
    public void getEmployeesOver30Test() {
        Response response = get("https://dummy.restapiexample.com/api/v1/employees");
        System.out.println(response.body().prettyPrint());
        List<String> employees1 = JsonPath.with(response.body().prettyPrint()).get("data.findAll { data -> data.employee_age > 30 }");
        System.out.println("Number of employees over 30: " + employees1.size());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void createUserTest() {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "testName");
        requestParams.put("salary", "123456");
        requestParams.put("age", "40");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post("https://dummy.restapiexample.com/api/v1/create");
        String responseBody = response.body().prettyPrint();
        System.out.println(responseBody);
        Assert.assertEquals(JsonPath.with(responseBody).get("status"), "success");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void updateUserTest() {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "testName");
        requestParams.put("salary", "123456");
        requestParams.put("age", "40");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.put("https://dummy.restapiexample.com/api/v1/update/1");
        String responseBody = response.body().prettyPrint();
        System.out.println(responseBody);
        Assert.assertEquals(JsonPath.with(responseBody).get("status"), "success");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void deleteUserTest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.delete("https://dummy.restapiexample.com/api/v1/delete/1");
        String responseBody = response.body().prettyPrint();
        System.out.println(responseBody);
        Assert.assertEquals(JsonPath.with(responseBody).get("status"), "success");
        Assert.assertEquals(response.statusCode(), 200);
    }
}
