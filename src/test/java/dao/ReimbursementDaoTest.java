package dao;

import beans.Reimbursement;
import beans.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDaoTest {

    public static Reimbursement testre;
    public static Reimbursement testre2;
    public static List<Reimbursement> reList;
    @BeforeAll
    public static void setup(){

        User testu1 = new User();
        testu1.setUserRoleID(User.UserType.EMPLOYEE);
        testu1.setUserId(101);
        testu1.setUserPassword("password");
        testu1.setUserName("testu1");

        User testu2 = new User();
        testu2.setUserRoleID(User.UserType.ADMIN);
        testu2.setUserId(102);
        testu2.setUserPassword("password");
        testu2.setUserName("testu2");

        User testu3 = new User();
        testu3.setUserRoleID(User.UserType.EMPLOYEE);
        testu3.setUserId(103);
        testu3.setUserPassword("password");
        testu3.setUserName("testu3");

        UserDao udao = new UserDao();

        udao.insertUser(testu1);
        udao.insertUser(testu2);
        udao.insertUser(testu3);

        ReimbursementDao rdao = new ReimbursementDao();

        testre = new Reimbursement();
        testre.setID(101);
        testre.setAmount(20);
        testre.setSubmitted(new Timestamp(System.currentTimeMillis()));
        testre.setResolved(new Timestamp(System.currentTimeMillis()));
        testre.setAuthor(testu1);
        testre.setResolver(testu2);
        testre.setDescription("testing");
        testre.setStatus(Reimbursement.ReimbursementStatus.OPEN);
        testre.setType(Reimbursement.ReimbursementType.OTHER);

        testre2 = new Reimbursement();
        testre2.setID(102);
        testre2.setAmount(20);
        testre2.setSubmitted(new Timestamp(System.currentTimeMillis()));
        testre2.setResolved(new Timestamp(System.currentTimeMillis()));
        testre2.setAuthor(testu3);
        testre2.setResolver(testu2);
        testre2.setDescription("testing");
        testre2.setStatus(Reimbursement.ReimbursementStatus.OPEN);
        testre2.setType(Reimbursement.ReimbursementType.OTHER);

        rdao.insertReimbursement(testre2);

        reList = new LinkedList<>();

        reList.add(testre2);
        //reList.add(testre);

        System.out.println(reList);



    }

    @Test
    void getAndInsertReimbursement() {
        ReimbursementDao  rdao = new ReimbursementDao();
        rdao.insertReimbursement(testre);
        assertEquals(testre.toString(),rdao.getReimbursement(testre.getID()).toString());
    }
    @Test
    void getReimbursementByUser() {
        ReimbursementDao  rdao = new ReimbursementDao();
        assertEquals(testre.toString(),rdao.getReimbursementByUser(testre.getAuthor()).get(0).toString());
    }

    @Test
    void denyReimbursement() {
        ReimbursementDao  rdao = new ReimbursementDao();
        rdao.DenyReimbursement(testre2.getID());
        testre2.setStatus(Reimbursement.ReimbursementStatus.DENIED);

        System.out.println(rdao.getReimbursement(testre2.getID()));
        assertEquals(testre2.getStatus(),rdao.getReimbursement(testre2.getID()).getStatus());
    }

    @Test
    void approveReimbursement() {
        ReimbursementDao  rdao = new ReimbursementDao();
        rdao.ApproveReimbursement(testre.getID());
        testre.setStatus(Reimbursement.ReimbursementStatus.APPROVED);

        assertEquals(testre.getStatus(),rdao.getReimbursement(testre.getID()).getStatus());

    }



    @Test
    void getAllReimbursements() {
        ReimbursementDao  rdao = new ReimbursementDao();

        assertEquals(rdao.getAllReimbursements().toString(),reList.toString());
    }


}
