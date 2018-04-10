package productions.pudl.siege.Data.GeneralObjects;

public class GeneralAliasObject
{
    private String name;
    private String createdAt;

    public GeneralAliasObject(String name, String createdAt)
    {
        setName(name);
        setCreatedAt(createdAt);
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString()
    {
        return ("{" + '"' + "name" + '"' + ":" + '"' + getName() + '"' + "," + '"' + "created_at" + '"' + ":" + '"' + getCreatedAt() + '"' + "}");
    }
}
