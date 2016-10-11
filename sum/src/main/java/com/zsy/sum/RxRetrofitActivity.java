package com.zsy.sum;

import android.widget.ListView;

import com.zsy.sum.Const.ConstHttp;
import com.zsy.sum.retrofit.GitHubService;
import com.zsy.sum.retrofit.api.BaseRetrofitApi;
import com.zsy.sum.utils.RetrofitUtils;
import com.zsy.sum.utils.RxJavaUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.depend.DebugUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 24275 on 2016/9/18.
 */
public class RxRetrofitActivity extends BaseActivity {

    private final String[] Datas = {"simpleRxjava", "simpleRetrofit", "simpleSum"};

    private CommAdapter<String> adapter;

    @BindView(R.id.lv_rx_retrofit) ListView rxRetrofitLv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_rxretrofit);
    }

    @Override
    public void initData() {
        List<String> datas = Arrays.asList(Datas);
        adapter = new CommAdapter<String>(datas, R.layout.item_samples) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_samples, obj);
            }
        };
    }



    @Override
    public void initView() {
        rxRetrofitLv.setAdapter(adapter);
    }

    @OnItemClick(R.id.lv_rx_retrofit)
    @Override
    public void doItemClick(int position) {
        DebugUtils.log("position:" + position);
        switch (position) {
            case 0:
                Observable.just("just")
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                return s + "-map";
                            }
                        })
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                DebugUtils.log(s);
                            }
                        });
                break;
            case 1:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .build();
                final GitHubService service = retrofit.create(GitHubService.class);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Response<ResponseBody> response = service.listRepos("octocat").execute();
                            DebugUtils.log(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
            case 2:
                RetrofitUtils
                        .getBlogRetrofit()
                        .create(BaseRetrofitApi.class)
                        .getContent("https://www.baidu.com/")
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                DebugUtils.log("doOn:1");
                            }
                        })
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                DebugUtils.log("map");
                                return s + "-map";
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                DebugUtils.log("doOn:2");
                            }
                        })
                        .subscribe(new RxJavaUtils.DebugSubscriber<String>());
                break;
            case 3:
                break;
        }
    }
}
