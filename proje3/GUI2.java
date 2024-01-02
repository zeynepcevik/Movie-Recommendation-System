/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proje3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import javax.swing.JComboBox;

/**
 *
 * @author Zeynep ARAS
 */
public class GUI2 extends javax.swing.JFrame {

    /**
     * Creates new form GUI2
     */
    public LinkedList<String[]> userData = new LinkedList<>();
    public LinkedList<double[]> array = new LinkedList<>();
    public List<String> movieNames;
    public LinkedList<String[][]> mainData = new LinkedList<>();
    public PriorityQueue<double[]> similarityHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
    PriorityQueue<Integer> indexHeap = new PriorityQueue<>(Comparator.naturalOrder());
    List<String[]> movieData = readMovieData("movies.csv");
    List<JComboBox<String>> comboBoxes = new LinkedList<>();

    public GUI2() {
        initComponents();

        List<String> movieNames = readMovieNames("movies.csv");

        List<String> randomMovieNames1 = getRandomMovieNames(movieNames, 10);
        for (String movieName : randomMovieNames1) {
            jComboBox1.addItem(movieName);

        }
        List<String> randomMovieNames2 = getRandomMovieNames(movieNames, 10);
        for (String movieName : randomMovieNames2) {

            jComboBox2.addItem(movieName);

        }
        List<String> randomMovieNames3 = getRandomMovieNames(movieNames, 10);
        for (String movieName : randomMovieNames3) {

            jComboBox3.addItem(movieName);

        }
        List<String> randomMovieNames4 = getRandomMovieNames(movieNames, 10);
        for (String movieName : randomMovieNames4) {

            jComboBox4.addItem(movieName);

        }
        List<String> randomMovieNames5 = getRandomMovieNames(movieNames, 10);
        for (String movieName : randomMovieNames5) {

            jComboBox5.addItem(movieName);

        }

    }
//Dosyadan film verilerini okuduk ve bir dizi string arrayi olarak sakladık. 
//her satırı okuduk ve virgülü ayraç olarak kullandık.    
//Bir diziyi oluşturduk ve bu diziyi movieData listesine ekledik. Eğer dosya okuma 
//işlemi sırasında bir IOException oluşursa, hata izini ekrana yazdırdık. 
//Son olarak, doldurduğumuz movieData listesini döndürdük.

    private static List<String[]> readMovieData(String filename) {
        List<String[]> movieData = new LinkedList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader("movies.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                movieData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieData;
    }

//Verilen bir film isimleri listesinden belirli bir sayıda rastgele film isimleri seçtik. 
//randomMovieNames adında boş bir liste oluşturduk.Ardından, belirtilen sayıda tekrarlanan 
//döngüde, random.nextInt(movieNames.size()) kullanılarak 0 ile movieNames'in boyutu arasında
// rastgele bir indeks oluşturduk. Oluşturduğumuz indeksteki film ismi movieNames listesinden 
// aldık ve randomMovieNames listesine ekledik. Son olarak, seçilen rastgele film 
//isimlerini içeren randomMovieNames listesi döndürdük.
    private List<String> getRandomMovieNames(List<String> movieNames, int count) {
        List<String> randomMovieNames = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(movieNames.size());
            String movieName = movieNames.get(index);
            randomMovieNames.add(movieName);
        }

        return randomMovieNames;
    }
//Bu kod, belirtilen bir dosyadan film isimlerini okur ve bir liste olarak döndürdük. 
//İşlem için boş bir liste olan movieNames oluşturduk. Ardından, belirtilen dosyayı 
//okumak için FileReader ve BufferedReader kullandık. Satır satır okuduk ve her bir 
//satır virgülle ayrılan bir diziye ayırdık. Eğer dizinin uzunluğu 3 veya daha fazlaysa, 
//film ismi olarak dizinin ikinci elemanı seçtik ve boşlukları temizledik movieNames 
//listesine ekledik. Dosya okuma işlemi sırasında oluşabilecek IOException hatası için 
//try-catch bloğu kullandık. Son olarak, film isimlerini içeren movieNames listesi döndürdük.

