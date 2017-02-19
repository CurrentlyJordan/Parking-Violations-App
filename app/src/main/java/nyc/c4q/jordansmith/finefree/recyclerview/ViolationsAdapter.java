package nyc.c4q.jordansmith.finefree.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.jordansmith.finefree.R;
import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;

/**
 * Created by helenchan on 2/18/17.
 */
public class ViolationsAdapter extends RecyclerView.Adapter<ViolationViewHolder> {
    List<ParkingCameraResponse> mViolationsList = new ArrayList<>();


    @Override
    public ViolationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.violations_viewholder, parent, false);
        return new ViolationViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViolationViewHolder holder, int position) {
        ParkingCameraResponse violations = mViolationsList.get(position);
        holder.bind(violations);
    }

    @Override
    public int getItemCount() {
        return mViolationsList.size();
    }

    public void setViolationsList(List<ParkingCameraResponse> violationsList) {
        mViolationsList.clear();
        mViolationsList.addAll(violationsList);
        notifyDataSetChanged();
    }
}
