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

package cn.linliangjun.polaris.framework.base.util;

import java.util.function.Predicate;

/**
 * 堆栈追踪工具类
 *
 * @author linliangjun
 */
public class StackTraceUtils {

    public static StackTraceElement[] getStackTrace() {
        return new Throwable().getStackTrace();
    }

    public static StackTraceElement getStackFrame(String className,
                                                  String methodName,
                                                  boolean ignoreSelfCall) {
        var stackTrace = getStackTrace();
        var index = getStackTraceIndex(stackTrace, className, methodName, 2, ignoreSelfCall);
        if (index == -1) {
            return null;
        }
        return stackTrace[index];
    }

    private static int getStackTraceIndex(StackTraceElement[] stackTrace,
                                          String className,
                                          String methodName,
                                          int start,
                                          boolean ignoreSelfCall) {
        Predicate<StackTraceElement> predicate = methodName == null ?
                frame -> className.equals(frame.getClassName())
                : frame -> className.equals(frame.getClassName()) && methodName.equals(frame.getMethodName());
        int index = -1;
        int len = stackTrace.length;
        for (int i = start; i < len; i++) {
            if (predicate.test(stackTrace[i])) {
                index = i;
                break;
            }
        }
        if (index == -1 || !ignoreSelfCall) {
            return index;
        }
        for (int i = index + 1; i < stackTrace.length; i++) {
            if (!className.equals(stackTrace[i].getMethodName())) {
                return i;
            }
        }
        return -1;
    }
}
