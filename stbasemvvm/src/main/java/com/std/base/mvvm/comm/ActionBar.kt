package com.std.base.mvvm.comm

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.std.base.mvvm.R
import com.std.base.mvvm.extension.*

/**
 * Description:标题栏
 * Author: lixiao
 * Create on: 2020/5/8.
 */
class ActionBar(
        title: String? = String.empty(),
        leftText: String? = String.empty(),
        leftIcon: Drawable? = null,
        leftIcon2: Drawable? = null,
        rightText: String? = String.empty(),
        rightIcon: Drawable? = null,
        titleCustomer: View? = null
) {
    private var header: View
    private var leftTextView: TextView
    private var leftImageView: ImageView
    private var leftImageView2: ImageView
    private var titleTextView: TextView
    private var rightTextView: TextView
    private var rightImageView: ImageView
    private var titleCustomerContainer: LinearLayout

    init {
        header = AppContext.context.inflate(R.layout.action_bar)
        leftTextView = header.findViewById(R.id.tv_left)
        leftImageView = header.findViewById(R.id.icon_left)
        leftImageView2 = header.findViewById(R.id.icon_left_2)
        titleTextView = header.findViewById(R.id.tv_title)
        rightTextView = header.findViewById(R.id.tv_right)
        rightImageView = header.findViewById(R.id.icon_right)
        titleCustomerContainer = header.findViewById(R.id.ll_customer_title)

        leftTextView.setNotNullText(leftText)
        titleTextView.setNotNullText(title)
        rightTextView.setNotNullText(rightText)

        leftIcon?.let {
            leftImageView.setImageDrawable(leftIcon)
        }
        leftIcon2?.let {
            leftImageView2.setImageDrawable(leftIcon2)
        }
        rightIcon?.let {
            rightImageView.setImageDrawable(rightIcon)
        }
        titleCustomer?.let {
            titleCustomerContainer.addView(titleCustomer, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            titleCustomerContainer.visible()
            titleTextView.gone()
        }
    }

    fun onLeftBackClick(onClickListener: View.OnClickListener?): ActionBar {
        leftTextView.setOnClickListener(onClickListener)
        return this
    }

    fun onRightClick(onClickListener: View.OnClickListener?): ActionBar {
        rightTextView.setOnClickListener(onClickListener)
        return this
    }

    fun onLeftIconClick(onClickListener: View.OnClickListener?): ActionBar {
        leftImageView.setOnClickListener(onClickListener)
        return this
    }

    fun onLeftIcon2Click(onClickListener: View.OnClickListener?): ActionBar {
        leftImageView2.setOnClickListener(onClickListener)
        return this
    }

    fun onRightIconClick(onClickListener: View.OnClickListener?): ActionBar {
        rightImageView.setOnClickListener(onClickListener)
        return this
    }

    fun getLeftBackView(): TextView {
        return leftTextView
    }

    fun getTitleView(): TextView {
        return titleTextView
    }

    fun getRightView(): TextView {
        return rightTextView
    }

    fun getTitleCustomerView(): ViewGroup {
        return titleCustomerContainer
    }

    fun gone() {
        header.gone()
    }

    fun get(): View {
        return header
    }

    class Builder {
        private var title: String? = String.empty()
        private var rightText: String? = String.empty()
        private var rightIcon: Drawable? = null
        private var leftText: String? = String.empty()
        private var leftIcon: Drawable? = null
        private var leftIcon2: Drawable? = null
        private var leftClick: View.OnClickListener? = null
        private var rightClick: View.OnClickListener? = null
        private var rightIconClick: View.OnClickListener? = null
        private var leftIconClick: View.OnClickListener? = null
        private var leftIcon2Click: View.OnClickListener? = null
        private var titleCustomerView: View? = null

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun leftText(text: String, click: View.OnClickListener): Builder {
            leftClick = click
            leftText = text
            return this
        }

        fun leftIcon(@DrawableRes resId: Int, click: View.OnClickListener): Builder {
            leftIconClick = click
            leftIcon = AppContext.context.resources.getDrawable(resId)
            return this
        }

        fun leftIcon2(@DrawableRes resId: Int, click: View.OnClickListener): Builder {
            leftIcon2Click = click
            leftIcon2 = AppContext.context.resources.getDrawable(resId)
            return this
        }

        fun rightText(text: String, click: View.OnClickListener): Builder {
            rightText = text
            rightClick = click
            return this
        }

        fun rightIcon(@DrawableRes resId: Int, click: View.OnClickListener): Builder {
            rightIcon = AppContext.context.resources.getDrawable(resId)
            rightIconClick = click
            return this
        }

        fun titleCustomer(titleView: View): Builder {
            titleCustomerView = titleView
            return this
        }

        fun build(): ActionBar {
            return ActionBar(title, leftText, leftIcon, leftIcon2, rightText, rightIcon, titleCustomerView)
                    .onLeftBackClick(leftClick)
                    .onRightClick(rightClick)
                    .onLeftIconClick(leftIconClick)
                    .onLeftIcon2Click(leftIcon2Click)
                    .onRightIconClick(rightIconClick)
        }
    }
}