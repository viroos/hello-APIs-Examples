package pl.mobiledeveloper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpUrlConnectionDownloader implements Downloader {

	@Override
	public String download(URI uri) {

		String responseString = null;

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			URL url = uri.toURL();
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());

			reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer sb = new StringBuffer("");
			String line = null;
			String NL = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				sb.append(line + NL);
			}
			responseString = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			urlConnection.disconnect();
			if (reader != null) {
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
