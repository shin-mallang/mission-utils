package mallang.missionutils.test;

import mallang.missionutils.Randoms;
import org.junit.jupiter.api.function.Executable;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class Assertions {
    private static final Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(1L);
    private static final Duration RANDOM_TEST_TIMEOUT = Duration.ofSeconds(10L);

    private Assertions() {
    }

    public static void assertSimpleTest(Executable executable) {
        org.junit.jupiter.api.Assertions.assertTimeoutPreemptively(SIMPLE_TEST_TIMEOUT, executable);
    }

    public static void assertRandomNumberInListTest(Executable executable, Integer value, Integer... values) {
        assertRandomTest(() -> {
            Randoms.pickNumberInList(ArgumentMatchers.anyList());
        }, executable, value, values);
    }

    public static void assertRandomNumberInRangeTest(Executable executable, Integer value, Integer... values) {
        assertRandomTest(() -> {
            Randoms.pickNumberInRange(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        }, executable, value, values);
    }

    public static void assertRandomUniqueNumbersInRangeTest(Executable executable, List<Integer> value, List<Integer>... values) {
        assertRandomTest(() -> {
            Randoms.pickUniqueNumbersInRange(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());
        }, executable, value, values);
    }

    public static <T> void assertShuffleTest(Executable executable, List<T> value, List<T>... values) {
        assertRandomTest(() -> {
            Randoms.shuffle(ArgumentMatchers.anyList());
        }, executable, value, values);
    }

    private static <T> void assertRandomTest(MockedStatic.Verification verification, Executable executable, T value, T... values) {
        org.junit.jupiter.api.Assertions.assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
            MockedStatic<Randoms> mock = Mockito.mockStatic(Randoms.class);
            Throwable var5 = null;

            try {
                mock.when(verification).thenReturn(value, Arrays.stream(values).toArray());
                executable.execute();
            } catch (Throwable var14) {
                var5 = var14;
                throw var14;
            } finally {
                if (mock != null) {
                    if (var5 != null) {
                        try {
                            mock.close();
                        } catch (Throwable var13) {
                            var5.addSuppressed(var13);
                        }
                    } else {
                        mock.close();
                    }
                }

            }

        });
    }
}
