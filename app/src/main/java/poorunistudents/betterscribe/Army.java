package poorunistudents.betterscribe;

/**
 * Created by lance.chisholm on 2018-03-05.
 */

public class Army{
    private int Id;
    private String Name;
    private int Points;

    public Army(){
        super();
    }
    //Army constructor
    public Army(int id, String name, int points){
        super();
        this.Id = id;
        this.Name = name;
        this.Points = points;
    }

    @Override
    public String toString(){
        return this.Id + ": " + this.Name + " [" + this.Points + "]";
    }
}