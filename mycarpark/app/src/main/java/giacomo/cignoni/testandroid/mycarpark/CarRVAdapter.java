package giacomo.cignoni.testandroid.mycarpark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CarRVAdapter extends ListAdapter<Car, CarRVAdapter.CarViewHolder> {

    public static class CarViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView textViewCarName;
        private ConstraintLayout constraintLayout;
        private ImageButton removeButton;
        //needed for calling switchCar method in main
        private MainActivity mainActivity;
        private Car car;

        //constructor for inner class
        public CarViewHolder(View itemView, MainActivity mainActivity) {
            super(itemView);
            textViewCarName = itemView.findViewById(R.id.text_car_name);
            constraintLayout = itemView.findViewById(R.id.layout_car_item);
            removeButton = itemView.findViewById(R.id.button_remove_car);
            this.mainActivity = mainActivity;
            constraintLayout.setOnClickListener(this);
            removeButton.setOnClickListener(this);
        }

        public void bind(String carNameString, Car c) {
            textViewCarName.setText(carNameString);
            this.car = c;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_remove_car) {
                mainActivity.showRemoveCarAlert(this.car);
            }
            else {
                mainActivity.switchCar(this.car);
            }
        }
    }

    private MainActivity mainActivity;

    public CarRVAdapter(@NonNull DiffUtil.ItemCallback<Car> diffCallback, MainActivity mainActivity) {
        super(diffCallback);
        this.mainActivity = mainActivity;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_rv_item, parent, false);
        return new CarViewHolder(view, mainActivity);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car carToBind = getItem(position);
        holder.bind(carToBind.getName(), carToBind);
    }

    static class CarDiff extends DiffUtil.ItemCallback<Car> {

        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getCarId() == newItem.getCarId();
        }
    }
}

