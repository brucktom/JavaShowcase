package Serialization_Preview;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    //second Version, added the String newFeature
    private long currentVersionID = 2L;

    Integer employeeId;
    String department;
    BigDecimal salaryPerYear;
    EmployeeStatus status;

    String newFeature = "";

    public void setOldVersion() {
        this.currentVersionID = 1;
    }

    public void writeObject(ObjectOutputStream o) throws Exception{
        
        //always write currentVersionID first
        o.writeLong(currentVersionID);
        o.writeUTF(newFeature);
        o.writeInt(employeeId);
        o.writeUTF(department);
        //use writeObject for stuff other than int, string,...
        o.writeObject(salaryPerYear);
        o.writeObject(status);

    }

    public void readObject(ObjectInputStream o) throws Exception {

        long versionNr = o.readLong();

        //if the read Object is pre-Version2
        if(versionNr < 2) {
            this.newFeature = "Not available for this version";
        } else {
            newFeature = o.readUTF();
        }
    }

    public void initPrintPerson() {
        this.firstnames = new String[] {"Hans", "Joachim"};
        this.lastname = "Müller";
        System.out.println("Name: " + lastname);
        this.dateOfBirth = new Date(23, 12, 22);
        System.out.println("Geburtstag : " + dateOfBirth);
        this.eyeColor = Color.BLACK;
        this.currentAddress = new Address("Lilienalle", "23444", "Dortmund", "Deutschland");
        this.formerAddresses = null;
        this.employeeId = 1;
        this.socialSecurityNumber = "233122";
        this.salaryPerYear = BigDecimal.valueOf(300_000_000);
        this.department = "Geschäftsführer";
        this.status = EmployeeStatus.MANAGING_DIRECTOR;
        System.out.println("________________");
    }


}
