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
import cn.linliangjun.polaris.exception.ClassCannotInstantiationException;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 异常工具类
 *
 * @author linliangjun
 */
public final class ExceptionUtils {

    public ExceptionUtils() {
    }

    /**
     * 抛出指定类型的异常
     *
     * @throws ClassCannotInstantiationException 指定的异常类无法实例化
     * @throws T                                 指定类型的异常
     */
    public static <T extends Exception> void thrown(@Nonnull Class<T> tClass) throws T {
        thrown(tClass, null);
    }

    /**
     * 抛出指定类型的异常，并附带指定的信息
     *
     * @throws ClassCannotInstantiationException 指定的异常类无法实例化
     * @throws E                                 指定类型的异常
     */
    public static <E extends Exception> void thrown(@Nonnull Class<E> tClass, @Nullable String message) throws E {
        Assert.notNull(tClass, "exception class(tClass) must not be null");
        E e = getException(tClass, message);
        var stackTrace = e.getStackTrace();
        StackTraceElement[] traceElements = Arrays.copyOfRange(stackTrace, 7, stackTrace.length);
        e.setStackTrace(traceElements);
        throw e;
    }

    private static <E extends Exception>  E getException(Class<E> tClass, String message) {
        if (!ClassUtils.isInstantiatable(tClass)) {
            throw ClassCannotInstantiationException.getInstance(tClass,
                    "it is an interface or abstract class etc.");
        }
        E exp;
        try {
            if (message == null) {
                var constructor = tClass.getConstructor();
                exp = constructor.newInstance();
            } else {
                var constructor = tClass.getConstructor(String.class);
                exp = constructor.newInstance(message);
            }
        } catch (NoSuchMethodException e) {
            // the message parameter is reused here
            message = "it has not public %s"
                    .formatted(message == null ? "parameterless" : "one and only one string parameter");
            throw ClassCannotInstantiationException.getInstance(tClass, message);
        } catch (InvocationTargetException e) {
            throw ClassCannotInstantiationException.getInstance(tClass,
                    "an exception thrown by an invoked constructor");
        } catch (InstantiationException | IllegalAccessException e) {
            throw ArrivalUnreachableLocationException.getInstance();
        }
        return exp;
    }

    public static void main(String[] args) throws Exception {
        thrown(Exception.class, null);
    }
}
