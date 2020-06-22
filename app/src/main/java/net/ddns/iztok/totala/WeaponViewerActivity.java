package net.ddns.iztok.totala;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;

public class WeaponViewerActivity extends AppCompatActivity {

	private static final String TAG = "TotalAnnihilation";
	private int currentImage = 0;
	private int currentWaterImage = 0;
	private int currentLavaImage = 0;
	private Handler handler;
	private LinearLayout layout;
	private Runnable drawRunnable;

	private ImageView explosionView, waterExplosionView, lavaExplosionView;
	private TextView explosionAnimProgress, waterAnimProgress, lavaAnimProgress;
	private TextView weaponname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weapon_viewer);

		layout = findViewById(R.id.weaponLayout);
		weaponname = findViewById(R.id.weaponname);

		Bundle bundle = getIntent().getExtras();
		if (bundle.getString("weapon") != null) {

			weaponname.setText(bundle.getString("weapon"));

			ArrayList<Pair<String, String>> data = getWeaponInfo(bundle.getString("weapon"));


			// Pridobi "explosionarte"
			String explosionart = "";
			String waterexplosionart = "";
			String lavaexplosionart = "";

			for (Pair<String, String> i : data) {
				// Doda layout s podatki o lastnosti
				LinearLayout l = new LinearLayout(this);
				l.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				l.setOrientation(LinearLayout.HORIZONTAL);

				TextView t = new TextView(this);

				int st = 20 - (i.first.length() + 1);
				StringBuilder sb = new StringBuilder();
				for (int n = 0; n < st; n++) {
					sb.append(" ");
				}

				t.setText(i.first + ":" + sb);
				t.setTextColor(Color.WHITE);
				//t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				t.setTypeface(Typeface.MONOSPACE);
				l.addView(t);
				TextView t2 = new TextView(this);
				t2.setText(i.second);
				//t2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				l.addView(t2);

				layout.addView(l);

				if (i.first.equals("explosionart") || i.first.equals("waterexplosionart") || i.first.equals("lavaexplosionart")) {
					LinearLayout l2 = new LinearLayout(this);
					l2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
					l2.setGravity(Gravity.CENTER);
					l2.setOrientation(LinearLayout.VERTICAL);
					l2.setPadding(20, 20, 20, 20);

					if (i.first.equals("explosionart")) {
						explosionart = i.second;
						explosionView = new ImageView(this);
						explosionView.setBackgroundColor(Color.parseColor("#5454fc"));
						explosionView.setLayoutParams(new LinearLayout.LayoutParams((int) (150 * getResources().getDisplayMetrics().density), (int) (150 * getResources().getDisplayMetrics().density)));
						l2.addView(explosionView);
						explosionAnimProgress = new TextView(this);
						explosionAnimProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						explosionAnimProgress.setGravity(Gravity.CENTER);
						l2.addView(explosionAnimProgress);
					}
					if (i.first.equals("waterexplosionart")) {
						waterexplosionart = i.second;
						waterExplosionView = new ImageView(this);
						waterExplosionView.setBackgroundColor(Color.parseColor("#5454fc"));
						waterExplosionView.setLayoutParams(new LinearLayout.LayoutParams((int) (150 * getResources().getDisplayMetrics().density), (int) (150 * getResources().getDisplayMetrics().density)));
						l2.addView(waterExplosionView);
						waterAnimProgress = new TextView(this);
						waterAnimProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						waterAnimProgress.setGravity(Gravity.CENTER);
						l2.addView(waterAnimProgress);
					}
					if (i.first.equals("lavaexplosionart")) {
						lavaexplosionart = i.second;
						lavaExplosionView = new ImageView(this);
						lavaExplosionView.setBackgroundColor(Color.parseColor("#5454fc"));
						lavaExplosionView.setLayoutParams(new LinearLayout.LayoutParams((int) (150 * getResources().getDisplayMetrics().density), (int) (150 * getResources().getDisplayMetrics().density)));
						l2.addView(lavaExplosionView);
						lavaAnimProgress = new TextView(this);
						lavaAnimProgress.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						lavaAnimProgress.setGravity(Gravity.CENTER);
						l2.addView(lavaAnimProgress);
					}

					layout.addView(l2);
				} else if (i.first.equals("soundstart") || i.first.equals("soundhit")) {
					Button b = new Button(this);
					final String sound = i.second;
					b.setText("Play sound");
					b.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Utils.playSound(getApplicationContext(), view, sound);
						}
					});
					l.addView(b);
				}

			}

			// Naloži slike
			final ArrayList<Drawable> explosionDrawables = loadImages(explosionart);
			final ArrayList<Drawable> waterExplosionDrawables = loadImages(waterexplosionart);
			final ArrayList<Drawable> lavaExplosionDrawables = loadImages(lavaexplosionart);

			handler = new Handler();

			drawRunnable = new Runnable() {
				@Override
				public void run() {
					if (explosionDrawables != null) {
						explosionView.setImageDrawable(explosionDrawables.get(currentImage));
						currentImage++;
						if (currentImage >= explosionDrawables.size()) currentImage = 0;
						explosionAnimProgress.setText((currentImage + 1) + " / " + explosionDrawables.size());
					}

					if (waterExplosionDrawables != null) {
						waterExplosionView.setImageDrawable(waterExplosionDrawables.get(currentWaterImage));
						currentWaterImage++;
						if (currentWaterImage >= waterExplosionDrawables.size()) currentWaterImage = 0;
						waterAnimProgress.setText((currentWaterImage + 1) + " / " + waterExplosionDrawables.size());
					}

					if (lavaExplosionDrawables != null) {
						lavaExplosionView.setImageDrawable(lavaExplosionDrawables.get(currentLavaImage));
						currentLavaImage++;
						if (currentLavaImage >= lavaExplosionDrawables.size()) currentLavaImage = 0;
						lavaAnimProgress.setText((currentLavaImage + 1) + " / " + lavaExplosionDrawables.size());
					}

					handler.postDelayed(drawRunnable, 100);
				}
			};
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (drawRunnable != null) handler.removeCallbacks(drawRunnable);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (drawRunnable != null) drawRunnable.run();
	}

	// Pridobi podatke o posamezni eksploziji
	public ArrayList<Drawable> loadImages(String uri) {
		AssetManager manager = this.getAssets();
		ArrayList<Drawable> images = new ArrayList<>();
		try {
			String[] s = manager.list(uri);
			Log.w(TAG, "Dolžina: " + s.length);
			for (String i : s) {
				Drawable d;
				try {
					Log.w(TAG, uri + File.separator + i);
					InputStream is = getAssets().open(uri + File.separator + i);
					images.add(Drawable.createFromStream(is, null));
				} catch (IOException e2) {
					Log.e(TAG, e2.getMessage());
					return null;
				}
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
		return images;
	}

	// Prebere podatke iz weapons.txt
	public ArrayList<Pair<String, String>> getWeaponInfo(String category) {
		ArrayList<Pair<String, String>> data = new ArrayList<>();
		try {
			InputStream is = getAssets().open("weapons.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			boolean bere = false;  // Ali je že prišel mimo oznake kategorije
			while ((line = br.readLine()) != null) {

				if (line.equals("[" + category.toUpperCase() + "]")) {
					// Prišel do oznake kategorije; spusti naslednjo vrsto, ker je zaviti oklepaj
					bere = true;
				} else if (bere && !line.equals("\t{") && !line.equals("\t}") && !line.equals("\t[DAMAGE]") && !(line.trim().length() == 0) && line.indexOf("//") == -1) {
					String[] keyValue = line.trim().split("=");
					Log.w(TAG, line);
					data.add(new Pair<>(keyValue[0].trim(), keyValue[1].trim().substring(0, keyValue[1].trim().indexOf(";")).toUpperCase()));
					//Log.w(TAG, line);
				} else if (bere && (line.equals("\t}") || line.equals("\t[DAMAGE]"))) {
					break;
				}
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
		return data;
	}

}
