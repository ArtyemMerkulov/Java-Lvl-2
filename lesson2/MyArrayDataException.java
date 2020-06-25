package homework2;

public class MyArrayDataException extends Exception {
    private int[] idxs;

    public MyArrayDataException(String msg, int[] idxs) {
        super(msg);
        this.idxs = idxs.clone();
    }

    public int[] getIdxs() {
        int[] idxs = new int[this.idxs.length];

        for(int i = 0; i < idxs.length; i++) {
            idxs[i] = this.idxs[i];
        }

        return idxs;
    }
}
