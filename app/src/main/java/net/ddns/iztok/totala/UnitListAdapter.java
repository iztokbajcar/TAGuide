package net.ddns.iztok.totala;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UnitListAdapter extends ArrayAdapter<SimpleUnit> implements View.OnClickListener {
	private ArrayList<SimpleUnit> units;
	Context context;

	private static class ViewHolder {
		ImageView iv;
		TextView unitname;
		TextView name;
	}

	public UnitListAdapter(ArrayList<SimpleUnit> data, Context context) {
		super(context, R.layout.unitlistview, data);
		this.units = data;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		int pos = (Integer) v.getTag();
		Object object = getItem(pos);
		//SimpleUnit unit = (SimpleUnit) object;

		switch (v.getId()) {
			case R.id.iv:
				Toast.makeText(context, "Klikniuuuuuuuuuuuu!", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private int lastPosition = -1;

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		SimpleUnit unit = getItem(pos);
		ViewHolder viewHolder;
		final View result;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.unitlistview, parent, false);

			viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
			viewHolder.unitname = (TextView) convertView.findViewById(R.id.unitname);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);

			//result = convertView;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			//result = convertView;
		}

		/*Animation animation = AnimationUtils.loadAnimation(context, (pos > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
		result.startAnimation(animation);
		lastPosition = pos;*/

		// Naloži sliko
		Drawable slika;
		try {
			InputStream is = context.getAssets().open("unitpics/" + unit.getUnitpic().toLowerCase() + ".png");
			slika = Drawable.createFromStream(is, null);
		} catch (IOException e) {
			slika = context.getDrawable(R.drawable.error);
			Log.e("TotalAnnihilation", e.getMessage());
		}
		viewHolder.iv.setImageDrawable(slika);
		viewHolder.unitname.setText(unit.getUnitpic());
		viewHolder.name.setText(unit.getName());

		return convertView;

	}
}
