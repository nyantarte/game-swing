import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FieldMapEditPanel extends JPanel{

    private FieldMap m_target;
    private JTextField m_nameField;
    private JTextField m_widthField;
    private JTextField m_heightField;
    private ActionListener m_closeAction;
    public FieldMapEditPanel(FieldMap target){
        super();
        m_target=target;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Name"));
        m_nameField=new JTextField();
        p.add(m_nameField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Width"));
        m_widthField=new JTextField();
        p.add(m_widthField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Height"));
        m_heightField=new JTextField();
        p.add(m_heightField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        JButton b=new JButton("OK");
        p.add(b);
        b=new JButton("CANCEL");
        p.add(b);
        add(p);
    }

}