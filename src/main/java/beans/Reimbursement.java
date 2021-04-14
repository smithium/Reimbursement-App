package beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {
    private int ID;
    private int Amount;
    private Timestamp Submitted;
    private Timestamp Resolved;
    private String Description;
    private User author;
    private User resolver;
    private ReimbursementStatus status;
    private ReimbursementType type;

    public static enum ReimbursementStatus {
        OPEN, RESOLVED, APPROVED, DENIED
    }
    public static enum ReimbursementType {
        LODGING, TRAVEL,FOOD,OTHER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return getID() == that.getID() && getAmount() == that.getAmount() && Objects.equals(getSubmitted(), that.getSubmitted()) && Objects.equals(getResolved(), that.getResolved()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getResolver(), that.getResolver()) && getStatus() == that.getStatus() && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getAmount(), getSubmitted(), getResolved(), getDescription(), getAuthor(), getResolver(), getStatus(), getType());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Timestamp getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        Submitted = submitted;
    }

    public Timestamp getResolved() {
        return Resolved;
    }

    public void setResolved(Timestamp resolved) {
        Resolved = resolved;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "ID=" + ID +
                ", Amount=" + Amount +
                ", Submitted=" + Submitted +
                ", Resolved=" + Resolved +
                ", Description='" + Description + '\'' +
                ", author=" + author.getUserName() +
                ", resolver=" + resolver.getUserName() +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    public Reimbursement() {
        super();
    }

    public Reimbursement(int ID, int amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
        this.ID = ID;
        Amount = amount;
        Submitted = submitted;
        Resolved = resolved;
        Description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }
}
