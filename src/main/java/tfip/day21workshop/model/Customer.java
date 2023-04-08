package tfip.day21workshop.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer implements Jsonable {
    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String stateProvince;

    public Customer() {
    }

    public Customer(Integer id, String company, String lastName, String firstName, String emailAddress, String jobTitle,
            String businessPhone, String homePhone, String mobilePhone, String address, String stateProvince) {
        this.id = id;
        this.company = company;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.businessPhone = businessPhone;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.stateProvince = stateProvince;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName
                + ", emailAddress=" + emailAddress + ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone
                + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone + ", address=" + address
                + ", stateProvince=" + stateProvince + "]";
    }

    // convert object to JSON
    @Override
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("company", getCompany())
                .add("last_name", getLastName())
                .add("first_name", getFirstName())
                .add("email_address", getEmailAddress())
                .add("job_title", getJobTitle())
                .add("business_phone", getBusinessPhone())
                .add("home_phone", getHomePhone())
                .add("mobile_phone", getMobilePhone())
                .add("address", getAddress())
                .add("state_province", getStateProvince())
                .build();
    }

    // create object from sqlrowset
    public static Customer create(SqlRowSet rs) {
        return new Customer(
                rs.getInt("id"),
                rs.getString("company"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("email_address"),
                rs.getString("job_title"),
                rs.getString("business_phone"),
                rs.getString("home_phone"),
                rs.getString("mobile_phone"),
                rs.getString("address"),
                rs.getString("state_province"));
    }

}
