package word2vec;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Word2VecModel <T> {
    double cosineSimilarity(T word1, T word2);
    double cosineSimilarityNormalizedVecs(T word1, T word2);
    double conceptSimilarityToWords(T concept, List<T> words);
    void saveModel(Path path, boolean isTsv, boolean fileHeader) throws IOException;
    int getVectorsLen();
}