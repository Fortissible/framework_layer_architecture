public class TemplatePattern {
  public static void main(String[] args){
    DatabaseDataParser dbParser = new DatabaseDataParser();
    CSVDataParser csvParser = new CSVDataParser();

    // Hollywood principle adalah pripsip dimana child/subclass hanya memanggil wrapper function milik parent, dan tidak
    // perlu mengetahui proses dari primitive operation wrapper function tersebut.
    System.out.println("---------------------------------");
    csvParser.parseDataAndGenerateOutput(); // Hollywood Principle
    System.out.println("---------------------------------");
    System.out.println("---------------------------------");
    System.out.println("---------------------------------");
    dbParser.parseDataAndGenerateOutput(); // Hollywood Principle
  }
}
