import custom.DisplayPrint;
import custom.InputHandler;
import library.Library;

public class Main {
    public static void main(String[] args) {
        DisplayPrint displayPrint = new DisplayPrint();
        InputHandler inputHandler = new InputHandler();
        Library library = new Library();
//        library.defaultData();

        while (true) {
            try {
                int choice = inputHandler.getIntegerInput(displayPrint.displayMenu());
                switch (choice) {
                    case 0 -> throw new Exception();
                    case 1 -> {
                        while (true) {
                            try {
                                inputHandler.newLine();
                                choice = inputHandler.getIntegerInput(displayPrint.displayBookController());

                                switch (choice) {
                                    case 0 -> throw new Exception();
                                    case 1 -> library.addBook();
                                    case 2 -> library.removeBook();
                                    case 3 -> library.showBooksNonAvailable();
                                    case 4 -> library.showBooksAvailable();
                                    case 5 -> library.findBook();
                                    default -> inputHandler.errorMessage("Input diluar batas pilihan.");
                                }
                            } catch (Exception e) {
                                System.out.println("Kembali ke menu utama.");
                                inputHandler.newLine();
                                break;
                            }
                        }
                    }
                    case 2 -> {
                        while (true) {
                            try {
                                inputHandler.newLine();
                                choice = inputHandler.getIntegerInput(displayPrint.displayMemberController());

                                switch (choice) {
                                    case 0 -> throw new Exception();
                                    case 1 -> library.addMember();
                                    case 2 -> library.removeMember();
                                    case 3 -> library.findMember();
                                    default -> inputHandler.errorMessage("Input diluar batas pilihan.");
                                }
                            } catch (Exception e) {
                                System.out.println("Kembali ke menu utama.");
                                inputHandler.newLine();
                                break;
                            }
                        }
                    }
                    case 3 -> library.borrowBook();
                    case 4 -> library.returnBorrowBook();
                    case 5 -> library.showRecord();
                    case 6 -> library.saveToFile();
                    case 7 -> library.loadFromFile();
                    default -> inputHandler.errorMessage("Input diluar batas pilihan.");
                }
            } catch (Exception e) {
                System.out.println("Keluar program.");
//                e.printStackTrace();
                break;
            }
        }
        inputHandler.close();

    }
}