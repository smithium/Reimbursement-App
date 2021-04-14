package com.example.Project1_2;

import beans.Reimbursement;
import beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ReimbursementDao;
import dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminReimbursementController {
    public static String getReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {

        ReimbursementDao rdao = new ReimbursementDao();
        UserDao udao = new UserDao();


        List<Reimbursement> rlist = rdao.getAllReimbursements();

        res.getWriter().write(new ObjectMapper().writeValueAsString(rlist));
        System.out.println(rlist);
        return "";
    }

    public static String ApproveReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        ReimbursementDao rdao = new ReimbursementDao();
        rdao.ApproveReimbursement(id);

        PrintWriter out = res.getWriter();
        out.println("<html><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Adding Reimbursement</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/Project1_2_war_exploded/css/newReimb.css\">\n" +
                "</head><body>");
        out.println("<h3>You have approved Reimbursement ID: " + id + "</h3>");
        out.println("<a href=\"ViewAllReimbursements.html\">Go Back</a>");
        out.println("</body></html>");


        return "";
    }

    public static String DenyReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        ReimbursementDao rdao = new ReimbursementDao();
        rdao.DenyReimbursement(id);

        PrintWriter out = res.getWriter();
        out.println("<html><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Adding Reimbursement</title>\n" +
                "    <link rel=\"stylesheet\" href=\"/Project1_2_war_exploded/css/newReimb.css\">\n" +
                "</head><body>");
        out.println("<h3>You have denied Reimbursement ID: " + id + "</h3>");
        out.println("<a href=\"ViewAllReimbursements.html\">Go Back</a>");
        out.println("</body></html>");


        return "";
    }

}
