package me.std.common.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import me.std.common.R;


/**
 * Description:个人中心学历和年限选择控件
 * Author: zhangjian
 * Create on: 2018/12/1
 * Job number: 1800802
 * Phone: 18310617891
 * Email: zhangjian@chunyu.me
 */
public class ChoiceDialog extends DialogFragment {

    public static final String TYPE = "type";

    private TextView mTvOk;
    private TextView mTvCancle;
    private ListView mListView;
    private WheelView mWheelView;
    private String[] mTexts;
    private int[] mValues;
    private int mInitPos = 0;
    private Listener mListener;
    private int type;

    public static ChoiceDialog getInstance(int type) {
        ChoiceDialog dialog = new ChoiceDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        dialog.setArguments(bundle);
        return dialog;
    }


    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setTexts(String... items) {
        mTexts = items;
        mValues = new int[mTexts.length];
        for (int i = 0; i < mValues.length; i++) {
            mValues[i] = i;
        }
        mInitPos = 0;
    }

    public void setValues(int... values) {
        mValues = values;
        mInitPos = 0;
    }

    public void setRange(int left, int right, String unit) {
        int count = right - left + 1;
        mTexts = new String[count];
        mValues = new int[count];
        for (int v = left; v <= right; v++) {
            int i = v - left;
            mTexts[i] = "" + v + unit;
            mValues[i] = v;
        }
        mInitPos = 0;
    }

    public void setInitValue(int initValue) {
        mInitPos = Arrays.binarySearch(mValues, initValue);
        if (mInitPos < 0) {
            mInitPos = 0;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.cyChoiceDialogTheme);
        if (getArguments().getInt(TYPE) != 0) {
            type = getArguments().getInt(TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        View view = inflater.inflate(R.layout.layout_type_choice, container, false);
        mTvOk = view.findViewById(R.id.tv_sure);
        mTvCancle = view.findViewById(R.id.tv_cancel);
        mListView = view.findViewById(R.id.lv_type_dialog);
        mWheelView = view.findViewById(R.id.wheel_view);

        Context context = inflater.getContext();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.cell_type_choice,
                R.id.tpye_choice_text, mTexts);
        mListView.setAdapter(adapter);
        mListView.setDivider(getResources().getDrawable(R.drawable.divider));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //使用同一dialogFragment操作业务时，快速点击导致dialog还没消失但数据已经被设置了新的业务数据，
                // 可能会数组越界，所以增加判断
                if (position < mTexts.length && position < mValues.length) {
                    mListener.onItemClick(mTexts[position], mValues[position], type);
                }
                dismiss();
            }
        });

        mListView.setSelection(mInitPos);

        mWheelView.setItems(Arrays.asList(mTexts));
        mWheelView.setSeletion(mInitPos);
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.getItemValue(mWheelView.getSeletedItem(), type);
                dismiss();
            }
        });
        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    public void show(FragmentManager manager, int height) {
        String tag = getClass().getName();
        if (manager.findFragmentByTag(tag) != null) {
            return;
        }
        super.show(manager, tag);
        manager.executePendingTransactions();

        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, height);
    }


    public interface Listener {
        void onItemClick(String text, int value, int type);

        void getItemValue(String text, int type);
    }

}
