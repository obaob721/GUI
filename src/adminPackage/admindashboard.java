package adminPackage;


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
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import loginReg.loginform;


public class admindashboard extends javax.swing.JFrame {
        private String fullname;
        private static String userImagePath = null;
    /**
     * Creates new form adminPage
     */
    public admindashboard(String fullname, String imgPath) {
        this.fullname = fullname;
        
        initComponents();
        adminprof.setText("" + fullname + "");
        
        displayData();
        displayTotalCitizens();
        displayPendingUsers();
        displayBlotterCount();
        displayTotalUsers();
        
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
        displayImage();
        
    }
    
    public admindashboard(){
        initComponents();
        
    }
    Color navcolor = new Color(0,51,51);
    Color headcolor = new Color(0,153,153);
    Color bodycolor = new Color(0,153,153);

    
  public void displayData() {
    try {
        dbConnector dbc = new dbConnector();
        String query = "SELECT CONCAT(firstName, ' ', lastName) AS Full_Name, user_status " +
                       "FROM user_table " +
                       "WHERE user_status = 'Pending';";

        ResultSet rs = dbc.getData(query);
        user_table.setModel(DbUtils.resultSetToTableModel(rs)); 
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

  public void displayTotalCitizens() {
    dbConnector dbc = new dbConnector();
    String query = "SELECT COUNT(*) AS total_citizens FROM citizen_table";

    try (ResultSet rs = dbc.getData(query)) {
        if (rs.next()) {
            int total = rs.getInt("total_citizens"); 
            totalCitizensLabel.setText(String.valueOf(total)); 
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
  
 public void displayPendingUsers() {
     dbConnector dbc = new dbConnector();
       String query = "SELECT COUNT(*) AS pending_users FROM user_table WHERE user_status = 'Pending'";

     try (ResultSet rs = dbc.getData(query)) {

        if (rs.next()) {
            int pendingUsers = rs.getInt("pending_users");
            pendingUsersLabel.setText(String.valueOf(pendingUsers)); 
        }

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
    public void displayTotalUsers() {
        dbConnector dbc = new dbConnector();
        String query = "SELECT COUNT(*) AS total_users FROM user_table";

        try (ResultSet rs = dbc.getData(query)) {
            if (rs.next()) {
                int sum = rs.getInt("total_users");
                totalUsersLabel.setText(String.valueOf(sum));
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

        dashboardPanel = new javax.swing.JPanel();
        nav = new javax.swing.JPanel();
        adminprof = new javax.swing.JLabel();
        reports = new javax.swing.JLabel();
        dash = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        managecitizen = new javax.swing.JLabel();
        blotter = new javax.swing.JLabel();
        manageuser = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        update = new javax.swing.JLabel();
        sumBlotter = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        totalBlottersLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sumUsers = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        totalUsersLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sumCitizens = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        totalCitizensLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        sumReports = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        settledcases = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pendingusers = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        pendingUsersLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        pendingcases = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        user = new javax.swing.JScrollPane();
        user_table = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dashboardPanel.setBackground(new java.awt.Color(204, 255, 204));
        dashboardPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nav.setBackground(new java.awt.Color(0, 51, 51));
        nav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminprof.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        adminprof.setForeground(new java.awt.Color(255, 255, 255));
        adminprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminprof.setText("Administrator");
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
        nav.add(adminprof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 180, -1));

        reports.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reports.setForeground(new java.awt.Color(255, 255, 255));
        reports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reports-50.png"))); // NOI18N
        reports.setText("         Reports");
        reports.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportsMouseExited(evt);
            }
        });
        nav.add(reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 180, 50));

        dash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dash.setForeground(new java.awt.Color(255, 255, 255));
        dash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-dashboard-50.png"))); // NOI18N
        dash.setText("     Dashboard");
        dash.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        dash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashMouseExited(evt);
            }
        });
        nav.add(dash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, 50));

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
        nav.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 180, 50));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome!");
        nav.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-64.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        nav.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 100));

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
        nav.add(managecitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 50));

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
        nav.add(blotter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 50));

        manageuser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        manageuser.setForeground(new java.awt.Color(255, 255, 255));
        manageuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-50.png"))); // NOI18N
        manageuser.setText("Manage User");
        manageuser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        manageuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageuserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageuserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageuserMouseExited(evt);
            }
        });
        nav.add(manageuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 180, 50));

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
        nav.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 180, 50));

        dashboardPanel.add(nav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 640));

        header.setBackground(new java.awt.Color(0, 51, 51));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("BLOTTERMATE COMMUNITY SYSTEM");

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-refresh-32.png"))); // NOI18N
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(update)
                .addGap(18, 18, 18))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        dashboardPanel.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 800, 50));

        sumBlotter.setBackground(new java.awt.Color(0, 51, 51));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-strike-50.png"))); // NOI18N

        totalBlottersLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalBlottersLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalBlottersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalBlottersLabel.setText("0");
        totalBlottersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalBlottersLabelMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total Blotter");

        javax.swing.GroupLayout sumBlotterLayout = new javax.swing.GroupLayout(sumBlotter);
        sumBlotter.setLayout(sumBlotterLayout);
        sumBlotterLayout.setHorizontalGroup(
            sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumBlotterLayout.createSequentialGroup()
                .addGroup(sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sumBlotterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sumBlotterLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(totalBlottersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(54, 54, 54))
        );
        sumBlotterLayout.setVerticalGroup(
            sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sumBlotterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sumBlotterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sumBlotterLayout.createSequentialGroup()
                        .addComponent(totalBlottersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        dashboardPanel.add(sumBlotter, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 230, 90));

        sumUsers.setBackground(new java.awt.Color(0, 51, 51));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-user-50.png"))); // NOI18N

        totalUsersLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalUsersLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalUsersLabel.setText("0");
        totalUsersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalUsersLabelMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Users");

        javax.swing.GroupLayout sumUsersLayout = new javax.swing.GroupLayout(sumUsers);
        sumUsers.setLayout(sumUsersLayout);
        sumUsersLayout.setHorizontalGroup(
            sumUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumUsersLayout.createSequentialGroup()
                .addGroup(sumUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sumUsersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sumUsersLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(totalUsersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(37, 37, 37))
        );
        sumUsersLayout.setVerticalGroup(
            sumUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sumUsersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sumUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sumUsersLayout.createSequentialGroup()
                        .addComponent(totalUsersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        dashboardPanel.add(sumUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 220, 90));

        sumCitizens.setBackground(new java.awt.Color(0, 51, 51));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-people-50.png"))); // NOI18N

        totalCitizensLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalCitizensLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalCitizensLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalCitizensLabel.setText("0");
        totalCitizensLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalCitizensLabelMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Total Citizens");

        javax.swing.GroupLayout sumCitizensLayout = new javax.swing.GroupLayout(sumCitizens);
        sumCitizens.setLayout(sumCitizensLayout);
        sumCitizensLayout.setHorizontalGroup(
            sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumCitizensLayout.createSequentialGroup()
                .addGroup(sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sumCitizensLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sumCitizensLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(totalCitizensLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(37, 37, 37))
        );
        sumCitizensLayout.setVerticalGroup(
            sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sumCitizensLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sumCitizensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sumCitizensLayout.createSequentialGroup()
                        .addComponent(totalCitizensLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        dashboardPanel.add(sumCitizens, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 223, 90));

        sumReports.setBackground(new java.awt.Color(0, 51, 51));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reports-50.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("0");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Reports");

        javax.swing.GroupLayout sumReportsLayout = new javax.swing.GroupLayout(sumReports);
        sumReports.setLayout(sumReportsLayout);
        sumReportsLayout.setHorizontalGroup(
            sumReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sumReportsLayout.createSequentialGroup()
                .addGroup(sumReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sumReportsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sumReportsLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(37, 37, 37))
        );
        sumReportsLayout.setVerticalGroup(
            sumReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sumReportsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sumReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sumReportsLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        dashboardPanel.add(sumReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 230, 90));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("USERS STATUS QUO");
        dashboardPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 300, 30));

        settledcases.setBackground(new java.awt.Color(0, 51, 102));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Settled Cases ");

        javax.swing.GroupLayout settledcasesLayout = new javax.swing.GroupLayout(settledcases);
        settledcases.setLayout(settledcasesLayout);
        settledcasesLayout.setHorizontalGroup(
            settledcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        settledcasesLayout.setVerticalGroup(
            settledcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        dashboardPanel.add(settledcases, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 230, 40));

        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 3));
        dashboardPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 230, 90));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("0");
        dashboardPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 230, 40));

        pendingusers.setBackground(new java.awt.Color(153, 0, 0));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Pending Users");

        javax.swing.GroupLayout pendingusersLayout = new javax.swing.GroupLayout(pendingusers);
        pendingusers.setLayout(pendingusersLayout);
        pendingusersLayout.setHorizontalGroup(
            pendingusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingusersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        pendingusersLayout.setVerticalGroup(
            pendingusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        dashboardPanel.add(pendingusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 230, 40));

        pendingUsersLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        pendingUsersLabel.setForeground(new java.awt.Color(204, 0, 51));
        pendingUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingUsersLabel.setText("0");
        pendingUsersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingUsersLabelMouseClicked(evt);
            }
        });
        dashboardPanel.add(pendingUsersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 540, 230, 40));

        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3));
        dashboardPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 230, 90));

        pendingcases.setBackground(new java.awt.Color(0, 102, 0));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Pending Cases");

        javax.swing.GroupLayout pendingcasesLayout = new javax.swing.GroupLayout(pendingcases);
        pendingcases.setLayout(pendingcasesLayout);
        pendingcasesLayout.setHorizontalGroup(
            pendingcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        pendingcasesLayout.setVerticalGroup(
            pendingcasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        dashboardPanel.add(pendingcases, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 230, 40));

        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 3));
        dashboardPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 230, 90));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("0");
        dashboardPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 230, 40));

        user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        user_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        user.setViewportView(user_table);

        dashboardPanel.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 300, 520));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("BLOTTER RECORD SUMMARY");
        dashboardPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 470, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("ADMIN DASHBOARD");
        dashboardPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 470, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseClicked
       displayData();
       

    }//GEN-LAST:event_dashMouseClicked

    private void managecitizenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseClicked
        adminCitizen  oten = new adminCitizen(fullname, userImagePath);
        oten.setVisible(true);
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

    private void manageuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseClicked
       adminPage min = new adminPage(fullname, userImagePath);
       min.setVisible(true);
       this.dispose();
        
    }//GEN-LAST:event_manageuserMouseClicked

    private void manageuserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseEntered
        manageuser.setBackground(bodycolor);
        manageuser.setOpaque(true);
    }//GEN-LAST:event_manageuserMouseEntered

    private void manageuserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseExited
        manageuser.setBackground(navcolor);
        manageuser.setOpaque(true);
    }//GEN-LAST:event_manageuserMouseExited

    private void dashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseEntered
        dash.setBackground(bodycolor);
        dash.setOpaque(true);

    }//GEN-LAST:event_dashMouseEntered

    private void dashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseExited
        dash.setBackground(navcolor);
        dash.setOpaque(true);
    }//GEN-LAST:event_dashMouseExited

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
      
        int choice = JOptionPane.showConfirmDialog(this,
        "Are you sure you want to log out?", 
        "Logout Confirmation",               
        JOptionPane.YES_NO_OPTION,           
        JOptionPane.QUESTION_MESSAGE); 

    if (choice == JOptionPane.YES_OPTION) {
        this.dispose(); 

        
        loginform login = new loginform(); 
        login.setVisible(true);
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

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
      
    }//GEN-LAST:event_updateMouseClicked

    private void totalCitizensLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalCitizensLabelMouseClicked
         displayTotalCitizens();
    }//GEN-LAST:event_totalCitizensLabelMouseClicked

    private void totalUsersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalUsersLabelMouseClicked
          displayTotalUsers();      
    }//GEN-LAST:event_totalUsersLabelMouseClicked

    private void pendingUsersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingUsersLabelMouseClicked
        displayPendingUsers();
    }//GEN-LAST:event_pendingUsersLabelMouseClicked

    private void adminprofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseClicked
       new adminSettings(fullname, userImagePath).setVisible(true);
       this.dispose();       
    }//GEN-LAST:event_adminprofMouseClicked

    private void blotterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blotterMouseClicked
        new adminBlotter(fullname, userImagePath).setVisible(true);
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

    private void reportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsMouseClicked
        new adminReport(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reportsMouseClicked

    private void reportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsMouseEntered
         reports.setBackground(bodycolor);
         reports.setOpaque(true);
    }//GEN-LAST:event_reportsMouseEntered

    private void reportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsMouseExited
        reports.setBackground(navcolor);
         reports.setOpaque(true);
    }//GEN-LAST:event_reportsMouseExited

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
        new adminLogs(fullname, userImagePath).setVisible(true);
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

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
    displayImage();
    new adminSettings(fullname, userImagePath).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_profileMouseClicked

    private void totalBlottersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalBlottersLabelMouseClicked
        displayBlotterCount();
        
    }//GEN-LAST:event_totalBlottersLabelMouseClicked

    private void adminprofMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseEntered
       adminprof.setForeground(java.awt.Color.GREEN);
    }//GEN-LAST:event_adminprofMouseEntered

    private void adminprofMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseExited
       adminprof.setForeground(java.awt.Color.WHITE);

    }//GEN-LAST:event_adminprofMouseExited

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
            java.util.logging.Logger.getLogger(admindashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admindashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admindashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admindashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 String imgPath = "path/to/default/image.png";
                new admindashboard("Admin User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminprof;
    private javax.swing.JLabel blotter;
    private javax.swing.JLabel dash;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel managecitizen;
    private javax.swing.JLabel manageuser;
    private javax.swing.JPanel nav;
    private javax.swing.JLabel pendingUsersLabel;
    private javax.swing.JPanel pendingcases;
    private javax.swing.JPanel pendingusers;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel reports;
    private javax.swing.JLabel settings;
    private javax.swing.JPanel settledcases;
    private javax.swing.JPanel sumBlotter;
    private javax.swing.JPanel sumCitizens;
    private javax.swing.JPanel sumReports;
    private javax.swing.JPanel sumUsers;
    private javax.swing.JLabel totalBlottersLabel;
    private javax.swing.JLabel totalCitizensLabel;
    private javax.swing.JLabel totalUsersLabel;
    private javax.swing.JLabel update;
    private javax.swing.JScrollPane user;
    private javax.swing.JTable user_table;
    // End of variables declaration//GEN-END:variables

    
}
