import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {
    public static void main(String[] args) {
        int result = 0;

        final int MAX_R = 12;
        final int MAX_G = 13;
        final int MAX_B = 14;

        int gameID = 1;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            boolean isPossible = true;

            // Forward scan without allocation
            int index = line.indexOf(':') + 2;
            while (index < line.length()) {
                int next = line.indexOf(' ', index + 1);

                int count = Integer.parseInt(line.substring(index, next++));
                char color = line.charAt(next++);

                if (color == 'r' && count > MAX_R) isPossible = false;
                else if (color == 'g' && count > MAX_G) isPossible = false;
                else if (count > MAX_B) isPossible = false;

                if (!isPossible)
                    break;

                index = line.indexOf(' ', next);
                if (index == -1) break;
                else ++index;
            }

            if (isPossible)
                result += gameID;

            ++gameID;
        }

        System.out.println(result);
    }
}