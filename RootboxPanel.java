
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class RootboxPanel extends JPanel{

    private static Random s_rand=new Random();
    private RootboxCharactor m_result;

    public RootboxPanel(){
        super();


        int idx=s_rand.nextInt(Main.s_charaList.size());
        idx=Math.abs(idx);
        m_result=Main.s_charaList.get(idx);
        

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new JLabel(m_result.getName()));
        JButton b=new JButton("Ok");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Main.s_playerCharaList.add(new BattleCharactor(m_result));
                MainWindow w=(MainWindow)Main.s_mainWin;
                w.returnState();
            }
        });
        add(b);

    }

}