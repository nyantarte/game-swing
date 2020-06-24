import java.util.*;
import java.io.*;
public class CreateRootboxCharactorFunc extends LispVM.NativeMethodType{

    @Override
    public int call(LispVM vm,int argNum,Stack<Object> o) {

        RootboxCharactor.SPEC_RANK airDef=
            8<argNum? RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.SPEC_RANK avoid=
            7<argNum? RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.SPEC_RANK torpedo=
            6<argNum?RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.SPEC_RANK airAtk=
            5<argNum? RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.SPEC_RANK atk=
            4<argNum? RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.SPEC_RANK life=
            3<argNum? RootboxCharactor.SPEC_RANK.valueOf((String)o.pop()):
                null;
        RootboxCharactor.TYPE type=
            2<argNum? RootboxCharactor.TYPE.valueOf((String)o.pop()):
                null;
        String imgFile=1< argNum?(String)o.pop():null;
        String name=(String)o.pop();
            Main.s_charaList.add(new RootboxCharactor(name, type,
            null!=imgFile?new File(Main.IMG_DIR,imgFile):null,
            life, atk, airAtk, torpedo, avoid, airDef));
        return 0;
    }
}