
public class BattleCharactor {

    private RootboxCharactor m_data;
    private int m_level;
    public BattleCharactor(RootboxCharactor data){
        m_data=data;
    }
    public String getName(){
        return m_data.getName();
    }


    @Override
    public String toString(){
        return String.format("%s Level %d",m_data.getName(),m_level);
    }
}