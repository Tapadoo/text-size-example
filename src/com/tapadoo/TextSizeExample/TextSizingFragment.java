package com.tapadoo.TextSizeExample;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TextSizingFragment extends Fragment {
	
    private TextView tvCustom;


    private LinearLayout llCustomWrapper;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frag_text_sizes, container , false);
	}



	/** Called when the activity is first created. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View view = getView();
        
        llCustomWrapper = (LinearLayout)view.findViewById(R.id.llCustomWrapper);
        
        tvCustom = (TextView) view.findViewById(R.id.tvCustom);
        
        final TextView mpText = (TextView) view.findViewById(R.id.mpText);
        
        mpText.addOnLayoutChangeListener(new OnLayoutChangeListener() {
			
			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
				
				
				DisplayMetrics metrics = new DisplayMetrics();
				getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

				mpText.setText( "match_parent " + mpText.getWidth() +  "px" + " - " + ( (1.0/metrics.density) * mpText.getWidth() ) + "dp" );
			}
		});
        
        SeekBar sbAdjustCustom = (SeekBar) view.findViewById(R.id.sbAdjustCustom);

        
        sbAdjustCustom.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				DisplayMetrics metrics = new DisplayMetrics();
				getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

				if( tvCustom != null )
				{
					llCustomWrapper.removeView(tvCustom);
				}
				
				tvCustom = (TextView) getActivity().getLayoutInflater().inflate(R.layout.dyn_text_view_ex, llCustomWrapper, false );
				tvCustom.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, metrics));
				
				tvCustom.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);
				tvCustom.setText(progress +" sp");
				
				llCustomWrapper.addView(tvCustom,0);
				 llCustomWrapper.requestLayout();
				tvCustom.postInvalidate();
			}
		});
        
    }



	public static TextSizingFragment create() {
		return new TextSizingFragment();
	}
}