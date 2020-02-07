package me.std.flutterbridge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.v4.app.SupportActivity;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StringCodec;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterRunArguments;
import io.flutter.view.FlutterView;

/**
 * 供native使用的flutterview
 * 此view外面多包了一层LinearLayout
 * 如果去掉这层包装需要自己做FlutterMain初始化工作
 */
public class CYFlutterView extends LinearLayout {
    private FlutterView flutterView;
    private CYFlutterChannel flutterChannel;
    private String mRoute = "/";

    public CYFlutterView(Context context) {
        super(context);
        onCreate(context);
    }

    public CYFlutterView(Context context, String initRoute) {
        super(context);
        mRoute = initRoute;
        onCreate(context);
    }

    public CYFlutterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlutterView);
        if (a.hasValue(R.styleable.FlutterView_route)) {
            mRoute = a.getString(R.styleable.FlutterView_route);
        }
        a.recycle();
        onCreate(context);
    }

    @SuppressLint("RestrictedApi")
    private void onCreate(Context context) {
        if (!(context instanceof SupportActivity)) {
            throw new Error("context not instanceof SupportActivity !!!");
        }
        SupportActivity activity = (SupportActivity) context;
        flutterView = createView(activity, activity.getLifecycle(), mRoute);
        flutterChannel = new CYFlutterChannel(context, flutterView);
        addView(flutterView);
    }

    /**
     * Creates a {@link FlutterView} linked to the specified {@link Activity} and {@link Lifecycle}.
     * The optional initial route string will be made available to the Dart code (via
     * {@code window.defaultRouteName}) and may be used to determine which widget should be displayed
     * in the view. The default initialRoute is "/".
     *
     * @param activity     an {@link Activity}
     * @param lifecycle    a {@link Lifecycle}
     * @param initialRoute an initial route {@link String}, or null
     * @return a {@link FlutterView}
     */
    @NonNull
    public static FlutterView createView(@NonNull final Activity activity, @NonNull final Lifecycle lifecycle, final String initialRoute) {
        FlutterMain.startInitialization(activity.getApplicationContext());
        FlutterMain.ensureInitializationComplete(activity.getApplicationContext(), null);
        final FlutterNativeView nativeView = new FlutterNativeView(activity);
        final FlutterView flutterView = new FlutterView(activity, null, nativeView) {
            private final BasicMessageChannel<String> lifecycleMessages = new BasicMessageChannel<>(this, "flutter/lifecycle", StringCodec.INSTANCE);

            @Override
            public void onFirstFrame() {
                super.onFirstFrame();
                setAlpha(1.0f);
            }

            @Override
            public void onPostResume() {
                // Overriding default behavior to avoid dictating system UI via PlatformPlugin.
                lifecycleMessages.send("AppLifecycleState.resumed");
            }
        };
        if (initialRoute != null) {
            flutterView.setInitialRoute(initialRoute);
        }
        lifecycle.addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            public void onCreate() {
                final FlutterRunArguments arguments = new FlutterRunArguments();
                arguments.bundlePath = FlutterMain.findAppBundlePath(activity.getApplicationContext());
                arguments.entrypoint = "main";
                flutterView.runFromBundle(arguments);
                GeneratedPluginRegistrant.registerWith(flutterView.getPluginRegistry());
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            public void onStart() {
                flutterView.onStart();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public void onResume() {
                flutterView.onPostResume();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            public void onPause() {
                flutterView.onPause();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public void onStop() {
                flutterView.onStop();
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy() {
                flutterView.destroy();
            }
        });
        flutterView.setAlpha(0.0f);
        flutterView.setZOrderOnTop(true);
        flutterView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        return flutterView;
    }

    /**
     * 关联flutter view route
     *
     * @param route
     */
    public void setRoute(String route) {
        this.mRoute = route;
        flutterView.setInitialRoute(route);
    }

    public FlutterView getFlutterView() {
        return flutterView;
    }

    /**
     * 获取flutter交互通道
     *
     * @return
     */
    public CYFlutterChannel getFlutterChannel() {
        return flutterChannel;
    }

}
