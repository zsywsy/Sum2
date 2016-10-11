package mzs.libtools.functions;

/**
 * Created by 24275 on 2016/8/28.
 */
public interface Func2<T1, T2, R> extends Function {
    R call(T1 t1, T2 t2);
}
