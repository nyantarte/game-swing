import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MapPanel extends JPanel implements Runnable,ContainerListener{

    private static final int FPS_NUM=15;
    private static final long TIME_PER_FRAME=1000/FPS_NUM;
    private FloorMap m_map;
    private MapParty m_playerParty;
    private volatile boolean m_isRunning=true;
    private Thread m_thread;
    public MapPanel(FieldMap m){
        super();
        m_map=new FloorMap(m);
        m_playerParty=new MapParty(Main.s_playerParty);
        Vector pos=null;
        Rectangle r=m_map.getRoomsRect()[0];
        pos=new Vector(r.x,r.y,0);
        m_playerParty.setPos(pos);
        m_playerParty.setMoveFunc(new AStarMoveFunc(m_map,m_playerParty) );

        m_thread=new Thread(this);
        m_thread.start();
        
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
                Thread.sleep(TIME_PER_FRAME);
            }catch(Exception e){
                e.printStackTrace();
            }
            updateFrame();
            repaint();
        }
    }

    private void updateFrame(){
        m_playerParty.getMoveFunc().move(m_playerParty);
    }

    public void componentAdded(ContainerEvent e){

    }

    public void componentRemoved(ContainerEvent e){
        m_isRunning=false;
        try{
           m_thread.join();
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}