package com.example.carcrash.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carcrash.Car;
import com.example.carcrash.R;

import java.util.List;

public class CarAdapter extends  RecyclerView.Adapter<CarAdapter.ViewHolder>{
    private List<Car> cars;
    public CarAdapter(List<Car> cars){
        this.cars = cars;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_layout, parent, false);
        return new ViewHolder(stateView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.bind(car);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvState;
        public TextView tvPeople;
        public TextView tvFatal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvState = itemView.findViewById(R.id.tvState);
            tvPeople = itemView.findViewById(R.id.tvPeople);
            tvFatal = itemView.findViewById(R.id.tvFatal);
        }

        public void bind(Car car) {
            tvState.setText(car.getState());
            tvPeople.setText(String.format("%d\n",car.getPeople()));
            tvFatal.setText(String.format("%d\n",car.getFatals()));

        }
    }
}
