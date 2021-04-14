package Servlets;

import com.example.Project1_2.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHelper {
    public static String process(HttpServletRequest req) throws IOException {
        System.out.println(req.getRequestURI());
        switch(req.getRequestURI()) {
            case "/Project1_2_war_exploded/login.change":
                System.out.println("In login.change rhelper");
                return LoginController.login(req);
            case "/Project1_2_war_exploded/home.change":
                System.out.println("In home.change rhelper");
                return HomeController.home(req);
            case "/Project1_2_war_exploded/html/home.change":
                return HomeController.home(req);
            case "/Project1_2_war_exploded/logout.change":
                return LogOutController.logout(req);
            case "/Project1_2_war_exploded/html/logout.change":
                return LogOutController.logout(req);

            default:
                System.out.println("In default");
                return "bad.html";
        }

    }
}
