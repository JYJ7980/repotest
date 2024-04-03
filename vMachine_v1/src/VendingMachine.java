import init.InitClass;
import view.UserView;

import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) {
        UserView userView = new UserView();
        InitClass.initialize();
        userView.userView();

    }
}
