
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

public class ToonFrame implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

//    public long timeStamp;

    public List<ShowBlock> showBlocks;

    public List<PluginItem> pluginItems;

    public ToonFrame() {
    }

    public void read(String targetType, long targetId, String frameName, String token, long cardId) {
    }

    public void insertPlugin(PluginItem item) {

    }

    public void insertShowBlock(ShowBlock block) {

    }

    public void deletePlugin(PluginItem item) {

    }

    public void deleteShowBlock(ShowBlock block) {

    }

    public void updatePlugin(PluginItem item) {

    }

    public void updateShowBlock(ShowBlock block) {

    }

}
