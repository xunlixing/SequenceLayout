package com.oliver.keywordgroupview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.oliver.keywordgroupview.R;

public class KeywordListView extends AdapterView<ListAdapter> {
	private int mHorizontalSpacing;
	private int mVerticalSpacing;
	

	private ListAdapter mAdapter;
	private boolean mDataChanged = false;

	public KeywordListView(Context context, AttributeSet attrs) {
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

	private DataSetObserver mDataObserver = new DataSetObserver() {

		@Override
		public void onChanged() {
			synchronized (KeywordListView.this) {
				mDataChanged = true;
			}
			invalidate();
			requestLayout();
		}

		@Override
		public void onInvalidated() {
			reset();
			invalidate();
			requestLayout();
		}

	};



	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mAdapter == null) {
			return;
		}
		if (mDataChanged) {
			removeAllViewsInLayout();
		}
		layoutChild();

	}

	private void layoutChild() {
		final int childCount = mAdapter.getCount();
		if (childCount <= 0) {
			return;
		}
		for (int i = 0; i < childCount; i++) {
			View child = obtainView(i);
			child.measure(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
			
			int width=child.getMeasuredWidth();
			addViewInLayout(child, i, null, true);
		}
	}

	View obtainView(int position) {
		View child = mAdapter.getView(position, null, this);
		return child;
	}

	private synchronized void reset() {
		removeAllViewsInLayout();
		requestLayout();
	}

	@Override
	public ListAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		if (mAdapter != null) {
			mAdapter.unregisterDataSetObserver(mDataObserver);
		}
		mAdapter = adapter;
		mAdapter.registerDataSetObserver(mDataObserver);
		reset();
	}

	@Override
	public View getSelectedView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelection(int position) {
		// TODO Auto-generated method stub

	}

}
