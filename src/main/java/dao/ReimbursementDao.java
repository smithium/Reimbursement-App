package dao;
import java.sql.*;
import beans.User;
import beans.Reimbursement;

public class ReimbursementDao {

    private String url = "jdbc:postgresql://project1db.c8vjwn4ozwqs.us-east-2.rds.amazonaws.com:5432/companydb";
    private String username = "dbadmin";
    private String password = "password";

    public Reimbursement getReimbursement(int ID){

        Reimbursement returnReimb = new Reimbursement();
        UserDao udao = new UserDao();

        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            String sql = "select * from ers_reimbursement where reimb_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,ID);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                returnReimb.setID(ID);
                returnReimb.setAmount(rs.getInt("reimb_amount"));
                returnReimb.setSubmitted(rs.getTimestamp("reimb_submitted"));
                returnReimb.setResolved(rs.getTimestamp("reimb_resolved"));
                returnReimb.setDescription(rs.getString("reimb_description"));
                returnReimb.setAuthor(udao.getUser(rs.getInt("reimb_author")));
                returnReimb.setResolver(udao.getUser(rs.getInt("reimb_resolver")));

                int status = rs.getInt("reimb_status_id");
                switch (status){
                    case 1 :
                        returnReimb.setStatus(Reimbursement.ReimbursementStatus.OPEN);
                        break;
                    case 2 :
                        returnReimb.setStatus(Reimbursement.ReimbursementStatus.RESOLVED);
                        break;

                }
                int type = rs.getInt("reimb_type_id");

                switch (type){
                    case 1:
                        returnReimb.setType(Reimbursement.ReimbursementType.LODGING);
                        break;
                    case 2:
                        returnReimb.setType(Reimbursement.ReimbursementType.TRAVEL);
                        break;
                    case 3:
                        returnReimb.setType(Reimbursement.ReimbursementType.FOOD);
                        break;
                    case 4:
                        returnReimb.setType(Reimbursement.ReimbursementType.OTHER);
                        break;
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return returnReimb;
    }

    public void insertReimbursement(Reimbursement r){
        try{
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement ps = conn.prepareStatement("insert into ers_reimbursement values (?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, r.getID());
            ps.setInt(2,r.getAmount());
            ps.setTimestamp(3,r.getSubmitted());
            ps.setTimestamp(4,r.getResolved());
            ps.setString(5,r.getDescription());
            ps.setInt(6,r.getAuthor().getUserId());
            ps.setInt(7,r.getResolver().getUserId());

            switch (r.getStatus()){
                case OPEN:
                    ps.setInt(8,1);
                    break;
                case RESOLVED:
                    ps.setInt(8,2);
                    break;
            }
            switch (r.getType()){
                case LODGING:
                    ps.setInt(9,1);
                case TRAVEL:
                    ps.setInt(9,2);
                case FOOD:
                    ps.setInt(9,3);
                case OTHER:
                    ps.setInt(9,4);
            }
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }




}
