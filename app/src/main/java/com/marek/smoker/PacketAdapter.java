package com.marek.smoker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.ViewHolder> {

    List<Packet> packets;

    public PacketAdapter(List<Packet> packets) {
        this.packets = packets;
    }

    @Override
    public PacketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.packet_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PacketAdapter.ViewHolder holder, int position) {

        holder.eventName.setText(packets.get(position).getPacketBrand());
        holder.eventDate.setText(packets.get(position).getPacketDate());
        holder.eventDistance.setText(packets.get(position).getPacketPrice());

    }

    @Override
    public int getItemCount() {
        return packets.size();
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
