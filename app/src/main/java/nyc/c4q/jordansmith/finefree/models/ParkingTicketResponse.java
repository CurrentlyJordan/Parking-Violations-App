package nyc.c4q.jordansmith.finefree.models;

/**
 * Created by helenchan on 2/18/17.
 */

public class ParkingTicketResponse {

    private String amount_due;
    private String county;
    private String fine_amount;
    private String interest_amount;
    private String issue_date;
    private String issuing_agency;
    private String judgment_entry_date;
    private String license_type;
    private String payment_amount;
    private String penalty_amount;
    private String plate;
    private String precinct;
    private String state;
    private String summons_image;
    private String summons_number;
    private String violation;
    private String violation_time;

    public String getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(String amount_due) {
        this.amount_due = amount_due;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(String fine_amount) {
        this.fine_amount = fine_amount;
    }

    public String getInterest_amount() {
        return interest_amount;
    }

    public void setInterest_amount(String interest_amount) {
        this.interest_amount = interest_amount;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getIssuing_agency() {
        return issuing_agency;
    }

    public void setIssuing_agency(String issuing_agency) {
        this.issuing_agency = issuing_agency;
    }

    public String getJudgment_entry_date() {
        return judgment_entry_date;
    }

    public void setJudgment_entry_date(String judgment_entry_date) {
        this.judgment_entry_date = judgment_entry_date;
    }

    public String getLicense_type() {
        return license_type;
    }

    public void setLicense_type(String license_type) {
        this.license_type = license_type;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPenalty_amount() {
        return penalty_amount;
    }

    public void setPenalty_amount(String penalty_amount) {
        this.penalty_amount = penalty_amount;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPrecinct() {
        return precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSummons_image() {
        return summons_image;
    }

    public void setSummons_image(String summons_image) {
        this.summons_image = summons_image;
    }

    public String getSummons_number() {
        return summons_number;
    }

    public void setSummons_number(String summons_number) {
        this.summons_number = summons_number;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getViolation_time() {
        return violation_time;
    }

    public void setViolation_time(String violation_time) {
        this.violation_time = violation_time;
    }
}
