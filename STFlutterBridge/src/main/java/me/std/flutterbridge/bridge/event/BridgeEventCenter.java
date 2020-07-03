package me.std.flutterbridge.bridge.event;

import java.util.Set;

public class BridgeEventCenter extends BridgeEventSubscribeManager<BridgeEventCenter.OnEventListener> {
    public interface OnEventListener {
        void onEvent(BridgeEventHandler.Event event);
    }

    private static BridgeEventCenter instance;

    private BridgeEventCenter() {}

    public static BridgeEventCenter getInstance(){
        if (null == instance){
            instance = new BridgeEventCenter();
        }
        return instance;
    }

    public boolean emit(BridgeEventHandler.Event event) {
        if (event == null || event.name == null) return false;

        Set<OnEventListener> listeners = getSubscribers(event.name);

        if (listeners != null) {
            for (OnEventListener listener: listeners) {
                listener.onEvent(event);
            }
        }

        return true;
    }
}
