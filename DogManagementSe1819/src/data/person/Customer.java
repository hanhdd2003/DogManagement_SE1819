package data.person;

public class Customer extends Person {
    private String customerID;
    private String feedBack;
    private String reply;
    public Customer() {
    }

    public Customer(String customerID, String name, int age, String phoneNumber, String address) {
        super(name, age, phoneNumber, address);
        this.customerID = customerID;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
    
    
    
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void display() {
        System.out.format("%-17s %-20s %-10s %-19s %-20s\n",
                this.getCustomerID(), this.getName(), this.getAge(), this.getPhoneNumber(),
                this.getAddress());

    }

    @Override
    public String toString() {
        return this.customerID + " , " + super.toString()+" , "+this.feedBack+" , "+this.reply;
    }

}
