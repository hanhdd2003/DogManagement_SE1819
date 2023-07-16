package menu;

import data.Sevice.Service;
import javax.swing.JOptionPane;
import tool.MyTool;

public class Menu {

    private MyTool tool = new MyTool();
    private Service sv = new Service();
    private boolean choice = true;
    private int b;

    public Menu() {
        sv.loadData();
    }

    public void menuCustomer() {
        while (choice) {
            //---------------------------------bán chó--------------------------------           
            System.out.println("MENU");
            System.out.println("1. Buy Dog.");
            System.out.println("2. Send Dog.");
            System.out.println("3. Received Dog.");
            System.out.println("4. Send Feedback.");
            System.out.println("5. Get reply.");
            System.out.println("0. Exit.");

            System.out.print("Your choice: ");
            b = tool.iInt();
            switch (b) {
                case 1:
                    sv.saleDog();
                    break;
                case 2:
                    sv.sendDog();
                    break;
                case 3:
                    sv.pickUpDog();
                    break;
                case 4:
                    sv.sendFeedBack();
                    break;
                case 5:
                    sv.displayReply();
                    break;
                case 0:
                    choice = false;
                    break;
            }
        }
    }

    public void menuEmployee() {   //02 là nhân viên
        while (choice) {
            System.out.println("MENU-----------------------");
            System.out.println("1. Sale Dog.");
            System.out.println("2. Received Dog.");
            System.out.println("3. Check Dog Sale.");
            System.out.println("4. Check Dog Send.");
            System.out.println("5. Check Customer.");
            System.out.println("6. Support Customer.");
            System.out.println("7. Fix infomation of dog sale.");
            System.out.println("8. Fix infomation of dog send.");
            System.out.println("9. Fix infomation of Customer.");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            b = tool.iInt();
            switch (b) {
                case 0:
                    choice = false;
                    break;
                case 1:
                    sv.saleDog();
                    break;
                case 2:
                    sv.pickUpDog();
                    break;
                case 3:
                    sv.displayDogSale();
                    break;
                case 4:
                    sv.displayDogSend();
                    break;
                case 5:
                    sv.displayCus();
                    break;
                case 6:
                    sv.reply();
                    break;
                case 7:
                    sv.fixInfoDogSale();
                    break;
                case 8:
                    sv.fixInfoDogSend();
                    break;
                case 9:
                    sv.fixInfoCus();
                    break;
            }
        }
    }

    public void menuManager() { // 01 là quản lý
        while (choice) {
            System.out.println("MENU-----------------------");
            System.out.println("1. Check Dog Sale.");
            System.out.println("2. Check Dog Send.");
            System.out.println("3. Check Customer.");
            System.out.println("4. Check Employee.");
            System.out.println("5. Add Dog Sale.");
            System.out.println("6. Remove Dog Sale.");
            System.out.println("7. Add Employee.");
            System.out.println("8. Remove Employee.");
            System.out.println("9. Pay Salery.");
            System.out.println("10. Statistical.");
            System.out.println("11. Reply Feedback.");
            System.out.println("12. Check history. ");
            System.out.println("0. Exit");

            System.out.print("Your choice: ");
            b = tool.iInt();
            switch (b) {
                case 0:
                    choice = false;
                    break;
                case 1:
                    sv.displayDogSale();
                    break;
                case 2:
                    sv.displayDogSend();
                    break;
                case 3:
                    sv.displayCus();
                    break;
                case 4:
                    sv.displayEmp();
                    break;
                case 5:
                    sv.inputDogSale();
                    break;
                case 6:
                    sv.removeDogSale();
                    break;
                case 7:
                    sv.inputEmployee();
                    break;
                case 8:
                    sv.removeEmployee();
                    break;
                case 9:

                case 10:
                    sv.paySalary();
                    break;
                case 11:
                    sv.reply();
                    break;
                case 12:
                    sv.displayHistory();
                    break;
            }
        }
    }
//-----------------------------gui----------------------------------

    public void menuCustomerGUI() {
        boolean a = true;
        while (a) {
            String input = JOptionPane.showInputDialog(null, "1. Buy Dog.\n"
                    + "2. Send Dog.\n" + "3. Received Dog.\n" + "4. Send Feedback.\n"
                    + "5. Get reply.\n" + "0. Exit.\n");
            if (input == null) {
                a = false;
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        sv.saleDog();
                        break;
                    case 2:
                        sv.sendDog();
                        break;
                    case 3:
                        sv.pickUpDog();
                        break;
                    case 4:
                        sv.sendFeedBack();
                        break;
                    case 5:
                        sv.displayReply();
                        
                        break;
                    case 0:
                        a = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice!");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Not a number choice");
            }
        }
    }

    public void menuEmployeeGUI() {
        boolean a = true;
        while (a) {
            String input = JOptionPane.showInputDialog(null, "1. Sale Dog.\n" + "2. Received Dog.\n"
                    + "3. Check Dog Sale.\n" + "4. Check Dog Send.\n" + "5. Check Customer.\n"
                    + "6. Support Customer.\n" + "7. Fix infomation of dog sale.\n"
                    + "8. Fix infomation of dog send.\n" + "9. Fix infomation of Customer.\n" + "0. Exit\n");
            if (input == null) {
                a = false;
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        a = false;
                        break;
                    case 1:
                        sv.saleDog();
                        break;
                    case 2:
                        sv.pickUpDog();
                        break;
                    case 3:
                        sv.displayDogSale();
                        break;
                    case 4:
                        sv.displayDogSend();
                        break;
                    case 5:
                        sv.displayCus();
                        break;
                    case 6:
                        sv.reply();
                        break;
                    case 7:
                        sv.fixInfoDogSale();
                        break;
                    case 8:
                        sv.fixInfoDogSend();
                        break;
                    case 9:
                        sv.fixInfoCus();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice!");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Not a number choice");
            }
        }
    }

    public void menuManagerGUI() {
        boolean a = true;
        while (a) {
            String input = JOptionPane.showInputDialog(null, "1. Check Dog Sale.\n"
                    + "2. Check Dog Send.\n"
                    + "3. Check Customer.\n"
                    + "4. Check Employee.\n"
                    + "5. Add Dog Sale.\n"
                    + "6. Remove Dog Sale.\n"
                    + "7. Add Employee.\n"
                    + "8. Remove Employee.\n"
                    + "9. Pay Salery.\n"
                    + "10. Statistical.\n"
                    + "11. Reply Feedback.\n"
                    + "12. Check history.\n"
                    + "0. Exit.\n");
            if (input == null) {
                a = false;
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        a = false;
                        break;
                    case 1:
                        sv.displayDogSale();
                        break;
                    case 2:
                        sv.displayDogSend();
                        break;
                    case 3:
                        sv.displayCus();
                        break;
                    case 4:
                        sv.displayEmp();
                        break;
                    case 5:
                        sv.inputDogSale();
                        break;
                    case 6:
                        sv.removeDogSale();
                        break;
                    case 7:
                        sv.inputEmployee();
                        break;
                    case 8:
                        sv.removeEmployee();
                        break;
                    case 9:
                        sv.paySalary();
                        break;
                    case 10:
                        sv.moneyFromHis();
                        break;
                    case 11:
                        sv.reply();
                        break;
                    case 12:
                        sv.displayHistory();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice!");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Not a number choice");
            }
        }
    }
}
