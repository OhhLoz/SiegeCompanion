package productions.pudl.siege.Data.GeneralObjects;

class GeneralAliasesObject
{
    String name;
    String createdAt;

    public GeneralAliasesObject(String name, String createdAt)
    {
        setName(name);
        setCreatedAt(createdAt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
