package stephania.com.reddit.activites;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import stephania.com.reddit.R;
import stephania.com.reddit.utils.ConstantUtil;

/**
 * Actividad Splash
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends RoboActivity {

    /** ImageView que contiene el logo **/
    @InjectView(R.id.logo_iv)
    private ImageView mLogoIv;

    /** Transition Name **/
    @InjectResource(R.string.transition_view)
    private String mTransitionView;

    /** Variable de tipo **/
    private ActivityOptionsCompat options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeAnimation(mLogoIv);

        //Hilo que lanza el metodo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goListUser();
            }
        }, ConstantUtil.SPLASH_DELAY);
    }

    /**
     * Este metodo inicializa el evento de la actividad
     */
    private void goListUser() {
        Intent loginIntent = new Intent(SplashActivity.this, CategoriesActivity.class);
        options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mLogoIv, mTransitionView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Pair<View, String>[] transitionPairs = new Pair[1];
            transitionPairs[0] = Pair.create((View) mLogoIv, getString(R.string.transition_splash));
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,transitionPairs);
            startActivity(loginIntent, options.toBundle());
        }else{
            startActivity(loginIntent);
        }
    }

    /**
     * Metodo que crea el objeto de animacion
     * animated and the initial value and final value
     *
     * @param view
     *         Target view
     * @param property
     *         Property to be animated
     * @param init
     *         Initial value
     * @param end
     *         Final value
     *
     * @return ObjectAnimator with the given animated property
     */
    @NonNull
    private ObjectAnimator createObjectAnimator(View view, String property, float init, float end) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(ConstantUtil.ANIM_DURATION);
        return scaleXAnimation;
    }

    /**
     * Método que realiza la animacion
     * @param view
     *          Vistas
     */
    private void fadeAnimation(View view) {
        ObjectAnimator scaleXAnimation = createObjectAnimator(view, "scaleX", 5.0F, 1.0F);
        ObjectAnimator scaleYAnimation = createObjectAnimator(view, "scaleY", 5.0F, 1.0F);
        ObjectAnimator alphaAnimation = createObjectAnimator(view, "alpha", 0.0F, 1.0F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }
}
