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

import cn.linliangjun.polaris.logging.impl.AbstractLogger;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Formatter;
import java.util.function.Supplier;

/**
 * 记录器
 *
 * <p>记录器是可以分层次和级别记录程序运行状态的组件，这在定位问题时非常有用。消息的格式化遵循
 * {@linkplain Formatter} 定义。
 *
 * @author linliangjun
 */
public sealed interface Logger permits AbstractLogger {

    String ROOT_LOGGER_NAME = "ROOT";

    @Nonnull String getName();

    @Nullable Logger getParent();

    void error(@Nonnull Supplier<String> messageSupplier);

    void warn(@Nonnull Supplier<String> messageSupplier);

    void info(@Nonnull Supplier<String> messageSupplier);

    void debug(@Nonnull Supplier<String> messageSupplier);

    void trace(@Nonnull Supplier<String> messageSupplier);

    void error(@Nonnull String message, @Nonnull Object... args);

    void warn(@Nonnull String message, @Nonnull Object... args);

    void info(@Nonnull String message, @Nonnull Object... args);

    void debug(@Nonnull String message, @Nonnull Object... args);

    void trace(@Nonnull String message, @Nonnull Object... args);

    void error(@Nonnull Throwable cause, @Nonnull Supplier<String> messageSupplier);

    void warn(@Nonnull Throwable cause, @Nonnull Supplier<String> messageSupplier);

    void info(@Nonnull Throwable cause, @Nonnull Supplier<String> messageSupplier);

    void debug(@Nonnull Throwable cause, @Nonnull Supplier<String> messageSupplier);

    void trace(@Nonnull Throwable cause, @Nonnull Supplier<String> messageSupplier);

    void error(@Nonnull Throwable cause, @Nonnull String message, @Nonnull Object... args);

    void warn(@Nonnull Throwable cause, @Nonnull String message, @Nonnull Object... args);

    void info(@Nonnull Throwable cause, @Nonnull String message, @Nonnull Object... args);

    void debug(@Nonnull Throwable cause, @Nonnull String message, @Nonnull Object... args);

    void trace(@Nonnull Throwable cause, @Nonnull String message, @Nonnull Object... args);
}
