package gug.android.pandemonium;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class CharteActivity  extends Activity  {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_charte);
        
       WebView wb = (WebView) this.findViewById(R.id.wb);
        
        wb.getSettings().setJavaScriptEnabled(true); 
        wb.loadUrl("file:///android_asset/charte.html");
    }
    
    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( CharteActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}

    
}
