package OopsPrac;

import java.util.HashSet;

public class HospitalPatientBillingEngine {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("------------");
        System.out.println("Hospital Patient Bill Engine:");

        try {

            PatientAccount p1 = new generalpatient("abc", "1", 1232);

            p1.generatebill();
            p1.generatebill("234");
            p1.generateinvoice();

            PatientAccount p2 = new insuredpatient("asbhda", "2", 100000);

            p2.generatebill();
            p2.generatebill("234");
            p2.generateinvoice();

        }

        catch (InvalidBillException e) {
            System.out.println(e.getMessage());
        }

        catch (PaymentNotAllowedException e) {
            System.out.println(e.getMessage());
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------");
        System.out.println();

    }

}

interface billingmethods {

    void generatebill() throws InvalidBillException; // cash default mode

    void generatebill(String insurancenumber) throws InvalidBillException; // method overloading

    void generateinvoice();

}

abstract class PatientAccount implements billingmethods {
    private String patientname;
    private String patientid;

    PatientAccount(String patientname, String patientid) throws InvalidBillException {
        setpatientid(patientid);
        setpatientname(patientname);
    }

    static HashSet<String> ids = new HashSet<>();

    protected void setpatientid(String patientid) {
        if (patientid == null || patientid.isEmpty()) {
            throw new IllegalArgumentException("invalid patient id...");
        }

        else if (ids.contains(patientid)) {
            throw new IllegalArgumentException("duplicate id...");
        }

        else {
            this.patientid = patientid;
            ids.add(patientid);
        }
    }

    protected void setpatientname(String patientname) {
        if (patientname == null || patientname.isEmpty()) {
            throw new IllegalArgumentException("invalid patient name...");
        }

        else {
            this.patientname = patientname;
        }
    }

    protected String getpatientid() {
        return this.patientid;
    }

    protected String getpatientname() {
        return this.patientname;
    }

    // abstract methods explicitly declared
    public abstract void generatebill() throws InvalidBillException;

    public abstract void generatebill(String insurancenumber) throws InvalidBillException;

    // abstract class implementation from the interface
    @Override
    public void generateinvoice() {
        System.out.println("invoice generated...");
    };

}

class generalpatient extends PatientAccount {

    private long billamount;

    generalpatient(String patientname, String patientid, long billamount) throws InvalidBillException {
        super(patientname, patientid);
        setbillamount(billamount);
    }

    protected void setbillamount(long billamount) throws InvalidBillException {
        if (billamount <= 0) {
            throw new InvalidBillException("invalid bill amount...");
        } else {
            this.billamount = billamount;
        }
    }

    @Override
    public void generatebill() throws InvalidBillException {

        if (billamount <= 0) {
            throw new InvalidBillException("invalid bill amount...");
        }

        else {
            System.out.println("bill paid:" + billamount);
            System.out.println("mode:cash");
            System.out.println();
        }
    }

    @Override
    public void generatebill(String insurancenumber) throws InvalidBillException {

        throw new PaymentNotAllowedException("general patient don't have insurance...");

    }

    @Override
    public void generateinvoice() {
        System.out.println();
        System.out.println("general patient:");
        System.out.println("bill amount:" + billamount);
        System.out.println("mode:cash");
        System.out.println();
    }

}

class insuredpatient extends PatientAccount {

    private long billamount;

    insuredpatient(String patientname, String patientid, long billamount) throws InvalidBillException {
        super(patientname, patientid);
        setbillamount(billamount);
    }

    protected void setbillamount(long billamount) throws InvalidBillException {
        if (billamount <= 0) {
            throw new InvalidBillException("invalid bill amount...");
        } else {
            this.billamount = billamount;
        }
    }

    @Override
    public void generatebill() throws InvalidBillException {
        throw new PaymentNotAllowedException("insurance is available...");
    }

    @Override
    public void generatebill(String insurancenumber) throws InvalidBillException {
        if (billamount <= 0) {
            throw new InvalidBillException("invalid bill amount...");
        }

        else if (insurancenumber == null || insurancenumber.isEmpty()) {
            throw new PaymentNotAllowedException("inavalid insurance number...");
        }

        else {
            System.out.println("bill amount:" + billamount);
            System.out.println("mode:insurance");
            System.out.println("insurance number:" + insurancenumber);
            System.out.println();
        }
    }

    @Override
    public void generateinvoice() {
        System.out.println();
        System.out.println("insured patient:");
        System.out.println("bill:" + billamount);
        System.out.println("mode:insurance");
        System.out.println();
    }

}

class InvalidBillException extends Exception {

    InvalidBillException(String message) {
        super(message);
    }

}

class PaymentNotAllowedException extends RuntimeException {

    PaymentNotAllowedException(String message) {
        super(message);
    }

}