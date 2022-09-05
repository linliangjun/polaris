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
 * 日志记录器接口
 *
 * @author linliangjun
 */
public sealed interface LoggerAdapter permits AbstractLoggerAdapter, LoggerAdapter.NoOpsLoggerAdapter {

    Logger createLogger(String name);

    final class NoOpsLoggerAdapter implements LoggerAdapter {

        @Override
        public Logger createLogger(String name) {
            return new NoOpsLogger(name);
        }

        private static class NoOpsLogger extends AbstractLogger {

            protected NoOpsLogger(String name) {
                super(name);
            }

            @Override
            protected void log(Level level, StackTraceElement sourceFrame, Throwable cause,
                               String message, Object... args) {
            }

            @Override
            protected Level convertLevel(Object level) {
                return null;
            }

            @Override
            protected <Lv> Lv convertLevel(Level level) {
                return null;
            }

            @Override
            public Level getLevel() {
                return null;
            }

            @Override
            public void setLevel(Level level) {}

            @Override
            public Logger getParent() {
                return null;
            }
        }
    }
}
