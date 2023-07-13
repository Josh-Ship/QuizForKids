package com.Joshua.quizforkids.MyAnimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class MyAnimator {

    public static void shakeAnimationOnView(View v){
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator rotateRight = ObjectAnimator.ofFloat(v, "rotation", 20f);
        rotateRight.setDuration(100l);
        ObjectAnimator rotateBack = ObjectAnimator.ofFloat(v, "rotation", 0f);
        rotateBack.setDuration(100l);
        ObjectAnimator rotateLeft = ObjectAnimator.ofFloat(v, "rotation", -20f);
        rotateBack.setDuration(100l);

        animationSet.playSequentially(rotateRight, rotateLeft, rotateBack);
        animationSet.start();
    }
}
