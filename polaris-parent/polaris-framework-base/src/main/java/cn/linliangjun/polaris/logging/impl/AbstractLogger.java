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
import cn.linliangjun.polaris.logging.Logger;
import cn.linliangjun.polaris.logging.LoggerFactory;
import cn.linliangjun.polaris.util.Assert;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.Supplier;

import static cn.linliangjun.polaris.logging.Level.*;

/**
 * 抽象记录器，记录器实现的公共父类
 *
 * @author linliangjun
 */
public abstract sealed class AbstractLogger implements Logger permits AbstractSimpleLogger, AbstractLocationAwareLogger {

    private final String name;

    public AbstractLogger(@Nonnull String name) {
        Assert.notNull(name, "logger name must not be null");
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Logger getParent() {
        int index = name.indexOf(".");
        String parentName = index == -1 ? ROOT_LOGGER_NAME : name.substring(index);
        return LoggerFactory.getLogger(parentName);
    }

    @Override
    public void error(Supplier<String> messageSupplier) {
        if (isActive(ERROR)) {
            log(ERROR, null, messageSupplier.get());
        }
    }

    @Override
    public void warn(Supplier<String> messageSupplier) {
        if (isActive(WARN)) {
            log(WARN, null, messageSupplier.get());
        }
    }

    @Override
    public void info(Supplier<String> messageSupplier) {
        if (isActive(INFO)) {
            log(INFO, null, messageSupplier.get());
        }
    }

    @Override
    public void debug(Supplier<String> messageSupplier) {
        if (isActive(DEBUG)) {
            log(DEBUG, null, messageSupplier.get());
        }
    }

    @Override
    public void trace(Supplier<String> messageSupplier) {
        if (isActive(TRACE)) {
            log(TRACE, null, messageSupplier.get());
        }
    }

    @Override
    public final void error(String message, Object... args) {
        if (isActive(ERROR)) {
            log(ERROR, null, message, args);
        }
    }

    @Override
    public final void warn(String message, Object... args) {
        if (isActive(WARN)) {
            log(WARN, null, message, args);
        }
    }

    @Override
    public final void info(String message, Object... args) {
        if (isActive(INFO)) {
            log(INFO, null, message, args);
        }
    }

    @Override
    public final void debug(String message, Object... args) {
        if (isActive(DEBUG)) {
            log(DEBUG, null, message, args);
        }
    }

    @Override
    public final void trace(String message, Object... args) {
        if (isActive(TRACE)) {
            log(TRACE, null, message, args);
        }
    }

    @Override
    public void error(Throwable cause, Supplier<String> messageSupplier) {
        if (isActive(ERROR)) {
            log(ERROR, cause, messageSupplier.get());
        }
    }

    @Override
    public void warn(Throwable cause, Supplier<String> messageSupplier) {
        if (isActive(WARN)) {
            log(WARN, cause, messageSupplier.get());
        }
    }

    @Override
    public void info(Throwable cause, Supplier<String> messageSupplier) {
        if (isActive(INFO)) {
            log(INFO, cause, messageSupplier.get());
        }
    }

    @Override
    public void debug(Throwable cause, Supplier<String> messageSupplier) {
        if (isActive(DEBUG)) {
            log(DEBUG, cause, messageSupplier.get());
        }
    }

    @Override
    public void trace(Throwable cause, Supplier<String> messageSupplier) {
        if (isActive(TRACE)) {
            log(TRACE, cause, messageSupplier.get());
        }
    }

    @Override
    public final void error(Throwable cause, String message, Object... args) {
        if (isActive(ERROR)) {
            log(ERROR, cause, message, args);
        }
    }

    @Override
    public final void warn(Throwable cause, String message, Object... args) {
        if (isActive(WARN)) {
            log(WARN, cause, message, args);
        }
    }

    @Override
    public final void info(Throwable cause, String message, Object... args) {
        if (isActive(INFO)) {
            log(INFO, cause, message, args);
        }
    }

    @Override
    public final void debug(Throwable cause, String message, Object... args) {
        if (isActive(DEBUG)) {
            log(DEBUG, cause, message, args);
        }
    }

    @Override
    public final void trace(Throwable cause, String message, Object... args) {
        if (isActive(TRACE)) {
            log(TRACE, cause, message, args);
        }
    }

    protected abstract boolean isActive(Level level);

    protected abstract void log(@Nonnull Level level, @Nullable Throwable cause, @Nonnull String message, @Nonnull Object... args);
}
