package coxauto;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.HttpClientConfig.HttpClientFactory;
import io.restassured.http.ContentType;
 
import io.restassured.response.Response;
 
 
import io.restassured.specification.RequestSpecification;

 

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.junit.Assert;

 



public class Common {

    final String BASEURI = "https://api.coxauto-interview.com/api";
    private static Common common = null;
    public RequestSpecification httpRequest = null;
      String datasetId;
      ArrayList<String> datasetid_value;
	 String[] vechicles_array;
	String vechicle_details;
	Response response_vechi;
	Response response_details;
	String vechicle_number;


     Common(){
        RestAssured.baseURI = BASEURI;
        final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() {
            protected boolean isRedirectable(String method) {
                return true;
            }
        };
         

        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().httpClientFactory(new HttpClientFactory() {
			public HttpClient createHttpClient() {
			    
				DefaultHttpClient httpClient = new DefaultHttpClient();
			    httpClient.setRedirectStrategy(redirectStrategy);
			    return httpClient;
			}
		}));

        httpRequest= RestAssured.given();
    }

    public static Common getInstance(){
        if(common == null){
            return new Common();
        }
        else {
            return common;
        }
    }

    public Response generateDatasetId(boolean withDatasetId){
        //https://deckofcardsapi.com/api/deck/new/
    	 Response response;
        if(withDatasetId) {
             response =  httpRequest.contentType(ContentType.JSON).get("/datasetId");

           
        }else
        {
             response=httpRequest.when().get("");
        }
		return response;
    }

    public  String getDatasetIdvalue(Response response){
        //https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/
    	 this.datasetId=response.jsonPath().get("datasetId"); 
    	 
		return datasetId;
    }
   
    public Response generateVechiclesBydataset(boolean withDatasetId,String dataseid){
        //https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/
    	System.out.println(withDatasetId+""+dataseid);	
    	 if(withDatasetId) {
    		 response_vechi=httpRequest.contentType(ContentType.JSON).get("/"+dataseid+"/vehicles");
    		 
           
        }else
        {
        	response_vechi=httpRequest.when().get("//vehicles");
        }
    	 
		return response_vechi;
         
    }
    
    public  void printvechicledetails(String dataseid,Response response){
    	 
    		 try{  
    			  
    	 for(String value:listOfvechicle(response)){			 
				 
    			 response_details=httpRequest.contentType(ContentType.JSON).get("/"+dataseid+"/vehicles/"+value);
    			 String Details=response_details.getBody().asString().toString();	
    			 System.out.println ( Details); 
    	 }
    		 }
    	 catch(Exception e){
    		 System.out.println(e.getStackTrace());
    	 }
	
    		 
    	
    }
    public  String[] listOfvechicle(Response response){
    	 vechicle_number=response.jsonPath().get("vehicleIds").toString();   		     		  
		    
  		vechicles_array=vechicle_number.toString().replace("[","").replace("]","").trim().split(",");
		return vechicles_array;
    }
    public  void printvechicles(Response response){
    	  try {
    		 
    			 for(String value: listOfvechicle(response)){
    	    			//String url="http://api.coxauto-interview.com/api/"+datasetId_value+"/vehicles/"+Integer.parseInt(value.trim());				
    	    				//Response datasetid=given().when().get(url);
    	    				//response_code();
    	    				System.out.println("The list of vechicle found "+value);
    	    				//System.out.println(str);	 
    	    				//System.out.println(datasetid);
    	    		  
    	    		 }
    		 }
    		 
    		  catch(Exception e){
    			  
    			  System.out.println(e.getStackTrace());
    		  }
    		 }
    		 
    		 
  

	
    

	public void verifyStatusCodes(Response response,int statusCode, String statusLine ){
      //Assert.assertEquals("application/json; charset=utf-8",response.header("Content-Type"));      
       Assert.assertEquals(statusCode,response.getStatusCode());
       Assert.assertEquals(statusLine,response.getStatusLine());
    }
}
