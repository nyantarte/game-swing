import java.util.*;

public class CreateFieldMapFunc extends LispVM.NativeMethodType{


    public int call(LispVM vm,int argNum, Stack<Object> o){

        int h=2 < argNum?(int)((float)((Float)o.pop())):1;
        int w=1< argNum?(int)((float)((Float)o.pop())):1;
        String name=(String)o.pop();

        Main.s_mapList.add(new FieldMap(name,w,h));
        return 0;
    }
}