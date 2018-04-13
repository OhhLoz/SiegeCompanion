package productions.pudl.siege.Data.DetailedObjects;

public class DetailedStatsObject
{
    private DetailedGameModeObject bomb;
    private DetailedStatObject casual;
    private DetailedGeneralObject general;
    private DetailedGameModeObject hostage;
    private DetailedOperatorsObject operators;
    private DetailedStatObject ranked;
    private DetailedGameModeObject secure;

    public DetailedStatsObject(DetailedGameModeObject bomb, DetailedStatObject casual, DetailedGeneralObject general, DetailedGameModeObject hostage, DetailedOperatorsObject operators, DetailedStatObject ranked, DetailedGameModeObject secure) {
        setBomb(bomb);
        setCasual(casual);
        setGeneral(general);
        setHostage(hostage);
        setOperators(operators);
        setRanked(ranked);
        setSecure(secure);
    }

    public DetailedGameModeObject getBomb() {
        return bomb;
    }

    public void setBomb(DetailedGameModeObject bomb) {
        this.bomb = bomb;
    }

    public DetailedStatObject getCasual() {
        return casual;
    }

    public void setCasual(DetailedStatObject casual) {
        this.casual = casual;
    }

    public DetailedGeneralObject getGeneral() {
        return general;
    }

    public void setGeneral(DetailedGeneralObject general) {
        this.general = general;
    }

    public DetailedGameModeObject getHostage() {
        return hostage;
    }

    public void setHostage(DetailedGameModeObject hostage) {
        this.hostage = hostage;
    }

    public DetailedOperatorsObject getOperators() {
        return operators;
    }

    public void setOperators(DetailedOperatorsObject operators) {
        this.operators = operators;
    }

    public DetailedStatObject getRanked() {
        return ranked;
    }

    public void setRanked(DetailedStatObject ranked) {
        this.ranked = ranked;
    }

    public DetailedGameModeObject getSecure() {
        return secure;
    }

    public void setSecure(DetailedGameModeObject secure) {
        this.secure = secure;
    }
}
