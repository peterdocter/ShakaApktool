package com.rover12421.shaka.smali.baksmali;

import com.rover12421.shaka.lib.HookMain;
import com.rover12421.shaka.lib.ReflectUtil;
import org.apache.commons.cli.Options;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.baksmali.main;

/**
 * Created by rover12421 on 5/17/15.
 */
@Aspect
public class baksmaliMainAj {
    public static final Options basicOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(main.class, "basicOptions");
    }

    public static final Options debugOptions() throws Exception {
        return (Options) ReflectUtil.getFieldValue(main.class, "debugOptions");
    }

    public static final Options options() throws Exception {
        return (Options) ReflectUtil.getFieldValue(main.class, "options");
    }

    private static HookMain hookMain;

    public static void setHookMain(HookMain hookMain) {
        baksmaliMainAj.hookMain = hookMain;
    }

    @Around("execution(* org.jf.baksmali.main.usage(..))" +
            "&& args(printDebugOptions)")
    public void usage(boolean printDebugOptions) {
        hookMain.usage();
    }

    @Around("execution(* org.jf.baksmali.main.version())")
    public void version() {
        hookMain.version();
    }
}
