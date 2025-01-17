package com.android.mevabe.dashboard;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mevabe.R;
import com.android.mevabe.common.Constants;
import com.android.mevabe.common.RefreshLoadMoreAdapter;
import com.android.mevabe.common.view.RoundedTransformation;
import com.android.mevabe.common.model.DBFeedModel;
import com.squareup.picasso.Picasso;

/**
 * DashBoardRecyclerAdapter controls view of list in DashBoard
 */
public class DBRecyclerViewAdapter extends RefreshLoadMoreAdapter {
    /**
     * IDashBoardListHandler interface for callback
     */
    public interface IDashBoardListHandler {
        void onItemClick(DBFeedModel item);
    }

    private IDashBoardListHandler handler;

    /**
     * Constructor
     *
     * @param context Context
     */
    public DBRecyclerViewAdapter(Activity context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Check to load load more view or not
        RecyclerView.ViewHolder view = super.onCreateViewHolder(viewGroup, viewType);
        if (view != null) {
            return view;
        }

        switch (viewType) {
            case Constants.FEED_VIEW_TYPE_ADD: {
                View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .dashboard_item_update_info, viewGroup, false);
                view = new TypeNewsViewHolder(layout);
                break;
            }

            case Constants.FEED_VIEW_TYPE_BIG: {
                View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .dashboard_item_viewtypebig, viewGroup, false);
                view = new TypeNewsViewHolder(layout);
                break;
            }

            case Constants.FEED_VIEW_TYPE_LEFT: {
                View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .dashboard_item_viewtypeleft, viewGroup, false);
                view = new TypeNewsViewHolder(layout);
                break;
            }

            case Constants.FEED_VIEW_TYPE_RIGHT: {
                View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .dashboard_item_viewtyperight, viewGroup, false);
                view = new TypeNewsViewHolder(layout);
                break;
            }
        }


        return view;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DBFeedModel feedItem = (DBFeedModel) listItems.get(position);
        if (feedItem != null) {
            int type = feedItem.getViewType();
            switch (type) {
                case Constants.FEED_VIEW_TYPE_ADD: {
                    ((TypeAddViewHolder) holder).bindData(feedItem);
                    break;
                }
                default: {
                    ((TypeNewsViewHolder) holder).bindData(feedItem);
                    break;
                }
            }
        }

    }


    // ************* View Holder *********** //
    class TypeAddViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public TypeAddViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }

        public void bindData(final DBFeedModel data) {
            //Render image using Picasso library
            if (!TextUtils.isEmpty(data.getThumb())) {
                Picasso.with(context).load(data.getThumb())
                        .error(R.drawable.common_placeholder)
                        .placeholder(R.drawable.common_placeholder)
                        .transform(new RoundedTransformation(5, 0))
                        .into(imageView);
            }

            //Setting text view title
            textView.setText(Html.fromHtml(data.getTitle()));

            // Add listener
            if (handler != null) {
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.onItemClick(data);
                    }
                };
                imageView.setOnClickListener(listener);
                textView.setOnClickListener(listener);
            }
        }
    }

    class TypeNewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public TypeNewsViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }

        public void bindData(final DBFeedModel data) {
            //Render image using Picasso library
            if (!TextUtils.isEmpty(data.getThumb())) {
                Picasso.with(context).load(data.getThumb())
                        .error(R.drawable.common_placeholder)
                        .placeholder(R.drawable.common_placeholder)
                        .into(imageView);
            }

            //Setting text view title
            textView.setText(Html.fromHtml(data.getTitle()));

            // Add listener
            if (handler != null) {
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.onItemClick(data);
                    }
                };
                imageView.setOnClickListener(listener);
                textView.setOnClickListener(listener);
            }
        }
    }


    // ***** Getters and Setters ***** //
    public IDashBoardListHandler getHandler() {
        return handler;
    }

    public void setHandler(IDashBoardListHandler handler) {
        this.handler = handler;
    }
}