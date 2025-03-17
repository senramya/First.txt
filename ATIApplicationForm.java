import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class ATIApplicationForm extends JFrame {

    private JTextField nameField, addressField, yearField, dateOfBirthField;
    private JComboBox<String> courseComboBox;
    private JButton submitButton, closeButton;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your DB URL
    private static final String DB_USER = "root";  // Replace with your DB username
    private static final String DB_PASSWORD = "password";  // Replace with your DB password

    public ATIApplicationForm() {
        setTitle("Application for ATI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2)); // Grid layout for labels and fields

        // Labels and Text Fields
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Course:"));
        String[] courses = {"Course A", "Course B", "Course C"}; // Replace with actual courses
        courseComboBox = new JComboBox<>(courses);
        add(courseComboBox);

        add(new JLabel("Year:"));
        yearField = new JTextField();
        add(yearField);

        add(new JLabel("Date of Birth (Date & time):"));
        dateOfBirthField = new JTextField();
        add(dateOfBirthField);

        // Buttons
        closeButton = new JButton("Close");
        submitButton = new JButton("Submit");

        add(closeButton);
        add(submitButton);

        // Button Actions
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    // Process the form data and save to the database
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String course = (String) courseComboBox.getSelectedItem();
                    String year = yearField.getText();
                    String dateOfBirth = dateOfBirthField.getText();

                    // Insert data into the database
                    saveToDatabase(name, address, course, year, dateOfBirth);

                    // Display a confirmation message
                    JOptionPane.showMessageDialog(ATIApplicationForm.this,
                            "Your Application is Successful",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private boolean validateForm() {
        // Validation logic (e.g., check for empty fields, date format)
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                yearField.getText().isEmpty() || dateOfBirthField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate Date of Birth format (e.g., "yyyy-MM-dd HH:mm")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = dateFormat.parse(dateOfBirthField.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveToDatabase(String name, String address, String course, String year, String dateOfBirth) {
        // JDBC connection and data insertion
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Establish connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to insert form data
            String sql = "INSERT INTO applications (name, address, course, year, date_of_birth) VALUES (?, ?, ?, ?, ?)";

            // Prepare the statement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, course);
            stmt.setInt(4, Integer.parseInt(year));  // Ensure year is an integer
            stmt.setString(5, dateOfBirth);  // Insert date as string (you can modify if needed)

            // Execute the update (insert into database)
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                // Close resources
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATIApplicationForm();
            }
        });
    }
}

