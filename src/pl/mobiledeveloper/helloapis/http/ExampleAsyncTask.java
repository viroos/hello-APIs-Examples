package pl.mobiledeveloper.helloapis.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import pl.mobiledeveloper.R;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ExampleAsyncTask extends AsyncTask<URI, Void, Drawable> {
	
	Downloader downloader;
	Activity activity;
	
	ExampleAsyncTask(Downloader downloader, Activity activity){
		this.downloader = downloader;
		this.activity = activity;
	}


    protected void onPostExecute(Drawable drawable) {
    	
        ImageView image = (ImageView) activity.findViewById(R.id.xkcdImageView);
	    image.setImageDrawable(drawable);


    }

	@Override
	protected Drawable doInBackground(URI... uris) {
		String imageJSON = downloader.download(uris[0]);
		XkcdJSONParser xkcdJSONParser = new XkcdJSONParser(imageJSON);
		String imageUrl = xkcdJSONParser.getImageUrl();

		
		Drawable drawable = null;
			try {
				URL url = new URL(imageUrl);

				InputStream content;
				content = (InputStream)url.getContent();
		    	drawable = Drawable.createFromStream(content , "src");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return drawable;
	}

}
