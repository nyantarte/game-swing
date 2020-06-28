import java.io.*;
import javax.swing.*;
import java.util.Stack;

import java.awt.*;
import java.util.*;
import java.util.logging.*;
import java.util.List;
public class Main {

    public static final int WND_W=640;
    public static final int WND_H=480;
    public static final int CARD_IMG_H=800/2;
    public static final int CARD_IMG_W=600/2;
    public static final String ASSET_DIR="./assets";
    public static final String IMG_DIR=ASSET_DIR+"/image";
    //public static MainWindow s_mainWin;
    public static JFrame s_mainWin;
    public static List<RootboxCharactor> s_charaList=new ArrayList<>();
    public static List<BattleCharactor> s_playerCharaList=new ArrayList<>();
    public static List<FieldMap> s_mapList=new ArrayList<>();
    public static Party s_playerParty=new Party();

    public static final LispVM.NativeMethodType s_createRootboxCharaFunc=
        new CreateRootboxCharactorFunc();
    public static final LispVM.NativeMethodType s_createMapFunc=
        new CreateFieldMapFunc();
    public static final LispVM.NativeMethodType s_createPanel=
        new CreatePanelFunc();

    public static final LispVM.NativeMethodType s_charaFunc=new CharaFunc();
    public static final LispVM.NativeMethodType s_pairFunc=new PairFunc();
    public static final LispVM.NativeMethodType s_mapFunc=new MapFunc();
    public static LispVM createVMState(String src){
        try{
            LispVM vm=new LispVM();
            vm.reg("createChara", s_createRootboxCharaFunc);
            vm.reg("createMap",s_createMapFunc);
            vm.reg("createPanel", s_createPanel);
            vm.reg("yaxis", CreatePanelFunc.LAYOUT_TYPE_YAXIS);
            vm.reg("xaxis",CreatePanelFunc.LAYOUT_TYPE_XAXIS);
            vm.reg("chara",s_charaFunc);
            vm.reg("pair",s_pairFunc);
            vm.reg("map",s_mapFunc);
            
            new LispSyntaxParser(vm, new LispLexerParser(src));
            vm.dump();
            vm.run();
            return vm;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args){
        
        if(0 < args.length){
            try{
                BufferedReader reader=new BufferedReader(new FileReader(args[0]));
                StringBuffer sb=new StringBuffer();
                String line;
                while(null!=(line=reader.readLine())){
                    sb.append(line+"\n");
                }
                createVMState(sb.toString());
            }catch(Exception e){
                e.printStackTrace();;
            }
        }

        //s_charaList.add(new RootboxCharactor("Unicorn",RootboxCharactor.TYPE.AIR_CARRIER));
        //s_mapList.add(new FieldMap("test",8,8));
        s_mainWin=new MainWindow(WND_W, WND_H);
        
    }
}