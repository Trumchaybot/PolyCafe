/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poly.cafe.ui.component;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import poly.cafe.dao.EmployeeInfoDAO;
import poly.cafe.dao.impl.ImportDAOImpl;
import poly.cafe.dao.impl.ExportDAOImpl;
import poly.cafe.entity.Suppliers;
import poly.cafe.entity.Import;
import poly.cafe.entity.Export;
import poly.cafe.ui.component.controller.QuanlyNhapXuatController;
import poly.cafe.ui.manager.ImAndExManagerDialog;
import poly.cafe.util.XJdbc;
import poly.cafe.dao.ExportDAO;
import poly.cafe.dao.ImportDAO;
import poly.cafe.dao.impl.EmployeeInfoDAOImpl;
import poly.cafe.util.TimeRange;
import poly.cafe.util.XDate;

/**
 *
 * @author admin
 */
public class QuanLyNhapXuat extends javax.swing.JPanel implements QuanlyNhapXuatController {

    ExportDAO daoxk = new ExportDAOImpl();
    ImportDAO daonk = new ImportDAOImpl();
    private Frame owner;
    private JDateChooser dateImport;

    /**
     * Creates new form QuanLyNhapXuat
     */
    public QuanLyNhapXuat() {
        initComponents();
        this.open();
        this.Front();
    }

    public QuanLyNhapXuat(Frame owner) {
        this.owner = owner;
        initComponents();
        open();
        dateImport = new JDateChooser();
        dateImport.setDateFormatString("dd/MM/yyyy");  // Định dạng bạn muốn
        jPanel1.add(dateImport);
    }

