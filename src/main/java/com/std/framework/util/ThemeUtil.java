package com.std.framework.util;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.util.StateSet;

import com.std.framework.R;
import com.std.framework.basic.App;

import java.util.WeakHashMap;

/**
 * Description : 主题皮肤适配，提供标题栏元素主题样式及图片资源的主题适配
 * Created by lx on 2015/8/14.
 * Job number：137289
 * Phone ：        18611867932
 * Email：          lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class ThemeUtil {
    private static WeakHashMap<String, Drawable> cache = new WeakHashMap<>();

    public static Drawable getDrawableSelector(String pressedDrawable,
                                               String normalDrawable) {
        TouchColorChangedDrawable drawable = new TouchColorChangedDrawable();

        Drawable pressed = ResourceUtil.getDrawable(pressedDrawable);
        Drawable normal = ResourceUtil.getDrawable(normalDrawable);

        // 从皮肤中获取颜色并更改图片按下时的颜色
        int title_skin_color = getThemeColor();
        if (title_skin_color != -1) {
            drawable.setSelectedEffect(getIconColorFilter(title_skin_color));
        }

        drawable.addState(new int[]{
                android.R.attr.state_selected
        }, pressed);
        drawable.addState(new int[]{
                -android.R.attr.state_selected
        }, normal);

        return drawable;
    }

    public static Drawable getDrawableSelector(String drawableName, String pressedColor, String normalColor) {
        Drawable drawable_src = ResourceUtil.getDrawable(drawableName);
        int pressed = ResourceUtil.getColor(pressedColor);
        int normal = ResourceUtil.getColor(normalColor);
        TouchColorChangedDrawable drawable = new TouchColorChangedDrawable();
        if (pressed != -1) {
            drawable.setSelectedEffect(getIconColorFilter(pressed));
        }
        if (normal != -1) {
            drawable.setNormalEffect(getIconColorFilter(normal));
        }

        drawable.addState(new int[]{
                android.R.attr.state_selected
        }, drawable_src);

        drawable.addState(new int[]{
                -android.R.attr.state_selected
        }, drawable_src);

        drawable.addState(new int[]{}, drawable_src);
        return drawable;
    }

    public static Drawable getDrawablePressedSelector(String drawableName, String pressedColor, String normalColor) {
        Drawable drawable_src = ResourceUtil.getDrawable(drawableName);
        int pressed = ResourceUtil.getColor(pressedColor);
        int normal = ResourceUtil.getColor(normalColor);
        TouchColorChangedDrawable drawable = new TouchColorChangedDrawable();
        if (pressed != -1) {
            drawable.setPressedEffect(getIconColorFilter(pressed));
        }
        if (normal != -1) {
            drawable.setNormalEffect(getIconColorFilter(normal));
        }

        drawable.addState(new int[]{
                android.R.attr.state_pressed
        }, drawable_src);

        drawable.addState(new int[]{}, drawable_src);
        return drawable;
    }

    public static ColorStateList getColorSelector(String pressedColor,
                                                  String normalColor) {
        int pressed = ResourceUtil.getColor(pressedColor);
        int normal = ResourceUtil.getColor(normalColor);
        int[][] states = new int[2][];

        // 从皮肤中获取颜色并更改文字按下时的颜色
        int title_skin_color = getThemeColor();
        if (title_skin_color != -1) {
            pressed = title_skin_color;
        }

        int[] colors = new int[]{
                pressed, normal
        };
        states[0] = new int[]{
                android.R.attr.state_selected
        };
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    public static ColorStateList getColorCheckedSelector(String checkedColor,
                                                         String unCheckColor) {
        int checked = ResourceUtil.getColor(checkedColor);
        int normal = ResourceUtil.getColor(unCheckColor);
        int[][] states = new int[2][];

        // 从皮肤中获取颜色并更改文字按下时的颜色
        int title_skin_color = getThemeColor();
        if (title_skin_color != -1) {
            checked = title_skin_color;
        }

        int[] colors = new int[]{
                checked, normal
        };
        states[0] = new int[]{
                android.R.attr.state_checked
        };
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 获取有选中状态的颜色状态值
     *
     * @param selectedColor
     * @param normalColor
     * @return
     */
    public static ColorStateList getColorSelectedSelector(String selectedColor,
                                                          String normalColor) {
        int selected = ResourceUtil.getColor(selectedColor);
        int normal = ResourceUtil.getColor(normalColor);
        int[][] states = new int[2][];

        int[] colors = new int[]{
                selected, normal
        };
        states[0] = new int[]{
                android.R.attr.state_selected
        };
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 获取有选中状态的颜色状态值
     *
     * @param pressedColor
     * @param normalColor
     * @return
     */
    public static ColorStateList getColorPressedSelector(String pressedColor,
                                                         String normalColor) {
        int pressed = ResourceUtil.getColor(pressedColor);
        int normal = ResourceUtil.getColor(normalColor);

        int[][] states = new int[2][];
        int[] colors = new int[]{
                pressed, normal
        };
        states[0] = new int[]{
                android.R.attr.state_pressed
        };
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 弹出菜单操作栏图片selector
     *
     * @param pressedResId
     * @param normalResId
     * @return
     */
    public static Drawable getOperatorDrawableSelector(int pressedResId, int normalResId) {
        Resources res = App.instance.getResources();
        TouchColorChangedDrawable drawable = new TouchColorChangedDrawable();
        Drawable pressed = res.getDrawable(pressedResId);
        Drawable normal = res.getDrawable(normalResId);

        int title_skin_color = getThemeColor();
        if (title_skin_color != -1) {
            drawable.setPressedEffect(getIconColorFilter(title_skin_color));
        }

        // Pressed
        drawable.addState(new int[]{
                android.R.attr.state_focused, android.R.attr.state_pressed
        }, pressed);
        drawable.addState(new int[]{
                android.R.attr.state_pressed
        }, pressed);

        // Non focused states
        drawable.addState(new int[]{}, normal);

        return drawable;
    }

    /**
     * 弹出菜单操作栏文字selector
     *
     * @param highLightColor
     * @param normalColor
     * @return
     */
    public static ColorStateList getOperatorColorSelector(int highLightColor, int normalColor) {
        int title_skin_color = getThemeColor();
        if (title_skin_color != -1) {
            highLightColor = title_skin_color;
        }

        int[][] states = new int[2][];
        int[] colors = new int[]{
                highLightColor, normalColor
        };

        states[0] = new int[]{
                android.R.attr.state_pressed
        };
        states[1] = new int[]{};

        ColorStateList csl = new ColorStateList(states, colors);
        return csl;
    }

    /**
     * 获取适配皮肤的图片
     *
     * @param drawableName
     * @param colorName
     * @return
     */
    public static Drawable getDrawableWithColor(String drawableName, String colorName) {
        Drawable drawable = ResourceUtil.getDrawable(drawableName);
        if (drawable != null)
            drawable.setColorFilter(getIconColorFilter(ResourceUtil.getColor(colorName)));
        return drawable;
    }

    /**
     * 获取适配皮肤的图片
     *
     * @param drawable
     * @param colorName
     * @return
     */
    public static Drawable getDrawableWithColor(Drawable drawable, String colorName) {
        if (drawable != null)
            drawable.setColorFilter(getIconColorFilter(ResourceUtil.getColor(colorName)));
        return drawable;
    }

    /**
     * 获取适配皮肤的图片
     *
     * @param drawable
     * @param color
     * @return
     */
    public static Drawable getDrawableWithColor(Drawable drawable, int color) {
        if (drawable != null)
            drawable.setColorFilter(getIconColorFilter(color));
        return drawable;
    }

    /**
     * 获取皮肤颜色
     *
     * @return
     */
    public static int getThemeColor() {
        return ResourceUtil.getColor("topic");
    }

    /**
     * 获取tab页标签颜色
     *
     * @return
     */
    public static ColorStateList getTabTextSelector() {
        return getColorSelector("topic", "topic_reverse");
    }

    /**
     * 获取标题栏tab页文本颜色
     *
     * @return
     */
    public static ColorStateList getTitleTabTextSelector() {
        return getColorSelectedSelector("title_bar_txt_selected_color", "title_bar_txt_color");
    }

    /**
     * 获取标题栏背景颜色
     *
     * @return
     */
    public static int getTitleBgColor() {
        return ResourceUtil.getColor("title_bar_bg");
    }

    /**
     * 标题栏左侧文字颜色
     *
     * @return
     */
    public static int getTitleBarLeftTxtColor() {
        return ResourceUtil.getColor("title_bar_left_txt_color");
    }

    /**
     * 标题栏返回图标
     *
     * @return
     */
    public static Drawable getTitleBarBackIcon() {
        return getDrawableWithColor("common_goback_white", "title_bar_left_icon_color");
    }

    /**
     * 标题文字颜色
     *
     * @return
     */
    public static int getTitleTxtColor() {
        try {
            return ResourceUtil.getColor("title_bar_txt_color");
        } catch (Exception ex) {
            return App.instance.getResources().getColor(R.color.c19);
        }
    }

    /**
     * 标题栏返回文字颜色
     *
     * @return
     */
    public static int getTitleBarBackTxtColor() {
        return ResourceUtil.getColor("title_bar_back_txt_color");
    }

    /**
     * 标题栏右侧文字颜色
     *
     * @return
     */
    public static int getTitleBarRightTxtColor() {
        return ResourceUtil.getColor("title_bar_right_txt_color");
    }

    /**
     * 右侧图标颜色
     *
     * @return
     */
    public static int getTitleBarRightIconColor() {
        return ResourceUtil.getColor("title_bar_right_icon_color");
    }

    /**
     * 右侧图标选中时颜色
     *
     * @return
     */
    public static int getTitleBarRightSelectedIconColor() {
        return ResourceUtil.getColor("title_bar_right_selected_color");
    }

    /**
     * 名片选择区颜色
     *
     * @return
     */
    public static ColorStateList getChooseCardSelector() {
        return getColorSelectedSelector("control_choose_card_txt_selected_color", "control_choose_card_txt_normal_color");
    }

    /**
     * all图标图像
     *
     * @return
     */
    public static Drawable getChooseCardAllIconDrawable() {
        if (!cache.containsKey("getChooseCardAllIconDrawable")) {
            cache.put("getChooseCardAllIconDrawable", getDrawableSelector("common_all_normal", "control_choose_card_all_pressed_color", "control_choose_card_all_color"));
        }
        return cache.get("getChooseCardAllIconDrawable");
    }

    /**
     * add图标图像
     *
     * @return
     */
    public static Drawable getChooseCardAddIconDrawable() {
        if (!cache.containsKey("getChooseCardAddIconDrawable")) {
            cache.put("getChooseCardAddIconDrawable", getDrawableSelector("common_add_normal", "control_choose_card_add_pressed_color", "control_choose_card_add_color"));
        }
        return cache.get("getChooseCardAddIconDrawable");
    }

    /**
     * communication_member_add图标图像
     *
     * @return
     */
    public static Drawable getCardAddIconDrawable() {
        if (!cache.containsKey("getCardAddIconDrawable")) {
            cache.put("getCardAddIconDrawable", getDrawableSelector("communication_member_add", "control_choose_card_add_pressed_color", "control_choose_card_add_color"));
        }
        return cache.get("getCardAddIconDrawable");
    }

    /**
     * 名片头像选中时颜色
     *
     * @return
     */
    public static int getChooseCardSelectedColor() {
        return ResourceUtil.getColor("control_choose_card_frame_selected_color");
    }

    /**
     * 名片头像正常状态颜色
     *
     * @return
     */
    public static int getChooseCardNormalColor() {
        return ResourceUtil.getColor("control_choose_card_frame_normal_color");
    }

    /**
     * 获取颜色值
     *
     * @param colorName 颜色名称
     * @return
     */
    public static int getColor(String colorName) {
        return ResourceUtil.getColor(colorName);
    }

    /**
     * 右上角all图标
     *
     * @return
     */
    public static Drawable getTitleBarRightAllIconDrawable() {
        return getDrawableWithColor("common_all_normal", "title_bar_right_icon_color");
    }

    public static PorterDuffColorFilter getIconColorFilter(int color) {
        PorterDuffColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return filter;
    }

    static class TouchColorChangedDrawable extends StateListDrawable {
        /**
         * 图标按下时颜色混合器
         */
        private ColorFilter pressed_color_filter = null;

        /**
         * 图标被选中时颜色混合器
         */
        private ColorFilter selected_color_filter = null;

        /**
         * 图标正常状态时颜色混合器
         */
        private ColorFilter normal_color_filter = null;

        @Override
        public boolean onStateChange(int[] stateSet) {
            String state = StateSet.dump(stateSet);
            if (state.trim().endsWith("S")) {// 选中状态
                applySelectedEffect();
            } else if (state.trim().endsWith("P")) {// 按下状态
                applyPressedEffect();
            } else {
                applyNormalEffect();
            }
            return super.onStateChange(stateSet);
        }

        private void applyPressedEffect() {
            if (pressed_color_filter != null) {
                setColorFilter(pressed_color_filter);
            }
        }

        private void applySelectedEffect() {
            if (selected_color_filter != null) {
                setColorFilter(selected_color_filter);
            }
        }

        private void applyNormalEffect() {
            if (normal_color_filter != null) {
                setColorFilter(normal_color_filter);
            } else {
                setColorFilter(getIconColorFilter(0));
            }
        }

        public void setPressedEffect(ColorFilter filter) {
            pressed_color_filter = filter;
        }

        public void setSelectedEffect(ColorFilter filter) {
            selected_color_filter = filter;
        }

        public void setNormalEffect(ColorFilter filter) {
            normal_color_filter = filter;
        }

    }
}
