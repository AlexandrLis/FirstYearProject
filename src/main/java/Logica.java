import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Logica extends JPanel{

    public boolean userWin = false;
    public boolean computerWin = false;

    char[][] nullField = new char[8][8];
    char[][] field = new char[8][8];
    private static boolean userMadeQueue = false;
    private static boolean userMadeBigQueue = false;
    private static boolean userMadeKill = false;
    private static boolean userMadeBigKill = false;
    private static boolean attackAgain = false;
    private static boolean attackBigAgain = false;
    private static final int cell_WINDOW_WIDTH = 83;
    private static final int cell_WINDOW_HEIGHT = 83;
    private static int takeFukX;
    private static int takeFukY;
    private static int takeFukBigX;
    private static int takeFukBigY;
    private static int newPositionTakeFukX;
    private static int newPositionTakeFukY;
    private static int newPositionTakeFukBigX;
    private static int newPositionTakeFukBigY;
    private static boolean computerMadeKill;
    private static boolean computerMadeBigKill;
    private static int positionWhoKillX;
    private static int positionWhoBigKillX;
    private static int positionWhoKillY;
    private static int positionWhoBigKillY;



    public Logica() {

        field = fillBox(nullField);
        printBoxInTerminal(field);

    }



    public void userClickMethod(int x, int y){

//        int one1 = 0;
//        int two2 = 0;
//        int fourth4 = 0;
//        int five5 = 0;
//
//      НИЧЬЯ
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
//                if(field[i][j] == '1'){
//                    one1++;
//                }
//                if(field[i][j] == '2' || field[i][j] == '3'){
//                    two2++;
//                }
//                if(field[i][j] == '4'){
//                    fourth4++;
//                }
//                if(field[i][j] == '5' || field[i][j] == '6'){
//                    five5++;
//                }
//            }
//        }
//        if(one1 == 0 && two2 == 0 && fourth4 == 1 && (five5 == 1 || five5 == 2)){
//            System.out.println("Ничья");
//            return;
//        }
//        if(one1 == 0 && two2 == 0 && (fourth4 == 1 || fourth4 == 2) && five5 == 1){
//            System.out.println("Ничья");
//            return;
//        }
//      НИЧЬЯ


//      БЛОК КОДА, ПРОВЕРЯЮЩИЙ ПОРАЖЕНИЕ ИГРОКА  - ВЕРХНЯЯ ЧАСТЬ
        List<Integer> userCanBigAttack = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '6' || field[i][j] == '5'){
                    userCanBigAttack.add(i);
                    userCanBigAttack.add(j);
                }
            }
        }

        int user = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '2' || field[i][j] == '3' || field[i][j] == '5' || field[i][j] == '6'){
                    user++;
                }
            }
        }
        if(user == 0){
            System.out.println("Победил Computer");
            computerWin = true;
            return;
        }

//        if(!userCanBigKill(userCanBigAttack) && !userCanKill() && !userCanGo()){
//            System.out.println("Победил Computer");
//            System.out.println("Шашки игрока не могут сходить или нанести удар");
//            return;
//        }
//      БЛОК КОДА, ПРОВЕРЯЮЩИЙ ПОРАЖЕНИЕ ИГРОКА  - НИЖНЯЯ ЧАСТЬ




        System.out.println("*******************userClickMethod***********************");
//      clickX, clickY - координаты куда кликнул user
        int clickX = x/cell_WINDOW_WIDTH;
        int clickY = y/cell_WINDOW_HEIGHT;

        System.out.println("attackAgain: " + attackAgain);
        System.out.println("attackBigAgain: " + attackBigAgain);
//      ЕСЛИ МОЖНО АТАКОВАТЬ ШАШКОЙ
        if(attackAgain) {
            System.out.println("ПОВТОРНАЯ АТАКА");
            userKill(clickX, clickY);
        }
//      ЕСЛИ МОЖНО АТАКОВАТЬ ДАМКОЙ
        else if(attackBigAgain){
            System.out.println("ПОВТОРНАЯ АТАКА ДАМКИ");
            userBigKill(clickX, clickY);
        }
//      ЕСЛИ АТАКОВАТЬ НЕЛЬЗЯ
        else {
            System.out.println("Ход игрока");
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if(field[i][j] == '9'){
                        field[i][j] = '0';
                    }
                }
            }
//          list - СОДЕРЖИТ 3 И 6 ЕСЛИ ОНИ ЕСТЬ
            List<Integer> list = hasThreeOrSix(field);
//          ПЕРВЫЙ КЛИК - ТО ЕСТЬ ПОДСВЕЧИВАЕМ 5 -> 6 ИЛИ 2 -> 3
            if(list.isEmpty()){
                System.out.println("Первый клик");
                if(field[clickX][clickY] == '2') {
                    field[clickX][clickY] = '3';
                }
                if(field[clickX][clickY] == '5') {
                    field[clickX][clickY] = '6';
                }
                printBoxInTerminal(field);
            }
            else {
//              ВТОРОЙ КЛИК - ТО ЕСТЬ ХОД ИЛИ АТАКА
                boolean checkCanKillSecondClick = userCanKill();
                List<Integer> listSix = new ArrayList<>();
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[0].length; j++) {
                        if(field[i][j] == '6'){
                            listSix.add(i);
                            listSix.add(j);
                        }
                    }
                }
                boolean checkCanKillSecondBigClick = userCanBigKill(listSix);
//              a, b - КООРДИНАТЫ ПОДСВЕЧЕННОЙ ШАШКИ (3 ИЛИ 6)
                int a = list.get(0);
                int b = list.get(1);
//              ЕСЛИ ВЫДЕЛЕНА ШАШКА, ТО ХОДИМ ЕЙ

                if(field[a][b] == '3'){
                    userQueue(clickX, clickY);
                }
                else{
                    userBigQueue(clickX, clickY, a, b);
                }
//              ЕСЛИ USER МОГ СРУБИТЬ НО НЕ СРУБИЛ, ТО ЗАБИРАЕМ ЭТУ ШАШКУ
                if(checkCanKillSecondClick && !userMadeKill && userMadeQueue){
                    if(field[takeFukX][takeFukY] == '2' || field[takeFukX][takeFukY] == '3') {
                        field[takeFukX][takeFukY] = '9';
                    }
                    if(field[takeFukX][takeFukY] == '0') {
                        field[takeFukX][takeFukY] = '9';
                        field[newPositionTakeFukX][newPositionTakeFukY] = '0';
                    }
                }
                if(checkCanKillSecondBigClick && !userMadeBigKill && userMadeBigQueue){
                    if(field[takeFukBigX][takeFukBigY] == '5' || field[takeFukBigX][takeFukBigY] == '6') {
                        field[takeFukBigX][takeFukBigY] = '9';
                    }
                    if(field[takeFukBigX][takeFukBigY] == '0') {
                        field[takeFukBigX][takeFukBigY] = '9';
                        field[newPositionTakeFukBigX][newPositionTakeFukBigY] = '0';
                    }
                }
                if(!attackAgain){
                    for (int i = 0; i < field.length; i++) {
                        for (int j = 0; j < field[0].length; j++) {
                            if(field[i][j] == '3' && (i != x || j != y)){
                                field[i][j] = '2';
                            }
                        }
                    }
                }
                if(!attackBigAgain){
                    for (int i = 0; i < field.length; i++) {
                        for (int j = 0; j < field[0].length; j++) {
                            if(field[i][j] == '6' && (i != x || j != y)){
                                field[i][j] = '5';
                            }
                        }
                    }
                }
                System.out.println("Еще ход игрока");
                printBoxInTerminal(field);

//              нужно сделать чтобы давал срубить пока не срубит всех кого может этой шашкой
                if(userMadeQueue || userMadeBigQueue){
                    System.out.println("computerQueue: ");
                    computerQueue();
                }
            }
        }

    }


