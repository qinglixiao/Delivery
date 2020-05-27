//package me.std.flutterbridge;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//
//import io.flutter.plugin.common.PluginRegistry;
//
//public class CYFlutterFragment extends Fragment implements PluginRegistry {
//    public static final String ARG_ROUTE = "route";
//    CYFlutterView cyFlutterView;
//    CYFlutterChannel mChannel;
//    String mRoute = "";
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mRoute = getArguments().getString(ARG_ROUTE);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        cyFlutterView = new CYFlutterView(getContext(), getRoute());
//        mChannel = cyFlutterView.getFlutterChannel();
//        return cyFlutterView;
//    }
//
//    protected String getRoute() {
//        return TextUtils.isEmpty(mRoute) ? "/" : mRoute;
//    }
//
//    public void setRoute(String route) {
//        this.mRoute = route;
//        if (cyFlutterView != null) {
//            cyFlutterView.setRoute(route);
//        }
//    }
//
//    public CYFlutterView getCYFlutterView() {
//        return cyFlutterView;
//    }
//
//    @Override
//    public Registrar registrarFor(String pluginKey) {
//        return cyFlutterView.getFlutterView().getPluginRegistry().registrarFor(pluginKey);
//    }
//
//    @Override
//    public boolean hasPlugin(String pluginKey) {
//        return cyFlutterView.getFlutterView().getPluginRegistry().hasPlugin(pluginKey);
//    }
//
//    @Override
//    public <T> T valuePublishedByPlugin(String pluginKey) {
//        return cyFlutterView.getFlutterView().getPluginRegistry().valuePublishedByPlugin(pluginKey);
//    }
//}
