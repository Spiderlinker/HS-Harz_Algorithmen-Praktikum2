import Lists.List;

/**
 * Hochschule Harz Fachbereich Automatisierung und Informatik Prof. Dr. Bernhard
 * Zimmermann
 * 
 * LV "Algorithmen" WiSe 2019
 *
 * @author Wittich, Lindemann
 * @version 1.0
 * 
 **/
public class Lkw {

  /** Variable fuer die LkwNr-Zuweisung */
  private static int lkwNrCount = 5;

  /** Eindeutige Nummer des Lkws */
  private final int lkwNr = lkwNrCount += 5;

  /** Maximales Ladevolumen */
  private int maxLadeVolumen;

  /** Aktuelles Ladevolumen dieses Lkws */
  private int aktuellesLadevolumen;

  /** Liste mit beladenen Paketen */
  private List ladeListe = new List();

  public Lkw(int maxLadeVolumen) {
    this.maxLadeVolumen = maxLadeVolumen;
    aktuellesLadevolumen = 0;
  }

  /**
   * 
   * @param paket
   * @return
   */
  public boolean ladePaket(Paket paket) {
    // neues mögliche Ladevolumen berechnen
    int neuesVolumen = aktuellesLadevolumen + paket.getVolumen();

    // Prüfen, ob das neue Ladevolumen das maximale Ladevolumen überschreitet
    if (neuesVolumen > maxLadeVolumen) {
      // Das neue Ladevolumen überschreitet das maximale Ladevolumen,
      // das Paket kann nicht verladen werden
      return false;
    }

    // Neues Ladevolumen setzen
    aktuellesLadevolumen = neuesVolumen;

    // Den Cursor auf das letzte Element der Liste setzen
    if (ladeListe.getValNext() != null) {
      ladeListe.next();
    }

    // Das Paket in die Liste einfügen
    ladeListe.insertAfter(paket);
    return true;
  }

  public int getLkwNr() {
    return this.lkwNr;
  }

  /**
   * Gibt den Lkw mit seiner Nummer, seiner Ladung, dem aktuellen und maximalen
   * Ladevolumen aus
   */
  public void ausgeben() {
    System.out.println("LKW-" + lkwNr + ":");
    ladeListe.processListnodes();
    System.out.println("Ladevolumen: " + aktuellesLadevolumen
        + "; max. Ladevolumen: " + maxLadeVolumen);
  }

}
