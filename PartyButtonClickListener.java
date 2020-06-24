import java.awt.event.*;

public class PartyButtonClickListener implements ActionListener{


    public void actionPerformed(ActionEvent e){
        MainWindow w=(MainWindow)Main.s_mainWin;
        w.setPartyState();
    }
}