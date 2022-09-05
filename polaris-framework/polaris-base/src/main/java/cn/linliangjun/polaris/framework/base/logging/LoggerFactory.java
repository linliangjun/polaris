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

package cn.linliangjun.polaris.framework.base.logging;

import java.util.Collections;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 记录器工厂
 *
 * @author linliangjun
 */
public final class LoggerFactory {

    private static final LoggerAdapter ADAPTER = select();
    private static final Map<String, Logger> LOGGER_CACHE = Collections.synchronizedMap(new WeakHashMap<>());

    public static Logger getLogger(String name) {
        return LOGGER_CACHE.computeIfAbsent(name, ADAPTER::createLogger);
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    private static LoggerAdapter select() {
        var map = ServiceLoader.load(AbstractLoggerAdapter.class).stream()
                .map(ServiceLoader.Provider::get)
                .filter(AbstractLoggerAdapter::exists)
                .collect(Collectors.toUnmodifiableMap(AbstractLoggerAdapter::getName, Function.identity()));
        for (var adapter : map.values()) {
            if (adapter.effective(map)) {
                return adapter;
            }
        }
        return new LoggerAdapter.NoOpsLoggerAdapter();
    }

    private LoggerFactory(){}
}
