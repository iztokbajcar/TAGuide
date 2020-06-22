package net.ddns.iztok.totala;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TotalAnnihilation";
    private UnitListAdapter adapter;
    private ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_planets:
                Intent intent = new Intent();
                intent.setClass(this, PlanetsActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //layout = findViewById(R.id.layout);
        listView = findViewById(R.id.listview);

        AssetManager manager = this.getAssets();
        try {
            final ArrayList<SimpleUnit> units = new ArrayList<>();
            String[] s = manager.list("unitpics");
            for (String i : s) {
                String n = i.substring(0, i.length() - 4).toUpperCase();
                units.add(new SimpleUnit(n, getName(n)));
            }
            adapter = new UnitListAdapter(units, getApplicationContext());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                    SimpleUnit unit = units.get(pos);
                    // Odpre stran s podatki o enoti
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), UnitActivity.class);
                    intent.putExtra("data", getUnitData(unit.getUnitpic()));
                    startActivity(intent);
                }
            });

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    // Pridobi podatke o posamezni enoti
    public String getUnitData(String unitName) {
    // Prebere allunits.txt
        try {
            InputStream is = getAssets().open("allunits.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] s = line.split("\t");
                if (s[0].equals(unitName.toUpperCase())) {
                    return line;
                }
            }
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    // Pridobi ime enote
    public String getName(String unitName) {
        try {
            InputStream is = getAssets().open("allunits.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] s = line.split("\t");
                //Log.w(TAG, s[0] + " " + s[5]);
                if (s[0].equals(unitName.toUpperCase())) {
                    return s[5];
                }
            }
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
