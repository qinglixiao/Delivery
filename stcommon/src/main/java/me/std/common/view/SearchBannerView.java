package me.std.common.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.std.common.R;

/**
 * Description:页面顶部搜索条
 * Author: lixiao
 * Create on: 2018/10/30.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class SearchBannerView extends LinearLayout implements View.OnClickListener {
    private View vSearchLayout;
    private EditText edtSearch;
    private ImageView imgDel;
    private TextView tvCancel;

    private OnSearchEventListener mSearchEventListener;

    public SearchBannerView(Context context) {
        super(context);
        init();
    }

    public SearchBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchBannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ((Activity) getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        vSearchLayout = LayoutInflater.from(getContext()).inflate(R.layout.view_search_bar, null);
        edtSearch = (EditText) vSearchLayout.findViewById(R.id.edt_search_txt);
        imgDel = (ImageView) vSearchLayout.findViewById(R.id.img_search_del);
        tvCancel = (TextView) vSearchLayout.findViewById(R.id.tv_cancel);
        imgDel.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mSearchEventListener != null) {
                    mSearchEventListener.OnKeyChanged(edtSearch, s.toString());
                }
            }
        });
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && mSearchEventListener != null) {
                    mSearchEventListener.OnKeyFinish(edtSearch, edtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        addView(vSearchLayout);
    }

    public void setOnSearchEventListener(OnSearchEventListener searchEventListener) {
        mSearchEventListener = searchEventListener;
    }

    public void setHintText(@StringRes int resId) {
        edtSearch.setHint(resId);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_search_del) {
            edtSearch.setText("");

        } else if (i == R.id.tv_cancel) {
            if (mSearchEventListener != null) {
                mSearchEventListener.onClose();
            }

        } else {
        }
    }

    public EditText getEdtSearch(){
        return edtSearch;
    }
    public TextView getCancelView(){return tvCancel;}

    public interface OnSearchEventListener {
        void OnKeyChanged(View view, String key);

        void OnKeyFinish(View view, String key);

        void onClose();
    }

}
