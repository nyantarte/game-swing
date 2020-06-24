import java.io.*;
public class RootboxCharactor {

    private String m_name;
    public enum TYPE{
        DESTROYER,
        CRUISSER,
        AIR_CARRIER,
        BATTLE_SHIP
    }
    private TYPE m_type;

    public enum SPEC_RANK{
        S,
        A,
        B,
        C,
        D,
        E
    }
    private SPEC_RANK m_life,m_atk,m_airAtk,m_torpedo,m_avoid,m_airDef;
    private File m_imageFile;
    public RootboxCharactor(String name,
        TYPE t,
        File image,
        SPEC_RANK life,
        SPEC_RANK atk,
        SPEC_RANK airAtk,
        SPEC_RANK torpedo,
        SPEC_RANK avoid,
        SPEC_RANK airDef){
            m_name=name;
            m_type=t;
            m_imageFile=image;
            m_life=life;
            m_atk=atk;
            m_airAtk=airAtk;
            m_torpedo=torpedo;
            m_avoid=avoid;
            m_airDef=airDef;
    }
    public RootboxCharactor(String name,TYPE type){
        m_name=name;
        m_type=type;
    }
    public RootboxCharactor(){}
    public SPEC_RANK getLifeRank(){
        return m_life;
    }
    public void setLifeRank(SPEC_RANK r){
        m_life=r;
    }
    public SPEC_RANK getAtkRank(){
        return m_atk;
    }
    public void setAtkRank(SPEC_RANK r){
        m_atk=r;
    }
    public SPEC_RANK getAirAtk(){
        return m_airAtk;
    }
    public void setAirAtk(SPEC_RANK r){
        m_airAtk=r;
    }
    public SPEC_RANK getTorpedoRank(){
        return m_torpedo;

    }
    public void setTorpedoRank(SPEC_RANK r){
        m_torpedo=r;
    }
    public SPEC_RANK getAirDefRank(){
        return m_airDef;
    }
    public void setAirDefRank(SPEC_RANK r){
        m_airDef=r;
    }
    public SPEC_RANK getAvoidRank(){
        return m_avoid;
    }
    public void setAvoidRank(SPEC_RANK r){
        m_avoid=r;
    }
    public String getName(){
        return m_name;
    }
    public void setName(String n){
        m_name=n;
    }

    public TYPE getType(){
        return m_type;
    }
    public void setType(TYPE t){
        m_type=t;
    }

    @Override
    public String toString(){
        return m_name;
    }

    public File getImageFile(){
        return m_imageFile;
    }
    public void setImageFile(File f){
        m_imageFile=f;
    }
}