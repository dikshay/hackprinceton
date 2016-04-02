package wolfram_java;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAInfo;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

import wolfram_java.utils.Constants;



import com.wolfram.alpha.WAEngine;

@Path("/wolfram")
public class API {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getResponse(@QueryParam("text1") String text1, 
			@QueryParam("text2") String text2) 
	{
		
		System.out.println("Request received ");
		WAEngine engine = new WAEngine();
        engine.setAppID(Constants.APP_ID);
        //engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        
        //for first input
        JSONArray jArray1 = new JSONArray();
        query.setInput(text1);
        try {
        	
			WAQueryResult queryResult = engine.performQuery(query);

        if (queryResult.isError()) {
            System.out.println("Query error");
            System.out.println("  error code: " + queryResult.getErrorCode());
            System.out.println("  error message: " + queryResult.getErrorMessage());
        } else if (!queryResult.isSuccess()) {
            System.out.println("Query was not understood; no results available.");
        } else {
            // Got a result.
            System.out.println("Successful query. Pods follow:\n");
            for (WAPod pod : queryResult.getPods()) {
            	System.out.println("inside pod");
                if (!pod.isError()) {
                    System.out.println(pod.getTitle());
                    System.out.println("------------");
                    
                    for (WASubpod subpod : pod.getSubpods()) {
                    	System.out.println("inside subpod");
                        for (Object element : subpod.getContents()) {
                        	JSONObject jobject = new JSONObject();
                        	jobject.put("title", pod.getTitle().toString());
                        	System.out.println("inside subpod element");
                            if (element instanceof WAPlainText) {
                            	System.out.println("plain text");
                                System.out.println(((WAPlainText) element).getText());
                                System.out.println("");
                                jobject.put("text", ((WAPlainText) element).getText());
                            }
                            if (element instanceof WAImage)
                            {
                            	System.out.println("******image*******");
                            	System.out.println(((WAImage) element).getURL());
                            	System.out.println("");
                            	jobject.put("image", ((WAImage) element).getURL());
                            }
                            jArray1.put(jobject);
                        }
                    }
                    System.out.println("");
                }
            }
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //end of first input
        
        //for second input
        JSONArray jArray2 = new JSONArray();
        query.setInput(text2);
        try {
        	
			WAQueryResult queryResult = engine.performQuery(query);

        if (queryResult.isError()) {
            System.out.println("Query error");
            System.out.println("  error code: " + queryResult.getErrorCode());
            System.out.println("  error message: " + queryResult.getErrorMessage());
        } else if (!queryResult.isSuccess()) {
            System.out.println("Query was not understood; no results available.");
        } else {
            // Got a result.
            System.out.println("Successful query. Pods follow:\n");
            for (WAPod pod : queryResult.getPods()) {
            	System.out.println("inside pod");
                if (!pod.isError()) {
                    System.out.println(pod.getTitle());
                    System.out.println("------------");
                    
                    for (WASubpod subpod : pod.getSubpods()) {
                    	System.out.println("inside subpod");
                        for (Object element : subpod.getContents()) {
                        	JSONObject jobject = new JSONObject();
                        	jobject.put("title", pod.getTitle().toString());
                        	System.out.println("inside subpod element");
                            if (element instanceof WAPlainText) {
                            	System.out.println("plain text");
                                System.out.println(((WAPlainText) element).getText());
                                System.out.println("");
                                jobject.put("text", ((WAPlainText) element).getText());
                            }
                            if (element instanceof WAImage)
                            {
                            	System.out.println("******image*******");
                            	System.out.println(((WAImage) element).getURL());
                            	System.out.println("");
                            	jobject.put("image", ((WAImage) element).getURL());
                            }
                            jArray2.put(jobject);
                        }
                    }
                    System.out.println("");
                }
            }
        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //end of second input
        JSONObject finalObject = new JSONObject();
        finalObject.put("first", jArray1);
        finalObject.put("second", jArray2);
		return finalObject.toString();
	}
}
