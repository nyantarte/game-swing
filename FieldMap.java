import java.awt.*;
public class FieldMap {

    private String m_name;
    private int m_width;
    private int m_height;
    private int m_maxFloorNum;

    public FieldMap(){}
    public FieldMap(String n,int w,int h){
        m_name=n;
        m_width=w;
        m_height=h;
    }
    
    public String getName(){
        return m_name;
    }
    public void setName(String n){
        m_name=n;
    }
    public int getWidth(){
        return m_width;
    }
    public void setWidth(int w){
        m_width=w;
    }
    public int getHeight(){
        return m_height;
    }

    public void setHeight(int h){
        m_height=h;
    }

    public int getMaxFloorNum(){
        return m_maxFloorNum;
    }
    public void setMaxFloorNum(int n){
        m_maxFloorNum=n;
    }
}