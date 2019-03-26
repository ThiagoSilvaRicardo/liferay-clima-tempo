package clima.tempo.api.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cidade")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {

    @XmlElement(name = "previsao")
    private List<Forecast> forecasts;

    private String nome;
    private String uf;
    private String atualizacao;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public String getAtualizacao() {
        return atualizacao;
    }

    @Override
    public String toString() {
        return "Weather [nome=" + nome + ", uf=" + uf + ", atualizacao=" + atualizacao + "]";
    }
}
