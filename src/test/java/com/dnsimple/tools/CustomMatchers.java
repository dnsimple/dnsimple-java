package com.dnsimple.tools;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

public class CustomMatchers {
    public static <T extends Throwable, U extends Throwable> FeatureMatcher<ThrowingRunnable2<T, U>, T> thrownException(final Matcher<? super T> submatcher) {
        return new FeatureMatcher<>(submatcher, "first thrown exception", "first thrown exception") {
            @Override
            @SuppressWarnings("unchecked")
            protected T featureValueOf(ThrowingRunnable2<T, U> actual) {
                try {
                    actual.run();
                    return null;
                } catch (Throwable e) {
                    return (T) e;
                }
            }
        };
    }

    public static <T, U> FeatureMatcher<T, U> property(Function<T, U> getter, final Matcher<? super U> submatcher) {
        return new FeatureMatcher<>(submatcher, "property", "property") {
            @Override
            protected U featureValueOf(T actual) {
                return getter.apply(actual);
            }
        };
    }

    /**
     * Returns a matcher that will reconcile Number types (int, long, and double). Useful
     * when combined with number values deserialized with GSON because it's impossible
     * to predict which specific number type will get.
     */
    public static BaseMatcher<Object> number(Number expectedValue) {
        if (expectedValue instanceof Float || expectedValue instanceof Byte)
            throw new IllegalArgumentException("Float and Byte not supported by this matcher");
        return new BaseMatcher<>() {
            @Override
            public boolean matches(Object item) {
                if (item instanceof Float || item instanceof Byte)
                    throw new IllegalArgumentException("Float and Byte not supported by this matcher");
                if (expectedValue instanceof Long) {
                    if (item instanceof Integer)
                        return Integer.MIN_VALUE <= expectedValue.longValue() && expectedValue.longValue() <= Integer.MAX_VALUE && (int) item == expectedValue.intValue();
                    if (item instanceof Long)
                        return (long) item == expectedValue.longValue();
                    if (item instanceof Double) {
                        Long itemAsLong = toLong((Double) item);
                        return itemAsLong != null && itemAsLong == expectedValue.longValue();
                    }
                }
                if (expectedValue instanceof Integer) {
                    if (item instanceof Integer)
                        return (int) item == expectedValue.intValue();
                    if (item instanceof Long)
                        return Integer.MIN_VALUE <= (long) item && (long) item <= Integer.MAX_VALUE && ((Long) item).intValue() == expectedValue.intValue();
                    if (item instanceof Double) {
                        Integer itemAsInt = toInt((Double) item);
                        return itemAsInt != null && itemAsInt == expectedValue.longValue();
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("number ").appendValue(expectedValue);
            }

            private Long toLong(Double d) {
                try {
                    return BigDecimal.valueOf(d).toBigIntegerExact().longValue();
                } catch (ArithmeticException ex) {
                    return null;
                }
            }

            private Integer toInt(Double d) {
                try {
                    BigInteger bigInteger = BigDecimal.valueOf(d).toBigIntegerExact();
                    Long longValue = bigInteger.longValue();
                    if (Integer.MIN_VALUE <= longValue && longValue <= Integer.MAX_VALUE)
                        return longValue.intValue();
                    return null;
                } catch (ArithmeticException ex) {
                    return null;
                }
            }
        };
    }
}
