import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

class Solution {
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

  public static void main(String[] args) {
    List<List<Character>> scheme = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();

      List<Character> chars = new ArrayList<>();
      for (int i = 0; i < line.length(); ++i)
        chars.add(line.charAt(i));

      scheme.add(chars);
    }

    int result = 0;

    int width = scheme.get(0).size();
    int height = scheme.size();

    for (int i = 0; i < height; ++i) {
      boolean isValidRun = false;
      int num = 0;

      for (int j = 0; j < width; ++j) {
        char c = scheme.get(i).get(j);

        if (Character.isDigit(c)) {
          num *= 10;
          num += c - '0';

          // Only check for adj symbol once for each run
          if (!isValidRun) {
            for (int[] direction : DIRECTIONS) {
              int ni = i + direction[0];
              int nj = j + direction[1];

              if (ni < 0 || ni >= height || nj < 0 || nj >= width)
                continue;

              char nc = scheme.get(ni).get(nj);
              if (!Character.isDigit(nc) && nc != '.') {
                isValidRun = true;
                break;
              }
            }
          }

          continue;
        }

        if (c == '.') {
          if (isValidRun)
            result += num;

          isValidRun = false;
          num = 0;
          continue;
        }
      }

      if (isValidRun)
        result += num;
    }

    System.out.println(result);
  }
}
