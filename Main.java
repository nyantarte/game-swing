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
    public static final String CHARA_DIR=ASSET_DIR+"/charactors";
    public static final String MAP_DIR=ASSET_DIR+"/map";
    public static final int FPS_NUM=15;
    public static final long TIME_PER_FRAME=1000/FPS_NUM;
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
    public static Party genParty(){
        Party p=new Party();
        p.getMembers()[0]=new BattleCharactor(s_charaList.get(0));
        return p;
    }
    public static void runScriptFile(File f){
        try{
            BufferedReader reader=new BufferedReader(new FileReader(f));
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
    public static void main(String[] args){
        
        String[] files=(new File(CHARA_DIR)).list();
        for(String fileName:files){
            if(fileName.endsWith(".lsp")){
                runScriptFile(new File(CHARA_DIR,fileName));
            }
        }
        files=(new File(MAP_DIR)).list();
        for(String fileName:files){
            if(fileName.endsWith(".lsp")){
                runScriptFile(new File(MAP_DIR,fileName));
            }
        }

        //s_charaList.add(new RootboxCharactor("Unicorn",RootboxCharactor.TYPE.AIR_CARRIER));
        //s_mapList.add(new FieldMap("test",8,8));
        s_mainWin=new MainWindow(WND_W, WND_H);
        
    }
}