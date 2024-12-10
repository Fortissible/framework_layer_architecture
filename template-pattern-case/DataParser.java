abstract class DataParser { 
  // Untuk Memproses Input dari berbagai macam tipedata menjadi laporan dalam bentuk CSV
  // Proses Input X ---> CSV
  // X
  //   -> CSV
  //   -> DB

  // Hollywood Principle
  // Template Method (Arranger of primitive operations)
  public void parseDataAndGenerateOutput() {
    readData();       // Step-1 // Template Steps
    processData();    // Step-2 // Template Steps
    writeData();      // Step-3 // Concrete Steps
  }

  // abstract method that will be implemented by subclass/child
  abstract void readData();
  abstract void processData();

  // concrete method that subclass/child will be executed for the same process
  public void writeData(){
    System.out.println("Menulis data yang telah diproses ke CSV...");
  }
}