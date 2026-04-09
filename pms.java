import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

/*new changes added*/
// Jenkins trigger
class ProjectManagementSystem extends JFrame implements ActionListener {
    private static DefaultTableModel model;
    JLabel lpms, l1, l2, l3, l4, l5;
    JTextField pid, ptitle, leader, domain, budget;
    JButton submit, clear;

    public ProjectManagementSystem() {
        String[] columnNames = {"Project ID", "Project Title", "Leader", "Domain", "Budget"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(500, 80, 420, 350);
        add(scrollPane);
        table.setFillsViewportHeight(true);

        lpms = new JLabel("Project Management System");
        l1 = new JLabel("Project ID:");
        l2 = new JLabel("Project Title:");
        l3 = new JLabel("Leader:");
        l4 = new JLabel("Domain:");
        l5 = new JLabel("Budget:");
        pid = new JTextField();
        ptitle = new JTextField();
        leader = new JTextField();
        domain = new JTextField();
        budget = new JTextField();
        submit = new JButton("Submit");
        clear = new JButton("Clear");

        lpms.setFont(new java.awt.Font("Segoe Print", 1, 24));
        l1.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        l2.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        l3.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        l4.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        l5.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        submit.setFont(new java.awt.Font("Segoe Print", 0, 14));
        clear.setFont(new java.awt.Font("Segoe Print", 0, 14));

        lpms.setBounds(50, 30, 380, 40);
        l1.setBounds(80, 120, 100, 30);
        pid.setBounds(230, 120, 200, 30);
        l2.setBounds(80, 180, 150, 30);
        ptitle.setBounds(230, 180, 200, 30);
        l3.setBounds(80, 240, 100, 30);
        leader.setBounds(230, 240, 200, 30);
        l4.setBounds(80, 300, 100, 30);
        domain.setBounds(230, 300, 200, 30);
        l5.setBounds(80, 360, 100, 30);
        budget.setBounds(230, 360, 200, 30);
        clear.setBounds(400, 450, 100, 30);
        submit.setBounds(500, 450, 100, 30);

        clear.addActionListener(this);
        submit.addActionListener(this);
        
        add(lpms);
        add(l1);
        add(pid);
        add(l2);
        add(ptitle);
        add(l3);
        add(leader);
        add(l4);
        add(domain);
        add(l5);
        add(budget);
        add(clear);
        add(submit);
        add(scrollPane);

        setSize(1000, 600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            clearFields();
        } else if (e.getSource() == submit) {
            insertData();
        }
    }

    private void clearFields() {
        pid.setText("");
        ptitle.setText("");
        leader.setText("");
        domain.setText("");
        budget.setText("");
    }

    private void insertData() {
        String ID = pid.getText();
        String TITLE = ptitle.getText();
        String DOMAIN = domain.getText();
        String LEADER = leader.getText();
        String BUDGET = budget.getText();
        model.addRow(new Object[]{ID, TITLE, LEADER, DOMAIN, BUDGET});
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PMS?useSSL=false", "root", "dbms");
             PreparedStatement pstm = con.prepareStatement("INSERT INTO project(ProjectID,PTitle,Domain,Leader,Budget) VALUES (?, ?, ?, ?, ?)")) {

            
            pstm.setInt(1, Integer.parseInt(ID));
            pstm.setString(2, TITLE);
            pstm.setString(3, DOMAIN);
            pstm.setString(4, LEADER);
            pstm.setInt(5, Integer.parseInt(BUDGET));
            pstm.executeUpdate();

            clearFields();
            JOptionPane.showMessageDialog(this, "Data inserted successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Project ID and Budget");
        }
    }
}

public class pp {
    public static void main(String[] args) {
        System.out.println("Hello world");
        new ProjectManagementSystem();
    }
}
