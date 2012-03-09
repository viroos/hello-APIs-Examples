package pl.mobiledeveloper.helloapis.http;

import java.net.URI;
import java.net.URISyntaxException;

import pl.mobiledeveloper.R;


import android.app.Activity;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HelloApisExamplesActivity extends Activity {

	URI uri = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Resources res = getResources();
		try {
			uri = new URI(res.getString(R.string.URI));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setContentView(R.layout.main);
	}

	public void httpClientOnClick(View v) {
		ExampleAsyncTask asyncTask = new ExampleAsyncTask(
				new HttpClientDownloader(), this);
		asyncTask.execute(uri);
	}

	public void httpURLConnectionOnClick(View v) {
		ExampleAsyncTask asyncTask = new ExampleAsyncTask(
				new HttpUrlConnectionDownloader(), this);
		asyncTask.execute(uri);
	}
	
	public void clearImage(View v){
        ImageView image = (ImageView) findViewById(R.id.xkcdImageView);
	    image.setImageDrawable(null);
	}
}