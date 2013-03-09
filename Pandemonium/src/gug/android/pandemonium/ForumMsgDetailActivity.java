package gug.android.pandemonium;

import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ForumMsgDetailActivity  extends Activity  {
	
	private String link;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_forumdetail);
        
        Bundle bundle = this.getIntent().getExtras();
        
     
		String title = bundle.getString("title");
		String author = bundle.getString("author");
		link = bundle.getString("link");
		String content = bundle.getString("content");
        
        
        TextView tv_title = (TextView) this.findViewById(R.id.TextDetailTitle);
        tv_title.setText(title);
        
        
        WebView wb_content = (WebView) this.findViewById(R.id.WbDetailContent);
        //wb_content.loadData(content, "text/html","UTF-8");
        
        wb_content.getSettings().setDefaultTextEncodingName("utf-8"); 
        wb_content.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        
    }
    
    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( ForumMsgDetailActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}

    
    public void BtnLinkEvent(View v)
	{
    	
    	URL MessageLink;
    	try {
    		MessageLink = new URL(link);
    		
    		} catch (MalformedURLException e) {
    			throw new RuntimeException(e);
    		}
    	
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, 
	    Uri.parse(MessageLink.toExternalForm()));
		startActivity(viewMessage);
		
	}
    

}
