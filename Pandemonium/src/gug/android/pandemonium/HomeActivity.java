package gug.android.pandemonium;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getApplicationContext()));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
                
                Intent launchNewIntent;
            	switch(position){               
                case 0: //R.id.menu_charte:
                	launchNewIntent = new Intent( HomeActivity.this,CharteActivity.class);
                	startActivity(launchNewIntent);
                	 break;
                    
                case 1:
                	launchNewIntent = new Intent( HomeActivity.this,ForumMsgListActivity.class);
                	startActivity(launchNewIntent);
                	 break;
                case 2:
                	launchNewIntent = new Intent( HomeActivity.this,TsActivity.class);
                	startActivity(launchNewIntent);
                	 break;
                case 3:
                	launchNewIntent = new Intent( HomeActivity.this,RaidActivity.class);
                	startActivityForResult(launchNewIntent, 0);
                	 break;
                    
                case 4:
                	launchNewIntent = new Intent( HomeActivity.this,VideoActivity.class);
                	startActivityForResult(launchNewIntent, 0);
                	 break;
                case 5:
                	launchNewIntent = new Intent( HomeActivity.this,LienActivity.class);
                	startActivityForResult(launchNewIntent, 0);
                	 break;
                	
                	
                }
                
                
                
                
                
                
            }
        });
    }
}