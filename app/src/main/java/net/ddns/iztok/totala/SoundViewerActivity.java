package net.ddns.iztok.totala;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class SoundViewerActivity extends AppCompatActivity {

	private static String TAG = "TotalAnnihilation";
	private LinearLayout layout;
	private TextView soundcat;
	private MediaPlayer player = new MediaPlayer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_viewer);

		layout = findViewById(R.id.soundActivityLayout);
		soundcat = findViewById(R.id.soundCategoryName);

		Bundle bundle = getIntent().getExtras();
		if (bundle.getString("soundcategory") != null) {
			soundcat.setText(bundle.getString("soundcategory"));
			ArrayList<Pair<String, String>> data = getSoundInfo(bundle.getString("soundcategory"));
			for (final Pair<String, String> i : data) {
				// Zgenerira layoute
				LinearLayout l = new LinearLayout(this);
				TextView name = new TextView(this);
				TextView sound = new TextView(this);
				Button play = new Button(this);

				l.setOrientation(LinearLayout.HORIZONTAL);
				l.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT
				));
				name.setText(i.first + ":    ");
				name.setTextColor(Color.WHITE);
				name.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT
				));
				sound.setText(i.second + ".WAV");
				sound.setLayoutParams(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT
				));
				play.setText("PLAY");
				play.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Utils.playSound(getApplicationContext(), view, i.second);
					}
				});
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT
				);
				play.setLayoutParams(params);

				l.addView(name);
				l.addView(sound);
				l.addView(play);
				layout.addView(l);
			}
		}
	}

	// Prebere podatke iz SOUND.TDF
	public ArrayList<Pair<String, String>> getSoundInfo(String category) {
		ArrayList<Pair<String, String>> data = new ArrayList<>();
		try {
			InputStream is = getAssets().open("SOUND.TDF");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			boolean bere = false;  // Ali je že prišel mimo oznake kategorije
			while ((line = br.readLine()) != null) {

				if (line.indexOf("[" + category.toUpperCase() + "]") > -1) {
					// Prišel do oznake kategorije; spusti naslednjo vrsto, ker je zaviti oklepaj
					bere = true;
					Log.w(TAG, "[" + category.toUpperCase() + "]");
				} else if (bere && !line.equals("\t{") && !line.equals("\t}") && line.indexOf("//") == -1) {
					Log.w(TAG, line);
					String[] keyValue = line.split("=");
					data.add(new Pair<String, String>(keyValue[0].trim(), keyValue[1].trim().substring(0, keyValue[1].trim().length() - 1).toUpperCase()));
				} else if (bere && line.equals("\t}")) {
					break;
				}
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
		return data;
	}

}
