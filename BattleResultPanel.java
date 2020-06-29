import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BattleResultPanel extends JPanel{

    private Party m_playerParty,m_enemyParty;
    private Timer m_timer;
    private int m_playerLifeLeft=0;
    private int m_enemyLifeLeft=0;

    public BattleResultPanel(Party p,Party e){
        super();
        m_playerParty=p;
        m_enemyParty=e;

        for(BattleCharactor bc:m_playerParty.getMembers()){
            if(null!=bc && 0 < bc.getLife()){
                m_playerLifeLeft+=bc.getLife();
            }
        }
        for(BattleCharactor bc:m_playerParty.getMembers()){
            if(null!=bc && 0 < bc.getLife()){
                m_enemyLifeLeft+=bc.getLife();
            }
        }
        
        m_timer=new Timer(1000,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ((MainWindow)Main.s_mainWin).returnState();
            }
        });

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel l=new JLabel("Battle result");
        add(l);

        

        m_timer.setRepeats(false);
        m_timer.start();
    }

}