package com.marek.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {

        holder.eventName.setText(events.get(position).getEventName());
        holder.eventDate.setText(events.get(position).getEventDate());
        holder.eventDistance.setText(events.get(position).getEventDistance());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public TextView eventDate;
        public TextView eventDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventDistance = itemView.findViewById(R.id.event_distance);
        }
    }
}
