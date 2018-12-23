package com.marek.smoker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class DailyInfoAdapter extends RecyclerView.Adapter<DailyInfoAdapter.ViewHolder> {

    List<DailyInfo> dailyInfos;

    public DailyInfoAdapter(List<DailyInfo> dailyInfos) {
        this.dailyInfos = dailyInfos;
    }

    @Override
    public DailyInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyInfoAdapter.ViewHolder holder, int position) {

        String day = "Day " + String.valueOf(dailyInfos.get(position).getDailyDay());
        holder.dailyInfoDay.setText(day);

        holder.dailyInfoDate.setText(dailyInfos.get(position).getDailyDate());

        String amount = "Smoked: " + String.valueOf(dailyInfos.get(position).getDailyAmount());
        holder.dailyInfoAmount.setText(amount);

        String money = "Spent: " + String.valueOf(dailyInfos.get(position).getDailyMoney());
        holder.dailyInfoMoney.setText(money);

    }

    @Override
    public int getItemCount() {
        return dailyInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dailyInfoDay;
        public TextView dailyInfoDate;
        public TextView dailyInfoAmount;
        public TextView dailyInfoMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyInfoDay = itemView.findViewById(R.id.daily_day);
            dailyInfoDate = itemView.findViewById(R.id.daily_date);
            dailyInfoAmount = itemView.findViewById(R.id.daily_amount);
            dailyInfoMoney = itemView.findViewById(R.id.daily_money);
        }
    }
}
