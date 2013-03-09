package gug.android.pandemonium;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class LienActivity  extends Activity  {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_lien);
        
      WebView wb = (WebView) this.findViewById(R.id.wb);
        
        wb.getSettings().setJavaScriptEnabled(true); 
        wb.setScrollbarFadingEnabled(false);
        wb.loadUrl("file:///android_asset/liens.html");
    }
    
    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( LienActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}

    
}
