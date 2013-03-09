package gug.android.pandemonium;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
 
public class BossActivity extends Activity {
 
    // url to make request
    //private static String url = "http://api.androidhive.info/contacts/";
    
    private static String url = "http://eu.battle.net/api/wow/character/varimathras/candidia?fields=progression";
    
    private ListView list;
    
    private ArrayList<HashMap<String, String>> bossList;
          
 
    // contacts JSONArray
    JSONObject progression = null;
    
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
                        
        Bundle bundle = this.getIntent().getExtras();
        String raidname = bundle.getString("raidname");
        ArrayList<HashMap<String, String>> bossList;
        bossList = (ArrayList<HashMap<String, String>>) bundle.getSerializable("bosslist");
                
        BossAdapter adapter = new BossAdapter(this, bossList,
                R.layout.boss_row,
                new String[] { "bossname","time"}, new int[] {
                          R.id.boss , R.id.normalTimestamp});
 
        list=(ListView)this.findViewById(R.id.list);     
        list.setAdapter(adapter);
        
      
    }
    

    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( BossActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}
    
       
 
}