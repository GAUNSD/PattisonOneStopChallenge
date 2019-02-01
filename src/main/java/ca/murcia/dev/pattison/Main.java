package ca.murcia.dev.pattison;

import java.util.List;

public class Main {

    public static void main(String[] args) throws BookException {

        List< Book > books = new BookReader().read();

        BookWriter bookWriter = new BookWriter();
        bookWriter.write( books );
    }
}
