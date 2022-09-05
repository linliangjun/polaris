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
 * 抽象记录器类，记录器实现的公共父类
 *
 * @author linliangjun
 */
public abstract non-sealed class AbstractLogger implements Logger {

    private final String name;

    protected AbstractLogger(String name) {
        this.name = name;
    }

    @Override
    public final void error(String message, Object... args) {
        log(Level.ERROR, null, null, message, args);
    }

    @Override
    public final void warn(String message, Object... args) {
        log(Level.WARN, null, null, message, args);
    }

    @Override
    public final void info(String message, Object... args) {
        log(Level.INFO, null, null, message, args);
    }

    @Override
    public final void debug(String message, Object... args) {
        log(Level.DEBUG, null, null, message, args);
    }

    @Override
    public final void trace(String message, Object... args) {
        log(Level.TRACE, null, null, message, args);
    }

    @Override
    public final void error(Throwable cause, String message, Object... args) {
        log(Level.ERROR, null, cause, message, args);
    }

    @Override
    public final void warn(Throwable cause, String message, Object... args) {
        log(Level.WARN, null, cause, message, args);
    }

    @Override
    public final void info(Throwable cause, String message, Object... args) {
        log(Level.INFO, null, cause, message, args);
    }

    @Override
    public final void debug(Throwable cause, String message, Object... args) {
        log(Level.DEBUG, null, cause, message, args);
    }

    @Override
    public final void trace(Throwable cause, String message, Object... args) {
        log(Level.TRACE, null, cause, message, args);
    }

    @Override
    public final void error(StackTraceElement sourceFrame, Throwable cause, String message, Object... args) {
        log(Level.ERROR, sourceFrame, cause, message, args);
    }

    @Override
    public final void warn(StackTraceElement sourceFrame, Throwable cause, String message, Object... args) {
        log(Level.WARN, sourceFrame, cause, message, args);
    }

    @Override
    public final void info(StackTraceElement sourceFrame, Throwable cause, String message, Object... args) {
        log(Level.INFO, sourceFrame, cause, message, args);
    }

    @Override
    public final void debug(StackTraceElement sourceFrame, Throwable cause, String message, Object... args) {
        log(Level.DEBUG, sourceFrame, cause, message, args);
    }

    @Override
    public final void trace(StackTraceElement sourceFrame, Throwable cause, String message, Object... args) {
        log(Level.TRACE, sourceFrame, cause, message, args);
    }

    @Override
    public final String getName() {
        return name;
    }

    protected abstract void log(Level level, StackTraceElement sourceFrame,
                                Throwable cause, String message, Object... args);

    protected abstract Level convertLevel(Object level);

    protected abstract <Lv> Lv convertLevel(Level level);
}
