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

/**
 * 记录器接口
 *
 * <p>记录器是可以分层次和级别记录程序运行状态的组件，这在定位问题时非常有用。消息的格式化遵循
 * {@linkplain String#format(String, Object...)} 方法。
 *
 * @author linliangjun
 */
public sealed interface Logger permits AbstractLogger {

    void error(String message, Object... args);

    void warn(String message, Object... args);

    void info(String message, Object... args);

    void debug(String message, Object... args);

    void trace(String message, Object... args);

    void error(Throwable cause, String message, Object... args);

    void warn(Throwable cause, String message, Object... args);

    void info(Throwable cause, String message, Object... args);

    void debug(Throwable cause, String message, Object... args);

    void trace(Throwable cause, String message, Object... args);

    void error(StackTraceElement sourceFrame, Throwable cause, String message, Object... args);

    void warn(StackTraceElement sourceFrame, Throwable cause, String message, Object... args);

    void info(StackTraceElement sourceFrame, Throwable cause, String message, Object... args);

    void debug(StackTraceElement sourceFrame, Throwable cause, String message, Object... args);

    void trace(StackTraceElement sourceFrame, Throwable cause, String message, Object... args);

    Logger getParent();

    Level getLevel();

    void setLevel(Level level);

    String getName();
}
