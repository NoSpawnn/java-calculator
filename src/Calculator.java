import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Calculator implements ActionListener {
    private static final Font font = new Font("Arial", Font.BOLD, 30);
    private static final int sizeWidth = 420;
    private static final int sizeHeight = 550;
    private static final int maxChars = 16;

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, multButton, divButton;
    JButton decButton, eqButton, delButton, clrButton, negButton;
    JPanel panel;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeWidth, sizeHeight);
        frame.setResizable(false);
        frame.setLayout(null);

        // https://www.flaticon.com/free-icon/calculator_548353
        URL iconURL = getClass().getResource("/assets/calculator.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            frame.setIconImage(icon.getImage());
        }

        textField = new JTextField();
        textField.setFont(font);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(50, 25, 300, 50);
        textField.setEditable(false);

        // Instantiate function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        multButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        negButton = new JButton("(-)");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = multButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = eqButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setFocusable(false);
        }

        // Instantiate number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 100, 50);
        clrButton.setBounds(150, 430, 100, 50);
        negButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Numbers and Operations button grid
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton source = (JButton) actionEvent.getSource();
        String text = textField.getText();

        if (source == addButton) {
            num1 = Double.parseDouble(text);
            operator = '+';
            textField.setText("");
            return;
        }

        if (source == subButton) {
            num1 = Double.parseDouble(text);
            operator = '-';
            textField.setText("");
            return;
        }

        if (source == multButton) {
            num1 = Double.parseDouble(text);
            operator = '*';
            textField.setText("");
            return;
        }

        if (source == divButton) {
            num1 = Double.parseDouble(text);
            operator = '/';
            textField.setText("");
            return;
        }

        if (source == eqButton) {
            num2 = Double.parseDouble(text);

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
            return;
        }

        if (source == clrButton) {
            textField.setText("");
            return;
        }

        if (source == negButton) {
            try {
                double temp = Double.parseDouble(text);
                textField.setText(String.valueOf(temp * -1));
            } catch (NumberFormatException _) {
            }
            return;
        }

        if (source == delButton) {
            try {
                textField.setText(text.substring(0, text.length() - 1));
            } catch (Exception _) {
            }
        }

        if (text.length() >= maxChars) return;

        for (JButton button : numberButtons) {
            if (source == button) {
                textField.setText(text + button.getText());
                return;
            }
        }

        if (source == decButton) {
            if (text.isEmpty() || text.contains(".")) {
                return;
            }
            textField.setText(text + ".");
        }


    }
}
