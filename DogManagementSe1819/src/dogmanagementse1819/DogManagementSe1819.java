package dogmanagementse1819;

import login.Account;
import login.AccountManagement;
import menu.Menu;
import tool.MyTool;

public class DogManagementSe1819 {

    private static MyTool tool = new MyTool();

    public static void main(String[] args) {

//------------------------------xử lý phần đăng nhập--------------------------------
        boolean a = true;
        String choice;
        AccountManagement acc = new AccountManagement();
        while (a) {
            acc.a();
            Account accLogin = acc.getAccountLogin();
            Menu mn = new Menu();
            if (accLogin != null) {
                switch (accLogin.getCodeUser()) {
                    case "01":
                        mn.menuManager();
                        break;
                    case "02":
                        mn.menuEmployee();
                        break;
                    case "03":
                        mn.menuCustomer();
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Do you want to continue ? (y/n)");
            choice = tool.iString();
            if (choice.equalsIgnoreCase("n")) {
                a = false;
            }
        }
    }

}
