package com.marek.smoker;

public class SelectableItem extends Packet {

    private boolean isSelected = false;


    public SelectableItem(Packet packet,boolean isSelected) {
        super(packet.getPacketBrand(),packet.getPacketDate(),
                packet.getPacketPrice(), packet.getPacketAvailable());
        this.isSelected = isSelected;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
