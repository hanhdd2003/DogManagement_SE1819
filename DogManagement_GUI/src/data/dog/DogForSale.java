package data.dog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tool.MyTool;

public class DogForSale extends Dog {

    MyTool tool = new MyTool();
    private String origin;

    public DogForSale() {
    }

    public DogForSale(String origin, String dogID, int age, String gender, String dogBreed, String color, String healthyStatus, String vaccineStatus, double price) {
        super(dogID, age, gender, dogBreed, color, healthyStatus, vaccineStatus, price);
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void displayGUI() {
        JFrame frame = new JFrame("Dog Information");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String dogID = this.getDogID();
        String origin1 = this.getOrigin();
        String gender = this.getGender();
        String dogBreed = this.getDogBreed();
        String color = this.getColor();
        String healthyStatus = this.getHealthyStatus();
        String vaccineStatus = this.getVaccineStatus();
        double price = this.getPrice();

        String labelText = "<html><b>ID:</b> " + dogID
                + "<br><b>Origin:</b> " + origin1
                + "<br><b>Gender:</b> " + gender
                + "<br><b>Dog Breed:</b> " + dogBreed
                + "<br><b>Color:</b> " + color
                + "<br><b>Healthy Status:</b> " + healthyStatus
                + "<br><b>Vaccine Status:</b> " + vaccineStatus
                + "<br><b>Price:</b> " + price + "</html>";

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 20));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Đóng JFrame khi ấn nút "OK"
            }
        });

        panel.add(okButton, BorderLayout.SOUTH);
        frame.add(panel);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void display() {
        System.out.printf("%-10s %-11s %-8s %-15s %-10s %-15s %-15s %-10.1f \t\n",
                this.getDogID(), this.getOrigin(), this.getGender(),
                this.getDogBreed(), this.getColor(), this.getHealthyStatus(),
                this.getVaccineStatus(), this.getPrice());
    }
//String origin, String dogID, int age, String gender, String dogBreed, String color, String healthyStatus, String vaccineStatus, double price

    @Override
    public String toString() {
        return this.origin + " , " + super.toString();
    }

}
