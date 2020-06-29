
public class BattleCharactor {

    private RootboxCharactor m_data;
    private int m_level;
    private int m_life;
    private int m_atk;
    private int m_airAtk;
    private int m_airDef;
    private int m_avoid;
    private Vector m_pos;
    public BattleCharactor(RootboxCharactor data){
        m_data=data;
        setLevel(1);
    }
    public String getName(){
        return m_data.getName();
    }

    public int getLevel(){
        return m_level;
    }
    public int getLife(){
        return m_life;
    }
    public Vector getPos(){
        return m_pos;
    }
    public void setPos(Vector p){
        m_pos=p;
    }

    @Override
    public String toString(){
        return String.format("%s Level %d",m_data.getName(),m_level);
    }

    public void setLevel(int level){
        m_life=0;
        m_atk=0;
        m_airAtk=0;
        m_airDef=0;
        m_avoid=0;
        for(int i=0;i < level;++i){
            m_life=computeParam(m_data.getLifeRank(), m_life);
            m_atk=computeParam(m_data.getAtkRank(), m_atk);
            m_airAtk=computeParam(m_data.getAirAtk(), m_airAtk);
            m_airDef=computeParam(m_data.getAirDefRank(), m_airDef);
            m_avoid=computeParam(m_data.getAvoidRank(), m_avoid);
        }
        m_level=level;
    }

    private int computeParam(RootboxCharactor.SPEC_RANK r,int v){
        if(RootboxCharactor.SPEC_RANK.S==r){
            return (int)Math.max(1, v*0.15f);
        }else if(RootboxCharactor.SPEC_RANK.A==r){
            return (int)Math.max(1, v*0.1f);
        }else if(RootboxCharactor.SPEC_RANK.B==r){
            return (int)Math.max(1, v*0.075f);

        }else if(RootboxCharactor.SPEC_RANK.C==r){
            return (int)Math.max(1, v*0.05f);

        }else if(RootboxCharactor.SPEC_RANK.D==r){
            return (int)Math.max(1, v*0.025f);

        }

        return 0;
    }
}