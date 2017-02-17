package stephania.com.reddit.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import stephania.com.reddit.R;

/**
 * Utilidad animacion
 *
 * @author <a href="stephania-moreno@gmail.com">Stephania Moreno V.</a>
 */
public class AnimacionUtil {

    /**
     * Animación para fade
     *
     * @param view
     *         Vista a animar
     */
    public static void animacionFade(View view, int delay, int duration) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 0.0F, 1.0F);
        alphaAnimation.setStartDelay(delay);
        alphaAnimation.setDuration(duration);
        alphaAnimation.start();
    }

    /**
     * Este método ajusta las transiciones necesarias únicamente cuando es Lollipop
     */
    public static void ajustarTransiciones(Activity activity, ImageView mLogo, View mHeader) {
        int delay = 1000;
        int duration = 500;
        animacionFade(mLogo, delay, duration);
        animacionFade(mHeader, delay, duration);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        activity.getWindow().setEnterTransition(null);
    }

    /**
     * Set the animation of the holder o the list
     *
     * @param viewToAnimate
     * @param position
     */
    public static void setAnimationleft(View viewToAnimate, int position, Context mContext) {
        if (position > ConstantUtil.LAST_POSITION) {
            Animation animation = android.view.animation.AnimationUtils.loadAnimation(mContext,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            ConstantUtil.LAST_POSITION = position;
        }
    }

    /**
     * Set the animation of the holder o the list
     *
     * @param viewToAnimate
     * @param position
     */
    public static void setAnimationTrans(View viewToAnimate, int position, Context mContext) {
        if (position > ConstantUtil.LAST_POSITION) {
            Animation animation = android.view.animation.AnimationUtils.loadAnimation(mContext,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            ConstantUtil.LAST_POSITION = position;
        }
    }

    /**
     * Configura la animacion para la lista y la flecha
     *
     * @param view
     *         item a animar
     */
    public static void configurarAnimacion(final Context context, final View view, boolean out,
                                           final Intent intent) {
        int animId = R.anim.anim_scale_out;
        int visibility = View.VISIBLE;

        if (!out) {
            animId = R.anim.anim_scale_in;
        }

        Animation anim = android.view.animation.AnimationUtils.loadAnimation(context, animId);
        final int finalVisibility = visibility;
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                context.startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(anim);
    }
}
