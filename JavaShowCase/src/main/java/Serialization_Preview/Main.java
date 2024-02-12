package Serialization_Preview;

import java.io.*;

public class Main {
    /*
    This project uses Serialization, File Input/Output to create personalized serializable class in order to
    reduce memory. Another topic is version control: version1 is not able to perform actions on features
    that were created in version 2.
     */
    public static void main(String[] args) throws Exception {

        Employee employee1 = new Employee();
        employee1.initPrintPerson();

        FileOutputStream fileOut = new FileOutputStream("Employee.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(employee1);
        FileInputStream fileIn = new FileInputStream("Employee.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Employee employee2 = (Employee) in.readObject();
        System.out.println(employee2);

        System.out.println("__________________");
        System.out.println("Personalisiertes Serializable im Vergleich zu default:");


        //default
        System.out.println("Default:");
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(employee1);
        System.out.println("Default Size: " + byteOut.size());
        //personalized (value is lower-> safes space)
        ByteArrayOutputStream bytePersonalizedOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytePersonalizedOut);
        employee1.writeObject(objectOutputStream);
        System.out.println("Personalized:" + bytePersonalizedOut.size());

        //version2
        System.out.println("Version 2:");
        FileOutputStream fileOutV2 = new FileOutputStream("EmployeeV2.ser");
        ObjectOutputStream objectOutV2 = new ObjectOutputStream(fileOutV2);
        Employee employeeV2 = new Employee();
        employeeV2.initPrintPerson();
        employeeV2.newFeature = "added Feature";
        employeeV2.writeObject(objectOutV2);
        FileInputStream fileInV2 = new FileInputStream("EmployeeV2.ser");
        ObjectInputStream objectInV2 = new ObjectInputStream(fileInV2);
        Employee employeeV2In = new Employee();
        employeeV2In.readObject(objectInV2);

        System.out.println(employeeV2In.newFeature);

        //older Version
        System.out.println("Old version:");
        FileOutputStream fileOutV1 = new FileOutputStream("OldVersionEmployee.ser");
        ObjectOutputStream objectOutv1 = new ObjectOutputStream(fileOutV1);
        Employee employeeV1 = new Employee();
        employeeV1.initPrintPerson();
        employeeV1.setOldVersion();
        employeeV1.writeObject(objectOutv1);


        FileInputStream fileInV1 = new FileInputStream("OldVersionEmployee.ser");
        ObjectInputStream objectInV1 = new ObjectInputStream(fileInV1);

        Employee employeeInV1 = new Employee();
        employeeInV1.readObject(objectInV1);
        System.out.println(employeeInV1.newFeature);

    }
}
