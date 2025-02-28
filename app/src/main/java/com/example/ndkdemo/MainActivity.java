package com.example.ndkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ndkdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.

    private static final String TAG = "MainActivityTag";
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnTestNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.sampleText.setText(stringFromJNI());
            }
        });


    }

    // 声明 JNI 方法
    public native long testNativeHeap();
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public static native void ginsengTest(short s, int i, long l, float f, double d, char c,
                                          boolean z, byte b, String str, Object obj, MainActivity p, int[] arr);

    //c++ 操作字符串
    public native String operateString(String str);


    //c++ 数组求和
    public native int sumArray(int[] array);

    //native 创建数组并返回给 java
    public native int[][] init2DArray(int size);


    //c++ 调用静态方法，有入参有返回值
    public native String callStaticMethod(String str);


    //c++ 调用实例方法
    public native void callAndCreateIntanceMethod();

    //测试 c++ 崩溃
    public native void testNativeCrash();

}