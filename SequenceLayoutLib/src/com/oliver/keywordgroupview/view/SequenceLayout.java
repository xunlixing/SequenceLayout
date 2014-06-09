package com.oliver.keywordgroupview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.oliver.keywordgroupview.R;

public class SequenceLayout extends ViewGroup {
	private int mHorizontalSpacing;
	private int mVerticalSpacing;

	public SequenceLayout(Context context, AttributeSet attrs) {
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

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int childLeft = getPaddingLeft();
		int childTop = getPaddingTop();
		int rowHeight = 0;
		final int globalWidth = resolveSize(100, widthMeasureSpec);
		int wantedHeight = 0;
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			int childMeasureWidth = child.getMeasuredWidth();
			int childMeasureHeight = child.getMeasuredHeight();
			rowHeight = Math.max(childMeasureHeight, rowHeight);
			if (childLeft + childMeasureWidth + getPaddingBottom() > globalWidth) {
				childLeft = getPaddingLeft();
				childTop += rowHeight + mVerticalSpacing;
				rowHeight = 0;
			}
			childLeft += childMeasureWidth + mHorizontalSpacing;

		}
		wantedHeight = childTop + rowHeight + getPaddingBottom();
		setMeasuredDimension(globalWidth,
				resolveSize(wantedHeight, heightMeasureSpec));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		int childLeft = getPaddingLeft();
		int childTop = getPaddingTop();
		final int globalWidth = right - left;
		int rowHeight = 0;
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			int childMeasureWidth = child.getMeasuredWidth();
			int childMeasureHeight = child.getMeasuredHeight();
			rowHeight = Math.max(childMeasureHeight, rowHeight);
			if (childLeft + childMeasureWidth + getPaddingBottom() > globalWidth) {
				childLeft = getPaddingLeft();
				childTop += rowHeight + mVerticalSpacing;
				rowHeight = 0;
			}
			child.layout(childLeft, childTop, childLeft + childMeasureWidth,
					childTop + childMeasureHeight);
			childLeft += childMeasureWidth + mHorizontalSpacing;
		}
	}

}
