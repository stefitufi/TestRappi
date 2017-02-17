package stephania.com.reddit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.johnpersano.supertoasts.SuperToast;

import stephania.com.reddit.R;


/**
 * Utilidad conexion
 *
 * @author <a href="stephania-moreno@gmail.com">Stephania Moreno V.</a>
 */
public class ConnectionUtil {

    public static Context mContext;

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    /**
     * Metodo que valida el staus de la diponibilidad de internet
     * @param context Actividad
     * @return Status
     */
    public static String getConnectivityStatusString(Context context) {
        int conn = ConnectionUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == ConnectionUtil.TYPE_WIFI || conn== ConnectionUtil.TYPE_MOBILE) {
            status = context.getString(R.string.desable_connection);
        }else if (conn == ConnectionUtil.TYPE_NOT_CONNECTED) {
            ToastUtil.crearToast(context,
                    context.getString(R.string.desable_connection), SuperToast.Duration.SHORT,
                    ToastUtil.TipoNotificacion.ERROR).show();
        }
        return status;
    }
}
