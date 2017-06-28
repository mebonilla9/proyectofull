package co.appreactor.proyectofull.negocio.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import co.appreactor.proyectofull.R;

/**
 * Created by Capacitaciones_pc7 on 27/06/2017.
 */

public class WebViewFragment extends Fragment {

    private WebView wvNavegador;
    private final String rutaWeb = "https://android-arsenal.com";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragmento_webview, container, false);
        this.wvNavegador = (WebView) vista.findViewById(R.id.wvNavegador);
        this.wvNavegador.setWebViewClient(new WebViewClient());
        this.wvNavegador.loadUrl(rutaWeb);
        this.wvNavegador.getSettings().setJavaScriptEnabled(true);
        this.wvNavegador.getSettings().setBuiltInZoomControls(true);
        this.wvNavegador.getSettings().setDisplayZoomControls(false);

        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
