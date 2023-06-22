import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MainForm extends JFrame {

    DbAPI dbAPI = new DbAPI();
    private JComboBox comboBox1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton newspaperRadioButton;
    private JRadioButton appearsOnRadioButton;
    private JLabel LabelFieldVal;
    private JPanel MainPanel;
    private JButton runQueryButton;
    private JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton createButton;
    private List<JRadioButton> lstRadioButton;
    private List<JButton> lstCRUD_opsButtons;
    ButtonGroup buttonGroup = new ButtonGroup();
    String selectedTableName = "";
    int selectedQueryIndex = -1;
    private static DefaultTableModel tableModel;
    JFrame frame;
    List<List<String>> data  = new ArrayList<>();

    public MainForm() {
        setTitle("Portal to DataBase");
        setVisible(true);
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPanel);

        // Create a JLabel to hold the image
        JLabel imageLabel = new JLabel(new ImageIcon("C:\\Users\\dberg\\OneDrive\\Documentos\\A_mechon_lev_final_projects\\DatabaseProject\\Frontend\\FrontendDB\\src\\images\\writer.jpg"));

        // Set the layout manager to BorderLayout
        setLayout(new BorderLayout());

        // Add the image label to the bottom right corner
        add(imageLabel, BorderLayout.SOUTH);

        // Set the content pane
        setContentPane(getContentPane());
        setVisible(true);


        lstRadioButton = List.of(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7
        , appearsOnRadioButton, newspaperRadioButton);
        lstCRUD_opsButtons = List.of(viewButton, deleteButton, updateButton, createButton);

        showOrHideCRUDOptions(false);
        for (JRadioButton button: lstRadioButton
             ) {
            buttonGroup.add(button);
            button.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                   selectedTableName = selectedRadioButton.getText();
               }
           });
        }
        runQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Query queryToRun = dbAPI.dbMetadata.getQueries().get(selectedQueryIndex);
                List<List<String>> res = dbAPI.runViewQuery(queryToRun);
                showTable(res, queryToRun.queryName);
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedTableName == ""){
                    return;
                } else {
                    data = dbAPI.selectAllFromTable(selectedTableName, null);
                    showTable(data, selectedTableName);
                }
            }
        });
        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(dbAPI.dbMetadata.getQueriesStrings().toArray(new String[0])));
        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedQueryIndex = comboBox1.getSelectedIndex();
                if(selectedQueryIndex == 0){
                    showOrHideCRUDOptions(true);
                } else {
                    showOrHideCRUDOptions(false);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idNumber;
                if (selectedTableName == "") {
                    return;
                }
                // Create a dialog box to prompt the user for the item's ID number
                String idInput = JOptionPane.showInputDialog(MainForm.this, "Please enter the " + selectedTableName + " ID number (1-100):");
                try {
                    idNumber = Integer.parseInt(idInput);
                     System.out.println( selectedTableName + "'s ID number: " + idNumber);

                    // Create a new dialog box with labels and editable text fields
                    JDialog itemDialog = new JDialog(MainForm.this, "Update " + selectedTableName);
                    itemDialog.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 5, 5, 5);

                    Optional<String> condition = Optional.of("where " + dbAPI.getIdFromTableName(selectedTableName) + " = " + idNumber);
                    List<List<String>> res = dbAPI.selectAllFromTable(selectedTableName, condition);

                    // Create the right column labels
                    String[] labels = new String[res.get(0).size()];
                    String[] defaultValues = new String[res.get(0).size()];
                    for (int i = 0; i < res.get(0).size(); i++) {
                        labels[i] = res.get(0).get(i);
                        defaultValues[i] = res.get(1).get(i);
                    }

                    for (int i = 0; i < labels.length; i++) {
                        JLabel label = new JLabel(labels[i]);
                        gbc.gridx = 0;
                        gbc.gridy = i;
                        itemDialog.add(label, gbc);
                    }

                    JTextField[] textFields = new JTextField[defaultValues.length];
                    for (int i = 0; i < defaultValues.length; i++) {
                        textFields[i] = new JTextField(defaultValues[i], 15);
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        itemDialog.add(textFields[i], gbc);
                    }

                    // Create the "Update" button
                    JButton updateButton2 = new JButton("Update");
                    gbc.gridx = 1;
                    gbc.gridy = defaultValues.length;
                    itemDialog.add(updateButton2, gbc);

                    // Create an empty ActionListener for the "Update" button
                    updateButton2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            List<String> enteredTextList = new ArrayList<>();
                            for (JTextField textField : textFields) {
                                enteredTextList.add(textField.getText());
                            }
                            List<List<String>> lstForQuery = new ArrayList<>();
                            lstForQuery.add(List.of(labels));
                            lstForQuery.add(enteredTextList);
                            List<String> idList = new LinkedList<>();
                            idList.add(dbAPI.getIdFromTableName(selectedTableName));
                            idList.add(" " + idNumber);
                            dbAPI.runUpdateQuery(selectedTableName, lstForQuery, idList);

                            itemDialog.dispose();

                            // Present a new dialog for "Update Successful"
                            JDialog successDialog = new JDialog(MainForm.this, "Update Successful", true);
                            successDialog.setLayout(new BorderLayout());

                            // Create the success message label
                            JLabel successLabel = new JLabel("Update Successful");
                            successLabel.setHorizontalAlignment(JLabel.CENTER);
                            successDialog.add(successLabel, BorderLayout.CENTER);

                            // Create the OK button
                            JButton okButton = new JButton("OK");
                            okButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    successDialog.dispose();
                                }
                            });

                            // Create the Cancel button
                            JButton cancelButton = new JButton("Cancel");
                            cancelButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    successDialog.dispose();
                                }
                            });

                            // Create a panel for the buttons
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.add(okButton);
                            buttonPanel.add(cancelButton);
                            successDialog.add(buttonPanel, BorderLayout.SOUTH);

                            // Pack and display the success dialog
                            successDialog.pack();
                            successDialog.setVisible(true);
                        }
                    });

                    itemDialog.pack();
                    itemDialog.setVisible(true);
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    System.out.println("Invalid input: " + idInput);
                }
            }
        });

    }

    private void showTable(List<List<String>> dataToDisplay, String queryTitle){

        // Create the JFrame and set its properties
        JFrame frame = new JFrame(queryTitle);
        // Create the JTable with a DefaultTableModel
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        // Get the column names from the first row of the data
        List<String> columnNames = dataToDisplay.get(0);
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        // Populate the rows in the table
        for (int i = 1; i < dataToDisplay.size(); i++) {
            List<String> rowData = dataToDisplay.get(i);
            model.addRow(rowData.toArray());
        }

        // Set the model for the JTable
        table.setModel(model);

        // Create a JScrollPane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }

    public void showOrHideCRUDOptions(boolean show){

        for (JRadioButton but: lstRadioButton
                 ) {
                but.setEnabled(show);
        }
        for (JButton button: lstCRUD_opsButtons
        ) {
            button.setEnabled(show);
        }

        runQueryButton.setEnabled(!show);
    }
}
