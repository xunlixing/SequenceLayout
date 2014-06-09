package com.oliver.keywordgroupview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.oliver.keywordgroupview.R;

public class KeywordGroupView extends RelativeLayout {
	private ListAdapter mAdapter;
	private int mHorizontalSpacing;
	private int mVerticalSpacing;
	private int mWidthMeasureSpec;
	private int chileViewHeight = 100;
	private int mItemCount;

	public KeywordGroupView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.KeywordListView);
		try {
			mHorizontalSpacing = typedArray.getDimensionPixelSize(
					R.styleable.KeywordListView_horizontal_spacing,
					getResources().getDimensionPixelSize(
							R.dimen.keyword_horizontal_spacing));

			mVerticalSpacing = typedArray.getDimensionPixelSize(
					R.styleable.KeywordListView_vertical_spacing,
					getResources().getDimensionPixelSize(
							R.dimen.keyword_vertical_spacing));
		} finally {
			typedArray.recycle();
		}
	}

	public void setAdapter(ListAdapter adapter) {
		mAdapter = adapter;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidthMeasureSpec = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);   
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(mWidthMeasureSpec, heightSize);  
		// if(mAdapter==null){
		// return;
		// }
		// final int childCount = mAdapter.getCount();
		// if(childCount<=0){
		// return;
		// }
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (mAdapter == null) {
			return;
		}
		final int childCount = mAdapter.getCount();
		if (childCount <= 0) {
			return;
		}

		for (int i = 0; i < childCount; i++) {
			View child = obtainView(i);
			child.measure(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			int childWidth = child.getMeasuredWidth();
			int left = (mHorizontalSpacing + childWidth) * i;
			int top = (mVerticalSpacing + chileViewHeight) * i;
			child.layout(left, top, childWidth, chileViewHeight);
			addView(child, i);
			invalidate();
		}

	}

	View obtainView(int position) {
		View child = mAdapter.getView(position, null, this);
		return child;
	}

	public static class LayoutParams extends RelativeLayout.LayoutParams {
		int x;
		int y;

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}
	}

}
