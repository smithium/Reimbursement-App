package Servlets;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.Project1_2.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONRequestHelper {
    public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException,
            IOException{
        System.out.println(req.getRequestURI());
        switch(req.getRequestURI()) {
            case "/Project1_2_war_exploded/userinfo.json":
                return GetUserInfoController.getUserInfo(req,res);

            case "/Project1_2_war_exploded/getReimbursements.json":
                return ReimbursementController.getReimbursements(req,res);

            case "/Project1_2_war_exploded/newReimb.json":
                return NewReimbController.addReimbursement(req,res);
            case "/Project1_2_war_exploded/AdminReimb.json":
                return AdminReimbursementController.getReimbursements(req,res);
            case "/Project1_2_war_exploded/Approve.json":
                return AdminReimbursementController.ApproveReimbursement(req,res);
            case "/Project1_2_war_exploded/Deny.json":
                return AdminReimbursementController.DenyReimbursement(req,res);
            default:

            return "bad.html";
        }

    }
}
