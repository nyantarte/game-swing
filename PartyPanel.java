import javax.swing.*;
import java.awt.*;
public class PartyPanel extends JPanel{
    

    private DefaultListModel m_listModel;
    public PartyPanel(){
        super();
        setLayout(new BorderLayout());
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        m_listModel=new DefaultListModel();
        JList list=new JList(m_listModel);
        add(list,BorderLayout.CENTER);

        for(int i=0;i < Party.MEMBER_NUM;++i){
            BattleCharactor bc=Main.s_playerParty.getMembers()[i];
            JButton b=new JButton(null!=bc?bc.getName():"");
            b.addActionListener(new PartyMemberClickListener(i,b, list));
            p.add(b);
        }
        JButton b=new JButton("Return");
        b.addActionListener(new ReturnButtonClickListener());
        p.add(b);
        add(p,BorderLayout.WEST);
    }

}