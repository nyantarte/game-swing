import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class ListItemSelectListener implements ListSelectionListener{

    private JList m_list;

    public ListItemSelectListener(JList l){
        m_list=l;
    }
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        MainWindow w=(MainWindow)Main.s_mainWin;
        
        w.setRootCharaSpecState((RootboxCharactor)m_list.getSelectedValue());
    }


}