package mzs.libtools.functions;

/**
 * Created by 24275 on 2016/8/28.
 */
public interface Func1<T, R> extends Function {
    R call(T t);
}
