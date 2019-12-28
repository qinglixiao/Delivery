//package me.std.base.widget.listview.model;
//
//import android.content.Context;
//
//import java.util.Map;
//
///**
// * Created by Roger Huang on 2019/1/23.
// */
//
//public class ModelRequest extends CYRequest implements IModelRequest {
//    public ModelRequest(Context context, String url, Map<String, String> parameters, G7HttpMethod method) {
//        setContext(context);
//        setUrl(url);
//        setParameters(parameters);
//        setMethod(method);
//    }
//
//    public ModelRequest(Context context, String url, Map<String, String> parameters) {
//        this(context, url, parameters, G7HttpMethod.GET);
//    }
//
//    @Override
//    public void start(final IModelRequestCallback callback) {
//        request(new CYRequestCallback() {
//            @Override
//            public void onResult(Object result, Error error) {
//                if (callback != null) {
//                    callback.onResult((String)result, error);
//                }
//            }
//        });
//    }
//}
