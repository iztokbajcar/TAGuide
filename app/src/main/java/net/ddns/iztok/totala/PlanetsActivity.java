package net.ddns.iztok.totala;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlanetsActivity extends AppCompatActivity {

	private String TAG = "TotalAnnihilation";

	private Bitmap archiBmp, archiRotateBmp, archiPan0, archiPan1, archiPan2, archiPan3;
	private Bitmap desertBmp, desertRotateBmp, desertPan0, desertPan1, desertPan2, desertPan3;
	private Bitmap greenBmp, greenRotateBmp, greenPan0, greenPan1, greenPan2, greenPan3;
	private Bitmap lavaBmp, lavaRotateBmp, lavaPan0, lavaPan1, lavaPan2, lavaPan3;
	private Bitmap lunar2Bmp, lunar2RotateBmp, lunar2Pan0, lunar2Pan1, lunar2Pan2, lunar2Pan3;
	private Bitmap lunarBmp, lunarRotateBmp, lunarPan0, lunarPan1, lunarPan2, lunarPan3;
	private Bitmap marsBmp, marsRotateBmp, marsPan0, marsPan1, marsPan2, marsPan3;
	private Bitmap metalBmp, metalRotateBmp, metalPan0, metalPan1, metalPan2, metalPan3;
	private Bitmap wetBmp, wetRotateBmp, wetPan0, wetPan1, wetPan2, wetPan3;
	private Canvas archiCan, archiRotateCan;
	private Canvas desertCan, desertRotateCan;
	private Canvas greenCan, greenRotateCan;
	private Canvas lavaCan, lavaRotateCan;
	private Canvas lunar2Can, lunar2RotateCan;
	private Canvas lunarCan, lunarRotateCan;
	private Canvas marsCan, marsRotateCan;
	private Canvas metalCan, metalRotateCan;
	private Canvas wetCan, wetRotateCan;
	private ImageView archiImage, archiRotate;
	private ImageView desertImage, desertRotate;
	private ImageView greenImage, greenRotate;
	private ImageView lavaImage, lavaRotate;
	private ImageView lunar2Image, lunar2Rotate;
	private ImageView lunarImage, lunarRotate;
	private ImageView marsImage, marsRotate;
	private ImageView metalImage, metalRotate;
	private ImageView wetImage, wetRotate;
	private Paint paint;

	private ArrayList<Bitmap> archiRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> desertRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> greenRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> lavaRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> lunar2RotateImages = new ArrayList<>();
	private ArrayList<Bitmap> lunarRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> marsRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> metalRotateImages = new ArrayList<>();
	private ArrayList<Bitmap> wetRotateImages = new ArrayList<>();
	private int archiRotateIndex = 0;
	private int desertRotateIndex = 0;
	private int greenRotateIndex = 0;
	private int lavaRotateIndex = 0;
	private int lunar2RotateIndex = 0;
	private int lunarRotateIndex = 0;
	private int marsRotateIndex = 0;
	private int metalRotateIndex = 0;
	private int wetRotateIndex = 0;

	private Handler handler = new Handler();
	private Runnable drawRunnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planets);

		archiImage = findViewById(R.id.archiPan);
		archiRotate = findViewById(R.id.archiRotate);
		desertImage = findViewById(R.id.desertPan);
		desertRotate = findViewById(R.id.desertRotate);
		greenImage = findViewById(R.id.greenPan);
		greenRotate = findViewById(R.id.greenRotate);
		lavaImage = findViewById(R.id.lavaPan);
		lavaRotate = findViewById(R.id.lavaRotate);
		lunar2Image = findViewById(R.id.lunar2Pan);
		lunar2Rotate = findViewById(R.id.lunar2Rotate);
		lunarImage = findViewById(R.id.lunarPan);
		lunarRotate = findViewById(R.id.lunarRotate);
		marsImage = findViewById(R.id.marsPan);
		marsRotate = findViewById(R.id.marsRotate);
		metalImage = findViewById(R.id.metalPan);
		metalRotate = findViewById(R.id.metalRotate);
		wetImage = findViewById(R.id.wetPan);
		wetRotate = findViewById(R.id.wetRotate);
		paint = new Paint();

		final int sizeMultiplier = 4;

		// Naloži slike
		archiPan0 = Utils.getBmpFromAsset(this, "Archibrief/ArchiPan00.BMP");
		archiPan1 = Utils.getBmpFromAsset(this, "Archibrief/ArchiPan01.BMP");
		archiPan2 = Utils.getBmpFromAsset(this, "Archibrief/ArchiPan02.BMP");
		archiPan3 = Utils.getBmpFromAsset(this, "Archibrief/ArchiPan03.BMP");
		desertPan0 = Utils.getBmpFromAsset(this, "Desertbrief/DDesPan00.BMP");
		desertPan1 = Utils.getBmpFromAsset(this, "Desertbrief/DDesPan01.BMP");
		desertPan2 = Utils.getBmpFromAsset(this, "Desertbrief/DDesPan02.BMP");
		desertPan3 = Utils.getBmpFromAsset(this, "Desertbrief/DDesPan03.BMP");
		greenPan0 = Utils.getBmpFromAsset(this, "Greenbrief/GreenPan00.BMP");
		greenPan1 = Utils.getBmpFromAsset(this, "Greenbrief/GreenPan01.BMP");
		greenPan2 = Utils.getBmpFromAsset(this, "Greenbrief/GreenPan02.BMP");
		greenPan3 = Utils.getBmpFromAsset(this, "Greenbrief/GreenPan03.BMP");
		lavaPan0 = Utils.getBmpFromAsset(this, "Lavabrief/LavaPan00.BMP");
		lavaPan1 = Utils.getBmpFromAsset(this, "Lavabrief/LavaPan01.BMP");
		lavaPan2 = Utils.getBmpFromAsset(this, "Lavabrief/LavaPan02.BMP");
		lavaPan3 = Utils.getBmpFromAsset(this, "Lavabrief/LavaPan03.BMP");
		lunar2Pan0 = Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Pan00.BMP");
		lunar2Pan1 = Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Pan01.BMP");
		lunar2Pan2 = Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Pan02.BMP");
		lunar2Pan3 = Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Pan03.BMP");
		lunarPan0 = Utils.getBmpFromAsset(this, "Lunarbrief/LunarPan00.BMP");
		lunarPan1 = Utils.getBmpFromAsset(this, "Lunarbrief/LunarPan01.BMP");
		lunarPan2 = Utils.getBmpFromAsset(this, "Lunarbrief/LunarPan02.BMP");
		lunarPan3 = Utils.getBmpFromAsset(this, "Lunarbrief/LunarPan03.BMP");
		marsPan0 = Utils.getBmpFromAsset(this, "Marsbrief/MarsPan00.BMP");
		marsPan1 = Utils.getBmpFromAsset(this, "Marsbrief/MarsPan01.BMP");
		marsPan2 = Utils.getBmpFromAsset(this, "Marsbrief/MarsPan02.BMP");
		marsPan3 = Utils.getBmpFromAsset(this, "Marsbrief/MarsPan03.BMP");
		metalPan0 = Utils.getBmpFromAsset(this, "Metalbrief/MetalPan00.BMP");
		metalPan1 = Utils.getBmpFromAsset(this, "Metalbrief/MetalPan01.BMP");
		metalPan2 = Utils.getBmpFromAsset(this, "Metalbrief/MetalPan02.BMP");
		metalPan3 = Utils.getBmpFromAsset(this, "Metalbrief/MetalPan03.BMP");
		wetPan0 = Utils.getBmpFromAsset(this, "WDesertbrief/WDesPan00.BMP");
		wetPan1 = Utils.getBmpFromAsset(this, "WDesertbrief/WDesPan01.BMP");
		wetPan2 = Utils.getBmpFromAsset(this, "WDesertbrief/WDesPan02.BMP");
		wetPan3 = Utils.getBmpFromAsset(this, "WDesertbrief/WDesPan03.BMP");

		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate00.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate01.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate02.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate03.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate04.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate05.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate06.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate07.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate08.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate09.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate10.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate11.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate12.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate13.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate14.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate15.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate16.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate17.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate18.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate19.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate20.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate21.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate22.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate23.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate24.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate25.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate26.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate27.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate28.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate29.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate30.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate31.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate32.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate33.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate34.BMP"));
		archiRotateImages.add(Utils.getBmpFromAsset(this, "Archibrief/ArchiRotate35.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate00.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate01.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate02.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate03.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate04.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate05.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate06.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate07.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate08.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate09.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate10.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate11.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate12.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate13.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate14.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate15.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate16.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate17.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate18.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate19.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate20.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate21.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate22.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate23.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate24.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate25.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate26.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate27.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate28.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate29.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate30.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate31.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate32.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate33.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate34.BMP"));
		desertRotateImages.add(Utils.getBmpFromAsset(this, "Desertbrief/DDesRotate35.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate00.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate01.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate02.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate03.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate04.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate05.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate06.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate07.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate08.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate09.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate10.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate11.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate12.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate13.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate14.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate15.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate16.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate17.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate18.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate19.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate20.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate21.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate22.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate23.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate24.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate25.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate26.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate27.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate28.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate29.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate30.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate31.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate32.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate33.BMP"));
		greenRotateImages.add(Utils.getBmpFromAsset(this, "Greenbrief/GreenRotate34.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate00.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate01.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate02.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate03.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate04.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate05.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate06.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate07.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate08.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate09.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate10.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate11.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate12.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate13.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate14.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate15.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate16.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate17.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate18.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate19.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate20.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate21.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate22.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate23.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate24.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate25.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate26.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate27.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate28.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate29.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate30.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate31.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate32.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate33.BMP"));
		lavaRotateImages.add(Utils.getBmpFromAsset(this, "Lavabrief/LavaRotate34.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate00.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate01.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate02.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate03.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate04.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate05.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate06.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate07.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate08.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate09.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate10.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate11.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate12.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate13.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate14.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate15.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate16.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate17.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate18.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate19.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate20.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate21.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate22.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate23.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate24.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate25.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate26.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate27.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate28.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate29.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate30.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate31.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate32.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate33.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate34.BMP"));
		lunar2RotateImages.add(Utils.getBmpFromAsset(this, "Lunar2Brief/Lunar2Rotate35.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate00.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate01.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate02.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate03.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate04.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate05.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate06.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate07.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate08.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate09.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate10.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate11.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate12.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate13.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate14.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate15.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate16.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate17.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate18.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate19.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate20.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate21.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate22.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate23.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate24.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate25.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate26.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate27.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate28.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate29.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate30.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate31.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate32.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate33.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate34.BMP"));
		lunarRotateImages.add(Utils.getBmpFromAsset(this, "Lunarbrief/LunarRotate35.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate00.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate01.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate02.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate03.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate04.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate05.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate06.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate07.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate08.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate09.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate10.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate11.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate12.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate13.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate14.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate15.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate16.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate17.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate18.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate19.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate20.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate21.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate22.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate23.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate24.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate25.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate26.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate27.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate28.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate29.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate30.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate31.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate32.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate33.BMP"));
		marsRotateImages.add(Utils.getBmpFromAsset(this, "Marsbrief/MarsRotate34.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate00.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate01.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate02.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate03.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate04.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate05.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate06.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate07.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate08.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate09.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate10.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate11.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate12.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate13.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate14.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate15.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate16.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate17.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate18.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate19.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate20.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate21.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate22.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate23.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate24.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate25.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate26.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate27.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate28.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate29.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate30.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate31.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate32.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate33.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate34.BMP"));
		metalRotateImages.add(Utils.getBmpFromAsset(this, "Metalbrief/MetalRotate35.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate00.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate01.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate02.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate03.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate04.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate05.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate06.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate07.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate08.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate09.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate10.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate11.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate12.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate13.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate14.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate15.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate16.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate17.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate18.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate19.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate20.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate21.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate22.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate23.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate24.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate25.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate26.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate27.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate28.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate29.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate30.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate31.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate32.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate33.BMP"));
		wetRotateImages.add(Utils.getBmpFromAsset(this, "WDesertbrief/WDesertRotate34.BMP"));

		archiBmp = Bitmap.createBitmap(archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier + archiPan2.getWidth() * sizeMultiplier + archiPan3.getWidth() * sizeMultiplier, archiPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		archiCan = new Canvas(archiBmp);
		archiCan.drawBitmap(archiPan0, new Rect(0, 0, archiPan0.getWidth(), archiPan0.getHeight()), new Rect(0, 0, archiPan0.getWidth() * sizeMultiplier, archiPan0.getHeight() * sizeMultiplier), paint);
		archiCan.drawBitmap(archiPan1, new Rect(0, 0, archiPan1.getWidth(), archiPan1.getHeight()), new Rect(archiPan0.getWidth() * sizeMultiplier, 0, archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier, archiPan1.getHeight() * sizeMultiplier), paint);
		archiCan.drawBitmap(archiPan2, new Rect(0, 0, archiPan2.getWidth(), archiPan2.getHeight()), new Rect(archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier, 0, archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier + archiPan2.getWidth() * sizeMultiplier, archiPan2.getHeight() * sizeMultiplier), paint);
		archiCan.drawBitmap(archiPan3, new Rect(0, 0, archiPan3.getWidth(), archiPan3.getHeight()), new Rect(archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier + archiPan2.getWidth() * sizeMultiplier, 0, archiPan0.getWidth() * sizeMultiplier + archiPan1.getWidth() * sizeMultiplier + archiPan2.getWidth() * sizeMultiplier + archiPan3.getWidth() * sizeMultiplier, archiPan3.getHeight() * sizeMultiplier), paint);
		archiImage.setImageBitmap(archiBmp);
		archiRotateBmp = Bitmap.createBitmap(archiRotateImages.get(0).getWidth() * 4, archiRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		archiRotateCan = new Canvas(archiRotateBmp);
		archiRotate.setImageBitmap(archiRotateBmp);

		desertBmp = Bitmap.createBitmap(desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier + desertPan2.getWidth() * sizeMultiplier + desertPan3.getWidth() * sizeMultiplier, desertPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		desertCan = new Canvas(desertBmp);
		desertCan.drawBitmap(desertPan0, new Rect(0, 0, desertPan0.getWidth(), desertPan0.getHeight()), new Rect(0, 0, desertPan0.getWidth() * sizeMultiplier, desertPan0.getHeight() * sizeMultiplier), paint);
		desertCan.drawBitmap(desertPan1, new Rect(0, 0, desertPan1.getWidth(), desertPan1.getHeight()), new Rect(desertPan0.getWidth() * sizeMultiplier, 0, desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier, desertPan1.getHeight() * sizeMultiplier), paint);
		desertCan.drawBitmap(desertPan2, new Rect(0, 0, desertPan2.getWidth(), desertPan2.getHeight()), new Rect(desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier, 0, desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier + desertPan2.getWidth() * sizeMultiplier, desertPan2.getHeight() * sizeMultiplier), paint);
		desertCan.drawBitmap(desertPan3, new Rect(0, 0, desertPan3.getWidth(), desertPan3.getHeight()), new Rect(desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier + desertPan2.getWidth() * sizeMultiplier, 0, desertPan0.getWidth() * sizeMultiplier + desertPan1.getWidth() * sizeMultiplier + desertPan2.getWidth() * sizeMultiplier + desertPan3.getWidth() * sizeMultiplier, desertPan3.getHeight() * sizeMultiplier), paint);
		desertImage.setImageBitmap(desertBmp);
		desertRotateBmp = Bitmap.createBitmap(desertRotateImages.get(0).getWidth() * 4, desertRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		desertRotateCan = new Canvas(desertRotateBmp);
		desertRotate.setImageBitmap(desertRotateBmp);

		greenBmp = Bitmap.createBitmap(greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier + greenPan2.getWidth() * sizeMultiplier + greenPan3.getWidth() * sizeMultiplier, greenPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		greenCan = new Canvas(greenBmp);
		greenCan.drawBitmap(greenPan0, new Rect(0, 0, greenPan0.getWidth(), greenPan0.getHeight()), new Rect(0, 0, greenPan0.getWidth() * sizeMultiplier, greenPan0.getHeight() * sizeMultiplier), paint);
		greenCan.drawBitmap(greenPan1, new Rect(0, 0, greenPan1.getWidth(), greenPan1.getHeight()), new Rect(greenPan0.getWidth() * sizeMultiplier, 0, greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier, greenPan1.getHeight() * sizeMultiplier), paint);
		greenCan.drawBitmap(greenPan2, new Rect(0, 0, greenPan2.getWidth(), greenPan2.getHeight()), new Rect(greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier, 0, greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier + greenPan2.getWidth() * sizeMultiplier, greenPan2.getHeight() * sizeMultiplier), paint);
		greenCan.drawBitmap(greenPan3, new Rect(0, 0, greenPan3.getWidth(), greenPan3.getHeight()), new Rect(greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier + greenPan2.getWidth() * sizeMultiplier, 0, greenPan0.getWidth() * sizeMultiplier + greenPan1.getWidth() * sizeMultiplier + greenPan2.getWidth() * sizeMultiplier + greenPan3.getWidth() * sizeMultiplier, greenPan3.getHeight() * sizeMultiplier), paint);
		greenImage.setImageBitmap(greenBmp);
		greenRotateBmp = Bitmap.createBitmap(greenRotateImages.get(0).getWidth() * 4, greenRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		greenRotateCan = new Canvas(greenRotateBmp);
		greenRotate.setImageBitmap(greenRotateBmp);

		lavaBmp = Bitmap.createBitmap(lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier + lavaPan2.getWidth() * sizeMultiplier + lavaPan3.getWidth() * sizeMultiplier, lavaPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		lavaCan = new Canvas(lavaBmp);
		lavaCan.drawBitmap(lavaPan0, new Rect(0, 0, lavaPan0.getWidth(), lavaPan0.getHeight()), new Rect(0, 0, lavaPan0.getWidth() * sizeMultiplier, lavaPan0.getHeight() * sizeMultiplier), paint);
		lavaCan.drawBitmap(lavaPan1, new Rect(0, 0, lavaPan1.getWidth(), lavaPan1.getHeight()), new Rect(lavaPan0.getWidth() * sizeMultiplier, 0, lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier, lavaPan1.getHeight() * sizeMultiplier), paint);
		lavaCan.drawBitmap(lavaPan2, new Rect(0, 0, lavaPan2.getWidth(), lavaPan2.getHeight()), new Rect(lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier, 0, lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier + lavaPan2.getWidth() * sizeMultiplier, lavaPan2.getHeight() * sizeMultiplier), paint);
		lavaCan.drawBitmap(lavaPan3, new Rect(0, 0, lavaPan3.getWidth(), lavaPan3.getHeight()), new Rect(lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier + lavaPan2.getWidth() * sizeMultiplier, 0, lavaPan0.getWidth() * sizeMultiplier + lavaPan1.getWidth() * sizeMultiplier + lavaPan2.getWidth() * sizeMultiplier + lavaPan3.getWidth() * sizeMultiplier, lavaPan3.getHeight() * sizeMultiplier), paint);
		lavaImage.setImageBitmap(lavaBmp);
		lavaRotateBmp = Bitmap.createBitmap(lavaRotateImages.get(0).getWidth() * 4, lavaRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		lavaRotateCan = new Canvas(lavaRotateBmp);
		lavaRotate.setImageBitmap(lavaRotateBmp);

		lunar2Bmp = Bitmap.createBitmap(lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier + lunar2Pan2.getWidth() * sizeMultiplier + lunar2Pan3.getWidth() * sizeMultiplier, lunar2Pan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		lunar2Can = new Canvas(lunar2Bmp);
		lunar2Can.drawBitmap(lunar2Pan0, new Rect(0, 0, lunar2Pan0.getWidth(), lunar2Pan0.getHeight()), new Rect(0, 0, lunar2Pan0.getWidth() * sizeMultiplier, lunar2Pan0.getHeight() * sizeMultiplier), paint);
		lunar2Can.drawBitmap(lunar2Pan1, new Rect(0, 0, lunar2Pan1.getWidth(), lunar2Pan1.getHeight()), new Rect(lunar2Pan0.getWidth() * sizeMultiplier, 0, lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier, lunar2Pan1.getHeight() * sizeMultiplier), paint);
		lunar2Can.drawBitmap(lunar2Pan2, new Rect(0, 0, lunar2Pan2.getWidth(), lunar2Pan2.getHeight()), new Rect(lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier, 0, lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier + lunar2Pan2.getWidth() * sizeMultiplier, lunar2Pan2.getHeight() * sizeMultiplier), paint);
		lunar2Can.drawBitmap(lunar2Pan3, new Rect(0, 0, lunar2Pan3.getWidth(), lunar2Pan3.getHeight()), new Rect(lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier + lunar2Pan2.getWidth() * sizeMultiplier, 0, lunar2Pan0.getWidth() * sizeMultiplier + lunar2Pan1.getWidth() * sizeMultiplier + lunar2Pan2.getWidth() * sizeMultiplier + lunar2Pan3.getWidth() * sizeMultiplier, lunar2Pan3.getHeight() * sizeMultiplier), paint);
		lunar2Image.setImageBitmap(lunar2Bmp);
		lunar2RotateBmp = Bitmap.createBitmap(lunar2RotateImages.get(0).getWidth() * 4, lunar2RotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		lunar2RotateCan = new Canvas(lunar2RotateBmp);
		lunar2Rotate.setImageBitmap(lunar2RotateBmp);

		lunarBmp = Bitmap.createBitmap(lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier + lunarPan2.getWidth() * sizeMultiplier + lunarPan3.getWidth() * sizeMultiplier, lunarPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		lunarCan = new Canvas(lunarBmp);
		lunarCan.drawBitmap(lunarPan0, new Rect(0, 0, lunarPan0.getWidth(), lunarPan0.getHeight()), new Rect(0, 0, lunarPan0.getWidth() * sizeMultiplier, lunarPan0.getHeight() * sizeMultiplier), paint);
		lunarCan.drawBitmap(lunarPan1, new Rect(0, 0, lunarPan1.getWidth(), lunarPan1.getHeight()), new Rect(lunarPan0.getWidth() * sizeMultiplier, 0, lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier, lunarPan1.getHeight() * sizeMultiplier), paint);
		lunarCan.drawBitmap(lunarPan2, new Rect(0, 0, lunarPan2.getWidth(), lunarPan2.getHeight()), new Rect(lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier, 0, lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier + lunarPan2.getWidth() * sizeMultiplier, lunarPan2.getHeight() * sizeMultiplier), paint);
		lunarCan.drawBitmap(lunarPan3, new Rect(0, 0, lunarPan3.getWidth(), lunarPan3.getHeight()), new Rect(lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier + lunarPan2.getWidth() * sizeMultiplier, 0, lunarPan0.getWidth() * sizeMultiplier + lunarPan1.getWidth() * sizeMultiplier + lunarPan2.getWidth() * sizeMultiplier + lunarPan3.getWidth() * sizeMultiplier, lunarPan3.getHeight() * sizeMultiplier), paint);
		lunarImage.setImageBitmap(lunarBmp);
		lunarRotateBmp = Bitmap.createBitmap(lunarRotateImages.get(0).getWidth() * 4, lunarRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		lunarRotateCan = new Canvas(lunarRotateBmp);
		lunarRotate.setImageBitmap(lunarRotateBmp);

		marsBmp = Bitmap.createBitmap(marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier + marsPan2.getWidth() * sizeMultiplier + marsPan3.getWidth() * sizeMultiplier, marsPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		marsCan = new Canvas(marsBmp);
		marsCan.drawBitmap(marsPan0, new Rect(0, 0, marsPan0.getWidth(), marsPan0.getHeight()), new Rect(0, 0, marsPan0.getWidth() * sizeMultiplier, marsPan0.getHeight() * sizeMultiplier), paint);
		marsCan.drawBitmap(marsPan1, new Rect(0, 0, marsPan1.getWidth(), marsPan1.getHeight()), new Rect(marsPan0.getWidth() * sizeMultiplier, 0, marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier, marsPan1.getHeight() * sizeMultiplier), paint);
		marsCan.drawBitmap(marsPan2, new Rect(0, 0, marsPan2.getWidth(), marsPan2.getHeight()), new Rect(marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier, 0, marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier + marsPan2.getWidth() * sizeMultiplier, marsPan2.getHeight() * sizeMultiplier), paint);
		marsCan.drawBitmap(marsPan3, new Rect(0, 0, marsPan3.getWidth(), marsPan3.getHeight()), new Rect(marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier + marsPan2.getWidth() * sizeMultiplier, 0, marsPan0.getWidth() * sizeMultiplier + marsPan1.getWidth() * sizeMultiplier + marsPan2.getWidth() * sizeMultiplier + marsPan3.getWidth() * sizeMultiplier, marsPan3.getHeight() * sizeMultiplier), paint);
		marsImage.setImageBitmap(marsBmp);
		marsRotateBmp = Bitmap.createBitmap(marsRotateImages.get(0).getWidth() * 4, marsRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		marsRotateCan = new Canvas(marsRotateBmp);
		marsRotate.setImageBitmap(marsRotateBmp);

		metalBmp = Bitmap.createBitmap(metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier + metalPan2.getWidth() * sizeMultiplier + metalPan3.getWidth() * sizeMultiplier, metalPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		metalCan = new Canvas(metalBmp);
		metalCan.drawBitmap(metalPan0, new Rect(0, 0, metalPan0.getWidth(), metalPan0.getHeight()), new Rect(0, 0, metalPan0.getWidth() * sizeMultiplier, metalPan0.getHeight() * sizeMultiplier), paint);
		metalCan.drawBitmap(metalPan1, new Rect(0, 0, metalPan1.getWidth(), metalPan1.getHeight()), new Rect(metalPan0.getWidth() * sizeMultiplier, 0, metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier, metalPan1.getHeight() * sizeMultiplier), paint);
		metalCan.drawBitmap(metalPan2, new Rect(0, 0, metalPan2.getWidth(), metalPan2.getHeight()), new Rect(metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier, 0, metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier + metalPan2.getWidth() * sizeMultiplier, metalPan2.getHeight() * sizeMultiplier), paint);
		metalCan.drawBitmap(metalPan3, new Rect(0, 0, metalPan3.getWidth(), metalPan3.getHeight()), new Rect(metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier + metalPan2.getWidth() * sizeMultiplier, 0, metalPan0.getWidth() * sizeMultiplier + metalPan1.getWidth() * sizeMultiplier + metalPan2.getWidth() * sizeMultiplier + metalPan3.getWidth() * sizeMultiplier, metalPan3.getHeight() * sizeMultiplier), paint);
		metalImage.setImageBitmap(metalBmp);
		metalRotateBmp = Bitmap.createBitmap(metalRotateImages.get(0).getWidth() * 4, metalRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		metalRotateCan = new Canvas(metalRotateBmp);
		metalRotate.setImageBitmap(metalRotateBmp);

		wetBmp = Bitmap.createBitmap(wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier + wetPan2.getWidth() * sizeMultiplier + wetPan3.getWidth() * sizeMultiplier, wetPan0.getHeight() * sizeMultiplier, Bitmap.Config.ARGB_8888);
		wetCan = new Canvas(wetBmp);
		wetCan.drawBitmap(wetPan0, new Rect(0, 0, wetPan0.getWidth(), wetPan0.getHeight()), new Rect(0, 0, wetPan0.getWidth() * sizeMultiplier, wetPan0.getHeight() * sizeMultiplier), paint);
		wetCan.drawBitmap(wetPan1, new Rect(0, 0, wetPan1.getWidth(), wetPan1.getHeight()), new Rect(wetPan0.getWidth() * sizeMultiplier, 0, wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier, wetPan1.getHeight() * sizeMultiplier), paint);
		wetCan.drawBitmap(wetPan2, new Rect(0, 0, wetPan2.getWidth(), wetPan2.getHeight()), new Rect(wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier, 0, wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier + wetPan2.getWidth() * sizeMultiplier, wetPan2.getHeight() * sizeMultiplier), paint);
		wetCan.drawBitmap(wetPan3, new Rect(0, 0, wetPan3.getWidth(), wetPan3.getHeight()), new Rect(wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier + wetPan2.getWidth() * sizeMultiplier, 0, wetPan0.getWidth() * sizeMultiplier + wetPan1.getWidth() * sizeMultiplier + wetPan2.getWidth() * sizeMultiplier + wetPan3.getWidth() * sizeMultiplier, wetPan3.getHeight() * sizeMultiplier), paint);
		wetImage.setImageBitmap(wetBmp);
		wetRotateBmp = Bitmap.createBitmap(wetRotateImages.get(0).getWidth() * 4, wetRotateImages.get(0).getHeight() * 4, Bitmap.Config.ARGB_8888);
		wetRotateCan = new Canvas(wetRotateBmp);
		wetRotate.setImageBitmap(wetRotateBmp);

		drawRunnable = new Runnable() {
			@Override
			public void run() {
				archiRotateCan.drawBitmap(
						archiRotateImages.get(archiRotateIndex),
						new Rect(0, 0, archiRotateImages.get(archiRotateIndex).getWidth(), archiRotateImages.get(archiRotateIndex).getHeight()),
						new Rect(0, 0, archiRotateImages.get(archiRotateIndex).getWidth() * 4, archiRotateImages.get(archiRotateIndex).getHeight() * 4),
						paint
				);
				archiRotate.invalidate();
				archiRotateIndex++;
				if (archiRotateIndex == archiRotateImages.size()) archiRotateIndex = 0;

				desertRotateCan.drawBitmap(
						desertRotateImages.get(desertRotateIndex),
						new Rect(0, 0, desertRotateImages.get(desertRotateIndex).getWidth(), desertRotateImages.get(desertRotateIndex).getHeight()),
						new Rect(0, 0, desertRotateImages.get(desertRotateIndex).getWidth() * 4, desertRotateImages.get(desertRotateIndex).getHeight() * 4),
						paint
				);
				desertRotate.invalidate();
				desertRotateIndex++;
				if (desertRotateIndex == desertRotateImages.size()) desertRotateIndex = 0;

				greenRotateCan.drawBitmap(
					greenRotateImages.get(greenRotateIndex),
					new Rect(0, 0, greenRotateImages.get(greenRotateIndex).getWidth(), greenRotateImages.get(greenRotateIndex).getHeight()),
					new Rect(0, 0, greenRotateImages.get(greenRotateIndex).getWidth() * 4, greenRotateImages.get(greenRotateIndex).getHeight() * 4),
					paint
				);
				greenRotate.invalidate();
				greenRotateIndex++;
				if (greenRotateIndex == greenRotateImages.size()) greenRotateIndex = 0;

				lavaRotateCan.drawBitmap(
						lavaRotateImages.get(lavaRotateIndex),
						new Rect(0, 0, lavaRotateImages.get(lavaRotateIndex).getWidth(), lavaRotateImages.get(lavaRotateIndex).getHeight()),
						new Rect(0, 0, lavaRotateImages.get(lavaRotateIndex).getWidth() * 4, lavaRotateImages.get(lavaRotateIndex).getHeight() * 4),
						paint
				);
				lavaRotate.invalidate();
				lavaRotateIndex++;
				if (lavaRotateIndex == lavaRotateImages.size()) lavaRotateIndex = 0;

				lunar2RotateCan.drawBitmap(
						lunar2RotateImages.get(lunar2RotateIndex),
						new Rect(0, 0, lunar2RotateImages.get(lunar2RotateIndex).getWidth(), lunar2RotateImages.get(lunar2RotateIndex).getHeight()),
						new Rect(0, 0, lunar2RotateImages.get(lunar2RotateIndex).getWidth() * 4, lunar2RotateImages.get(lunar2RotateIndex).getHeight() * 4),
						paint
				);
				lunar2Rotate.invalidate();
				lunar2RotateIndex++;
				if (lunar2RotateIndex == lunar2RotateImages.size()) lunar2RotateIndex = 0;

				lunarRotateCan.drawBitmap(
						lunarRotateImages.get(lunarRotateIndex),
						new Rect(0, 0, lunarRotateImages.get(lunarRotateIndex).getWidth(), lunarRotateImages.get(lunarRotateIndex).getHeight()),
						new Rect(0, 0, lunarRotateImages.get(lunarRotateIndex).getWidth() * 4, lunarRotateImages.get(lunarRotateIndex).getHeight() * 4),
						paint
				);
				lunarRotate.invalidate();
				lunarRotateIndex++;
				if (lunarRotateIndex == lunarRotateImages.size()) lunarRotateIndex = 0;

				marsRotateCan.drawBitmap(
						marsRotateImages.get(marsRotateIndex),
						new Rect(0, 0, marsRotateImages.get(marsRotateIndex).getWidth(), marsRotateImages.get(marsRotateIndex).getHeight()),
						new Rect(0, 0, marsRotateImages.get(marsRotateIndex).getWidth() * 4, marsRotateImages.get(marsRotateIndex).getHeight() * 4),
						paint
				);
				marsRotate.invalidate();
				marsRotateIndex++;
				if (marsRotateIndex == marsRotateImages.size()) marsRotateIndex = 0;

				metalRotateCan.drawBitmap(
						metalRotateImages.get(metalRotateIndex),
						new Rect(0, 0, metalRotateImages.get(metalRotateIndex).getWidth(), metalRotateImages.get(metalRotateIndex).getHeight()),
						new Rect(0, 0, metalRotateImages.get(metalRotateIndex).getWidth() * 4, metalRotateImages.get(metalRotateIndex).getHeight() * 4),
						paint
				);
				metalRotate.invalidate();
				metalRotateIndex++;
				if (metalRotateIndex == metalRotateImages.size()) metalRotateIndex = 0;

				wetRotateCan.drawBitmap(
						wetRotateImages.get(wetRotateIndex),
						new Rect(0, 0, wetRotateImages.get(wetRotateIndex).getWidth(), wetRotateImages.get(wetRotateIndex).getHeight()),
						new Rect(0, 0, wetRotateImages.get(wetRotateIndex).getWidth() * 4, wetRotateImages.get(wetRotateIndex).getHeight() * 4),
						paint
				);
				wetRotate.invalidate();
				wetRotateIndex++;
				if (wetRotateIndex == wetRotateImages.size()) wetRotateIndex = 0;

				handler.postDelayed(drawRunnable, 100);
			}
		};

		//drawRunnable.run();
	}

	@Override
	protected void onPause() {
		super.onPause();
		handler.removeCallbacks(drawRunnable);
	}

	@Override
	protected void onResume() {
		super.onResume();
		handler.postDelayed(drawRunnable, 100);
	}
}
