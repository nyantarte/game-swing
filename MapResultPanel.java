import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MapResultPanel extends JPanel implements ActionListener{

    private Timer m_timer;
    public MapResultPanel(boolean isSucceded,FloorMap m){
        super();
    
        setLayout(new BorderLayout());

        JLabel l=new JLabel(isSucceded?String.format("Floor %d cleared",m.getCurFloorNum()):"Failed ");
        add(l,BorderLayout.CENTER);

        m_timer=new Timer(1000,this);
        m_timer.setRepeats(false);
        m_timer.start();
    }

    public void actionPerformed(ActionEvent e){
        ((MainWindow)Main.s_mainWin).returnState();
        System.out.println("A");
    }

}