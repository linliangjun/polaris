/*
 * Copyright 2022 linliangjun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.linliangjun.polaris.logging;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 记录器工厂
 *
 * @author linliangjun
 */
public final class LoggerFactory {

    private static final Map<String, Logger> LOGGER_CACHE = Collections.synchronizedMap(new WeakHashMap<>());

    public static Logger getLogger(String name) {
        return null;
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

}
