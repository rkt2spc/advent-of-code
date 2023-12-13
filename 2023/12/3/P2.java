import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class P2 {
  private final static int[][] DIRECTIONS = new int[][] {
    // top
    { 0, -1 },
    // top right
    { 1, -1 },
    // right
    { 1, 0 },
    // bottom right
    { 1, 1 },
    // bottom
    { 0, 1 },
    // bottom left
    { -1, 1 },
    // left
    { -1, 0 },
    // top left
    { -1, -1 },
  };

  public static int solve(InputStream in) {
    List<List<Character>> scheme = new ArrayList<>();

    Scanner sc = new Scanner(in);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();

      List<Character> chars = new ArrayList<>();
      for (int i = 0; i < line.length(); ++i)
        chars.add(line.charAt(i));

      scheme.add(chars);
    }
    sc.close();

    // Map of encoded gear location to list of adjacent nums
    Map<Integer, List<Integer>> gearNums = new HashMap<>();

    int width = scheme.get(0).size();
    int height = scheme.size();

    for (int i = 0; i < height; ++i) {
      // Encoded gears location: col + (row * num_cols)
      Set<Integer> gearsEncountered = new HashSet<>();
      int num = 0;

      for (int j = 0; j < width; ++j) {
        char c = scheme.get(i).get(j);

        if (Character.isDigit(c)) {
          num *= 10;
          num += c - '0';

          for (int[] direction : DIRECTIONS) {
            int ni = i + direction[0];
            int nj = j + direction[1];

            if (ni < 0 || ni >= height || nj < 0 || nj >= width)
              continue;

            char nc = scheme.get(ni).get(nj);
            if (nc == '*')
              gearsEncountered.add(nj + (ni * width));
          }

          continue;
        }

        for (int gear : gearsEncountered) {
          List<Integer> adjNums = gearNums.getOrDefault(gear, new ArrayList<>());
          adjNums.add(num);
          gearNums.put(gear, adjNums);
        }

        gearsEncountered.clear();
        num = 0;
      }

      if (num != 0) {
        for (int gear : gearsEncountered) {
          List<Integer> adjNums = gearNums.getOrDefault(gear, new ArrayList<>());
          adjNums.add(num);
          gearNums.put(gear, adjNums);
        }
      }
    }

    int result = 0;
    for (List<Integer> adjNums : gearNums.values()) {
      if (adjNums.size() < 2)
        continue;

      int gearRatio = 1;
      for (int num : adjNums)
        gearRatio *= num;

      result += gearRatio;
    }

    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    InputStream in = args.length > 0 ? new FileInputStream(args[0]) : System.in;
    System.out.println(solve(in));
  }
}
