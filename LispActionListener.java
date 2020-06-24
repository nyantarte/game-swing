
import java.awt.event.*;

public class LispActionListener implements ActionListener{

    private LispVM m_vm;
    private String m_label;

    public LispActionListener(LispVM vm,String label){
        m_vm=vm;
        m_label=label;
    }
    public void actionPerformed(ActionEvent e){
        m_vm.run(m_label);
    }
}