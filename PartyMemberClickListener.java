import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PartyMemberClickListener implements ActionListener{

    private int m_targetIdx;
    private JButton m_target;
    private JList m_list;
    public PartyMemberClickListener(int tIdx,JButton t,JList list){
        m_targetIdx=tIdx;
        m_target=t;
        m_list=list;
    }
    public void actionPerformed(ActionEvent e){
        DefaultListModel listModel=(DefaultListModel)m_list.getModel();
        listModel.clear();
        m_target.setText("");
        for(BattleCharactor c:Main.s_playerCharaList){
            boolean isInParty=false;
            for(BattleCharactor pm:Main.s_playerParty.getMembers()){
                if(c==pm){
                    isInParty=true;
                    break;
                }
            }
            if(!isInParty)
                listModel.addElement(c);
        }
        m_list.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                BattleCharactor c=(BattleCharactor)m_list.getSelectedValue();
                Main.s_playerParty.getMembers()[m_targetIdx]=c;
                m_target.setText(c.toString());
                m_list.addListSelectionListener(null);
            }
        });


    
    }

}