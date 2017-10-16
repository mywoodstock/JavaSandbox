import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelFor {

  private static final int MIN_RAND = 0;
  private static final int MAX_RAND = 10000;
  private static final int SIZE = 6400000;

  class MyInteger extends AtomicInteger {
    /** Default constructor*/
    public MyInteger() {
      super(0);
    }
    /** Default constructor*/
    public MyInteger(int val) {
      super(val);
    }
    /** Copy constructor */
    public MyInteger(MyInteger orig) {
      super(orig.get());
    }

    public operator+(MyInteger val) {
      return super + val;
    }
  }

  public void main(String[] args) {
    Random rand = new Random();
    MyInteger[] numbers = new MyInteger[SIZE];

    for(int i = 0; i < numbers.length; ++i) {
      numbers[i] = new MyInteger(rand.nextInt(MAX_RAND - MIN_RAND + 1) + MIN_RAND);
    }

    MyInteger sum = new MyInteger(0), local_sum = new MyInteger(0);

    // omp parallel
    {
      // omp for public(sum) private(local_sum)
      for (int i = 0; i < numbers.length; ++i) {
        // omp critical
        {
          sum = sum + numbers[i];
        }
      }
      System.out.println("Thread " + OMP4J_THREAD_NUM + ": " + local_sum);
    }

    System.out.println("RESULT: " + sum);
  }

}
