import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FieldMapListPanel extends JPanel{

    private JList m_list;
    private ActionListener m_newAction;
    public FieldMapListPanel(boolean needEditMenu){
        super();
        setLayout(new BorderLayout());
        JList list=m_list=new JList(new DefaultListModel());
        add(list,BorderLayout.CENTER);
        
        
        if(needEditMenu){
            JPanel p=new JPanel();
            p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
            JButton b=new JButton("NEW");
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(null!=m_newAction){
                        m_newAction.actionPerformed(e);
                    }
                }
            });
            p.add(b);
            b=new JButton("DELETE");
            p.add(b);
            add(p,BorderLayout.SOUTH);
        }
        updateList();
    }


    private void updateList(){
        DefaultListModel model=(DefaultListModel)m_list.getModel();
        model.clear();
        for(FieldMap m:Main.s_mapList){
            model.addElement(m);
        }

    }

    public void addMouseListener(MouseListener l){
        m_list.addMouseListener(l);
    }
    public void addNewActionListener(ActionListener l){
        m_newAction=l;
    }

    public Object getSelectedValue(){
        return m_list.getSelectedValue();
    }
}