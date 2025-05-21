package ss12_java_collection_framework.thuc_hanh.binary_search_tree;

public class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean insert(E e) {
        return false;
    }

    @Override
    public void inorder() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
