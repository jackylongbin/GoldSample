package com.app.jacky.goldsample.view;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.jacky.goldsample.R;
import com.app.jacky.goldsample.application.Utils;
import com.app.jacky.goldsample.logger.LoggerUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final int SERVICE_BIND = 0X0001;
    private static final int CONNECTING = 0X0002;
    private static final int CONNECTED = 0X0003;
    private static final int DISCONNECTED = 0X0004;
    private static final int DISCONNECTING = 0X0005;
    @BindView(R.id.rv_easy_swipe)
    RecyclerView rvEasySwipe;

    @BindView(R.id.bt_bind_service)
    Button btBindService;

    @BindView(R.id.bt_enable)
    Button btEnable;

    @BindView(R.id.bt_scan)
    Button btScan;

    @BindView(R.id.bt_connect)
    Button btConnect;

    @BindView(R.id.bt_read)
    Button btRead;

    @BindView(R.id.bt_write)
    Button btWrite;

    private RvEasySwipeAdapter rvAdapter;
    private List<String> listData;
    private LayoutInflater inflater;


    private static final String SMART_WATCH_MAC="00:00:18:7A:93:01";

    private static final String SERVICE_UUID="0000fff0-0000-1000-8000-00805f9b34fb";
    private static final String NOTIFY_CHARACTERISTIC_UUID="0000fff1-0000-1000-8000-00805f9b34fb";
    private static final String WRITE_CHARACTERISTIC_UUID="0000fff2-0000-1000-8000-00805f9b34fb";
    private static final String CLIENT_CHARACTERISTIC_CONFIG="00002902-0000-1000-8000-00805F9B34FB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        RxPermissions rxPermissions = new RxPermissions(this);

        rxPermissions.
                requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.BLUETOOTH,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
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

        initBle();
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
        rvAdapter.addData(listData);
        rvAdapter.notifyDataSetChanged();
    }

    public class RvEasySwipeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


        public RvEasySwipeAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);

        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {

            helper.setText(R.id.tv_content,item);
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
                    Toast.makeText(MainActivity.this, "setOnClickListener" + v.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case SERVICE_BIND:
                    btBindService.setText("UNBAND");
                    break;
                case DISCONNECTED:
                    btConnect.setText("CONNECT");
                    mIsConnected = false;
                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(SMART_WATCH_MAC);
                    connect();
                    break;
                case DISCONNECTING:
                    btConnect.setText("DISCONNECTING");
                    break;
                case CONNECTING:
                    btConnect.setText("CONNECTING");
                    break;
                case CONNECTED:
                    btConnect.setText("CONNECTED");
                    mIsConnected = true;


                    mBluetoothGatt.discoverServices();

                    break;
            }
        }
    };

    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {

        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            return;
        }
        //通知远程端开启 notify
        if (characteristic.getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG)) != null) {
            if (enabled == true) {
                BluetoothGattDescriptor descriptor = characteristic
                        .getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG));
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                mBluetoothGatt.writeDescriptor(descriptor);
            } else {
                BluetoothGattDescriptor descriptor = characteristic
                        .getDescriptor(UUID.fromString(CLIENT_CHARACTERISTIC_CONFIG));
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                mBluetoothGatt.writeDescriptor(descriptor);
            }
        }
        mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
    }

    private boolean mIsBind = false;
    private boolean mIsScanning = false;
    private boolean mIsConnected = false;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothGattService mBluetoothGattService;

    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private BluetoothGattCharacteristic mWriteCharacteristic;

    private void initBle()
    {
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
    }

    private final  BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

            if(mBluetoothGatt == null)
            {
                LoggerUtil.d("Bluetooth gatt is null!");
                return;
            }
            mBluetoothGattService = mBluetoothGatt.getService(UUID.fromString(SERVICE_UUID));
            if(mBluetoothGattService == null)
            {
                LoggerUtil.d("Bluetooth gatt service is null!");
                return;
            }

            mNotifyCharacteristic = mBluetoothGattService.getCharacteristic(UUID.fromString(NOTIFY_CHARACTERISTIC_UUID));

            if(mNotifyCharacteristic != null)
            {
                setCharacteristicNotification(mNotifyCharacteristic,true);

                LoggerUtil.d("Get notify characteristic success!");
            }

            mWriteCharacteristic = mBluetoothGattService.getCharacteristic(UUID.fromString(WRITE_CHARACTERISTIC_UUID));

            if(mWriteCharacteristic != null)
            {
                LoggerUtil.d("Get write characteristic success!");
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            LoggerUtil.d("Characteristic read:" + Utils.bytesToHexStringNoSpace(characteristic.getValue()));
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            LoggerUtil.d("Characteristic write:" + Utils.bytesToHexStringNoSpace(characteristic.getValue()));
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            LoggerUtil.d("Characteristic change:" + Utils.bytesToHexStringNoSpace(characteristic.getValue()));
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                            int newState) {

            Message msg = new Message();
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                LoggerUtil.d("Connected!");
                msg.what = CONNECTED;
                mHandler.sendMessage(msg);
            }
            // GATT Server disconnected
            else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                LoggerUtil.d("Disconnected!");
                msg.what = DISCONNECTED;
                mHandler.sendMessage(msg);
            }
            // GATT Server disconnected
            else if (newState == BluetoothProfile.STATE_DISCONNECTING) {
                LoggerUtil.d("Disconnecting!");
                msg.what = DISCONNECTING;
                mHandler.sendMessage(msg);
            }
            else if (newState == BluetoothProfile.STATE_CONNECTING) {
                LoggerUtil.d("Connecting!");
                msg.what = CONNECTING;
                mHandler.sendMessage(msg);
            }
        }
    };

    @OnClick(R.id.bt_bind_service) void onBindService()
    {

    }

    @OnClick(R.id.bt_scan) void onSanBle()
    {

    }

    @OnClick(R.id.bt_connect) void onBleConnect()
    {
        if(!mIsConnected)
        {
            connect();
        }
        else
        {
            mBluetoothGatt.disconnect();
        }
    }

    private void connect()
    {
        mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(SMART_WATCH_MAC);
        if(mBluetoothDevice == null)
        {
            LoggerUtil.d("Get remote device failed!");
            return;
        }
        mBluetoothGatt = mBluetoothDevice.connectGatt(this,false,mGattCallback);
    }

    @OnClick(R.id.bt_write) void onBleWrite()
    {
        byte[] readStep = new byte[]{(byte)0xc6,0x01,0x08,(byte)0x08};

        byte[] setTime = new byte[]{
                (byte)0xc2,0x07,0x13,0x02,0x1b,0x0f,0x00,0x00,0x03,(byte)0x06,
        };

        mWriteCharacteristic.setValue(readStep);
        boolean ret = mBluetoothGatt.writeCharacteristic(mWriteCharacteristic);
        if(ret)
            LoggerUtil.d("Write characteristic success!");
    }
}