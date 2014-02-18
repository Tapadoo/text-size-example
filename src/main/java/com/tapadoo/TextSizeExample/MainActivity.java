package com.tapadoo.TextSizeExample;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity implements OnNavigationListener {
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.nav_options,android.R.layout.simple_spinner_dropdown_item);
        
        getActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
        getActionBar().setDisplayShowTitleEnabled(false);
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {

		FragmentTransaction ft = getFragmentManager().beginTransaction(); 
		if( itemPosition == 0 )
		{
			ft.replace(android.R.id.content, TextSizingFragment.create() );
		}
		else if( itemPosition == 1)
		{
			ft.replace(android.R.id.content, BoxSizingFragment.create() );
		}
		else if( itemPosition == 2)
		{
			ft.replace(android.R.id.content, BoxModelFragment.create() );
		}
		
		ft.commit() ;
		return true;
	}
}