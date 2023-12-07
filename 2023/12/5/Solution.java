import java.util.Scanner;
import java.util.TreeMap;
import java.util.Arrays;

public class Solution {
  private static void computeNextValues(long[] values, TreeMap<Long, long[]> tm) {
    for (int i = 0; i < values.length; ++i) {
      Long start = tm.floorKey(values[i]);
      if (start == null)
        continue;

      long[] entry = tm.get(start);

      if (values[i] <= start + entry[1])
        values[i] = entry[0] + (values[i] - start);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    if (!sc.hasNextLine())
      return;

    // Parse seeds one-liner hihi
    long[] seeds = Arrays.stream(sc.nextLine().substring("seeds: ".length()).trim().split(" "))
        .mapToLong(Long::parseLong).toArray();

    // Use tree map to find next range
    // source -> [target, offset]
    TreeMap<Long, long[]> tm = new TreeMap<>();

    while (sc.hasNextLine()) {
      String line = sc.nextLine();

      // Encounter empty line
      // Convert source values to target values and clear the tree
      if (line.length() == 0) {
        computeNextValues(seeds, tm);
        tm.clear();
        continue;
      }

      // Skip un-necessary header line
      if (!Character.isDigit(line.charAt(0)))
        continue;

      long[] range = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
      assert range.length == 3;

      tm.put(range[1], new long[] { range[0], range[2] });
    }

    // Last mile convert
    computeNextValues(seeds, tm);

    System.out.println(Arrays.toString(seeds));
    System.out.println(Arrays.stream(seeds).min().getAsLong());
  }
}
