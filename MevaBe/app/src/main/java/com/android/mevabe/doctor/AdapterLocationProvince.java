package com.android.mevabe.doctor;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mevabe.R;
import com.android.mevabe.common.model.LocationProvince;

import java.util.ArrayList;
import java.util.List;

/**
 * AdapterLocationProvince controls view of list setting location for filtering
 */
public class AdapterLocationProvince extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * ILocationProvinceAdapter interface for callback
     */
    public interface ILocationProvinceAdapter {
        void onChangeProvince(LocationProvince item);
    }

    private ILocationProvinceAdapter handler;

    private List<LocationProvince> listItems;
    private Activity context;
    private long selected;

    /**
     * Constructor
     *
     * @param context Context
     */
    public AdapterLocationProvince(Activity context, long selected, ILocationProvinceAdapter handler) {
        this.listItems = new ArrayList<>();
        this.context = context;
        this.selected = selected;
        this.handler = handler;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .doctors_filter_location_item, viewGroup, false);
        RecyclerView.ViewHolder view = new MyViewHolder(layout);
        return view;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LocationProvince item = (LocationProvince) listItems.get(position);
        if (item != null) {
            ((MyViewHolder) holder).bindData(item);
        }

    }

    @Override
    public int getItemCount() {
        return (null != listItems ? listItems.size() : 0);
    }

    // ************* Update data *********** //

    /**
     * Refresh data by new list data
     */
    public void refreshItems(List result) {
        if (result != null) {
            listItems.clear();
            listItems.addAll(result);

            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }
    }

    // ************* View Holder *********** //
    class MyViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView title;
        private ImageView tick;

        public MyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.tick = (ImageView) view.findViewById(R.id.tick);
            this.layout = view;

        }

        public void bindData(final LocationProvince data) {
            // Show title
            title.setText(data.getTitle());

            // ON/OFF tick
            if (selected == data.getCode()) {
                tick.setVisibility(View.VISIBLE);
                title.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                tick.setVisibility(View.INVISIBLE);
                title.setTextColor(context.getResources().getColor(R.color.textColor));
            }

            // Add listener
            if (handler != null) {
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getCode() != selected) {
                            // Update for current view
                            selected = data.getCode();
                            notifyDataSetChanged();

                            // Call back
                            handler.onChangeProvince(data);
                        }
                    }
                });
            }

        }
    }
}