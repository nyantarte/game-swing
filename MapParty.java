
public class MapParty {

    private Vector m_pos;
    private Party m_data;
    private IMoveFunc m_moveFunc;
    private int m_encountCount;
    public MapParty(Party data){
        m_data=data;
    }
    public Vector getPos(){
        return m_pos;
    }
    public void setPos(Vector pos){
        m_pos=pos;
    }
    public void setMoveFunc(IMoveFunc f){
        m_moveFunc=f;
    }
    public IMoveFunc getMoveFunc(){
        return m_moveFunc;
    }

    public int getEncountCount(){
        return m_encountCount;
    }
    public void setEncountCount(int c){
        m_encountCount=c;
    }
    public Party getData(){
        return m_data;
    }
    
}