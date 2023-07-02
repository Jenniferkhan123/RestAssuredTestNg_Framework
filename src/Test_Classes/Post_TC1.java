package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods;
import Common_API_Methods.Common_Utility_Method;
import RequestRepository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC1 {
	@Test
	public static void execute() throws IOException {
		System.out.println("extractor method call");
		  
		for (int i =0 ; i <5 ; i++) {
			int statusCode = API_Methods.ResponseStatusCode(Post_Req_Repository.baseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_TC1());
		   	
		    if(statusCode==201)
		    {
		        System.out.println(statusCode);
		        
			    String ResponseBody = API_Methods.ResponseBody(Post_Req_Repository.baseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_TC1());
				//System.out.println( ResponseBody);
				
				String RequestBody = Post_Req_Repository.Post_TC1();
			    Common_Utility_Method.EvidenceCreator("Post TC1", RequestBody, ResponseBody, statusCode);
				
			    validator(RequestBody, ResponseBody);
			    break;
		    }
		    else
		    {
		    	//System.out.println("Incorrect StatusCode");
		    }
	      }
		}
	
     public static void validator (String RequestBody , String ResponseBody) {
    	 JsonPath JspRequest = new JsonPath(RequestBody);
         String Req_name = JspRequest.getString("name");
         String Req_job = JspRequest.getString("job");
         LocalDateTime currentdate = LocalDateTime.now();
         String expecteddate = currentdate.toString().substring(0,10);
         
         JsonPath JspResponse = new JsonPath(ResponseBody);
         String Res_name = JspResponse.getString("name");
         //System.out.println(Res_name);
         String Res_job = JspResponse.getString("job");
         //System.out.println(Res_job);
         String Res_id = JspResponse.getString("id");
         //System.out.println(Res_id);
         String Res_createdAt  = JspResponse.getString("createdAt ");
         Res_createdAt  = Res_createdAt .substring(0,10);
         //System.out.println(Res_createdAt );
         
         Assert.assertEquals(Res_name, Req_name);
         Assert.assertEquals(Res_job,Req_job);
         Assert.assertNotEquals(Res_id,0);
         Assert.assertNotEquals(Res_id,null);
         Assert.assertEquals(Res_createdAt ,expecteddate);
         
     }
}
