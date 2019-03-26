package clima.tempo.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.net.ConnectException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import clima.tempo.api.WeatherService;
import clima.tempo.api.model.Weather;
import clima.tempo.constants.ClimaTempoPortletKeys;

/**
 * @author thiagosilvaricardo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ClimaTempoPortletKeys.ClimaTempo,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ClimaTempoPortlet extends MVCPortlet {
	
	private WeatherService weatherService;
    private final Log log = LogFactoryUtil.getLog(Weather.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        int cityId = getSaoPauloCityId();
        try {
            Weather weather = weatherService.getWeather(cityId);
            renderRequest.setAttribute("weather", weather);
        } catch (Exception e) {
            handleError(e);
        }

        super.doView(renderRequest, renderResponse);
    }

    private void handleError(Exception e) {
        if (e instanceof ConnectException) {
            log.warn(String.format(
                    "Não foi possível conectar-se com o serviço de clima. Mensagem de erro: %s",
                    e.getMessage()));
        } else {
            log.error(e);
        }
    }

    @Reference(unbind = "-")
    protected void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    private int getSaoPauloCityId() {
        return 244;
    }
}