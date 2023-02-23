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

package cn.linliangjun.polaris.logging.impl;

import cn.linliangjun.polaris.logging.Level;
import cn.linliangjun.polaris.logging.LocationAware;

public abstract non-sealed class AbstractLocationAwareLogger extends AbstractLogger implements LocationAware {

    public AbstractLocationAwareLogger(String name) {
        super(name);
    }

    @Override
    protected boolean isActive(Level level) {
        return false;
    }

    @Override
    protected void log(Level level, Throwable cause, String message, Object... args) {

    }

    @Override
    public StackTraceElement getLocation() {
        return null;
    }
}
