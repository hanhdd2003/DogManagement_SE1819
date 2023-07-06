package dogmanagementse1819;

import data.Sevice.Service;
import login.Account;
import login.AccountManagement;
import menu.Menu;

public class DogManagementSe1819 {

    public static void main(String[] args) {

      
        
        
        
//------------------------------xử lý phần đăng nhập--------------------------------
       AccountManagement.a();
        Account accLogin = AccountManagement.getAccountLogin();
        Menu mn = new Menu();
        if (accLogin != null) {
            if (accLogin.getCodeUser().equals("01")) {
                mn.menuManager();
            } else if (accLogin.getCodeUser().equals("02")) {
                mn.menuEmployee();
            } else if (accLogin.getCodeUser().equals("03")) {
                mn.menuCustomer();
            }
        }

    }

}
