package com.tianxing.ui.fragment.child.teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;
import com.tianxing.fscteachersedition.R;
import com.tianxing.ui.fragment.child.AssignmentReplyView;
import com.tianxing.ui.fragment.child.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by tianxing on 16/7/28.
 */
public class AssignmentReplyFragment extends BaseBackFragment implements AssignmentReplyView, View.OnClickListener{


    private Unbinder unbinder;
    @BindView(R.id.toolbar_child_Fragment)
    Toolbar toolbar;
    @BindView(R.id.imageButton_icon)
    ImageButton icon;
    @BindView(R.id.checkBox1)
    CheckBox checkBox1;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.editText_remark)
    EditText remark;


    private int score = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_reply, container, false);
        unbinder = ButterKnife.bind(this, view);
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
        toolBarInit(toolbar);
        setToolBarTitle("批阅");
        Picasso.with(getContext()).load(R.mipmap.user_icon_40dp).transform(new CropCircleTransformation()).into(icon);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == checkBox1){
            checkBox1.setChecked(true);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
            checkBox5.setChecked(false);
            score = 1;
        }else if (v == checkBox2){
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
            checkBox5.setChecked(false);
            score = 2;
        }else if (v == checkBox3){
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
            checkBox3.setChecked(true);
            checkBox4.setChecked(false);
            checkBox5.setChecked(false);
            score = 3;
        }else if (v == checkBox4){
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
            checkBox3.setChecked(true);
            checkBox4.setChecked(true);
            checkBox5.setChecked(false);
            score = 4;
        }else if (v == checkBox5){
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
            checkBox3.setChecked(true);
            checkBox4.setChecked(true);
            checkBox5.setChecked(true);
            score = 5;
        }
    }
}
