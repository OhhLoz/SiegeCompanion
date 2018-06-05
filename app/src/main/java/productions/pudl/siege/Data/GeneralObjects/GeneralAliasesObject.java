package productions.pudl.siege.Data.GeneralObjects;

import java.util.ArrayList;

public class GeneralAliasesObject
{
    private ArrayList<GeneralAliasObject> aliasObjects = new ArrayList<>();

    public GeneralAliasesObject(ArrayList<GeneralAliasObject> inputArrayList)
    {
        setAliasObjects(inputArrayList);
    }

    public ArrayList<GeneralAliasObject> getAliasObjects() {
        return aliasObjects;
    }

    private void setAliasObjects(ArrayList<GeneralAliasObject> aliasObjects) {
        this.aliasObjects = aliasObjects;
    }

    @Override
    public String toString()
    {
        String result = '"' + "aliases" + '"' + ":[";
        StringBuilder str = new StringBuilder(result);
        for(int i = 0; i < getAliasObjects().size(); i++)
        {
            str.append(getAliasObjects().get(i).toString());
            if (i != getAliasObjects().size()-1)
                str.append(",");
        }
        str.append("]");

        return str.toString();
    }
}
