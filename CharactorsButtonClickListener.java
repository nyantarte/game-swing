import java.awt.event.*;
public class CharactorsButtonClickListener implements ActionListener{


    public void actionPerformed(ActionEvent e){
        MainWindow w=(MainWindow)Main.s_mainWin;
        w.setCharaCollectionState();
    }

}