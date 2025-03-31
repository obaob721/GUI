
package userPackage;

import config.dbConnector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import loginReg.loginform;

/**
 *
 * @author PATRICIA
 */
public class userDashboard extends javax.swing.JFrame {
       private String fullname;
       private static String userImagePath = null;
    /**
     * Creates new form userDashboard
     */
    public userDashboard(String fullname, String imgPath) {
        this.fullname = fullname;
        
        initComponents();
        adminprof.setText("" + fullname + "");
        displayTotalCitizens();
        displayBlotterCount();
        displayPendingCase();
        displaySettledCase();
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
        displayImage();
    }
     public userDashboard(){
        initComponents();
    }
     
    Color navcolor = new Color(0,51,51);
    Color headcolor = new Color(0,51,51);
    Color bodycolor = new Color(0,153,153);
    
    public void displayTotalCitizens() {
    dbConnector dbc = new dbConnector();
    String query = "SELECT COUNT(*) AS total_citizens FROM citizen_table";

    try (ResultSet rs = dbc.getData(query)) {
        if (rs.next()) {
            int total = rs.getInt("total_citizens"); 
            totalCitizensLabel.setText(String.valueOf(total)); // Display count in the label
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
     public void displayBlotterCount() {
        dbConnector dbc = new dbConnector();
        String query = "SELECT COUNT(*) AS total_blotters FROM blotter_table";

        try (ResultSet rs = dbc.getData(query)) {
            if (rs.next()) {
                int totalBlotters = rs.getInt("total_blotters");
                totalBlottersLabel.setText(String.valueOf(totalBlotters));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public void displayPendingCase() {
     dbConnector dbc = new dbConnector();
       String query = "SELECT COUNT(*) AS pending_case FROM blotter_table WHERE b_status = 'Pending'";

     try (ResultSet rs = dbc.getData(query)) {

        if (rs.next()) {
            int pendingCases = rs.getInt("pending_case");
            pendingCasesLabel.setText(String.valueOf(pendingCases)); 
        }

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
      public void displaySettledCase() {
     dbConnector dbc = new dbConnector();
       String query = "SELECT COUNT(*) AS settled_case FROM blotter_table WHERE b_status = 'Settled'";

     try (ResultSet rs = dbc.getData(query)) {

        if (rs.next()) {
            int settledCases = rs.getInt("settled_case");
            settledCasesLabel.setText(String.valueOf(settledCases)); 
        }

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
     
       private void displayImage() {
        if (userImagePath != null && !userImagePath.isEmpty()) {
            updateProfilePicture(userImagePath);
        }
    }

    public void updateProfilePicture(String imgPath) {
        File imgFile = new File(imgPath);
        if (imgFile.exists()) {
            try {
                BufferedImage img = ImageIO.read(imgFile);
                ImageIcon circularImg = new ImageIcon(getRoundedImage(img, profile.getWidth(), profile.getHeight()));
                profile.setIcon(circularImg);
                profile.setText("");
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        } else {
            profile.setText("Image Not Found");
        }
    }

private Image getRoundedImage(BufferedImage img, int width, int height) {
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = output.createGraphics();
    
    // Enable anti-aliasing for smooth edges
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    // Create a circular clipping mask
    g2.setClip(new Ellipse2D.Float(0, 0, width, height));
    
    // Draw the image inside the circular area
    g2.drawImage(img, 0, 0, width, height, null);
    g2.dispose();
    
    return output;
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardPanel2 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        sumBlotter = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        totalBlottersLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sumCitizens = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        totalCitizensLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        settledcases = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        settledCasesLabel = new javax.swing.JLabel();
        pendingcases = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        pendingCasesLabel = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userprof = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        dash2 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        managecitizen = new javax.swing.JLabel();
        blotter = new javax.swing.JLabel();
        adminprof = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dashboardPanel2.setBackground(new java.awt.Color(204, 255, 204));
        dashboardPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 51, 51));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("BLOTTERMATE COMMUNITY SYSTEM");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-refresh-32.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(18, 18, 18))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dashboardPanel2.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 800, 50));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("USER DASHBOARD");
        dashboardPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 800, 60));

        sumBlotter.setBackground(new java.awt.Color(0, 51, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-strike-50.png"))); // NOI18N

        totalBlottersLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        totalBlottersLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalBlottersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalBlottersLabel.setText("0");
        totalBlottersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalBlottersLabelMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total Blotter");

        javax.swing.GroupLayout sumBlotterLayout = new javax.swing.GroupLayout(sumBlotter);
        sumBlotter.setLayout(sumBlotterLayout);
        sumBlotterLayout.setHorizontalGroup(
            sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumBlotterLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalBlottersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(36, 36, 36))
        );
        sumBlotterLayout.setVerticalGroup(
            sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumBlotterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalBlottersLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(sumBlotterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboardPanel2.add(sumBlotter, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 770, 120));

        sumCitizens.setBackground(new java.awt.Color(0, 51, 51));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-people-50.png"))); // NOI18N

        totalCitizensLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        totalCitizensLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalCitizensLabel.setText("0");
        totalCitizensLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalCitizensLabelMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Total Citizens");

        javax.swing.GroupLayout sumCitizensLayout = new javax.swing.GroupLayout(sumCitizens);
        sumCitizens.setLayout(sumCitizensLayout);
        sumCitizensLayout.setHorizontalGroup(
            sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumCitizensLayout.createSequentialGroup()
                .addGroup(sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sumCitizensLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sumCitizensLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(totalCitizensLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 529, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(37, 37, 37))
        );
        sumCitizensLayout.setVerticalGroup(
            sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumCitizensLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sumCitizensLayout.createSequentialGroup()
                        .addComponent(totalCitizensLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dashboardPanel2.add(sumCitizens, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 770, 120));

        settledcases.setBackground(new java.awt.Color(0, 51, 102));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Settled Cases ");

        javax.swing.GroupLayout settledcasesLayout = new javax.swing.GroupLayout(settledcases);
        settledcases.setLayout(settledcasesLayout);
        settledcasesLayout.setHorizontalGroup(
            settledcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        settledcasesLayout.setVerticalGroup(
            settledcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        dashboardPanel2.add(settledcases, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 350, 40));

        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 3));
        dashboardPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 350, 120));

        settledCasesLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        settledCasesLabel.setForeground(new java.awt.Color(0, 51, 102));
        settledCasesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settledCasesLabel.setText("0");
        settledCasesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settledCasesLabelMouseClicked(evt);
            }
        });
        dashboardPanel2.add(settledCasesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 350, 70));

        pendingcases.setBackground(new java.awt.Color(0, 102, 0));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Pending Cases");

        javax.swing.GroupLayout pendingcasesLayout = new javax.swing.GroupLayout(pendingcases);
        pendingcases.setLayout(pendingcasesLayout);
        pendingcasesLayout.setHorizontalGroup(
            pendingcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        pendingcasesLayout.setVerticalGroup(
            pendingcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        dashboardPanel2.add(pendingcases, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 350, 40));

        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 3));
        dashboardPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 350, 120));

        pendingCasesLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        pendingCasesLabel.setForeground(new java.awt.Color(0, 102, 0));
        pendingCasesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingCasesLabel.setText("0");
        pendingCasesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingCasesLabelMouseClicked(evt);
            }
        });
        dashboardPanel2.add(pendingCasesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 350, 70));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("BLOTTER RECORD SUMMARY");
        dashboardPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 800, 30));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userprof.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        userprof.setForeground(new java.awt.Color(255, 255, 255));
        userprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userprof.setText("User");
        jPanel1.add(userprof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 180, -1));

        settings.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        settings.setForeground(new java.awt.Color(255, 255, 255));
        settings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-settings-50.png"))); // NOI18N
        settings.setText("         Settings");
        settings.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsMouseExited(evt);
            }
        });
        jPanel1.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 50));

        dash2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dash2.setForeground(new java.awt.Color(255, 255, 255));
        dash2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-50.png"))); // NOI18N
        dash2.setText("     Dashboard");
        dash2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        dash2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dash2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dash2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dash2MouseExited(evt);
            }
        });
        jPanel1.add(dash2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, 50));

        logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-logout-50.png"))); // NOI18N
        logout.setText("      Logout");
        logout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 180, 50));

        jLabel8.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome!");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        managecitizen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        managecitizen.setForeground(new java.awt.Color(255, 255, 255));
        managecitizen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        managecitizen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-people-50.png"))); // NOI18N
        managecitizen.setText("Manage Citizen");
        managecitizen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        managecitizen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managecitizenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                managecitizenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                managecitizenMouseExited(evt);
            }
        });
        jPanel1.add(managecitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 180, 50));

        blotter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        blotter.setForeground(new java.awt.Color(255, 255, 255));
        blotter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blotter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-strike-50.png"))); // NOI18N
        blotter.setText("         Blotter");
        blotter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        blotter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blotterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                blotterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                blotterMouseExited(evt);
            }
        });
        jPanel1.add(blotter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 50));

        adminprof.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        adminprof.setForeground(new java.awt.Color(255, 255, 255));
        adminprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminprof.setText("User Name");
        adminprof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminprofMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminprofMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminprofMouseExited(evt);
            }
        });
        jPanel1.add(adminprof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, -1));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-50.png"))); // NOI18N
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 90));

        dashboardPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dash2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseClicked
        

    }//GEN-LAST:event_dash2MouseClicked

    private void managecitizenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseClicked
       new userPage(fullname, userImagePath).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_managecitizenMouseClicked

    private void managecitizenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseEntered
                managecitizen.setBackground(bodycolor);
                managecitizen.setOpaque(true);
    }//GEN-LAST:event_managecitizenMouseEntered

    private void managecitizenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseExited
                managecitizen.setBackground(navcolor);
                managecitizen.setOpaque(true);
    }//GEN-LAST:event_managecitizenMouseExited

    private void dash2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseEntered
                dash2.setBackground(bodycolor);
                dash2.setOpaque(true);
    }//GEN-LAST:event_dash2MouseEntered

    private void dash2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dash2MouseExited
                dash2.setBackground(navcolor);
                dash2.setOpaque(true);
    }//GEN-LAST:event_dash2MouseExited

    private void totalCitizensLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalCitizensLabelMouseClicked
     displayTotalCitizens();
    }//GEN-LAST:event_totalCitizensLabelMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
       int choice = JOptionPane.showConfirmDialog(this,
        "Are you sure you want to log out?", 
        "Logout Confirmation",               
        JOptionPane.YES_NO_OPTION,           
        JOptionPane.QUESTION_MESSAGE); 

    if (choice == JOptionPane.YES_OPTION) {
        this.dispose(); 

        
        new loginform().setVisible(true);
    }

    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(bodycolor);
        logout.setOpaque(true);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
         logout.setBackground(navcolor);
         logout.setOpaque(true);
    }//GEN-LAST:event_logoutMouseExited

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
       new userSettings(fullname, userImagePath).setVisible(true);
       this.dispose();
        
        
    }//GEN-LAST:event_settingsMouseClicked

    private void settingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseEntered
                settings.setBackground(bodycolor);
                settings.setOpaque(true);
    }//GEN-LAST:event_settingsMouseEntered

    private void settingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseExited
                settings.setBackground(navcolor);
                settings.setOpaque(true);
    }//GEN-LAST:event_settingsMouseExited

    private void blotterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blotterMouseClicked
        new userBlotter(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_blotterMouseClicked

    private void blotterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blotterMouseEntered
                blotter.setBackground(bodycolor);
                blotter.setOpaque(true);
    }//GEN-LAST:event_blotterMouseEntered

    private void blotterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blotterMouseExited
                blotter.setBackground(navcolor);
                blotter.setOpaque(true);
    }//GEN-LAST:event_blotterMouseExited

    private void adminprofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseClicked
        new userSettings(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminprofMouseClicked

    private void adminprofMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseEntered
        adminprof.setForeground(java.awt.Color.GREEN);
    }//GEN-LAST:event_adminprofMouseEntered

    private void adminprofMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseExited
        adminprof.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_adminprofMouseExited

    private void totalBlottersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalBlottersLabelMouseClicked
        displayBlotterCount();
    }//GEN-LAST:event_totalBlottersLabelMouseClicked

    private void pendingCasesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingCasesLabelMouseClicked
              displayPendingCase();

    }//GEN-LAST:event_pendingCasesLabelMouseClicked

    private void settledCasesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settledCasesLabelMouseClicked
                displaySettledCase();

    }//GEN-LAST:event_settledCasesLabelMouseClicked

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
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String imgPath = "path/to/default/image.png";
                new userDashboard("Regular User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminprof;
    private javax.swing.JLabel blotter;
    private javax.swing.JLabel dash2;
    private javax.swing.JPanel dashboardPanel2;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel managecitizen;
    private javax.swing.JLabel pendingCasesLabel;
    private javax.swing.JPanel pendingcases;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel settings;
    private javax.swing.JLabel settledCasesLabel;
    private javax.swing.JPanel settledcases;
    private javax.swing.JPanel sumBlotter;
    private javax.swing.JPanel sumCitizens;
    private javax.swing.JLabel totalBlottersLabel;
    private javax.swing.JLabel totalCitizensLabel;
    private javax.swing.JLabel userprof;
    // End of variables declaration//GEN-END:variables

   
}
