import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class DevelopPanel extends JPanel{

    private JPanel m_menuPanel;
    public DevelopPanel(){
        super();
        setLayout(new BorderLayout());
        JPanel p=m_menuPanel=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JButton b=new JButton("Charactor");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setCharaListPanel();
            }
        });
        p.add(b);
        b=new JButton("Map");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setMapListPanel();
            }
        });
        p.add(b);

        add(p,BorderLayout.WEST);
    
    }

    private void removeContentPane(){
        for(int i=0;i < getComponentCount();++i){
            Component c=getComponent(i);
            if(m_menuPanel!=c){
                remove(c);
            }
        }


    }

    private void setCharaListPanel(){
        removeContentPane();
        RootboxCharactorListPanel listPanel=new RootboxCharactorListPanel(true);
        listPanel.addNewClickListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                removeContentPane();
                add(createCharaEditPanel(null),BorderLayout.CENTER);

                revalidate();
                repaint();

            }
        });
        listPanel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                RootboxCharactor selObj=(RootboxCharactor)listPanel.getSelectedValue();
                if(1<e.getClickCount() && null!=selObj){
                    removeContentPane();
                    add(createCharaEditPanel(selObj),BorderLayout.CENTER);

                    revalidate();
                    repaint();
                }
            }
        });
        add(listPanel,BorderLayout.CENTER);
        revalidate();
        repaint();

    }

    private void setMapListPanel(){
        removeContentPane();
        FieldMapListPanel panel=new FieldMapListPanel(true);
        panel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                FieldMap m=(FieldMap)panel.getSelectedValue();
                if(1<e.getClickCount() && null!=m){
                    setFieldMapEditPanel(m);
                }
            }
        });
        panel.addNewActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setFieldMapEditPanel(null);
            }
        });
        add(panel);
        revalidate();
        repaint();
    }
    private void setFieldMapEditPanel(FieldMap m){
        removeContentPane();
        FieldMapEditPanel editPanel=new FieldMapEditPanel(m);
        add(editPanel);
        revalidate();
        repaint();;

    }

    private RootboxCharactorEditPanel createCharaEditPanel(RootboxCharactor editObj){
        RootboxCharactorEditPanel editPanel=new RootboxCharactorEditPanel(editObj);
        editPanel.setCloseAction(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setCharaListPanel();
            }
        });
        return editPanel;

    }
}