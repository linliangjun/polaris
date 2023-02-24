package cn.linliangjun.polaris.exception;

import cn.linliangjun.polaris.util.Assert;
import cn.linliangjun.polaris.util.ClassUtils;
import jakarta.annotation.Nonnull;

public final class ClassCannotInstantiationException extends RuntimeException {

    @Nonnull
    public static ClassCannotInstantiationException getInstance(@Nonnull Class<?> clazz, @Nonnull String message) {
        Assert.notNull(clazz, "class(clazz) must not be null");
        String className = ClassUtils.getClassName(clazz);
        message = "class %s can not instantiation, caused by: %s".formatted(className, message);
        return new ClassCannotInstantiationException(message);
    }

    private ClassCannotInstantiationException(String message) {
        super(message);
    }
}
