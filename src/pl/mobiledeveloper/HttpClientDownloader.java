package pl.mobiledeveloper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpClientDownloader implements Downloader {
	
	public String download(URI uri){
		
		String responseString = null;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet(uri);
		BufferedReader reader = null;
		try {
			HttpResponse response = httpClient.execute(request);
			Log.d("HttpClientDownloader.download","response.toString(): " + response.toString());
			
			 
			reader = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = null;
			String NL = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				sb.append(line + NL);
			}
			responseString = sb.toString();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(reader !=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return responseString;
		
	}

}
