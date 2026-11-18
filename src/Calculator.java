import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JTextField ResultTextField;
    private JButton OneButton;
    private JButton FourButton;
    private JButton ModuloButton;
    private JButton SevenButton;
    private JButton MultiplicationButton;
    private JButton DivisionButton;
    private JButton SubtractionButton;
    private JButton SumButton;
    private JButton PercentButton;
    private JButton NineButton;
    private JButton SixButton;
    private JButton ThreeButton;
    private JButton AbsButton;
    private JButton EightButton;
    private JButton FiveButton;
    private JButton TwoButton;
    private JButton ACButton;
    private JButton ZeroButton;
    private JButton CommaButton;
    private JButton EqualsButton;
    private JPanel CalculatorPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().CalculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private double firstNumber = 0;
    private double secondNumber = 0;

    private String operator = "";

    private boolean isNewOperation = true;

    private void clear() {
        ResultTextField.setText("0");

        firstNumber = 0;
        secondNumber = 0;
        operator = "";

        isNewOperation = true;
    }

    private void setOperator(String op) {
        if (!operator.isEmpty()) {
            calculateResult();
        }

        firstNumber = Double.parseDouble(
                ResultTextField.getText().replace(',', '.')
        );

        operator = op;
        isNewOperation = true;
    }

    private void calculateResult() {
        secondNumber = Double.parseDouble(
                ResultTextField.getText().replace(',', '.')
        );

        double result = switch (operator) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> secondNumber != 0
                    ? firstNumber / secondNumber : 0;
            case "%" -> firstNumber % secondNumber;
            default -> 0;
        };

        ResultTextField.setText(String.valueOf(result));

        firstNumber = result;
        operator = "";
        isNewOperation = true;
    }

    private void addDecimalPoint() {
        if (isNewOperation) {
            ResultTextField.setText("0,");
            isNewOperation = false;
        } else if (!ResultTextField.getText().contains(",")) {
            ResultTextField.setText(
                    ResultTextField.getText() + ","
            );
        }
    }

    private void applyPercent() {
        double value = Double.parseDouble(
                ResultTextField.getText().replace(',', '.')
        );

        value = value / 100;

        ResultTextField.setText(
                String.valueOf(value).replace('.', ',')
        );

        isNewOperation = true;
    }

    private void applyAbs() {
        double value = Double.parseDouble(
                ResultTextField.getText().replace(',', '.')
        );

        value = value * -1;

        ResultTextField.setText(
                String.valueOf(value).replace('.', ',')
        );

        isNewOperation = true;
    }

    public Calculator() {
        ActionListener numberListener = new NumberListener();

        ZeroButton.addActionListener(numberListener);
        OneButton.addActionListener(numberListener);
        TwoButton.addActionListener(numberListener);
        ThreeButton.addActionListener(numberListener);
        FourButton.addActionListener(numberListener);
        FiveButton.addActionListener(numberListener);
        SixButton.addActionListener(numberListener);
        SevenButton.addActionListener(numberListener);
        EightButton.addActionListener(numberListener);
        NineButton.addActionListener(numberListener);

        CommaButton.addActionListener(e -> addDecimalPoint());

        SumButton.addActionListener(e -> setOperator("+"));
        SubtractionButton.addActionListener(e -> setOperator("-"));
        MultiplicationButton.addActionListener(e -> setOperator("*"));
        DivisionButton.addActionListener(e -> setOperator("/"));
        ModuloButton.addActionListener(e -> setOperator("%"));

        PercentButton.addActionListener(e -> applyPercent());
        AbsButton.addActionListener(e -> applyAbs());

        EqualsButton.addActionListener(e -> calculateResult());
        ACButton.addActionListener(e -> clear());

        ResultTextField.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private class NumberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String value = ((JButton) e.getSource()).getText();

            if (isNewOperation) {
                ResultTextField.setText(value);
                isNewOperation = false;
            } else {
                ResultTextField.setText(
                        ResultTextField.getText() + value
                );
            }
        }
    }
}

