package hk.edu.hkmu.mobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BusRouteAdapter extends RecyclerView.Adapter<BusRouteAdapter.ViewHolder> {
    private List<BusRoute> busRoutes;

    public void setData(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bus_route, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusRoute route = busRoutes.get(position);
        holder.tvRouteNumber.setText(route.getRouteNumber());
        holder.tvDestination.setText(route.getDestination());
    }

    @Override
    public int getItemCount() {
        return busRoutes == null ? 0 : busRoutes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRouteNumber, tvDestination;
        ViewHolder(View view) {
            super(view);
            tvRouteNumber = view.findViewById(R.id.tvRouteNumber);
            tvDestination = view.findViewById(R.id.tvDestination);
        }
    }
}
