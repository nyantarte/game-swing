import java.util.*;

public class PairFunc extends LispVM.NativeMethodType{
    public int call(LispVM vm,int argNum, Stack<Object> o){
        
        Object p2=o.pop();
        Object p1=o.pop();
        o.push(new Pair<>(p1,p2));

        
        
        return 1;

    }

}