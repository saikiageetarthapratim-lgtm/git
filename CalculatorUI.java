import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame implements ActionListener {
    // UI components
    private JTextField num1Field, num2Field, resultField;
    private JButton addBtn, subBtn, mulBtn, divBtn, clearBtn, exitBtn;

    public CalculatorUI() {
        setTitle("Simple Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Labels and text fields
        add(new JLabel("Enter First Number:"));
        num1Field = new JTextField();
        add(num1Field);

        add(new JLabel("Enter Second Number:"));
        num2Field = new JTextField();
        add(num2Field);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        // Buttons
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        clearBtn = new JButton("Clear");
        exitBtn = new JButton("Exit");

        // Add listeners
        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        divBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        // Add buttons to frame
        add(addBtn);
        add(subBtn);
        add(mulBtn);
        add(divBtn);
        add(clearBtn);
        add(exitBtn);

        // Center the window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = num1Field.getText();
        String s2 = num2Field.getText();

        try {
            double num1 = Double.parseDouble(s1);
            double num2 = Double.parseDouble(s2);
            double result = 0;

            if (e.getSource() == addBtn) {
                result = num1 + num2;
            } else if (e.getSource() == subBtn) {
                result = num1 - num2;
            } else if (e.getSource() == mulBtn) {
                result = num1 * num2;
            } else if (e.getSource() == divBtn) {
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return;
                }
                result = num1 / num2;
            } else if (e.getSource() == clearBtn) {
                num1Field.setText("");
                num2Field.setText("");
                resultField.setText("");
                return;
            } else if (e.getSource() == exitBtn) {
                System.exit(0);
            }

            resultField.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorUI());
    }
}
