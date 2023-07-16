package data.dog;

import javax.swing.JOptionPane;

public class DogForSend extends Dog {

    private String customerId;
    private String timeSend;
    private String timePickUp;
    private String name;

    public DogForSend() {
    }

    public DogForSend(String customerId, String timeSend, String timePickUp, String name, String dogID, int age, String gender, String dogBreed, String color, String healthyStatus, String vaccineStatus, double price) {
        super(dogID, age, gender, dogBreed, color, healthyStatus, vaccineStatus, price);
        this.customerId = customerId;
        this.timeSend = timeSend;
        this.timePickUp = timePickUp;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getTimePickUp() {
        return timePickUp;
    }

    public void setTimePickUp(String timePickUp) {
        this.timePickUp = timePickUp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayDogInformation(DogForSend dog) {
        String message = "ID: " + dog.getDogID() + "\n"
                + "Name: " + dog.getName() + "\n"
                + "Gender: " + dog.getGender() + "\n"
                + "Dog Breed: " + dog.getDogBreed() + "\n"
                + "Color: " + dog.getColor() + "\n"
                + "Healthy Status: " + dog.getHealthyStatus() + "\n"
                + "Vaccine Status: " + dog.getVaccineStatus() + "\n"
                + "Time Pick Up: " + dog.getTimePickUp() + "\n"
                + "Time Send: " + dog.getTimeSend() + "\n"
                + "Price: " + dog.getPrice();

        JOptionPane.showMessageDialog(null, message, "Dog Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public Object[] toDataArray() {
    return new Object[]{getDogID(), getName(), getGender(), getDogBreed(), getColor(), getHealthyStatus(), getVaccineStatus(), getPrice()};
}

    @Override
    public void display() {
        System.out.format("%-13s %-12s %-14s %-9s %-15s %-11s %-14s %-17s %-16s %-14s %-13.1f\n", this.customerId,
                this.getDogID(), this.getName(), this.getGender(),
                this.getDogBreed(), this.getColor(), this.getHealthyStatus(),
                this.getVaccineStatus(), this.getTimePickUp(), this.getTimeSend(), this.getPrice());
    }
    //String timeSend, String timePickUp, String name, 

    @Override
    public String toString() {
        return this.customerId + " , " + this.timeSend + " , " + this.timePickUp + " , " + this.name + " , " + super.toString();
    }

}
