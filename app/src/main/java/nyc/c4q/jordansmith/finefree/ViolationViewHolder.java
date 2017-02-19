package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
                Uri uri = Uri.parse(violationURL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                itemView.getContext().startActivity(intent);
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
        summons_tv.setText(summons_tv.getText() + violations.getSummonsNumber());
        issueDate_tv.setText(issueDate_tv.getText() + violations.getIssueDate());
        fineAmount.setText(fineAmount.getText() + violations.getPaymentAmount());
        violationURL = violations.getIssueImage();
    }
}

