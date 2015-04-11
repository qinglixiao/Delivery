
package com.syswin.toon.bean.events;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class Deploy extends BaseBean<Deploy> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private int componentId;

    /**
     * 名片id
     */
    private long cardId;

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Override
    public Deploy parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public Deploy cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }

}
