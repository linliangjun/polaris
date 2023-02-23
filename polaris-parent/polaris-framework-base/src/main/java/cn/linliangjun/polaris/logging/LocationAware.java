package cn.linliangjun.polaris.logging;

/**
 * 位置感知
 *
 * @author linliangjun
 */
public interface LocationAware {

    /**
     * 获取位置
     */
    StackTraceElement getLocation();
}
