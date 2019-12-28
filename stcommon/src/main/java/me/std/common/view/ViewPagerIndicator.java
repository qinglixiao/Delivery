package me.std.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import me.std.common.R;

/**
 * Description:下划线indicator
 * Author: xuye
 * Modify: zhangjian
 * Create on: 2019/1/23
 * Job number: 1800802
 * Phone: 18310617891
 * Email: zhangjian@chunyu.me
 */
public class ViewPagerIndicator extends LinearLayout {
    /**
     * 绘制下划线的画笔
     */
    private Paint mPaint;

    /**
     * path构成一个下划线矩形
     */
    private Path mPath;

    /**
     * 下划线的宽度
     */
    private int mUnderlineWidth;

    /**
     * 下划线的高度
     */
    private int mUnderlineHeight;

    /**
     * 下划线的宽度为单个Tab的y/x
     */
    private static final float RADIO_TRIANGEL = 2.0f / 3;

    /**
     * 下划线的最大宽度
     */
    private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 3 * RADIO_TRIANGEL);

    /**
     * 初始时，下划线指示器的偏移量
     */
    private int mInitTranslationX;

    /**
     * 手指滑动时的偏移量
     */
    private float mTranslationX;

    /**
     * 默认的Tab数量
     */
    private static final int COUNT_DEFAULT_TAB = 3;

    /**
     * tab数量
     */
    private int mTabVisibleCount = COUNT_DEFAULT_TAB;

    /**
     * tab文字选中时颜色
     */
    private int mTabSelectedTextColor;

    /**
     * tab文字未选中时颜色
     */
    private int mTabDefaultTextColor;

    /**
     * tab文字默认颜色默认值，白色
     */
    private static final int COLOR_TEXT_NORMAL = R.color.A3;

    /**
     * tab文字选中时颜色默认值，蓝色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = R.color.indicator_line;

    /**
     * tab下划线颜色
     */
    private int mTabIndicatorColor;

    /**
     * tab文字大小
     */
    private int mTabTextSize;

    /**
     * tab文字默认大小16sp
     */
    private static final int SIZE_TEXT_DEFAULT = 16;

    /**
     * tab上的内容
     */
    private List<String> mTabTitles;

    /**
     * 与之绑定的ViewPager
     */
    public ViewPager mViewPager;

    private Context mContext;

    /**
     * 对外的ViewPager的回调接口
     */
    private PageChangeListener onPageChangeListener;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_item_count, COUNT_DEFAULT_TAB);
        mTabDefaultTextColor = a.getInt(R.styleable.ViewPagerIndicator_item_default_text_color, COLOR_TEXT_NORMAL);
        mTabSelectedTextColor = a.getInt(R.styleable.ViewPagerIndicator_item_selected_text_color, COLOR_TEXT_HIGHLIGHTCOLOR);
        mTabIndicatorColor = a.getInt(R.styleable.ViewPagerIndicator_item_indicator_color, COLOR_TEXT_HIGHLIGHTCOLOR);
        mTabTextSize = a.getInt(R.styleable.ViewPagerIndicator_item_indicator_text_size, SIZE_TEXT_DEFAULT);

        if (mTabVisibleCount < 0) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }
        a.recycle();

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(mTabIndicatorColor));
        mPaint.setStyle(Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));

    }

    // 对外的ViewPager的回调接口的设置
    public void setOnPageChangeListener(PageChangeListener pageChangeListener) {
        this.onPageChangeListener = pageChangeListener;
    }

    /**
     * 绘制指示器
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        // 画笔平移到正确的位置
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    /**
     * 初始化下滑线的宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mUnderlineWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);
        // width
        mUnderlineWidth = Math.min(DIMENSION_TRIANGEL_WIDTH, mUnderlineWidth);
        // 初始化下划线
        initUnderline();
        // 初始时的偏移量
        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mUnderlineWidth / 2;
    }

    /**
     * 设置可见的tab的数量
     */
    public void setVisibleTabCount(int count) {
        this.mTabVisibleCount = count;
    }

    /**
     * 设置tab的标题内容 可选，可以自己在布局文件中写死
     */
    public void setTabItemTitles(List<String> datas) {
        // 如果传入的list有值，则移除布局文件中设置的view
        if (datas != null && datas.size() > 0) {
            this.removeAllViews();
            this.mTabTitles = datas;

            for (String title : mTabTitles) {
                // 添加view
                addView(generateTextView(title));
            }
            // 设置item的click事件
            setItemClickEvent();
        }

    }

    /**
     * 设置关联的ViewPager
     */
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 设置字体颜色高亮
                resetTextViewColor();
                highLightTextView(position);
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 滚动
                scroll(position, positionOffset);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position,
                            positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
        // 设置当前页
        mViewPager.setCurrentItem(pos);
        // 高亮
        highLightTextView(pos);
    }

    /**
     * 高亮文本
     */
    protected void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(mContext.getResources().getColor(COLOR_TEXT_HIGHLIGHTCOLOR));
        }

    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(mContext.getResources().getColor(COLOR_TEXT_NORMAL));
            }
        }
    }

    /**
     * 设置点击事件
     */
    public void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

    /**
     * 根据标题生成我们的TextView
     */
    private TextView generateTextView(String text) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(mContext.getResources().getColor(COLOR_TEXT_NORMAL));
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabTextSize);
        tv.setLayoutParams(lp);
        return tv;
    }

    /**
     * 初始化下划线指示器
     */
    private void initUnderline() {
        mPath = new Path();
        mUnderlineHeight = 5;
        mPath.moveTo(0, 0);
        mPath.lineTo(mUnderlineWidth, 0);
        mPath.lineTo(mUnderlineWidth, -mUnderlineHeight);
        mPath.lineTo(0, -mUnderlineHeight);
        mPath.close();
    }

    /**
     * 指示器跟随手指滚动，以及容器滚动
     */
    public void scroll(int position, float offset) {
        // 不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);
        int tabWidth = getScreenWidth() / mTabVisibleCount;
        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2)
                && getChildCount() > mTabVisibleCount) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else {
                // 为count为1时 的特殊处理
                this.scrollTo(
                        position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }
        invalidate();
    }

    /**
     * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount == 0) {
            return;
        }
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view
                    .getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }
        // 设置点击事件
        setItemClickEvent();
    }

    /**
     * 获得屏幕的宽度
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 对外的ViewPager的回调接口
     */
    public interface PageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

}