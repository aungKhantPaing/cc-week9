import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<MineField> fields = new ArrayList<>();

        while (true) {
            String[] inputs = sc.nextLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int m = Integer.parseInt(inputs[1]);

            if (n == 0 || m == 0) break;

            String[][] b = new String[n][m];

            for (int i = 0; i < n; i++) {
                String[] l = sc.nextLine().split(" ");
                b[i] = l;
            }

            fields.add(new MineField(b));
        }

        for (int i = 0; i < fields.size(); i++) {
            System.out.printf("Field #%d:\n", i + 1);
            System.out.println(fields.get(i).field);
        }
    }
}

class MineField {
    String field;

    public MineField(String[][] blocks) {
        int n = blocks.length;
        int m = blocks[0].length;

        int[][] clonedField = new int[n + 2][m + 2];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (blocks[x][y].equals("*")) {
                    this.flagMine(x, y, clonedField);
                }
            }
        }

        StringBuilder field = new StringBuilder();
        for (int x = 0; x < n; x++) {
            for (int y =   0; y < m; y++) {
                if (blocks[x][y].equals("*")) field.append("* ");
                else field.append(clonedField[x + 1][y + 1]).append(" ");
            }
            field.append("\n");
        }

        this.field = field.toString();
    }

    private void flagMine(int x, int y, int[][] field) {
        field[x+1][y] += 1; // mid-left
    //  field[x+1][y+1] = -1; // mid-mid
        field[x+1][y+2] += 1; // mid-right

        field[x][y] += 1; // top-left
        field[x][y+1] += 1; // top-mid
        field[x][y+2] += 1; // top-right

        field[x+2][y] += 1; // bot-left
        field[x+2][y+1] += 1; // bot-mid
        field[x+2][y+2] += 1; // bot-right
    }
}