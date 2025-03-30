/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userPackage;

import config.ImageHandler;
import config.dbConnector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import loginReg.loginform;

/**
 *
 * @author PATRICIA
 */
public class userSettings extends javax.swing.JFrame {
       private String fullname;
       private static String userImagePath = null;
       
    /**
     * Creates new form userSettings
     */
    public userSettings(String fullname, String imgPath) {
        initComponents();
        setSize(970, 590);
        setResizable(false);
        
        
        this.fullname = fullname;
        displayData();
        loadUserData(fullname);
        
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
    }
    
    Color navcolor = new Color(204,255,204);
    Color headcolor = new Color(0,51,51);
    Color bodycolor = new Color(0,153,153);
    
    
    private String saveImageToFolder(String email) {
        try {
            // Convert JLabel Icon to BufferedImage
            Icon icon = profile.getIcon();
            if (icon instanceof ImageIcon) {
                Image image = ((ImageIcon) icon).getImage();
                BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bufferedImage.createGraphics();
                g2.drawImage(image, 0, 0, null);
                g2.dispose();

                // Define Folder and File Name
                String folderPath = "src/usersImages";
                File directory = new File(folderPath);
                if (!directory.exists()) {
                    directory.mkdir(); // Create folder if not exists
                }

                // Save image with unique name
                String filePath = folderPath + email + ".jpg";
                File outputFile = new File(filePath);
                ImageIO.write(bufferedImage, "jpg", outputFile);

                return filePath; 
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
   private void loadUserData(String fullname) {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        String query = "SELECT u_image FROM user_table WHERE CONCAT(firstName, ' ', lastName) = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fullname);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String imagePath = rs.getString("u_image");

                    // Load image from file
                    if (imagePath != null && !imagePath.isEmpty()) {
                        File file = new File(imagePath);
                        if (file.exists()) {
                            ImageIcon icon = new ImageIcon(imagePath);
                            Image img = icon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
                            profile.setIcon(new ImageIcon(img));
                        } else {
                            JOptionPane.showMessageDialog(this, "Image file not found: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    
    

    
     public void displayData() {
   try {
            dbConnector dbc = new dbConnector();
            Connection conn = dbc.getConnection(); 

            String query = "SELECT * FROM user_table WHERE CONCAT(firstName, ' ', lastName) = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fullname);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                enteremail.setText(rs.getString("email"));
                enteremail.setEditable(false);
                enterfn.setText(rs.getString("firstName"));
                enterln.setText(rs.getString("lastName"));
                enterpass.setText("********"); 
        } else {
            System.out.println("No user data found.");
        }

        rs.close();
        pstmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    public static String passwordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA"); // Use SHA-256
            md.update(password.getBytes());
            byte[] rbt = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : rbt) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Log the error properly
            return null;
        }
    }

     
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        profile1 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        profile3 = new javax.swing.JLabel();
        fn1 = new javax.swing.JLabel();
        email4 = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        enterpass = new javax.swing.JTextField();
        enterln = new javax.swing.JTextField();
        enteremail = new javax.swing.JTextField();
        changepass = new javax.swing.JPanel();
        email1 = new javax.swing.JLabel();
        logoutbutton = new javax.swing.JPanel();
        email2 = new javax.swing.JLabel();
        ln5 = new javax.swing.JLabel();
        profile4 = new javax.swing.JLabel();
        enterfn = new javax.swing.JTextField();
        select = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(0, 51, 51));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACCOUNT SETTINGS");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 4, 900, 50));

        back.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-left-50.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });
        header.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 40));

