package com.app.jacky.goldsample.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jacky.goldsample.R;
import com.app.jacky.goldsample.entity.User;
import com.app.jacky.goldsample.logger.LoggerUtil;
import com.app.jacky.goldsample.retrofit.RetrofitHelper;
import com.app.jacky.goldsample.service.UserService;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_easy_swipe)
    RecyclerView rvEasySwipe;

    private RvEasySwipeAdapter rvAdapter;
    private List<String> listData;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        tvTitle.setText("GoldSample");
        LoggerUtil.d("debug>>>>>>");
        LoggerUtil.i("info>>>>>>");
        LoggerUtil.w("warning>>>>>>");
        LoggerUtil.v("verbose>>>>>>");
        LoggerUtil.wtf("what a terrible failure>>>>>>");

        RxPermissions rxPermissions = new RxPermissions(this);

        rxPermissions.
                requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            LoggerUtil.i("Write external storage granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            LoggerUtil.i("Write external storage denied without ask never again.");
                        } else {
                            LoggerUtil.i("Write external storage denied with ask never again");
                        }
                    }
                });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                sleep(3000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(Integer integer) throws Exception {
                                   Log.d("MainActivity", "Accept:" + integer);
                               }

                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
    }

    private void initView()
    {
        ButterKnife.bind(this);
        rvEasySwipe.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RvEasySwipeAdapter(R.layout.easy_swipe_item_layout, null);
        rvEasySwipe.setAdapter(rvAdapter);
        inflater = getLayoutInflater();
    }

    private void initData()
    {
        listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("index is =" + i);
        }
        rvAdapter.addData(listData);
        rvAdapter.notifyDataSetChanged();
    }

    public class RvEasySwipeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


        public RvEasySwipeAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {

            helper.getView(R.id.right_menu_2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                    EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.es);

                    easySwipeMenuLayout.resetStatus();
                }
            });
            helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "setOnClickListener", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    //Retrofit test
    private void getUserDetail()
    {
        UserService request = RetrofitHelper.getInstance(this).getRetrofitAPI(UserService.class);

        Call<User> call = request.getUserByName("jacky");

        call.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}