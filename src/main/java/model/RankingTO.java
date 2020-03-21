package model;

public class RankingTO {
    private String username;
    private Integer incidencias;

    public RankingTO(String username, Integer incidencias) {
        this.username = username;
        this.incidencias = incidencias;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Integer incidencias) {
        this.incidencias = incidencias;
    }
}
