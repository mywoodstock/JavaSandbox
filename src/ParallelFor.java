import java.util.Random;

public class ParallelFor {

  private static final int MIN_RAND = 0;
  private static final int MAX_RAND = 10000;
  private static final int SIZE = 6400000;

  public static void main(String[] args) {
    Random rand = new Random();
    Integer[] numbers = new Integer[SIZE];

    for(int i = 0; i < numbers.length; ++i) {
      numbers[i] = rand.nextInt(MAX_RAND - MIN_RAND + 1) + MIN_RAND;
    }

    Integer sum = 0;

    // omp parallel for public(sum)
    for(int i = 0; i < numbers.length; ++i) {
      // omp critical
      {
        sum += numbers[i];
      }
    }

    System.out.println("RESULT: " + sum);
  }

}
