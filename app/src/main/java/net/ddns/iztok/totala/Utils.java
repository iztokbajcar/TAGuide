package net.ddns.iztok.totala;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

	public static MediaPlayer player = new MediaPlayer();
	public static final String TAG = "TotalAnnihilation";

	// Predvaja zvok
	public static void playSound(Context c, View v, String sound) {
		if (player.isPlaying()) {
			player.stop();
		}
		try {
			AssetFileDescriptor descriptor = c.getAssets().openFd("sounds/" + sound + ".WAV");
			player.reset();
			player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			player.prepare();
			player.start();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}

	}

	// Naloži Bitmap iz assetov
	public static Bitmap getBmpFromAsset(Context context, String path) {
		AssetManager manager = context.getAssets();
		InputStream is;
		Bitmap bmp = null;
		try {
			is = manager.open(path);
			bmp = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
		return bmp;
	}
}
