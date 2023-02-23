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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 异常工具类
 *
 * @author linliangjun
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * 抛出指定类型的异常
     *
     * @throws T                       指定类型的异常
     * @throws ThrowableUtilsException 工具类内部异常
     */
    public static <T extends Throwable> void thrown(@Nonnull Class<T> tClass, @Nullable String message) throws T {
        String className = ClassUtils.getClassName(tClass);
        if (!ClassUtils.isInstantiatable(tClass)) {
            throw new ThrowableUtilsException("类型 %s 无法实例化".formatted(className));
        }
        T t = null;
        try {
            Constructor<T> constructor;
            if (message == null) {
                constructor = tClass.getConstructor();
                t = constructor.newInstance();
            } else {
                constructor = tClass.getConstructor(String.class);
                t = constructor.newInstance(message);
            }
        } catch (NoSuchMethodException e) {
            throw new ThrowableUtilsException("类型 %s 不存在公共且%s的构造方法"
                    .formatted(className, message == null ? "无参" : "仅含有有 String 类型"));
        } catch (InstantiationException | IllegalAccessException e) {
            Assert.unreachableLocation(null);
        } catch (InvocationTargetException e) {

        }
        assert t != null;
        throw t;
    }

    private static class ThrowableUtilsException extends RuntimeException {

        public ThrowableUtilsException(String message) {
            super(message);
        }
    }
}
