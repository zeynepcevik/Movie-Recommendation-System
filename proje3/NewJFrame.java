/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proje3;

import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeynep ARAS
 */
public class NewJFrame extends javax.swing.JFrame {

    public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//    public LinkedList<String> topKIds = new LinkedList<>();
//        public PriorityQueue<double[]> similarityHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
    //   private PriorityQueue<SimilarityResult> similarityHeap = new PriorityQueue<>(Comparator.reverseOrder());
    //----------------------------------------------------------------------------------
    public LinkedList<String[]> userData = new LinkedList<>();
    public LinkedList<String[][]> mainData = new LinkedList<>();
    public PriorityQueue<double[]> similarityHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
    public PriorityQueue<String> userIDHeap = new PriorityQueue<>(Collections.reverseOrder());
    public LinkedList<String> topKIds = new LinkedList<>();
    PriorityQueue<Integer> indexHeap = new PriorityQueue<>(Comparator.naturalOrder());

    public NewJFrame() {
        initComponents();

    }
//mainData ve userData listelerinin boş olup olmadığını kontrol ettik. 
//tüm satırlar için cosine benzerliği hesapladık ve en yüksek benzerlik değerlerini bulduk.
// Hesaplama işleminde cosine similarity formülünü kullandık.
// En yüksek benzerlik değerleri cosineSimilarities dizisine ekledik ve daha sonra 
//similarityHeap'e sıralı olarak ekledik. 
    public void calculateCosineSimilarity() {
        if (mainData.isEmpty() || userData.isEmpty()) {
            System.out.println("Lütfen önce kullanıcı verilerini ve ana veri setini yükleyin.");
            return;
        }

        int arraySize = mainData.size();
        int arrayColumnSize = mainData.get(0)[0].length;
        int userVectorSize = userData.get(0).length;

        if (arrayColumnSize != userVectorSize) {
            System.out.println("Dizi sütun boyutu ve kullanıcı vektör boyutu eşleşmiyor.");
            return;
        }

        double[] cosineSimilarities = new double[arraySize];

        for (int k = 0; k < arraySize; k++) {
            String[][] arrayMatrix = mainData.get(k);

            double maxSimilarity = 0.0;

            for (String[] userRow : userData) {
                double[] arrayVector = new double[arrayColumnSize];
                double[] userVector = new double[userVectorSize];

                for (int i = 0; i < arrayColumnSize; i++) {
                    try {
                        arrayVector[i] = Double.parseDouble(arrayMatrix[0][i]);
                        userVector[i] = Double.parseDouble(userRow[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Veri ayrıştırılırken hata oluştu.");
                        return;
                    }
                }

                double dotProduct = 0.0;
                double normUser = 0.0;
                double normArray = 0.0;

                for (int i = 0; i < userVectorSize; i++) {
                    dotProduct += userVector[i] * arrayVector[i];
                    normUser += userVector[i] * userVector[i];
                    normArray += arrayVector[i] * arrayVector[i];
                }

                double cosineSimilarity = dotProduct / (Math.sqrt(normUser) * Math.sqrt(normArray));
                maxSimilarity = Math.max(maxSimilarity, cosineSimilarity);
            }

            cosineSimilarities[k] = maxSimilarity;
            double[] similarityInfo = new double[]{maxSimilarity, k};
            similarityHeap.add(similarityInfo);
        }
        for (int i = 0; i < arraySize; i++) {
            System.out.println("Cosine Similarity for Array " + (i + 1) + ": " + cosineSimilarities[i]);

        }

    }

    // Rest of the code...
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        jButton4.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton4.setText("COSINE SIMILARITY");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jComboBox1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jComboBox1.setMaximumRowCount(10);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton1.setText(" GET RECOMMENDATION X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("TARGET USER :");

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel2.setText("X:");

        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel3.setText("K:");

        jButton2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton2.setText("TARGET_USER ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton3.setText("MAIN_DATA ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton5.setText("GET RECOMMENDATION K");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3))
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(448, 448, 448))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        calculateCosineSimilarity();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed


// İlk olarak, userData listesinden seçtiğimiz kullanıcının vektörünü filtreledik ve
// similarityHeap oluşturduk. Ardından, en yüksek benzerlik değeri similarityHeap'e ekledik.
//Daha sonra, topK adedi kadar en yüksek benzerlik değerine sahip kullanıcıların ID numaraları 
//topKIds listesine ekledik. jTextField5 içerisinden alınan k değerini kullanılarak rastgele indeksler 
//oluşturduk ve seçilen indeksler randomIndexes adlı bir StringBuilder nesnesine ekledik. 
//Son olarak görmek istediğimiz verileri jtextarea içerisine yazdırdık.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int topK = Integer.parseInt(jTextField3.getText()); // Get the number from the JTextField
        int selectedUserId = Integer.parseInt((String) jComboBox1.getSelectedItem());

        // Filter userData to get the selected user's vector
        String[] selectedUserVector = null;
        for (String[] userRow : userData) {
            if (Integer.parseInt(userRow[0]) == selectedUserId) {
                selectedUserVector = userRow;
                break;
            }
        }

        if (selectedUserVector == null) {
            System.out.println("Seçilen kullanıcının vektörü bulunamadı.");
            return;
        }

        PriorityQueue<double[]> similarityHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        int arraySize = mainData.size();
        int arrayColumnSize = mainData.get(0)[0].length;
        int userVectorSize = selectedUserVector.length;

        for (int k = 0; k < arraySize; k++) {
            String[][] arrayMatrix = mainData.get(k);
            double maxSimilarity = 0.0;

            for (String[] arrayRow : arrayMatrix) {
                double[] arrayVector = new double[arrayColumnSize];
                double[] userVector = new double[userVectorSize];

                for (int i = 0; i < arrayColumnSize; i++) {
                    try {
                        arrayVector[i] = Double.parseDouble(arrayRow[i]);
                        userVector[i] = Double.parseDouble(selectedUserVector[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Veri ayrıştırılırken hata oluştu.");
                        return;
                    }
                }

                double dotProduct = 0.0;
                double normUser = 0.0;
                double normArray = 0.0;

                for (int i = 0; i < userVectorSize; i++) {
                    dotProduct += userVector[i] * arrayVector[i];
                    normUser += userVector[i] * userVector[i];
                    normArray += arrayVector[i] * arrayVector[i];
                }

                double cosineSimilarity = dotProduct / (Math.sqrt(normUser) * Math.sqrt(normArray));
                maxSimilarity = Math.max(maxSimilarity, cosineSimilarity);
            }

            double[] similarityInfo = new double[]{maxSimilarity, k};
            similarityHeap.add(similarityInfo);
        }

        LinkedList<String> topKIds = new LinkedList<>();

        while (topK > 0 && !similarityHeap.isEmpty()) {
            double[] similarityInfo = similarityHeap.poll();
            double similarity = similarityInfo[0];
            int arrayId = (int) similarityInfo[1];
            String userId = ""; // Fill this with the corresponding user ID from main_data

            topKIds.add(String.valueOf(arrayId + 1));

            topK--;
        }

        // Print the topKIds to jTextArea1
        for (String id : topKIds) {
            jTextArea1.append("User ID: " + id + "\n");
        }

        int k = Integer.parseInt(jTextField5.getText()); // Get the value of k from JTextField5
        StringBuilder randomIndexes = new StringBuilder();

        for (String id : topKIds) {
            if (jTextArea1.getText().contains(id)) {
                String[][] arrayMatrix = mainData.get(Integer.parseInt(id) - 1);
                int columnCount = arrayMatrix[0].length;

                for (int i = 0; i < k; i++) {
                    List<Integer> indexes = new ArrayList<>();

                    for (int j = 0; j < columnCount; j++) {
                        if (arrayMatrix[0][j].equals("5")) {
                            indexes.add(j);
                        }
                    }

                    if (indexes.size() > 0) {
                        int randomIndex = indexes.get((int) (Math.random() * indexes.size()));
                        randomIndexes.append(randomIndex).append(", ");
                    }
                }
            }
        }

        jTextArea2.append(randomIndexes.toString()); // Print the random indexes to jTextArea2

    }//GEN-LAST:event_jButton1ActionPerformed
//"target_user.csv" dosyasını okuduk kullanıcı verilerini işledik. 
// Dosyayı satır satır okuduk ve her bir satır , karakterine göre parçalara ayırdık.
//jComboBox1'dan seçtiğimiz kullanıcı adı (selectedUser) aldık ve döngü içinde 
//bu kullanıcının verileri aradık. Eğer kullanıcının ID'si (userID) seçilen 
//kullanıcıyla eşleştiyse, kullanıcı verileri (parts) userData isimli LinkedList'e ekledik. 
//Ayrıca, userVector isimli double dizisi, kullanıcı verilerinin boyutuna göre oluşturduk.
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try ( BufferedReader reader = new BufferedReader(new FileReader("target_user.csv"))) {
            String line;
            String selectedUser = (String) jComboBox1.getSelectedItem();
            System.out.println("Selected User: " + selectedUser);
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                jComboBox1.addItem(parts[0]);
                String userID = parts[0];
                if (userID.equals(selectedUser)) {
                    System.out.println("User Data: " + line);
                    userData.add(parts); // Veriyi LinkedList'e ekle
                    double[] userVector = new double[userData.size()];

                 
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed
//"main_data.csv dosyasını okuduk ve dosya içindeki verileri iki boyutlu bir array içerisinde sakladık."
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        File file = new File("main_data.csv");

        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            // Skip the first line.
            bufferedReader.readLine();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                mainData.add(new String[][]{tokens}); // Add data to mainData as a 2D array
                System.out.println();
                System.out.println(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        int a = Integer.parseInt(jTextField3.getText());

    }//GEN-LAST:event_jTextField3ActionPerformed
//Kullanıcının seçtiği indekslere sahip filmlerin adlarını "movies.csv" 
//dosyasından okuduk ve bu film adlarını jTextArea3 bileşenine ekledik.
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String[] indexes = jTextArea2.getText().split(", ");
        for (String index : indexes) {
            indexHeap.add(Integer.parseInt(index));
        }
        try ( BufferedReader reader = new BufferedReader(new FileReader("movies.csv"))) {
            String[][] moviesData = new String[9019][3];

            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] movieData = line.split(",");
                moviesData[index][0] = movieData[0];
                moviesData[index][1] = movieData[1];
                moviesData[index][2] = movieData[2];
                index++;
            }

            for (int movieId : indexHeap) {
                if (movieId >= 0 && movieId < moviesData.length) {
                    String movieName = moviesData[movieId][1];
                    //System.out.println("Movie ID: " + movieId + ", Movie Name: " + movieName + "\n");

                    jTextArea3.append("Movie ID: " + movieId + ", Movie Name: " + movieName + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
