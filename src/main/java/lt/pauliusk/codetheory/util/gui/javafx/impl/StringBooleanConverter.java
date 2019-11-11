package lt.pauliusk.codetheory.util.gui.javafx.impl;

import lt.pauliusk.codetheory.util.gui.javafx.IStringBooleanConverter;
import org.springframework.stereotype.Component;

@Component
public class StringBooleanConverter implements IStringBooleanConverter {
    @Override
    public String convertFromBooleanArray(boolean[][] booleans) {
        StringBuilder builder = new StringBuilder();

        int rows = booleans.length;

        for (int i = 0; i < rows; i ++) {
            int columns = booleans[i].length;

            for (int j = 0; j < columns; j ++) {
                builder.append(booleans[i][j] ? '1' : '0');
            }

            if (i != rows - 1) {
                builder.append('\n');
            }
        }

        return builder.toString();
    }

    @Override
    public boolean[][] convertFromString(String booleans) {
        String[] toBuild = booleans.split("\n");
        boolean[][] toReturn = new boolean[toBuild.length][];

        for (int i = 0; i < toBuild.length; i ++) {
            toReturn[i] = buildFromRow(toBuild[i]);
        }

        return toReturn;
    }

    private boolean[] buildFromRow(String row) {
        boolean[] toReturn = new boolean[row.length()];

        for (int i = 0; i < row.length(); i ++) {
            toReturn[i] = row.charAt(i) == '1' ? true : false;
        }

        return toReturn;
    }
}
