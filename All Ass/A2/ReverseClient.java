
import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

class ReverseClient
{
    
    public static void main(String args[])
    {
        Reverse ReverseImpl=null;
        
        try
        {
            // initialize the ORB
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            String name = "Reverse";
            ReverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Enter String=");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str= br.readLine();

            String tempStr= ReverseImpl.reverse_string(str);
        
            System.out.println(tempStr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

/* 
1. Create the all ReverseServer.java , ReverseClient.java , ReverseImpl.java & ReverseModule.idl  files.

2. Run the IDL-to-Java compiler idlj, on the IDL file to create stubs and skeletons. This step assumes that you have included the path to the java/bin directory in your path.

  idlj -fall  ReverseModule.idl
The idlj compiler generates a number of files.
3. Compile the .java files, including the stubs and skeletons (which are in the directory newly created directory). This step assumes the java/bin directory is included in your path.

   javac *.java  ReverseModule/*.java
4. Start orbd. To start orbd from a UNIX command shell, enter :

  	orbd -ORBInitialPort 1050&
5. Start the server. To start the  server from a UNIX command shell, enter :
  java ReverseServer -ORBInitialPort 1050& -ORBInitialHost localhost&

6. Run the client application :
  java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost
  
*/
