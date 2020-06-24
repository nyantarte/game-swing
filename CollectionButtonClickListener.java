import java.awt.event.*;
public class CollectionButtonClickListener implements ActionListener{


    public void actionPerformed(ActionEvent e){
        MainWindow w=(MainWindow)Main.s_mainWin;
        w.setCollectionState();
    }

}