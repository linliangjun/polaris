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

package cn.linliangjun.polaris.framework.base.logging.impl;

import cn.linliangjun.polaris.framework.base.logging.AbstractLogger;
import cn.linliangjun.polaris.framework.base.logging.AbstractLoggerAdapter;
import cn.linliangjun.polaris.framework.base.logging.Level;
import cn.linliangjun.polaris.framework.base.logging.Logger;
import cn.linliangjun.polaris.framework.base.logging.LoggerFactory;
import cn.linliangjun.polaris.framework.base.util.StackTraceUtils;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * java.util.logging 记录器适配器
 *
 * @author linliangjun
 */
public final class JulLoggerAdapter extends AbstractLoggerAdapter {

    public JulLoggerAdapter() {
        super("java.util.logging.Logger");
    }

    @Override
    public Logger createLogger(String name) {
        return new JulLogger(name);
    }

    @Override
    public boolean effective(Map<String, AbstractLoggerAdapter> adapters) {
        return adapters.size() == 1;
    }

    private static class JulLogger extends AbstractLogger {

        private final java.util.logging.Logger logger;

        public JulLogger(String name) {
            super(name);
            logger = java.util.logging.Logger.getLogger(name);
        }

        @Override
        protected void log(Level level, StackTraceElement sourceFrame,
                           Throwable cause, String message, Object... args) {
            var record = new LogRecord(convertLevel(level), message);
            record.setThrown(cause);
            record.setParameters(args);
            if (sourceFrame == null) {
                sourceFrame = StackTraceUtils.getStackFrame(AbstractLogger.class.getName(),
                        null, true);
            }
            if (sourceFrame != null) {
                record.setSourceClassName(sourceFrame.getClassName());
                record.setSourceMethodName(sourceFrame.getMethodName());
            }
            logger.log(record);
        }

        @Override
        protected Level convertLevel(Object level) {
            Level lv;
            if (level == java.util.logging.Level.SEVERE) {
                lv = Level.ERROR;
            } else if (level == java.util.logging.Level.WARNING) {
                lv = Level.WARN;
            } else if (level == java.util.logging.Level.INFO) {
                lv = Level.INFO;
            } else if (level == java.util.logging.Level.CONFIG) {
                lv = Level.DEBUG;
            } else if (level == java.util.logging.Level.FINE) {
                lv = Level.TRACE;
            } else {
                throw new IllegalStateException("不兼容的等级类型：" + level);
            }
            return lv;
        }

        @Override
        protected <Lv> Lv convertLevel(Level level) {
            @SuppressWarnings("unchecked")
            Lv lv =(Lv) switch (level) {
                case ERROR -> java.util.logging.Level.SEVERE;
                case WARN -> java.util.logging.Level.WARNING;
                case INFO -> java.util.logging.Level.INFO;
                case DEBUG -> java.util.logging.Level.CONFIG;
                case TRACE -> java.util.logging.Level.FINE;
            };
            return lv;
        }

        @Override
        public Logger getParent() {
            var parent = logger.getParent();
            if (parent == null) {
                return null;
            }
            return LoggerFactory.getLogger(parent.getName());
        }

        @Override
        public Level getLevel() {
            var level = logger.getLevel();
            if (level != null) {
                return convertLevel(logger.getLevel());
            }
            var parent = getParent();
            if (parent == null) {
                return null;
            }
            return parent.getLevel();
        }

        @Override
        public void setLevel(Level level) {
            java.util.logging.Level lv = convertLevel(level);
            var handlers = logger.getHandlers();
            if (handlers.length == 0) {
                var consoleHandler = new ConsoleHandler();
                consoleHandler.setFormatter(new SimpleFormatter());
                consoleHandler.setLevel(lv);
                logger.addHandler(consoleHandler);
            }else {
                for (Handler handler : handlers) {
                    handler.setLevel(lv);
                }
            }
            logger.setLevel(lv);
        }
    }
}
