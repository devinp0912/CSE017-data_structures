public class Pair<E1,E2>{
    private E1 first;
    private E2 second;

    public Pair(){
        first = null;
        second = null;
    }
    public Pair(E1 first, E2 second){
        this.first = first;
        this.second = second;
    }
    public E1 getFirst(){
        return first;
    }
    public E2 getSecond(){
        return second;
    }
    public void setFirst(E1 f){
        first = f;
    }
    public void setSecond(E2 s){
        second = s;
    }
    public String toString(){
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
    public boolean equals(Object o){
        if(o instanceof Pair){
            Pair<E1,E2> pair = (Pair) o;
            return this.getFirst().equals(pair.getFirst()) && this.getSecond().equals(pair.getSecond());
        }else{
            return false;
        }
    }
}