    private List<String> readMovieNames(String filename) {
        List<String> movieNames = new LinkedList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader("movies.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String movieName = data[1].trim();
                    movieNames.add(movieName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieNames;
    }
//Bir double türünde iki boyutlu bir dizi aldık ve diziyi ekrana yazdırdık. 
//İlk olarak, dizi boyutları olan rowSize ve columnSize değişkenleri tanımladıık. 
//Ardından, iki for döngüsü kullanarak dizinin her bir elemanını dolaştık. 
//Her bir eleman ekrana yazdırdık ve her satırın sonunda bir satır atlamak için println() kullandık.

    public void printArray(double[][] array) {
        int rowSize = array.length;
        int columnSize = array[0].length;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

//Bu kodda, cosine benzerliğini hesapladık. Öncelikle, mainData ve kullanıcı 
//array isimli dizilerin boş olup olmadığı kontrol ettik. Eğer boşlarsa hata mesajı yazdırdık 
//ve fonksiyon sonlandırdık.Daha sonra, her bir dizi için cosine benzerliği hesapladık. 
//Bunun için, döngüler kullanılarak dot product, normUser ve normArray değerleri hesapladık. 
//Bu değerleri kullanılarak cosine benzerlik değeri hesapladık ve maxSimilarity değişkenini güncelledik.
//Son olarak, hesaplanan cosine benzerlik değerlerini ekrana yazdırdık.
    public void calculateCosineSimilarity() {
        if (mainData.isEmpty() || array.isEmpty()) {
            System.out.println("Lütfen önce kullanıcı verilerini ve ana veri setini yükleyin.");
            return;
        }

        int arraySize = mainData.size();
        int arrayColumnSize = mainData.get(0)[0].length;
        int userVectorSize = array.get(0).length;

        if (arrayColumnSize != userVectorSize) {
            System.out.println("Dizi sütun boyutu ve kullanıcı vektör boyutu eşleşmiyor.");
            return;
        }

        double[] cosineSimilarities = new double[arraySize];

        for (int k = 0; k < arraySize; k++) {
            String[][] arrayMatrix = mainData.get(k);

            double maxSimilarity = 0.0;

            for (double[] userRow : array) {
                double[] arrayVector = new double[arrayColumnSize];
                double[] userVector = new double[userVectorSize];

                for (int i = 0; i < arrayColumnSize; i++) {
                    try {
                        arrayVector[i] = Double.parseDouble(arrayMatrix[0][i]);
                        userVector[i] = userRow[i];
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
        }

        for (int i = 0; i < arraySize; i++) {
            System.out.println("Cosine Similarity for Array " + (i + 1) + ": " + cosineSimilarities[i]);
        }

    }
//Bu kodda, en yakın kullanıcıları bulduk.Öncelikle, ana veri seti (mainData) ve kullanıcı 
//verileri (array) boş olup olmadığı kontrol ettik. Eğer boşlarsa hata mesajı yazdırdık ve 
//fonksiyonu sonlandırdık.Daha sonra, her bir dizi için cosine benzerliği hesapladık. 
//Bunun için, döngüler kullanılarak dot product, normUser ve normArray değerleri hesapladık. 
//Bu değerleri kullanılarak cosine benzerlik değeri hesapladık ve maxSimilarity değişkenini güncelledik.
//Cosine benzerlik değerleri PriorityQueue'e ekledik ve en yüksek benzerlik değerine sahip kullanıcıları tuttuk. 
//PriorityQueue'in boyutu numberOfUsers değerinden büyükse, en düşük benzerlik değerine sahip kullanıcıyı çıkardık.
//Son olarak, en yakın kullanıcıların ID numaraları JTextArea'ya ekledik.

    public void findClosestUsers(int numberOfUsers) {
        if (mainData.isEmpty() || array.isEmpty()) {
            System.out.println("Lütfen önce kullanıcı verilerini ve ana veri setini yükleyin.");
            return;
        }

        int arraySize = mainData.size();
        int arrayColumnSize = mainData.get(0)[0].length;
        int userVectorSize = array.get(0).length;

        if (arrayColumnSize != userVectorSize) {
            System.out.println("Dizi sütun boyutu ve kullanıcı vektör boyutu eşleşmiyor.");
            return;
        }

        double[] cosineSimilarities = new double[arraySize];

        for (int k = 0; k < arraySize; k++) {
            String[][] arrayMatrix = mainData.get(k);

            double maxSimilarity = 0.0;

            for (double[] userRow : array) {
                double[] arrayVector = new double[arrayColumnSize];
                double[] userVector = new double[userVectorSize];

                for (int i = 0; i < arrayColumnSize; i++) {
                    try {
                        arrayVector[i] = Double.parseDouble(arrayMatrix[0][i]);
                        userVector[i] = userRow[i];
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
        }

        PriorityQueue<Integer> topUsers = new PriorityQueue<>((a, b) -> Double.compare(cosineSimilarities[b], cosineSimilarities[a]));

        // Add the users to the PriorityQueue
        for (int i = 0; i < arraySize; i++) {
            topUsers.add(i);
            if (topUsers.size() > numberOfUsers) {
                topUsers.poll();
            }
        }

        // En yakın kullanıcıların id numaralarını ekrana yazdır
        System.out.println(numberOfUsers + " adet en yakın kullanıcılar:");
        for (int i = 0; i < numberOfUsers; i++) {
            int userId = topUsers.poll();
            // System.out.println("User ID: " + (userId + 1));
            jTextArea1.append("User ID :" + (userId + 1) + " \n");

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jComboBox4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jComboBox5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("X:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("K:");

        jTextField6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jButton3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton3.setText("VECTOR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton2.setText("MAIN DATA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton5.setText("COSINE SIMILARITY");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton6.setText("GET RECOMMENDATION X");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jButton7.setText("GET RECOMMENDATION K");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.LEADING, 0, 210, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField6))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(jTextField2)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(jTextField4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//İlk olarak, 9019 elemanlı bir dizi oluşturduk. Ardından, kullanıcının seçtiği filmler ve 
//bu filmlere atanmış değerleri döngüler aracılığıyla yaptık. Her bir filmi, movieData adındaki 
//bir veri dizisi üzerinde aradık ve ilgili film ID'si belirleyerek bu film ID'sine karşılık 
//gelen dizi indeksine kullanıcının atadığı değer atadık. İşlem tamamlandıktan sonra, 
//dizi içindeki veriler yazdırıdık.
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int columnSize = 9019;
        double[] arrayy = new double[columnSize];

        String selectedMovie1 = jComboBox1.getSelectedItem().toString();
        String a = jTextField1.getText();
        for (String[] movieDataArray : movieData) {
            if (movieDataArray[1].equals(selectedMovie1)) {
                String movieId = movieDataArray[0].trim();
                int mI = Integer.parseInt(movieId);
                arrayy[mI] = Double.parseDouble(a);
                System.out.println("Selected Movie: " + selectedMovie1 + ", Movie ID: " + movieId);
                break;
            }
        }

        String selectedMovie2 = jComboBox2.getSelectedItem().toString();
        String b = jTextField2.getText();
        for (String[] movieDataArray : movieData) {
            if (movieDataArray[1].equals(selectedMovie2)) {
                String movieId = movieDataArray[0].trim();
                int mI = Integer.parseInt(movieId);
                arrayy[mI] = Double.parseDouble(b);
                System.out.println("Selected Movie: " + selectedMovie2 + ", Movie ID: " + movieId);
                break;
            }
        }

        String selectedMovie3 = jComboBox3.getSelectedItem().toString();
        String c = jTextField3.getText();
        for (String[] movieDataArray : movieData) {
            if (movieDataArray[1].equals(selectedMovie3)) {
                String movieId = movieDataArray[0].trim();
                int mI = Integer.parseInt(movieId);
                arrayy[mI] = Double.parseDouble(c);
                System.out.println("Selected Movie: " + selectedMovie3 + ", Movie ID: " + movieId);
                break;
            }
        }

        String selectedMovie4 = jComboBox4.getSelectedItem().toString();
        String d = jTextField4.getText();
        for (String[] movieDataArray : movieData) {
            if (movieDataArray[1].equals(selectedMovie4)) {
                String movieId = movieDataArray[0].trim();
                int mI = Integer.parseInt(movieId);
                arrayy[mI] = Double.parseDouble(d);
                System.out.println("Selected Movie: " + selectedMovie4 + ", Movie ID: " + movieId);
                break;
            }
        }

        String selectedMovie5 = jComboBox5.getSelectedItem().toString();
        String e = jTextField5.getText();
        for (String[] movieDataArray : movieData) {
            if (movieDataArray[1].equals(selectedMovie5)) {
                String movieId = movieDataArray[0].trim();
                int mI = Integer.parseInt(movieId);
                arrayy[mI] = Double.parseDouble(e);
                System.out.println("Selected Movie: " + selectedMovie5 + ", Movie ID: " + movieId);
                break;
            }
        }

        array.add(arrayy);

        // Print the array
        for (double[] movieDataArray : array) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(movieDataArray[j] + " ");
            }
            System.out.println();
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed
//Burada, "main_data.csv" doyasını okuduk ve verileri "mainData" adlı bir 2D diziye ekledik.  
//İlk olarak, "main_data.csv"yı  bir dosya nesnesi olarak oluşturduk. Ardından, bir BufferedReader 
//nesnesi kullanılarak dosyayı okunmaya başladık. İlk satırı atladık çünkü ilk satır başlık 
//bilgilerini içeriyor. Daha sonra, dosyanın geri kalan satırlarını okuyarak virgülle ayrılmış 
//değerleri elde ettik. Bu değerler bir diziye ait ve "mainData" dizisine 2D dizi olarak ekledik 
//Ayrıca, her satırın içeriğini de yazdırdık.
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
//calculateCosineSimilarity() methodunu çağırdık
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        calculateCosineSimilarity();
    }//GEN-LAST:event_jButton5ActionPerformed
//jtextfield içinden aldığımız değeri integer bir değere atadık ve findClosestUsers methodunu çağırdık
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int a = Integer.parseInt(jTextField6.getText());
        findClosestUsers(a);
    }//GEN-LAST:event_jButton6ActionPerformed
//Öncelikle "jTextArea2" adlı metin alanından alınan veri boşluklara göre bölünerek 
//"indexes" adlı bir diziye atadık. Ardından,  eğer indeks boş değilse, integer türüne 
//dönüştürülerek "indexHeap" adlı bir kuyruğa ekledik. Daha sonra, "movies.csv" 
//dosyasını bir BufferedReader nesnesi kullanılarak okuduk. Bu dosya satır satır 
//okunarak virgülle ayrılmış değerleri elde ettik ve "moviesData" adlı bir 2D diziye atadık. 
//Son olarak, "indexHeap" kuyruğundaki her bir film kimliği için ; eğer film kimliği 
//"moviesData" dizisinin sınırları içindeyse, ilgili film adı alınarak "jTextArea2" metin 
//alanına yazdırdık. Bu kod parçası, kullanıcının seçtiği indekslere göre "movies.csv" 
//dosyasındaki film bilgilerini alıp ekrana yazdırdık.
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        String[] indexes = jTextArea2.getText().split(" ");
        for (String index : indexes) {
            if (!index.isEmpty()) {
                indexHeap.add(Integer.parseInt(index));
            }
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
                    jTextArea2.append("Movie ID: " + movieId + ", Movie Name: " + movieName + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
