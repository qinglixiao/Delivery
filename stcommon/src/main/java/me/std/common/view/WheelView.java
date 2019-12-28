package me.std.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.std.common.utils.DimenUtil;

/**
 * Description:
 * Author: zhangjian
 * Create on: 2018/12/12
 * Job number: 1800802
 * Phone: 18611867932
 * Email: zhangjian@chunyu.me
 */
public class WheelView extends ScrollView {

    public static final int OFF_SET_DEFAULT = 1;
    private Paint paint;
    private int viewWidth;
    /**
     * 偏移量（需要在最前面和最后面补全）
     */
    private int offset = OFF_SET_DEFAULT;
    private Context context;
    private LinearLayout views;
    private int initialY;
    private int itemHeight = 0;
    /**
     * 获取选中区域的边界
     */
    private int[] selectedAreaBorder;
    public List<String> items;
    public Runnable scrollerTask;
    int newCheck = 50;
    /**
     * 每页显示的数量
     */
    public int displayItemCount;
    public int selectedIndex = 1;

    public WheelView(Context context) {
        super(context);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private List<String> getItems() {
        return items;
    }

    public void setItems(List<String> list) {
        if (null == items) {
            items = new ArrayList<>();
        }
        items.clear();
        items.addAll(list);

        // 前面和后面补全
        for (int i = 0; i < offset; i++) {
            items.add(0, "");
            items.add("");
            items.add("");
        }
        initData();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    private void init(Context context) {
        this.context = context;
        this.setVerticalScrollBarEnabled(false);

        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);

        scrollerTask = new Runnable() {
            @Override
            public void run() {
                int newY = getScrollY();
                // stopped
                if (initialY - newY == 0) {
                    final int remainder = initialY % itemHeight;
                    final int divided = initialY / itemHeight;
                    if (remainder == 0) {
                        selectedIndex = divided + offset;
                    } else {
                        if (remainder > itemHeight / 2) {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollTo(0, initialY - remainder + itemHeight);
                                    selectedIndex = divided + offset + 1;
                                }
                            });
                        } else {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollTo(0, initialY - remainder);
                                    selectedIndex = divided + offset;
                                }
                            });
                        }
                    }
                } else {
                    initialY = getScrollY();
                    WheelView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };


    }

    public void startScrollerTask() {
        initialY = getScrollY();
        this.postDelayed(scrollerTask, newCheck);
    }

    private void initData() {
        displayItemCount = offset * 3 + 1;
        for (String item : items) {
            views.addView(createView(item));
        }
        refreshItemView(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
    }

    private void refreshItemView(int y) {
        int position = y / itemHeight + offset;
        int remainder = y % itemHeight;
        int divided = y / itemHeight;

        if (remainder == 0) {
            position = divided + offset;
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }
        int childSize = views.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) views.getChildAt(i);
            if (null == itemView) {
                return;
            }
            if (position == i) {
                itemView.setTextColor(Color.parseColor("#666666"));
            } else {
                itemView.setTextColor(Color.parseColor("#bbbbbb"));
            }
        }
    }

    /**
     * 设置高度
     */
    private int[] obtainSelectedAreaBorder() {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        if (viewWidth == 0) {
            viewWidth = DimenUtil.getDisplayWidth();
        }
        if (null == paint) {
            paint = new Paint();
            paint.setColor(Color.parseColor("#bbbbbb"));
            paint.setStrokeWidth(DimenUtil.dpToPx(1f));
        }
        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawLine(0,
                        obtainSelectedAreaBorder()[0],
                        viewWidth,
                        obtainSelectedAreaBorder()[0],
                        paint);
                canvas.drawLine(0,
                        obtainSelectedAreaBorder()[1],
                        viewWidth,
                        obtainSelectedAreaBorder()[1],
                        paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        super.setBackgroundDrawable(background);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        setBackgroundDrawable(null);
    }

    public void setSeletion(int position) {
        final int p = position;
        selectedIndex = p + offset;
        this.post(new Runnable() {
            @Override
            public void run() {
                WheelView.this.smoothScrollTo(0, p * itemHeight);
            }
        });

    }

    public String getSeletedItem() {
        return items.get(selectedIndex);
    }

    public int getSeletedIndex() {
        return selectedIndex - offset;
    }


    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }


    private TextView createView(String item) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DimenUtil.dpToPx(50f)));
        tv.setSingleLine(true);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setText(item);
        tv.setGravity(Gravity.CENTER);
        int padding = (int) DimenUtil.dpToPx(5f);
        tv.setPadding(padding, padding, padding, padding);
        if (0 == itemHeight) {
            itemHeight = (int) DimenUtil.dpToPx(50f);
            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
        }
        return tv;
    }

}

