package clima.tempo.api.model;

import com.liferay.portal.kernel.search.ParseException;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import clima.tempo.date.DateInternationalizationService;

@XmlRootElement(name = "Previsao")
@XmlAccessorType(XmlAccessType.FIELD)
public class Forecast {

    private String dia;
    private String tempo;
    private int maxima;
    private int minima;
    private double iuv;

    @XmlTransient
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String getDayOfWeek() throws ParseException, java.text.ParseException {
        return DateInternationalizationService.getDayOfWeek(dateFormat.parse(getDia()),
                new Locale("pt", "br"));
    }

    public String getURLSmallIcon() {
        return "http://img0.cptec.inpe.br/~rgrafico/icones_principais/tempo/icones_pequenos/"
                + getTempo() + ".png";
    }

    public String getURLIcon() {
        return "http://www.cptec.inpe.br/widget/images/selo/" + getTempo() + ".png";
    }

    public String getDia() {
        return dia;
    }

    public String getTempo() {
        return tempo;
    }

    public int getMaxima() {
        return maxima;
    }

    public int getMinima() {
        return minima;
    }

    public double getIuv() {
        return iuv;
    }

    @Override
    public String toString() {
        return "Forecast [dia=" + dia + ", tempo=" + tempo + ", maxima=" + maxima + ", minima="
                + minima + ", iuv=" + iuv + "]";
    }
}
