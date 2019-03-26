package clima.tempo.api.impl;

import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = HTTPWeatherService.class)
public class HTTPWeatherService {

    private final String URL = "http://servicos.cptec.inpe.br/XML/";

    public InputStream get(String... urlPaths) throws IOException {
        String urlString = buildURL(urlPaths);
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        return conn.getInputStream();
    }

    private String buildURL(String... urlPaths) {
        StringBuilder sb = new StringBuilder(URL);

        for (String urlPath : urlPaths) {
            sb.append(urlPath);
            sb.append(StringPool.SLASH);
        }

        return removeLastSlash(sb);
    }

    private String removeLastSlash(StringBuilder sb) {
        int lastIndexOfLastSlash = sb.lastIndexOf(StringPool.SLASH);
        sb.setLength(lastIndexOfLastSlash);

        return sb.toString();
    }
}