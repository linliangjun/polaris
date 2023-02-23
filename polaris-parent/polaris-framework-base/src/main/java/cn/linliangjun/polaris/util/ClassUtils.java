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

import java.lang.reflect.Modifier;
import java.util.function.Consumer;

/**
 * Class 工具类
 *
 * @author linliangjun
 */
public final class ClassUtils {

    private ClassUtils() {
    }

    /**
     * 判断指定的类是否可实例化
     *
     * <p>可实例化要求类不能是原始类型、数组、抽象类、匿名类、接口、枚举、注解、Void/void 类型、合成类型。
     */
    public static boolean isInstantiatable(@Nonnull Class<?> clazz) {
        Assert.notNull(clazz, "class(clazz) must not be null");
        return !clazz.isPrimitive()
                && !clazz.isArray()
                && !Modifier.isAbstract(clazz.getModifiers())
                && !clazz.isAnonymousClass()
                && !clazz.isInterface()
                && !clazz.isEnum()
                && !clazz.isAnnotation()
                && clazz != void.class
                && clazz != Void.class
                && !clazz.isSynthetic();
    }

    public static void main(String[] args) {

    }

    /**
     * @return
     */
//    public static boolean hasPublicConstruct() {
//
//    }

    /**
     * 获取指定类的类名
     */
    public static String getClassName(@Nonnull Class<?> clazz) {
        Assert.notNull(clazz, "class(clazz) must not be null");
        return clazz.getCanonicalName();
    }
}
