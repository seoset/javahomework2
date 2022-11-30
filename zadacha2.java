//2. Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.Date;

class ArrayBubble{
    private long[] arr;
    private int iterNumber;
    private int elCount;
    private PrintWriter logPrinter;

    private void init(int max, String logFileName) {
      this.arr = new long[max];
      try{
         this.logPrinter =  new PrintWriter(new FileOutputStream(logFileName, true));
      }
      catch (Exception e){
        System.out.println(e);
      }
      this.elCount = 0;
      this.iterNumber = 0;
    }

    public ArrayBubble(int max) {
      init(max, "./bubbleArrayLog.txt");
    }

    public ArrayBubble(int max, String logFileName){
      init(max, logFileName);
    }

    public void into(long value){
        arr[elCount] = value;
        elCount++;
    }

    public void logState(){
      logPrinter.println("----iteration "+Integer.toString(iterNumber)+"----");
      for (int i = 0; i < elCount; i++){
            logPrinter.print(arr[i] + " ");
        }
      logPrinter.println("");
      logPrinter.println("-------------------");
      logPrinter.flush();
    }

    public void print(){
      for (int i = 0; i < elCount; i++){
          System.out.print(arr[i] + " ");
      }
      System.out.println("");
    }

    private void swap(int first, int second){
        long tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }

    public void sort(){
        logPrinter.println("new sort: "+ new Date().toString());
        iterNumber = 0;
        boolean wasSwapped = false;
        this.logState();
        for (int out = elCount - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(arr[in] > arr[in + 1]) {
                    swap(in, in + 1);
                    wasSwapped = true;
                    iterNumber++;
                    this.logState();
                }
            }
          if (!wasSwapped) return;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array length: ");
        int arraySize = sc.nextInt();
        ArrayBubble bubbleArr = new ArrayBubble(arraySize);

      long val = 0;
      System.out.println("-Enter values-");
      for(int i=0; i<arraySize; i++) {
        System.out.print("arr["+Integer.toString(i)+"] = ");
        val = sc.nextLong();
        bubbleArr.into(val);
      }
      sc.close();
      System.out.println("--------------");
      bubbleArr.sort();
      bubbleArr.print();
      System.out.println("-Sort is done-");
    }
}
