package pa2.SPL_DIGITAL_LIB;

/**
 * @author zhangqiang26@jd.com
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @date 2018/11/18
 * @Description: TODO
 */

public class BootStrap {

    public static void main(String[] args){
        Book book1 = new Book("An Interesting Book", "Antonella", 12345678);
        Book book2 = new Book("Another Book", "Raphael", 87654312);
        Book book3 = new Book("A Third Book", "Allison", 90871234);

        SplayTreeNode<Book> node = new SplayTreeNode<Book>(book1);
        SplayTreeNode<Book> leftnode = new SplayTreeNode<Book>(book2);
        SplayTreeNode<Book> rightnode = new SplayTreeNode<Book>(book3);
        new SplayTreeDigitalLibrary().main(args);
    }
}
