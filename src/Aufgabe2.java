import BinaryTrees.BinaryTree;
import BinaryTrees.Info;

/**
 * Hochschule Harz Fachbereich Automatisierung und Informatik Prof. Dr. Bernhard
 * Zimmermann
 * 
 * LV "Algorithmen" WiSe 2019
 *
 * Problem: Paketverwaltung mit sortierten binären Bäumen
 * 
 * @author Wittich, Lindemann
 * @version 1.0
 * 
 **/
public class Aufgabe2 extends Environment {

  /*
   * Mögliche Arbeitsschritte
   */
  private static final int PAKETNAMEN_AUSGEBEN = 1;
  private static final int PAKETE_VERTEILEN = 2;
  private static final int PAKETVERTEILUNG_AUSGEBEN = 3;

  // Feld mit Lkws (Fuhrpark)
  private Lkw[] fuhrpark;

  // Baum mit allen Paketen
  private BinaryTree paketDB;

  // Aktueller Arbeitsschritt
  private int aktuellerArbeitssschritt;

  void mainProgram(String[] args) {

    System.out.println("Paketverwaltung");
    System.out.println("===============");
    System.out.println();

    lkwEinlesen();
    paketeEinlesen();

    // Alle Paketnamen ausgeben
    System.out.println("Namen der Pakete, aufsteigend sortiert");
    System.out.println("--------------------------------------");
    aktuellerArbeitssschritt = PAKETNAMEN_AUSGEBEN;
    paketDB.traverseIn();
    System.out.println();

    paketeVerteilen();
    paketeverteilungAusgeben();

  }

  private void lkwEinlesen() {
    // Fuhrpark erstellen (Anzahl der Lkws einlesen und Array mit dieser Anzahl
    // an Feldern initialisieren)
    fuhrpark = new Lkw[stdin.readInt()];

    // Für jedes Element im Feld einen Lkw erzeugen und maximales Ladevolumen
    // einlesen
    for (int i = 0; i < fuhrpark.length; i++) {
      fuhrpark[i] = new Lkw(stdin.readInt());
    }
  }

  private void paketeEinlesen() {
    paketDB = new BinaryTree();

    // Anzahl der einzulesenden Pakete lesen
    int anzahlPakete = stdin.readInt();
    for (int i = 0; i < anzahlPakete; i++) {

      // char-Feld soll Namen des Paketes beinhalten
      // Größe des Feldes einlesen
      char[] paketname = new char[stdin.readInt()];
      for (int j = 0; j < paketname.length; j++) {
        paketname[j] = stdin.readChar();
      }

      // Paketvolumen einlesen
      int paketvolumen = stdin.readInt();

      // Paket mit Paketnamen und Paketvolumen erzeugen
      Paket paket = new Paket(new String(paketname), paketvolumen);

      // Paket in PaketInfo und diese Info in die PaketDB (BinaryTree) einfügen
      paketDB.insertInfo(new PaketInfo(paket));
    }

  }

  private void paketeVerteilen() {
    aktuellerArbeitssschritt = PAKETE_VERTEILEN;

    // Pakete verteilen
    paketDB.traverseIn();
  }

  private void paketeverteilungAusgeben() {
    aktuellerArbeitssschritt = PAKETVERTEILUNG_AUSGEBEN;

    // Paketverteilung ausgeben
    System.out.println("Paketverteilung");
    System.out.println("---------------");
    paketDB.traverseIn();
    System.out.println();

    // Alle Lkws mit Beladung ausgeben
    for (Lkw lkw : fuhrpark) {
      lkw.ausgeben();
      System.out.println();
    }
  }

  class PaketInfo extends Info {

    public PaketInfo(Object val) {
      super(val);
    }

    @Override
    public boolean equal(Object p) {
      return ((Paket) val).getName()
          .compareTo(((Paket) p).getName()) == 0;
    }

    @Override
    public boolean greater(Object p) {
      return ((Paket) val).getName()
          .compareTo(((Paket) p).getName()) > 0;
    }

    @Override
    public boolean less(Object p) {
      return ((Paket) val).getName()
          .compareTo(((Paket) p).getName()) < 0;
    }

    @Override
    public void process() {

      // Prüfen, ob der Paketname dieses Paketes ausgegeben werden soll
      if (PAKETNAMEN_AUSGEBEN == aktuellerArbeitssschritt) {
        System.out.println(((Paket) val).getName());

        // Prüfen, ob Pakete verteilt werden sollen
      } else if (PAKETE_VERTEILEN == aktuellerArbeitssschritt) {

        // Ein Lkw zum Beladen dieses Paketes wird gesucht
        for (Lkw lkw : fuhrpark) {
          // Prüfen, ob Paket beladen werden konnte
          if (lkw.ladePaket((Paket) val)) {
            // Paket erfolgreich beladen, Suche abbrechen
            ((Paket) val).setLkw(lkw);
            break;
          }
        }

        // Prüfen, ob die Paketverteilung ausgegeben werden soll
      } else if (PAKETVERTEILUNG_AUSGEBEN == aktuellerArbeitssschritt) {

        // Ist dieses Paket einem LKW zugeordnet?
        if (((Paket) val).getLkw() == null) {
          // Dieses Paket ist keinem LKW zugeordnet, ausgeben
          System.out.println("Das Paket ");
          System.out.println("   \"" + val + "\"");
          System.out.println("   passt aktuell in keinen LKW!");
        }
      }
    }
  }

  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        System.setIn(new java.io.FileInputStream(args[0]));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("*** Eingabedatei nicht gefunden ***");
        System.exit(1);
      }
    }
    if (args.length > 1) {
      try {
        System.setOut(new java.io.PrintStream(
            new java.io.FileOutputStream(args[1])));
      } catch (java.io.FileNotFoundException e) {
      }
    }
    new Aufgabe2().mainProgram(args);
  }
}
