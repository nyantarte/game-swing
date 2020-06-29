import javax.swing.*;
import javax.swing.Timer;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
public class BattlePanel extends JPanel{

    private static final int BULLET_SIZE=16;
    private static final int BULLET_MOVE_COUNT=30;
    private Party m_playerParty;
    private Party m_enemyParty;
    private static class Attack{
        public BattleCharactor attacker;
        public BattleCharactor defender;

        public Attack(BattleCharactor a,BattleCharactor d){
            attacker=a;
            defender=d;
        }
    }
    private List<Attack> m_atkQueue=new LinkedList<>();
    private int m_count=0;
    private Vector m_bulletPos,m_bulletDir;
    private Timer m_timer;
    public BattlePanel(Party p,Party e){
        super();
        m_playerParty=p;
        m_enemyParty=e;

        for(BattleCharactor bc :m_enemyParty.getMembers()){

            if(null!=bc && 0<bc.getLife()){
                m_atkQueue.add(new Attack(bc, randomChooseChara(m_enemyParty)));
            }
        }

        for(BattleCharactor bc :m_enemyParty.getMembers()){
            if(null!=bc && 0<bc.getLife()){
                m_atkQueue.add(new Attack(bc, randomChooseChara(m_playerParty)));
            }
        }
        m_timer=new Timer((int)Main.TIME_PER_FRAME,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateFrame();
                repaint();
            }
        });
        m_timer.setRepeats(true);
        m_timer.start();

    }
    @Override
    public void setSize(int width, int height) {
        // TODO Auto-generated method stub
        super.setSize(width, height);

        System.out.println("A");
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);

        g.setColor(Color.BLACK);

        int  nW=getWidth()/3;
        int nH=getHeight()/6;

        for(int i=0;i < m_playerParty.getMembers().length;++i){
            BattleCharactor bc=m_playerParty.getMembers()[i];
            if(null!=bc){
                if(null==bc.getPos()){
                    bc.setPos(new Vector(nW/2,nH*i+nH/2,0));
                }
                Rectangle r=bc.getPos().convRect(nW, nH);
                g.drawRect(r.x,r.y,r.width,r.height);
            }
        }

        for(int i=0;i < m_enemyParty.getMembers().length;++i){
            BattleCharactor bc=m_enemyParty.getMembers()[i];
            if(null!=bc){
                if(null==bc.getPos()){
                    bc.setPos(new Vector(nW*2+nW/2,nH*i+nH/2,0));
                }
                Rectangle r=bc.getPos().convRect(nW, nH);
                g.drawRect(r.x,r.y,r.width,r.height);
            }
        }

        if(null!=m_bulletPos){
            g.setColor(Color.WHITE);
            Rectangle r=m_bulletPos.convRect(BULLET_SIZE,BULLET_SIZE);
            g.fillRect(r.x,r.y,r.width,r.height);
        }
    }

    private void updateFrame(){
        if(0 < m_count){
            --m_count;
            m_bulletPos=Vector.add(m_bulletPos, m_bulletDir);

        }else if(0==m_count){
            if(m_atkQueue.isEmpty()){
                m_timer.setRepeats(false);
                ((MainWindow)Main.s_mainWin).returnState();
                ((MainWindow)Main.s_mainWin).setBattleResultState(m_playerParty,m_enemyParty);
                return;
            }
            Attack a=m_atkQueue.get(0);
            m_atkQueue.remove(0);
            if(0 < a.attacker.getLife() && 0 < a.defender.getLife()){
                Vector dir=Vector.sub(a.defender.getPos(),a.attacker.getPos());
                m_bulletDir=Vector.div(dir, BULLET_MOVE_COUNT);
                m_bulletPos=(Vector)a.attacker.getPos().clone();
                m_count=BULLET_MOVE_COUNT;
            }
        }

    }
    private BattleCharactor randomChooseChara(Party p){
        int n=(int)(Math.random()*p.getMembers().length);

        int i=0;
        while(0 < n){
            BattleCharactor bc=p.getMembers()[i%p.getMembers().length];
            if(null!=bc && 0 < bc.getLife()){
                --n;
            }
        }
        return p.getMembers()[i*p.getMembers().length];
    }


}