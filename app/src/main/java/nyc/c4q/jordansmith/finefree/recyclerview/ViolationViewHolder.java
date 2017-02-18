package nyc.c4q.jordansmith.finefree.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.jordansmith.finefree.R;
import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;

/**
 * Created by helenchan on 2/18/17.
 */
public class ViolationViewHolder extends RecyclerView.ViewHolder {
    TextView summons_tv;
    TextView fineAmount;

    public ViolationViewHolder(View itemView) {
        super(itemView);
        summons_tv = (TextView) itemView.findViewById(R.id.summons_textview);
        fineAmount = (TextView) itemView.findViewById(R.id.fine_textview);
    }

    public void bind(ParkingCameraResponse violations) {
        summons_tv.setText(violations.getSummonsNumber());
        fineAmount.setText(Integer.toString(violations.getPaymentAmount()));
    }
}
