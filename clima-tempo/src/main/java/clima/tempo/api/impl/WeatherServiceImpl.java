package clima.tempo.api.impl;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import clima.tempo.api.WeatherService;
import clima.tempo.api.model.Weather;

@Component(immediate = true, service = WeatherService.class)
public class WeatherServiceImpl implements WeatherService {

    private static final PortalCache<Integer, Weather> cache =
            SingleVMPoolUtil.getPortalCache(WeatherService.class.getName());

    private final String WEATHER_PATH = "cidade";
    private final String WEATHER_FILE = "previsao.xml";
    private HTTPWeatherService httpWeatherService;

    @Override
    public Weather getWeather(int cityId) throws Exception {
        Weather weather = getFromCache(cityId);
        
        

        if (Validator.isNull(weather)) {
            weather = getFromService(cityId);
            
            System.out.println("****************"+weather);

            cache.put(cityId, weather);
        }
        return weather;
    }

    private Weather getFromCache(int cityId) {
        return cache.get(cityId);
    }

    private Weather getFromService(int cityId) throws IOException, JAXBException {

        Weather weather = null;
        InputStream weatherResponse =
                httpWeatherService.get(WEATHER_PATH, String.valueOf(cityId), WEATHER_FILE);

        JAXBContext jc = JAXBContext.newInstance(Weather.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        weather = (Weather) unmarshaller.unmarshal(weatherResponse);

        return weather;
    }

    @Reference(unbind = "-")
    protected void setHTTPWeatherService(HTTPWeatherService httpWeatherService) {
        this.httpWeatherService = httpWeatherService;
    }
}

