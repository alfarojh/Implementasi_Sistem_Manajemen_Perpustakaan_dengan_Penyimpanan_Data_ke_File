package manager;

import library.models.Book;
import library.models.LibraryRecord;
import library.models.Member;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private final String wordSeparator = ",";
    private String pathMember;
    private String pathBook;
    private String pathRecord;

    // Konstrukter untuk mengatur path berdasarkan file config
    public FileManager() {
        try {
            File file = new File("config.env");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[\"'\\s]", "");
                String[] column = line.split("=");

                if (column[0].equalsIgnoreCase("member")) {
                    pathMember = column[1];
                } else if (column[0].equalsIgnoreCase("book")) {
                    pathBook = column[1];
                } else if (column[0].equalsIgnoreCase("log")) {
                    pathRecord = column[1];
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.print("");
        }
    }

    // Fungsi untuk menyimpan list member ke dalam file
    public void saveMembersToFile(ArrayList<Member> arrayList) {
        try {
            File file = new File(pathMember);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fw);

            for (Member member : arrayList) {
                bf.append(member.getId()).append(wordSeparator);
                bf.append(member.getName()).append(wordSeparator);
                bf.append(String.valueOf(member.getBorrowedBookCount())).append(wordSeparator);
                bf.append(member.getStatus());
                bf.newLine();
                bf.flush();
            }
            bf.close();
        } catch (IOException e) {
            System.out.print("");
        }
    }

    // Fungsi untuk memuat file dan menyimpannya ke dalam list member
    public ArrayList<Member> loadMembersFromFile() {
        ArrayList<Member> members = new ArrayList<>();
        try {
            File file = new File(pathMember);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] column = line.split(wordSeparator);
                members.add(new Member(column[0], column[1], Integer.parseInt(column[2]), column[3]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File Member.csv tidak ditemukan.");
        }
        return members;
    }

    // Fungsi untuk menyimpan list buku ke dalam file
    public void saveBooksToFile(ArrayList<Book> listBooks, ArrayList<Member> listMembers) {
        try {
            File file = new File(pathBook);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fw);

            for (Book book : listBooks) {
                bf.append(book.getISBN()).append(wordSeparator);
                bf.append(book.getTitle()).append(wordSeparator);
                bf.append(book.getAuthor()).append(wordSeparator);
                bf.append(String.valueOf(book.getAmount())).append(wordSeparator);
                if (book.getMember() == null) {
                    bf.append("0").append(wordSeparator);
                } else {
                    for (Member member: listMembers) {
                        if (book.getMember().equals(member)) {
                            bf.append(member.getId()).append(wordSeparator);
                            break;
                        }
                    }
                }
                bf.append(book.getStatus());
                bf.newLine();
                bf.flush();
            }
            bf.close();
        } catch (IOException e) {
            System.out.print("");
        }
    }

    // Fungsi untuk memuat file dan menyimpannya ke dalam list buku
    public ArrayList<Book> loadBookFromFile(ArrayList<Member> listMembers) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            File file = new File(pathBook);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] column = line.split(wordSeparator);
                if (column[4].equals("0")) {
                    books.add(new Book(column[0], column[1], column[2], Integer.parseInt(column[3]), null, column[5]));
                } else {
                    for (Member member: listMembers) {
                        if (member.getId().equals(column[4])) {
                            books.add(new Book(column[0], column[1], column[2], Integer.parseInt(column[3]), member, column[5]));
                            break;
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File Book.csv tidak ditemukan.");
        }
        return books;
    }

    // Fungsi untuk menyimpan list transaksi peminjaman ke dalam file
    public void saveRecordToFile(ArrayList<LibraryRecord> listRecord, ArrayList<Book> listBooks, ArrayList<Member> listMembers) {
        try {
            File file = new File(pathRecord);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fw);

            for (LibraryRecord libraryRecord: listRecord) {
                bf.append(libraryRecord.getTimestampBorrow()).append(wordSeparator);
                for (Book book: listBooks) {
                    if (book.equals(libraryRecord.getBook())) {
                        bf.append(book.getISBN()).append(wordSeparator);
                        break;
                    }
                }
                for (Member member: listMembers) {
                    if (member.equals(libraryRecord.getMember())) {
                        bf.append(member.getId()).append(wordSeparator);
                        break;
                    }
                }
                bf.append(libraryRecord.getStatusBorrow()).append(wordSeparator);
                bf.append(libraryRecord.getTimestampReturn());
                bf.flush();
            }
            bf.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    // Fungsi untuk memuat file dan menyimpannya ke dalam list transaksi peminjaman
    public ArrayList<LibraryRecord> loadRecordFromFile(ArrayList<Book> listBooks, ArrayList<Member> listMembers) {
        ArrayList<LibraryRecord> records = new ArrayList<>();
        try {
            File file = new File(pathRecord);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] column = line.split(wordSeparator);
                Book book = null;
                Member member = null;

                for (Book book1: listBooks) {
                    if (book1.getISBN().equals(column[1])) {
                        book = book1;
                        break;
                    }
                }
                for (Member member1: listMembers) {
                    if (member1.getId().equals(column[2])) {
                        member = member1;
                        break;
                    }
                }
                records.add(new LibraryRecord(column[0], book, member, column[3], column[4]));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File Record.csv tidak ditemukan.");
        }

        return records;
    }
}
