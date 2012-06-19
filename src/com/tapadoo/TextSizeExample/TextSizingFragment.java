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
    private TextView tvCustomSize;
    private TextView tvCustomSizePx;

    private LinearLayout llCustomWrapper;
	private LinearLayout llCustomWrapperSize;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.main, container , false);
	}



	/** Called when the activity is first created. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View view = getView();
        
        llCustomWrapper = (LinearLayout)view.findViewById(R.id.llCustomWrapper);
        llCustomWrapperSize = (LinearLayout)view.findViewById(R.id.llCustomSizeWrapper);
        
        tvCustom = (TextView) view.findViewById(R.id.tvCustom);
        tvCustomSize = (TextView) view.findViewById(R.id.tvCustomSize);
        tvCustomSizePx = (TextView) view.findViewById(R.id.tvCustomSizePx);
        
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
        SeekBar sbAdjustCustomSizeDp = (SeekBar) view.findViewById(R.id.sbAdjustCustomSizeDp);
        SeekBar sbAdjustCustomSizePx = (SeekBar) view.findViewById(R.id.sbAdjustCustomSizePx);
        
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
        
        sbAdjustCustomSizeDp.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
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

				if( tvCustomSize != null )
				{
					llCustomWrapperSize.removeView(tvCustomSize);
				}

				int newSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progress, metrics) ;
				
				tvCustomSize = (TextView) getActivity().getLayoutInflater().inflate(R.layout.dyn_text_view_ex_size, llCustomWrapperSize, false );
				llCustomWrapperSize.addView(tvCustomSize,0);
				tvCustomSize.setHeight(newSize);
				tvCustomSize.setWidth(newSize);
				

				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) tvCustomSize.getLayoutParams();
				lp.width = newSize;
				lp.height = newSize;
				tvCustomSize.setLayoutParams(lp);
				
				
				
				tvCustomSize.setText(progress + " dp");
				
				 llCustomWrapperSize.requestLayout();
				 tvCustomSize.postInvalidate();
			}
		});
        
        sbAdjustCustomSizePx.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
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

				if( tvCustomSizePx != null )
				{
					llCustomWrapperSize.removeView(tvCustomSizePx);
				}
				
				tvCustomSizePx = (TextView)  getActivity().getLayoutInflater().inflate(R.layout.dyn_text_view_ex_size, llCustomWrapperSize, false );
				llCustomWrapperSize.addView(tvCustomSizePx,1);
				tvCustomSizePx.setHeight(progress);
				tvCustomSizePx.setWidth(progress);
				
				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) tvCustomSizePx.getLayoutParams();
				lp.width = progress;
				lp.height = progress;
				tvCustomSizePx.setLayoutParams(lp);
				
				tvCustomSizePx.setText(progress + " px" );
				
				 llCustomWrapperSize.requestLayout();
				 tvCustomSizePx.postInvalidate();
			}
		});
        
    }



	public static TextSizingFragment create() {
		return new TextSizingFragment();
	}
}