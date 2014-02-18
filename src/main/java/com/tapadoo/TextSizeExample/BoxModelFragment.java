package com.tapadoo.TextSizeExample;

import android.app.Fragment;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class BoxModelFragment extends Fragment implements OnSeekBarChangeListener {
	
	public static BoxModelFragment create()
	{
		return new BoxModelFragment();
	}
	
	private Handler handler = new Handler();

	private TextView tvBoxSizeText;
	private SeekBar sbTvLayoutMargin;
	private DisplayMetrics metrics = new DisplayMetrics();
	private TextView tvLayoutMarginVal;
	private TextView tvLayoutPaddingVal;
	private SeekBar sbTvLayoutPadding;
	private SeekBar sbTvHeight;
	private TextView tvHeightVal;
	private RelativeLayout rlBoxSizeWrapper;
	private TextView tvWrapperHeightActual;
	private TextView tvWrapperHeightVal;
	private SeekBar sbWrapperHeight;
	private Spinner spTextViewGravity;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		View view = getView();
		
		tvBoxSizeText = (TextView) view.findViewById(R.id.tvBoxSizeText);
		tvLayoutMarginVal = (TextView) view.findViewById(R.id.tvLayoutMarginVal);
		tvLayoutPaddingVal = (TextView) view.findViewById(R.id.tvLayoutPaddingVal);
		tvHeightVal = (TextView) view.findViewById(R.id.tvHeightVal);
		
		tvWrapperHeightActual = (TextView) view.findViewById(R.id.tvWrapperHeightActual);
		tvWrapperHeightVal = (TextView) view.findViewById(R.id.tvWrapperHeightVal);
		
		sbTvLayoutMargin = (SeekBar)view.findViewById(R.id.sbTvLayoutMargin);
		sbTvLayoutPadding = (SeekBar)view.findViewById(R.id.sbTvLayoutPadding);
		sbTvHeight = (SeekBar)view.findViewById(R.id.sbTvHeight);
		sbWrapperHeight = (SeekBar)view.findViewById(R.id.sbWrapperHeight);
		
		rlBoxSizeWrapper = (RelativeLayout) view.findViewById(R.id.rlBoxSizeWrapper);
		
		
		sbTvLayoutMargin.setOnSeekBarChangeListener(this);
		sbTvLayoutPadding.setOnSeekBarChangeListener(this);
		sbTvHeight.setOnSeekBarChangeListener(this);
		sbWrapperHeight.setOnSeekBarChangeListener(this);
		
		rlBoxSizeWrapper.addOnLayoutChangeListener( new OnLayoutChangeListener() {
			
			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
				
				handler.postDelayed(new Runnable(){

					@Override
					public void run() {
						tvWrapperHeightActual.setText("Actual Size : " +  rlBoxSizeWrapper.getHeight() / metrics.density + "dp");	
					}
					
				} , 500);
			}
		});
		
		spTextViewGravity = (Spinner)view.findViewById(R.id.spTextViewGravity);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.gravity_options, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spTextViewGravity.setAdapter(adapter);
		
		
		spTextViewGravity.setOnItemSelectedListener(new MySpinnerListener() );
		spTextViewGravity.setSelection(1);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frag_box_model, container, false);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
	
		if ( seekBar == sbTvLayoutMargin )
		{
			LayoutParams lp = (LayoutParams) tvBoxSizeText.getLayoutParams();
			int marginInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, metrics);
			lp.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
			tvBoxSizeText.setLayoutParams(lp);
			tvLayoutMarginVal.setText(progress+"dp");
		}
		else if ( seekBar == sbTvLayoutPadding )
		{
			int paddingInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, metrics);
			tvBoxSizeText.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
			tvLayoutPaddingVal.setText(progress+"dp");
		}
		else if ( seekBar == sbTvHeight )
		{
			LayoutParams lp = (LayoutParams) tvBoxSizeText.getLayoutParams();
			if( progress == 0 )
			{
				lp.height = LayoutParams.WRAP_CONTENT;
			}
			else
			{
				int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, metrics);
				lp.height = heightInPx ;
			}
			tvBoxSizeText.setLayoutParams(lp);
			if( progress == 0 )
			{
				tvHeightVal.setText("wrap");
			}else {
				tvHeightVal.setText(progress+"dp");
			}
		}
		else if ( seekBar == sbWrapperHeight )
		{
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rlBoxSizeWrapper.getLayoutParams();
			if( progress == 0 )
			{
				lp.height = LayoutParams.WRAP_CONTENT;
			}
			else
			{
				int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, metrics);
				lp.height = heightInPx ;
			}
			rlBoxSizeWrapper.setLayoutParams(lp);
			if( progress == 0 )
			{
				tvWrapperHeightVal.setText("wrap");
			}else {
				tvWrapperHeightVal.setText(progress+"dp");
			}
		}
		
		//rlBoxSizeWrapper.requestLayout();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	
	private class MySpinnerListener implements OnItemSelectedListener
	{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View v, int position,
				long id) {
			
			
			if( arg0 == spTextViewGravity )
			{
				
				LayoutParams lp = (LayoutParams) tvBoxSizeText.getLayoutParams() ;
				
				if( position == 0 )
				{
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
					lp.addRule(RelativeLayout.CENTER_VERTICAL , 0);
					lp.addRule(RelativeLayout.CENTER_HORIZONTAL , 0);
				}
				if( position == 1 )
				{
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
					lp.addRule(RelativeLayout.CENTER_VERTICAL , RelativeLayout.TRUE);
				}			
				if( position == 2 )
				{
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
					lp.addRule(RelativeLayout.CENTER_VERTICAL , RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.CENTER_HORIZONTAL , RelativeLayout.TRUE);
					
				}
				if( position == 3 )
				{
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
					lp.addRule(RelativeLayout.CENTER_VERTICAL , RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.CENTER_HORIZONTAL , 0);
				}
				if( position == 4 )
				{
					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
					lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
					lp.addRule(RelativeLayout.CENTER_VERTICAL , 0);
					lp.addRule(RelativeLayout.CENTER_HORIZONTAL , 0);
				}
	
				tvBoxSizeText.setLayoutParams(lp);
	
	
			}
			
		}
	
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	
}
