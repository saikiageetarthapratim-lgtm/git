import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame implements ActionListener {
    // UI components
    private JTextField num1Field, num2Field, resultField;
    private JButton addBtn, subBtn, mulBtn, divBtn, clearBtn, exitBtn;
    private JButton modBtn, powBtn, factBtn, hashBtn, openBtn, closeBtn;

    public CalculatorUI() {
        setTitle("Simple Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 10, 10));

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
        modBtn = new JButton("%");
        powBtn = new JButton("^");
        factBtn = new JButton("!");
        hashBtn = new JButton("#");
        openBtn = new JButton("(");
        closeBtn = new JButton(")");
        clearBtn = new JButton("Clear");
        exitBtn = new JButton("Exit");

        // Add Action Listeners
        JButton[] buttons = {
            addBtn, subBtn, mulBtn, divBtn, modBtn, powBtn,
            factBtn, hashBtn, openBtn, closeBtn, clearBtn, exitBtn
        };
        for (JButton b : buttons) {
            b.addActionListener(this);
        }

        // Add buttons to layout
        add(addBtn); add(subBtn);
        add(mulBtn); add(divBtn);
        add(modBtn); add(powBtn);
        add(factBtn); add(hashBtn);
        add(openBtn); add(closeBtn);
        add(clearBtn); add(exitBtn);

        // Center and show window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = num1Field.getText();
        String s2 = num2Field.getText();

        try {
            double num1 = Double.parseDouble(s1.isEmpty() ? "0" : s1);
            double num2 = Double.parseDouble(s2.isEmpty() ? "0" : s2);
            double result = 0;
            String symbol = "";

            Object src = e.getSource();

            if (src == addBtn) {
                result = num1 + num2;
                symbol = "+";
            } else if (src == subBtn) {
                result = num1 - num2;
                symbol = "-";
            } else if (src == mulBtn) {
                result = num1 * num2;
                symbol = "*";
            } else if (src == divBtn) {
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return;
                }
                result = num1 / num2;
                symbol = "/";
            } else if (src == modBtn) {
                result = num1 % num2;
                symbol = "%";
            } else if (src == powBtn) {
                result = Math.pow(num1, num2);
                symbol = "^";
            } else if (src == factBtn) {
                if (num1 < 0 || num1 != Math.floor(num1)) {
                    JOptionPane.showMessageDialog(this, "Factorial only for non-negative integers");
                    return;
                }
                result = factorial((int) num1);
                symbol = "!";
            } else if (src == hashBtn) {
                symbol = "#";
                resultField.setText(symbol);
                return;
            } else if (src == openBtn) {
                symbol = "(";
                resultField.setText(symbol);
                return;
            } else if (src == closeBtn) {
                symbol = ")";
                resultField.setText(symbol);
                return;
            } else if (src == clearBtn) {
                num1Field.setText("");
                num2Field.setText("");
                resultField.setText("");
                return;
            } else if (src == exitBtn) {
                System.exit(0);
            }

            // Show formatted result
            resultField.setText(num1 + " " + symbol + " " + num2 + " = " + result);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }

    private double factorial(int n) {
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorUI());
    }
}
