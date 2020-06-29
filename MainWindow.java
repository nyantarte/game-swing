import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MainWindow extends JFrame{

    private Stack<JPanel> m_stateStack=new Stack<>();
    public MainWindow(int w,int h){
        setSize(w,h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setHomeState();
        setVisible(true);
    }



    public void setHomeState(){

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JButton b=new JButton("Party");
        b.addActionListener(new PartyButtonClickListener());
        p.add(b);
        b=new JButton("Mission");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setMissionState(Main.s_mapList.get(0));
            }
        });
        p.add(b);
        b=new JButton("Collection");
        b.addActionListener(new CollectionButtonClickListener());
        p.add(b);
        
        b=new JButton("Develop");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                moveState(new DevelopPanel());
            }
        });
        p.add(b);
//        p.revalidate();
        
        moveState(p);

        if(0==Main.s_playerCharaList.size()){
            setRootboxState();
        }
    }

    public void setRootboxState(){
        moveState(new RootboxPanel());
    }
    public void setMissionState(FieldMap m){
        
        moveState(new MapPanel(m));
    }


    public void setPartyState(){
        moveState(new PartyPanel());
    }
    public void setCollectionState(){
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JButton b=new JButton("Charactors");
        b.addActionListener(new CharactorsButtonClickListener() );
        p.add(b);

        moveState(p);
    }

    public void setCharaCollectionState(){
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        DefaultListModel<RootboxCharactor> listModel=new DefaultListModel<>();
        for(RootboxCharactor c:Main.s_charaList){
            listModel.addElement(c);
            //System.out.println(c.toString());
        }
        JPanel subView=new JPanel();
        JList l=new JList(listModel);
        l.addListSelectionListener(new ListItemSelectListener(l));
        subView.add(l);
        JPanel menu=new JPanel();
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        JButton b=new JButton("Return");
        menu.add(b);
        p.add(menu,BorderLayout.WEST);
        p.add(subView,BorderLayout.CENTER);
        moveState(p);
    }

    public void setRootCharaSpecState(RootboxCharactor c){
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());

        JPanel menu=new JPanel();
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
        JButton b=new JButton("Return");
        b.addActionListener(new ReturnButtonClickListener());
        menu.add(b);

        p.add(menu,BorderLayout.WEST);

        p.add(new CharaDetailPanel(c));
        moveState(p);
    }

    public void setMapResultState(boolean isSucceded,FloorMap m){
        moveState(new MapResultPanel(isSucceded, m));
    }
    public void setBattleState(Party p,Party e){
        moveState(new BattlePanel(p, e));
    }
    public void setBattleResultState(Party p,Party e){
        moveState(new BattleResultPanel(p, e));
    }
    private void moveState(JPanel p){
        getContentPane().removeAll();
        m_stateStack.push(p);
        getContentPane().add(p,BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void returnState(){
        getContentPane().removeAll();
        m_stateStack.pop();
        JPanel p=m_stateStack.peek();
        getContentPane().add(p,BorderLayout.CENTER);
        revalidate();
        repaint();

    }
}