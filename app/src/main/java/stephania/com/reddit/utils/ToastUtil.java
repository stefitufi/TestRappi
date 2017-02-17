package stephania.com.reddit.utils;

import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

/**
 * Clase de utilidades para personalizar {@link android.widget.Toast}
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno</a>
 */
public class ToastUtil {
    /**
     * Este método crea un Toast básico
     *
     * @param context  Contexto donde se va a ejecutar el Toast
     * @param mensaje  Mensaje del Toast
     * @param duracion Duración (usando las variables SuperToast.Duration.LONG, SuperToast.Duration.MEDIUM,
     *                 etc)
     * @return SuperToast básico
     */
    public static SuperToast crearToast(Context context, String mensaje, int duracion,
                                        TipoNotificacion tipoNotificacion) {

        return SuperToast.create(context, mensaje, duracion,
                Style.getStyle(clasificarEstiloNotificacion(tipoNotificacion, Boolean.FALSE),
                        SuperToast.Animations.FLYIN));
    }

    /**
     * Este método clasifica el estilo de la notificación dependiendo de su tipo
     *
     * @param tipoNotificacion Tipo de notificación a ser mostrada
     * @return Color del estilo
     */
    private static int clasificarEstiloNotificacion(TipoNotificacion tipoNotificacion,
                                                    boolean esCard) {
        int style;
        switch (tipoNotificacion) {
            default:
            case INFORMATIVA:
                style = (esCard ? SuperToast.Background.BLUE : Style.BLUE);
                break;
            case ALERTA:
                style = (esCard ? SuperToast.Background.ORANGE : Style.ORANGE);
                break;
            case EXITOSA:
                style = (esCard ? SuperToast.Background.GREEN : Style.GREEN);
                break;
            case ERROR:
                style = (esCard ? SuperToast.Background.RED : Style.RED);
                break;
        }

        return style;
    }

    /**
     * Tipos de notificación manejados por los Toasts
     */
    public enum TipoNotificacion {
        /**
         * Es una notificación informativa
         **/
        INFORMATIVA,

        /**
         * Notificación de alerta
         **/
        ALERTA,

        /**
         * Notificación exitosa
         **/
        EXITOSA,

        /**
         * Notificación de error
         **/
        ERROR;
    }
}
