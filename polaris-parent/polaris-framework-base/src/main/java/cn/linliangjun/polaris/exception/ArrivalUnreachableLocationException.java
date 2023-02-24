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

package cn.linliangjun.polaris.exception;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * 到达了不可达的位置异常
 *
 * @author linliangjun
 */
public final class ArrivalUnreachableLocationException extends RuntimeException {

    @Nonnull
    public static ArrivalUnreachableLocationException getInstance() {
        return new ArrivalUnreachableLocationException();
    }

    @Nonnull
    public static ArrivalUnreachableLocationException getInstance(@Nullable String message) {
        if (message == null) {
            return new ArrivalUnreachableLocationException();
        }
        return new ArrivalUnreachableLocationException(message);
    }

    private ArrivalUnreachableLocationException() {
    }

    private ArrivalUnreachableLocationException(String message) {
        super(message);
    }
}
