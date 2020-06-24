import java.awt.*;
public class FieldMap {

    private String m_name;
    private int m_width;
    private int m_height;


    public FieldMap(String n,int w,int h){
        m_name=n;
        m_width=w;
        m_height=h;
    }
    
    public String getName(){
        return m_name;
    }
    public int getWidth(){
        return m_width;
    }
    public int getHeight(){
        return m_height;
    }
}