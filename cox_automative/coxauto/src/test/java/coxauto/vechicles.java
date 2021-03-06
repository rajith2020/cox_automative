package coxauto;

import io.restassured.response.Response;
 
import org.junit.Test;


//import org.testng.annotations.*;
public class vechicles  extends Common
{
String[] vechicles_arrays;
    /*
        Test case 1 : Generate a new datasetid
     */
   @Test
    public void newdatasetid(){
        Response response = Common.getInstance().generateDatasetId(true);
        Common.getInstance().verifyStatusCodes(response,200,"HTTP/1.1 200 OK");
             
    }
    /*
    Test case 2 : Generate a new invalid datasetid
 */
    @Test
public void Nodatasetid(){
    Response response = Common.getInstance().generateDatasetId(false);
   Common.getInstance().verifyStatusCodes(response,404,"HTTP/1.1 404 Not Found");
         
}
    /*
    Test case 3 : Generate a new vechicle with datasetid
 */
    @Test
public void NewVechicleswithdatasetid(){
    Response response = Common.getInstance().generateDatasetId(true);
    Common.getInstance().verifyStatusCodes(response,200,"HTTP/1.1 200 OK");     
    generateVechiclesBydataset(true,getDatasetIdvalue(response));   
    Common.getInstance().verifyStatusCodes(response_vechi,200,"HTTP/1.1 200 OK");
    printvechicles(response_vechi);
   
}
/*
Test case 3 : Generate a new vechicle without datasetid error message
*/

    @Test
public void NewVechicleswithoutdatasetid(){
    Response response = Common.getInstance().generateDatasetId(false);
    Common.getInstance().verifyStatusCodes(response,404,"HTTP/1.1 404 Not Found");
    generateVechiclesBydataset(false,"");
    Common.getInstance().verifyStatusCodes(response,404,"HTTP/1.1 404 Not Found");
   
}
    /*
    Test case 4 : Print  a new vechicles with details  
    */
   @Test //@org.testng.annotations.Test(priority = 5)
public void NewVechicledetailswithdatasetid(){
    Response response = Common.getInstance().generateDatasetId(true);
    Common.getInstance().verifyStatusCodes(response,200,"HTTP/1.1 200 OK");    
    generateVechiclesBydataset(true,getDatasetIdvalue(response));
    Common.getInstance().verifyStatusCodes(response_vechi,200,"HTTP/1.1 200 OK");
    printvechicledetails(getDatasetIdvalue(response),response_vechi);
   
}
   /*
   Test case 5 : 404 when you try with invalid vechicleid   
   */
   @Test //@org.testng.annotations.Test(priority = 5)
   public void NewVechicledetailswithinvalidvechid(){
       Response response = Common.getInstance().generateDatasetId(true);
       Common.getInstance().verifyStatusCodes(response,200,"HTTP/1.1 200 OK");    
       generateVechiclesBydataset(false,"");
       System.out.print(response_vechi.getStatusCode());
       Common.getInstance().verifyStatusCodes(response_vechi,404,"HTTP/1.1 404 Not Found");
      
      
   }

}



