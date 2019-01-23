package com.c4k.emnumbers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

class AnimationClass
{
   static void Animate(RecyclerView.ViewHolder holder, boolean b)
   {
       ObjectAnimator objanimY;
       AnimatorSet animset = new AnimatorSet();

       objanimY = ObjectAnimator.ofFloat(holder.itemView, "translationY", b ? 300 : -250, 0);
       objanimY.setDuration(600);
       animset.playTogether(objanimY);
       animset.start();
   }
}
