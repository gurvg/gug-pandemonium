package gug.android.pandemonium;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v;
        if(convertView==null){
            //LayoutInflater li = getLayoutInflater();
            LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.menu_image, null);
            TextView tv = (TextView)v.findViewById(R.id.menu_text);
            tv.setText(mMenuIds[position]);            
            
            ImageView iv = (ImageView)v.findViewById(R.id.menu_image);
            iv.setImageResource(mThumbIds[position]);
            
           

        }
        else
        {
            v = convertView;
        }
        
        return v;
    }

    // references to our images
    private Integer[] mThumbIds = { 
    		R.drawable.menu_charte, 
    		R.drawable.menu_forum, 
    		R.drawable.menu_ts, 
    		R.drawable.menu_raid, 
    		R.drawable.menu_video, 
    		R.drawable.menu_lien
    		 };
    
    // references to our images
    public String[] mMenuIds = { 
    		"Charte", 
    		"Forum", 
    		"TeamSpeak", 
    		"Avancée Raid", 
    		"Vidéos", 
    		"Liens"
    		 };
    
}