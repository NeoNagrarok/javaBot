package BotThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Pattern {

	private static final long serialVersionUID = 1L;

	private String template;
	private String response;
	private ArrayList<String> responseType = new ArrayList<>();

	public Pattern(String template, String response) {
		this.template = template;
		this.response = response;
		this.responseType.add("notroll");
		this.responseType.add("shuffle");
		this.responseType.add("giphy");
	}

	public String getTemplate() {
		return this.template;
	}

	public String getResponse() {
		String response = "";
		try {
			response = (String) Pattern.class.getDeclaredMethod(
				this.responseType.get(
					(int)(Math.random() * this.responseType.size())
				)
			).invoke(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private String notroll()
	{
		return this.response;
	}

	private String shuffle()
	{
		List<Character> chars = this.response.chars().mapToObj(e->(char)e).collect(Collectors.toList());;
    	Collections.shuffle(chars);
    	return chars.toString()
			.substring(1, 3 * chars.size() - 1)
			.replaceAll(", ", "");
	}

	private String giphy()
	{
		try {
			JsonObject jsonObject = this.MyGETRequest("https://api.giphy.com/v1/gifs/search?api_key=0kDGaUr8lKSVNNGsEFLoEp7MXEl2JV6n&q=" + URLEncoder.encode(this.response) + "&limit=1&offset=0&rating=g&lang=en");

			if (jsonObject != null)
			{
				/**
				 * export to specialized method
				 */
				return jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("images").getAsJsonObject().get("original").getAsJsonObject().get("url").toString();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Je n'ai rien à te dire.";
	}

	public JsonObject MyGETRequest(String url) throws IOException {
		URL urlForGetRequest = new URL(url);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
		int responseCode = conection.getResponseCode();
	
	
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in .readLine()) != null) {
				response.append(readLine);
			} in .close();
			// print result
			// System.out.println("JSON String Result " + response.toString());
			return new JsonParser().parse(response.toString()).getAsJsonObject();

			//GetAndPost.POSTRequest(response.toString());
		} else {
			System.out.println("GET NOT WORKED : " + responseCode);
			return null;
		}
	
	}
}
