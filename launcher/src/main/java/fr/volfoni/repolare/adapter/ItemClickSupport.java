package fr.volfoni.repolare.adapter;

import android.view.View;

import fr.volfoni.repolare.R;

import androidx.recyclerview.widget.RecyclerView;

public class ItemClickSupport {
	private final RecyclerView mRecyclerView;
	private OnItemClickListener mOnItemClickListener;
	private OnItemLongClickListener mOnItemLongClickListener;
	private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mOnItemClickListener != null) {
				// ask the RecyclerView for the viewHolder of this view.
				// then use it to get the position for the adapter
				RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
				mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
			}
		}
	};
	private final View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			if (mOnItemLongClickListener != null) {
				RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
				return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
			}
			return false;
		}
	};
	private final RecyclerView.OnChildAttachStateChangeListener mAttachListener
			= new RecyclerView.OnChildAttachStateChangeListener() {
		@Override
		public void onChildViewAttachedToWindow(View view) {
			// every time a new child view is attached add click listeners to it
			if (mOnItemClickListener != null) {
				view.setOnClickListener(mOnClickListener);
			}
			if (mOnItemLongClickListener != null) {
				view.setOnLongClickListener(mOnLongClickListener);
			}
		}

		@Override
		public void onChildViewDetachedFromWindow(View view) {

		}
	};

	private ItemClickSupport(RecyclerView recyclerView) {
		mRecyclerView = recyclerView;
		// the ID must be declared in XML, used to avoid
		// replacing the ItemClickSupport without removing
		// the old one from the RecyclerView
		mRecyclerView.setTag(R.id.item_click_support, this);
		mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
	}

	public static ItemClickSupport addTo(RecyclerView view) {
		// if there's already an ItemClickSupport attached
		// to this RecyclerView do not replace it, use it
		ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
		if (support == null) {
			support = new ItemClickSupport(view);
		}
		return support;
	}

	public static ItemClickSupport removeFrom(RecyclerView view) {
		ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
		if (support != null) {
			support.detach(view);
		}
		return support;
	}

	public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
		return this;
	}

	public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
		mOnItemLongClickListener = listener;
		return this;
	}

	private void detach(RecyclerView view) {
		view.removeOnChildAttachStateChangeListener(mAttachListener);
		view.setTag(R.id.item_click_support, null);
	}

	public interface OnItemClickListener {

		void onItemClicked(RecyclerView recyclerView, int position, View v);
	}

	public interface OnItemLongClickListener {

		boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
	}
}