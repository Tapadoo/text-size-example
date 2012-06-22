package com.tapadoo.TextSizeExample;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class BoxModelFragment extends Fragment implements OnSeekBarChangeListener {
	
	public static BoxModelFragment create()
	{
		return new BoxModelFragment();
	}

	private TextView tvBoxSizeText;
	private SeekBar sbLayoutGravity;
	private SeekBar sbTvLayoutMargin;
	private DisplayMetrics metrics = new DisplayMetrics();
	private TextView tvLayoutMarginVal;
	private TextView tvLayoutPaddingVal;
	private SeekBar sbTvLayoutPadding;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		View view = getView();
		
		tvBoxSizeText = (TextView) view.findViewById(R.id.tvBoxSizeText);
		tvLayoutMarginVal = (TextView) view.findViewById(R.id.tvLayoutMarginVal);
		tvLayoutPaddingVal = (TextView) view.findViewById(R.id.tvLayoutPaddingVal);
		
		sbLayoutGravity = (SeekBar)view.findViewById(R.id.sbLayoutGravity);
		sbTvLayoutMargin = (SeekBar)view.findViewById(R.id.sbTvLayoutMargin);
		sbTvLayoutPadding = (SeekBar)view.findViewById(R.id.sbTvLayoutPadding);
		
		sbLayoutGravity.setOnSeekBarChangeListener(this);
		sbTvLayoutMargin.setOnSeekBarChangeListener(this);
		sbTvLayoutPadding.setOnSeekBarChangeListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frag_box_model, container, false);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
	
		if( seekBar == sbLayoutGravity )
		{
			LinearLayout.LayoutParams lp = (LayoutParams) tvBoxSizeText.getLayoutParams();
			lp.gravity = ( progress == 0 ? Gravity.LEFT : ( progress == 1 ? Gravity.CENTER : Gravity.RIGHT ) );
			tvBoxSizeText.setLayoutParams(lp);
		}
		else if ( seekBar == sbTvLayoutMargin )
		{
			LinearLayout.LayoutParams lp = (LayoutParams) tvBoxSizeText.getLayoutParams();
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
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	
}
