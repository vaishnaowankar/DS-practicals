import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;

import CalculatorApp.*;
import CalculatorApp.CalculatorPackage.DivisionByZero;

import org.omg.CosNaming.*;
//import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class CalcClient {
    static Calculator calcImp1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]){

        try{
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is part of the Interoperable Nmaing Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objref);

            // resolve the object Reference in Naming
            String name = "Calc";
            calcImp1 = CalculatorHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Hello....From the server");

            while (true){
                out.println("1. Addition");
                out.println("2. Subtraction");
                out.println("3. Multiplication");
                out.println("4. Division");
                out.println("5. Exit");
                out.println("--");
                out.println("Choice: ");

                try{
                    String opt = br.readLine();
                    if(opt.equals("5")){
                        break;
                    } else if (opt.equals("1")){
                        out.println("a+b = " + calcImp1.getSum(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("2")){
                        out.println("a-b = " + calcImp1.getSub(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("3")){
                        out.println("a*b = " + calcImp1.getProduct(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("4")){
                        try{
                            out.println("a/b = " + calcImp1.getQuotient(getFloat("a"), getFloat("b")));

                        } catch (DivisionByZero de) {
                            out.println("Division by Zero!!!");
                        }             
        
                            
} } catch (Exception e) {
    out.println("===");
    out.println("There is an error with numbers");
    out.println("===");
}
out.println("");
        }
// CalcImp1.shutdown();
    }catch(

    Exception e)
    {
        System.out.println("ERROR: " + e);
        e.printStackTrace(System.out);}
    }

    static float getFloat(String number) throws Exception {
        out.print(number + ": ");
        return Float.parseFloat(br.readLine());
    }
    
}


