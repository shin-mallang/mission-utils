package mallang.missionutils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Scanner;

public class Console {
    private static Scanner scanner;

    private Console() {
    }

    public static String readLine() {
        return getInstance().nextLine();
    }

    private static Scanner getInstance() {
        if (Objects.isNull(scanner) || isClosed()) {
            scanner = new Scanner(System.in);
        }

        return scanner;
    }

    private static boolean isClosed() {
        try {
            String string = scanner.toString();
            return string.contains("[source closed=true]");
        } catch (Exception var1) {
            System.out.println("unable to determine if the scanner is closed.");
            return true;
        }
    }
}
