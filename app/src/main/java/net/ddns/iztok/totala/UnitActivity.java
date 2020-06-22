package net.ddns.iztok.totala;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class UnitActivity extends AppCompatActivity {
	private static String TAG = "TotalAnnihilation";

	ImageView unitpic, sidepic;
	LinearLayout movement, explosion, weapon1Layout, weapon2Layout, weapon3Layout, sound;
	TextView name, description, unitname, maxvelocity, brakerate, acceleration, turnrate, category, explodeas, weapon1, weapon2, weapon3, soundcategory, objectname, designation, version, footprintX, footprintZ, buildcostenergy, buildcostmetal, maxdamage, maxwaterdepth, maxslope, energyuse, buildtime;
	Unit u;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit);

		movement = findViewById(R.id.movementLayout);
		explosion = findViewById(R.id.explosionLayout);
		weapon1Layout = findViewById(R.id.weapon1Layout);
		weapon2Layout = findViewById(R.id.weapon2Layout);
		weapon3Layout = findViewById(R.id.weapon3Layout);
		sound = findViewById(R.id.soundLayout);

		unitpic = findViewById(R.id.unitpic);
		sidepic = findViewById(R.id.side);
		name = findViewById(R.id.name);
		description = findViewById(R.id.description);
		unitname = findViewById(R.id.unitname);
		maxvelocity = findViewById(R.id.maxvelocity);
		brakerate = findViewById(R.id.brakerate);
		acceleration = findViewById(R.id.acceleration);
		turnrate = findViewById(R.id.turnrate);
		category = findViewById(R.id.category);
		explodeas = findViewById(R.id.explodeas);
		weapon1 = findViewById(R.id.weapon1);
		weapon2 = findViewById(R.id.weapon2);
		weapon3 = findViewById(R.id.weapon3);
		soundcategory = findViewById(R.id.soundcategory);
		objectname = findViewById(R.id.objectname);
		designation = findViewById(R.id.designation);
		version = findViewById(R.id.version);
		footprintX = findViewById(R.id.footprintX);
		footprintZ = findViewById(R.id.footprintZ);
		buildcostenergy = findViewById(R.id.buildcostenergy);
		buildcostmetal = findViewById(R.id.buildcostmetal);
		maxdamage = findViewById(R.id.maxdamage);
		maxwaterdepth = findViewById(R.id.maxwaterdepth);
		maxslope = findViewById(R.id.maxslope);
		energyuse = findViewById(R.id.energyuse);
		buildtime = findViewById(R.id.buildtime);

		Bundle bundle = getIntent().getExtras();
		if (bundle.getString("data") != null) {
			// Ustvari enoto
			u = new Unit(bundle.getString("data"));

			// Naloži sliko enote
			Drawable slika;
			try {
				InputStream is = getAssets().open("unitpics/" + u.getUnitName().toLowerCase() + ".png");
				slika = Drawable.createFromStream(is, null);
			} catch (IOException e) {
				slika = getDrawable(R.drawable.error);
				Log.e(TAG, e.getMessage());
			}
			unitpic.setImageDrawable(slika);

			// Naloži sliko strani (ARM/CORE)
			try {
				InputStream is = getAssets().open("sides/" + u.getSide().toLowerCase() + ".png");
				slika = Drawable.createFromStream(is, null);
			} catch (IOException e) {
				slika = getDrawable(R.drawable.error);
				Log.e(TAG, e.getMessage());
			}
			sidepic.setImageDrawable(slika);

			name.setText(u.getName());
			description.setText(u.getDescription());
			unitname.setText(u.getUnitName());
			buildtime.setText(u.getBuildTime() + " worker seconds");
			if (u.getTurnRate() > 0) {  // Če se ne more obračati, se ne more premikati
				maxvelocity.setText(u.getMaxVelocity() + " pixels per tick");
				brakerate.setText(u.getBrakeRate() + " pixels per tick");
				acceleration.setText(u.getAcceleration() + " pixels per tick");
				turnrate.setText(u.getTurnRate() + " units per second");
			} else {
				movement.setVisibility(View.GONE);
			}
			category.setText(TextUtils.join("\n", u.getCategory().split(" ")));
			if (u.getExplodeAs().length() > 0) {
				explodeas.setText(u.getExplodeAs());
			} else {
				explosion.setVisibility(View.GONE);
			}
			if (u.getWeapon1() != null && u.getWeapon1().length() > 0) {
				weapon1.setText(u.getWeapon1());
			} else {
				weapon1Layout.setVisibility(View.GONE);
			}
			if (u.getWeapon2() != null && u.getWeapon2().length() > 0) {
				weapon2.setText(u.getWeapon2());
			} else {
				weapon2Layout.setVisibility(View.GONE);
			}
			if (u.getWeapon3() != null && u.getWeapon3().length() > 0) {
				weapon3.setText(u.getWeapon3());
			} else {
				weapon3Layout.setVisibility(View.GONE);
			}
			if (!u.getSoundCategory().equals("NONE")) {
				soundcategory.setText(u.getSoundCategory());
			} else {
				sound.setVisibility(View.GONE);
			}
			objectname.setText(u.getObjectName());
			designation.setText(u.getDesignation());
			version.setText(Integer.toString(u.getVersion()));
			footprintX.setText(Integer.toString(u.getFootprintX()));
			footprintZ.setText(Integer.toString(u.getFootprintZ()));
			buildcostenergy.setText(Float.toString(u.getBuildCostEnergy()));
			buildcostmetal.setText(Float.toString(u.getBuildCostMetal()));
			maxdamage.setText(Integer.toString(u.getMaxDamage()));
			maxwaterdepth.setText(Integer.toString(u.getMaxWaterDepth()));
			maxslope.setText(Integer.toString(u.getMaxSlope()));
			energyuse.setText(Float.toString(u.getEnergyUse()));
		}
	}

	// Prikaže podatke o "orožju", s katerim enota eksplodira
	public void showWeapon(View v) {
		Intent intent = new Intent();
		intent.setClass(this, WeaponViewerActivity.class);
		intent.putExtra("weapon", u.getExplodeAs());
		startActivity(intent);
	}

	// Prikaže podatke o orožju 1
	public void showWeapon1(View v) {
		Intent intent = new Intent();
		intent.setClass(this, WeaponViewerActivity.class);
		intent.putExtra("weapon", u.getWeapon1());
		startActivity(intent);
	}

	public void showWeapon2(View v) {
		Intent intent = new Intent();
		intent.setClass(this, WeaponViewerActivity.class);
		intent.putExtra("weapon", u.getWeapon2());
		startActivity(intent);
	}

	public void showWeapon3(View v) {
		Intent intent = new Intent();
		intent.setClass(this, WeaponViewerActivity.class);
		intent.putExtra("weapon", u.getWeapon3());
		startActivity(intent);
	}


	public void showSounds(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SoundViewerActivity.class);
		intent.putExtra("soundcategory", u.getSoundCategory());
		startActivity(intent);
	}
}
