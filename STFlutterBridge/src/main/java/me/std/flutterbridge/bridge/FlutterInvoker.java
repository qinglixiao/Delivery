package me.std.flutterbridge.bridge;

import androidx.annotation.Nullable;

import io.flutter.plugin.common.MethodChannel;

/*
 * invoke flutter method
 */
public class FlutterInvoker {
    public interface SimpleCallback {
        void onResult(Boolean completed);
    }

    public interface Callback {
        void onResult(Object object, Error error);
    }

    private MethodChannel methodChannel;

    public FlutterInvoker(MethodChannel methodChannel) {
        this.methodChannel = methodChannel;
    }

    // flutter导航的返回
    public void onBack(final SimpleCallback callback) {
        invoke("pop", null, new Callback() {
            @Override
            public void onResult(Object object, Error error) {
                if (callback != null) {
                    callback.onResult("completed".equals(object));
                }
            }
        });
    }

    public void invoke(final String method, Object parameters, final Callback callback) {
        methodChannel.invokeMethod(method, parameters, new MethodChannel.Result() {
            @Override
            public void success(@Nullable Object o) {
                if (callback != null) {
                    callback.onResult(o, null);
                }
            }

            @Override
            public void error(String s, @Nullable String s1, @Nullable Object o) {
                if (callback != null) {
                    callback.onResult(null, new Error(s1));
                }
            }

            @Override
            public void notImplemented() {
                if (callback != null) {
                    callback.onResult(null, new Error(String.format("`%s` not implemented in flutter side", method)));
                }
            }
        });
    }
}
