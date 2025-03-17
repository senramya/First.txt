import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATIApplicationForm extends JFrame {

    private JTextField nameField, addressField, yearField, dateOfBirthField;
    private JComboBox<String> courseComboBox;
    private JButton submitButton, closeButton;

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
                    // Process the form data (e.g., save to a file or database)
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String course = (String) courseComboBox.getSelectedItem();
                    String year = yearField.getText();
                    String dateOfBirth = dateOfBirthField.getText();

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATIApplicationForm();
            }
        });
    }
}