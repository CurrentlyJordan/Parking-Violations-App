package nyc.c4q.jordansmith.finefree.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andresarango on 2/18/17.
 */
public class ParkingCameraResponse {

    @SerializedName("summons_number")
    private String summonsNumber;

    @SerializedName("summons_image")
    private String issueImage;

    @SerializedName("issue_date")
    private String issuDate;

    @SerializedName("payment_amount")
    private String paymentAmount;

    private String violation;

    public String getSummonsNumber() {
        return summonsNumber;
    }

    public String getIssueImage() {
        return issueImage;
    }

    public String getIssuDate() {
        return issuDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getViolation() {
        return violation;
    }


    /*"amount_due": "0",
	"county": "BX",
	"fine_amount": "45",
	"interest_amount": "8.33",
	"issue_date": "06/09/2015",
	"issuing_agency": "POLICE DEPARTMENT",
	"judgment_entry_date": "09/24/2015",
	"license_type": "PAS",
	"payment_amount": "113.28",
	"penalty_amount": "60",
	"plate": "GRV9657",
	"precinct": "042",
	"reduction_amount": "0.05",
	"state": "NY",
	"summons_image": "http://nycserv.nyc.gov/NYCServWeb/ShowImage?searchID=VFZSTk5FMXFTVE5OZWsxNFQxRTlQUT09&locationName=_____________________",
	"summons_image_description": "View Summons",
	"summons_number": "1382273319",
	"violation": "NO PARKING-STREET CLEANING",
	"violation_time": "02:20P"*/
}
