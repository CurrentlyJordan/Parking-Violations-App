package nyc.c4q.jordansmith.finefree.recyclerview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import nyc.c4q.jordansmith.finefree.R;
import nyc.c4q.jordansmith.finefree.model.ParkingCameraResponse;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by helenchan on 2/18/17.
 */
public class ViolationViewHolder extends RecyclerView.ViewHolder {
    private final String PAYMENT_URL = "https://secure24.ipayment.com/NYCPayments/nycbookmark_1.htm";
    private TextView summons_tv;
    private TextView fineAmount;
    private TextView issueDate_tv;
    private TextView violation_tv;
    private Button payButton;
    private Button calendarButton;
    private Button viewTicket;
    private ImageView ticketImageView;


    public ViolationViewHolder(View itemView) {
        super(itemView);
        summons_tv = (TextView) itemView.findViewById(R.id.summons_textview);
        issueDate_tv = (TextView) itemView.findViewById(R.id.issue_date_textview);
        fineAmount = (TextView) itemView.findViewById(R.id.fine_textview);
        violation_tv = (TextView) itemView.findViewById(R.id.violation_textview);
        payButton = (Button) itemView.findViewById(R.id.pay_button);
        calendarButton = (Button) itemView.findViewById(R.id.calendar_button);
        viewTicket =(Button) itemView.findViewById(R.id.violation_view_button);
        setImageView();

    }

    private void setImageView() {
        ticketImageView = new ImageView(itemView.getContext());
        ticketImageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }

    private void cardClick(final String issueImageURL) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(issueImageURL));
                itemView.getContext().startActivity(browserIntent);

            }
        });
    }

    private View.OnClickListener payButtonClick(final String string) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) itemView.getContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", string);
                clipboard.setPrimaryClip(clip);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PAYMENT_URL));
                itemView.getContext().startActivity(browserIntent);
            }
        };
    }

    public void bind(ParkingCameraResponse violations) {
        String violation = "<b>" + violations.getViolation() + "</b>";
        String fine_amount = "<b>Amount Due: </b>" + Integer.toString(violations.getAmountDue());
        String summons = "<b>Summons#: </b>" + violations.getSummonsNumber();
        String issueDate = "<b>Issue Date: </b>" + violations.getIssueDate();

        violation_tv.setText(Html.fromHtml(violation));
        issueDate_tv.setText(Html.fromHtml(issueDate));
        summons_tv.setText(Html.fromHtml(summons));
        fineAmount.setText(Html.fromHtml(fine_amount));
        payButton.setOnClickListener(payButtonClick(violations.getSummonsNumber()));
        cardClick(violations.getIssueImageURL());
    }
}

