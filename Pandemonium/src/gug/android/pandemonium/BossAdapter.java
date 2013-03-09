package gug.android.pandemonium;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BossAdapter extends SimpleAdapter {
	

	private List<HashMap<String, String>> items;
	
	public BossAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to) {
		super(context, items, resource, from, to);
		
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	  View view = super.getView(position, convertView, parent);
	  
	 
	  HashMap<String, String> map = items.get(position);
	  
	  
	 
	  if (map.get("kills").equals("0"))
	  {
	
		  view.setBackgroundResource(R.drawable.raid_gradient_ko);
	  }
	  else
	  {
		 
		  view.setBackgroundResource(R.drawable.raid_gradient_ok);
		  
	  }
	  
	 
	  
	  return view;
	}
}