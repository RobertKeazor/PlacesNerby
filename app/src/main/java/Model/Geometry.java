package Model;

public class Geometry
{
    public Location location;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+"]";
    }
}