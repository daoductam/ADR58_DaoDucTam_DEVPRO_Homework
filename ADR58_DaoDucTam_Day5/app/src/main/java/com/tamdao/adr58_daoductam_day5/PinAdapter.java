package com.tamdao.adr58_daoductam_day5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinViewHolder> {

    private List<Pin> pinList;

    public PinAdapter(List<Pin> pinList) {
        this.pinList = pinList;
    }

    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin, parent, false);
        return new PinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PinViewHolder holder, int position) {
        Pin pin = pinList.get(position);
        
        ViewGroup.LayoutParams params = holder.imgPin.getLayoutParams();
        params.height = pin.getHeight();
        holder.imgPin.setLayoutParams(params);
        
        holder.imgPin.setImageResource(pin.getImageResId());
    }

    @Override
    public int getItemCount() {
        return pinList.size();
    }

    static class PinViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPin;

        public PinViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPin = itemView.findViewById(R.id.imgPin);
        }
    }
}