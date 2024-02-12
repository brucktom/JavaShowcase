package Annotation_Preview;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Scanner;

public class ClassPrinter {
    /*
    The following program expects a class as input, and then prints out its inheritance tree,
    its fields and methods
    */
    private String path;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void printParameters(Method m) {
        Parameter[] parameterTypes = m.getParameters();
        System.out.print("(");
        for(int i=0; i < parameterTypes.length; i++) {
            if(i==(parameterTypes.length-1)) {
                System.out.print(parameterTypes[i].getType().getSimpleName() + " " + parameterTypes[i].getName());
            }
            else {
                System.out.print(parameterTypes[i].getType().getSimpleName() + " " + parameterTypes[i].getName() + ", ");
            }
        }
        System.out.println(")");
    }
    public ClassPrinter() {}


    public static void main(String[] args) {

        //Colors
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";

        System.out.println("Insert Class: ");
        //PASTE THIS FOR EXAMPLE:
        //java.util.LinkedHashMap

        ClassPrinter c = new ClassPrinter();
        Scanner sc = new Scanner(System.in);
        c.setPath(sc.next());
        System.out.println(c.getPath());


        Class<?> typeClass = null;
        try {
            typeClass = Class.forName(c.getPath());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Inheritance Tree
        System.out.println(ANSI_BLUE + "Information on " + typeClass);
        Class<?> ancestor = typeClass.getSuperclass();
        System.out.println("Inheritance Tree:");
        while(ancestor!=null) {
            System.out.println(ANSI_RESET + ancestor.getCanonicalName());
            ancestor = ancestor.getSuperclass();
        }
        System.out.println();

        //Fields
        System.out.println(ANSI_BLUE + "Class definition:");
        System.out.println("public class LinkedHashMap {");
        Field[] typeClassFields = typeClass.getDeclaredFields();
        for(Field field : typeClassFields) {
            System.out.println(ANSI_RESET + Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName() );
        }
        System.out.println(ANSI_BLUE + "}");

        //Methods
        Method[] methods = typeClass.getDeclaredMethods();
        System.out.println(ANSI_BLUE + "Methoden: ");
        for(Method method: methods) {
            System.out.print(ANSI_RESET + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + "  " + method.getName() + " ") ;
            c.printParameters(method);
        }

    }

}
