package clima.tempo.api;

import clima.tempo.api.model.Weather;

public interface WeatherService {

    Weather getWeather(int cityId) throws Exception;
}

