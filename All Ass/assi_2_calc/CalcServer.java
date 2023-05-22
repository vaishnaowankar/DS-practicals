import CalculatorApp.*;
import CalculatorApp.CalculatorPackage.DivisionByZero;

import org.omg.CosNaming.*;
//import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

//import java.util.Properties;

class CalculatorImp1 extends CalculatorPOA {

    @Override
    public float getSum(float a, float b) {
        return a + b;
    }

    @Override
    public float getQuotient(float a, float b) throws DivisionByZero {
        if (b == 0) {
            throw new CalculatorApp.CalculatorPackage.DivisionByZero();
        } else {
            return a / b;
        }
    }

    @Override
    public float getProduct(float a, float b) {
        return a * b;
    }

    @Override
    public float getSub(float a, float b) {
        return a - b;
    }

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }
}

public class CalcServer {
    public static void main (String args[]){
        try{
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            //get refernce to rootpoa & activate the POAManager

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            //Create servant and register it with the ORB
            CalculatorImp1 helloImp1 = new CalculatorImp1();
            helloImp1.setORB(orb);

            //get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImp1);
            Calculator href = CalculatorHelper.narrow(ref);

            // get the root naming context
            //NameService invokes the name service
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable Naming Service (INS) specification

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objref);

            //bind the object REference in Naming
            String name = "Calc";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Ready...");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("Error:" +e);
            e.printStackTrace(System.out);
        }
        System.out.println("Exiting...");
    }
    
}
