package edu.dartmouth.cs.camcon;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;

public class CameraDemoActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 111;
	private File output = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// create Intent to take a picture and return control to the calling
		// application
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// set up the path name and create a file to save the image
		File dir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

		output = new File(dir, "CameraDemo.jpeg");
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

		// start the image capture Intent
		startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check the request is for taking a photo and OK
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				// fire the intent to view the photo in gallery or another app
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(output), "image/jpeg");
				startActivity(i);
				finish();
			}
		}
	}
}