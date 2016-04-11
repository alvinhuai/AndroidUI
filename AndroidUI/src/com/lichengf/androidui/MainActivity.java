package com.lichengf.androidui;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;


public class MainActivity extends ActionBarActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.ActionBar mActionBar = getActionBar();
        // do not show app icon in left of ActionBar
        mActionBar.setDisplayShowHomeEnabled(false);
        // show app title in left of ActionBar
        mActionBar.setDisplayShowTitleEnabled(true);
        getOverFlowMenu();
    }
    
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
    	// without this function setOverflowIconVisible , the menu icon do not show
    	setOverflowIconVisible(featureId,menu);
    	return super.onMenuOpened(featureId, menu);
    }
    
    private void setOverflowIconVisible(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {  
                    Method m = menu.getClass().getDeclaredMethod(  
                            "setOptionalIconsVisible", Boolean.TYPE);  
                    m.setAccessible(true);  
                    m.invoke(menu, true);  
                } catch (Exception e) {  
                    Log.d("OverflowIconVisible", e.getMessage());  
                }
            }  
        }  
    }  
    
    private void getOverFlowMenu() {
    	try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyId = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyId != null) {
				menuKeyId.setAccessible(true);
				menuKeyId.setBoolean(config, false);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
        case R.id.id_action_settings:
        	return true;
		default:
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
}
