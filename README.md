# Aho-Corasick

Aho-Corasick adalah algoritma string matching yang dapat digunakan untuk mencari banyak pattern dalam suatu text. Pattern-pattern tersebut disebut kamus. Algoritma ini mempunyai dasar _finite state automaton_ yang berdasarkan struktur data trie yang memiliki kompleksitas waktu O(n + m + z) dengan n adalah panjang text, m adalah jumlah pattern, dan z adalah jumlah kemunculan pattern dalam text. 

## Installing Dependencies 🔨

1. Install [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Install [JavaFX](https://openjfx.io/).
3. Install [Maven](https://maven.apache.org/download.cgi).
4. Buka folder root di terminal
5. Jalankan perintah berikut:
    ```bash
    mvn clean compile assembly:single
    ```
6. Jika berhasil, maka akan muncul folder target
7. Pastikan JavaFX sudah terinstall di komputer Anda
8. Jalankan program dengan perintah berikut:
    ```bash
    java --module-path $FX-PATH --add-modules javafx.controls,javafx.fxml -jar .\target\Aho_Corasick-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
    dimana $FX-PATH adalah path menuju folder lib JavaFX
9. Program akan berjalan

## Pembuat
| 13522153 | Muhammad Fauzan Azhim | [LinkedIn](https://www.linkedin.com/in/fauzanazhim/) |
