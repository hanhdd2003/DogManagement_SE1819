package data.Sevice;

import data.dog.*;
import data.*;
import data.history.History;
import data.person.Customer;
import data.person.Employee;
import data.person.Feedback;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import tool.MyTool;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Service {

    private MyTool tool = new MyTool();
    private ArrayList<DogForSale> dogSale = new ArrayList<>();
    private ArrayList<DogForSend> dogSend = new ArrayList<>();
    private ArrayList<Customer> cus = new ArrayList<>();
    private ArrayList<Employee> emp = new ArrayList<>();
    private ArrayList<History> his = new ArrayList<>();
    private ArrayList<Feedback> feedback = new ArrayList<>();

    public Service() {
    }

//--------------------------------------------load dữ liệu------------------------------
    public void loadData() {
        try {
            this.readDogSale();
            this.readDogSend();
            this.readCustomer();
            this.readEmployee();
            this.readHistory();
            this.readFeedback();
        } catch (Exception e) {
            System.err.println("Lỗi phần LoadData!");
        }
    }

//-------------------------------------Sale dog--------------------------------
    public void saleDog() {
        try {
            boolean a = true;
            while (a) {
                this.displayCus();

                String idCus = JOptionPane.showInputDialog("Input Customer ID:");

                // Kiểm tra ID khách hàng
                if (searchCus(idCus, cus) == null) {
                    JOptionPane.showMessageDialog(null, "Customer ID does not exist!");
                    int option = JOptionPane.showConfirmDialog(null, "Add new customer?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Customer cusTemp = inputCustomer();
                        cus.add(cusTemp);
                    } else {
                        a = false;
                    }
                }

                this.displayDogSale();

                String idDog = JOptionPane.showInputDialog("Input ID of the dog you want to buy:");

                // Kiểm tra ID chó bán
                if (searchDogSale(idDog, dogSale) == null) {
                    JOptionPane.showMessageDialog(null, "Dog ID does not exist!");
                } else {
                    this.displayEmp();

                    String idEmp = JOptionPane.showInputDialog("Input employee ID:");

                    // Kiểm tra ID nhân viên
                    if (seachEmp(idEmp, emp) == null) {
                        JOptionPane.showMessageDialog(null, "Employee ID does not exist!");
                    } else {
                        double price = searchDogSale(idDog, dogSale).getPrice();
                        JOptionPane.showMessageDialog(null, "You have to pay: " + price);

                        // Cập nhật dữ liệu
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String formattedDateTime = now.format(formatter);
                        History his1 = new History(1, idCus.toUpperCase(), idDog.toUpperCase(),
                                idEmp.toUpperCase(), price, formattedDateTime);
                        his.add(his1);

                        // Tăng bonus cho nhân viên
                        if (seachEmp(idEmp, emp) != null && searchDogSale(idDog, dogSale) != null) {
                            double bonus = price * 0.1;
                            seachEmp(idEmp, emp).setBonus(bonus);
                        }

                        dogSale.remove(searchDogSale(idDog, dogSale));

                        // Lưu dữ liệu
                        this.saveDogSale(dogSale);
                        this.saveCustomer(cus);
                        this.saveHistory(his);
                        this.saveEmployee(emp);

                        JOptionPane.showMessageDialog(null, "Purchase successful!");
                        a = false;
                    }
                }
            }
        } catch (HeadlessException e) {
            System.err.println("An error occurred in the saleDog() method.");
        }
    }

    //-------------------------------------Send dog---------------------------------
    public void sendDog() {
        try {
            boolean a = true;
            while (a) {
                this.displayCus();

                String idCus = JOptionPane.showInputDialog("Input Customer ID:");

                // Kiểm tra ID khách hàng
                if (!this.checkIdCus(cus, idCus)) {
                    JOptionPane.showMessageDialog(null, "Customer ID does not exist!");
                    int option = JOptionPane.showConfirmDialog(null, "Add new customer?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Customer cusTemp = inputCustomer();
                        cus.add(cusTemp);
                    } else {
                        a = false;
                    }
                }

                String idSend = JOptionPane.showInputDialog("Input ID Dog Send:");

                // Kiểm tra ID chó gửi
                if (checkIdDogSend(dogSend, idSend)) {
                    JOptionPane.showMessageDialog(null, "Dog Send already exists!");
                } else {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(10, 2));

                    JTextField nameField = new JTextField();
                    JTextField ageField = new JTextField();
                    JTextField genderField = new JTextField();
                    JTextField breedField = new JTextField();
                    JTextField colorField = new JTextField();
                    JTextField healthField = new JTextField();
                    JTextField vaccineField = new JTextField();
                    JTextField priceField = new JTextField();
                    JTextField timeSendField = new JTextField();
                    JTextField timePickUpField = new JTextField();

                    panel.add(new JLabel("Dog Name: "));
                    panel.add(nameField);
                    panel.add(new JLabel("Age: "));
                    panel.add(ageField);
                    panel.add(new JLabel("Gender: "));
                    panel.add(genderField);
                    panel.add(new JLabel("Breed: "));
                    panel.add(breedField);
                    panel.add(new JLabel("Color: "));
                    panel.add(colorField);
                    panel.add(new JLabel("Health Status: "));
                    panel.add(healthField);
                    panel.add(new JLabel("Vaccine Status: "));
                    panel.add(vaccineField);
                    panel.add(new JLabel("Price: "));
                    panel.add(priceField);
                    panel.add(new JLabel("Time Send: "));
                    panel.add(timeSendField);
                    panel.add(new JLabel("Time Pick Up: "));
                    panel.add(timePickUpField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Add Dog Send", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String name = nameField.getText().toUpperCase();
                        int age = Integer.parseInt(ageField.getText());
                        String gender = genderField.getText().toUpperCase();
                        String breed = breedField.getText().toUpperCase();
                        String color = colorField.getText().toUpperCase();
                        String health = healthField.getText().toUpperCase();
                        String vaccine = vaccineField.getText().toUpperCase();
                        double price = Double.parseDouble(priceField.getText());
                        String timeSend = timeSendField.getText().toUpperCase();
                        String timePickUp = timePickUpField.getText().toUpperCase();

                        DogForSend sendTemp = new DogForSend(idCus, timeSend, timePickUp, name, idSend, age, gender, breed, color, health, vaccine, price);
                        dogSend.add(sendTemp);

                        displayEmp();
                        String idEmp = JOptionPane.showInputDialog("Input Employee ID:");

                        // Kiểm tra ID nhân viên
                        if (checkIdEmp(emp, idEmp)) {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String formattedDateTime = now.format(formatter);
                            History hisTemp = new History(2, idCus.toUpperCase(), idSend.toUpperCase(), idEmp.toUpperCase(), price, formattedDateTime);
                            his.add(hisTemp);

                            JOptionPane.showMessageDialog(null, "Send successfully!");
                            a = false;
                        } else {
                            JOptionPane.showMessageDialog(null, "Employee ID does not exist!");
                        }
                    } else {
                        a = false;
                    }
                }
            }

            this.saveCustomer(cus);
            this.saveDogSend(dogSend);
            this.saveHistory(his);
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("An error occurred in the sendDog() method.");
        }
    }

    //-------------------------------------pick up dog ----------------------------
    public void pickUpDog() {
    try {
        boolean a = true;
        while (a) {
            this.displayDogSend();

            String idCus = JOptionPane.showInputDialog("Input Customer ID:");
            // Kiểm tra ID khách hàng
            boolean customerExists = false;
            for (DogForSend send : dogSend) {
                if (send.getCustomerId().equalsIgnoreCase(idCus)) {
                    customerExists = true;
                    send.displayDogInformation(send);
                }
            }

            if (!customerExists) {
                JOptionPane.showMessageDialog(null, "Customer ID does not exist!");
            } else {
                String idSend = JOptionPane.showInputDialog("Input ID Dog Send:");

                // Kiểm tra ID chó gửi
                boolean dogSendExists = this.checkIdDogSend(dogSend, idSend);
                if (dogSendExists) {
                    DogForSend sendDog = this.searchDogSend(idSend, dogSend);
                    String[] columnNames = {"DOG ID", "ORIGIN", "GENDER", "DOG BREED", "COLOR", "HEALTH STATUS", "VACCINE STATUS", "PRICE"};
                    Object[][] data = {sendDog.toDataArray()};

                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setFillsViewportHeight(true);

                    JOptionPane.showMessageDialog(null, scrollPane, "Dog Send Details", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "You have to pay: " + sendDog.getPrice());

                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDateTime = now.format(formatter);
                    for (History history : his) {
                        if (history.getType().equals("02") && history.getIdCus().equalsIgnoreCase(idCus) && history.getIdDog().equalsIgnoreCase(idSend)) {
                            History temp = new History(3, history.getIdCus().toUpperCase(),
                                    history.getIdDog().toUpperCase(), history.getIdEmp().toUpperCase(), history.getPrice(), formattedDateTime);
                            his.add(temp);
                            break;
                        }
                    }

                    dogSend.remove(sendDog);
                    a = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Dog Send ID does not exist!");
                }
            }
        }
        this.saveDogSend(dogSend);
        this.saveHistory(his);
    } catch (HeadlessException e) {
        System.err.println("An error occurred in the pickUpDog() method.");
    }
}


    //-------------------------------------thêm các đối tượng-----------------------------------
    public void inputDogSale() {
        try {
            boolean isAdding = true;
            while (isAdding) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField id = new JTextField();
                panel.add(new JLabel("ID Dog Sale: "));
                panel.add(id);

                int result = JOptionPane.showConfirmDialog(null, panel, "Input ID Dog", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (!this.checkIdDogSale(dogSale, id.getText())) {
                        JPanel panel1 = new JPanel();
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                        JTextField age = new JTextField();
                        panel1.add(new JLabel("Input Age: "));
                        panel1.add(age);

                        JTextField gender = new JTextField();
                        panel1.add(new JLabel("Input Gender: "));
                        panel1.add(gender);

                        JTextField dogBreed = new JTextField();
                        panel1.add(new JLabel("Input Dog Breed: "));
                        panel1.add(dogBreed);

                        JTextField color = new JTextField();
                        panel1.add(new JLabel("Input Color: "));
                        panel1.add(color);

                        JTextField healthyStatus = new JTextField();
                        panel1.add(new JLabel("Input Health Status: "));
                        panel1.add(healthyStatus);

                        JTextField vaccineStatus = new JTextField();
                        panel1.add(new JLabel("Input Vaccine Status: "));
                        panel1.add(vaccineStatus);

                        JTextField price = new JTextField();
                        panel1.add(new JLabel("Input Price: "));
                        panel1.add(price);

                        JTextField origin = new JTextField();
                        panel1.add(new JLabel("Input Origin: "));
                        panel1.add(origin);

                        int result1 = JOptionPane.showConfirmDialog(null, panel1, "Add Dog Sale", JOptionPane.OK_CANCEL_OPTION);
                        if (result1 == JOptionPane.OK_OPTION) {
                            try {
                                String dogId = id.getText().toUpperCase();
                                int dogAge = Integer.parseInt(age.getText());
                                String dogGender = gender.getText().toUpperCase();
                                String dogBreedText = dogBreed.getText().toUpperCase();
                                String dogColor = color.getText().toUpperCase();
                                String dogHealthyStatus = healthyStatus.getText().toUpperCase();
                                String dogVaccineStatus = vaccineStatus.getText().toUpperCase();
                                double dogPrice = Double.parseDouble(price.getText());
                                String dogOrigin = origin.getText().toUpperCase();

                                DogForSale temp = new DogForSale(dogOrigin, dogId, dogAge, dogGender, dogBreedText, dogColor, dogHealthyStatus, dogVaccineStatus, dogPrice);
                                dogSale.add(temp);
                                JOptionPane.showMessageDialog(null, "Add successful!");
                                this.saveDogSale(dogSale);
                                isAdding = false;
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numbers.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Dog already exists!");
                    }
                } else {
                    isAdding = false;
                }
            }
        } catch (HeadlessException e) {
            System.err.println("Có lỗi phần inputDogSale.");
        }
    }

    public void inputEmployee() {
        try {
            boolean a = true;
            while (a) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField employeeID = new JTextField();
                panel.add(new JLabel("Employee ID: "));
                panel.add(employeeID);

                int result = JOptionPane.showConfirmDialog(null, panel, "Input Employee Information", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (!this.checkIdEmp(emp, employeeID.getText())) {
                        JPanel panel1 = new JPanel();
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                        JTextField name = new JTextField();
                        panel1.add(new JLabel("Name: "));
                        panel1.add(name);

                        JTextField age = new JTextField();
                        panel1.add(new JLabel("Age: "));
                        panel1.add(age);

                        JTextField phoneNumber = new JTextField();
                        panel1.add(new JLabel("Phone Number: "));
                        panel1.add(phoneNumber);

                        JTextField address = new JTextField();
                        panel1.add(new JLabel("Address: "));
                        panel1.add(address);

                        int result1 = JOptionPane.showConfirmDialog(null, panel1, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
                        if (result1 == JOptionPane.OK_OPTION) {
                            emp.add(new Employee(employeeID.getText().toUpperCase(),
                                    name.getText().toUpperCase(), Integer.parseInt(age.getText()), phoneNumber.getText(), address.getText().toUpperCase()));
                            JOptionPane.showMessageDialog(null, "Add employee successfully!");
                            this.saveEmployee(emp);
                            a = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee ID already exists!");
                    }
                } else {
                    a = false;
                }
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần inputEmployee() .");
        }
    }

    public Customer inputCustomer() {
        try {
            boolean a = true;
            while (a) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField customerID = new JTextField();
                panel.add(new JLabel("Customer ID: "));
                panel.add(customerID);

                int result = JOptionPane.showConfirmDialog(null, panel, "Input Customer Information", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    if (!this.checkIdCus(cus, customerID.getText())) {
                        JPanel panel1 = new JPanel();
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                        JTextField name = new JTextField();
                        panel1.add(new JLabel("Name: "));
                        panel1.add(name);

                        JTextField age = new JTextField();
                        panel1.add(new JLabel("Age: "));
                        panel1.add(age);

                        JTextField phoneNumber = new JTextField();
                        panel1.add(new JLabel("Phone Number: "));
                        panel1.add(phoneNumber);

                        JTextField address = new JTextField();
                        panel1.add(new JLabel("Address: "));
                        panel1.add(address);

                        int result1 = JOptionPane.showConfirmDialog(null, panel1, "Add Customer", JOptionPane.OK_CANCEL_OPTION);
                        if (result1 == JOptionPane.OK_OPTION) {
                            Customer customer = new Customer(customerID.getText().toUpperCase(),
                                    name.getText().toUpperCase(), Integer.parseInt(age.getText()),
                                    phoneNumber.getText(), address.getText().toUpperCase());
                            cus.add(customer);
                            JOptionPane.showMessageDialog(null, "Add customer successfully!");
                            this.saveCustomer(cus);
                            a = false;
                            return customer;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer ID already exists!");
                    }
                } else {
                    a = false;
                }
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần inputCustomer().");
        }
        return null;
    }

//--------------------------sửa thông tin các đối tượng-----------------------
    public void fixInfoEmp() {
        try {
            String employee = JOptionPane.showInputDialog("ID of Employee need to edit:");
            if (this.checkIdEmp(emp, employee)) {
                Employee employ = this.seachEmp(employee, emp);
                boolean isEditing = true;
                while (isEditing) {
                    String[] options = {"Employee ID", "Employee Name", "Employee Age", "Employee PhoneNumber", "Employee Day Number", "Employee Salary", "Employee Bonus", "Employee Address", "Exit"};
                    int choice = JOptionPane.showOptionDialog(null, "What employee information do you want to edit?", "Edit Employee Information", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    switch (choice) {
                        case 0:
                            String newID = JOptionPane.showInputDialog("Enter new ID:");
                            employ.setEmployeeID(newID);
                            JOptionPane.showMessageDialog(null, "Update ID successful!");
                            break;
                        case 1:
                            String newName = JOptionPane.showInputDialog("Enter new Name:");
                            employ.setName(newName);
                            JOptionPane.showMessageDialog(null, "Update Name successful!");
                            break;
                        case 2:
                            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new Age:"));
                            employ.setAge(newAge);
                            JOptionPane.showMessageDialog(null, "Update Age successful!");
                            break;
                        case 3:
                            String newPhoneNumber = JOptionPane.showInputDialog("Enter new PhoneNumber:");
                            employ.setPhoneNumber(newPhoneNumber);
                            JOptionPane.showMessageDialog(null, "Update PhoneNumber successful!");
                            break;
                        case 4:
                            double newDay = Double.parseDouble(JOptionPane.showInputDialog("Enter new Day Number:"));
                            employ.setDayNumber(newDay);
                            JOptionPane.showMessageDialog(null, "Update Day Number successful!");
                            break;
                        case 5:
                            double newSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter new Salary:"));
                            employ.setSalary(newSalary);
                            JOptionPane.showMessageDialog(null, "Update Salary successful!");
                            break;
                        case 6:
                            double newBonus = Double.parseDouble(JOptionPane.showInputDialog("Enter new Bonus:"));
                            employ.setBonus(newBonus);
                            JOptionPane.showMessageDialog(null, "Update Bonus successful!");
                            break;
                        case 7:
                            String newAddress = JOptionPane.showInputDialog("Enter new Address:");
                            employ.setAddress(newAddress);
                            JOptionPane.showMessageDialog(null, "Update Address successful!");
                            break;
                        case 8:
                            isEditing = false;
                            break;
                        default:
                            break;
                    }
                }
                this.saveEmployee(emp);
            } else {
                JOptionPane.showMessageDialog(null, "Can't find Employee!");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần fixInfoEmp().");
        }
    }

    public void fixInfoCus() {
        try {
            String customer = JOptionPane.showInputDialog("ID of Customer need to edit:");
            if (this.checkIdCus(cus, customer)) {
                Customer cus1 = this.searchCus(customer, cus);
                boolean isEditing = true;
                while (isEditing) {
                    String[] options = {"Customer ID", "Customer Name", "Customer Age", "Customer PhoneNumber", "Customer Address", "Exit"};
                    int choice = JOptionPane.showOptionDialog(null, "What customer information do you want to edit?", "Edit Customer Information", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    switch (choice) {
                        case 0:
                            String newID = JOptionPane.showInputDialog("Enter new ID:");
                            cus1.setCustomerID(newID);
                            JOptionPane.showMessageDialog(null, "Update ID successful!");
                            break;
                        case 1:
                            String newName = JOptionPane.showInputDialog("Enter new Name:");
                            cus1.setName(newName);
                            JOptionPane.showMessageDialog(null, "Update Name successful!");
                            break;
                        case 2:
                            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new Age:"));
                            cus1.setAge(newAge);
                            JOptionPane.showMessageDialog(null, "Update Age successful!");
                            break;
                        case 3:
                            String newPhoneNumber = JOptionPane.showInputDialog("Enter new PhoneNumber:");
                            cus1.setPhoneNumber(newPhoneNumber);
                            JOptionPane.showMessageDialog(null, "Update PhoneNumber successful!");
                            break;
                        case 4:
                            String newAddress = JOptionPane.showInputDialog("Enter new Address:");
                            cus1.setAddress(newAddress);
                            JOptionPane.showMessageDialog(null, "Update Address successful!");
                            break;
                        case 5:
                            isEditing = false;
                            break;
                        default:
                            break;
                    }
                }
                this.saveCustomer(cus);
            } else {
                JOptionPane.showMessageDialog(null, "Can't find Customer!");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần fixInfoCus() .");
        }
    }

    public void fixInfoDogSale() {
        try {
            String saleID = JOptionPane.showInputDialog("ID of Dog Sale need to edit:");
            if (this.checkIdDogSale(dogSale, saleID)) {
                DogForSale saleDog = this.searchDogSale(saleID, dogSale);
                boolean isEditing = true;
                while (isEditing) {
                    String[] options = {"Dog Sale ID", "Dog Sale Origin", "Dog Sale Age", "Dog Sale Gender", "Dog Sale Breed", "Dog Sale Color", "Dog Sale Health", "Dog Sale Vaccine", "Dog Sale Price", "Exit"};
                    int choice = JOptionPane.showOptionDialog(null, "What Dog Sale information do you want to edit?", "Edit Dog Sale Information", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    switch (choice) {
                        case 0:
                            String newID = JOptionPane.showInputDialog("Enter new ID:");
                            saleDog.setDogID(newID);
                            JOptionPane.showMessageDialog(null, "Update ID successful!");
                            break;
                        case 1:
                            String newOrigin = JOptionPane.showInputDialog("Enter new Origin:");
                            saleDog.setOrigin(newOrigin);
                            JOptionPane.showMessageDialog(null, "Update Origin successful!");
                            break;
                        case 2:
                            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new Age:"));
                            saleDog.setAge(newAge);
                            JOptionPane.showMessageDialog(null, "Update Age successful!");
                            break;
                        case 3:
                            String newGender = JOptionPane.showInputDialog("Enter new Gender:");
                            saleDog.setGender(newGender);
                            JOptionPane.showMessageDialog(null, "Update Gender successful!");
                            break;
                        case 4:
                            String newBreed = JOptionPane.showInputDialog("Enter new Breed:");
                            saleDog.setDogBreed(newBreed);
                            JOptionPane.showMessageDialog(null, "Update Breed successful!");
                            break;
                        case 5:
                            String newColor = JOptionPane.showInputDialog("Enter new Color:");
                            saleDog.setColor(newColor);
                            JOptionPane.showMessageDialog(null, "Update Color successful!");
                            break;
                        case 6:
                            String newHealth = JOptionPane.showInputDialog("Enter new Health:");
                            saleDog.setHealthyStatus(newHealth);
                            JOptionPane.showMessageDialog(null, "Update Health successful!");
                            break;
                        case 7:
                            String newVaccine = JOptionPane.showInputDialog("Enter new Vaccine:");
                            saleDog.setVaccineStatus(newVaccine);
                            JOptionPane.showMessageDialog(null, "Update Vaccine successful!");
                            break;
                        case 8:
                            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new Price:"));
                            saleDog.setPrice(newPrice);
                            JOptionPane.showMessageDialog(null, "Update Price successful!");
                            break;
                        case 9:
                            isEditing = false;
                            break;
                        default:
                            break;
                    }
                }
                this.saveDogSale(dogSale);
            } else {
                JOptionPane.showMessageDialog(null, "Can't find Dog Sale!");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần fixInfoDogSale().");
        }
    }

    public void fixInfoDogSend() {
        try {
            String sendID = JOptionPane.showInputDialog("ID of Dog Send need to edit:");
            if (this.checkIdDogSend(dogSend, sendID)) {
                DogForSend sendDog = this.searchDogSend(sendID, dogSend);
                boolean isEditing = true;
                while (isEditing) {
                    String[] options = {"Dog Send ID", "Dog Send Name", "Dog Send Age", "Dog Send Gender", "Dog Send Breed", "Dog Send Color", "Dog Send Health", "Dog Send Vaccine", "Dog Send Price", "Dog Send Time Send", "Dog Send Time PickUp", "Exit"};
                    int choice = JOptionPane.showOptionDialog(null, "What Dog Send information do you want to edit?", "Edit Dog Send Information", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    switch (choice) {
                        case 0:
                            String newID = JOptionPane.showInputDialog("Enter new ID:");
                            sendDog.setDogID(newID);
                            JOptionPane.showMessageDialog(null, "Update ID successful!");
                            break;
                        case 1:
                            String newName = JOptionPane.showInputDialog("Enter new Name:");
                            sendDog.setName(newName);
                            JOptionPane.showMessageDialog(null, "Update Name successful!");
                            break;
                        case 2:
                            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new Age:"));
                            sendDog.setAge(newAge);
                            JOptionPane.showMessageDialog(null, "Update Age successful!");
                            break;
                        case 3:
                            String newGender = JOptionPane.showInputDialog("Enter new Gender:");
                            sendDog.setGender(newGender);
                            JOptionPane.showMessageDialog(null, "Update Gender successful!");
                            break;
                        case 4:
                            String newBreed = JOptionPane.showInputDialog("Enter new Breed:");
                            sendDog.setDogBreed(newBreed);
                            JOptionPane.showMessageDialog(null, "Update Breed successful!");
                            break;
                        case 5:
                            String newColor = JOptionPane.showInputDialog("Enter new Color:");
                            sendDog.setColor(newColor);
                            JOptionPane.showMessageDialog(null, "Update Color successful!");
                            break;
                        case 6:
                            String newHealth = JOptionPane.showInputDialog("Enter new Health:");
                            sendDog.setHealthyStatus(newHealth);
                            JOptionPane.showMessageDialog(null, "Update Health successful!");
                            break;
                        case 7:
                            String newVaccine = JOptionPane.showInputDialog("Enter new Vaccine:");
                            sendDog.setVaccineStatus(newVaccine);
                            JOptionPane.showMessageDialog(null, "Update Vaccine successful!");
                            break;
                        case 8:
                            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new Price:"));
                            sendDog.setPrice(newPrice);
                            JOptionPane.showMessageDialog(null, "Update Price successful!");
                            break;
                        case 9:
                            String newTimeSend = JOptionPane.showInputDialog("Enter new Time Send:");
                            sendDog.setTimeSend(newTimeSend);
                            JOptionPane.showMessageDialog(null, "Update Time Send successful!");
                            break;
                        case 10:
                            String newPickUp = JOptionPane.showInputDialog("Enter new Time PickUp:");
                            sendDog.setTimePickUp(newPickUp);
                            JOptionPane.showMessageDialog(null, "Update Time PickUp successful!");
                            break;
                        case 11:
                            isEditing = false;
                            break;
                        default:
                            break;
                    }
                }
                this.saveDogSend(dogSend);
            } else {
                JOptionPane.showMessageDialog(null, "Can't find Dog Send!");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.err.println("Có lỗi phần fixInfoDogSend().");
        }
    }

//=============================================================================
    //-----------------------phần xóa chó, xóa khách, xóa nhân viên-------------------------
    public void removeDogSale() {
        try {
            String dogID = JOptionPane.showInputDialog("Enter ID Dog Sale to remove:");
            if (this.checkIdDogSale(dogSale, dogID)) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Dog Sale?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dogSale.remove(this.searchDogSale(dogID, dogSale));
                    this.saveDogSale(dogSale);
                    JOptionPane.showMessageDialog(null, "Dog Sale removed successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Can't find Dog For Sale!");
            }
        } catch (HeadlessException e) {
            System.err.println("Có lỗi phần removeDogSale() method.");
        }
    }

    public void removeDogSend() {
        String dogID = JOptionPane.showInputDialog("Enter ID Dog Send to remove:");
        if (this.checkIdDogSend(dogSend, dogID)) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Dog Send?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dogSend.remove(this.searchDogSend(dogID, dogSend));
                this.saveDogSend(dogSend);
                JOptionPane.showMessageDialog(null, "Dog Send removed successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can't find Dog For Send!");
        }
    }

    public void removeCustomer() {
        String customerID = JOptionPane.showInputDialog("Enter ID Customer to remove:");
        if (this.checkIdCus(cus, customerID)) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Customer?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cus.remove(searchCus(customerID, cus));
                this.saveCustomer(cus);
                JOptionPane.showMessageDialog(null, "Customer removed successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can't find Customer!");
        }
    }

    public void removeEmployee() {
        String employeeID = JOptionPane.showInputDialog("Enter ID Employee to remove:");
        if (this.checkIdEmp(emp, employeeID)) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Employee?", "Confirm Remove", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                emp.remove(seachEmp(employeeID, emp));
                this.saveEmployee(emp);
                JOptionPane.showMessageDialog(null, "Employee removed successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can't find Employee!");
        }
    }

// --------------------------------------phan display---------------------------------------
    public boolean displayDogSale() {
        JFrame frame = new JFrame("LIST DOG SALE");

        String[] columnNames = {"DOG ID", "ORIGIN", "GENDER", "DOG BREED",
            "COLOR", "HEALTH STATUS", "VACCINE STATUS", "PRICE"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        dogSale.forEach((sale) -> {
            model.addRow(new Object[]{sale.getDogID(), sale.getOrigin(), sale.getGender(),
                sale.getDogBreed(), sale.getColor(), sale.getHealthyStatus(), sale.getVaccineStatus(), sale.getPrice()});
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }
        return true;
    }

    public boolean displayDogSend() {
        JFrame frame = new JFrame("LIST DOG SEND");

        String[] columnNames = {"CUSTOMER ID", "DOG ID", "NAME", "GENDER", "DOG BREED",
            "COLOR", "HEALTH STATUS", "VACCINE STATUS", "TIME PICK UP", "TIME SEND", "PRICE"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        dogSend.forEach((send) -> {
            model.addRow(new Object[]{send.getCustomerId(), send.getDogID(), send.getName(), send.getGender(),
                send.getDogBreed(), send.getColor(), send.getHealthyStatus(), send.getVaccineStatus(),
                send.getTimePickUp(), send.getTimeSend(), send.getPrice()});
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }
        return true;
    }

    public boolean displayCus() {
        JFrame frame = new JFrame("LIST CUSTOMER");

        String[] columnNames = {"CUSTOMER ID", "NAME", "AGE", "PHONE NUMBER", "ADDRESS"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        cus.forEach((cus1) -> {
            model.addRow(new Object[]{cus1.getCustomerID(), cus1.getName(), cus1.getAge(),
                cus1.getPhoneNumber(), cus1.getAddress()});
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }
        return true;
    }

    public boolean displayEmp() {
        JFrame frame = new JFrame("LIST EMPLOYEE");

        String[] columnNames = {"EMPLOYEE ID", "DAY", "NAME", "AGE", "PHONE NUMBER", "ADDRESS"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        this.emp.forEach((emp1) -> {
            model.addRow(new Object[]{emp1.getEmployeeID(), emp1.getDayNumber(), emp1.getName(),
                emp1.getAge(), emp1.getPhoneNumber(), emp1.getAddress()});
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }
        return true;
    }

    public void displayHistory() {
        JFrame frame = new JFrame("LIST HISTORY");

        String[] columnNames = {"HISTORY TYPE", "PRICE", "CUSTOMER ID", "DOG ID", "EMPLOYEE ID", "TIME"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        his.forEach((history) -> {
            model.addRow(new Object[]{history.getType(), history.getPrice(), history.getIdCus(),
                history.getIdDog(), history.getIdEmp(), history.getCurrentTime()});
        });

        frame.add(scrollPane);
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }
    }

    //----------------------------------------phần ghi xuống file-------------------------------------------------
    public void saveDogSale(ArrayList<DogForSale> dogSale) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataDogSale.txt"))) {
            for (DogForSale dogForSale : dogSale) {
                writer.write(dogForSale + "");
                writer.newLine();
            }
            writer.close();
            System.out.println("Save to file Dog Sale complete!");
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

    public void saveDogSend(ArrayList<DogForSend> dogSend) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataDogSend.txt"))) {
            for (DogForSend dogForSend : dogSend) {
                writer.write(dogForSend + "");
                writer.newLine();
            }
            writer.close();
            //System.out.println("Save to file complete!");
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

    public void saveCustomer(ArrayList<Customer> cus) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataCustomer.txt"))) {
            for (Customer customer : cus) {
                writer.write(customer + "");
                writer.newLine();
            }
            writer.close();
            //System.out.println("Save to file complete!");
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

    public void saveEmployee(ArrayList<Employee> emp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataEmployee.txt"))) {
            for (Employee employee : emp) {
                writer.write(employee + "");
                writer.newLine();
            }
            writer.close();
            //System.out.println("Save to file complete!");
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

    public void saveHistory(ArrayList<History> his) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataHistory.txt"))) {
            for (History history : his) {
                writer.write(history + "");
                writer.newLine();
            }
            writer.close();
            //System.out.println("Save to file complete!");
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

    public void saveFeedBack(ArrayList<Feedback> feedback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataFeedback.txt"))) {
            for (Feedback fb : feedback) {
                writer.write(fb + "");
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.err.println("Can't save file!");
        }
    }

//--------------------------------------- phần đọc file ------------------------------------------------
    public void readDogSale() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataDogSale.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                try {
                    String[] parts = line.split(" , ");
                    String origin = parts[0];
                    String dogID = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String gender = parts[3];
                    String dogBreed = parts[4];
                    String color = parts[5];
                    String healthyStatus = parts[6];
                    String vaccineStatus = parts[7];
                    double price = Double.parseDouble(parts[8]);
                    DogForSale dg = new DogForSale(origin, dogID, age, gender, dogBreed, color, healthyStatus, vaccineStatus, price);
                    if (dogSale == null) {
                        dogSale = new ArrayList<>();
                    }
                    dogSale.add(dg);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            //System.out.println("Read Dog Sale complete!");
        } catch (Exception e) {
            System.err.println("Can't read Dog Sale file!");
        }
    }

    public void readDogSend() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataDogSend.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" , ");
                    String customerID = parts[0];
                    String timeSend = parts[1];
                    String timePickUp = parts[2];
                    String name = parts[3];
                    String dogID = parts[4];
                    int age = Integer.parseInt(parts[5]);
                    String gender = parts[6];
                    String dogBreed = parts[7];
                    String color = parts[8];
                    String healthy = parts[9];
                    String vaccine = parts[10];
                    double price = Double.parseDouble(parts[11]);
                    DogForSend send = new DogForSend(customerID, timeSend, timePickUp, name, dogID, age, gender, dogBreed, color, healthy, vaccine, price);
                    if (dogSend == null) {
                        dogSend = new ArrayList<>();
                    }
                    dogSend.add(send);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Can't read Dog Send file!");
        }
    }

    public void readHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataHistory.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                try {
                    String[] parts = line.split(" , ");
                    int type = Integer.parseInt(parts[0]);
                    String idCus = parts[1];
                    String idDog = parts[2];
                    String idEmp = parts[3];
                    double price = Double.parseDouble(parts[4]);
                    String currentTime = parts[5];
                    History history = new History(type, idCus, idDog, idEmp, price, currentTime);
                    if (his == null) {
                        his = new ArrayList<>();
                    }
                    his.add(history);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            //System.out.println("Read History complete!");
            reader.close();
        } catch (Exception e) {
            System.err.println("Can't read History file!");
        }
    }

    public void readCustomer() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataCustomer.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                try {
                    String[] parts = line.split(" , ");
                    String customerID = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String phoneNumber = parts[3];
                    String address = parts[4];
                    Customer dg = new Customer(customerID, name, age, phoneNumber, address);
                    if (cus == null) {
                        cus = new ArrayList<>();
                    }
                    cus.add(dg);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            //System.out.println("Read file Customer complete!");
            reader.close();
        } catch (Exception e) {
            System.err.println("Can't read Customer file!");
        }
    }

    public void readEmployee() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataEmployee.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                try {
                    String[] parts = line.split(" , ");
                    String employeeID = parts[0];
                    double dayNumber = Double.parseDouble(parts[1]);
                    String name = parts[2];
                    int age = Integer.parseInt(parts[3]);
                    String phoneNumber = parts[4];
                    String address = parts[5];

                    Employee dg = new Employee(employeeID, name, age, phoneNumber, address);
                    dg.setDayNumber(dayNumber);
                    if (emp == null) {
                        emp = new ArrayList<>();
                    }
                    emp.add(dg);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            //System.out.println("Read file Employee complete!");
            reader.close();
        } catch (Exception e) {
            System.err.println("Can't read Employee file!");
        }
    }

    public void readFeedback() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dataFeedback.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                try {
                    String[] parts = line.split(" , ");
                    String id = parts[0];
                    String f = parts[1];
                    String r = parts[2];
                    Feedback dg = new Feedback(id, f, r);
                    if (feedback == null) {
                        feedback = new ArrayList<>();
                    }
                    feedback.add(dg);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                }
            }
            //System.out.println("Read file Feedback complete!");
            reader.close();
        } catch (Exception e) {
            System.err.println("Can't read FeedBack file!");
        }
    }
// --------------------------------------- phần lấy dữ liệu danh sách---------------------------------

    public ArrayList<DogForSale> getDogForSale() {
        return dogSale;
    }

    public ArrayList<DogForSend> getDogForSend() {
        return dogSend;
    }

    public ArrayList<Customer> getCustomer() {
        return cus;
    }

    public ArrayList<Employee> getEmployee() {
        return emp;
    }

    public ArrayList<History> getHistory() {
        return his;
    }
//-------------------------------------phan search-------------------------------------------

    public DogForSale searchDogSale(String id, ArrayList<DogForSale> arrDog) {
        for (DogForSale dog : arrDog) {
            if (id.equalsIgnoreCase(dog.getDogID())) {
                return dog;
            }
        }
        return null;
    }

    public DogForSend searchDogSend(String id, ArrayList<DogForSend> arrDog) {
        for (DogForSend dog : arrDog) {
            if (id.equalsIgnoreCase(dog.getDogID())) {
                return dog;
            }
        }
        return null;
    }

    public Customer searchCus(String id, ArrayList<Customer> arrCus) {
        for (Customer cus1 : arrCus) {
            if (id.equalsIgnoreCase(cus1.getCustomerID())) {
                return cus1;
            }
        }
        return null;
    }

    public Employee seachEmp(String id, ArrayList<Employee> arrEmp) {
        for (Employee emp1 : arrEmp) {
            if (id.equalsIgnoreCase(emp1.getEmployeeID())) {
                return emp1;
            }
        }
        return null;
    }

    public History seachHis(String idCus) {
        for (History his1 : this.getHistory()) {
            if (idCus.equalsIgnoreCase(his1.getIdCus())) {
                return his1;
            }
        }
        return null;
    }
//---------------------------check id da ton tai hay chua-------------------

    public boolean checkIdEmp(ArrayList<Employee> arr, String id) {
        return arr.stream().anyMatch((emp1) -> (emp1.getEmployeeID().equalsIgnoreCase(id)));
    }

    public boolean checkIdCus(ArrayList<Customer> arr, String id) {
        return arr.stream().anyMatch((cus1) -> (cus1.getCustomerID().equalsIgnoreCase(id)));
    }

    public boolean checkIdDogSend(ArrayList<DogForSend> arr, String id) {
        return arr.stream().anyMatch((dog) -> (dog.getDogID().equalsIgnoreCase(id)));
    }

    public boolean checkIdDogSale(ArrayList<DogForSale> arr, String id) {
        return arr.stream().anyMatch((dog) -> (dog.getDogID().equalsIgnoreCase(id)));
    }
//-----------------------------------feedback----------------------------------------

    public void sendFeedBack() {
        try {
            String id = JOptionPane.showInputDialog(null, "Enter your ID:");
            if (this.checkIdCus(cus, id)) {
                String feedBack = JOptionPane.showInputDialog(null, "Send your feedback:");
                Feedback fb = new Feedback(id.toUpperCase(), feedBack.toUpperCase());
                feedback.add(fb);
                this.saveFeedBack(feedback);
                JOptionPane.showMessageDialog(null, "Send feedback successful!");
            } else {
                JOptionPane.showMessageDialog(null, "ID Customer does not exist!");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "An error occurred in the sendFeedBack() method.");
        }
    }

    public boolean displayAllFeedback() {
        String[] columnNames = {"ID", "Feedback"};

        Object[][] data = new Object[feedback.size()][2];
        for (int i = 0; i < feedback.size(); i++) {
            Feedback fb = feedback.get(i);
            data[i][0] = fb.getIdCustomer();
            data[i][1] = fb.getFeedBack();
        }

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

        // Đặt font chữ cho dữ liệu trong bảng
        Font font = new Font("Arial", Font.BOLD, 20);
        table.setFont(font);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đặt DefaultTableCellRenderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JFrame frame = new JFrame("All Feedback");
        JScrollPane scrollPane = new JScrollPane(table);

        JButton okButton = new JButton("OK");
        okButton.setFont(font);
        okButton.setBackground(Color.WHITE);
        okButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(1000, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // Đợi cho đến khi frame bị đóng
        try {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    synchronized (frame) {
                        frame.notify();
                    }
                }
            });

            synchronized (frame) {
                frame.wait();
            }
        } catch (InterruptedException ex) {
        }

        return true;
    }

    public void reply() {
        try {
            this.displayAllFeedback();
            String cusID = JOptionPane.showInputDialog("Enter Customer ID to reply to:");
            boolean isCustomerExist = false;

            for (Feedback fb : feedback) {
                if (fb.getIdCustomer().equalsIgnoreCase(cusID)) {
                    String reply = JOptionPane.showInputDialog("Enter your reply:");
                    fb.setReply(reply.toUpperCase());
                    isCustomerExist = true;
                    break;
                }
            }

            if (isCustomerExist) {
                this.saveFeedBack(feedback);
                JOptionPane.showMessageDialog(null, "Reply sent successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Customer does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.err.println("An error occurred in the reply() method.");
        }
    }

    public boolean displayReply() {
        try {
            String idCuss = JOptionPane.showInputDialog("Enter your ID:");
            String[] columnNames = {"ID Customer", "FeedBack", "Reply"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);
            table.setRowHeight(30); // Đặt chiều cao của hàng trong bảng để có thể set font

            // Đặt font chữ cho dữ liệu trong bảng
            Font font = new Font("Arial", Font.BOLD, 20);
            table.setFont(font);

            JScrollPane scrollPane = new JScrollPane(table);

            feedback.stream()
                    .filter(fb -> fb.getIdCustomer().equalsIgnoreCase(idCuss))
                    .forEach(feedback1 -> {
                        model.addRow(new Object[]{feedback1.getIdCustomer(), feedback1.getFeedBack(), feedback1.getReply()});
                    });

            JFrame frame = new JFrame("Reply");

            JButton ok = new JButton("OK");
            ok.setFont(font);
            ok.setBackground(Color.white);
            ok.addActionListener((ActionEvent e) -> {
                frame.dispose();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(ok);

            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setSize(800, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // Đợi cho đến khi frame bị đóng
            try {
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        synchronized (frame) {
                            frame.notify();
                        }
                    }
                });

                synchronized (frame) {
                    frame.wait();
                }
            } catch (InterruptedException ex) {
            }
        } catch (HeadlessException e) {
            System.err.println("An error occurred in the displayReply() method.");
        }
        return true;
    }

//------------------------------------ trả lương --------------------------------
    public void paySalary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%5s %20s %20s \n", "ID", "NAME", "Salary"));

        emp.forEach(employee -> {
            sb.append(String.format("%-18s %-18s %-20s\n", employee.getEmployeeID(), employee.getName(), employee.getSalary()));
        });

        JOptionPane.showMessageDialog(null, sb.toString(), "Pay Salary", JOptionPane.INFORMATION_MESSAGE);
    }

//-----------------------------------hiển thị tiền----------------------------------
    public void moneyFromHis() {
        try {
            double tongTien = 0;
            tongTien = this.getHistory().stream().map((temp) -> temp.getPrice()).reduce(tongTien, (accumulator, _item) -> accumulator + _item);
            double tienBanCho = 0;
            tienBanCho = his.stream().filter((history) -> (history.getType().equalsIgnoreCase("SALE DOG"))).map((history) -> history.getPrice()).reduce(tienBanCho, (accumulator, _item) -> accumulator + _item);

            double tienGuiCho = 0;
            tienGuiCho = his.stream().filter((history) -> (history.getType().equalsIgnoreCase("SEND DOG") || history.getType().equalsIgnoreCase("PICK UP"))).map((history) -> history.getPrice()).reduce(tienGuiCho, (accumulator, _item) -> accumulator + _item);

            JOptionPane.showMessageDialog(null, "Tien Ban Cho: " + tienBanCho + "\nTien Giu Cho: " + tienGuiCho + "\nAll money: " + tongTien);
        } catch (Exception e) {
            System.err.println("co loi phan moneyFromHis()");
        }
    }
}
