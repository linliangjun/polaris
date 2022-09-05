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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 记录器工厂测试
 *
 * @author linliangjun
 */
@DisplayName("记录器工厂测试")
class LoggerFactoryTest {

    private static Logger logger;

    @BeforeAll
    static void beforeAll() {
        logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
    }

    @Test
    @DisplayName("获取记录器")
    void getLogger() {
        Assertions.assertNotNull(logger);
    }

    @Test
    @DisplayName("记录 info 级别信息")
    void info() {
        logger.info("日志测试 {0}", 123);
    }
}
