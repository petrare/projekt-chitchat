package si.reberc.chitchat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Hello ChitChat!
 */
public class Chat {
    
    public static void seznamUporabnikov() {
        try {String uporabniki = Request.Get("http://chitchat.andrej.com/users")
                                  .execute()
                                  .returnContent().asString();
            System.out.println(uporabniki);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

    public static void prijava(String vzdevek) {
        try {
            URI uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", vzdevek).build();
            String responseBody = Request.Post(uri).execute().returnContent().asString();
            System.out.println(responseBody);

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static void odjava(String vzdevek) {
        try {
			URI uri = new URIBuilder("http://chitchat.andrej.com/users").addParameter("username", vzdevek).build();
            String responseBody = Request.Delete(uri).execute().returnContent().asString();
            System.out.println(responseBody);

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static List<Message> getMessages(String vzdevek) throws ClientProtocolException, IOException, URISyntaxException {
            URI uri = new URIBuilder("http://chitchat.andrej.com/messages").addParameter("username", vzdevek).build();
            String responseBody = Request.Get(uri).execute().returnContent().asString();     
            ObjectMapper mapper = new ObjectMapper();
    		mapper.setDateFormat(new ISO8601DateFormat());
    		TypeReference<List<Message>> t = new TypeReference<List<Message>>() { };
    		List<Message> messages = mapper.readValue(responseBody, t);
  

    	return messages;
    }
    
    
	public static void SendMessage(boolean global, String vzdevekPosiljatelja, String vzdevekPrejemnika, String message){
		URI uri;
		ObjectMapper mapper = new ObjectMapper();
		String responseBody = null;
		try {
			uri = new URIBuilder("http://chitchat.andrej.com/messages").addParameter("username", vzdevekPosiljatelja).build();
						  String jsonMessage = mapper.writeValueAsString(new Message(global, vzdevekPrejemnika, message));
			  responseBody = Request.Post(uri)
			          .bodyString(jsonMessage, ContentType.APPLICATION_JSON)
			          .execute()
			          .returnContent()
			          .asString();
		} catch (URISyntaxException e1) {
			System.out.println(e1.getMessage());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(responseBody);
	}}

