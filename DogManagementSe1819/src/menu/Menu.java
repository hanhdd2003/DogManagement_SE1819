package menu;

import data.Sevice.Service;
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
            System.out.println("+------------------MENU-----------------------+");
            System.out.printf("| %-20s | %-20s |\n", "1. Search Dog.", "2. Buy Dog.");
            System.out.printf("| %-20s | %-20s |\n", "3. Send Dog.", "4. Pick Up.");
            System.out.printf("| %-20s | %-20s |\n", "5. Send Feedback.", "6. Get Reply");
            System.out.printf("| %-20s |\n", "0. Exit");
            System.out.println("+---------------------------------------------+\n");
            System.out.print("Your choice: ");
            b = tool.iInt();
            switch (b) {
                case 1:
                    sv.searchDogSaleToBuy();
                    break;
                case 2:
                    sv.saleDog();
                    break;
                case 3:
                    sv.sendDog();
                    break;
                case 4:
                    sv.pickUpDog();
                    break;
                case 5:
                    sv.sendFeedBack();
                    break;
                case 6:
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
            System.out.println("+------------------------------MENU-------------------------------+");
            System.out.printf("| %-30s | %-30s |\n","1. Sale Dog.","6. List History.");
            System.out.printf("| %-30s | %-30s |\n","2. Received Dog.","7. Fix infomation of dog send.");
            System.out.printf("| %-30s | %-30s |\n","3. List Dog Sale.","8. Fix infomation of Customer.");
            System.out.printf("| %-30s | %-30s |\n","4. List Dog Send.","9. Support Customer");
            System.out.printf("| %-30s | %-30s |\n","5. List Customer.","0. Exit");
            System.out.println("+-----------------------------------------------------------------+\n");

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
                    sv.displayHistory();
                    break;
                case 7:
                    sv.fixInfoDogSend();
                    break;
                case 8:
                    sv.fixInfoCus();
                    break;
                case 9:
                    sv.reply();
                    break;
                default:
                    break;
            }
        }
    }

    public void menuManager() { // 01 là quản lý
        while (choice) {
            System.out.println("+--------------------------------MENU----------------------------------+");
            System.out.printf("| %-29s |  %-35s |\n", "1. List Dog Sale.", "9. Remove Employee.");
            System.out.printf("| %-29s |  %-35s |\n", "2. List Dog Send.", "10. Fix infomation of dog sale.");
            System.out.printf("| %-29s |  %-35s |\n", "3. List Customer.", "11. Fix infomation of Employee.");
            System.out.printf("| %-29s |  %-35s |\n", "4. List Employee.", "12. Reply Feedback. ");
            System.out.printf("| %-29s |  %-35s |\n", "5. List History.", "13. Set Day For Employee");
            System.out.printf("| %-29s |  %-35s |\n", "6. Add Dog Sale.", "14. Pay salary");
            System.out.printf("| %-29s |  %-35s |\n", "7. Add Employee.", "15. Statics");
            System.out.printf("| %-29s |  %-35s |\n", "8. Remove Dog Sale.", "0. Exit");
            System.out.println("+----------------------------------------------------------------------+\n");

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
                    sv.displayHistory();
                    break;
                case 6:
                    sv.inputDogSale();
                    break;
                case 7:
                    sv.inputEmployee();
                    break;
                case 8:
                    sv.removeDogSale();
                    break;
                case 9:
                    sv.removeEmployee();
                    break;
                case 10:
                    sv.fixInfoDogSale();
                    break;
                case 11:
                    sv.fixInfoEmp();
                    break;
                case 12:
                    sv.reply();
                    break;
                case 13:
                    sv.setDayForEmployee();
                    break;
                case 14:
                    sv.paySalary();
                    break;
                case 15:
                    sv.moneyFromHis();
                    break;
                default:
                    break;
            }
        }
    }

}
