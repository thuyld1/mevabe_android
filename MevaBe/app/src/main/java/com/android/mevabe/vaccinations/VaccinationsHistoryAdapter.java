package com.android.mevabe.vaccinations;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mevabe.R;
import com.android.mevabe.common.Constants;
import com.android.mevabe.common.model.VaccinationsHistoryModel;
import com.android.mevabe.common.view.InjectionStatusBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * VaccinationsHistoryAdapter controls view of list "Chọn lịch tiêm" tab
 */
public class VaccinationsHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * IVaccinationsPlanHandler interface for callback
     */
    public interface IVaccinationsHistoryHandler {
        void editVaccinePlan(VaccinationsHistoryModel item);
    }

    protected List<VaccinationsHistoryModel> listItems;
    protected Activity context;
    private IVaccinationsHistoryHandler handler;

    private SimpleDateFormat df = new SimpleDateFormat(Constants.VACCINE_INJECTION_DATE_FORMAT);
    private String vaccinInjectionDateFormat;

    /**
     * Constructor
     *
     * @param context Context
     */
    public VaccinationsHistoryAdapter(Activity context) {
        this.listItems = new ArrayList<>();
        this.context = context;

        vaccinInjectionDateFormat = context.getString(R.string.vaccinations_injection_date);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .vaccinations_history_item_view, viewGroup, false);
        RecyclerView.ViewHolder view = new MyViewHolder(layout);
        return view;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VaccinationsHistoryModel item = (VaccinationsHistoryModel) listItems.get(position);
        if (item != null) {
            ((MyViewHolder) holder).bindData(item);
        }

    }

    @Override
    public int getItemCount() {
        return (null != listItems ? listItems.size() : 0);
    }

    // ************* Update data *********** //
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

    /**
     * Update data when load more has finished
     */
    public synchronized void appendItem(VaccinationsHistoryModel result) {
        if (result != null) {
            listItems.add(result);
        }
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }


    // ************* View Holder *********** //
    class MyViewHolder extends RecyclerView.ViewHolder {
        private InjectionStatusBox vaccinationsStatus;
        private TextView childInfo;
        private TextView vaccinName;
        private TextView injectionDate;
        private TextView vaccinDes;

        public MyViewHolder(View view) {
            super(view);
            this.vaccinationsStatus = (InjectionStatusBox) view.findViewById(R.id.vaccinationsStatus);
            this.childInfo = (TextView) view.findViewById(R.id.child_info);
            this.vaccinName = (TextView) view.findViewById(R.id.vaccinName);
            this.injectionDate = (TextView) view.findViewById(R.id.injectionDate);
            this.vaccinDes = (TextView) view.findViewById(R.id.vaccinDes);
        }

        public void bindData(final VaccinationsHistoryModel data) {
            // Setting vaccinations status
            vaccinationsStatus.bindData(data.getInStatus(), data.getInDate());

            //Setting text view title
            vaccinName.setText(data.getVaccineName());
            childInfo.setText(data.getChildInfo());
            vaccinDes.setText(data.getVaccineShortDes());

            if (data.getInDate() > 0) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(data.getInDate());
                injectionDate.setText(String.format(vaccinInjectionDateFormat, df.format(cal.getTime())));
            }

            // Set "Add add vaccine plan for child" listener
            View.OnClickListener addVaccineListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.editVaccinePlan(data);
                }
            };
            vaccinationsStatus.setOnClickListener(addVaccineListener);
        }
    }
}