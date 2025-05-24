/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPackage;

import config.dbConnector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import loginReg.loginform;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author PATRICIA
 */
public class adminBlotter extends javax.swing.JFrame {
       private String fullname;
       private static String userImagePath = null;
       
    /**
     * Creates new form adminBlotter
     */
    public adminBlotter(String fullname, String imgPath) {
        this.fullname = fullname;
    
        initComponents();
        adminprof.setText("" + fullname + "");
        
        displayBlotterData();
        
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
        displayImage();
    }
    
    Color navcolor = new Color(0,51,51);
    Color headcolor = new Color(0,51,51);
    Color bodycolor = new Color(0,153,153);

    
private void displayBlotterData() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT c_id, b_id, b_fname, b_incident, b_location, b_status, b_date, b_witness1, b_witness2 FROM blotter_table");
        c_table.setModel(DbUtils.resultSetToTableModel(rs));

        c_table.getColumnModel().getColumn(2).setHeaderValue("Complainant's Name");
        c_table.getColumnModel().getColumn(3).setHeaderValue("Incident Type");
        c_table.getColumnModel().getColumn(4).setHeaderValue("Location");
        c_table.getColumnModel().getColumn(5).setHeaderValue("Status");

        c_table.getTableHeader().repaint();

        int[] columnsToHide = {8, 7, 6, 1, 0}; 
        for (int i : columnsToHide) {
            c_table.removeColumn(c_table.getColumnModel().getColumn(i));
        }

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}



    private void highlightRow() {
    String searchText = searchblotter.getText().trim().toLowerCase();

    if (searchText.isEmpty()) {
        return;
    }

    c_table.clearSelection();
    boolean matchFound = false;

    for (int i = 0; i < c_table.getRowCount(); i++) { 
        for (int j = 0; j < c_table.getColumnCount(); j++) { 
            Object cellValue = c_table.getValueAt(i, j);

            if (cellValue != null) {
                String cellText = cellValue.toString().trim().toLowerCase();

                if (cellText.contains(searchText)) {
                    c_table.addRowSelectionInterval(i, i); 
                    matchFound = true;
                    break;
                }
            }
        }
    }

    if (matchFound) {
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
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    g2.setClip(new Ellipse2D.Float(0, 0, width, height));
    
    g2.drawImage(img, 0, 0, width, height, null);
    g2.dispose();
    
    return output;
}
private String getSusFullName(int c_id) {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";
    String susFullName = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement("SELECT c_fname, c_lname FROM citizen_table WHERE c_id = ?")) {
        stmt.setInt(1, c_id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String c_fname = rs.getString("c_fname");
            String c_lname = rs.getString("c_lname");
            susFullName = c_fname + " " + c_lname;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    return susFullName;
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
        addbutton2 = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        deletebutton = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        citizen = new javax.swing.JScrollPane();
        c_table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        reports = new javax.swing.JLabel();
        dash = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        managecitizen = new javax.swing.JLabel();
        blotter = new javax.swing.JLabel();
        manageuser = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        adminprof = new javax.swing.JLabel();
        refresh = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        editbutton1 = new javax.swing.JPanel();
        edit1 = new javax.swing.JLabel();
        searchbtn = new javax.swing.JPanel();
        search = new javax.swing.JLabel();
        searchblotter = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(204, 255, 204));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        main.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 800, 40));

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

        main.add(addbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 80, 30));

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

        main.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 80, 30));

        citizen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        citizen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        main.add(citizen, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 780, 480));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 180, 50));

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

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Admin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 180, 30));

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-admin-64.png"))); // NOI18N
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        jPanel1.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 100));

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

        main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 640));

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

        main.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

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

        main.add(editbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 80, 30));

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

        searchblotter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        searchblotter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchblotterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchbtnLayout = new javax.swing.GroupLayout(searchbtn);
        searchbtn.setLayout(searchbtnLayout);
        searchbtnLayout.setHorizontalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchblotter, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchbtnLayout.setVerticalGroup(
            searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchbtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(searchbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchblotter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        main.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, -1, -1));

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

    private void addbutton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseClicked
       new adminBlotterCRUD(fullname, userImagePath).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_addbutton2MouseClicked

    private void addbutton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseEntered
        addbutton2.setBackground(bodycolor);
    }//GEN-LAST:event_addbutton2MouseEntered

    private void addbutton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseExited
        addbutton2.setBackground(navcolor);
    }//GEN-LAST:event_addbutton2MouseExited

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked
        new adminBlotterCRUD(fullname, userImagePath).setVisible(true);
        this.dispose();   
    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseEntered
        deletebutton.setBackground(bodycolor);
    }//GEN-LAST:event_deletebuttonMouseEntered

    private void deletebuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseExited
        deletebutton.setBackground(navcolor);
    }//GEN-LAST:event_deletebuttonMouseExited

    private void c_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_tableMouseClicked
     int selectedRow = c_table.getSelectedRow();
    if (selectedRow != -1) {
        TableModel model = c_table.getModel();

        int c_id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        int b_id = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());
        String b_fname = model.getValueAt(selectedRow, 2).toString();
        String b_incident = model.getValueAt(selectedRow, 3).toString();
        String b_location = model.getValueAt(selectedRow, 4).toString();
        String b_status = model.getValueAt(selectedRow, 5) != null ? model.getValueAt(selectedRow, 5).toString() : "Pending";
        String b_date = model.getValueAt(selectedRow, 6).toString();
        String b_witness1 = model.getValueAt(selectedRow, 7).toString();
        String b_witness2 = model.getValueAt(selectedRow, 8).toString();

        String susFullName = getSusFullName(c_id);
        boolean isEditable = !b_status.equalsIgnoreCase("Settled");

        adminBlotterCRUD editForm = new adminBlotterCRUD(
            susFullName, userImagePath, c_id, b_id, b_fname, b_incident, b_location,
            b_status, b_date, b_witness1, b_witness2, isEditable
        );
        editForm.setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_c_tableMouseClicked

    private void dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseClicked
        admindashboard admin = new admindashboard(fullname, userImagePath);
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashMouseClicked

    private void dashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseEntered
        dash.setBackground(bodycolor);
        dash.setOpaque(true);
    }//GEN-LAST:event_dashMouseEntered

    private void dashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseExited
        dash.setBackground(navcolor);
        dash.setOpaque(true);
    }//GEN-LAST:event_dashMouseExited

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
          String sql = "SELECT user_id FROM user_table WHERE CONCAT(firstName, ' ', lastName) = ?"; 
    
    dbConnector db = new dbConnector();

    try (Connection conn = db.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, this.fullname); 

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int user_id = rs.getInt("user_id");

            int response = JOptionPane.showConfirmDialog(this,
                "Confirm Log Out?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                logActivity(user_id, "Admin Logged out");

                new loginform().setVisible(true);

                this.dispose();
            }
        }        

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void managecitizenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managecitizenMouseClicked
        new adminCitizen(fullname, userImagePath).setVisible(true);
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
        adminPage pag = new adminPage(fullname, userImagePath);
        pag.setVisible(true);
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

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        

    }//GEN-LAST:event_refreshMouseClicked

    private void refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseEntered
        refresh.setBackground(bodycolor);
    }//GEN-LAST:event_refreshMouseEntered

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        refresh.setBackground(navcolor);
    }//GEN-LAST:event_refreshMouseExited

    private void editbutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseClicked
        new adminBlotterCRUD(fullname, userImagePath).setVisible(true);
        this.dispose();
       
    }//GEN-LAST:event_editbutton1MouseClicked

    private void editbutton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseEntered
        editbutton1.setBackground(bodycolor);
    }//GEN-LAST:event_editbutton1MouseEntered

    private void editbutton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseExited
        editbutton1.setBackground(navcolor);
    }//GEN-LAST:event_editbutton1MouseExited

    private void searchblotterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchblotterActionPerformed

    }//GEN-LAST:event_searchblotterActionPerformed

    private void blotterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blotterMouseClicked
   
         displayBlotterData();
        
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
        displayImage();
        new adminSettings(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_profileMouseClicked

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
            java.util.logging.Logger.getLogger(adminBlotter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminBlotter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminBlotter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminBlotter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              String imgPath = "path/to/default/image.png";
              new adminBlotter("Admin User", imgPath).setVisible(true);
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
    private javax.swing.JLabel dash;
    private javax.swing.JPanel deletebutton;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JPanel editbutton1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel main;
    private javax.swing.JLabel managecitizen;
    private javax.swing.JLabel manageuser;
    private javax.swing.JLabel profile;
    private javax.swing.JPanel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JLabel reports;
    private javax.swing.JLabel search;
    private javax.swing.JTextField searchblotter;
    private javax.swing.JPanel searchbtn;
    private javax.swing.JLabel settings;
    // End of variables declaration//GEN-END:variables
}
