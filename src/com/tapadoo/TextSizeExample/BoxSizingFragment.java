package com.tapadoo.TextSizeExample;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class BoxSizingFragment extends Fragment {
	
    private TextView tvCustomSize;
    private TextView tvCustomSizePx;

	private LinearLayout llCustomWrapperSize;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frag_boxes, container , false);
	}



	/** Called when the activity is first created. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        View view = getView();
        
        llCustomWrapperSize = (LinearLayout)view.findViewById(R.id.llCustomSizeWrapper);

        tvCustomSize = (TextView) view.findViewById(R.id.tvCustomSize);
        tvCustomSizePx = (TextView) view.findViewById(R.id.tvCustomSizePx);
        
        SeekBar sbAdjustCustomSizeDp = (SeekBar) view.findViewById(R.id.sbAdjustCustomSizeDp);
        SeekBar sbAdjustCustomSizePx = (SeekBar) view.findViewById(R.id.sbAdjustCustomSizePx);
        

        
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



	public static BoxSizingFragment create() {
		return new BoxSizingFragment();
	}
}