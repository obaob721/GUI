package adminPackage;

import config.Session;
import loginReg.loginform;
import config.dbConnector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PATRICIA
 */
public class adminPage extends javax.swing.JFrame {
       private String fullname;
       private static String userImagePath = null;
   

    /**
     * Creates new form adminPage
     */
    public adminPage(String fullname, String imgPath) {
        this.fullname = fullname;

        initComponents();
        adminprof.setText("" + fullname + "");

        displayData();
        highlightRow();
        
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
        displayImage();
    }

    Color navcolor = new Color(0, 51, 51);
    Color headcolor = new Color(0, 153, 153);
    Color bodycolor = new Color(0, 153, 153);

  public void displayData() {
    try {
        dbConnector dbc = new dbConnector();
        String query = "SELECT CONCAT(firstName, ' ', lastName) AS Full_Name, email, use_type, user_status, u_image "
                     + "FROM user_table WHERE user_id != ?";
        
        PreparedStatement ps = dbc.getConnection().prepareStatement(query);
        ps.setInt(1, Session.getInstance().getUid());  // Use uid from Session
        
        ResultSet rs = ps.executeQuery();
        c_table.setModel(DbUtils.resultSetToTableModel(rs));

        // Hide the image column in the table (index 4 for image_path)
        c_table.getColumnModel().getColumn(4).setMinWidth(0);
        c_table.getColumnModel().getColumn(4).setMaxWidth(0);
        c_table.getColumnModel().getColumn(4).setWidth(0);

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
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
   private void highlightRow() {
    String searchText = searchuser.getText().trim().toLowerCase();

    if (searchText.isEmpty()) {
        return;
    }

    c_table.clearSelection(); // Clear previous selection
    boolean matchFound = false;

    for (int i = 0; i < c_table.getRowCount(); i++) { // Corrected loop condition
        for (int j = 0; j < c_table.getColumnCount(); j++) { // Use 0-based index
            Object cellValue = c_table.getValueAt(i, j);

            if (cellValue != null) {
                String cellText = cellValue.toString().trim().toLowerCase();

                if (cellText.contains(searchText)) {
                    c_table.addRowSelectionInterval(i, i); // Select row
                    matchFound = true;
                    break; // Exit column loop once a match is found
                }
            }
        }
    }

    if (matchFound) {
        // Scroll to the first selected row
        int firstSelectedRow = c_table.getSelectedRow();
        if (firstSelectedRow != -1) {
            c_table.scrollRectToVisible(c_table.getCellRect(firstSelectedRow, 0, true));
        }
    } else {
        JOptionPane.showMessageDialog(null, "No matching record found!", "Search", JOptionPane.INFORMATION_MESSAGE);
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
                ImageIcon circularImg = new ImageIcon(getRoundedImage(img, profile1.getWidth(), profile1.getHeight()));
                profile1.setIcon(circularImg);
                profile1.setText("");
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        } else {
            profile1.setText("Image Not Found");
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

   private void logActivity(int user_id, String action) {
        String sql = "INSERT INTO system_logs (user_id, logs_action, logs_date) VALUES (?, ?, NOW())";
        dbConnector db = new dbConnector();

        try (Connection conn = db.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, user_id);
            pst.setString(2, action);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging activity: " + e.getMessage());
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
        citizen = new javax.swing.JScrollPane();
        c_table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dash = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        managecitizen = new javax.swing.JLabel();
        blotter = new javax.swing.JLabel();
        manageuser = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        confirm1 = new javax.swing.JLabel();
        adminprof = new javax.swing.JLabel();
        profile1 = new javax.swing.JLabel();
        addbutton2 = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        deletebutton = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        refresh = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        editbutton1 = new javax.swing.JPanel();
        edit1 = new javax.swing.JLabel();
        fn1 = new javax.swing.JLabel();
        enterfn = new javax.swing.JTextField();
        ln = new javax.swing.JLabel();
        enterln = new javax.swing.JTextField();
        emails = new javax.swing.JLabel();
        enteremail = new javax.swing.JTextField();
        pass = new javax.swing.JLabel();
        enterpass = new javax.swing.JPasswordField();
        confirm = new javax.swing.JLabel();
        user = new javax.swing.JComboBox<>();
        userStatusComboBox = new javax.swing.JComboBox<>();
        confirm3 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        confirm2 = new javax.swing.JLabel();
        searchuser = new javax.swing.JTextField();
        searchbtn = new javax.swing.JPanel();
        search = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(204, 255, 204));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        main.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 800, 40));

        citizen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        c_table.setModel(new javax.swing.table.DefaultTableModel(
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
        c_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_tableMouseClicked(evt);
            }
        });
        citizen.setViewportView(c_table);

        main.add(citizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 780, 330));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-reports-50.png"))); // NOI18N
        jLabel3.setText("         Reports");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 180, 50));

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
        jPanel1.add(dash, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, 50));

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
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 180, 50));

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
        jPanel1.add(managecitizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 50));

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
        jPanel1.add(blotter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 50));

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
        jPanel1.add(manageuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 180, 50));

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
        jPanel1.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 180, 50));

        confirm1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        confirm1.setForeground(new java.awt.Color(255, 255, 255));
        confirm1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirm1.setText("Admin");
        jPanel1.add(confirm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 180, 30));

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
        jPanel1.add(adminprof, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 180, -1));

        profile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-64.png"))); // NOI18N
        profile1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profile1MouseClicked(evt);
            }
        });
        jPanel1.add(profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 100));

        main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 640));

        addbutton2.setBackground(new java.awt.Color(0, 51, 51));
        addbutton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        addbutton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addbutton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addbutton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addbutton2MouseExited(evt);
            }
        });

        edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setText("ADD");

        javax.swing.GroupLayout addbutton2Layout = new javax.swing.GroupLayout(addbutton2);
        addbutton2.setLayout(addbutton2Layout);
        addbutton2Layout.setHorizontalGroup(
            addbutton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addbutton2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addbutton2Layout.setVerticalGroup(
            addbutton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addbutton2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(addbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 80, 30));

        deletebutton.setBackground(new java.awt.Color(0, 51, 51));
        deletebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        deletebutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletebuttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deletebuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deletebuttonMouseExited(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add.setText("DELETE");

        javax.swing.GroupLayout deletebuttonLayout = new javax.swing.GroupLayout(deletebutton);
        deletebutton.setLayout(deletebuttonLayout);
        deletebuttonLayout.setHorizontalGroup(
            deletebuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deletebuttonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        deletebuttonLayout.setVerticalGroup(
            deletebuttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deletebuttonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 80, 30));

        refresh.setBackground(new java.awt.Color(0, 51, 51));
        refresh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshMouseExited(evt);
            }
        });

        refresh1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refresh1.setForeground(new java.awt.Color(255, 255, 255));
        refresh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refresh1.setText("REFRESH");

        javax.swing.GroupLayout refreshLayout = new javax.swing.GroupLayout(refresh);
        refresh.setLayout(refreshLayout);
        refreshLayout.setHorizontalGroup(
            refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refreshLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        refreshLayout.setVerticalGroup(
            refreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refreshLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        editbutton1.setBackground(new java.awt.Color(0, 51, 51));
        editbutton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        editbutton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editbutton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editbutton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editbutton1MouseExited(evt);
            }
        });

        edit1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit1.setForeground(new java.awt.Color(255, 255, 255));
        edit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit1.setText("EDIT");

        javax.swing.GroupLayout editbutton1Layout = new javax.swing.GroupLayout(editbutton1);
        editbutton1.setLayout(editbutton1Layout);
        editbutton1Layout.setHorizontalGroup(
            editbutton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editbutton1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        editbutton1Layout.setVerticalGroup(
            editbutton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editbutton1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(editbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 80, 30));

        fn1.setBackground(new java.awt.Color(255, 255, 255));
        fn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fn1.setText("First Name:");
        main.add(fn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 100, 30));

        enterfn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterfn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterfn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterfnActionPerformed(evt);
            }
        });
        main.add(enterfn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 170, 30));

        ln.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln.setText("Last Name:");
        main.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 90, 30));

        enterln.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterlnActionPerformed(evt);
            }
        });
        main.add(enterln, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 180, 30));

        emails.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emails.setText("Email:");
        main.add(emails, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 70, 30));

        enteremail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enteremail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enteremail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enteremailActionPerformed(evt);
            }
        });
        main.add(enteremail, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 170, 30));

        pass.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pass.setText("Password:");
        main.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 80, 30));

        enterpass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        enterpass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        enterpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterpassKeyPressed(evt);
            }
        });
        main.add(enterpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 180, 30));

        confirm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirm.setText("Profile Picture");
        main.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 190, 30));

        user.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        user.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User\t", "Admin" }));
        user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        main.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 170, 30));

        userStatusComboBox.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Approved" }));
        userStatusComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        userStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userStatusComboBoxActionPerformed(evt);
            }
        });
        main.add(userStatusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 180, 30));

        confirm3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirm3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirm3.setText("User Type:");
        main.add(confirm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 90, 30));

        profile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-male-user-100.png"))); // NOI18N
        profile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        main.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 190, 140));

        confirm2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirm2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirm2.setText("User Status:");
        main.add(confirm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 80, 30));

        searchuser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        searchuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchuserMouseClicked(evt);
            }
        });
        searchuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchuserActionPerformed(evt);
            }
        });
        main.add(searchuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 170, 34));

        searchbtn.setBackground(new java.awt.Color(0, 51, 51));
        searchbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        searchbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchbtnMouseExited(evt);
            }
        });

        search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        search.setText("SEARCH");

        javax.swing.GroupLayout searchbtnLayout = new javax.swing.GroupLayout(searchbtn);
        searchbtn.setLayout(searchbtnLayout);
        searchbtnLayout.setHorizontalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchbtnLayout.setVerticalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, -1, -1));

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

    private void searchuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchuserActionPerformed

    }//GEN-LAST:event_searchuserActionPerformed

    private void addbutton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseClicked
          String firstName = enterfn.getText().trim();
    String lastName = enterln.getText().trim();
    String email = enteremail.getText().trim();
    String password = new String(enterpass.getPassword());
    String use_type = user.getSelectedItem().toString();
    String user_status = userStatusComboBox.getSelectedItem().toString();

    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address (example@gmail.com)", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (password.length() < 8) {
        JOptionPane.showMessageDialog(this, "Password should have at least 8 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try {
        Connection conn = DriverManager.getConnection(url, user, pass);
        
        String hashedPassword = passwordHash(password);
        
        String sql = "INSERT INTO user_table (firstName, lastName, email, password, use_type, user_status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, email);
        pstmt.setString(4, hashedPassword);
        pstmt.setString(5, use_type);
        pstmt.setString(6, user_status);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
           
            // 🔒 Log action by session user (not the new user)
            Session session = Session.getInstance();
            int sessionUserId = session.getUid();
            logActivity(sessionUserId, "Registered new User: " + firstName + " " + lastName);
        }
         this.dispose();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addbutton2MouseClicked

    private void addbutton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseEntered
        addbutton2.setBackground(bodycolor);
    }//GEN-LAST:event_addbutton2MouseEntered

    private void addbutton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseExited
        addbutton2.setBackground(navcolor);
    }//GEN-LAST:event_addbutton2MouseExited

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked
 String user_email = enteremail.getText();

    if (user_email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an email.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try {
        Connection conn = DriverManager.getConnection(url, user, pass);

        // Get target user (to be deleted)
        String getIdQuery = "SELECT user_id, firstName, lastName FROM user_table WHERE email = ?";
        PreparedStatement idStmt = conn.prepareStatement(getIdQuery);
        idStmt.setString(1, user_email);
        ResultSet rs = idStmt.executeQuery();

        int targetUserId = -1;
        String fullName = "";
        if (rs.next()) {
            targetUserId = rs.getInt("user_id");
            fullName = rs.getString("firstName") + " " + rs.getString("lastName");
        }
        rs.close();
        idStmt.close();

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            conn.close();
            return;
        }

        // Delete target user
        String sql = "DELETE FROM user_table WHERE email = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user_email);

        int rowsDeleted = pstmt.executeUpdate();
        pstmt.close();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(this, "User deleted successfully!");

            // 🟢 Use Session to log the ACTOR's ID, not the deleted user's
            Session session = Session.getInstance();
            int sessionUserId = session.getUid();
            logActivity(sessionUserId, "Deleted User Account: " + fullName);
        } else {
            JOptionPane.showMessageDialog(this, "Deletion failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseEntered
        deletebutton.setBackground(bodycolor);
    }//GEN-LAST:event_deletebuttonMouseEntered

    private void deletebuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseExited
        deletebutton.setBackground(navcolor);
    }//GEN-LAST:event_deletebuttonMouseExited

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        displayData();
        enterfn.setText("");
        enterln.setText("");
        enteremail.setText("");
        enterpass.setText("");
        user.setSelectedIndex(-1);
        userStatusComboBox.setSelectedIndex(-1);

    }//GEN-LAST:event_refreshMouseClicked

    private void refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseEntered
        refresh.setBackground(bodycolor);
    }//GEN-LAST:event_refreshMouseEntered

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        refresh.setBackground(navcolor);
    }//GEN-LAST:event_refreshMouseExited

    private void editbutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseClicked
       String firstName = enterfn.getText().trim();
    String lastName = enterln.getText().trim();
    String email = enteremail.getText().trim();
    String password = new String(enterpass.getPassword());
    String use_type = user.getSelectedItem().toString();
    String user_status = userStatusComboBox.getSelectedItem().toString();

    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address (example@gmail.com)", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (password.length() < 8) {
        JOptionPane.showMessageDialog(this, "Password should have at least 8 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try {
        Connection conn = DriverManager.getConnection(url, user, pass);
        
        // Update user status if it's "Pending"
        if (user_status.equals("Approved")) {
            user_status = "Active";
        } else if (user_status.equals("Pending")) {
            user_status = "Deactivated";
        }
        
        String hashedPassword = passwordHash(password);
        String sql = "UPDATE user_table SET firstName = ?, lastName = ?, password = ?, use_type = ?, user_status = ? WHERE email = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, hashedPassword);
        pstmt.setString(4, use_type);
        pstmt.setString(5, user_status);
        pstmt.setString(6, email);

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "User information updated successfully!");
            displayData();
            
            // Use the session to get the logged-in user's user_id
            Session session = Session.getInstance();
            int user_id = session.getUid(); // Get the user_id from the session
            
            // Log the update activity (this logs who updated their profile)
            logActivity(user_id, "Updated User Profile: " + firstName + " " + lastName);
        } else {
            JOptionPane.showMessageDialog(this, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_editbutton1MouseClicked

    private void editbutton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseEntered
        editbutton1.setBackground(bodycolor);
    }//GEN-LAST:event_editbutton1MouseEntered

    private void editbutton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseExited
        editbutton1.setBackground(navcolor);
    }//GEN-LAST:event_editbutton1MouseExited

    private void enterfnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterfnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterfnActionPerformed

    private void enterlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterlnActionPerformed

    private void enteremailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enteremailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enteremailActionPerformed

    private void userStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userStatusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userStatusComboBoxActionPerformed

    private void c_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_tableMouseClicked
     int i = c_table.getSelectedRow(); // Get selected row index

    // Check if the selected row is valid
    if (i == -1) {
        System.out.println("No row selected");
        return; // Exit the method if no row is selected
    }

    TableModel model = c_table.getModel();

    // Get and split full name
    String fullName = model.getValueAt(i, 0).toString();
    String[] nameParts = fullName.split(" ", 2);
    String firstName = (nameParts.length > 0) ? nameParts[0] : "";
    String lastName = (nameParts.length > 1) ? nameParts[1] : "";

    enterfn.setText(firstName);
    enterln.setText(lastName);
    enteremail.setText(model.getValueAt(i, 1).toString());
    enterpass.setText("********");

    String type = model.getValueAt(i, 2).toString();
    user.setSelectedItem(type);

    String status = model.getValueAt(i, 3).toString();
    userStatusComboBox.setSelectedItem(status);

    // Handle profile image (column index 4)
    try {
        Object imgValue = model.getValueAt(i, 4);
        if (imgValue != null && !imgValue.toString().trim().isEmpty()) {
            // Image path exists and is not empty, so display it
            String u_image = imgValue.toString().trim();
            File file = new File(u_image).getAbsoluteFile();
            if (file.exists()) {
                // Image file exists, load it
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
                profile.setIcon(new ImageIcon(img));
            }
        } else {
            // If there is no image path or it's empty, do nothing (leave profile empty)
            profile.setIcon(null);
        }
    } catch (Exception e) {
        // Catch any errors during image loading and leave it empty
        System.out.println("Error loading image: " + e.getMessage());
        profile.setIcon(null); // No image shown
    }
    }//GEN-LAST:event_c_tableMouseClicked

    private void enterpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enterpassKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_enterpassKeyPressed

    private void manageuserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseExited
        manageuser.setBackground(navcolor);
        manageuser.setOpaque(true);
    }//GEN-LAST:event_manageuserMouseExited

    private void manageuserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseEntered
        manageuser.setBackground(bodycolor);
        manageuser.setOpaque(true);
    }//GEN-LAST:event_manageuserMouseEntered

    private void manageuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserMouseClicked
        displayData();
    }//GEN-LAST:event_manageuserMouseClicked

    private void managecitizenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseExited
        managecitizen.setBackground(navcolor);
        managecitizen.setOpaque(true);
    }//GEN-LAST:event_managecitizenMouseExited

    private void managecitizenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseEntered
        managecitizen.setBackground(bodycolor);
        managecitizen.setOpaque(true);
    }//GEN-LAST:event_managecitizenMouseEntered

    private void managecitizenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseClicked
        adminCitizen zen = new adminCitizen(fullname, userImagePath);
        zen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_managecitizenMouseClicked

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        logout.setBackground(navcolor);
        logout.setOpaque(true);
    }//GEN-LAST:event_logoutMouseExited

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setBackground(bodycolor);
        logout.setOpaque(true);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
         String sql = "SELECT user_id FROM user_table WHERE CONCAT(firstName, ' ', lastName) = ?"; 
    
    dbConnector db = new dbConnector();

    try (Connection conn = db.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, this.fullname); // Set the logged-in user's full name (firstName + lastName)

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int user_id = rs.getInt("user_id");

            // Confirm logout with the user
            int response = JOptionPane.showConfirmDialog(this,
                "Confirm Log Out?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                // Log the logout activity
                logActivity(user_id, "Logged out");

                // Redirect to login form
                new loginform().setVisible(true);

                // Dispose current window (user is logged out)
                this.dispose();
            }
        }        

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_logoutMouseClicked

    private void dashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseExited
        dash.setBackground(navcolor);
        dash.setOpaque(true);
    }//GEN-LAST:event_dashMouseExited

    private void dashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseEntered
        dash.setBackground(bodycolor);
        dash.setOpaque(true);
    }//GEN-LAST:event_dashMouseEntered

    private void dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseClicked
        admindashboard admin = new admindashboard(fullname, userImagePath);
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashMouseClicked

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

    private void searchuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchuserMouseClicked
      
    }//GEN-LAST:event_searchuserMouseClicked

    private void searchbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseClicked
      highlightRow();
    }//GEN-LAST:event_searchbtnMouseClicked

    private void searchbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseEntered
        searchbtn.setBackground(bodycolor);
    }//GEN-LAST:event_searchbtnMouseEntered

    private void searchbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbtnMouseExited
         searchbtn.setBackground(navcolor);
    }//GEN-LAST:event_searchbtnMouseExited

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked

    }//GEN-LAST:event_profileMouseClicked

    private void profile1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile1MouseClicked
        displayImage();
        new adminSettings(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_profile1MouseClicked

    private void adminprofMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminprofMouseClicked
        new adminSettings(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_adminprofMouseClicked

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
            java.util.logging.Logger.getLogger(adminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String imgPath = "path/to/default/image.png";
                new adminPage("Admin User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel addbutton2;
    private javax.swing.JLabel adminprof;
    private javax.swing.JLabel blotter;
    private javax.swing.JTable c_table;
    private javax.swing.JScrollPane citizen;
    private javax.swing.JLabel confirm;
    private javax.swing.JLabel confirm1;
    private javax.swing.JLabel confirm2;
    private javax.swing.JLabel confirm3;
    private javax.swing.JLabel dash;
    private javax.swing.JPanel deletebutton;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JPanel editbutton1;
    private javax.swing.JLabel emails;
    private javax.swing.JTextField enteremail;
    private javax.swing.JTextField enterfn;
    private javax.swing.JTextField enterln;
    private javax.swing.JPasswordField enterpass;
    private javax.swing.JLabel fn1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel ln;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel main;
    private javax.swing.JLabel managecitizen;
    private javax.swing.JLabel manageuser;
    private javax.swing.JLabel pass;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JPanel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JLabel search;
    private javax.swing.JPanel searchbtn;
    private javax.swing.JTextField searchuser;
    private javax.swing.JLabel settings;
    private javax.swing.JComboBox<String> user;
    private javax.swing.JComboBox<String> userStatusComboBox;
    // End of variables declaration//GEN-END:variables
}
