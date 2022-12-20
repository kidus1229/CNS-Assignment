import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JTextArea encfeild = new JTextArea(5,23);
    private JTextArea dycfeild = new JTextArea(5,23);
    private JTextArea afterencfeild = new JTextArea(5,23);
    private JTextArea afterdycfeild = new JTextArea(5,23);
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JButton enc = new JButton("Encrypt");
    private JButton dyc = new JButton("Decrypt");
    String[] option =  {"OTP", "3DES", "AES"};
    private JComboBox choice = new JComboBox<>(option);
    private static int algorithm = 0;
    TripleDES tripleDES = new TripleDES();
    AES aes = new AES();
    private String pad;
    private String secretKey = "SecretKey";


    public Main(){
        setTitle("Message Encyption and Decryption");
        setVisible(true);
        setResizable( false );



        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        encfeild.setForeground(Color.DARK_GRAY);
        dycfeild.setForeground(Color.DARK_GRAY);
        afterencfeild.setForeground(Color.DARK_GRAY);
        afterdycfeild.setForeground(Color.DARK_GRAY);
        dycfeild.setFont(new Font("Serif",Font.BOLD,18));
        encfeild.setFont(new Font("Serif",Font.BOLD,18));
        afterdycfeild.setFont(new Font("Serif",Font.BOLD,18));
        afterencfeild.setFont(new Font("Serif",Font.BOLD,18));
        encfeild.setLineWrap(true);
        dycfeild.setLineWrap(true);
        afterencfeild.setLineWrap(true);
        afterdycfeild.setLineWrap(true);
        
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        // encfeild.setPreferredSize(new Dimension(260,100));
        // afterencfeild.setPreferredSize(new Dimension(260,100));
        // dycfeild.setPreferredSize(new Dimension(260,100));
        // afterdycfeild.setPreferredSize(new Dimension(260,100));
        
        p1.add(encfeild); 
        p2.add(dycfeild); 
        p1.add(enc); 
        p2.add(dyc); 
        p1.add(afterencfeild); 
        p2.add(afterdycfeild); 
        p3.add(choice); 
        
        p1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p1.setPreferredSize(new Dimension(400, 200));
        p1.setMaximumSize(new Dimension(400, 200));
        p1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        p2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        p2.setPreferredSize(new Dimension(400, 200));
        p2.setMaximumSize(new Dimension(400, 200));
        p2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        p3.setAlignmentX(Component.CENTER_ALIGNMENT);
        p3.setPreferredSize(new Dimension(800, 60));
        p3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
        c.add(p3, BorderLayout.NORTH);
        c.add(p1, BorderLayout.WEST);
        c.add(p2, BorderLayout.EAST);

        choice.addActionListener(new EL());
        enc.addActionListener(new EL());
        dyc.addActionListener(new EL());
        // encfeild.addActionListener(new EL());
        // dycfeild.addActionListener(new EL());
        // afterencfeild.addActionListener(new EL());
        // afterdycfeild.addActionListener(new EL());

    }

    private class EL implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == choice){
                algorithm = choice.getSelectedIndex();
            }
            if (e.getSource() == enc){
                switch(algorithm){
                    case 0:
                    
                        pad = OTP.generate_pad(encfeild.getText().length());
                        String ciphertext = OTP.encrypt(encfeild.getText(), pad);
                        afterencfeild.setText(ciphertext);
                        break;
                    case 1:
                        String clearText = encfeild.getText();
                        String data = tripleDES.encrypt(clearText, secretKey);
                        afterencfeild.setText(data);                  
                        break;
                    case 2:
                        try {
                            aes.init();
                            String encryptedMessage = aes.encrypt(encfeild.getText());
                            afterencfeild.setText(encryptedMessage);
                        } catch (Exception ignored) {
                            }
                        break;
                }
            }
        
            if (e.getSource() == dyc){
                switch(algorithm){
                    case 0:
                    
                        String plaintextOTP = OTP.decrypt(pad, dycfeild.getText());
                        afterdycfeild.setText(plaintextOTP);
                        break;
                    case 1:
                        
                        String plaintextDES = tripleDES.decrypt(dycfeild.getText(), secretKey);
                        afterdycfeild.setText(plaintextDES);               
                        break;
                    case 2:
                        try {
                            String decryptedMessage = aes.decrypt(dycfeild.getText());
                            afterdycfeild.setText(decryptedMessage);
                        } catch (Exception ignored) {
                            }
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
