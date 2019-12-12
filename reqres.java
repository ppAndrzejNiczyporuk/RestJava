import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class reqres {
	
	
	public static void  main(String[] args)
	{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("reqres.in")
					
					.setPath("/api/users")
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		reqResGet(uri,httpClient);
		
		String stringAdd = "{\"name\": \"Andrzej\", \"job\": \"Niczyporuk\" }";
		
		reqResPost(uri,httpClient, stringAdd);
		
		String stringUpdate  = "{\"name\": \"Andrzej\", \"job\": \"Niczyporuk22\" }";
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("reqres.in")
					
					.setPath("/api/users/2")
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		reqResPut(uri,httpClient, stringAdd);
		
		reqResDelete(uri,httpClient);
		
		String stringLogin = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("reqres.in")
					
					.setPath("/api/login")
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		reqResPostLogin(uri,httpClient, stringLogin);
	}
	
	private static void reqResPostLogin(URI uri, CloseableHttpClient httpClient, String stringLogin) {
		System.out.println("reqResPostLogin");
		HttpPost request = new HttpPost(uri);
		StringEntity strEntity = new StringEntity(stringLogin, "UTF-8");
		strEntity.setContentType("application/json");
		request.setEntity(strEntity);
		request.setHeader("Content-type", "application/json");
		request.addHeader(HttpHeaders.USER_AGENT, "Andrzej-Client");
		String result ="";
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			
			// Get HttpResponse Status
			//System.out.println(response.getStatusLine().toString());
			if (response.getStatusLine().toString().contains("200")){
				HttpEntity entity = response.getEntity();
				Header headers = entity.getContentType();
				
				if (entity != null) {
					// return it as a String
					result = EntityUtils.toString(entity);
					System.out.println(result);
				}
			}else
			{
				System.out.println(response.getStatusLine().toString());
				return;
			}
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = result.indexOf("{");
		result = result.substring(i);
		//sprawdzenie czy 200 była odpowiedź
		
		JSONObject obj = new JSONObject(result.trim());
		String token= obj.get("token").toString();
		System.out.println("Pobrano Token : " + token  );
	}
	
	
	private static void reqResDelete(URI uri, CloseableHttpClient httpClient) {
		System.out.println("reqResDelete");
		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Content-type", "application/json");
		request.addHeader(HttpHeaders.USER_AGENT, "Andrzej-Client");
		String result ="";
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			
			// Get HttpResponse Status
			//System.out.println(response.getStatusLine().toString());
			if (response.getStatusLine().toString().contains("204")){
				 System.out.println("Usunięto");
			}else
			{
				System.out.println(response.getStatusLine().toString());
				return;
			}
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private static void reqResPut(URI uri, CloseableHttpClient httpClient, String stringAdd) {
		System.out.println("reqResPut");
		HttpPut request = new HttpPut(uri);
		StringEntity strEntity = new StringEntity(stringAdd, "UTF-8");
		strEntity.setContentType("application/json");
		request.setEntity(strEntity);
		request.setHeader("Content-type", "application/json");
		request.addHeader(HttpHeaders.USER_AGENT, "Andrzej-Client");
		String result ="";
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			
			// Get HttpResponse Status
			//System.out.println(response.getStatusLine().toString());
			if (response.getStatusLine().toString().contains("200")){
				HttpEntity entity = response.getEntity();
				Header headers = entity.getContentType();
				
				if (entity != null) {
					// return it as a String
					result = EntityUtils.toString(entity);
					System.out.println(result);
				}
			}else
			{
				System.out.println(response.getStatusLine().toString());
				return;
			}
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = result.indexOf("{");
		result = result.substring(i);
		JSONObject obj = new JSONObject(result.trim());
		String name= obj.get("name").toString();
		String job= obj.get("job").toString();
		String at= obj.get("updatedAt").toString();
		
		System.out.println("Zmieniono o:" + at  );
	}
	
	private static void reqResPost(URI uri, CloseableHttpClient httpClient, String stringAdd) {
		System.out.println("reqResPost");
		HttpPost request = new HttpPost(uri);
		StringEntity strEntity = new StringEntity(stringAdd, "UTF-8");
		strEntity.setContentType("application/json");
		request.setEntity(strEntity);
		request.setHeader("Content-type", "application/json");
		request.addHeader(HttpHeaders.USER_AGENT, "Andrzej-Client");
		String result ="";
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			
			// Get HttpResponse Status
			//System.out.println(response.getStatusLine().toString());
			if (response.getStatusLine().toString().contains("201")){
				HttpEntity entity = response.getEntity();
				Header headers = entity.getContentType();
				
				if (entity != null) {
					// return it as a String
					result = EntityUtils.toString(entity);
					System.out.println(result);
				}
			}else
			{
				System.out.println(response.getStatusLine().toString());
				return;
			}
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = result.indexOf("{");
		result = result.substring(i);
		//sprawdzenie czy 200 była odpowiedź
		
		JSONObject obj = new JSONObject(result.trim());
		String name= obj.get("name").toString();
		String job= obj.get("job").toString();
		String id= obj.get("id").toString();
		
		System.out.println("Dodano Id: " + id  );
	}
	
	public static void reqResGet(URI uri, CloseableHttpClient httpClient){
		System.out.println("reqResGet");
		HttpGet request = new HttpGet(uri);
		request.addHeader("custom-key", "mkyong");
		request.addHeader(HttpHeaders.USER_AGENT, "Andrzej-Client");
		String result ="";
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			
			// Get HttpResponse Status
			//System.out.println(response.getStatusLine().toString());
			
			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			
			if (entity != null) {
				// return it as a String
				result = EntityUtils.toString(entity);
				System.out.println(result);
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = result.indexOf("{");
		result = result.substring(i);
		
		JSONObject obj = new JSONObject(result.trim());
		String page= obj.get("page").toString();
		System.out.println("Strona: " + page );
		/*String area= obj.get("area").toString();
		System.out.println(" ma " + area+ " km2 powierzchni!");*/
		JSONArray DataList= (JSONArray) obj.get("data");
		//TODO while  po licie i wyppisanie emaili
		JSONObject object= (JSONObject) DataList.get(0);
		String email= object.get("email").toString();
		System.out.println("Email  pierwszego elementu " + email);
	}
}
