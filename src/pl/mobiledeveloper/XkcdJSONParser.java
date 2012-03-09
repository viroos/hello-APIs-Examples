package pl.mobiledeveloper;

import org.json.JSONException;
import org.json.JSONObject;

public class XkcdJSONParser {
	
	JSONObject xkcdJSON;
	String imageUrl;
	
	public XkcdJSONParser(String rawJSON) {
		try {
			xkcdJSON = new JSONObject(rawJSON);
			imageUrl = xkcdJSON.getString("img");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getImageUrl(){
		return imageUrl;
	}

}
