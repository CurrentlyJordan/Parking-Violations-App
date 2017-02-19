package nyc.c4q.jordansmith.finefree.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import nyc.c4q.jordansmith.finefree.R;
import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;

/**
 * Created by helenchan on 2/18/17.
 */
public class ViolationViewHolder extends RecyclerView.ViewHolder {
    private TextView summons_tv;
    private TextView fineAmount;
    private TextView issueDate_tv;
    private ImageButton payButton;
    private String violationURL;


    public ViolationViewHolder(View itemView) {
        super(itemView);
        summons_tv = (TextView) itemView.findViewById(R.id.summons_textview);
        issueDate_tv = (TextView) itemView.findViewById(R.id.issue_date_textview);
        fineAmount = (TextView) itemView.findViewById(R.id.fine_textview);
        payButton = (ImageButton) itemView.findViewById(R.id.pay_button);
        payButtonClick();
        cardClick(itemView);
    }

    private void cardClick(final View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void payButtonClick() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void bind(ParkingCameraResponse violations) {
        fineAmount.setText(Integer.toString(violations.getAmountDue()));
        summons_tv.setText(violations.getSummonsNumber());
        issueDate_tv.setText(violations.getIssueDate());
        violationURL = violations.getIssueImage();
    }
}

