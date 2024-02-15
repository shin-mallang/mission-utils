package mallang.missionutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randoms {
    private static final Random defaultRandom = ThreadLocalRandom.current();

    private Randoms() {
    }

    public static int pickNumberInList(List<Integer> numbers) {
        validateNumbers(numbers);
        return (Integer)numbers.get(pickNumberInRange(0, numbers.size() - 1));
    }

    public static int pickNumberInRange(int startInclusive, int endInclusive) {
        validateRange(startInclusive, endInclusive);
        return startInclusive + defaultRandom.nextInt(endInclusive - startInclusive + 1);
    }

    public static List<Integer> pickUniqueNumbersInRange(int startInclusive, int endInclusive, int count) {
        validateRange(startInclusive, endInclusive);
        validateCount(startInclusive, endInclusive, count);
        List<Integer> numbers = new ArrayList();

        for(int i = startInclusive; i <= endInclusive; ++i) {
            numbers.add(i);
        }

        return shuffle(numbers).subList(0, count);
    }

    public static <T> List<T> shuffle(List<T> list) {
        List<T> result = new ArrayList(list);
        Collections.shuffle(result);
        return result;
    }

    private static void validateNumbers(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("numbers cannot be empty.");
        }
    }

    private static void validateRange(int startInclusive, int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive cannot be greater than endInclusive.");
        } else if (endInclusive == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("endInclusive cannot be greater than Integer.MAX_VALUE.");
        } else if (endInclusive - startInclusive >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("the input range is too large.");
        }
    }

    private static void validateCount(int startInclusive, int endInclusive, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be less than zero.");
        } else if (endInclusive - startInclusive + 1 < count) {
            throw new IllegalArgumentException("count cannot be greater than the input range.");
        }
    }
}