    public void Front() {
        jPanel1.setBackground(new Color(255, 255, 200));
        jPanel2.setBackground(new Color(255, 255, 200));
        jPanel3.setBackground(new Color(255, 255, 200));
        btnAllExport.setBackground(new Color(255, 255, 230));
        jButton2.setBackground(new Color(255, 255, 230));
        jButton4.setBackground(new Color(255, 255, 230));
        cboUserExport.setBackground(new Color(255, 255, 230));
        cboMonthExport.setBackground(new Color(255, 255, 230));
        cboYearExport.setBackground(new Color(255, 255, 230));
        cboUserImport.setBackground(new Color(255, 255, 230));
        jButton1.setBackground(new Color(255, 255, 230));
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 230));
        jScrollPane2.getViewport().setBackground(new Color(255, 255, 230));
        tblNhapKho.setSelectionBackground(new Color(255, 255, 230)); // cùng màu nền bạn set
        tblNhapKho.setSelectionForeground(Color.BLACK);
        JTableHeader header = tblNhapKho.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setBackground(new Color(255, 255, 230)); // Màu be nhạt
                c.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font đậm
                setHorizontalAlignment(CENTER);            // Canh giữa chữ

                return c;
            }
        });
        tblXuatKho.setSelectionBackground(new Color(255, 255, 230)); // cùng màu nền bạn set
        tblXuatKho.setSelectionForeground(Color.BLACK);
        JTableHeader headerr = tblXuatKho.getTableHeader();
        headerr.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setBackground(new Color(255, 255, 230)); // Màu be nhạt
                c.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font đậm
                setHorizontalAlignment(CENTER);            // Canh giữa chữ

                return c;
            }
        });
        jTabbedPane1.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement,
                    int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                if (isSelected) {
                    g.setColor(new Color(255, 255, 200)); // màu tab được chọn
                } else {
                    g.setColor(new Color(255, 255, 230)); // màu tab chưa chọn
                }
                g.fillRect(x, y, w, h);
            }
        });
    }

    private void fillUserImport() {
        cboUserImport.removeAllItems();
        cboUserImport.addItem("All");
        String sql = "SELECT DISTINCT UserName FROM Import ORDER BY UserName";
        try (ResultSet rs = XJdbc.executeQuery(sql)) {
            while (rs.next()) {
                cboUserImport.addItem(String.valueOf(rs.getString("UserName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillUserExport() {
        cboUserExport.removeAllItems();
        cboUserExport.addItem("All");
        String sql = "SELECT DISTINCT UserName FROM Export ORDER BY UserName";
        try (ResultSet rs = XJdbc.executeQuery(sql)) {
            while (rs.next()) {
                cboUserExport.addItem(String.valueOf(rs.getString("UserName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectAll(JComboBox... combos) {
        for (JComboBox combo : combos) {
            combo.setSelectedItem("All");
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

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapKho = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cboUserImport = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtBegin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cboTimeRanges = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboUserExport = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboMonthExport = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboYearExport = new javax.swing.JComboBox<>();
        btnAllExport = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblXuatKho = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        tblNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhập", "Người tạo", "Nhà cung cấp", "Tổng tiền", "Ngày nhập", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapKhoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhapKho);

        jLabel7.setText("UserName:");

        cboUserImport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Họ Tên:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jButton2.setText("Thêm đơn nhập");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Từ ngày:");

        jLabel6.setText("Đến ngày:");

        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboTimeRanges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Quý này", "Năm này" }));
        cboTimeRanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimeRangesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboUserImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboUserImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhập kho", jPanel1);

        jLabel1.setText("UserName");

        cboUserExport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Họ Tên");

        jLabel3.setText("Tháng");

        cboMonthExport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Năm");

        cboYearExport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAllExport.setText("All");

        tblXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Người tạo", "Vật liệu", "Số lượng", "Tổng tiền", "Ngày xuất"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblXuatKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXuatKhoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblXuatKho);

        jButton4.setText("Thêm đơn xuất");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboUserExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMonthExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboYearExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAllExport)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(41, 41, 41))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboUserExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboMonthExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboYearExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAllExport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Xuất kho", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Import newNhapKho = new Import();
        newNhapKho.setImportDate(new Date());

// Giả định gán khách mặc định để tránh lỗi null
        Suppliers ncc = new Suppliers();
        ncc.setSupplierId(1); // ID khách mặc định trong hệ thống
        newNhapKho.setSupplier(ncc);  // Gán khách hàng vào

        int newId = daonk.insertAndReturnId(newNhapKho);
        newNhapKho.setImportId(newId);

// Mở dialog
        ImAndExManagerDialog dialog = new ImAndExManagerDialog(owner, true);
        dialog.setImportId(newId);
        dialog.setFormNhapKho(newNhapKho);
        dialog.setSelectedTab(0);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblNhapKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapKhoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.editNhapKho();
        }
    }//GEN-LAST:event_tblNhapKhoMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Export newXuatKho = new Export();
        newXuatKho.setExportDate(new Date());

// Giả định gán khách mặc định để tránh lỗi null
        Suppliers ncc = new Suppliers();

        int newId = daoxk.insertAndReturnId(newXuatKho);
        newXuatKho.setExportId(newId);

// Mở dialog
        ImAndExManagerDialog dialog = new ImAndExManagerDialog(owner, true);
        dialog.setExportId(newId);
        dialog.setFormXuatKho(newXuatKho);
        dialog.setSelectedTab(1);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblXuatKhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatKhoMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.editXuatKho();
        }
    }//GEN-LAST:event_tblXuatKhoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.fillToTableNhapKho();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboTimeRangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimeRangesActionPerformed
        // TODO add your handling code here:
        this.selectTimeRange();
    }//GEN-LAST:event_cboTimeRangesActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllExport;
    private javax.swing.JComboBox<String> cboMonthExport;
    private javax.swing.JComboBox<String> cboTimeRanges;
    private javax.swing.JComboBox<String> cboUserExport;
    private javax.swing.JComboBox<String> cboUserImport;
    private javax.swing.JComboBox<String> cboYearExport;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblNhapKho;
    private javax.swing.JTable tblXuatKho;
    private javax.swing.JTextField txtBegin;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
 @Override
    public void fillToTableXuatKho() {
        DefaultTableModel model = (DefaultTableModel) tblXuatKho.getModel();
        model.setRowCount(0);

        List<Export> list = daoxk.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Export xk : list) {
            model.addRow(new Object[]{
                xk.getExportId(),
                xk.getUserName(),
                xk.getTotalAmount(),
                xk.getExportDate() != null ? sdf.format(xk.getExportDate()) : ""
            });
        }
    }

    @Override
    public void open() {
        this.selectTimeRange();
        this.addComboBoxListeners();
        this.fillToTableNhapKho();
        this.fillToTableXuatKho();
        this.fillUserImport();
        this.fillUserExport();
    }

    

    public void editNhapKho() {
        int selectedRow = tblNhapKho.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn nhập để chỉnh sửa.");
            return;
        }

        Integer donNhapId = (Integer) tblNhapKho.getValueAt(selectedRow, 0);

        Import nk = daonk.findById(donNhapId);
        if (nk == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin đơn nhập!");
            return;
        }

        // Mở JDialog và đổ dữ liệu vào
        ImAndExManagerDialog dialog = new ImAndExManagerDialog(null, true);
        dialog.setFormNhapKho(nk); // Hàm setForm để set các giá trị vào textfield, combobox, radio...
        dialog.setSelectedTab(0);
        dialog.setVisible(true);

        // Sau khi đóng dialog có thể cập nhật lại bảng nếu có thay đổi
        fillToTableNhapKho();
    }

    public void editXuatKho() {
        int selectedRow = tblXuatKho.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn nhập để chỉnh sửa.");
            return;
        }

        Integer donXuatId = (Integer) tblXuatKho.getValueAt(selectedRow, 0);

        Export xk = daoxk.findById(donXuatId);
        if (xk == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin đơn nhập!");
            return;
        }

        // Mở JDialog và đổ dữ liệu vào
        ImAndExManagerDialog dialog = new ImAndExManagerDialog(null, true);
        dialog.setFormXuatKho(xk); // Hàm setForm để set các giá trị vào textfield, combobox, radio...
        dialog.setSelectedTab(1);
        dialog.setVisible(true);

        // Sau khi đóng dialog có thể cập nhật lại bảng nếu có thay đổi
        fillToTableXuatKho();
    }

    @Override
    public void selectTimeRange() {
        TimeRange range = TimeRange.today();
        switch (cboTimeRanges.getSelectedIndex()) {
            case 0 ->
                range = TimeRange.today();
            case 1 ->
                range = TimeRange.thisWeek();
            case 2 ->
                range = TimeRange.thisMonth();
            case 3 ->
                range = TimeRange.thisQuarter();
            case 4 ->
                range = TimeRange.thisYear();
        }
        txtBegin.setText(XDate.format(range.getBegin(), "dd/MM/yyyy"));
        txtEnd.setText(XDate.format(range.getEnd(), "dd/MM/yyyy"));
        this.fillToTableNhapKho();
    }
    private void addComboBoxListeners() {
    cboUserImport.addActionListener(e -> {
        fillHoTen();   // fill họ tên khi chọn UserName
        fillToTableNhapKho(); // load lại table theo UserName
    });
    
}
    private void fillHoTen() {
    String selectedUser = (String) cboUserImport.getSelectedItem();
    if (selectedUser != null && !"All".equals(selectedUser)) {
        EmployeeInfoDAO thongTinNVDAO = new EmployeeInfoDAOImpl();
        String hoTen = thongTinNVDAO.findFullNameByUserName(selectedUser);
        txtName.setText(hoTen != null ? hoTen : "Không tìm thấy");
    } else {
        txtName.setText("");
    }
}
    @Override
    public void fillToTableNhapKho() {
    DefaultTableModel model = (DefaultTableModel) tblNhapKho.getModel();
    model.setRowCount(0);

    ImportDAO dao = new ImportDAOImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    String user = (String) cboUserImport.getSelectedItem();
    String fromDateStr = txtBegin.getText().trim();
    String toDateStr = txtEnd.getText().trim();
    
    // Kiểm tra ngày rỗng
    if (fromDateStr.isEmpty() || toDateStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Từ ngày và Đến ngày!");
        return;
    }

    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
        LocalDate toDate = LocalDate.parse(toDateStr, formatter);

        // 🔹 Lấy danh sách đã lọc
        List<Import> imports;
        if ("All".equalsIgnoreCase(user)) {
            imports = dao.findByImportDate(fromDate, toDate);
        } else {
            imports = dao.findByImportDateAndUsername(fromDate, toDate, user);
        }

        // 🔹 Hiển thị dữ liệu lên bảng
        for (Import nk : imports) {
            String tenNCC = (nk.getSupplier() != null)
                    ? nk.getSupplier().getSupplierName()
                    : "Chưa có nhà cung cấp";

            model.addRow(new Object[]{
                nk.getImportId(),
                nk.getUserName(),
                tenNCC,
                nk.getTotalAmount(),
                nk.getImportDate() != null ? sdf.format(nk.getImportDate()) : ""
            });
        }

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ! (dd/MM/yyyy)");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi không xác định khi lọc dữ liệu: " + e.getMessage());
    }
}

}
