/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.player;

import communication.CommunicationC;
import util.DateFormatter;
import constants.Operation;
import domain.City;
import domain.Club;
import domain.domainEnum.Gender;
import domain.Player;
import domain.Position;
import form.FormMode;
import java.awt.Window;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import logic.ControlerC;
import session.Session;
import transfer.ClientRequest;

/**
 *
 * @author TANJA-PC
 */
public class PanelPlayer extends javax.swing.JPanel {

    JDialog ancestor;
    FormMode mode;

    /**
     * Creates new form PanelPlayer
     *
     * @param mode
     * @param ancestor
     */
    public PanelPlayer(FormMode mode, JDialog ancestor) {
        initComponents();
        this.mode = mode;
        this.ancestor = ancestor;
        ControlerC.getInstance().getFormMain().setPanelPlayer(this);

        requestForCMB();
        populateCMBGender();
        setCityPanelFalse();
        adjustForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClub = new javax.swing.JLabel();
        cmbClub = new javax.swing.JComboBox<>();
        lblPlayerRegNo = new javax.swing.JLabel();
        txtPlayerRegNo = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblDateOfBirth = new javax.swing.JLabel();
        lblAdress = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDateOfBirth = new javax.swing.JTextField();
        txtAdress = new javax.swing.JTextField();
        lblCity = new javax.swing.JLabel();
        cmbCity = new javax.swing.JComboBox<>();
        lblPosition = new javax.swing.JLabel();
        cmbPosition = new javax.swing.JComboBox<>();
        lblJerseyNumber = new javax.swing.JLabel();
        txtJerseyNumber = new javax.swing.JTextField();
        btnShowCityPanel = new javax.swing.JButton();
        cityPanel = new javax.swing.JPanel();
        btnCityDetails = new javax.swing.JLabel();
        lblZipCode = new javax.swing.JLabel();
        lblCityName = new javax.swing.JLabel();
        txtZipCode = new javax.swing.JTextField();
        txtCityName = new javax.swing.JTextField();
        btnAddNewCity = new javax.swing.JButton();
        btnSavePlayer = new javax.swing.JButton();
        btnHideCityPanel = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEditPlayer = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lblGender = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        btnRemove = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(626, 550));

        lblClub.setText("Klub");

        cmbClub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblPlayerRegNo.setText("Registarski broj igra??a:");

        lblName.setText("Ime i prezime:");

        lblDateOfBirth.setText("Datum ro??enja (dd.MM.yyyy):");

        lblAdress.setText("Adresa:");

        lblCity.setText("Grad:");

        cmbCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblPosition.setText("Pozicija:");

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblJerseyNumber.setText("Broj dresa:");

