import business.UserManager;
import core.Db;
import core.Helper;
import view.AdminView;
import view.LoginView;
import core.ComboItem;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();


        UserManager userManager = new UserManager();
        AdminView adminView = new AdminView(userManager.findByLogin("hasan","1234"));


    }
}