import java.util.*;
public class MapFunc extends LispVM.NativeMethodType{
    public int call(LispVM vm,int argNum, Stack<Object> o){
        
        FieldMap m=new FieldMap();
        for(int i=0;i < argNum;++i){
            Pair<Object,Object> param=(Pair<Object,Object>)o.pop();
            String paramName=(String)param.first;
            if("width".equals(paramName)){
                m.setWidth(((Float)param.second).intValue());
            }else if("height".equals(paramName)){
                m.setHeight(((Float)param.second).intValue());

            }else if("name".equals(paramName)){
                m.setName((String)param.second);
            }else if("floor".equals(paramName)){
                m.setMaxFloorNum(((Float)param.second).intValue());
            }
        }
        Main.s_mapList.add(m);
        o.push(m);
        return 1;
    }
}