package dogmanagementse1819;

import javax.swing.JOptionPane;
import login.Account;
import login.AccountManagement;
import menu.Menu;
import tool.MyTool;

public class DogManagementSe1819 {

    private static MyTool tool = new MyTool();

    public static void main(String[] args) {
        //AccountManagement.a();

//------------------------------xử lý phần đăng nhập--------------------------------
        boolean a = true;
        while (a) {
            AccountManagement.a();
            Account accLogin = AccountManagement.getAccountLogin();
            Menu mn = new Menu();
            if (accLogin != null) {
                switch (accLogin.getCodeUser()) {
                    case "01":
                        mn.menuManagerGUI();
                        break;
                    case "02":
                        mn.menuEmployeeGUI();
                        break;
                    case "03":
                        mn.menuCustomerGUI();
                        break;
                    default:
                        break;
                }
            }
            int result = JOptionPane.showConfirmDialog(null, "Do you want to continue ? (y/n)", "Xác Nhận", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) {
                a = false;
            }
        }
    }
}
