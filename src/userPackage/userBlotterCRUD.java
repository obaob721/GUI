/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userPackage;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author PATRICIA
 */
public class userBlotterCRUD extends javax.swing.JFrame {
      private String fullname;
      private static String userImagePath = null;
      private javax.swing.JComboBox<String> citizendisplays;
      private javax.swing.JCheckBox othersComboBox;
    /**
     * Creates new form userBlotterCRUD
     */
    public userBlotterCRUD(String fullname, String imgPath) {
        this.fullname = fullname;
        initComponents();
        setSize(970, 540);
        setResizable(false);
        
        
        setLocationRelativeTo(null);
        
        userImagePath = imgPath;
        
        othersComboBox = new javax.swing.JCheckBox();
        othersCombobox.setVisible(false); // hidden at first
        add(othersComboBox);
        populateCitizenComboBox();
        populateOthersComboBox();
    }
    private Integer getCidByFullName(String fullName) {
    Integer c_id = null;
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    String[] names = fullName.split(" ", 2); // split into first and last name

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement(
             "SELECT c_id FROM citizen_table WHERE c_fname = ? AND c_lname = ?")) {

        if (names.length == 2) {
            stmt.setString(1, names[0]);
            stmt.setString(2, names[1]);
        } else {
            // Handle if no last name or only one name
            stmt.setString(1, fullName);
            stmt.setString(2, "");
        }

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            c_id = rs.getInt("c_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return c_id;
}

    private void populateOthersComboBox() {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement("SELECT c_fname, c_lname FROM citizen_table");
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String fullName = rs.getString("c_fname") + " " + rs.getString("c_lname");
            othersCombobox.addItem(fullName);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load citizens.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
  private void populateCitizenComboBox() {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement("SELECT c_id, c_fname, c_lname FROM citizen_table");
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String fullName = rs.getString("c_fname") + " " + rs.getString("c_lname");
            citizendisplay.addItem(fullName);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load citizens.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


  public int getCitizenIdByName(String fullName) {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    int c_id = -1;

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement("SELECT c_id FROM citizen_table WHERE CONCAT(c_fname, ' ', c_lname) = ?")) {
        stmt.setString(1, fullName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            c_id = rs.getInt("c_id");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return c_id;
}

private String getFullNameById(int c_id) {
    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement stmt = conn.prepareStatement("SELECT CONCAT(c_fname, ' ', c_lname) AS full_name FROM citizen_table WHERE c_id = ?")) {

        stmt.setInt(1, c_id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("full_name");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return null;
}

    
    
 public userBlotterCRUD(String fullname, String imgPath, int c_id, int b_id, String b_fname, String b_incident,
                       String b_location, String b_status, String b_date, String b_witness1, String b_witness2,
                       boolean isEditable) { // ✅ Added
    this.fullname = fullname; 
    this.userImagePath = imgPath;

    initComponents();
    populateCitizenComboBox();

    String selectedFullName = getFullNameById(c_id);
    if (selectedFullName != null) {
        for (int i = 0; i < citizendisplay.getItemCount(); i++) {
            if (citizendisplay.getItemAt(i).equals(selectedFullName)) {
                citizendisplay.setSelectedIndex(i);
                break;
            }
        }
    }

    txtb_id.setText(String.valueOf(b_id));
    txtComplainant1.setText(b_fname);
    txtincident.setText(b_incident);
    txtlocation.setText(b_location);
    cmbstatus.setSelectedItem(b_status);
    txtwitness1.setText(b_witness1);
    txtwitness2.setText(b_witness2);

    if (!isEditable) {
        txtComplainant1.setEnabled(false);
        txtincident.setEnabled(false);
        txtlocation.setEnabled(false);
        cmbstatus.setEnabled(false);
        txtwitness1.setEnabled(false);
        txtwitness2.setEnabled(false);
        editbutton1.setEnabled(false);
        citizendisplay.setEnabled(false);
    }
}

    private void switchToAdminBlotter() {
        userBlotter adminFrame = new userBlotter(fullname, userImagePath);  // 🔥 Pass fullname
        adminFrame.setVisible(true);
        this.dispose();
    }
    Color navcolor = new Color(204,255,204);
    Color headcolor = new Color(0,51,51);
    Color bodycolor = new Color(0,153,153);
    
    
  

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
        addbutton2 = new javax.swing.JPanel();
        edit = new javax.swing.JLabel();
        deletebutton = new javax.swing.JPanel();
        add = new javax.swing.JLabel();
        refresh = new javax.swing.JPanel();
        refresh1 = new javax.swing.JLabel();
        editbutton1 = new javax.swing.JPanel();
        edit1 = new javax.swing.JLabel();
        fn1 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        txtComplainant1 = new javax.swing.JTextField();
        txtincident = new javax.swing.JTextField();
        email2 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        email1 = new javax.swing.JLabel();
        txtlocation = new javax.swing.JTextField();
        email3 = new javax.swing.JLabel();
        cmbstatus = new javax.swing.JComboBox<>();
        ln1 = new javax.swing.JLabel();
        txtwitness1 = new javax.swing.JTextField();
        ln2 = new javax.swing.JLabel();
        txtwitness2 = new javax.swing.JTextField();
        back = new javax.swing.JPanel();
        edit2 = new javax.swing.JLabel();
        ln4 = new javax.swing.JLabel();
        txtb_id = new javax.swing.JTextField();
        othersCombobox = new javax.swing.JComboBox<>();
        box = new javax.swing.JCheckBox();
        citizendisplay = new javax.swing.JComboBox<>();
        ln = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(0, 51, 51));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(0, 153, 153));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BLOTTER DETAILS");
        header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 980, 30));

        main.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 40));

        addbutton2.setBackground(new java.awt.Color(204, 255, 204));
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

        main.add(addbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 80, 30));

        deletebutton.setBackground(new java.awt.Color(204, 255, 204));
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

        main.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 80, 30));

        refresh.setBackground(new java.awt.Color(204, 255, 204));
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

        main.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        editbutton1.setBackground(new java.awt.Color(204, 255, 204));
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

        main.add(editbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 80, 30));

        fn1.setBackground(new java.awt.Color(255, 255, 255));
        fn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fn1.setForeground(new java.awt.Color(255, 255, 255));
        fn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fn1.setText("Complainant:");
        main.add(fn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 130, 40));

        email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email.setForeground(new java.awt.Color(255, 255, 255));
        email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email.setText("Incident Type:");
        main.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 130, 30));

        txtComplainant1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtComplainant1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtComplainant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplainant1ActionPerformed(evt);
            }
        });
        main.add(txtComplainant1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 260, 40));

        txtincident.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtincident.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtincident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtincidentActionPerformed(evt);
            }
        });
        main.add(txtincident, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 260, 40));

        email2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email2.setForeground(new java.awt.Color(255, 255, 255));
        email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email2.setText("Date Reported:");
        main.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 120, 30));

        txtdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdateActionPerformed(evt);
            }
        });
        main.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 260, 40));

        email1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email1.setForeground(new java.awt.Color(255, 255, 255));
        email1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email1.setText("Status:");
        main.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 120, 40));

        txtlocation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtlocation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtlocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlocationActionPerformed(evt);
            }
        });
        main.add(txtlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 260, 40));

        email3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email3.setForeground(new java.awt.Color(255, 255, 255));
        email3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        email3.setText("Location:");
        main.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 110, 30));

        cmbstatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Ongoing", "Settled" }));
        main.add(cmbstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 260, 40));

        ln1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln1.setForeground(new java.awt.Color(255, 255, 255));
        ln1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln1.setText("Witness 1:");
        main.add(ln1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 120, 30));

        txtwitness1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtwitness1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtwitness1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtwitness1ActionPerformed(evt);
            }
        });
        main.add(txtwitness1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 260, 40));

        ln2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln2.setForeground(new java.awt.Color(255, 255, 255));
        ln2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln2.setText("Witness 2:");
        main.add(ln2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 120, 40));

        txtwitness2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtwitness2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtwitness2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtwitness2ActionPerformed(evt);
            }
        });
        main.add(txtwitness2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 360, 260, 40));

        back.setBackground(new java.awt.Color(204, 255, 204));
        back.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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

        edit2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit2.setText("BACK");

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 80, 30));

        ln4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln4.setForeground(new java.awt.Color(255, 255, 255));
        ln4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln4.setText("Blotter ID:");
        main.add(ln4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 80, 30));

        txtb_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtb_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtb_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtb_idActionPerformed(evt);
            }
        });
        main.add(txtb_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 70, 30));

        main.add(othersCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 260, 40));

        box.setBackground(new java.awt.Color(204, 255, 204));
        box.setForeground(new java.awt.Color(255, 255, 255));
        box.setText("Others_________");
        box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxActionPerformed(evt);
            }
        });
        main.add(box, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 160, 20));

        main.add(citizendisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 260, 40));

        ln.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ln.setForeground(new java.awt.Color(255, 255, 255));
        ln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ln.setText("Suspect Name:");
        main.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 120, 30));

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
    }// </editor-fold>//GEN-END:initComponents

    private void addbutton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseClicked
      String selectedSuspect = (String) citizendisplay.getSelectedItem();
    if (selectedSuspect == null || selectedSuspect.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select a suspect from the list.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Integer suspectCid = getCidByFullName(selectedSuspect);
    if (suspectCid == null) {
        JOptionPane.showMessageDialog(null, "Selected suspect not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String complainant;
    if (box.isSelected()) {
        String selectedComplainant = (String) othersCombobox.getSelectedItem();
        if (selectedComplainant == null || selectedComplainant.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a complainant from the list.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        complainant = selectedComplainant;
    } else {
        complainant = txtComplainant1.getText().trim();
        if (complainant.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the complainant's name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    String b_incident = txtincident.getText().trim();
    String b_location = txtlocation.getText().trim();
    String b_status = cmbstatus.getSelectedItem().toString();
    String b_witness1 = txtwitness1.getText().trim();
    String b_witness2 = txtwitness2.getText().trim();
    Timestamp b_date = new Timestamp(System.currentTimeMillis());

    if (b_incident.isEmpty() || b_location.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in the incident and location fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String insertSQL = "INSERT INTO blotter_table (c_id, b_fname, b_incident, b_location, b_status, b_date, b_witness1, b_witness2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/obaob_db", "root", "");
         PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, suspectCid);
        pstmt.setString(2, complainant);
        pstmt.setString(3, b_incident);
        pstmt.setString(4, b_location);
        pstmt.setString(5, b_status);
        pstmt.setTimestamp(6, b_date);
        pstmt.setString(7, b_witness1);
        pstmt.setString(8, b_witness2);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int b_id = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "Blotter record added successfully!\nGenerated Blotter ID: " + b_id);
                txtb_id.setText(String.valueOf(b_id)); // Optionally auto-fill the txtb_id field
            } else {
                JOptionPane.showMessageDialog(null, "Blotter added, but failed to retrieve its ID.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add blotter record.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error while adding blotter record.");
    }
        
    }//GEN-LAST:event_addbutton2MouseClicked

    private void addbutton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseEntered
        addbutton2.setBackground(bodycolor);
    }//GEN-LAST:event_addbutton2MouseEntered

    private void addbutton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbutton2MouseExited
        addbutton2.setBackground(navcolor);
    }//GEN-LAST:event_addbutton2MouseExited

    private void deletebuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseClicked
         String b_id = txtb_id.getText().trim(); // Get Blotter ID

    if (b_id.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select a blotter record to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this blotter record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String url = "jdbc:mysql://localhost:3306/obaob_db";
    String user = "root";
    String pass = "";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        // Delete only from blotter_table
        String deleteBlotterQuery = "DELETE FROM blotter_table WHERE b_id = ?";
        try (PreparedStatement blotterStmt = conn.prepareStatement(deleteBlotterQuery)) {
            blotterStmt.setString(1, b_id);
            int rowsDeleted = blotterStmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Blotter record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No record found to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_deletebuttonMouseClicked

    private void deletebuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseEntered
        deletebutton.setBackground(bodycolor);
    }//GEN-LAST:event_deletebuttonMouseEntered

    private void deletebuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebuttonMouseExited
        deletebutton.setBackground(navcolor);
    }//GEN-LAST:event_deletebuttonMouseExited

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked

    }//GEN-LAST:event_refreshMouseClicked

    private void refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseEntered
        refresh.setBackground(bodycolor);
    }//GEN-LAST:event_refreshMouseEntered

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        refresh.setBackground(navcolor);
    }//GEN-LAST:event_refreshMouseExited

    private void editbutton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseClicked
       String selectedSuspect = (String) citizendisplay.getSelectedItem();
    if (selectedSuspect == null || selectedSuspect.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select a suspect from the list.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    Integer suspectCid = getCidByFullName(selectedSuspect);
    if (suspectCid == null) {
        JOptionPane.showMessageDialog(null, "Selected suspect not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String complainant;
    if (box.isSelected()) {
        String selectedComplainant = (String) othersCombobox.getSelectedItem();
        if (selectedComplainant == null || selectedComplainant.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a complainant from the list.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        complainant = selectedComplainant;
    } else {
        complainant = txtComplainant1.getText().trim();
        if (complainant.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the complainant's name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    String b_id_str = txtb_id.getText().trim();
    if (b_id_str.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No blotter record selected to edit.");
        return;
    }

    int b_id;
    try {
        b_id = Integer.parseInt(b_id_str);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Invalid blotter ID.");
        return;
    }

    String b_incident = txtincident.getText().trim();
    String b_location = txtlocation.getText().trim();
    String b_status = cmbstatus.getSelectedItem().toString();
    String b_witness1 = txtwitness1.getText().trim();
    String b_witness2 = txtwitness2.getText().trim();

    if (b_incident.isEmpty() || b_location.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in the incident and location fields.");
        return;
    }

    if ("Settled".equalsIgnoreCase(b_status)) {
        JOptionPane.showMessageDialog(null, "The case is settled. Data cannot be edited.");
        return;
    }

    String updateSQL = "UPDATE blotter_table SET c_id = ?, b_fname = ?, b_incident = ?, b_location = ?, b_status = ?, b_witness1 = ?, b_witness2 = ? WHERE b_id = ?";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/obaob_db", "root", "");
         PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

        pstmt.setInt(1, suspectCid);
        pstmt.setString(2, complainant);
        pstmt.setString(3, b_incident);
        pstmt.setString(4, b_location);
        pstmt.setString(5, b_status);
        pstmt.setString(6, b_witness1);
        pstmt.setString(7, b_witness2);
        pstmt.setInt(8, b_id);

        if (pstmt.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Blotter record updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update blotter record.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error while updating blotter record.");
    }
        
        
    }//GEN-LAST:event_editbutton1MouseClicked

    private void editbutton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseEntered
        editbutton1.setBackground(bodycolor);
    }//GEN-LAST:event_editbutton1MouseEntered

    private void editbutton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbutton1MouseExited
        editbutton1.setBackground(navcolor);
    }//GEN-LAST:event_editbutton1MouseExited

    private void txtComplainant1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplainant1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplainant1ActionPerformed

    private void txtincidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtincidentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtincidentActionPerformed

    private void txtdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateActionPerformed

    private void txtlocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlocationActionPerformed

    private void txtwitness1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtwitness1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtwitness1ActionPerformed

    private void txtwitness2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtwitness2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtwitness2ActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        new userBlotter(fullname, userImagePath).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        back.setBackground(bodycolor);
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        back.setBackground(navcolor);
    }//GEN-LAST:event_backMouseExited

    private void txtb_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtb_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtb_idActionPerformed

    private void boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxActionPerformed
        if (box.isSelected()) {
            othersCombobox.setVisible(true);      // Show second combo box
            populateOthersComboBox();             // Load names into it

        } else {
            othersCombobox.setVisible(false);     // Hide it again

        }
    }//GEN-LAST:event_boxActionPerformed

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
            java.util.logging.Logger.getLogger(userBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userBlotterCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String imgPath = "path/to/default/image.png";
                new userBlotterCRUD("Regular User", imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel addbutton2;
    private javax.swing.JPanel back;
    private javax.swing.JCheckBox box;
    private javax.swing.JComboBox<String> citizendisplay;
    private javax.swing.JComboBox<String> cmbstatus;
    private javax.swing.JPanel deletebutton;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel edit1;
    private javax.swing.JLabel edit2;
    private javax.swing.JPanel editbutton1;
    private javax.swing.JLabel email;
    private javax.swing.JLabel email1;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    private javax.swing.JLabel fn1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel ln;
    private javax.swing.JLabel ln1;
    private javax.swing.JLabel ln2;
    private javax.swing.JLabel ln4;
    private javax.swing.JPanel main;
    private javax.swing.JComboBox<String> othersCombobox;
    private javax.swing.JPanel refresh;
    private javax.swing.JLabel refresh1;
    private javax.swing.JTextField txtComplainant1;
    private javax.swing.JTextField txtb_id;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtincident;
    private javax.swing.JTextField txtlocation;
    private javax.swing.JTextField txtwitness1;
    private javax.swing.JTextField txtwitness2;
    // End of variables declaration//GEN-END:variables
}
