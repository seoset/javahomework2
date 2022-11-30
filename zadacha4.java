//задача 4. К калькулятору из предыдущего дз добавить логирование.

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.*;

class Calculator{
    private PrintWriter logPrinter;

    private void init(String logFileName) {
      try{
         this.logPrinter =  new PrintWriter(new FileOutputStream(logFileName, true));
        logln("===================");
        logln("start: "+ new Date().toString());
      }
      catch (Exception e){
        System.out.println(e);
      }
    }
    public Calculator() {
      init("./CalculatorLog.txt");
    }
    public Calculator(String logFileName){
      init(logFileName);
    }

    public void close() {
      println("===Good Bye===");
     logln("quit: "+ new Date().toString()); 
    }

    public void log(String msg) {
      logPrinter.print(msg);
      logPrinter.flush();
    } 

    public void logln(String msg) {
      logPrinter.println(msg);
      logPrinter.flush();
    } 

    public void print(String msg) {
      log(msg);
      System.out.print(msg);
    }

      public void println(String msg) {
      logln(msg);
      System.out.println(msg);
    }

    public String compute(String cmd) {
      if (cmd.equals("q")) return "q";
      if (cmd.equals("")) return "";
      logPrinter.println(cmd);
      cmd = cmd.replaceAll(" ", "");
      String options[] = {"+", "-", "*", "/"};
      List<String> optionsList = new ArrayList<String>(Arrays.asList(options));
      List<String> nums = new ArrayList<String>();
      List<String> opts = new ArrayList<String>();
      int prevI = -1;
      for (int i=0; i< cmd.length(); i++) {
        if (cmd.charAt(i) == ',') {
          return "Error: use '.' as a delimiter instead of ','";
        }
        if (optionsList.contains(""+cmd.charAt(i))) {
          nums.add(cmd.substring(prevI+1, i));
          if (nums.get(nums.size()-1).equals("")) {
            return "Error: incorrect command";
          }
          opts.add(""+cmd.charAt(i));
          prevI = i;
        }
      }
      nums.add(cmd.substring(prevI+1, cmd.length()));
      if (nums.get(nums.size()-1).equals("") || opts.size() == 0 || opts.size() != nums.size()-1) {
        return "Error: incorrect command";
      }
      // System.out.println(Arrays.toString(nums.toArray()));
      // System.out.println(Arrays.toString(opts.toArray()));
      float prevNum = Float.valueOf(nums.get(0));
      float newNum;
      String opt;
      for (int i=0; i < opts.size(); i++) {
        newNum = Float.valueOf(nums.get(i+1));
        opt = opts.get(i);
        if (opt.equals("+")) {
          prevNum += newNum;
        } else if (opt.equals("-")) {
          prevNum -= newNum;
        } else if (opt.equals("*")) {
          prevNum *= newNum;
        } else if (opt.equals("/")) {
          if (newNum == 0) {
            return "Error: zero division";
          }
          prevNum /= newNum;
        }
      }
      return Float.toString(prevNum);
    }
}

public class Main {
    public static void main(String[] args) {
      Calculator calc = new Calculator();
      calc.println("===Calculator===");
      calc.println("-supported commands: +,-,*,/");
      calc.println("-enter 'q' to quit");
      Scanner sc = new Scanner(System.in);
      String inputCmd = "";
      while(!inputCmd.equals("q")) {
        calc.print("> ");
        inputCmd = sc.nextLine();
        String result = calc.compute(inputCmd);
        calc.println(result);
      }
      sc.close();
      calc.close();
    }
}