        main.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 60));

        profile1.setBackground(new java.awt.Color(255, 255, 255));
        profile1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profile1.setText("Looker user Gravatar for profile picture.");
        main.add(profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 250, 40));

        profile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-male-user-100.png"))); // NOI18N
        main.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 190, 150));

        profile3.setBackground(new java.awt.Color(255, 255, 255));
        profile3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        profile3.setForeground(new java.awt.Color(255, 255, 255));
        profile3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile3.setText("Personal Information");
        main.add(profile3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 260, 40));

        fn1.setBackground(new java.awt.Color(255, 255, 255));
        fn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fn1.setForeground(new java.awt.Color(255, 255, 255));
        fn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fn1.setText("First Name:");
        main.add(fn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 110, 40));

        email4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email4.setForeground(new java.awt.Color(255, 255, 255));
        email4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email4.setText("Email:");
        main.add(email4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 100, 40));

        pass.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pass.setText("Password:");
        main.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 100, 30));

        enterpass.setBackground(new java.awt.Color(204, 204, 204));
        enterpass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterpass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterpassMouseClicked(evt);
            }
        });
        enterpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterpassActionPerformed(evt);
            }
        });
        main.add(enterpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 200, 30));

        enterln.setBackground(new java.awt.Color(204, 204, 204));
        enterln.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterln.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterlnMouseClicked(evt);
            }
        });
        enterln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterlnActionPerformed(evt);
            }
        });
        main.add(enterln, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 180, 40));

        enteremail.setBackground(new java.awt.Color(204, 204, 204));
        enteremail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enteremail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enteremail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enteremailMouseClicked(evt);
            }
        });
        enteremail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enteremailActionPerformed(evt);
            }
        });
        main.add(enteremail, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 480, 40));

        changepass.setBackground(new java.awt.Color(204, 255, 204));
        changepass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changepassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changepassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changepassMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changepassMousePressed(evt);
            }
        });

        email1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        email1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email1.setText("Change Password");

        javax.swing.GroupLayout changepassLayout = new javax.swing.GroupLayout(changepass);
        changepass.setLayout(changepassLayout);
        changepassLayout.setHorizontalGroup(
            changepassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(email1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        changepassLayout.setVerticalGroup(
            changepassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changepassLayout.createSequentialGroup()
                .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        main.add(changepass, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 120, 30));

        logoutbutton.setBackground(new java.awt.Color(204, 255, 204));
        logoutbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutbuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutbuttonMouseExited(evt);
            }
        });

        email2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email2.setText("Logout");

        javax.swing.GroupLayout logoutbuttonLayout = new javax.swing.GroupLayout(logoutbutton);
        logoutbutton.setLayout(logoutbuttonLayout);
        logoutbuttonLayout.setHorizontalGroup(
            logoutbuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(email2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        logoutbuttonLayout.setVerticalGroup(
            logoutbuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoutbuttonLayout.createSequentialGroup()
                .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        main.add(logoutbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 160, 30));

        ln5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln5.setForeground(new java.awt.Color(255, 255, 255));
        ln5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln5.setText("Last Name:");
        main.add(ln5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 100, 40));

        profile4.setBackground(new java.awt.Color(255, 255, 255));
        profile4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profile4.setForeground(new java.awt.Color(255, 255, 255));
        profile4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile4.setText("Profile Picture:");
        main.add(profile4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 130, 40));

        enterfn.setBackground(new java.awt.Color(204, 204, 204));
        enterfn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterfn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterfn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterfnMouseClicked(evt);
            }
        });
        enterfn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterfnActionPerformed(evt);
            }
        });
        main.add(enterfn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 200, 40));

        select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("Select File");
        select.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        select.setOpaque(true);
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMouseExited(evt);
            }
        });
        main.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Update Profile");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        new userDashboard(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered

    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited

    }//GEN-LAST:event_backMouseExited

    private void enterpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterpassMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_enterpassMouseClicked

    private void enterpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterpassActionPerformed

    private void enterlnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterlnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_enterlnMouseClicked

    private void enterlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterlnActionPerformed

    private void enteremailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enteremailMouseClicked

    }//GEN-LAST:event_enteremailMouseClicked

    private void enteremailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enteremailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enteremailActionPerformed

    private void changepassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepassMouseClicked
        String email = enteremail.getText().trim();
        String password = passwordHash(enterpass.getText()).trim();

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/obaob_db", "root", "");
            
            String hashedPassword = passwordHash(password);
            
            String sql = "UPDATE user_table SET password = ? WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, email);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                enterpass.setText("********");
            } else {
                JOptionPane.showMessageDialog(this, "Error: User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_changepassMouseClicked

    private void changepassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepassMouseEntered
        changepass.setBackground(bodycolor);
    }//GEN-LAST:event_changepassMouseEntered

    private void changepassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepassMouseExited
        changepass.setBackground(navcolor);
    }//GEN-LAST:event_changepassMouseExited

    private void changepassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepassMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_changepassMousePressed

    private void logoutbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbuttonMouseClicked
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to log out?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();

            new loginform().setVisible(true);
        }

    }//GEN-LAST:event_logoutbuttonMouseClicked

    private void logoutbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbuttonMouseEntered
        logoutbutton.setBackground(bodycolor);
    }//GEN-LAST:event_logoutbuttonMouseEntered

    private void logoutbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbuttonMouseExited
        logoutbutton.setBackground(navcolor);
    }//GEN-LAST:event_logoutbuttonMouseExited

    private void enterfnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterfnMouseClicked

    }//GEN-LAST:event_enterfnMouseClicked

    private void enterfnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterfnActionPerformed

    }//GEN-LAST:event_enterfnActionPerformed

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        ImageHandler.chooseImage(profile);
    }//GEN-LAST:event_selectMouseClicked

    private void selectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseEntered

    }//GEN-LAST:event_selectMouseEntered

    private void selectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseExited

    }//GEN-LAST:event_selectMouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String email = enteremail.getText();

        if (!email.toLowerCase().endsWith(".com")) {
            JOptionPane.showMessageDialog(this, "Email must be valid. Please enter a valid email account.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/obaob_db";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String selectQuery = "SELECT email FROM user_table WHERE email = ?";
            try (PreparedStatement selectPstmt = conn.prepareStatement(selectQuery)) {
                selectPstmt.setString(1, email);
                try (ResultSet rs = selectPstmt.executeQuery()) {
                    if (rs.next()) {
                        // Save Image
                        String imagePath = saveImageToFolder(email); // Save and get the file path

                        // Update user details along with the image path
                        String updateQuery = "UPDATE user_table SET  u_image = ? WHERE email = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                            updatePstmt.setString(1, imagePath);
                            updatePstmt.setString(2, email);

                            int rowsUpdated = updatePstmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(this, "Account information updated successfully!");
                            } else {
                                JOptionPane.showMessageDialog(this, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String imgPath = "path/to/default/image.png";
                new userSettings("Regular User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel changepass;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email4;
    private javax.swing.JTextField enteremail;
    private javax.swing.JTextField enterfn;
    private javax.swing.JTextField enterln;
    private javax.swing.JTextField enterpass;
    private javax.swing.JLabel fn1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel ln5;
    private javax.swing.JPanel logoutbutton;
    private javax.swing.JPanel main;
    private javax.swing.JLabel pass;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JLabel profile3;
    private javax.swing.JLabel profile4;
    private javax.swing.JLabel select;
    // End of variables declaration//GEN-END:variables
}
