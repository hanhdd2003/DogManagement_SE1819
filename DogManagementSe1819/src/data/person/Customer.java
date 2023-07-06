package data.person;

public class Customer extends Person {

    private String giveFeedback;
    private String customerID;
    private String reply;

    public Customer() {
    }

    public Customer(String customerID, String name, int age, String phoneNumber, String address) {
        super(name, age, phoneNumber, address);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void display() {
        System.out.format("%-17s %-11s %-9s %-19s %-20s\n",
                this.getCustomerID(), this.getName(), this.getAge(), this.getPhoneNumber(),
                this.getAddress());

    }
    //String customerID, String name, int age, String phoneNumber, String address

    public String getGiveFeedback() {
        return giveFeedback;
    }

    public void setGiveFeedback(String giveFeedback) {
        this.giveFeedback = giveFeedback;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return this.customerID + " , " + super.toString();
    }

}
