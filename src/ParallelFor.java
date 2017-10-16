import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelFor {

  private static final int MIN_RAND = 0;
  private static final int MAX_RAND = 10000;
  private static final int SIZE = 6400000;

  public static void main(String[] args) {
    Random rand = new Random();
    int[] numbers = new int[SIZE];

    for(int i = 0; i < numbers.length; ++i) {
      numbers[i] = rand.nextInt(MAX_RAND - MIN_RAND + 1) + MIN_RAND;
    }

    int sum = 0, local_sum = 0;

    // omp parallel
    {
      // omp parallel for public(sum) private(local_sum)
      for (int i = 0; i < numbers.length; ++i) {
        // omp critical
        {
          sum += numbers[i];
        }
        local_sum += numbers[i];
      }
      System.out.println("Thread " + OMP4J_THREAD_NUM + ": " + local_sum);
    }

    System.out.println("RESULT: " + sum);
  }

}
