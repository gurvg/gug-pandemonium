package gug.android.pandemonium;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlSerializer;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class VideoActivity extends ListActivity {
	
	private List<Message> messages;
	static String feedUrl = "http://gdata.youtube.com/feeds/base/users/NaShTyGaMinG/uploads?alt=atom";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        
        new ProgressTask(VideoActivity.this).execute(feedUrl);

    }
    
    public void BtnRefreshEvent(View v)
  	{       
    	new ProgressTask(VideoActivity.this).execute(feedUrl);
  	}
    
    public void BtnHomeEvent(View v)
  	{       
    	Intent launchNewIntent;
    	launchNewIntent = new Intent( VideoActivity.this,HomeActivity.class);
        startActivity(launchNewIntent);
  	}
    
    public void BtnBackEvent(View v)
  	{       
    	 this.onBackPressed();
  	}

    
    public void setMessages(List<Message> msgs) {
        messages = msgs;
     }

   
    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
    	for (Message msg : messages){
    		 list.add(putData(msg.getTitle(), msg.getAuthor() + " / " +  msg.getDate()));
    		
    	}
        
        return list;
      }

      private HashMap<String, String> putData(String name, String purpose) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("author", purpose);
        return item;
      }
    
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Bundle bundle = new Bundle();
		bundle.putString("title", messages.get(position).getTitle());
		bundle.putString("author", messages.get(position).getAuthor() + " / " +  messages.get(position).getDate());
		//bundle.putString("date", messages.get(position).getDate());
		bundle.putString("link", messages.get(position).getLink().toString());
		bundle.putString("content", messages.get(position).getDescription());
		
		Intent intent = new Intent(VideoActivity.this, ForumMsgDetailActivity.class);
		intent.putExtras(bundle);
		
		startActivity(intent);
	
		
	}
	
	    class ProgressTask extends AsyncTask<String, Void, Boolean> {

	    	private List<Message> messages;
	    	private ProgressDialog dialog;
	    	private Context context;
	    	
	        /** application context. */
	        private VideoActivity activity;
	    	
	    	public ProgressTask(VideoActivity activity) {
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
	            
	            ArrayList<Map<String, String>> list = buildData();
		    	
		        String[] from = { "name", "author"};
		        int[] to = { R.id.TextRow01, R.id.TextRow02  };

		        SimpleAdapter adapter = new SimpleAdapter(activity, list,R.layout.forum_row, from, to);
		       
		    	activity.setListAdapter(adapter);
	            
	            adapter.notifyDataSetChanged();
	            }

	        }
	    
	        protected Boolean doInBackground(String... urls) {
	            
	        	try{
	        		Log.i("AndroidNews", "ParserType=ANDROID_SAX");
	        		AndroidSaxFeedParser parser = new AndroidSaxFeedParser(urls[0]);
	    	    	long start = System.currentTimeMillis();
	    	    	messages = parser.parse();
	    	    	long duration = System.currentTimeMillis() - start;
	    	    	Log.i("AndroidNews", "Parser duration=" + duration);
	    	    	String xml = writeXml();
	    	    	Log.i("AndroidNews", xml);
	    	    	
	    	    	activity.setMessages(messages);
	    	    	
	    	    	
	    	    	//Thread.sleep(5000);
	    	    	return true;
	    	    	
	        	} catch (Throwable t){
	        		Log.e("AndroidNews",t.getMessage(),t);
	        		return false;
	        	}
	        
	        }
	           
	    	private String writeXml(){
	    		XmlSerializer serializer = Xml.newSerializer();
	    		StringWriter writer = new StringWriter();
	    		try {
	    			serializer.setOutput(writer);
	    			serializer.startDocument("UTF-8", true);
	    			serializer.startTag("", "messages");
	    			serializer.attribute("", "number", String.valueOf(messages.size()));
	    			for (Message msg: messages){
	    				serializer.startTag("", "message");
	    				serializer.attribute("", "date", msg.getDate());
	    				serializer.startTag("", "title");
	    				serializer.text(msg.getTitle());
	    				serializer.endTag("", "title");
	    				serializer.startTag("", "url");
	    				serializer.text(msg.getLink().toExternalForm());
	    				serializer.endTag("", "url");
	    				serializer.startTag("", "body");
	    				serializer.text(msg.getDescription());
	    				serializer.endTag("", "body");
	    				serializer.endTag("", "message");
	    			}
	    			serializer.endTag("", "messages");
	    			serializer.endDocument();
	    			return writer.toString();
	    		} catch (Exception e) {
	    			throw new RuntimeException(e);
	    		} 
	    	}
	     }
	    
	    
	    
    

}

