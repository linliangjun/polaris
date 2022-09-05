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

import cn.linliangjun.polaris.framework.base.util.ClassUtils;

import java.util.Map;

/**
 * 抽象记录器适配器类，外部记录器适配器实现的公共父类
 *
 * @author linliangjun
 */
public non-sealed abstract class AbstractLoggerAdapter implements LoggerAdapter {

    private final String name;

    public AbstractLoggerAdapter(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final boolean exists() {
        return ClassUtils.exists(name);
    }

    public abstract boolean effective(Map<String, AbstractLoggerAdapter> adapters);
}
