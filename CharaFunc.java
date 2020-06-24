import java.util.*;


public class CharaFunc extends LispVM.NativeMethodType{

    public int call(LispVM vm,int argNum, Stack<Object> o){
        RootboxCharactor c=new RootboxCharactor();
        for(int i=0;i < argNum;++i){
            Pair<Object,Object> param=(Pair<Object,Object>)o.pop();
            String paramName=(String)param.first;
            if("type".equals(paramName)){
                c.setType(RootboxCharactor.TYPE.valueOf((String)param.second));
            }else if("name".equals(paramName)){
                c.setName((String)param.second);
            }else if("life".equals(paramName)){
                c.setLifeRank((RootboxCharactor.SPEC_RANK)param.second);
            }else if("atk".equals(paramName)){
                c.setAtkRank((RootboxCharactor.SPEC_RANK)param.second);

            }else if("airAtk".equals(paramName)){
                c.setAirAtk((RootboxCharactor.SPEC_RANK)param.second);

            }else if("airDef".equals(paramName)){
                c.setAirDefRank((RootboxCharactor.SPEC_RANK)param.second);

            }else if("avoid".equals(paramName)){
                c.setAvoidRank((RootboxCharactor.SPEC_RANK)param.second);
            }
        }
        o.push(c);
        Main.s_charaList.add(c);
        return 1;

    }
}