import java.util.*;
import javax.swing.*;
import java.awt.*;
public class CreatePanelFunc extends LispVM.NativeMethodType{

    public static final int LAYOUT_TYPE_YAXIS=1;
    public static final int LAYOUT_TYPE_XAXIS=2;
    public static final int LAYOUT_TYPE_GRID=3;
    @Override
    public int call(LispVM vm,int argNum, Stack<Object> o){
        JPanel p=new JPanel();
        if(0 < argNum){
            int type=(int)o.pop();
            switch(type){
                case LAYOUT_TYPE_YAXIS:
                    p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
                break;
                case LAYOUT_TYPE_XAXIS:
                    p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
                break;
            }
            
        }else{
            p.setLayout(new BorderLayout());
        }

        o.push(p);
        return 1;
    }


}