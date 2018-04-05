package productions.pudl.siege.Data.GeneralObjects;

public class GeneralStatObject
{
    private GeneralRankedObject rankedObject;
    private GeneralCasualObject casualObject;
    private GeneralOverallObject overallObject;
    private GeneralProgressionObject progressionObject;

    public GeneralStatObject(GeneralRankedObject rankedObject, GeneralCasualObject casualObject, GeneralOverallObject overallObject, GeneralProgressionObject progressionObject)
    {
        setRankedObject(rankedObject);
        setCasualObject(casualObject);
        setOverallObject(overallObject);
        setProgressionObject(progressionObject);
    }

    public GeneralRankedObject getRankedObject() {
        return rankedObject;
    }

    public void setRankedObject(GeneralRankedObject rankedObject) {
        this.rankedObject = rankedObject;
    }

    public GeneralCasualObject getCasualObject() {
        return casualObject;
    }

    public void setCasualObject(GeneralCasualObject casualObject) {
        this.casualObject = casualObject;
    }

    public GeneralOverallObject getOverallObject() {
        return overallObject;
    }

    public void setOverallObject(GeneralOverallObject overallObject) {
        this.overallObject = overallObject;
    }

    public GeneralProgressionObject getProgressionObject() {
        return progressionObject;
    }

    public void setProgressionObject(GeneralProgressionObject progressionObject) {
        this.progressionObject = progressionObject;
    }
}
