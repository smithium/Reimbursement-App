package com.example.Project1_2;

import Driver.main;
import beans.Reimbursement;
import org.apache.log4j.Level;
import services.ReimbursementServices;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NewReimbController {

    public static String addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Integer type = Integer.parseInt(req.getParameter("type"));
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        String description = req.getParameter("description");

        String username = req.getSession().getAttribute("Username").toString();
        String password = req.getSession().getAttribute("Password").toString();

        System.out.println(type+amount+description+username+password);
        System.out.println(type);


        Reimbursement.ReimbursementType rType = null;

        switch (type){
            case 1:
                rType = Reimbursement.ReimbursementType.LODGING;
                break;
            case 2:
                System.out.println("in here");
                rType = Reimbursement.ReimbursementType.TRAVEL;
                break;
            case 3:
                rType = Reimbursement.ReimbursementType.FOOD;
                break;
            case 4:
                rType = Reimbursement.ReimbursementType.OTHER;
                break;
        }

        System.out.println(rType);

        String log = new String("user " + username + " created a reimburstment");
        main.logger.setLevel(Level.ALL);
        main.logger.info(log);

        ReimbursementServices r = new ReimbursementServices();
        r.postReimbursement(username,password,amount,rType,description);

        PrintWriter out = res.getWriter();
        out.println("<html><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Adding Reimbursement</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/Project1_2_war_exploded/css/newReimb.css\">\n" +
                "</head><body>");
        out.println("<h3>you have added the Reimbursement Request!</h3>");
        out.println("<a href=\"ViewReimbursements.html\">Go Back</a>");
        out.println("</body></html>");
        return HomeController.home(req);
    }
}
