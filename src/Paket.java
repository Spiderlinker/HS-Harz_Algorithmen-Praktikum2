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
public class Paket {

  /** Beliebig langer Name des Paketes */
  private String name;

  /** Ganzzahliges Attribut mit dem Volumen des Paketes */
  private int volumen;

  /** Zugeordneter Lkw */
  private Lkw paketLkw;

  public Paket(String name, int volumen) {
    this.name = name;
    this.volumen = volumen;
  }
  
  public void setLkw(Lkw lkw) {
    this.paketLkw = lkw;
  }

  public Lkw getLkw() {
    return paketLkw;
  }

  public int getVolumen() {
    return this.volumen;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return "Name: " + name + "; Volumen: " + volumen
        + (paketLkw == null ? "" : "; LkwNr: " + paketLkw.getLkwNr());
  }

}
