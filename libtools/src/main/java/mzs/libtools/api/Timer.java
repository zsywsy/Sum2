package mzs.libtools.api;

/**
 * Created by 24275 on 2016/7/15.
 */
public interface Timer {

    boolean isTm();

    void startTm(Long maxTm);

    void startCd(Long periodCd); //start count down

    void pauseTm();

    void continueTm();

    void stopTm();


}
