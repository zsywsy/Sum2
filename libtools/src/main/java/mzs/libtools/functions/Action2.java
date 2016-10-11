package mzs.libtools.functions;

/**
 * Created by 24275 on 2016/8/28.
 */
public interface Action2<T1, T2> extends Action {
    void call(T1 t1, T2 t2);
}
