package javadasar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

public class KalkulatorGUI extends JFrame {
    private JTextField inputA;
    private JTextField inputB;
    private JTextField inputTinggi;
    private JTextField inputTanggalLahir;
    private JTextArea outputArea;
    private JButton calculateButton;
    private JComboBox<String> kalkulatorOptions;
    private JComboBox<String> operatorOptions; // Tambahkan combo box untuk operator aritmatika

    // Panel untuk input agar dapat diubah berdasarkan kalkulator yang dipilih
    private JPanel panelA;
    private JPanel panelB;
    private JPanel panelOperator;
    private JPanel panelTinggi;
    private JPanel panelTanggalLahir;

    public KalkulatorGUI() {
        // Pengaturan frame
        setTitle("Kalkulator Multi-Fungsi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Opsi jenis kalkulator
        String[] kalkulatorTipe = { "Aritmetika", "Pangkat", "Akar Kuadrat", "Kalkulator BMI", "Hitung Umur" };
        kalkulatorOptions = new JComboBox<>(kalkulatorTipe);

        // Opsi operator untuk aritmatika
        String[] operatorTipe = { "+", "-", "*", "/" };
        operatorOptions = new JComboBox<>(operatorTipe);

        // Membuat komponen input
        panelA = new JPanel();
        panelB = new JPanel();
        panelOperator = new JPanel();
        panelTinggi = new JPanel();
        panelTanggalLahir = new JPanel();

        JLabel labelA = new JLabel("Nilai a:");
        inputA = new JTextField(10);
        JLabel labelB = new JLabel("Nilai b:");
        inputB = new JTextField(10);
        JLabel labelTinggi = new JLabel("Tinggi (dalam meter) untuk BMI:");
        inputTinggi = new JTextField(10);
        JLabel labelTanggalLahir = new JLabel("Masukkan Tanggal Lahir (YYYY-MM-DD):");
        inputTanggalLahir = new JTextField(10);

        calculateButton = new JButton("Hitung");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Menambahkan komponen ke panel input
        panelA.add(labelA);
        panelA.add(inputA);
        panelB.add(labelB);
        panelB.add(inputB);
        panelOperator.add(new JLabel("Operator:"));
        panelOperator.add(operatorOptions);

        panelTinggi.add(labelTinggi);
        panelTinggi.add(inputTinggi);
        panelTanggalLahir.add(labelTanggalLahir);
        panelTanggalLahir.add(inputTanggalLahir);

        // Menambahkan komponen ke frame
        add(new JLabel("Pilih Kalkulator:"));
        add(kalkulatorOptions);
        add(calculateButton);
        add(new JScrollPane(outputArea));

        // Menambahkan action listener untuk JComboBox
        kalkulatorOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();  // Tambahkan fitur reset otomatis
                updateInputFields();
            }
        });

        // Event handling untuk tombol hitung
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        // Update pertama kali
        updateInputFields();
    }

    // Metode untuk memperbarui tampilan input berdasarkan opsi kalkulator
    private void updateInputFields() {
        // Bersihkan panel dari semua komponen input
        remove(panelA);
        remove(panelB);
        remove(panelOperator);
        remove(panelTinggi);
        remove(panelTanggalLahir);

        // Ambil kalkulator yang dipilih
        String selectedCalc = (String) kalkulatorOptions.getSelectedItem();

        // Atur tampilan input berdasarkan kalkulator yang dipilih
        switch (selectedCalc) {
            case "Aritmetika":
                add(panelA);
                add(panelB);
                add(panelOperator); // Tambahkan operator untuk aritmatika
                break;
            case "Pangkat":
            case "Akar Kuadrat":
                add(panelA); // Hanya satu nilai input diperlukan
                break;
            case "Kalkulator BMI":
                add(panelA); // Nilai A = berat
                add(panelTinggi); // Nilai tinggi
                break;
            case "Hitung Umur":
                add(panelTanggalLahir); // Input untuk tanggal lahir
                break;
        }

        // Refresh tampilan setelah perubahan
        revalidate();
        repaint();
    }

    // Metode untuk mereset semua field input
    private void resetFields() {
        inputA.setText("");
        inputB.setText("");
        inputTinggi.setText("");
        inputTanggalLahir.setText("");
        outputArea.setText("");
    }

    private void calculate() {
        String selectedCalc = (String) kalkulatorOptions.getSelectedItem();
        try {
            switch (selectedCalc) {
                case "Aritmetika":
                    kalkulatorAritmetika();
                    break;
                case "Pangkat":
                    kalkulatorPangkat();
                    break;
                case "Akar Kuadrat":
                    kalkulatorAkarKuadrat();
                    break;
                case "Kalkulator BMI":
                    kalkulatorBMI();
                    break;
                case "Hitung Umur":
                    kalkulatorUmur();
                    break;
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Harap masukkan angka yang valid!");
        }
    }

    private void kalkulatorAritmetika() {
        float a = Float.parseFloat(inputA.getText());
        float b = Float.parseFloat(inputB.getText());
        String operator = (String) operatorOptions.getSelectedItem(); // Mengambil operator dari combo box

        float hasil = 0;
        switch (operator) {
            case "+":
                hasil = a + b;
                break;
            case "-":
                hasil = a - b;
                break;
            case "*":
                hasil = a * b;
                break;
            case "/":
                if (b == 0) {
                    outputArea.setText("Tidak dapat dibagi dengan nol");
                    return;
                }
                hasil = a / b;
                break;
        }
        outputArea.setText("Hasil " + a + " " + operator + " " + b + " = " + hasil);
    }

    private void kalkulatorPangkat() {
        double a = Double.parseDouble(inputA.getText());
        double b = Double.parseDouble(inputB.getText());
        double hasil = Math.pow(a, b);
        outputArea.setText("Hasil " + a + " pangkat " + b + " = " + hasil);
    }

    private void kalkulatorAkarKuadrat() {
        double a = Double.parseDouble(inputA.getText());
        double hasil = Math.sqrt(a);
        outputArea.setText("Hasil akar kuadrat dari " + a + " = " + hasil);
    }

    private void kalkulatorBMI() {
        try {
            double berat = Double.parseDouble(inputA.getText());
            double tinggi = Double.parseDouble(inputTinggi.getText());

            if (tinggi == 0) {
                outputArea.setText("Tinggi tidak boleh nol!");
                return;
            }

            double bmi = berat / (tinggi * tinggi);
            String status;
            if (bmi < 18.5) {
                status = "Kekurangan berat badan";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                status = "Berat badan normal";
            } else if (bmi >= 25 && bmi <= 29.9) {
                status = "Kelebihan berat badan";
            } else {
                status = "Obesitas";
            }

            outputArea.setText("BMI = " + bmi + " (" + status + ")");
        } catch (NumberFormatException e) {
            outputArea.setText("Masukkan berat dan tinggi yang valid.");
        }
    }

    private void kalkulatorUmur() {
        try {
            LocalDate tanggalLahir = LocalDate.parse(inputTanggalLahir.getText());
            LocalDate tanggalSekarang = LocalDate.now();
            Period umur = Period.between(tanggalLahir, tanggalSekarang);

            outputArea.setText("Umur anda: " + umur.getYears() + " tahun, " +
                    umur.getMonths() + " bulan, dan " + umur.getDays() + " hari.");
        } catch (Exception e) {
            outputArea.setText("Format tanggal salah, gunakan format YYYY-MM-DD.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KalkulatorGUI gui = new KalkulatorGUI();
            gui.setVisible(true);
        });
    }
}