        btnShowCityPanel.setText("* Dodaj grad");
        btnShowCityPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCityPanelActionPerformed(evt);
            }
        });

        cityPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cityPanel.setEnabled(false);

        btnCityDetails.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCityDetails.setText("Podaci o gradu");

        lblZipCode.setText("Po??tanski broj:");

        lblCityName.setText("Naziv:");

        btnAddNewCity.setText("Dodaj grad");
        btnAddNewCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewCityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cityPanelLayout = new javax.swing.GroupLayout(cityPanel);
        cityPanel.setLayout(cityPanelLayout);
        cityPanelLayout.setHorizontalGroup(
            cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cityPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cityPanelLayout.createSequentialGroup()
                        .addGroup(cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblZipCode, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCityName, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(txtZipCode)))
                    .addComponent(btnCityDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnAddNewCity)
                .addContainerGap())
        );
        cityPanelLayout.setVerticalGroup(
            cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCityDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblZipCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCityName)
                    .addComponent(btnAddNewCity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnSavePlayer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSavePlayer.setText("Registruj igra??a");
        btnSavePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePlayerActionPerformed(evt);
            }
        });

        btnHideCityPanel.setText("Sakrij");
        btnHideCityPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideCityPanelActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancel.setText("Odustani");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnEditPlayer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEditPlayer.setText("Izmeni");
        btnEditPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPlayerActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUpdate.setText("A??uriraj");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblGender.setText("Pol:");

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemove.setText("Obri??i igra??a");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblJerseyNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtJerseyNumber)
                                .addComponent(cmbPosition, 0, 396, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDateOfBirth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnHideCityPanel)
                                        .addGap(2, 2, 2)
                                        .addComponent(btnShowCityPanel))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbCity, javax.swing.GroupLayout.Alignment.LEADING, 0, 396, Short.MAX_VALUE)
                                        .addComponent(txtAdress, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addComponent(cityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnRemove)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUpdate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditPlayer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSavePlayer)))
                    .addComponent(btnCancel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPlayerRegNo, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                    .addComponent(lblClub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPlayerRegNo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbGender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbClub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClub)
                    .addComponent(cmbClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayerRegNo)
                    .addComponent(txtPlayerRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDateOfBirth)
                    .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress)
                    .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCity)
                    .addComponent(cmbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowCityPanel)
                    .addComponent(btnHideCityPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosition)
                    .addComponent(cmbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJerseyNumber)
                    .addComponent(txtJerseyNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSavePlayer)
                    .addComponent(btnCancel)
                    .addComponent(btnEditPlayer)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowCityPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCityPanelActionPerformed
        setCityPanelTrue();
    }//GEN-LAST:event_btnShowCityPanelActionPerformed

    private void btnHideCityPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideCityPanelActionPerformed
        setCityPanelFalse();
    }//GEN-LAST:event_btnHideCityPanelActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddNewCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewCityActionPerformed
        String zipCode = txtZipCode.getText();
        String cityName = txtCityName.getText();

        try {
            validationCity(zipCode, cityName);
            City city = new City(zipCode, cityName);
            ClientRequest cr = new ClientRequest();
            cr.setOperation(Operation.ADD_CITY);
            cr.setParameter(city);
            CommunicationC.getInstance().sendRequest(cr);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ancestor, ex.getMessage(), "Gre??ka",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddNewCityActionPerformed

    private void btnSavePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePlayerActionPerformed
        try {
            validation();
            Club club = (Club) cmbClub.getSelectedItem();
            String playerRegNo = txtPlayerRegNo.getText().trim();
            String name = txtName.getText();
            Gender gender = (Gender) cmbGender.getSelectedItem();
            String adress = txtAdress.getText();
            City city = (City) cmbCity.getSelectedItem();
            Position position = (Position) cmbPosition.getSelectedItem();
            int jerseyNumber = 0;
            if (!txtJerseyNumber.getText().trim().isEmpty()) {
                jerseyNumber = Integer.valueOf(txtJerseyNumber.getText().trim());
            }
            Date dateOfBirth = getDate();

            Player player = new Player(club, playerRegNo, name, gender, dateOfBirth,
                    adress, city, position, jerseyNumber);
            //System.out.println(player);

            ClientRequest cr = new ClientRequest();
            cr.setOperation(Operation.ADD_PLAYER);
            cr.setParameter(player);
            CommunicationC.getInstance().sendRequest(cr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSavePlayerActionPerformed

    private void btnEditPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPlayerActionPerformed
        this.mode = FormMode.EDIT;
        adjustForm();
    }//GEN-LAST:event_btnEditPlayerActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            validation();
            Club club = (Club) cmbClub.getSelectedItem();
            String playerRegNo = txtPlayerRegNo.getText().trim();
            String name = txtName.getText();
            Gender gender = (Gender) cmbGender.getSelectedItem();
            String adress = txtAdress.getText();
            City city = (City) cmbCity.getSelectedItem();
            Position position = (Position) cmbPosition.getSelectedItem();
            
            int jerseyNumber = 0;
            if (!txtJerseyNumber.getText().trim().isEmpty()) {
                jerseyNumber = Integer.valueOf(txtJerseyNumber.getText().trim());
            }
            Date dateOfBirth = getDate();

            Player player = new Player(club, playerRegNo, name, gender, dateOfBirth,
                    adress, city, position, jerseyNumber);
            Session.getInstance().getUseCaseParams().put("player", player);

            ClientRequest cr = new ClientRequest();
            cr.setOperation(Operation.UPDATE_PLAYER);
            cr.setParameter(player);
            CommunicationC.getInstance().sendRequest(cr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne mo??e da izmeni podatke o Igra??u." + ex.getMessage(), "Gre??ka!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        Player player = (Player) Session.getInstance().getUseCaseParams().get("player");

        ClientRequest cr = new ClientRequest();
        cr.setOperation(Operation.DELETE_PLAYER);
        cr.setParameter(player);
        //Session.getInstance().getUseCaseParams().put("remove player", "PanelSearchPlayer");
        CommunicationC.getInstance().sendRequest(cr);
    }//GEN-LAST:event_btnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewCity;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel btnCityDetails;
    private javax.swing.JButton btnEditPlayer;
    private javax.swing.JButton btnHideCityPanel;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSavePlayer;
    private javax.swing.JButton btnShowCityPanel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel cityPanel;
    private javax.swing.JComboBox<Object> cmbCity;
    private javax.swing.JComboBox<Object> cmbClub;
    private javax.swing.JComboBox<Object> cmbGender;
    private javax.swing.JComboBox<Object> cmbPosition;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblCityName;
    private javax.swing.JLabel lblClub;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblJerseyNumber;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPlayerRegNo;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblZipCode;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtCityName;
    private javax.swing.JTextField txtDateOfBirth;
    private javax.swing.JTextField txtJerseyNumber;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPlayerRegNo;
    private javax.swing.JTextField txtZipCode;
    // End of variables declaration//GEN-END:variables

    public JDialog getAncestor() {
        return ancestor;
    }

    public void setAncestor(JDialog ancestor) {
        this.ancestor = ancestor;
    }

    public FormMode getMode() {
        return mode;
    }

    public void setMode(FormMode mode) {
        this.mode = mode;
    }

    private void adjustForm() {
        switch (mode) {
            case NEW:
                resetForNewPlayer();

                cmbClub.setEnabled(true);
                txtPlayerRegNo.setEnabled(true);
                txtName.setEnabled(true);
                cmbGender.setEnabled(true);
                txtDateOfBirth.setEnabled(true);
                txtAdress.setEnabled(true);
                cmbCity.setEnabled(true);
                cmbPosition.setEnabled(true);
                txtJerseyNumber.setEnabled(true);

                btnShowCityPanel.setVisible(true);

                btnSavePlayer.setVisible(true);
                btnEditPlayer.setVisible(false);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
                btnCancel.setVisible(true);
                btnCancel.setText("Odustani");

                setTitle("Registrovanje novog igra??a");
                break;

            case EDIT:
                cmbClub.setEnabled(true);
                txtPlayerRegNo.setEnabled(false);
                txtName.setEnabled(true);
                cmbGender.setEnabled(true);
                txtDateOfBirth.setEnabled(true);
                txtAdress.setEnabled(true);
                cmbCity.setEnabled(true);
                cmbPosition.setEnabled(true);
                txtJerseyNumber.setEnabled(true);

                btnShowCityPanel.setVisible(true);

                btnSavePlayer.setVisible(false);
                btnEditPlayer.setVisible(false);
                btnUpdate.setVisible(true);
                btnRemove.setVisible(false);
                btnCancel.setVisible(true);
                btnCancel.setText("Odustani");

                setTitle("Izmena igra??a");
                break;

            case VIEW:
                cmbClub.setEnabled(false);
                txtPlayerRegNo.setEnabled(false);
                txtName.setEnabled(false);
                cmbGender.setEnabled(false);
                txtDateOfBirth.setEnabled(false);
                txtAdress.setEnabled(false);
                cmbCity.setEnabled(false);
                cmbPosition.setEnabled(false);
                txtJerseyNumber.setEnabled(false);

                btnShowCityPanel.setVisible(false);

                btnSavePlayer.setVisible(false);
                btnEditPlayer.setVisible(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(true);
                btnCancel.setVisible(true);
                btnCancel.setText("Iza??i");

                setTitle("Pregled igra??a");
                setPlayer();
                break;
        }
    }

    public void cancel() {
        switch (mode) {
            case EDIT:
                mode = FormMode.VIEW;
                adjustForm();
                break;

            case NEW:
            case VIEW:
                ControlerC.getInstance().setActiveWindow((Window) ancestor.getParent());
                ControlerC.getInstance().getFormMain().setPanelPlayer(null);
                ancestor.dispose();
                break;
        }
    }
    
    private void requestForCMB() {
        requestCities();
        requestClubs();
        requestPositions();
    }

    private void requestCities() {
        PanelSearchPlayer psp = ControlerC.getInstance().getFormMain().getPanelSearchPlayer();
        if (psp != null) {
            LinkedList<City> list = psp.getCitiesFromCMB();
            populateCMBCities(list);
        } else {
            ClientRequest cr = new ClientRequest();
            Session.getInstance().getUseCaseParams().put("request city", "PanelPlayer");
            cr.setOperation(Operation.GET_ALL_CITIES);
            CommunicationC.getInstance().sendRequest(cr);
        }
    }

    private void requestClubs() {
        PanelSearchPlayer psp = ControlerC.getInstance().getFormMain().getPanelSearchPlayer();
        if (psp != null) {
            LinkedList<Club> list = psp.getClubsFromCMB();
            populateCMBClubs(list);
        } else {
            ClientRequest cr = new ClientRequest();
            Session.getInstance().getUseCaseParams().put("request club", "PanelPlayer");
            cr.setOperation(Operation.GET_ALL_CLUBS);
            CommunicationC.getInstance().sendRequest(cr);
        }
    }

    private void requestPositions() {
        PanelSearchPlayer psp = ControlerC.getInstance().getFormMain().getPanelSearchPlayer();
        if (psp != null) {
            LinkedList<Position> list = psp.getPositionsFromCMB();
            populateCMBPosition(list);
        } else {
            ClientRequest cr = new ClientRequest();
            Session.getInstance().getUseCaseParams().put("request position", "PanelPlayer");
            cr.setOperation(Operation.GET_ALL_POSITIONS);
            CommunicationC.getInstance().sendRequest(cr);
        }
    }

    /*
    private void populateCMB() {
        //populateCMBClubs();
        populateCMBGender();
        //populateCMBCities();
        //populateCMBPosition();
    }*/
    public void populateCMBCities(LinkedList<City> cities) {
        cmbCity.removeAllItems();
        for (City c : cities) {
            cmbCity.addItem(c);
        }
    }

    public void populateCMBClubs(LinkedList<Club> clubs) {
        cmbClub.removeAllItems();
        for (Club c : clubs) {
            cmbClub.addItem(c);
        }
    }

    public void populateCMBPosition(LinkedList<Position> positions) {
        cmbPosition.removeAllItems();
        for (Position position : positions) {
            cmbPosition.addItem(position);
        }
    }

    private void populateCMBGender() {
        Gender[] values = Gender.values();
        cmbGender.removeAllItems();
        for (Gender g : values) {
            cmbGender.addItem(g);
        }
    }

    public void resetForNewPlayer() {
        txtPlayerRegNo.setText("");
        txtName.setText("");
        txtDateOfBirth.setText("");
        txtAdress.setText("");
        txtJerseyNumber.setText("");

        setCityPanelFalse();

    }

    private void setPlayer() {
        Player p = (Player) Session.getInstance().getUseCaseParams().get("player");
        System.out.println(p);

        txtPlayerRegNo.setText(p.getPlayerRegistrationNumber());
        txtName.setText(p.getName());
        txtDateOfBirth.setText(DateFormatter.dateToStringApp(p.getDateOfBirth()));
        txtAdress.setText(p.getPlayerAdress());
        txtJerseyNumber.setText(p.getJerseyNumber() + "");

        cmbCity.setSelectedItem(p.getCity());
        cmbGender.setSelectedItem(p.getGender());
        cmbClub.setSelectedItem(p.getClub());
        cmbPosition.setSelectedItem(p.getPosition());
    }

    private void setTitle(String title) {
        ancestor.setTitle(title);
    }

    private void setCityPanelFalse() {
        cityPanel.setVisible(false);
        btnHideCityPanel.setVisible(false);
        btnShowCityPanel.setVisible(true);

        txtCityName.setText("");
        txtZipCode.setText("");
    }

    private void setCityPanelTrue() {
        cityPanel.setVisible(true);
        btnHideCityPanel.setVisible(true);
        btnShowCityPanel.setVisible(false);
    }

    private void validationCity(String zipCode, String cityName) throws Exception {
        if (zipCode.isEmpty() || zipCode.equals("")) {
            throw new Exception("Nije unet po??tanski broj!");
        }
        if (cityName.isEmpty() || cityName.equals("")) {
            throw new Exception("Nije unet naziv grada!");
        }

        for (int i = 0; i < zipCode.length(); i++) {
            if (!Character.isDigit(zipCode.charAt(i))) {
                throw new Exception("Po??tanski broj mo??e sadr??ati samo brojeve!");
            }
        }

        if (zipCode.length() != 5) {
            throw new Exception("Po??tanski broj mora imati 5 cifara!");
        }

        for (int i = 0; i < cityName.length(); i++) {
            if (Character.isDigit(cityName.charAt(i))) {
                throw new Exception("Naziv grada ne sme sadr??ati brojeve!");
            }
        }

    }

    private void validation() throws Exception {
        validateCmb();
        validatePlayerRegNo();
        validateName();
        validateDateOfBirth();
        validateAdress();
        validateJerseyNumber();
    }

    private Date getDate() throws Exception {
        Date d1 = DateFormatter.stringToDateApp(txtDateOfBirth.getText());
        System.out.println("pre parsiranja: " + DateFormatter.dateToStringApp(d1));

        String dateS = DateFormatter.dateToStringDB(d1);
        Date d2 = DateFormatter.stringToDateDB(dateS);
        System.out.println("posle parsiranja: " + dateS);

        return d2;
    }

    private void validateCmb() throws Exception {
        LinkedList<Club> clubs = (LinkedList<Club>) ControlerC.getInstance().getFormMain().getModelClub().getClubs();
        if (clubs.isEmpty()) {
            String message = "Nijedan klub nije registrovan. "
                    + "Morate registrovati klub da biste registrovali igra??e!";
            throw new Exception(message);
        } else if (cmbClub.getSelectedIndex() == -1) {
            throw new Exception("Niste odabrali klub!");
        }

        if (cmbGender.getSelectedItem() == null) {
            throw new Exception("Niste odabrali pol!");
        }

        if (cmbCity.getSelectedItem() == null) {
            throw new Exception("Niste odabrali grad!");
        }

        if (cmbPosition.getSelectedIndex() == -1) {
            throw new Exception("Niste odabrali poziciju!");
        }

    }

    private void validatePlayerRegNo() throws Exception {
        if (txtPlayerRegNo.getText().isEmpty() || txtPlayerRegNo.getText().equals("")) {
            throw new Exception("Nije unet registarski broj igra??a!");
        } else if (txtPlayerRegNo.getText().length() != 5) {
            throw new Exception("Registarski broj igra??a mora imati 5 cifara!");
        } else {
            String regNo = txtPlayerRegNo.getText();
            for (int i = 0; i < regNo.length(); i++) {
                if (!Character.isDigit(regNo.charAt(i))) {
                    throw new Exception("Registarski broj igra??a mo??e sadr??ati samo brojeve!");
                }
            }
        }
    }

    private void validateJerseyNumber() throws Exception {
        //not required
        String jerseyNumber = txtJerseyNumber.getText().trim();
        if (!jerseyNumber.isEmpty()) {
            for (int i = 0; i < jerseyNumber.length(); i++) {
                if (!Character.isDigit(jerseyNumber.charAt(i))) {
                    throw new Exception("Broj dresa igra??a ne sme sadr??ati slova!");
                }
            }

            if (jerseyNumber.length() > 2) {
                throw new Exception("Broj dresa mora biti jednocifren ili dvocifren broj!");
            }
        }
    }

    private void validateDateOfBirth() throws Exception {
        //not required
        if (!txtDateOfBirth.getText().isEmpty()) {
            DateFormatter.stringToDateApp(txtDateOfBirth.getText());
        }
    }

    private void validateName() throws Exception {
        if (txtName.getText().isEmpty() || txtName.getText().equals("")) {
            throw new Exception("Nije uneto ime igra??a!");
        }
    }

    private void validateAdress() throws Exception {
        if (txtAdress.getText().isEmpty() || txtAdress.getText().equals("")) {
            throw new Exception("Nije uneta adresa igra??a!");
        }
    }

    
}
