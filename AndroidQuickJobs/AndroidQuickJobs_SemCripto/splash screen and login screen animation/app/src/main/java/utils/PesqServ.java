package utils;

public abstract class PesqServ {

    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PesqServ.id = id;
    }
}
