package cn.lookk.powerauth.util;

/**
 * @ClassName: StringUtil
 * @Description: TODO
 * @Author jiaoxiangyu
 * @Date 2019/10/23
 * @Version 1.0
 * @Since JDK1.8
 */
public class StringUtil {

    public static Integer[] toInts(String[] strings) {
        Integer[] ints = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.valueOf(strings[i]);
        }
        return ints;
    }
}