//  country - ОПРЕДЕЛЯЕМ, В КАКИХ ИЗ ЧЕТЫРЕХ НАПРАВЛЕНИЙ МОЖНО АТАКОВАТЬ
    public List<Integer> country(int clickX, int clickY){
        System.out.println(clickX);
        System.out.println(clickY);
        System.out.println("Внутри country");
        List<Integer> country = new ArrayList<>();
        try{
            int buffer = 0;
            for (int i = clickX + 1, j = clickY + 1; i < field.length || j < field[0].length; i++, j++) {
                if(field[i][j] == '2' || field[i][j] == '5'){
                    buffer++;
                }
                if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                    buffer++;
                }
                if(buffer == 0 && (field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                    System.out.println("дамка может атаковать вправо вниз - 1");
                    country.add(1);
                }
            }
        }catch (Exception e){

        }
        try{
            int buffer = 0;
            for (int i = clickX - 1, j = clickY - 1; i >= 0 || j >= 0; i--, j--) {
                if(field[i][j] == '2' || field[i][j] == '5'){
                    buffer++;;
                }
                if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                    buffer++;;
                }
                if(buffer == 0 && (field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                    System.out.println("дамка может атаковать влево вверх - 2");
                    country.add(2);
                }
            }
        }catch (Exception e){

        }
        try{
            int buffer = 0;
            for (int i = clickX - 1, j = clickY + 1; i >= 0 || j < field[0].length; i--, j++) {
                if(field[i][j] == '2' || field[i][j] == '5'){
                    buffer++;
                }
                if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                    buffer++;
                }
                if(buffer == 0 && (field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                    System.out.println("дамка может атаковать вправо вверх - 3");
                    country.add(3);
                }
            }
        }catch (Exception e){

        }
        try{
            int buffer = 0;
            for (int i = clickX + 1, j = clickY - 1; i < field.length || j >= 0; i++, j--) {
                if(field[i][j] == '2' || field[i][j] == '5'){
                    buffer++;
                }
                if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                    buffer++;
                }
                if(buffer == 0 && (field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                    System.out.println("дамка может атаковать влево вниз - 4");
                    country.add(4);
                }
            }
        }catch (Exception e){

        }
        return country;
    }


    public List<Integer> checking(int x, int y, List<Integer> listCountry){
        System.out.println("полученные координаты '6' x и y -> x: " + x + ", y: " + y);
//      listCountry - ЦИФРАМИ УКАЗЫВАЕТ НАПРАВЛЕНИЕ ДЛЯ АТАКИ
//      list - содержит координаты всех клеток на диагоналях '6', куда можно сходить
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
//      НУЖНО КАДЖУЮ КООРДИНАТУ i, j ПРОВЕРИТЬ НА ВОЗМОЖНОСТЬ ДАЛЬЕЙШЕЙ АТАКИ
        for (int w = 0; w < listCountry.size(); w++) {
//          ПРОВЕРЯЕМ ПЕРВОЕ НАПРАВЛЕНИЕ ДЛЯ АТАКИ
            if(listCountry.get(w) == 1){
                try{
                    int count = 0;
                    for (int i = x + 1, j = y + 1; (i < field.length && j < field[0].length); i++, j++) {
                        if(count == 1 && field[i][j] == '0'){
                            list1.add(i);
                            list1.add(j);
                        }
                        if(field[i][j] != '0'){
                            count++;
                        }
                    }
                }catch (Exception e){

                }
            }
            if(listCountry.get(w) == 2){
                try{
                    int count = 0;
                    for (int i = x - 1, j = y - 1; (i >= 0 && j >= 0); i--, j--) {
                        if(count == 1 && field[i][j] == '0'){
                            list2.add(i);
                            list2.add(j);
                        }
                        if(field[i][j] != '0'){
                            count++;
                        }
                    }
                }catch (Exception e){

                }
            }
            if(listCountry.get(w) == 3){
                try{
                    int count = 0;
                    for (int i = x - 1, j = y + 1; (i >= 0 && j < field[0].length); i--, j++) {
                        if(count == 1 && field[i][j] == '0'){
                            list3.add(i);
                            list3.add(j);
                        }
                        if(field[i][j] != '0'){
                            count++;
                        }
                    }
                }catch (Exception e){

                }
            }
            if(listCountry.get(w) == 4){
                try{
                    int count = 0;
                    for (int i = x + 1, j = y - 1; (i < field.length && j >= 0); i++, j--) {
                        if(count == 1 && field[i][j] == '0'){
                            list4.add(i);
                            list4.add(j);
                        }
                        if(field[i][j] != '0'){
                            count++;
                        }

                    }
                }catch (Exception e){

                }
            }
        }

        System.out.println("Список координат клеток, куда может атаковать '6' без учета продолжения атаки: ");
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("list4: " + list4);

        return checkingEveryCoordinata(list1, list2, list3, list4);
    }


    public List<Integer> checkingEveryCoordinata(List<Integer> listSix1, List<Integer> listSix2, List<Integer> listSix3, List<Integer> listSix4){
//      list - содержит координаты клеток, на одну из которых должен встать игрок для продолжения атаки
        List<Integer> list = new ArrayList<>();
        List<Integer> One = new ArrayList<>(listSix1);
        List<Integer> Two = new ArrayList<>(listSix2);
        List<Integer> Three = new ArrayList<>(listSix3);
        List<Integer> Fourth = new ArrayList<>(listSix4);
        while (!listSix1.isEmpty()){
            int x = listSix1.remove(0);
            int y = listSix1.remove(0);
            try{
                int count = 0;
                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
        }
        while (!listSix2.isEmpty()){
            int x = listSix2.remove(0);
            int y = listSix2.remove(0);
            try{
                int count = 0;
                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
        }
        while (!listSix3.isEmpty()){
            int x = listSix3.remove(0);
            int y = listSix3.remove(0);
            try{
                int count = 0;
                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
        }
        while (!listSix4.isEmpty()){
            int x = listSix4.remove(0);
            int y = listSix4.remove(0);
            try{
                int count = 0;
                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
            try{
                int count = 0;
                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                        count++;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                        if(count == 0){
                            list.add(x);
                            list.add(y);
                        }
                    }
                }
            }catch (Exception e){

            }
        }

        List<Integer> resultList = new ArrayList<>(list);
        System.out.println("Проверяемый список: " + resultList);
//      ПРОЙДИСЬ ПО КАЖДОМУ ИЗ 4 СПИСКОВ И ЕСЛИ В СПИСКЕ НЕТ КООРДИНАТ ИЗ list, ТО ДОБАВЬ ИЗ ЭТОГО СПИСКА ВСЕ КООРДИНАТЫ В resultList
//      ЭТО ПОЗВОЛИТ РУБИТЬ И НЕ ОТДАВАТЬ ДАМКУ ЗА ФУК НА ТЕХ НАПРАВЛЕНИЯХ, ГДЕ НЕТ ВОЗМОЖНОСТИ АТАКОВАТЬ ПОВТОРНО
        int number = 0;
        int c = 0;
        while (c < One.size()){
            int a = One.get(c);
            int b = One.get(c+1);
            for (int i = 0; i < list.size(); i+=2) {
                if(a == list.get(i) && b == list.get(i + 1)){
                    number++;
                }
            }
            c+=2;
        }
        if(number == 0){
            resultList.addAll(One);
        }

        number = 0;
        c = 0;
        while (c < Two.size()){
            int a = Two.get(c);
            int b = Two.get(c+1);
            for (int i = 0; i < list.size(); i+=2) {
                if(a == list.get(i) && b == list.get(i + 1)){
                    number++;
                }
            }
            c+=2;
        }
        if(number == 0){
            resultList.addAll(Two);
        }
        number = 0;
        c = 0;
        while (c < Three.size()){
            int a = Three.get(c);
            int b = Three.get(c+1);
            for (int i = 0; i < list.size(); i+=2) {
                if(a == list.get(i) && b == list.get(i + 1)){
                    number++;
                }
            }
            c+=2;
        }
        if(number == 0){
            resultList.addAll(Three);
        }
        number = 0;
        c = 0;
        while (c < Fourth.size()){
            int a = Fourth.get(c);
            int b = Fourth.get(c+1);
            for (int i = 0; i < list.size(); i+=2) {
                if(a == list.get(i) && b == list.get(i + 1)){
                    number++;
                }
            }
            c+=2;
        }
        if(number == 0){
            resultList.addAll(Fourth);
        }

        System.out.println("resultList: " + resultList);

        return resultList;
    }



    public boolean userCanBigKill(List<Integer> listSix){
//      listSix - СОДЕРЖИТ КООРДИНАТЫ ДАМОК ИГРОКА X + Y;
        while (!listSix.isEmpty()){
            int x = listSix.remove(0);
            int y = listSix.remove(0);
            try{
                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] != '0'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j + 1] == '0'){
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }
                }
            }catch (Exception e){

            }
            try{
                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] != '0'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j - 1] == '0'){
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }
                }
            }catch (Exception e){

            }
            try{
                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] != '0'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i - 1][j + 1] == '0'){
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }
                }
            }catch (Exception e){

            }
            try{
                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
                    if(field[i][j] == '2' || field[i][j] == '5'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] != '0'){
                        return false;
                    }
                    if((field[i][j] == '1' || field[i][j] == '4') && field[i + 1][j - 1] == '0'){
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }
                }
            }catch (Exception e){

            }
        }
        return false;
    }

    public void userBigQueue(int x, int y, int a, int b){
        int one = 0;
        int two = 0;
        int three = 0;
        int fourth = 0;
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;
        int positionAttackOneX = 0;
        int positionAttackOneY = 0;
        int positionAttackTwoX = 0;
        int positionAttackTwoY = 0;
        int positionAttackThreeX = 0;
        int positionAttackThreeY = 0;
        int positionAttackFourthX = 0;
        int positionAttackFourthY = 0;
        char firstPosition = '8';
        char secondPosition = '8';
        char thirdPosition = '8';
        char fourthPosition = '8';
        System.out.println("*******************userBigQueue***********************");
//      ЕСЛИ ВЫДЕЛЕНА ДАМКА, ТО ХОДИМ ЕЙ
//      listCountry - ЦИФРАМИ УКАЗЫВАЕТ НАПРАВЛЕНИЯ ДЛЯ АТАКИ
        List<Integer> listCountry = country(a, b);
        System.out.println("listCountry: " + listCountry);

//      a, b - координаты выделенной шашки(в этом случае дамки)
//      coordinatesForAttack - координаты клеток, куда должен сходить игрок дамкой, что бы
//      дамку не забрали за фук
        List<Integer> coordinatesForAttack = checking(a, b, listCountry);
//                    НА ЭТИ КООРДИНАТЫ МОЖЕТ СТАНОВИТЬСЯ ДАМКА ПОСЛЕ АТАКИ ЧТО БЫ ЕЕ НЕ ЗАБРАЛИ ЗА ФУК
        System.out.println("Координаты для атаки дамкой, что бы эту дамку не забрали за фук: ");
        System.out.println("coordinatesForAttack: " + coordinatesForAttack);

//      ЕСЛИ ИГРОК КЛИКНУЛ НА КООРДИНАТЫ ИЗ coordinatesForAttack, ТО УВЕЛИЧИВАЕМ count
        int count = 0;
        if(!coordinatesForAttack.isEmpty()){
            for (int i = 0; i < coordinatesForAttack.size(); i+=2) {
                if(x == coordinatesForAttack.get(i) && y == coordinatesForAttack.get(i + 1)){
                    count++;
                }
            }
        }
        System.out.println("*******************userBigQueue***********************");
        for (int i = 1; i < field.length; i++) {
//          ХОДИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y + i] == '6'){
                    if(one == 0){
                        field[x][y] = '5';
                        field[x + i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x + i][y + i] != '0'){
//                      one увеличивается в том случае, если между координатой клика и '6' есть другие шашки
//                      в таком случае не получится сходить на выбранную клетку
                        one++;
                    }
                }
            }catch (Exception ignored){

            }
