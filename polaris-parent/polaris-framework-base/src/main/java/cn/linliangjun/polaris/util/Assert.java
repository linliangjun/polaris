/*
 * Copyright 2023-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.linliangjun.polaris.util;

import cn.linliangjun.polaris.exception.ArrivalUnreachableLocationException;
import cn.linliangjun.polaris.exception.MethodArgsInvalidException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.Supplier;

/**
 * 断言工具类
 *
 * @author linliangjun
 */
public final class Assert {

    private Assert() {
    }

    /**
     * 断言对象不为 {@code null}
     *
     * @throws MethodArgsInvalidException  参数 {@code message} 或 {@code args} 为 {@code null}
     * @throws NullPointerException 对象为 {@code null}
     */
    public static void notNull(@Nullable Object o, @Nonnull String message, @Nonnull Object... args) {
        Assert.argsNotNull(new Pair<>("message", message), new Pair<>("args", args));
        if (o == null) {
            throw new NullPointerException(message.formatted(args));
        }
    }

    /**
     * 断言对象不为 {@code null}
     *
     * @throws MethodArgsInvalidException  参数 {@code messageSupplier} 为 {@code null}，或其提供的 {@code message} 在对象为
     *                              {@code null} 时为 {@code null}
     * @throws NullPointerException 对象为 {@code null}
     */
    public static void notNull(@Nullable Object o, @Nonnull Supplier<String> messageSupplier) {
        Assert.argsNotNull(new Pair<>("messageSupplier", messageSupplier));
        if (o == null) {
            String message = messageSupplier.get();
            Assert.argsNotNull(new Pair<>("messageSupplier.message", message));
            throw new NullPointerException(message);
        }
    }

    /**
     * 断言对象不为 {@code null}
     *
     * @throws MethodArgsInvalidException  参数 {@code message}、{@code args} 或 {@code tClass} 为 {@code null}
     * @throws NullPointerException 参数为 {@code null}
     */
    public static <T extends Throwable> void notNull(@Nullable Object o, @Nonnull Class<T> tClass,
                                                     @Nonnull String message, @Nonnull Object... args) throws T {
        Assert.argsNotNull(new Pair<>("tClass", tClass), new Pair<>("message", message), new Pair<>("args", args));
        if (o == null) {
            ExceptionUtils.thrown(tClass, message.formatted(args));
        }
    }

    public static void main(String[] args) {
        notNull(null, IllegalStateException.class, "test");
    }

    @SafeVarargs
    private static void argsNotNull(Pair<String, Object>... pairs) {
        StringBuilder sb = null;
        for (var pair : pairs) {
            if (pair.value() != null) {
                continue;
            }
            String message = pair.key() + " must not be null";
            if (sb == null) {
                sb = new StringBuilder(message);
            } else {
                sb.append("; ").append(message);
            }
        }
        if (sb != null) {
            sb.append(".");
            throw MethodArgsInvalidException.getInstance(sb.toString());
        }
    }

    /**
     * 断言位置不可达
     *
     * @throws ArrivalUnreachableLocationException 到达了不可达的位置
     */
    public static void unreachableLocation(@Nullable String message) {
        throw ArrivalUnreachableLocationException.getInstance(message);
    }
}
