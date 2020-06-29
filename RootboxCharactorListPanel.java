import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
public class RootboxCharactorListPanel extends JPanel{


    private JList m_list;
    private ActionListener m_newClickListener;
    public RootboxCharactorListPanel(boolean needItemMenu){
        super();
        setLayout(new BorderLayout());
        JList list=m_list=new JList(new DefaultListModel());
        
        add(list,BorderLayout.CENTER);

        if(needItemMenu){
            JPanel p=new JPanel();
            p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS ));
            JButton b=new JButton("New");
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(null!=m_newClickListener){
                        m_newClickListener.actionPerformed(e);
                    }
                }
            });
            p.add(b);
            b=new JButton("Delete");
            p.add(b);
            add(p,BorderLayout.SOUTH);
            
        }
        updateList();
    }

    private void updateList(){
        DefaultListModel listModel=(DefaultListModel)m_list.getModel();
        listModel.clear();
        for(RootboxCharactor c:Main.s_charaList){
            listModel.addElement(c);
            //System.out.println(c.toString());
        }

    }

    public void addMouseListener(MouseListener listener){
        m_list.addMouseListener(listener);
    }
    public void addNewClickListener(ActionListener listener){
        m_newClickListener=listener;
    }
    public Object getSelectedValue(){
        return m_list.getSelectedValue();
    }
}