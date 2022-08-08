package GBK_server;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JLabel;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   private String a=null, b=null, d=null, e=null;
   private Integer skor1=3, skor2=3, heart1=3, heart2=3;

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat", value="Value";
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    System.out.print("\n");
                    System.out.print(data[0] + "-");
                    System.out.print(data[1] + "-");
                    System.out.print(data[2]);
                    
                    for (String token:data) 
                    {
                        ta_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        if(a != null){
                            b=data[0];                            
                        }else{
                            a=data[0];
                        }
                        userAdd(data[0]);
                        tellEveryone((data[0] + ":" + heart1 + ":" + value));
                        tellEveryone((data[0] + ":" + heart2 + ":" + value));
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        if(data[0].equals(a)){
                            System.out.println("bebek : "+data[1]);
                            d=data[1];
                            if(d != null && e != null){
                                System.out.println("2");
                                if(d.equals("gunting") && e.equals("gunting")){
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
//                                    message=data[0]+":TIE."+":"+chat;
//                                    ta_chat.append("TIE\n");
                                }else if(d.equals("batu") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
                                }else if(d.equals("kertas") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
                                }else if(d.equals("gunting") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor1 = skor1 - 1;
                                    System.out.print(skor1);
                                }else if(d.equals("gunting") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else if(d.equals("batu") && e.equals("gunting")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else if(d.equals("batu") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor1 = skor1-1;
                                    System.out.print(skor1);
                                }else if(d.equals("kertas") && e.equals("gunting")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor1 = skor1-1;
                                    System.out.print(skor1);
                                }else if(d.equals("kertas") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else{
                                    tellEveryone((data[0] + ":INVALID." + ":" + chat));
                                }
                                d=null;
                                e=null;
                            }else{
                                System.out.println("1");
                            }
                        }else if(data[0].equals(b)){
                            System.out.println("ayam : "+data[1]);
                            e=data[1];
                            if(d != null && e != null){
                                System.out.println("2");
                                if(d.equals("gunting") && e.equals("gunting")){
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
//                                    ta_chat.append("TIE\n");
                                }else if(d.equals("batu") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
                                }else if(d.equals("kertas") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":TIE." + ":" + chat));
                                }else if(d.equals("gunting") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor1 = skor1-1;
                                    System.out.print(skor1);
                                }else if(d.equals("gunting") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else if(d.equals("batu") && e.equals("gunting")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else if(d.equals("batu") && e.equals("kertas")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor1 = skor1-1;
                                    System.out.print(skor1);
                                }else if(d.equals("kertas") && e.equals("gunting")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":WIN." + ":" + chat));
                                    skor1 = skor1-1;
                                    System.out.print(skor1);
                                }else if(d.equals("kertas") && e.equals("batu")){
//                                    ta_chat.append(data[0] + " WIN\n");
                                    tellEveryone((data[0] + ":LOSE." + ":" + chat));
                                    skor2 = skor2-1;
                                    System.out.print(skor2);
                                }else{
                                    tellEveryone((data[0] + ":INVALID." + ":" + chat));
                                }
                                d=null;
                                e=null;
                            }else{
                                System.out.println("1");
                            }
                        }else {
                            System.out.println("ayam bebek");
                        }
                        tellEveryone(message);
                        
                        if(skor1==0){
                            System.out.print(a + " KALAH");
                            tellEveryone((a + ":KALAH." + ":" + chat));
                            skor1=3;
                            heart1=heart1-1;
                            tellEveryone((a + ":" + heart1 + ":" + value));
                            if(heart1==0){
                                heart1=3;
                                try 
                                {
                                    Thread.sleep(3000); //5000 milliseconds is five second.
                                } 
                                catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                                tellEveryone((a + ":" + heart1 + ":" + value));
                            }
                        }
                            
                        if(skor2==0){
                            System.out.print(b + " KALAH");
                            tellEveryone((b + ":KALAH." + ":" + chat));
                            skor2=3;
                            heart2=heart2-1;
                            tellEveryone((b + ":" + heart2 + ":" + value));
                            if(heart2==0){
                                heart2=3;
                                try 
                                {
                                    Thread.sleep(3000); //5000 milliseconds is five second.
                                } 
                                catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                                tellEveryone((b + ":" + heart2 + ":" + value));
                            }
                        }
                    } 
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GBK - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_users.setText("Online Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });

        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_users, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_start)
                    .addComponent(b_users))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_clear)
                    .addComponent(b_end))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
//        try 
//        {
//            Thread.sleep(5000);                 //5000 milliseconds is five second.
//        } 
//        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }//GEN-LAST:event_b_usersActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) throws InterruptedException 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        ta_chat.append("Before " + name + " added. \n");
        users.add(name);
//        if(a==null){
//            a=name;
//            System.out.print("a: "+a);
//        }else{
//            b=name;
//            System.out.print("b: "+b);
//        }
        ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();
        
//        String[] data = message.split(":");
//        if(data[1].equals("TIE.") || data[1].equals("WIN.") || data[1].equals("LOSE.") || data[1].equals("INVALID.")){
//            try 
//            {
//                Thread.sleep(3000);                 //5000 milliseconds is five second.
//            } 
//            catch(InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }            
//        }

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.append("Sending: " + message + "\n");
//                try{
//                    Thread.sleep(3000);                 //5000 milliseconds is five second.
//                }catch(InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }                
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
