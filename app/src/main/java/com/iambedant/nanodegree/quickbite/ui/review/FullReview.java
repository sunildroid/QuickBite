package com.iambedant.nanodegree.quickbite.ui.review;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iambedant.nanodegree.quickbite.R;
import com.iambedant.nanodegree.quickbite.data.model.Reviews.Review;
import com.iambedant.nanodegree.quickbite.ui.base.BaseActivity;
import com.iambedant.nanodegree.quickbite.util.Constants;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FullReview extends BaseActivity implements ReviewMvpView {

    @Inject
    ReviewPrsenter mReviewPresenter;
    Context mContext;
    Review mReview;


    @Bind(R.id.tv_name)
    TextView mTextViewName;

    @Bind(R.id.tv_foodie_level)
    TextView mTextViewFoodieLevel;

    @Bind(R.id.tv_review)
    TextView mTextViewReview;

    @Bind(R.id.btn_view_profile)
    Button mButtonProfile;

    @Bind(R.id.btn_view_profile_fake)
    Button mButtonProfileFake;

    @Bind(R.id.iv_profile_pic)
    ImageView mImageViewProfile;

    Activity host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_review);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mContext = this;
        host = this;
        if (getIntent().hasExtra(Constants.CURRENT_REVIEW)) {
            mReview = getIntent().getParcelableExtra(Constants.CURRENT_REVIEW);
        }
        mReviewPresenter.attachView(this);
        bindUi();


    }

    public void bindUi() {
        Glide.with(host)
                .load(mReview.getUser().getProfileImage())
                .override(480,400)
                .into(mImageViewProfile);

        //todo: Add a slide animation to  Review Content

        mTextViewName.setText(mReview.getUser().getName());
        mTextViewFoodieLevel.setText(mReview.getUser().getFoodieLevel());
        mTextViewReview.setText(mReview.getReviewText());


        mButtonProfileFake.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mButtonProfile.dispatchTouchEvent(event);
            }
        });

    }

    @OnClick(R.id.btn_view_profile)
    public void openReviewerProfile(){
        Toast.makeText(mContext,"This is from Click event",Toast.LENGTH_SHORT).show();
    }
//    @OnTouch(R.id.btn_view_profile)
//    public void openReviewProfileTouch(){
//        Toast.makeText(mContext,"This is from touch event",Toast.LENGTH_SHORT).show();
//    }
}