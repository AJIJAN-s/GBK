package gbk_client;

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class client_frame extends javax.swing.JFrame 
{
    String username, address;
    ArrayList<String> users = new ArrayList();
    int port;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    private String gbk, name, x, y;
    
    private int otr;
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
//             users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public client_frame() 
    {
        initComponents();
        ImageIcon im = new ImageIcon(getClass().getResource("/images/gamechar1.png"));
        Image img = im.getImage();
        Image newimg = img.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        ImageIcon im2 = new ImageIcon(getClass().getResource("/images/gamechar12.png"));
        Image img2 = im2.getImage();
        Image newimg2 = img2.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(newimg2);
        jLabel12.setIcon(image);
        jLabel13.setIcon(image2);
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
        jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat", value = "Value";
            
            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
//                        try 
//                        {
//                            Thread.sleep(3000);                 //5000 milliseconds is five second.
//                        } 
//                        catch(InterruptedException ex) {
//                            Thread.currentThread().interrupt();
//                        }
                        gbk=data[1];
                        name = tf_username.getText();
                        if(gbk.equals("gunting") || gbk.equals("batu") || gbk.equals("kertas") || gbk.equals("KALAH.") || gbk.equals("TIE.") || gbk.equals("INVALID.") || gbk.equals("LOSE.") || gbk.equals("WIN.")){
//                            try 
//                            {
//                                Thread.sleep(3000);                 //5000 milliseconds is five second.
//                            } 
//                            catch(InterruptedException ex) {
//                                Thread.currentThread().interrupt();
//                            }
                            if(data[0].equals(name)){
                                if(data[1].equals("KALAH.")){
                                    jLabel3.setText("KALAH");
                                    ImageIcon im = new ImageIcon(getClass().getResource("/images/gamechar22.png"));
                                    Image img = im.getImage();
                                    Image newimg = img.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
                                    ImageIcon image = new ImageIcon(newimg);
                                    jLabel12.setIcon(image);
                                    ImageIcon im2 = new ImageIcon(getClass().getResource("/images/gamechar12.png"));
                                    Image img2 = im2.getImage();
                                    Image newimg2 = img2.getScaledInstance(jLabel13.getWidth(), jLabel13.getHeight(), Image.SCALE_SMOOTH);
                                    ImageIcon image2 = new ImageIcon(newimg2);
                                    jLabel13.setIcon(image2);
                                }else if(gbk.equals("gunting") || gbk.equals("batu") || gbk.equals("kertas")){
                                    y=gbk;
                                    jLabel1.setText(gbk);
//                                    ImageIcon im = new ImageIcon(getClass().getResource("/images/"+gbk+".png"));
//                                    Image img = im.getImage();
//                                    Image newimg = img.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
//                                    ImageIcon image = new ImageIcon(newimg);
//                                    jLabel4.setIcon(image);
                                    jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/"+gbk+".gif")));
                                    if(x != null){
//                                        ImageIcon im2 = new ImageIcon(getClass().getResource("/images/"+x+".png"));
//                                        Image img2 = im2.getImage();
//                                        Image newimg2 = img2.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH);
//                                        ImageIcon image2 = new ImageIcon(newimg2);
//                                        jLabel5.setIcon(image2);
                                        jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/"+x+".gif")));
                                        jLabel2.setText(x);
                                        x=null;
                                        y=null;
                                    }else{
                                        jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));                                        
                                    }                                    
                                }else{
//                                    jLabel1.setText("");
//                                    jLabel2.setText("");
                                    InputStream music;
                                    if(gbk.equals("LOSE.")){
                                        jLabel3.setText("LOSE");
                                        music = new FileInputStream(new File("src\\audio\\sound_lose.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                        if(jLabel24.getBackground()==red){
                                            if(jLabel23.getBackground()==red){
                                                jLabel22.setBackground(Color.red);                                            
                                            }
                                            jLabel23.setBackground(Color.red);                                            
                                        }
                                        jLabel24.setBackground(Color.red);
                                        if(jLabel22.getBackground()==red && jLabel23.getBackground()==red && jLabel24.getBackground()==red){
                                            try 
                                            {
                                                Thread.sleep(500); //5000 milliseconds is five second.
                                            } 
                                            catch(InterruptedException ex) {
                                                Thread.currentThread().interrupt();
                                            }
                                            jLabel22.setBackground(Color.CYAN);
                                            jLabel23.setBackground(Color.CYAN);
                                            jLabel24.setBackground(Color.CYAN);
                                            jLabel25.setBackground(Color.CYAN);
                                            jLabel26.setBackground(Color.CYAN);
                                            jLabel27.setBackground(Color.CYAN);
                                        } 
                                    }else if(gbk.equals("WIN.")){
                                        jLabel3.setText("WIN");
                                        music = new FileInputStream(new File("src\\audio\\sound_win.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                        if(jLabel25.getBackground()==red){
                                            if(jLabel26.getBackground()==red){
                                                jLabel27.setBackground(Color.red);                                            
                                            }
                                            jLabel26.setBackground(Color.red);                                            
                                        }
                                        jLabel25.setBackground(Color.red);
                                        if(jLabel25.getBackground()==red && jLabel26.getBackground()==red && jLabel27.getBackground()==red){
                                            try 
                                            {
                                                Thread.sleep(500); //5000 milliseconds is five second.
                                            } 
                                            catch(InterruptedException ex) {
                                                Thread.currentThread().interrupt();
                                            }
                                            jLabel22.setBackground(Color.CYAN);
                                            jLabel23.setBackground(Color.CYAN);
                                            jLabel24.setBackground(Color.CYAN);
                                            jLabel25.setBackground(Color.CYAN);
                                            jLabel26.setBackground(Color.CYAN);
                                            jLabel27.setBackground(Color.CYAN);
                                        }
                                    }else if(gbk.equals("TIE.")){
                                        jLabel3.setText("TIE");
                                        music = new FileInputStream(new File("src\\audio\\sound_draw.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                    }else{
                                        jLabel3.setText("");
                                    }
                                }
                            }else{
                                if(data[1].equals("KALAH.")){
                                    jLabel3.setText("MENANG");
                                    ImageIcon im = new ImageIcon(getClass().getResource("/images/gamechar2.png"));
                                    Image img = im.getImage();
                                    Image newimg = img.getScaledInstance(jLabel13.getWidth(), jLabel13.getHeight(), Image.SCALE_SMOOTH);
                                    ImageIcon image = new ImageIcon(newimg);
                                    jLabel13.setIcon(image);
                                    ImageIcon im2 = new ImageIcon(getClass().getResource("/images/gamechar1.png"));
                                    Image img2 = im2.getImage();
                                    Image newimg2 = img2.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
                                    ImageIcon image2 = new ImageIcon(newimg2);
                                    jLabel12.setIcon(image2);
                                }else if(gbk.equals("gunting") || gbk.equals("batu") || gbk.equals("kertas")){
                                    x=gbk;
                                    jLabel2.setText("set");
                                    if(y != null){
//                                        ImageIcon im = new ImageIcon(getClass().getResource("/images/"+gbk+".png"));
//                                        Image img = im.getImage();
//                                        Image newimg = img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH);
//                                        ImageIcon image = new ImageIcon(newimg);
//                                        jLabel5.setIcon(image);
                                        jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/"+gbk+".gif")));
                                        jLabel2.setText(x);
                                        x=null;
                                        y=null;
                                    }else{
                                        jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));                                        
                                    }
                                }else{
                                    InputStream music;
//                                    jLabel1.setText("");
//                                    jLabel2.setText("");
                                    if(gbk.equals("LOSE.")){
                                        jLabel3.setText("WIN");
                                        music = new FileInputStream(new File("src\\audio\\sound_win.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                        if(jLabel25.getBackground()==red){
                                            if(jLabel26.getBackground()==red){
                                                jLabel27.setBackground(Color.red);                                            
                                            }
                                            jLabel26.setBackground(Color.red);                                            
                                        }
                                        jLabel25.setBackground(Color.red);
                                        if(jLabel25.getBackground()==red && jLabel26.getBackground()==red && jLabel27.getBackground()==red){
                                            try 
                                            {
                                                Thread.sleep(500); //5000 milliseconds is five second.
                                            } 
                                            catch(InterruptedException ex) {
                                                Thread.currentThread().interrupt();
                                            }
                                            jLabel22.setBackground(Color.CYAN);
                                            jLabel23.setBackground(Color.CYAN);
                                            jLabel24.setBackground(Color.CYAN);
                                            jLabel25.setBackground(Color.CYAN);
                                            jLabel26.setBackground(Color.CYAN);
                                            jLabel27.setBackground(Color.CYAN);
                                        }
                                    }else if(gbk.equals("WIN.")){
                                        jLabel3.setText("LOSE");
                                        music = new FileInputStream(new File("src\\audio\\sound_lose.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                        if(jLabel24.getBackground()==red){
                                            if(jLabel23.getBackground()==red){
                                                jLabel22.setBackground(Color.red);                                            
                                            }
                                            jLabel23.setBackground(Color.red);                                            
                                        }
                                        jLabel24.setBackground(Color.red);
                                        if(jLabel22.getBackground()==red && jLabel23.getBackground()==red && jLabel24.getBackground()==red){
                                            try 
                                            {
                                                Thread.sleep(500); //5000 milliseconds is five second.
                                            } 
                                            catch(InterruptedException ex) {
                                                Thread.currentThread().interrupt();
                                            }
                                            jLabel22.setBackground(Color.CYAN);
                                            jLabel23.setBackground(Color.CYAN);
                                            jLabel24.setBackground(Color.CYAN);
                                            jLabel25.setBackground(Color.CYAN);
                                            jLabel26.setBackground(Color.CYAN);
                                            jLabel27.setBackground(Color.CYAN);
                                        }
                                    }else if(gbk.equals("TIE.")){
                                        jLabel3.setText("TIE");
                                        music = new FileInputStream(new File("src\\audio\\sound_draw.wav"));
                                        AudioStream audios=new AudioStream(music);
                                        AudioPlayer.player.start(audios);
                                    }else{
                                        jLabel3.setText("");                                        
                                    }
                                }
//                                System.out.println(x);
                            }
                        }else{
                            ta_chat.append(data[0] + ": " + data[1] + "\n");
                            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());                            
                        }
                     }
                     else if (data[2].equals(value))
                     {
                         ImageIcon im = new ImageIcon(getClass().getResource("/images/heart3.png"));
                         Image img = im.getImage();
                         Image newimg = img.getScaledInstance(jLabel16.getWidth(), jLabel16.getHeight(), Image.SCALE_SMOOTH);
                         ImageIcon image = new ImageIcon(newimg);
                         
                         InputStream music;
                         music = new FileInputStream(new File("src\\audio\\punch.wav"));
                         AudioStream audios=new AudioStream(music);
                         AudioPlayer.player.start(audios);
                         
                         if(data[0].equals(name)){
                            if(Integer.parseInt(data[1])==3){
                                jLabel16.setIcon(image);
                                jLabel17.setIcon(image);
                                jLabel18.setIcon(image);
                                jLabel19.setIcon(image);
                                jLabel20.setIcon(image);
                                jLabel21.setIcon(image);
                            }else if(Integer.parseInt(data[1])==2){
                                jLabel16.setIcon(image);
                                jLabel17.setIcon(image);
                                jLabel18.setIcon(null);
                                AudioPlayer.player.start(audios);
                            }else if(Integer.parseInt(data[1])==1){
                                jLabel16.setIcon(image);;
                                jLabel17.setIcon(null);
                                jLabel18.setIcon(null);
                                AudioPlayer.player.start(audios);
                            }else {
                                jLabel16.setIcon(null);
                                jLabel17.setIcon(null);
                                jLabel18.setIcon(null);
                                jLabel3.setText("LOSER");
                                AudioPlayer.player.start(audios);
                                for (int i=3; i>=0; i--){
                                    try 
                                    {
                                        Thread.sleep(1000); //5000 milliseconds is five second.
                                    } 
                                    catch(InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }
                                    jLabel3.setText(Integer.toString(i));
                                }
                                jLabel3.setText("");
                                ImageIcon im3 = new ImageIcon(getClass().getResource("/images/gamechar12.png"));
                                Image img3 = im3.getImage();
                                Image newimg3 = img3.getScaledInstance(jLabel13.getWidth(), jLabel13.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image3 = new ImageIcon(newimg3);
                                jLabel13.setIcon(image3);
                                ImageIcon im2 = new ImageIcon(getClass().getResource("/images/gamechar1.png"));
                                Image img2 = im2.getImage();
                                Image newimg2 = img2.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image2 = new ImageIcon(newimg2);
                                jLabel12.setIcon(image2);
                                jLabel1.setText("");
                                jLabel2.setText("");
                                jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
                                jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
                            }
                         }else{
                            if(Integer.parseInt(data[1])==3){
                                jLabel16.setIcon(image);
                                jLabel17.setIcon(image);
                                jLabel18.setIcon(image);
                                jLabel19.setIcon(image);
                                jLabel20.setIcon(image);
                                jLabel21.setIcon(image);
                            }else if(Integer.parseInt(data[1])==2){
                                jLabel19.setIcon(image);
                                jLabel20.setIcon(image);
                                jLabel21.setIcon(null);
                                AudioPlayer.player.start(audios);
                            }else if(Integer.parseInt(data[1])==1){
                                jLabel19.setIcon(image);
                                jLabel20.setIcon(null);
                                jLabel21.setIcon(null);
                                AudioPlayer.player.start(audios);
                            }else {
                                jLabel19.setIcon(null);
                                jLabel20.setIcon(null);
                                jLabel21.setIcon(null);
                                jLabel3.setText("WINNER");
                                AudioPlayer.player.start(audios);
                                for (int i=3; i>=0; i--){
                                    try 
                                    {
                                        Thread.sleep(1000); //5000 milliseconds is five second.
                                    } 
                                    catch(InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                    }
                                    jLabel3.setText(Integer.toString(i));
                                }
                                jLabel3.setText("");
                                ImageIcon im3 = new ImageIcon(getClass().getResource("/images/gamechar12.png"));
                                Image img3 = im3.getImage();
                                Image newimg3 = img3.getScaledInstance(jLabel13.getWidth(), jLabel13.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image3 = new ImageIcon(newimg3);
                                jLabel13.setIcon(image3);
                                ImageIcon im2 = new ImageIcon(getClass().getResource("/images/gamechar1.png"));
                                Image img2 = im2.getImage();
                                Image newimg2 = img2.getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image2 = new ImageIcon(newimg2);
                                jLabel12.setIcon(image2);
                                jLabel1.setText("");
                                jLabel2.setText("");
                                jLabel4.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
                                jLabel5.setIcon(new ImageIcon(getClass().getResource("/images/load.gif")));
                            } 
                         }
//                        String j="j";
//                        for (int i=0; i<Integer.parseInt(data[1]); i++){
//                            System.out.println(j);
//                            jLabel14.setText(j);
//                        }
//                        System.out.println("");
                     }
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GBK - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(625, 566));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_address.setForeground(new java.awt.Color(255, 255, 255));
        lb_address.setText("Address  :");
        getContentPane().add(lb_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 60, 20));

        tf_address.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        tf_address.setForeground(new java.awt.Color(255, 153, 0));
        tf_address.setText("localhost");
        tf_address.setBorder(null);
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });
        getContentPane().add(tf_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 89, -1));

        lb_port.setForeground(new java.awt.Color(255, 255, 255));
        lb_port.setText("Port    :");
        getContentPane().add(lb_port, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 40, 20));

        tf_port.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        tf_port.setForeground(new java.awt.Color(255, 153, 0));
        tf_port.setText("2222");
        tf_port.setBorder(null);
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });
        getContentPane().add(tf_port, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 89, -1));

        lb_username.setForeground(new java.awt.Color(255, 255, 255));
        lb_username.setText("Username  :");
        getContentPane().add(lb_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 70, 20));

        tf_username.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        tf_username.setForeground(new java.awt.Color(255, 153, 0));
        tf_username.setBorder(null);
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(tf_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 89, -1));

        b_connect.setBackground(new java.awt.Color(0, 204, 204));
        b_connect.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        b_connect.setForeground(new java.awt.Color(255, 255, 255));
        b_connect.setText("Connect");
        b_connect.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b_connect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_connectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_connectMouseExited(evt);
            }
        });
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });
        getContentPane().add(b_connect, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 80, 30));

        b_disconnect.setBackground(new java.awt.Color(255, 31, 90));
        b_disconnect.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        b_disconnect.setForeground(new java.awt.Color(255, 255, 255));
        b_disconnect.setText("Disconnect");
        b_disconnect.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b_disconnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_disconnectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_disconnectMouseExited(evt);
            }
        });
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });
        getContentPane().add(b_disconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 100, 30));

        jScrollPane1.setBorder(null);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.setBorder(null);
        jScrollPane1.setViewportView(ta_chat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 460, 84));

        tf_chat.setBorder(null);
        tf_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_chatActionPerformed(evt);
            }
        });
        tf_chat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_chatKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_chatKeyTyped(evt);
            }
        });
        getContentPane().add(tf_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 352, 20));

        b_send.setBackground(new java.awt.Color(51, 255, 153));
        b_send.setFont(new java.awt.Font("Kristen ITC", 1, 11)); // NOI18N
        b_send.setForeground(new java.awt.Color(255, 255, 255));
        b_send.setText("SEND");
        b_send.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b_send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_sendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_sendMouseExited(evt);
            }
        });
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
        getContentPane().add(b_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 100, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 100, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 100, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 260, 58));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("VS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 90, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gameicon.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 140));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kertas_2.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 500, 100, 90));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gunting_2.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 100, 90));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/batu_2.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 100, 90));

        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 60, 100));

        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 60, 100));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 0));
        jLabel14.setText("PLAYER 1");
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 0));
        jLabel15.setText("PLAYER 2");
        jLabel15.setOpaque(true);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 20, 20));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 20, 20));
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 20, 20));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 20, 20));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 20, 20));
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 20, 20));

        jLabel22.setBackground(new java.awt.Color(0, 255, 255));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setOpaque(true);
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 20, 20));

        jLabel23.setBackground(new java.awt.Color(0, 255, 255));
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setOpaque(true);
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 270, 20, 20));

        jLabel24.setBackground(new java.awt.Color(0, 255, 255));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setOpaque(true);
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 20, 20));

        jLabel25.setBackground(new java.awt.Color(0, 255, 255));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setOpaque(true);
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 20, 20));

        jLabel26.setBackground(new java.awt.Color(0, 255, 255));
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setOpaque(true);
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 270, 20, 20));

        jLabel27.setBackground(new java.awt.Color(0, 255, 255));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setOpaque(true);
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 20, 20));

        jLabel6.setBackground(new java.awt.Color(0, 204, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg4.jpg"))); // NOI18N
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 558, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            address = tf_address.getText();
            port = Integer.parseInt(tf_port.getText());
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void tf_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_chatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_chatActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        gbk="gunting";
        InputStream music;
        try{
            writer.println(username + ":" + gbk + ":" + "Chat");
            writer.flush();
            music = new FileInputStream(new File("src\\audio\\punch.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){
            ta_chat.append("Message was not sent. \n");
        }        
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        gbk="batu";
        InputStream music;
        try{
            writer.println(username + ":" + gbk + ":" + "Chat");
            writer.flush();
            music = new FileInputStream(new File("src\\audio\\punch.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){
            ta_chat.append("Message was not sent. \n");
        }        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        gbk="kertas";
        InputStream music;
        try{
            writer.println(username + ":" + gbk + ":" + "Chat");
            writer.flush();
            music = new FileInputStream(new File("src\\audio\\punch.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){
            ta_chat.append("Message was not sent. \n");
        }        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        // TODO add your handling code here:
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gunting_2biru.png")));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        // TODO add your handling code here:
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gunting_2.png")));
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kertas_2biru.png")));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kertas_2.png")));
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        // TODO add your handling code here:
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/batu_2biru.png")));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/batu_2.png")));
    }//GEN-LAST:event_jLabel11MouseExited

    private void tf_chatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_chatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_chatKeyTyped

    private void tf_chatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_chatKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String nothing = "";
            if ((tf_chat.getText()).equals(nothing)) {
                tf_chat.setText("");
                tf_chat.requestFocus();
            } else {
                try {
                   writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                   writer.flush(); // flushes the buffer
                } catch (Exception ex) {
                    ta_chat.append("Message was not sent. \n");
                }
                tf_chat.setText("");
                tf_chat.requestFocus();
            }

            tf_chat.setText("");
            tf_chat.requestFocus();
        }
    }//GEN-LAST:event_tf_chatKeyPressed

    private void b_connectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_connectMouseEntered
        // TODO add your handling code here:
        b_connect.setBackground(new Color(255,255,255));
        b_connect.setForeground(new Color(0,204,204));
        b_connect.setBorder(new LineBorder(new Color(0,204,204)));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}
    }//GEN-LAST:event_b_connectMouseEntered

    private void b_connectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_connectMouseExited
        // TODO add your handling code here:
        b_connect.setBackground(new Color(0,204,204));
        b_connect.setForeground(new Color(255,255,255));
        b_connect.setBorder(new LineBorder(new Color(255,255,255)));        
    }//GEN-LAST:event_b_connectMouseExited

    private void b_disconnectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_disconnectMouseEntered
        // TODO add your handling code here:
        b_disconnect.setBackground(new Color(255,255,255));
        b_disconnect.setForeground(new Color(255,31,90));
        b_disconnect.setBorder(new LineBorder(new Color(255,31,90)));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}        
    }//GEN-LAST:event_b_disconnectMouseEntered

    private void b_disconnectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_disconnectMouseExited
        // TODO add your handling code here:
        b_disconnect.setBackground(new Color(255,31,90));
        b_disconnect.setForeground(new Color(255,255,255));
        b_disconnect.setBorder(new LineBorder(new Color(255,255,255)));
    }//GEN-LAST:event_b_disconnectMouseExited

    private void b_sendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_sendMouseEntered
        // TODO add your handling code here:
        b_send.setBackground(new Color(255,255,255));
        b_send.setForeground(new Color(51,255,153));
        b_send.setBorder(new LineBorder(new Color(51,255,153)));
        InputStream music;
        try{
            music = new FileInputStream(new File("src\\audio\\bow.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }catch (Exception ex){}        
    }//GEN-LAST:event_b_sendMouseEntered

    private void b_sendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_sendMouseExited
        // TODO add your handling code here:
        b_send.setBackground(new Color(51,255,153));
        b_send.setForeground(new Color(255,255,255));
        b_send.setBorder(new LineBorder(new Color(255,255,255)));
    }//GEN-LAST:event_b_sendMouseExited

    public static void main(String args[]) throws UnsupportedAudioFileException, LineUnavailableException 
    {
        try {

            InputStream input = client_frame.class.getResource("/audio/sound_awal.wav").openStream();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(input);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
