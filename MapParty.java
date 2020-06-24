
public class MapParty {

    private Vector m_pos;
    private Party m_data;
    private IMoveFunc m_moveFunc;
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
    
}