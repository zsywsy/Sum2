package mzs.libtools.functions;

/**
 * Created by 24275 on 2016/8/28.
 */
public interface FuncN<R> extends Function {
    R call(Object... args);
}
