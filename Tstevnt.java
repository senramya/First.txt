import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tstevnt implements ActionListener{
        private JFrame F;
        private JLabel L;
        private JTextField T;
        private JButton submiButton, closeButton;

    public void setGUI(){
        //Frame creation
        F= new JFrame ("Application for ATI");
        F.setSize(400,300);
        F.setVisible(true);
        F.setLayout(new GridLayout(7,2));
        L=new JLabel("Name");
        F.add(L);
        //TextField
        T= new JTextField(30);
        F.add(T);
        //Button
        submiButton= new JButton("Submit");
        F.add(submiButton);
        closeButton= new JButton("CLEAR");
        F.add(closeButton);
        submiButton.addActionListener(this);
        


    }
    public void ActionPerformed(ActionEvent e){
        T.setText("Button clicked");
    }
    public static void main(String[] args) {
        Tstevnt obj = new Tstevnt();
        obj.setGUI();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}