//          ХОДИТЬ ДАМКОЙ ВПРАВО ВНИЗ

            try{
                if(field[x][y] == '0' && field[x - i][y - i] == '6'){
                    if(two == 0){
                        field[x][y] = '5';
                        field[x - i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x - i][y - i] != '0'){
                        two++;
                    }
                }
            }catch (Exception e){

            }

//          ХОДИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y + i] == '6'){
                    if(three == 0){
                        field[x][y] = '5';
                        field[x - i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x - i][y + i] != '0'){
                        three++;
                    }
                }
            }catch (Exception e){

            }


//          ХОДИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y - i] == '6'){
                    if(fourth == 0){
                        field[x][y] = '5';
                        field[x + i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x + i][y - i] != '0'){
                        fourth++;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y + i] == '6'){
                    if(oneKill == 1){
                        field[x][y] = '5';
                        field[x + i][y + i] = '0';
                        firstPosition = field[positionAttackOneX][positionAttackOneY];
                        field[positionAttackOneX][positionAttackOneY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x + i][y + i] == '1') || (field[x + i][y + i] == '4')){
                        oneKill++;
//                      координаты атакуемой шашки компьютера
                        positionAttackOneX = x + i;
                        positionAttackOneY = y + i;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВПРАВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y - i] == '6'){
                    if(twoKill == 1){
                        field[x][y] = '5';
                        field[x - i][y - i] = '0';
                        secondPosition = field[positionAttackTwoX][positionAttackTwoY];
                        field[positionAttackTwoX][positionAttackTwoY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x - i][y - i] == '1') || (field[x - i][y - i] == '4')){
                        twoKill++;
                        positionAttackTwoX = x - i;
                        positionAttackTwoY = y - i;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y + i] == '6'){
                    if(threeKill == 1){
                        field[x][y] = '5';
                        field[x - i][y + i] = '0';
                        thirdPosition = field[positionAttackThreeX][positionAttackThreeY];
                        field[positionAttackThreeX][positionAttackThreeY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x - i][y + i] == '1') || (field[x - i][y + i] == '4')){
                        threeKill++;
                        positionAttackThreeX = x - i;
                        positionAttackThreeY = y + i;
                    }
                }
            }catch (Exception e){

            }


//          РУБИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y - i] == '6'){
                    if(fourthKill == 1){
                        field[x][y] = '5';
                        field[x + i][y - i] = '0';
                        fourthPosition = field[positionAttackFourthX][positionAttackFourthY];
                        field[positionAttackFourthX][positionAttackFourthY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x + i][y - i] == '1') || (field[x + i][y - i] == '4')){
                        fourthKill++;
                        positionAttackFourthX = x + i;
                        positionAttackFourthY = y - i;
                    }
                }
            }catch (Exception e){

            }
        }
        System.out.println("oneKill: " + oneKill);
        System.out.println("twoKill: " + twoKill);
        System.out.println("threeKill: " + threeKill);
        System.out.println("fourthKill: " + fourthKill);

        System.out.println("######coordinatesForAttack#####: " + coordinatesForAttack);
        if(!coordinatesForAttack.isEmpty() && count == 0 && userMadeBigQueue){
            if(oneKill != 0){
                System.out.println("firstPosition: " + firstPosition);
                field[positionAttackOneX][positionAttackOneY] = firstPosition;
            }
            if(twoKill != 0){
                System.out.println("secondPosition: " + secondPosition);
                field[positionAttackTwoX][positionAttackTwoY] = secondPosition;
            }
            if(threeKill != 0){
                System.out.println("thirdPosition: " + thirdPosition);
                field[positionAttackThreeX][positionAttackThreeY] = thirdPosition;
            }
            if(fourthKill != 0){
                System.out.println("fourthPosition: " + fourthPosition);
                field[positionAttackFourthX][positionAttackFourthY] = fourthPosition;
            }
            userMadeBigKill = false;
            field[a][b] = '0';
            field[x][y] = '9';
            computerQueue();
        }

//      ПРОВЕРЯЕМ МОЖЕТ ЛИ ДАМКА ПОСЛЕ ТЕКУЩЕЙ АТАКИ СРУБИТЬ ЕЩЕ ШАШКУ
        if(checkBigAttack(x, y) && userMadeBigKill && field[x][y] == '5'){
            field[x][y] = '6';
            System.out.println("Дамкой можно срубить еще шашку противника");
            userMadeBigQueue = false;
            attackBigAgain = true;
        }


    }


    public boolean checkBigAttack(int x, int y){
        printBoxInTerminal(field);
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;
        for (int i = 1; i < field.length; i++) {
//          РУБИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
            try{
                if(field[x][y] == '5' && field[x + i][y + i] == '0'){
                    if(oneKill == 1){
                        return true;
                    }
                }
                else {
                    if((field[x + i][y + i] == '1') || (field[x + i][y + i] == '4')){
                        oneKill++;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВПРАВО ВНИЗ
            try{
                if(field[x][y] == '5' && field[x - i][y - i] == '0'){
                    if(twoKill == 1){
                        return true;
                    }
                }
                else {
                    if((field[x - i][y - i] == '1') || (field[x - i][y - i] == '4')){
                        twoKill++;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
            try{
                if(field[x][y] == '5' && field[x - i][y + i] == '0'){
                    if(threeKill == 1){
                        return true;
                    }
                }
                else {
                    if((field[x - i][y + i] == '1') || (field[x - i][y + i] == '4')){
                        threeKill++;
                    }
                }
            }catch (Exception e){

            }


//          РУБИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
            try{
                if(field[x][y] == '5' && field[x + i][y - i] == '0'){
                    if(fourthKill == 1){
                        return true;
                    }
                }
                else {
                    if((field[x + i][y - i] == '1') || (field[x + i][y - i] == '4')){
                        fourthKill++;
                    }
                }
            }catch (Exception e){

            }
        }
        attackBigAgain = false;
        return false;
    }



    public void userBigKill(int x, int y){
        System.out.println("userBigKill");
        int one = 0;
        int two = 0;
        int three = 0;
        int fourth = 0;
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;
        int positionAttackOneX = 0;
        int positionAttackOneY = 0;
        int positionAttackTwoX = 0;
        int positionAttackTwoY = 0;
        int positionAttackThreeX = 0;
        int positionAttackThreeY = 0;
        int positionAttackFourthX = 0;
        int positionAttackFourthY = 0;

        int a = 0;
        int b = 0;
        try{
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if(field[i][j] == '6'){
                        a = i;
                        b = j;
                    }
                }
            }
        }catch (Exception e){

        }

        System.out.println("*******************userBigQueue***********************");
//      ЕСЛИ ВЫДЕЛЕНА ДАМКА, ТО ХОДИМ ЕЙ
//      listCountry - ЦИФРАМИ УКАЗЫВАЕТ НАПРАВЛЕНИЯ ДЛЯ АТАКИ
        List<Integer> listCountry = country(a, b);
        System.out.println("listCountry: " + listCountry);

//      a, b - координаты выделенной шашки(в этом случае дамки)
//      coordinatesForAttack - координаты клеток, куда должен сходить игрок дамкой, что бы
//      дамку не забрали за фук
        List<Integer> coordinatesForAttack = checking(a, b, listCountry);
//      НА ЭТИ КООРДИНАТЫ МОЖЕТ СТАНОВИТЬСЯ ДАМКА ПОСЛЕ АТАКИ ЧТО БЫ ЕЕ НЕ ЗАБРАЛИ ЗА ФУК
        System.out.println("Координаты для атаки дамкой, что бы эту дамку не забрали за фук: ");
        System.out.println("coordinatesForAttack: " + coordinatesForAttack);
//      НУЖНО ПРОВЕРИТЬ ЕСТЬ ЛИ С ЭТОМ СПИСКЕ clickX, clickY

//      СЮДА НУЖНО ПОСТАВИТЬ ПРОВЕРКУ НА НАЛИЧИЕ КООРДИНАТ ДЛЯ ПОСЛЕДУЮЩЕГО УДАРА ПОСЛЕ ТЕКУЩЕГО УДАРА

        int count = 0;
        for (int i = 0; i < coordinatesForAttack.size(); i+=2) {
            if(x == coordinatesForAttack.get(i) && y == coordinatesForAttack.get(i + 1)){
                count++;
            }
        }

        System.out.println("*******************userBigQueue***********************");
        for (int i = 1; i < field.length; i++) {
//          ХОДИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y + i] == '6'){
                    if(one == 0){
                        field[x][y] = '5';
                        field[x + i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x + i][y + i] != '0'){
                        one++;
                    }
                }
            }catch (Exception e){

            }
//          ХОДИТЬ ДАМКОЙ ВПРАВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y - i] == '6'){
                    if(two == 0){
                        field[x][y] = '5';
                        field[x - i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x - i][y - i] != '0'){
                        two++;
                    }
                }
            }catch (Exception e){

            }

//          ХОДИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y + i] == '6'){
                    if(three == 0){
                        field[x][y] = '5';
                        field[x - i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x - i][y + i] != '0'){
                        three++;
                    }
                }
            }catch (Exception e){

            }


