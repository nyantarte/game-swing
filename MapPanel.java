import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MapPanel extends JPanel implements Runnable{

    private static final int ENCOUNT_COUNT_MAX=4;
    private FloorMap m_map;
    private MapParty m_playerParty;
    private volatile boolean m_isRunning=true;
    private Thread m_thread;
    public MapPanel(FieldMap m){
        super();
        m_map=new FloorMap(m);
        m_playerParty=new MapParty(Main.s_playerParty);
        m_thread=new Thread(this);
        m_thread.start();
        resetFloor();
        
    }
    @Override
    protected void finalize() throws Throwable {
        stopWorkThread();
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public void setVisible(boolean aFlag) {
        if(!aFlag){
            stopWorkThread();
        }else{
            int playerLifeLeft=0;
            for(BattleCharactor bc:m_playerParty.getData().getMembers()){
                if(null!=bc){
                    playerLifeLeft+=bc.getLife();
                }
            }
            if(0>=playerLifeLeft){
                ((MainWindow)Main.s_mainWin).returnState();
                return;
            }
            m_isRunning=true;
            m_thread.start();
        }
        // TODO Auto-generated method stub
        super.setVisible(aFlag);
    }
    @Override
    public void paint(Graphics g){
        g.fillRect(0,0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        int qW=getWidth()/m_map.getWidth();
        int qH=getHeight()/m_map.getHeight();

        for(int i=0;i < m_map.getHeight();++i){
            for(int j=0;j < m_map.getWidth();++j){
                Tile t=m_map.getTile(j,i);
                g.setColor(t.color);
                g.fillRect(j*qW, i*qH, qW,qH);
            }
        }

        g.setColor(Color.GREEN);
        Vector pos=m_playerParty.getPos();
        g.fillRect((int)pos.getX()*qW,(int)pos.getY()*qH,qW,qH);


    }


    public void run(){
        while(m_isRunning){
            try{
                Thread.sleep(Main.TIME_PER_FRAME);
            }catch(Exception e){
                e.printStackTrace();
            }
            updateFrame();
            repaint();
        }
    }

    private void updateFrame(){
        m_playerParty.getMoveFunc().move(m_playerParty);
        Vector goal=m_map.getGoalPos();
        if(goal.equals(m_playerParty.getPos())){
            if(m_map.getCurFloorNum() < m_map.getCurFloorNum()){
                m_map.setCurFloorNum(m_map.getCurFloorNum()+1);
                resetFloor();
            }else{
                ((MainWindow)(Main.s_mainWin)).returnState();    
            }
            ((MainWindow)(Main.s_mainWin)).setMapResultState(true,m_map);
            m_isRunning=false;

        }else{
            int ec=m_playerParty.getEncountCount();
            if(0==(--ec)){

                ((MainWindow)Main.s_mainWin).setBattleState(m_playerParty.getData(), Main.genParty());
                resetEncountCount();
                m_isRunning=false;
            }else{
                m_playerParty.setEncountCount(ec);
            }
            
        }
    }


    private void resetFloor(){
        m_map.resetFloor();
        Vector pos=null;
        Rectangle r=m_map.getRoomsRect()[0];
        pos=new Vector(r.x,r.y,0);
        m_playerParty.setPos(pos);
        m_playerParty.setMoveFunc(new AStarMoveFunc(m_map,m_playerParty) );

        resetEncountCount();

    }
    private void resetEncountCount(){
        m_playerParty.setEncountCount((int)(Math.random()*ENCOUNT_COUNT_MAX));

    }

    private void stopWorkThread(){
        if(m_isRunning){
            m_isRunning=false;
         try{
            m_thread.join();
         }   catch(Exception e){
             e.printStackTrace();
         }
        }

    }

}
