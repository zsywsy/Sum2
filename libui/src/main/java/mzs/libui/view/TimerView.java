package mzs.libui.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.TimerTask;

import mzs.libtools.api.Timer;
import mzs.libtools.utils.depend.DebugUtils;


/**
 * Created by 24275 on 2016/7/15.
 */
public class TimerView extends TextView implements Timer {

    public static final int TIMING = 0x10;
    public static final int COUNTDOWN = 0x11;
    private final long DEFAULT_DELAY = 0;
    private final long DEFAULT_PEROID = 500;
    private final Long DEFAULT_TM_MAX = 24 * 60 * 60 * 1000 - 1L;

    private final String DEFAULT_SDF_TMPL = "HH:mm:ss";
    private final String DEFAULT_TIMEZONE_ID = "GMT";

    private final String SuperStateKey = "SuperStateKey";
    private final String TimerInfoKey = "TimerInfoKey";

    private SimpleDateFormat sdf;

    private java.util.Timer timer;
    private TimerTask timerTask;

    private boolean isTm = false;
    private boolean isStopped = true;
    private long period;
    private int state;

    private Long startTm;
    private Long pauseTm;
    private Long continueTm;

    private Handler handler;

    private TimeFinishLsn timeFinishLsn;

    public TimerView(Context context) {
        this(context, null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSDF();
        handler = new MyHandler(this);
    }

    public void initSDF() {
        sdf = new SimpleDateFormat(DEFAULT_SDF_TMPL, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE_ID));
    }

    public void setSDF(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    @Override
    public boolean isTm() {
        return isTm;
    }

    @Override
    public void startTm(Long maxMs) {
        DebugUtils.log("startTm");
        isTm = true;
        isStopped = false;
        state = TIMING;
        if (maxMs == null) {
            this.period = DEFAULT_TM_MAX;
        } else {
            this.period = maxMs;
        }
        cancelTimerAndTask();
        startTm = SystemClock.elapsedRealtime();
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isTm) {
                    handler.obtainMessage(TIMING, SystemClock.elapsedRealtime() - startTm).sendToTarget();
                }
            }
        }, DEFAULT_DELAY, DEFAULT_PEROID);
    }

    @Override
    public void startCd(Long period) {
        if (period == null) {
            throw new NullPointerException("period == null");
        } else {
            this.period = period;
        }
        isTm = true;
        isStopped = false;
        state = COUNTDOWN;
        cancelTimerAndTask();
        startTm = SystemClock.elapsedRealtime();
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isTm) {
                    handler.obtainMessage(COUNTDOWN, SystemClock.elapsedRealtime() - startTm).sendToTarget();
                }
            }
        }, DEFAULT_DELAY, DEFAULT_PEROID);
    }

    @Override
    public void pauseTm() {
        if (isStopped || !isTm) {
            return;
        }
        isTm = false;
        pauseTm = SystemClock.elapsedRealtime();
    }

    @Override
    public void continueTm() {
        if (isStopped || isTm) {
            return;
        }
        isTm = true;
        continueTm = SystemClock.elapsedRealtime();
        startTm += continueTm - pauseTm;
    }

    @Override
    public void stopTm() {
        isTm = false;
        isStopped = true;
        cancelTimerAndTask();
    }

    public void cancelTimerAndTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public void setTimeFinishLsn(TimeFinishLsn timeFinishLsn) {
        this.timeFinishLsn = timeFinishLsn;
    }

    public interface TimeFinishLsn {
        void finish(int timeStyle);
    }

    private static class MyHandler extends Handler {
        private WeakReference<TimerView> weakReference;

        public MyHandler(TimerView timerView) {
            weakReference = new WeakReference<>(timerView);
        }

        @Override
        public void handleMessage(Message msg) {
            TimerView timerView = weakReference.get();
            if (timerView == null) {
                return;
            }
            switch (msg.what) {
                case TIMING:
                    Long tm = (Long) msg.obj;
                    if (tm >= timerView.period) {
                        tm = timerView.period;
                        timerView.stopTm();
                        if (timerView.timeFinishLsn != null) {
                            timerView.timeFinishLsn.finish(TIMING);
                        }
                    }
                    timerView.setText(timerView.sdf.format(tm));
                    break;
                case COUNTDOWN:
                    Long cd = (Long) msg.obj;
                    if (cd >= timerView.period) {
                        cd = timerView.period;
                        timerView.stopTm();
                        if (timerView.timeFinishLsn != null) {
                            timerView.timeFinishLsn.finish(COUNTDOWN);
                        }
                    }
                    timerView.setText(timerView.sdf.format(timerView.period + 999 - cd));
                    break;
            }
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SuperStateKey, super.onSaveInstanceState());
        String text = getText().toString();
        TimerInfo timerInfo = new TimerInfo(text, isStopped, isTm, startTm, period, state);
        bundle.putParcelable(TimerInfoKey, timerInfo);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof Bundle)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Bundle bundle = (Bundle) state;
        state = bundle.getParcelable(SuperStateKey);
        super.onRestoreInstanceState(state);
        TimerInfo timerInfo = bundle.getParcelable(TimerInfoKey);
        setText(timerInfo.getText());
        if (timerInfo.isStopped()) {
            return;
        }
        isTm = timerInfo.isTm();
        this.state = timerInfo.getState();
        period = timerInfo.getPeroid();
        if (this.state == TIMING) {
            startTm(period);
        } else if (this.state == COUNTDOWN) {
            startCd(period);
        }
        startTm = timerInfo.getStartTm();
        if (!isTm) {
            pauseTm();
        }
    }

}


class TimerInfo implements Parcelable {
    private String text;
    private boolean isStopped;
    private boolean isTm;
    private long startTm;
    private long peroid;
    private int state;


    public TimerInfo() {
    }

    public TimerInfo(String text, boolean isStopped, boolean isTm, long startTm, long peroid, int state) {
        this.text = text;
        this.isStopped = isStopped;
        this.isTm = isTm;
        this.startTm = startTm;
        this.peroid = peroid;
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public boolean isTm() {
        return isTm;
    }

    public void setTm(boolean tm) {
        isTm = tm;
    }

    public long getStartTm() {
        return startTm;
    }

    public void setStartTm(long startTm) {
        this.startTm = startTm;
    }

    public long getPeroid() {
        return peroid;
    }

    public void setPeroid(long peroid) {
        this.peroid = peroid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    protected TimerInfo(Parcel in) {
        text = in.readString();
        isStopped = in.readByte() != 0;
        isTm = in.readByte() != 0;
        startTm = in.readLong();
        peroid = in.readLong();
        state = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeByte((byte) (isStopped ? 1 : 0));
        dest.writeByte((byte) (isTm ? 1 : 0));
        dest.writeLong(startTm);
        dest.writeLong(peroid);
        dest.writeInt(state);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TimerInfo> CREATOR = new Creator<TimerInfo>() {
        @Override
        public TimerInfo createFromParcel(Parcel in) {
            return new TimerInfo(in);
        }

        @Override
        public TimerInfo[] newArray(int size) {
            return new TimerInfo[size];
        }
    };
}