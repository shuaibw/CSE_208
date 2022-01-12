public class Vertex {
    public final int type; //0->s/t, 1->single team, 2->paired team
    public final int x; //original team
    public final int y; //against whom, if single team then -1
    public final int id; //unique vertex id

    public Vertex(int type, int x, int y, int id) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.id = id;
    }
}
