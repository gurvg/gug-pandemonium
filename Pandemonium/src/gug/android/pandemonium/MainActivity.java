package gug.android.pandemonium;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebView wb = (WebView) this.findViewById(R.id.wb_accueil);
        wb.loadUrl("file:///android_asset/accueil.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       	Intent launchNewIntent;
    	switch(item.getItemId()){
        case R.id.menu_ts:
        	launchNewIntent = new Intent( MainActivity.this,TsActivity.class);
        	startActivity(launchNewIntent);
            return true; 
        case R.id.menu_charte:
        	launchNewIntent = new Intent( MainActivity.this,CharteActivity.class);
        	startActivity(launchNewIntent);
            return true; 
        case R.id.menu_lien:
        	launchNewIntent = new Intent( MainActivity.this,LienActivity.class);
        	startActivity(launchNewIntent);
            return true; 
        case R.id.menu_forum:
        	launchNewIntent = new Intent( MainActivity.this,ForumMsgListActivity.class);
        	startActivity(launchNewIntent);
            return true; 
            
        case R.id.menu_raid:
        	launchNewIntent = new Intent( MainActivity.this,RaidActivity.class);
        	startActivityForResult(launchNewIntent, 0);
            return true; 
            
        case R.id.menu_video:
        	launchNewIntent = new Intent( MainActivity.this,VideoActivity.class);
        	startActivityForResult(launchNewIntent, 0);
            return true; 
    	}
        return false;
    }
   
    
    
    
    
}
