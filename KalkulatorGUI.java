package javadasar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorGUI extends JFrame {
    private JTextField inputA;
    private JTextField inputB;
    private JTextField operatorField;
    private JTextArea outputArea;
    private JButton calculateButton;

    public KalkulatorGUI() {
        // Pengaturan frame
        setTitle("Kalkulator Sederhana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Membuat komponen
        JLabel labelA = new JLabel("Nilai a:");
        inputA = new JTextField(10);
        JLabel labelOperator = new JLabel("Operator (+, -, *, /):");
        operatorField = new JTextField(5);
        JLabel labelB = new JLabel("Nilai b:");
        inputB = new JTextField(10);
        calculateButton = new JButton("Hitung");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Menambahkan komponen ke frame
        add(labelA);
        add(inputA);
        add(labelOperator);
        add(operatorField);
        add(labelB);
        add(inputB);
        add(calculateButton);
        add(new JScrollPane(outputArea));

        // Event handling
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
    }

    private void calculate() {
        float a, b, hasil;
        char operator;

        try {
            a = Float.parseFloat(inputA.getText());
            operator = operatorField.getText().charAt(0);
            b = Float.parseFloat(inputB.getText());

            if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                outputArea.setText("Operator tidak ditemukan");
            } else if (operator == '+') {
                hasil = a + b;
                outputArea.setText("Hasil = " + hasil);
            } else if (operator == '-') {
                hasil = a - b;
                outputArea.setText("Hasil = " + hasil);
            } else if (operator == '*') {
                hasil = a * b;
                outputArea.setText("Hasil = " + hasil);
            } else if (operator == '/') {
                if (b == 0) {
                    outputArea.setText("Tidak dapat dibagi dengan nol");
                } else {
                    hasil = a / b;
                    outputArea.setText("Hasil = " + hasil);
                }
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Harap masukkan angka yang valid!");
        } catch (StringIndexOutOfBoundsException e) {
            outputArea.setText("Harap masukkan operator!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorGUI gui = new KalkulatorGUI();
            gui.setVisible(true);
        });
    }
}
