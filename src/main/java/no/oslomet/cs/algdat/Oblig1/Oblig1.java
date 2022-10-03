package no.oslomet.cs.algdat.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {
    public static final int NUMBERS_OF_WORDS_IN_ALFABET = 29;

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
            final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "Arrayet er tom og en tom Array/tabell har ingen verdi" +
                    " og dermed kan ikke største tallet finnes.";
            int storste_tall = 0;

            if (a.length < 1){                      //om det er mindre en element eller null så må sendes varsel
                throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
            }else if(a.length == 1){                //ellers om det er kun en eksisterende elemenet, blir vel
                storste_tall = a[0];                // det tallet som største
            }
            for (int i = 1; i < a.length; i++){
                if(a[i] < a[i-1]){
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                }
                storste_tall = a[i];
            }
            return storste_tall;
        }
    public static int ombyttinger(int[] a) {
        // en teller funksjon som teller hvor mange ganger verdier har blitt
        int teller = 0;                      // sammenlignet for å komme til den største tallet

        for (int i = 1; i < a.length; i++){
            if (a[i] < a[i-1]){
                teller++;

                int temp = a[i];
                a[i] = a[i-1];
                a[i-1] = temp;
            }
        }
        return teller;
    }
    //Når blir det flest ombyttinger?
//    Det blir flest ombyttninger om arrayet er sortert "feil vei". DVS fra minst til størst.
// Når blir det færrest?
//    når listen er sortert "riktig vei", DVS fra størst til minst

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        final String ILLIGALSTATE_EXCEPTION_MESSAGE = "Arrayet må være sortert stigende";

        int teller = 1;
        if (a.length == 0){   //tilfellet der vi har 0 element
            teller = 0;
        }

        for(int i = 1; i < a.length; i++){
            if (a[i] < a[i-1]){
                throw new IllegalStateException(ILLIGALSTATE_EXCEPTION_MESSAGE);
            }
            if ( a[i-1] != a[i]){            //for å ikke ta med duplikater
                teller++;
            }
        }
        return teller;
    }

    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {

        int teller = 0;
        if (a.length == 0){
            teller = 0;
        }

        for (int i = 0; i < a.length; i++){

            boolean Dup = false;       //bruke boolean for å unngå duplikater og resten av oppgaven er som opp2
            for (int j = 0; j < i; j++){
                if (a[j] == a[i]){
                    Dup = true;
                    break;
                }
            }
            if (!Dup){
                teller++;
            }
        }

        return teller;
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void delsortering(int[] a) {

        int lengde = a.length; //definerer variabler
        int odd_tall = 0;
        int tmp = 0;
        int o = 0;

        quickSort(a, 0, lengde-1);         //innskrevet array, blir sortert

        for(int i = 0; i < lengde; i++){       //Sjekker gjennom hele arrayet med en for-løkke


            if (a[i]%2 == 0){               //satt en if løkke for å Sjekke om tallet er partall
                if (a[i] == 0){
                    o++;
                }
            }
            else {                  //om det ikke er blir dem odde tall og settes i posisjon
                tmp = a[i];
                a[i] = a[odd_tall];
                a[odd_tall] = tmp;
                odd_tall++;
            }
        }

        quickSort(a, lengde-odd_tall-o, lengde-1); //partallene sorteres


    }

    //en metode for quick-sort med venstre og høyre side for å ha odde og par tallene for sin side
    public static void quickSort(int[]a, int v, int h){

        if (v < h) {              //om venstre er større enn høyre så ...
            int partitionIndex = partition(a, v, h);

            quickSort(a, v, partitionIndex - 1);  //quicksort, indekser, riktig posisjon
            quickSort(a, partitionIndex + 1, h);
        }
    }

    //Deler og returnerer delingsindeksen
    public static int partition(int[] a, int begin, int end){

        int pivot = a[end]; //med pivoten deles listen vår i to

        int i = (begin-1); // i blir da start/bak posisjonen

        for (int j = begin; j < end; j++) { //en for løkke med begin og end

            if (a[j] < pivot) {    //arrayet j sjekkes mot pivoten
                i++;              // om det er noe så partiationIndexen +1
                int swapTmp = a[i]; //så går tallene som vi ønsker på riktig rekkefølge
                a[i] = a[j];
                a[j] = swapTmp;
            }
        }

        int swapTmp = a[i+1]; //så byttes posisjon til ledd
        a[i+1] = a[end];
        a[end] = swapTmp;

        return i+1; //indeksen returneres
    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        if (a.length == 1 || a.length == 0){
            System.out.println(Arrays.toString(a));
        }

        int c = 1;
        int n = a.length;  if (n < 2){
            return;
        }
        if ((c %= n) < 0){
            c += n;
        }

        char[] b = Arrays.copyOfRange(a, n - c, n);
        for (int i = n - 1; i >= c; i--){
            a[i] = a[i - c];
        }
        System.arraycopy(b, 0, a, 0, c);

        System.out.println(Arrays.toString(a));
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        int n = a.length;
        final int maks = 100;
        final int min = -100;

        if (k > maks || k < min){ //om k  er større en maks og mindre enn k så returner
            return;
        }
        if (n < 2){  //til arrayet har  2 elementer, blir returnert ingenting. siden det ikke gjør forskjell
            return;
        }
        if ((k %= n) < 0){
            k += n;
        }

        char[] b = Arrays.copyOfRange(a, n - k, n);
        for (int i = n - 1; i >= k; i--){
            a[i] = a[i - k];
        }
        System.arraycopy(b, 0, a, 0, k);
        System.out.println(Arrays.toString(a));
    }


    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
            StringBuilder a = new StringBuilder();
            String b = s + t;

            for (int i = 0; i < b.length(); i++) { //går gjennom og setter ting i new StringBuilder
                if (i < s.length()) {
                    a.append(s.charAt(i));
                }
                if (i < t.length()) {
                    a.append(t.charAt(i));
                }
            }

            return a.toString();  // returnerer tostring a og t som  ble satt i StringBuilder
    }

    /// 7b)
    public static String flett(String... s) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new UnsupportedOperationException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new UnsupportedOperationException();
    }

}  // Oblig1
