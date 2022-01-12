package springboot.vote_for_cafe.to;


public class CafeTo {

    protected Integer id;

    private String name;

    private int scores;

    public CafeTo(Integer id, String name, int scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public CafeTo() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "CafeTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

}
