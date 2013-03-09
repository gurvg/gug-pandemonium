package gug.android.pandemonium;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
 
public class CopyOfRaidActivity extends ListActivity {
 
    // url to make request
    //private static String url = "http://api.androidhive.info/contacts/";
    
    private static String url = "http://eu.battle.net/api/wow/character/varimathras/candidia?fields=progression";
    
    private ListView list;
    
    private ArrayList<HashMap<String, String>> bossList;
    private ArrayList<HashMap<String, String>> raidList;
    
    // JSON Node names
    private static final String TAG_PROGRESSION = "progression";
    private static final String TAG_RAIDS = "raids";
    private static final String TAG_RAIDNAME = "name";
    private static final String TAG_BOSSES = "bosses";
    private static final String TAG_BOSSNAME = "name";

 
    // contacts JSONArray
    JSONObject progression = null;
    
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raid);
                
        new ProgressTask(CopyOfRaidActivity.this).execute(url);
      
    }
    
    public void onRadioButtonClicked(View view) {
    	 new ProgressTask(CopyOfRaidActivity.this).execute(url);
       
    }
    
    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( CopyOfRaidActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}
    
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Bundle bundle = new Bundle();
		bundle.putString("raidname", raidList.get(position).get("raidname"));
		//bundle.putString("author", messages.get(position).getAuthor() + " / " +  messages.get(position).getDate());
		//bundle.putString("date", messages.get(position).getDate());
		//bundle.putString("link", messages.get(position).getLink().toString());
		//bundle.putString("content", messages.get(position).getDescription());
		
		Intent intent = new Intent(CopyOfRaidActivity.this, BossActivity.class);
		intent.putExtras(bundle);
		
		startActivity(intent);
	
		
	}

    
    

    class ProgressTask extends AsyncTask<String, Void, Boolean> {

    	private List<Message> messages;
    	private ProgressDialog dialog;
    	private Context context;
    	
        /** application context. */
        private CopyOfRaidActivity activity;
    	
    	public ProgressTask(CopyOfRaidActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

    	@Override
    	protected void onPreExecute() {
    		this.dialog.setMessage("Traitement en cours");
    		dialog.show();
    	
    	}
    	
        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            
            if (success)
            {
                       
            String[] from = { "raidname", "nbbosskill"};
	        int[] to = { R.id.TextRow01, R.id.TextRow02  };

	        SimpleAdapter adapter = new SimpleAdapter(activity, raidList,R.layout.forum_row, from, to);
	                   
            activity.setListAdapter(adapter);
            
            adapter.notifyDataSetChanged();
            
            }

        }
        
    
        protected Boolean doInBackground(String... urls) {
            
        	// Hashmap for ListView
            bossList = new ArrayList<HashMap<String, String>>();
            raidList = new ArrayList<HashMap<String, String>>();
     
            
            // Creating JSON Parser instance
            JSONParser jParser = new JSONParser();
     
            // getting JSON string from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            
            if (json == null)
            return false;
            else
            {
     
            try {
                // Getting Array of Contacts
            	
            	progression = json.getJSONObject(TAG_PROGRESSION);
            	
            	JSONArray raids = progression.getJSONArray(TAG_RAIDS);
            	
            	
                // looping through All Contacts
                for(int i = 0; i < raids.length(); i++)
                {
                    JSONObject c = raids.getJSONObject(i);
                   
                    String raidname = c.getString(TAG_RAIDNAME);
     
                    // Storing each json item in variable
                              
                   if (raidname.equals("Heart of Fear")
                		   || raidname.equals("Terrace of Endless Spring")
                		   || raidname.equals("Mogu'shan Vaults")
                		   || raidname.equals("Throne of Thunder")
                		   )
                	  
                   {
                	   
                	                  	
                	   JSONArray bosses = c.getJSONArray(TAG_BOSSES);
                	  
                	   Integer nbbosskill = 0;
                	   Integer nbboss = bosses.length();
                	                   	   
                	   for(int j = 0; j < bosses.length(); j++){
                           JSONObject c2 = bosses.getJSONObject(j);
                           
                      String bossname = c2.getString(TAG_BOSSNAME);
                      
                      
                      RadioGroup radioModeGroup;
                      RadioButton radioModeButton;
                     
                      radioModeGroup = (RadioGroup) activity.findViewById(R.id.radioMode);
                      
                   // get selected radio button from radioGroup
          			int selectedId = radioModeGroup.getCheckedRadioButtonId();
           
          			// find the radiobutton by returned id
          		     radioModeButton = (RadioButton) activity.findViewById(selectedId);
                      
                                           
                      String kills, time;
                      kills = "0";
                      time = "0";
                     
                      // pour certains boss il n y a pas de mode normal
                      // d ou le try catch
                      try
                  	{                      
                      if (radioModeButton.getText().equals("Normal"))
                      {       
                    	
                    	  kills = c2.getString("normalKills");
                    	  time = c2.getString("normalTimestamp");
                    
                     
                      }
                      else
                      {
                    	  kills = c2.getString("heroicKills");
                    	  time = c2.getString("heroicTimestamp"); 
                      }

                  	 } catch (JSONException e) {
                         e.printStackTrace();
                           
                     }
                      
                      if (!kills.equals("0")) nbbosskill +=1;
                        
                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();
     
                    // adding each child node to HashMap key => value
                   
                    String Killdatestring = "";
                    
                    if (!time.equals("0"))
                    {
                    	
                    	long dv = Long.valueOf(time);// its need to be in milisecond
                    	Date df = new java.util.Date(dv);
                    	Killdatestring = new SimpleDateFormat("dd/MM/yyyy").format(df);
                    	
                    }
                 
                    map.put("raidname", raidname);
                    map.put("bossname", bossname);
                    map.put("kills", kills);
                    map.put("time", Killdatestring);
                   
                    // adding HashList to ArrayList
                    bossList.add(map);
                    }
                	   
                	   // creating new HashMap
                       HashMap<String, String> mapraid = new HashMap<String, String>();
                	   
                       mapraid.put("raidname", raidname);                       
                       mapraid.put("nbbosskill", nbbosskill + " / " + nbboss );
                                            
                       // adding HashList to ArrayList
                       raidList.add(mapraid);
                	                   	   
                    }
                }
                return true;
                
            } catch (JSONException e) {
                e.printStackTrace();
                bossList.clear();
                raidList.clear();
                return false;
            }
            }
        	
        }
           
    }
    
    
    
 
}