//          ХОДИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y - i] == '6'){
                    if(fourth == 0){
                        field[x][y] = '5';
                        field[x + i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if(field[x + i][y - i] != '0'){
                        fourth++;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y + i] == '6'){
                    if(oneKill == 1){
                        field[x][y] = '5';
                        field[x + i][y + i] = '0';
                        field[positionAttackOneX][positionAttackOneY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x + i][y + i] == '1') || (field[x + i][y + i] == '4')){
                        oneKill++;
                        positionAttackOneX = x + i;
                        positionAttackOneY = y + i;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВПРАВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y - i] == '6'){
                    if(twoKill == 1){
                        field[x][y] = '5';
                        field[x - i][y - i] = '0';
                        field[positionAttackTwoX][positionAttackTwoY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x - i][y - i] == '1') || (field[x - i][y - i] == '4')){
                        twoKill++;
                        positionAttackTwoX = x - i;
                        positionAttackTwoY = y - i;
                    }
                }
            }catch (Exception e){

            }

//          РУБИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
            try{
                if(field[x][y] == '0' && field[x - i][y + i] == '6'){
                    if(threeKill == 1){
                        field[x][y] = '5';
                        field[x - i][y + i] = '0';
                        field[positionAttackThreeX][positionAttackThreeY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x - i][y + i] == '1') || (field[x - i][y + i] == '4')){
                        threeKill++;
                        positionAttackThreeX = x - i;
                        positionAttackThreeY = y + i;
                    }
                }
            }catch (Exception e){

            }


