package com.dnsimple.tools;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.function.Function;

public class CustomMatchers {
    public static <T extends Throwable, U extends Throwable> FeatureMatcher<ThrowingRunnable2<T, U>, T> thrownException(final Matcher<? super T> submatcher) {
        return new FeatureMatcher<ThrowingRunnable2<T, U>, T>(submatcher, "first thrown exception", "first thrown exception") {
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
        return new FeatureMatcher<T, U>(submatcher, "property", "property") {
            @Override
            protected U featureValueOf(T actual) {
                return getter.apply(actual);
            }
        };
    }
}
