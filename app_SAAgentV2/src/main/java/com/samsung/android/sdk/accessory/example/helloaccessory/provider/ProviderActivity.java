package com.samsung.android.sdk.accessory.example.helloaccessory.provider;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProviderActivity extends Activity {
    static int nLineIndex1 = 0;
    static int nLineIndex2 = 0;

    LineChart lineChart1;
    LineChart lineChart2;
    final int DATA_RANGE = 100;

    ArrayList<Entry> accelEntries1;
    ArrayList<Entry> accelEntries2;
    ArrayList<Entry> accelEntries3;
    LineDataSet accelSet1;
    LineDataSet accelSet2;
    LineDataSet accelSet3;
    ArrayList<ILineDataSet> accelDataSets;

    ArrayList<Entry> gyroEntries1;
    ArrayList<Entry> gyroEntries2;
    ArrayList<Entry> gyroEntries3;
    LineDataSet gyroSet1;
    LineDataSet gyroSet2;
    LineDataSet gyroSet3;
    ArrayList<ILineDataSet> gyroDataSets;
    public static Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        initChart1();
        initChart2();

        //threadStart(1);
        //threadStart(2);

        mContext = this;
    }

    private void initChart1() {
        lineChart1 = (LineChart) findViewById(R.id.chart);

        //String[] accel_xAxis = new String[DATA_RANGE];

        //{"0", "1", "2", "3", "4", "5", "6", "8", "9"};
        accelEntries1 = new ArrayList<>();
        accelEntries2 = new ArrayList<>();
        accelEntries3 = new ArrayList<>();

        for(int i = 0 ; i < DATA_RANGE ; i++) {
            //accel_xAxis[i] = Integer.toString(i);
            accelEntries1.add(new Entry(i,0f));
            accelEntries2.add(new Entry(i,0f));
            accelEntries3.add(new Entry(i,0f));
        }

        accelSet1 = new LineDataSet(accelEntries1, "AccelX");
        accelSet1.setColors(Color.RED);
        accelSet1.setDrawValues(false);
        accelSet1.setDrawCircles(false);
        accelSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        accelSet2 = new LineDataSet(accelEntries2, "AccelY");
        accelSet2.setColors(Color.GREEN);
        accelSet2.setDrawValues(false);
        accelSet2.setDrawCircles(false);
        accelSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        accelSet3 = new LineDataSet(accelEntries3, "AccelZ");
        accelSet3.setColors(Color.BLUE);
        accelSet3.setDrawValues(false);
        accelSet3.setDrawCircles(false);
        accelSet3.setAxisDependency(YAxis.AxisDependency.LEFT);

        accelDataSets = new ArrayList<ILineDataSet> ();
        accelDataSets.add(accelSet1);
        accelDataSets.add(accelSet2);
        accelDataSets.add(accelSet3);
        lineChart1.setData(new LineData(accelDataSets));

        YAxis y1 = lineChart1.getAxisLeft();
        y1.setAxisMaxValue(600);
        y1.setAxisMinValue(-600);
        //lineChart.animateY(5000);
    }

    private void initChart2() {
        lineChart2 = (LineChart) findViewById(R.id.chart1);

        gyroEntries1 = new ArrayList<>();
        gyroEntries2 = new ArrayList<>();
        gyroEntries3 = new ArrayList<>();
        for(int i = 0 ; i < DATA_RANGE ; i++) {
            gyroEntries1.add(new Entry(i,0f));
            gyroEntries2.add(new Entry(i,0f));
            gyroEntries3.add(new Entry(i,0f));
        }

        gyroSet1 = new LineDataSet(gyroEntries1, "GyroX");
        gyroSet1.setColors(Color.RED);
        gyroSet1.setDrawValues(false);
        gyroSet1.setDrawCircles(false);
        gyroSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        gyroSet2 = new LineDataSet(gyroEntries2, "GyroY");
        gyroSet2.setColors(Color.GREEN);
        gyroSet2.setDrawValues(false);
        gyroSet2.setDrawCircles(false);
        gyroSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        gyroSet3 = new LineDataSet(gyroEntries3, "GyroZ");
        gyroSet3.setColors(Color.BLUE);
        gyroSet3.setDrawValues(false);
        gyroSet3.setDrawCircles(false);
        gyroSet3.setAxisDependency(YAxis.AxisDependency.LEFT);

        gyroDataSets = new ArrayList<ILineDataSet> ();
        gyroDataSets.add(gyroSet1);
        gyroDataSets.add(gyroSet2);
        gyroDataSets.add(gyroSet3);
        lineChart2.setData(new LineData(gyroDataSets));

        YAxis y2 = lineChart2.getAxisLeft();
        y2.setAxisMaxValue(30);
        y2.setAxisMinValue(-30);
        //lineChart.animateY(5000);
    }


    public void updateChart(int nId) {

        if(nId == 1) { // Message id 가 0 이면
            nLineIndex1 = ++nLineIndex1 % DATA_RANGE;
            //entries.add(new Entry(n, n));
            accelEntries1.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 573)));
            accelEntries2.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 573)));
            accelEntries3.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 573)));
            //dataSet.notifyDataSetChanged();
            lineChart1.notifyDataSetChanged();
            lineChart1.invalidate();
        } else if(nId == 2) {
            nLineIndex2 = ++nLineIndex2 % DATA_RANGE;
            gyroEntries1.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 20)));
            gyroEntries2.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 20)));
            gyroEntries3.set(nLineIndex1, new Entry(nLineIndex1, (float) (Math.random() * 20)));
            lineChart2.notifyDataSetChanged();
            lineChart2.invalidate();
        }
    }

    public void updateDataChart(int nId, float f1, float f2, float f3) {

        if(nId == 1) { // Gyro Data
            nLineIndex1 = ++nLineIndex1 % DATA_RANGE;
            accelEntries1.set(nLineIndex1, new Entry(nLineIndex1, f1));
            accelEntries2.set(nLineIndex1, new Entry(nLineIndex1, f2));
            accelEntries3.set(nLineIndex1, new Entry(nLineIndex1, f3));
            lineChart1.notifyDataSetChanged();
            lineChart1.invalidate();
        } else if(nId == 2) { // Accel Data
            nLineIndex2 = ++nLineIndex2 % DATA_RANGE;
            gyroEntries1.set(nLineIndex1, new Entry(nLineIndex1, f1));
            gyroEntries2.set(nLineIndex1, new Entry(nLineIndex1, f2));
            gyroEntries3.set(nLineIndex1, new Entry(nLineIndex1, f3));
            lineChart2.notifyDataSetChanged();
            lineChart2.invalidate();
        }
    }

    public Handler updateHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1 || msg.what == 2) {
                updateChart(msg.what);
            } else {
                SensorData sd = (SensorData)msg.obj;
                updateDataChart(sd.nId, sd.f1, sd.f2, sd.f3);
            }
        }
    };

    public class updateThread extends Thread{
        int nThreadId;

        public void setId(int nValue) {
            this.nThreadId = nValue;
        }
        @Override
        public void run() {
            while(true){
                updateHandler.sendEmptyMessage(nThreadId);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void threadStart(int nId) {
        updateThread thread = new updateThread();
        thread.setId(nId);
        //thread.setDaemon(true);
        thread.start();
    }
}
