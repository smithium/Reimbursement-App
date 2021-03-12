package services;

import beans.Reimbursement;
import beans.User;
import dao.ReimbursementDao;
import dao.UserDao;

import java.sql.Timestamp;

public class ReimbursementServices {

    public Reimbursement postReimbursement(String username,String password, int amount, Reimbursement.ReimbursementType type, String description){
        UserDao udao = new UserDao();

        Reimbursement newRreimb = new Reimbursement();
        int randomID = (int)(Math.random() * 10000 + 1);
        newRreimb.setID(randomID);
        newRreimb.setType(type);
        newRreimb.setStatus(Reimbursement.ReimbursementStatus.OPEN);
        newRreimb.setAuthor(udao.getUserByLogginInfo(username,password));
        newRreimb.setDescription(description);
        newRreimb.setSubmitted(new Timestamp(System.currentTimeMillis()));
        newRreimb.setAmount(amount);

        ReimbursementDao rdao = new ReimbursementDao();
        rdao.insertReimbursement(newRreimb);

        return newRreimb;
    }


}
