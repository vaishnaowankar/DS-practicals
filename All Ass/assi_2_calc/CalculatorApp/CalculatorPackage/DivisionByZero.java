package CalculatorApp.CalculatorPackage;


/**
* CalculatorApp/CalculatorPackage/DivisionByZero.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculator.idl
* Sunday, 21 May, 2023 5:08:10 PM IST
*/

public final class DivisionByZero extends org.omg.CORBA.UserException
{

  public DivisionByZero ()
  {
    super(DivisionByZeroHelper.id());
  } // ctor


  public DivisionByZero (String $reason)
  {
    super(DivisionByZeroHelper.id() + "  " + $reason);
  } // ctor

} // class DivisionByZero
