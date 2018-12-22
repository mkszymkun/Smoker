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

        holder.packetBrand.setText(packets.get(position).getPacketBrand());
        holder.packetDate.setText(packets.get(position).getPacketDate());
        holder.packetPrice.setText(packets.get(position).getPacketPrice());
        holder.packetAvailable.setText(packets.get(position).getPacketAvailable());

    }

    @Override
    public int getItemCount() {
        return packets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView packetBrand;
        public TextView packetDate;
        public TextView packetPrice;
        public TextView packetAvailable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            packetBrand = itemView.findViewById(R.id.packet_brand);
            packetDate = itemView.findViewById(R.id.packet_date);
            packetPrice = itemView.findViewById(R.id.packet_price);
            packetAvailable = itemView.findViewById(R.id.packet_available);
        }
    }
}