//          РУБИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
            try{
                if(field[x][y] == '0' && field[x + i][y - i] == '6'){
                    if(fourthKill == 1){
                        field[x][y] = '5';
                        field[x + i][y - i] = '0';
                        field[positionAttackFourthX][positionAttackFourthY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                }
                else {
                    if((field[x + i][y - i] == '1') || (field[x + i][y - i] == '4')){
                        fourthKill++;
                        positionAttackFourthX = x + i;
                        positionAttackFourthY = y - i;
                    }
                }
            }catch (Exception e){

            }
        }

        if(!coordinatesForAttack.isEmpty() && count == 0 && userMadeBigQueue){
            userMadeBigKill = false;
            field[a][b] = '0';
            field[x][y] = '9';
            computerQueue();
        }

//      НУЖНО ПРОВЕРИТЬ ДИАГОНАЛИ ШАШКИ ИГРОКА НА НАЛИЧИЕ ПОЗИЦИИ ДЛЯ ПОСЛЕДУЮЩЕЙ АТАКИ
        if(!userMadeBigKill && userMadeBigQueue){
            field[x][y] = '9';
        }


        if(checkBigAttack(x, y) && userMadeBigKill && field[x][y] == '5'){
            field[x][y] = '6';
            System.out.println("Дамкой можно срубить еще шашку противника");
            userMadeBigQueue = false;
            attackBigAgain = true;
        }
        else{
            userMadeBigQueue = true;
            attackBigAgain = false;
            computerQueue();
        }

    }






    public void userQueue(int x, int y){
        System.out.println("*******************userQueue***********************");
//      ХОДИТЬ ВПРАВО-ВВЕРХ
        try{
            if (field[x][y] == '0' && field[x + 1][y - 1] == '3') {
                if(x == 0){
                    field[x][y] = '5';
                }
                else {
                    field[x][y] = '2';
                }
                field[x + 1][y - 1] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = false;
                userMadeQueue = true;
            }
        }catch (Exception e){
//          System.err.println("Выход за пределы массива field");
        }
//      ХОДИТЬ ВЛЕВО-ВВЕРХ
        try{
            if (field[x][y] == '0' && field[x + 1][y + 1] == '3') {
                if(x == 0){
                    field[x][y] = '5';
                }
                else {
                    field[x][y] = '2';
                }
                field[x + 1][y + 1] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = false;
                userMadeQueue = true;
            }
        }catch (Exception e){
//          System.err.println("Выход за пределы массива field");
        }
//      срубить влево-вверх
        try {
            if (field[x][y] == '0' && field[x + 2][y + 2] == '3' && (field[x + 1][y + 1] == '1' || field[x + 1][y + 1] == '4')) {
                if(x == 0){
                    field[x][y] = '5';
                }
                else {
                    field[x][y] = '2';
                }
                field[x + 1][y + 1] = '0';
                field[x + 2][y + 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        }
        catch (Exception e) {

        }
//      срубить влево-вниз
        try {
            if (field[x][y] == '0' && field[x + 2][y - 2] == '3' && (field[x + 1][y - 1] == '1' || field[x + 1][y - 1] == '4')) {
                if(x == 0){
                    field[x][y] = '5';
                }
                else {
                    field[x][y] = '2';
                }
                field[x + 1][y - 1] = '0';
                field[x + 2][y - 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }
//      срубить вправо-вверх
        try {
            if (field[x][y] == '0' && field[x - 2][y + 2] == '3' && (field[x - 1][y + 1] == '1' || field[x - 1][y + 1] == '4')) {
                field[x][y] = '2';
                field[x - 1][y + 1] = '0';
                field[x - 2][y + 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }
//      срубить вправо-вниз
        try {
            if (field[x][y] == '0' && field[x - 2][y - 2] == '3' && (field[x - 1][y - 1] == '1' || field[x - 1][y - 1] == '4')) {
                field[x][y] = '2';
                field[x - 1][y - 1] = '0';
                field[x - 2][y - 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }

//      ПРОВЕРЯЕМ МОЖЕТ ЛИ ПОСЛЕ ТЕКУЩЕЙ АТАКИ СРУБИТЬ ЕЩЕ ШАШКУ
        if(checkAttack(x, y) && userMadeKill && field[x][y] == '2'){
            field[x][y] = '3';
            System.out.println("Можно срубить еще шашку противника");
            userMadeQueue = false;
            attackAgain = true;
        }
    }


    public void userKill(int x, int y){

        System.out.println("userKill");
//      срубить влево-вверх
        try {
            if (field[x][y] == '0' && field[x + 2][y + 2] == '3' && (field[x + 1][y + 1] == '1' || field[x + 1][y + 1] == '4')) {
                System.out.println("Срубить влево-вверх");
                field[x][y] = '2';
                field[x + 1][y + 1] = '0';
                field[x + 2][y + 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        }
        catch (Exception e) {

        }
//      срубить влево-вниз
        try {
            if (field[x][y] == '0' && field[x + 2][y - 2] == '3' && (field[x + 1][y - 1] == '1' || field[x + 1][y - 1] == '4')) {
                System.out.println("Срубить вправо-вверх");
                field[x][y] = '2';
                field[x + 1][y - 1] = '0';
                field[x + 2][y - 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }
//      срубить вправо-вверх
        try {
            if (field[x][y] == '0' && field[x - 2][y + 2] == '3' && (field[x - 1][y + 1] == '1' || field[x - 1][y + 1] == '4')) {
                System.out.println("Срубить влево-вниз");
                field[x][y] = '2';
                field[x - 1][y + 1] = '0';
                field[x - 2][y + 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }
//      срубить вправо-вниз
        try {
            if (field[x][y] == '0' && field[x - 2][y - 2] == '3' && (field[x - 1][y - 1] == '1' || field[x - 1][y - 1] == '4')) {
                System.out.println("Срубить вправо-вниз");
                field[x][y] = '2';
                field[x - 1][y - 1] = '0';
                field[x - 2][y - 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception e) {

        }

//      ПРОВЕРЯЕМ МОЖЕТ ЛИ USER ПОСЛЕ ТЕКУЩЕЙ АТАКИ СРУБИТЬ ЕЩЕ ШАШКУ
        if(checkAttack(x, y) && userMadeKill && field[x][y] == '2'){
            field[x][y] = '3';
            System.out.println("Можно срубить еще шашку противника");
            userMadeQueue = false;
            attackAgain = true;
        }
        else{
            userMadeQueue = true;
            attackAgain = false;
            computerQueue();
        }

    }







    public boolean checkAttack(int x, int y){
        printBoxInTerminal(field);
        try{
            if (field[x][y] == '2' && field[x + 2][y + 2] == '0' && field[x + 1][y + 1] == '1'){
                attackAgain = true;
                return true;
            }
            if (field[x][y] == '2' && field[x + 2][y - 2] == '0' && field[x + 1][y - 1] == '1'){
                attackAgain = true;
                return true;
            }
            if (field[x][y] == '2' && field[x - 2][y + 2] == '0' && field[x - 1][y + 1] == '1'){
                attackAgain = true;
                return true;
            }
            if (field[x][y] == '2' && field[x - 2][y - 2] == '0' && field[x - 1][y - 1] == '1'){
                attackAgain = true;
                return true;
            }
        }catch (Exception e){

        }
        attackAgain = false;
        return false;
    }



    public List<Integer> hasThreeOrSix(char[][] field){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '3' || field[i][j] == '6'){
                    list.add(i);
                    list.add(j);
                }
            }
        }
        return list;
    }



//  ПРОВЕРКА - МОЖЕТ ЛИ USER СРУБИТЬ ШАШКУ ПРОТИВНИКА
    public boolean userCanKill(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
//              срубить влево-вверх
                try {
                    if (field[i][j] == '0' && (field[i + 2][j + 2] == '2' || field[i + 2][j + 2] == '3') && (field[i + 1][j + 1] == '1' || field[i + 1][j + 1] == '4')) {
                        takeFukX = i + 2;
                        takeFukY = j + 2;
                        return true;
                    }
                }
                catch (Exception e) {

                }
//              срубить влево-вниз
                try {
                    if (field[i][j] == '0' && (field[i + 2][j - 2] == '2' || field[i + 2][j - 2] == '3') && (field[i + 1][j - 1] == '1' || field[i + 1][j - 1] == '4')) {
                        takeFukX = i + 2;
                        takeFukY = j - 2;
                        return true;
                    }
                } catch (Exception e) {

                }
//              срубить вправо-вверх
                try {
                    if (field[i][j] == '0' && (field[i - 2][j + 2] == '2' || field[i - 2][j + 2] == '3') && (field[i - 1][j + 1] == '1' || field[i - 1][j + 1] == '4')) {
                        takeFukX = i - 2;
                        takeFukY = j + 2;
                        return true;
                    }
                } catch (Exception e) {

                }
//              срубить вправо-вниз
                try {
                    if (field[i][j] == '0' && (field[i - 2][j - 2] == '2' || field[i - 2][j - 2] == '3') && (field[i - 1][j - 1] == '1' || field[i - 1][j - 1] == '4')) {
                        takeFukX = i - 2;
                        takeFukY = j - 2;
                        return true;
                    }
                } catch (Exception e) {

                }
            }
        }
        return false;
    }



//      ХОД КОМПЬЮТЕРА
//    public void computerQueue(){
//
////      БЛОК КОДА, ПРОВЕРЯЮЩИЙ ПОРАЖЕНИЕ COMPUTER  - ВЕРХНЯЯ ЧАСТЬ
////      **************************************************************************
////        int computer = 0;
////        for (int i = 0; i < field.length; i++) {
////            for (int j = 0; j < field[0].length; j++) {
////                if(field[i][j] == '1' || field[i][j] == '4'){
////                    computer++;
////                }
////            }
////        }
////        if(computer == 0){
////            System.out.println("Победил User");
////            return;
////        }
////      **************************************************************************
//
//
////        if(!computerCanKill() && !computerCanGo()){
////            System.out.println("Победил User");
////            System.out.println("Шашки Computer не могут сходить или нанести удар");
////            return;
////        }
////      БЛОК КОДА, ПРОВЕРЯЮЩИЙ ПОРАЖЕНИЕ COMPUTER  - НИЖНЯЯ ЧАСТЬ
//
//        System.out.println("Ход перешел к компьютеру");
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
//                if(field[i][j] == '3'){
//                    field[i][j] = '9';
//                }
//            }
//        }
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
//                if(field[i][j] == '6'){
//                    field[i][j] = '9';
//                }
//            }
//        }
//        System.out.println("Первое отображение доски в computerQueue");
//        printBoxInTerminal(field);
//        computerMadeKill = false;
//        computerMadeBigKill = false;
//        Random random = new Random();
//        int count = 0;
//        while (count < 1){
//            try{
//                int computerX = random.nextInt( 8);
//                int computerY = random.nextInt( 8);
//                List<Integer> listFourth = new ArrayList<>();
//                for (int x = 0; x < field.length; x++) {
//                    for (int y = 0; y < field.length; y++) {
//                        if(field[x][y] == '4'){
//                            listFourth.add(x);
//                            listFourth.add(y);
//                        }
//                    }
//                }
//                if(computerCanKill()){
//                    computerKill();
//                    while (computerCanSecondKill()){
//                        computerSecondKill();
//                    }
//                    count++;
//                }
//                else if (computerCanBigKill(listFourth)) {
//                    computerBigKill();
//                    while (computerCanSecondBigKill()){
//                        computerSecondBigKill();
//                    }
//                    count++;
//                }
////                else if (!computerCanKill() && !computerCanBigKill(listFourth) && computerCanGoOnlyOne()){
////                else {
////                    System.out.println("Проверяем возможность ходить обычной шашкой компьютера");
////                    System.out.println("Компьютер не может атаковать");
////                    if(field[computerX][computerY] == '0' || field[computerX][computerY] == '9'){
////                        int leftRight = random.nextInt(1,3);
////                        if(leftRight == 1) {
////                            try {
////                                if (field[computerX - 1][computerY - 1] == '1') {
////                                    if(computerX == 7){
////                                        field[computerX][computerY] = '4';
////                                    }
////                                    else {
////                                        field[computerX][computerY] = '1';
////                                    }
////                                    field[computerX - 1][computerY - 1] = '0';
////                                    count++;
////                                }
////                            } catch (Exception e) {
//////                                e.printStackTrace();
////                            }
////                        }
////                        else {
////                            try{
////                                if (field[computerX - 1][computerY + 1] == '1'){
////                                    if(computerX == 7){
////                                        field[computerX][computerY] = '4';
////                                    }
////                                    else {
////                                        field[computerX][computerY] = '1';
////                                    }
////                                    field[computerX - 1][computerY + 1] = '0';
////                                    count++;
////                                }
////                            }catch (Exception e){
//////                                e.printStackTrace();
////                            }
////                        }
////                    }
////                    count++;
////                }
////              БЛОК КОДА ОПИСЫВАЕТ ХОД ДАМКОЙ КОМПЬЮТЕРА, В СЛУЧАЕ ЕСЛИ ОБЫЧНЫЕ ШАШКИ НЕ МОГУХ ХОДИТЬ - ВЕРХНЯЯ ЧАСТЬ
//                else{
//                    System.out.println("У компьютера могут ТОЛЬКО ходить ТОЛЬКО дамки");
//                    int one = 0;
//                    int two = 0;
//                    int three = 0;
//                    int fourth = 0;
//                    int c = 0;
//                    List<Integer> computerListFourth = new ArrayList<>();
//                    for (int x = 0; x < field.length; x++) {
//                        for (int y = 0; y < field.length; y++) {
//                            if(field[x][y] == '4'){
//                                computerListFourth.add(x);
//                                computerListFourth.add(y);
//                            }
//                        }
//                    }
//                    while (c == 0){
//                        int x = computerListFourth.remove(0);
//                        int y = computerListFourth.remove(0);
//                        for (int i = 1; i < field.length; i++) {
////                          ХОДИТЬ ДАМКОЙ ВЛЕВО ВВЕРХ
//                            try{
//                                if(field[x][y] == '4' && field[x + i][y + i] == '0'){
//                                    if(one == 0){
//                                        field[x][y] = '0';
//                                        field[x + i][y + i] = '4';
//                                        c++;
//                                        count++;
//                                    }
//                                }
//                                else {
//                                    if(field[x + i][y + i] != '0'){
////                                      one увеличивается в том случае, если между координатой клика и '4' есть другие шашки
////                                      в таком случае не получится сходить на выбранную клетку
//                                        one++;
//                                    }
//                                }
//                            }catch (Exception ignored){
//
//                            }
////                          ХОДИТЬ ДАМКОЙ ВПРАВО ВНИЗ
//                            try{
//                                if(field[x][y] == '4' && field[x - i][y - i] == '0'){
//                                    if(two == 0){
//                                        field[x][y] = '0';
//                                        field[x - i][y - i] = '4';
//                                        c++;
//                                        count++;
//                                    }
//                                }
//                                else {
//                                    if(field[x - i][y - i] != '0'){
//                                        two++;
//                                    }
//                                }
//                            }catch (Exception e){
//
//                            }
//
////                          ХОДИТЬ ДАМКОЙ ВЛЕВО ВНИЗ
//                            try{
//                                if(field[x][y] == '4' && field[x - i][y + i] == '0'){
//                                    if(three == 0){
//                                        field[x][y] = '0';
//                                        field[x - i][y + i] = '4';
//                                        c++;
//                                        count++;
//                                    }
//                                }
//                                else {
//                                    if(field[x - i][y + i] != '0'){
//                                        three++;
//                                    }
//                                }
//                            }catch (Exception e){
//
//                            }
//
//
////                          ХОДИТЬ ДАМКОЙ ВПРАВО ВВЕРХ
//                            try{
//                                if(field[x][y] == '4' && field[x + i][y - i] == '0'){
//                                    if(fourth == 0){
//                                        field[x][y] = '0';
//                                        field[x + i][y - i] = '4';
//                                        c++;
//                                        count++;
//                                    }
//                                }
//                                else {
//                                    if(field[x + i][y - i] != '0'){
//                                        fourth++;
//                                    }
//                                }
//                            }catch (Exception e){
//
//                            }
//                        }
//                    }
//                }
////              БЛОК КОДА ОПИСЫВАЕТ ХОД ДАМКОЙ КОМПЬЮТЕРА, В СЛУЧАЕ ЕСЛИ ОБЫЧНЫЕ ШАШКИ НЕ МОГУХ ХОДИТЬ - НИЖНЯЯ ЧАСТЬ
//
//            }catch (Exception e){
////                System.err.println("Произошла ошибка во время хода компьютера");
//            }
//        }
//        printBoxInTerminal(field);
//        userMadeQueue = false;
//        userMadeBigQueue = false;
//        attackAgain = false;
//        attackBigAgain = false;
//
//
//
//    }
//
////  ПРОВЕРКА - МОЖЕТ ЛИ КОМПЬЮТЕР СРУБИТЬ ШАШКУ ПРОТИВНИКА
//    public boolean computerCanKill(){
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
////              срубить влево-вверх
//                try {
//                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j + 2] == '1' && (field[i + 1][j + 1] == '2' || field[i + 1][j + 1] == '5')) {
//                        return true;
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j - 2] == '1' && (field[i + 1][j - 1] == '2' || field[i + 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j + 2] == '1' && (field[i - 1][j + 1] == '2' || field[i - 1][j + 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j - 2] == '1' && (field[i - 1][j - 1] == '2' || field[i - 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        }
//        return false;
//    }
//
//
//
//
//
//    public void computerKill(){
//        System.out.println("computerKill()");
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field[0].length; y++) {
//                try {
//                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y + 2] == '1' && (field[x + 1][y + 1] == '2' || field[x + 1][y + 1] == '5')) {
//                        if(field[x + 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y + 1] = '0';
//                        field[x + 2][y + 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y - 2] == '1' && (field[x + 1][y - 1] == '2' || field[x + 1][y - 1] == '5')) {
//                        if(field[x + 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y - 1] = '0';
//                        field[x + 2][y - 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y + 2] == '1' && (field[x - 1][y + 1] == '2' || field[x - 1][y + 1] == '5')) {
//                        if(field[x - 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y + 1] = '0';
//                        field[x - 2][y + 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y - 2] == '1' && (field[x - 1][y - 1] == '2' || field[x - 1][y - 1] == '5')) {
//                        if(field[x - 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y - 1] = '0';
//                        field[x - 2][y - 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                    }
//                } catch (Exception e) {
//
//                }
//
//            }
//        }
//    }
//
//
//
////    public boolean computerCanBigKill(){
////
////        int one = 0;
////        int two = 0;
////        int three = 0;
////        int fourth = 0;
////
//////      ************************************ПРОВЕРКА ДАМКИ НА ВОЗМОЖНОСТЬ ПЕРВОГО УДАРА
////        for (int x = 0; x < field.length; x++) {
////            for (int y = 0; y < field.length; y++) {
////                for (int i = 0; i < field.length; i++) {
////                    for (int j = i; j < field.length; j++) {
////                        try{
////                            if (one == 1 && field[x][y] == '4' && (field[x + i][y + i] == '2' || field[x + i][y + i] == '5') && (field[x + j][y + j] == '0' || field[x + j][y + j] == '9')) {
////                                attackAgain = true;
////                                System.out.println("computerCanBigKill 1");
////                                return true;
////                            }
////                            else{
////                                if(field[x + i][y + i] == '2' || field[x + i][y + i] == '5'){
////                                    one++;
////                                }
////                            }
////                        }catch (Exception e){
//////                    System.err.println("Выход за пределы массива field");
////                        }
////                        try{
////                            if (two == 1 && field[x][y] == '4' && (field[x - j][y + j] == '0' || field[x - j][y + j] == '9') && (field[x - i][y + i] == '2' || field[x - i][y + i] == '5')) {
////                                attackAgain = true;
////                                System.out.println("computerCanBigKill 2");
////                                return true;
////                            }
////                            else{
////                                if(field[x - i][y + i] == '2' || field[x - i][y + i] == '5'){
////                                    two++;
////                                }
////                            }
////                        }catch (Exception e){
//////                    System.err.println("Выход за пределы массива field");
////                        }
////                        try{
////                            if (three == 1 && field[x][y] == '4' && (field[x + j][y - j] == '0' || field[x + j][y - j] == '9') && (field[x + i][y - i] == '2' || field[x + i][y - i] == '5')) {
////                                attackAgain = true;
////                                System.out.println("computerCanBigKill 3");
////                                return true;
////                            }
////                            else{
////                                if(field[x + i][y - i] == '2' || field[x + i][y - i] == '5'){
////                                    three++;
////                                }
////                            }
////                        }catch (Exception e){
//////                    System.err.println("Выход за пределы массива field");
////                        }
////                        try{
////                            if(fourth == 1){
//////                                System.out.println("===========================================================");
//////                                System.out.println("fourth: " + fourth);
//////                                System.out.println("field[x][y]: " + field[x][y]);
//////                                System.out.println("field[x + i][y + i]: " + field[x + i][y + i]);
//////                                System.out.println("field[x + j][y + j]: " + field[x + j][y + j]);
//////                                System.out.println("===========================================================");
////                            }
////                            if (fourth == 1 && field[x][y] == '4' && (field[x - j][y - j] == '0' || field[x - j][y - j] == '9') && (field[x - i][y - i] == '2' || field[x - i][y - i] == '5')) {
////                                attackAgain = true;
////                                System.out.println("computerCanBigKill 4");
////                                return true;
////                            }
////                            else{
////                                if(field[x - i][y - i] == '2' || field[x - i][y - i] == '5'){
////                                    fourth++;
////                                }
////                            }
////                        }catch (Exception e){
//////                    System.err.println("Выход за пределы массива field");
////                        }
////                    }
////                }
////            }
////        }
////        return false;
////    }
//
//    public boolean computerCanBigKill(List<Integer> listFourth){
//
////      ************************************ПРОВЕРКА ДАМКИ НА ВОЗМОЖНОСТЬ ПЕРВОГО УДАРА
//        while (!listFourth.isEmpty()){
//            int x = listFourth.remove(0);
//            int y = listFourth.remove(0);
//            try{
//                for (int i = x + 1, j = y + 1; i < field.length || j < field[0].length; i++, j++) {
//                    if(field[i][j] == '1' || field[i][j] == '4'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i + 1][j + 1] != '0'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i + 1][j + 1] == '0'){
//                        return true;
//                    }
//                }
//            }catch (Exception e){
//
//            }
//            try{
//                for (int i = x - 1, j = y - 1; i >= 0 || j >= 0; i--, j--) {
//                    if(field[i][j] == '1' || field[i][j] == '4'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i - 1][j - 1] != '0'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i - 1][j - 1] == '0'){
//                        return true;
//                    }
//                }
//            }catch (Exception e){
//
//            }
//            try{
//                for (int i = x - 1, j = y + 1; i >= 0 || j < field[0].length; i--, j++) {
//                    if(field[i][j] == '1' || field[i][j] == '4'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i - 1][j + 1] != '0'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i - 1][j + 1] == '0'){
//                        return true;
//                    }
//                }
//            }catch (Exception e){
//
//            }
//            try{
//                for (int i = x + 1, j = y - 1; i < field.length || j >= 0; i++, j--) {
//                    if(field[i][j] == '1' || field[i][j] == '4'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i + 1][j - 1] != '0'){
//                        return false;
//                    }
//                    if((field[i][j] == '2' || field[i][j] == '5') && field[i + 1][j - 1] == '0'){
//                        return true;
//                    }
//                }
//            }catch (Exception e){
//
//            }
//        }
//        return false;
//    }
//
//
//    public void computerBigKill(){
//        System.out.println("computerKill()");
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field[0].length; y++) {
//                for (int i = 0; i < field.length; i++) {
//                    for (int j = i; j < field[0].length; j++) {
//                        try{
//                            if (!computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + j][y + j] == '4' && (field[x + i][y + i] == '2' || field[x + j][y + j] == '5')) {
//                                field[x][y] = '4';
//                                field[x + j][y + j] = '0';
//                                field[x + i][y + i] = '0';
//                                computerMadeBigKill = true;
//                                positionWhoBigKillX = x;
//                                positionWhoBigKillY = y;
//                            }
//                        }catch (Exception e){
////                          System.err.println("Выход за пределы массива field");
//                        }
//                        try{
//                            if (!computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - j][y + j] == '4' && (field[x - i][y + i] == '2' || field[x - j][y + j] == '5')) {
//                                field[x][y] = '4';
//                                field[x - j][y + j] = '0';
//                                field[x - i][y + i] = '0';
//                                computerMadeBigKill = true;
//                                positionWhoBigKillX = x;
//                                positionWhoBigKillY = y;
//                            }
//                        }catch (Exception e){
////                          System.err.println("Выход за пределы массива field");
//                        }
//                        try{
//                            if (!computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + j][y - j] == '4' && (field[x + i][y - i] == '2' || field[x + j][y - j] == '5')) {
//                                field[x][y] = '4';
//                                field[x + j][y - j] = '0';
//                                field[x + i][y - i] = '0';
//                                computerMadeBigKill = true;
//                                positionWhoBigKillX = x;
//                                positionWhoBigKillY = y;
//                            }
//                        }catch (Exception e){
////                          System.err.println("Выход за пределы массива field");
//                        }
//                        try{
//                            if (!computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - j][y - j] == '4' && (field[x - i][y - i] == '2' || field[x - j][y - j] == '5')) {
//                                field[x][y] = '4';
//                                field[x - j][y - j] = '0';
//                                field[x - i][y - i] = '0';
//                                computerMadeBigKill = true;
//                                positionWhoBigKillX = x;
//                                positionWhoBigKillY = y;
//                            }
//                        }catch (Exception e){
////                          System.err.println("Выход за пределы массива field");
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//
//
//
//    public boolean computerCanSecondKill(){
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
////              срубить влево-вверх
//                try {
//                    if ((positionWhoKillX == i + 2) && (positionWhoKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i + 2][j + 2] == '1' || field[i + 2][j + 2] == '4') && (field[i + 1][j + 1] == '2' || field[i + 1][j + 1] == '5')) {
//                        return true;
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if ((positionWhoKillX == i + 2) && (positionWhoKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i + 2][j - 2] == '1' || field[i + 2][j - 2] == '4') && (field[i + 1][j - 1] == '2' || field[i + 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if ((positionWhoKillX == i - 2) && (positionWhoKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i - 2][j + 2] == '1' || field[i - 2][j + 2] == '4') && (field[i - 1][j + 1] == '2' || field[i - 1][j + 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if ((positionWhoKillX == i - 2) && (positionWhoKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i - 2][j - 2] == '1' || field[i - 2][j - 2] == '4') && (field[i - 1][j - 1] == '2' || field[i - 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        }
//        return false;
//    }
//
//
//
//
//    public void computerSecondKill(){
//        System.out.println("computerSecondKill");
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field[0].length; y++) {
//                try {
//                    if ((positionWhoKillX == x + 2) && (positionWhoKillY == y + 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x + 2][y + 2] == '1' || field[x + 2][y + 2] == '4') && (field[x + 1][y + 1] == '2' || field[x + 1][y + 1] == '5')) {
//                        if(field[x + 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y + 1] = '0';
//                        field[x + 2][y + 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                        System.out.println("First");
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if ((positionWhoKillX == x + 2) && (positionWhoKillY == y - 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x + 2][y - 2] == '1' || field[x + 2][y - 2] == '4') && (field[x + 1][y - 1] == '2' || field[x + 1][y - 1] == '5')) {
//                        if(field[x + 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y - 1] = '0';
//                        field[x + 2][y - 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                        System.out.println("Second");
//
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if ((positionWhoKillX == x - 2) && (positionWhoKillY == y + 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x - 2][y + 2] == '1' || field[x - 2][y + 2] == '4') && (field[x - 1][y + 1] == '2' || field[x - 1][y + 1] == '5')) {
//                        if(field[x - 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y + 1] = '0';
//                        field[x - 2][y + 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                        System.out.println("Third");
//
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if ((positionWhoKillX == x - 2) && (positionWhoKillY == y - 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x - 2][y - 2] == '1' || field[x - 2][y - 2] == '4') && (field[x - 1][y - 1] == '2' || field[x - 1][y - 1] == '5')) {
//                        if(field[x - 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y - 1] = '0';
//                        field[x - 2][y - 2] = '0';
//                        computerMadeKill = true;
//                        positionWhoKillX = x;
//                        positionWhoKillY = y;
//                        System.out.println("Forth");
//
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        }
//    }
//
//
//
//    public boolean computerCanSecondBigKill(){
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[0].length; j++) {
////              срубить влево-вверх
//                try {
//                    if ((positionWhoBigKillX == i + 2) && (positionWhoBigKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i + 2][j + 2] == '1' || field[i + 2][j + 2] == '4') && (field[i + 1][j + 1] == '2' || field[i + 1][j + 1] == '5')) {
//                        return true;
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if ((positionWhoBigKillX == i + 2) && (positionWhoBigKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i + 2][j - 2] == '1' || field[i + 2][j - 2] == '4') && (field[i + 1][j - 1] == '2' || field[i + 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if ((positionWhoBigKillX == i - 2) && (positionWhoBigKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i - 2][j + 2] == '1' || field[i - 2][j + 2] == '4') && (field[i - 1][j + 1] == '2' || field[i - 1][j + 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if ((positionWhoBigKillX == i - 2) && (positionWhoBigKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && (field[i - 2][j - 2] == '1' || field[i - 2][j - 2] == '4') && (field[i - 1][j - 1] == '2' || field[i - 1][j - 1] == '5')) {
//                        return true;
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        }
//        return false;
//    }
//
//
//
//    public void computerSecondBigKill(){
//        System.out.println("computerSecondKill");
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field[0].length; y++) {
//                try {
//                    if ((positionWhoBigKillX == x + 2) && (positionWhoBigKillY == y + 2) && computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x + 2][y + 2] == '1' || field[x + 2][y + 2] == '4') && (field[x + 1][y + 1] == '2' || field[x + 1][y + 1] == '5')) {
//                        if(field[x + 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y + 1] = '0';
//                        field[x + 2][y + 2] = '0';
//                        computerMadeBigKill = true;
//                        positionWhoBigKillX = x;
//                        positionWhoBigKillY = y;
//                        System.out.println("First");
//                    }
//                }
//                catch (Exception e) {
//
//                }
////              срубить влево-вниз
//                try {
//                    if ((positionWhoBigKillX == x + 2) && (positionWhoBigKillY == y - 2) && computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x + 2][y - 2] == '1' || field[x + 2][y - 2] == '4') && (field[x + 1][y - 1] == '2' || field[x + 1][y - 1] == '5')) {
//                        if(field[x + 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x + 1][y - 1] = '0';
//                        field[x + 2][y - 2] = '0';
//                        computerMadeBigKill = true;
//                        positionWhoBigKillX = x;
//                        positionWhoBigKillY = y;
//                        System.out.println("Second");
//
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вверх
//                try {
//                    if ((positionWhoBigKillX == x - 2) && (positionWhoBigKillY == y + 2) && computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x - 2][y + 2] == '1' || field[x - 2][y + 2] == '4') && (field[x - 1][y + 1] == '2' || field[x - 1][y + 1] == '5')) {
//                        if(field[x - 2][y + 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y + 1] = '0';
//                        field[x - 2][y + 2] = '0';
//                        computerMadeBigKill = true;
//                        positionWhoBigKillX = x;
//                        positionWhoBigKillY = y;
//                        System.out.println("Third");
//
//                    }
//                } catch (Exception e) {
//
//                }
////              срубить вправо-вниз
//                try {
//                    if ((positionWhoBigKillX == x - 2) && (positionWhoBigKillY == y - 2) && computerMadeBigKill && (field[x][y] == '0' || field[x][y] == '9') && (field[x - 2][y - 2] == '1' || field[x - 2][y - 2] == '4') && (field[x - 1][y - 1] == '2' || field[x - 1][y - 1] == '5')) {
//                        if(field[x - 2][y - 2] == '1'){
//                            field[x][y] = '1';
//                        }
//                        else {
//                            field[x][y] = '4';
//                        }
//                        field[x - 1][y - 1] = '0';
//                        field[x - 2][y - 2] = '0';
//                        computerMadeBigKill = true;
//                        positionWhoBigKillX = x;
//                        positionWhoBigKillY = y;
//                        System.out.println("Forth");
//
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        }
//    }
//
//    public boolean userCanGo(){
//        try{
//            for (int i = 0; i < field.length; i++) {
//                for (int j = 0; j < field[0].length; j++) {
//                    if(field[i][j] == '5'|| field[i][j] == '6'){
//                        System.out.println("Есть возможность перемещаться по игровому полю");
//                        return true;
//                    }
//                    if((field[i][j] == '2'|| field[i][j] == '3') && (field[i - 1][j + 1] == '0'|| field[i - 1][j - 1] == '0')){
//                        System.out.println("Есть возможность перемещаться по игровому полю");
//                        return true;
//                    }
//                }
//            }
//        }catch (Exception e){
//
//        }
//        System.out.println("Невозможно перемещаться по игровому полю");
//        return false;
//    }
//
//    public boolean computerCanGo(){
//        try{
//            for (int i = 0; i < field.length; i++) {
//                for (int j = 0; j < field[0].length; j++) {
//                    if(field[i][j] == '4'){
//                        return true;
//                    }
//                    if(field[i][j] == '1' && (field[i + 1][j + 1] == '0'|| field[i + 1][j - 1] == '0')){
//                        return true;
//                    }
//                }
//            }
//        }catch (Exception e){
//
//        }
//        System.out.println("Шашки компьютера не могут перемещаться по игровому полю");
//        return false;
//    }
//
//    public boolean computerCanGoOnlyOne(){
//        try{
//            for (int i = 0; i < field.length; i++) {
//                for (int j = 0; j < field[0].length; j++) {
//                    if(field[i][j] == '1' && (field[i + 1][j + 1] == '0'|| field[i + 1][j - 1] == '0')){
//                        return true;
//                    }
//                }
//            }
//        }catch (Exception e){
//
//        }
//        System.out.println("Обычные шашки компьютера не могут перемещаться по игровому полю");
//        return false;
//    }


    //    ХОД КОМПЬЮТЕРА
    public void computerQueue(){
        int computer = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '1' || field[i][j] == '4'){
                    computer++;
                }
            }
        }
        if(computer == 0){
            System.out.println("Победил User");
            userWin = true;
            return;
        }
        System.out.println("Ход перешел к компьютеру");
        printBoxInTerminal(field);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == '3'){
                    field[i][j] = '9';
                }
                if(field[i][j] == '6'){
                    field[i][j] = '9';
                }
            }
        }
        System.out.println("Первое отображение доски в computerQueue");
        printBoxInTerminal(field);
        computerMadeKill = false;
        Random random = new Random();
        int count = 0;
        while (count < 1){
            try{
                int computerX = random.nextInt( 8);
                int computerY = random.nextInt( 8);
                if(computerCanKill()){
                    computerKill();
                    while (computerCanSecondKill()){
                        computerSecondKill();
                    }
                    count++;
                }
                else {
                    if(field[computerX][computerY] == '0' || field[computerX][computerY] == '9'){
                        int leftRight = random.nextInt(1,3);
                        if(leftRight == 1) {
                            try {
                                if (field[computerX - 1][computerY - 1] == '1') {
                                    if(computerX == 7){
                                        field[computerX][computerY] = '4';
                                    }
                                    else {
                                        field[computerX][computerY] = '1';
                                    }
                                    field[computerX - 1][computerY - 1] = '0';
                                    count++;
                                }
                            } catch (Exception e) {
//                                e.printStackTrace();
                            }
                        }
                        else {
                            try{
                                if (field[computerX - 1][computerY + 1] == '1'){
                                    if(computerX == 7){
                                        field[computerX][computerY] = '4';
                                    }
                                    else {
                                        field[computerX][computerY] = '1';
                                    }
                                    field[computerX - 1][computerY + 1] = '0';
                                    count++;
                                }
                            }catch (Exception e){
//                                e.printStackTrace();
                            }
                        }
                    }
                }
            }catch (Exception e){
//                System.err.println("Произошла ошибка во время хода компьютера");
            }
        }
        printBoxInTerminal(field);
        userMadeQueue = false;
        attackAgain = false;
    }

    //  ПРОВЕРКА - МОЖЕТ ЛИ КОМПЬЮТЕР СРУБИТЬ ШАШКУ ПРОТИВНИКА
    public boolean computerCanKill(){

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
//          срубить влево-вверх
                try {
                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j + 2] == '1' && field[i + 1][j + 1] == '2') {
                        return true;
                    }
                }
                catch (Exception e) {

                }
//          срубить влево-вниз
                try {
                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j - 2] == '1' && field[i + 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вверх
                try {
                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j + 2] == '1' && field[i - 1][j + 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вниз
                try {
                    if ((field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j - 2] == '1' && field[i - 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
            }
        }

//      ************************************ПРОВЕРКА ДАМКИ НА ВОЗМОЖНОСТЬ ПОВТОРНОЙ АТАКИ
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field.length; j++) {
                        try{
                            if (field[x][y] == '4' && field[x + j][y + j] == '0' && (field[x + i][y + i] == '2' || field[x + j][y + j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '4' && field[x - j][y + j] == '0' && (field[x - i][y + i] == '2' || field[x - j][y + j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '4' && field[x + j][y - j] == '0' && (field[x + i][y - i] == '2' || field[x + j][y - j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '4' && field[x - j][y - j] == '0' && (field[x - i][y - i] == '2' || field[x - j][y - j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                    }
                }
            }
        }

        return false;
    }


    public void computerKill(){
        System.out.println("computerKill()");
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                try {
                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y + 2] == '1' && field[x + 1][y + 1] == '2') {
                        field[x][y] = '1';
                        field[x + 1][y + 1] = '0';
                        field[x + 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                }
                catch (Exception e) {

                }
//          срубить влево-вниз
                try {
                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y - 2] == '1' && field[x + 1][y - 1] == '2') {
                        field[x][y] = '1';
                        field[x + 1][y - 1] = '0';
                        field[x + 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вверх
                try {
                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y + 2] == '1' && field[x - 1][y + 1] == '2') {
                        field[x][y] = '1';
                        field[x - 1][y + 1] = '0';
                        field[x - 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вниз
                try {
                    if (!computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y - 2] == '1' && field[x - 1][y - 1] == '2') {
                        field[x][y] = '1';
                        field[x - 1][y - 1] = '0';
                        field[x - 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception e) {

                }

                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field.length; j++) {
                        try{
                            if (field[x][y] == '0' && field[x + j][y + j] == '4' && (field[x + i][y + i] == '2' || field[x + j][y + j] == '5')) {
                                field[x][y] = '4';
                                field[x + j][y + j] = '0';
                                field[x + i][y + i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '0' && field[x - j][y + j] == '4' && (field[x - i][y + i] == '2' || field[x - j][y + j] == '5')) {
                                field[x][y] = '4';
                                field[x - j][y + j] = '0';
                                field[x - i][y + i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '0' && field[x + j][y - j] == '4' && (field[x + i][y - i] == '2' || field[x + j][y - j] == '5')) {
                                field[x][y] = '4';
                                field[x + j][y - j] = '0';
                                field[x + i][y - i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                        try{
                            if (field[x][y] == '0' && field[x - j][y - j] == '4' && (field[x - i][y - i] == '2' || field[x - j][y - j] == '5')) {
                                field[x][y] = '4';
                                field[x - j][y - j] = '0';
                                field[x - i][y - i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        }catch (Exception e){
//                    System.err.println("Выход за пределы массива field");
                        }
                    }
                }
            }
        }
    }




    public boolean computerCanSecondKill(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
//          срубить влево-вверх
                try {
                    if ((positionWhoKillX == i + 2) && (positionWhoKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j + 2] == '1' && field[i + 1][j + 1] == '2') {
                        return true;
                    }
                }
                catch (Exception e) {

                }
//          срубить влево-вниз
                try {
                    if ((positionWhoKillX == i + 2) && (positionWhoKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && field[i + 2][j - 2] == '1' && field[i + 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вверх
                try {
                    if ((positionWhoKillX == i - 2) && (positionWhoKillY == j + 2) && (field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j + 2] == '1' && field[i - 1][j + 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
//          срубить вправо-вниз
                try {
                    if ((positionWhoKillX == i - 2) && (positionWhoKillY == j - 2) && (field[i][j] == '0' || field[i][j] == '9') && field[i - 2][j - 2] == '1' && field[i - 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception e) {

                }
            }
        }
        return false;
    }



    public void computerSecondKill(){
        System.out.println("computerSecondKill");
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                try {
                    if ((positionWhoKillX == x + 2) && (positionWhoKillY == y + 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y + 2] == '1' && field[x + 1][y + 1] == '2') {
                        field[x][y] = '1';
                        field[x + 1][y + 1] = '0';
                        field[x + 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("First");
                    }
                }
                catch (Exception e) {

                }
//          срубить влево-вниз
                try {
                    if ((positionWhoKillX == x + 2) && (positionWhoKillY == y - 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x + 2][y - 2] == '1' && field[x + 1][y - 1] == '2') {
                        field[x][y] = '1';
                        field[x + 1][y - 1] = '0';
                        field[x + 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Second");

                    }
                } catch (Exception e) {

                }
//          срубить вправо-вверх
                try {
                    if ((positionWhoKillX == x - 2) && (positionWhoKillY == y + 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y + 2] == '1' && field[x - 1][y + 1] == '2') {
                        field[x][y] = '1';
                        field[x - 1][y + 1] = '0';
                        field[x - 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Third");

                    }
                } catch (Exception e) {

                }
//          срубить вправо-вниз
                try {
                    if ((positionWhoKillX == x - 2) && (positionWhoKillY == y - 2) && computerMadeKill && (field[x][y] == '0' || field[x][y] == '9') && field[x - 2][y - 2] == '1' && field[x - 1][y - 1] == '2') {
                        field[x][y] = '1';
                        field[x - 1][y - 1] = '0';
                        field[x - 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Forth");

                    }
                } catch (Exception e) {

                }
            }
        }
    }




//  ЗАПОЛНИЛИ ДОСКУ ШАШКАМИ (*,0,1,2)
    public char[][] fillBox(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(i%2 == 0 && j%2 != 0 && i < 3 || i%2 != 0 && j%2 == 0 && i < 3){
                    field[i][j] = '0';
                } else if (i%2 == 0 && j%2 != 0 && i > 4 || i%2 != 0 && j%2 == 0 && i > 4) {
                    field[i][j] = '0';
                } else if (i%2 == 0 && j%2 != 0 || i%2 != 0 && j%2 == 0) {
                    field[i][j] = '0';
                } else {
                    field[i][j] = '*';
                }


                field[7][0] = '2';
//                field[5][2] = '1';
                field[6][1] = '1';

            }
        }
        return field;
    }


//  РИСУЕТ ПОЛОЖЕНИЕ ШАШЕК НА ДОСКЕ В ТЕРМИНАЛЕ (*,0,1,2,3,4,5,6,9)
    public void printBoxInTerminal(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }



//  ПЕРЕДАЛ ПОЛОЖЕНИЕ ШАШЕК В ГРАФИКУ
    public char[][] getField() {

        return field;

    }

    public boolean userIsWinner(){

        return userWin;

    }

    public boolean computerIsWinner(){

        return computerWin;

    }

}


