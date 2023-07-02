package Test_Classes;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Put_API_Methods;
import RequestRepository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC1 {
	@Test
public static void execute() {
		
		int statusCode = Put_API_Methods.ResponseStatusCode(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_TC1());
        System.out.println(statusCode);

        String ResponseBody = Put_API_Methods.ResponseBody(Put_Req_Repository.BaseURI(),Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_TC1());
        //System.out.println(ResponseBody);
        
        JsonPath JspResponse = new JsonPath(ResponseBody);
        String Res_name = JspResponse.getString("name");
        System.out.println(Res_name);
        
        String Res_job = JspResponse.getString("job");
        System.out.println(Res_job);
        
        String Res_updatedAt = JspResponse.getString("updatedAt");
        System.out.println(Res_updatedAt);
        
        Assert.assertEquals(Res_name, "morpheus");
        Assert.assertEquals(Res_job, "zion resident");
	}
}
