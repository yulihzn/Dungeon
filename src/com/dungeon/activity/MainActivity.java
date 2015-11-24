package com.dungeon.activity;

import com.banditcat.dungeon.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{
	
	private ShimmerFrameLayout shimmerFrameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.layout_shimmer);
		shimmerFrameLayout.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_shimmer:
//			shimmerFrameLayout.setDuration(5000);
//			shimmerFrameLayout.setRepeatDelay(ObjectAnimator.RESTART);
//			shimmerFrameLayout.startShimmerAnimation();
			break;

		default:
			break;
		}
		
	}

}
