package word2vec;


import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

/**
 * This class rapresent a Word2vec model with vector type Double.
 * @param <T> Type of words.
 */
public class Word2VecModelDouble<T> implements Map<T , double[]>, Word2VecModel<T>{
    private HashMap<T, double[]> map;
    private int vectorsLen;

    public Word2VecModelDouble(int vectorsLen) {
        this.map = new HashMap<>();
        this.vectorsLen = vectorsLen;
    }

    /**
     *
     * @param word1 first word
     * @param word2 second word
     * @return Return 0 if one of words not exist.
     */
    public double cosineSimilarity(T word1, T word2) {
        double[] vec1 = map.get(word1);
        double[] vec2 = map.get(word2);

        if (vec1 == null || vec2 == null) return 0.0;

        return Word2VecMath.cosineSimilarity(vec1,vec2);
    }

    /**
     *
     * @param word1 first word
     * @param word2 second word
     * @return Return 0 if word not exist.
     */
    public double cosineSimilarityNormalizedVecs(T word1, T word2) {
        double[] vec1 = map.get(word1);
        double[] vec2 = map.get(word2);

        if (vec1 == null || vec2 == null) return 0.0;

        return Word2VecMath.cosineSimilarityNormalizedVecs(vec1,vec2);
    }


    public double conceptSimilarityToWords(T concept,List<T> words) {
        double max = 0;
        for (T word : words) {
            max = Math.max(max,cosineSimilarity(concept,word));
        }
        return max;
    }

    /**
     *
     * @param path File path
     * @param converter Convert string to a generic type G
     * @param <G> Generic type
     * @return Word2vec model of File
     * @throws IOException Error during read of file.
     */

    public static <G> Word2VecModelDouble<G> readModelFromTxt(Path path, Function<String, G> converter, boolean isTsv, boolean fileHeader) throws IOException {
        // String format
        BufferedReader buff = new BufferedReader(new FileReader(path.toFile()));
        int vectorsLen = 0;
        String separator = isTsv ? "\t" : " ";

        if (fileHeader) {
            String header = buff.readLine();
            System.out.println(header);
            String[] arr = header.split(separator);

            int nVector = Integer.valueOf(arr[0]);
            vectorsLen = Integer.valueOf(arr[1]);
        }

        Word2VecModelDouble<G> model = new Word2VecModelDouble<>(vectorsLen);

        while (buff.ready()){
            String line = buff.readLine();
            String[] splittedArr = line.split(separator);

            if (!fileHeader) vectorsLen = splittedArr.length-1;

            if (splittedArr.length == (vectorsLen + 1)) {
                double[] vec = new double[vectorsLen];
                for (int i = 0; i < vectorsLen; i++) vec[i] = Double.valueOf(splittedArr[i+1]);
                if (converter.apply(splittedArr[0]) == null) continue;
                model.put(converter.apply(splittedArr[0]), vec);
            }
        }
        return model;
    }

    /**
     *
     * @param path File path
     * @param isTsv File is tab separated.
     * @param fileHeader insert file header in file.
     * @throws IOException
     */
    public void saveModel (Path path, boolean isTsv,boolean fileHeader) throws IOException {
        BufferedWriter buff = new BufferedWriter(new FileWriter(path.toFile()));
        String separator = isTsv ? "\t" : " ";
        if (fileHeader) {
            buff.write(Integer.toString(this.map.size()) + separator + Integer.toString(this.map.get(0).length));
            buff.newLine();
        }
        for (T elem : this.map.keySet()) {
            buff.write(elem.toString());
            for (double doub : this.map.get(elem)) {
                buff.write(separator + Double.toString(doub));
            }
            buff.newLine();
        }
    }

    /**
     *
     * @param path File path
     * @return Word2vec model of File.
     * @throws IOException Error during read of file.
     */
    public static Word2VecModelDouble<String> readModelFromTxt(Path path) throws IOException {
        boolean isTsv = path.getFileName().toString().endsWith(".tsv");
        return readModelFromTxt(path, Function.identity(),isTsv, true);
    }



    public int getVectorsLen() {
        return vectorsLen;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public double[] get(Object key) {
        return map.get(key);
    }

    @Override
    public double[] put(T key, double[] value) {
        return map.put(key,value);
    }

    @Override
    public double[] remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends T, ? extends double[]> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<T> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<double[]> values() {
        return map.values();
    }

    @Override
    public Set<Entry<T, double[]>> entrySet() {
        return map.entrySet();
    }

}
