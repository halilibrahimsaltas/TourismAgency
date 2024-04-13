import core.Db;
import core.Helper;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        Db conn = new Db();

        LoginView loginView = new LoginView();



    }
}