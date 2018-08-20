package word2vec;

/**
 * Word To Vector Math.
 */
public class Word2VecMath {

    public synchronized static float[] sub(final float[] vectorA, final float[] vectorB) {
        if (vectorA.length == vectorB.length) {
            final float[] add = new float[vectorA.length];
            for (int i = 0; i < vectorA.length; i++) {
                add[i] = vectorA[i] - vectorB[i];
            }
            return add;
        }
        return null;
    }

    public synchronized static float[] add(final float[] vectorA, final float[] vectorB) {
        if (vectorA.length == vectorB.length) {
            final float[] add = new float[vectorA.length];
            for (int i = 0; i < vectorA.length; i++) {
                add[i] = vectorA[i] + vectorB[i];
            }
            return add;
        }
        return null;
    }

    public synchronized static double cosineSimilarity(final float[] vectorA, final float[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += vectorA[i] * vectorA[i];
            normB += vectorB[i] * vectorB[i];
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public synchronized static double cosineSimilarityNormalizedVecs(final float[] vectorA,
                                                                     final float[] vectorB) {
        double c = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            c += vectorA[i] * vectorB[i];
        }
        return c;
    }

    public synchronized static double norm(final float[] vectorA) {
        double normA = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            normA += vectorA[i] * vectorA[i];
        }
        return Math.sqrt(normA);
    }

    public synchronized static float[] normalize(final float[] vectorA) {
        final Double normA = norm(vectorA);
        for (int i = 0; i < vectorA.length; i++) {
            vectorA[i] /= normA.floatValue();
        }
        return vectorA;
    }

    //Adding maxval and minval methods
    //Method for getting the maximum value
    public static float getMax(float[] inputArray) {
        float maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    // Method for getting the minimum value
    public static float getMin(float[] inputArray) {
        float minValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] < minValue) {
                minValue = inputArray[i];
            }
        }
        return minValue;
    }

    public synchronized static double[] sub(final double[] vectorA, final double[] vectorB) {
        if (vectorA.length == vectorB.length) {
            final double[] add = new double[vectorA.length];
            for (int i = 0; i < vectorA.length; i++) {
                add[i] = vectorA[i] - vectorB[i];
            }
            return add;
        }
        return null;
    }

    public synchronized static double[] add(final double[] vectorA, final double[] vectorB) {
        if (vectorA.length == vectorB.length) {
            final double[] add = new double[vectorA.length];
            for (int i = 0; i < vectorA.length; i++) {
                add[i] = vectorA[i] + vectorB[i];
            }
            return add;
        }
        return null;
    }

    public synchronized static double cosineSimilarity(final double[] vectorA, final double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += vectorA[i] * vectorA[i];
            normB += vectorB[i] * vectorB[i];
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public synchronized static double cosineSimilarityNormalizedVecs(final double[] vectorA,
                                                                     final double[] vectorB) {
        double c = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            c += vectorA[i] * vectorB[i];
        }
        return c;
    }

    public synchronized static double norm(final double[] vectorA) {
        double normA = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            normA += vectorA[i] * vectorA[i];
        }
        return Math.sqrt(normA);
    }

    public synchronized static double[] normalize(final double[] vectorA) {
        final Double normA = norm(vectorA);
        for (int i = 0; i < vectorA.length; i++) {
            vectorA[i] /= normA.floatValue();
        }
        return vectorA;
    }

    //Adding maxval and minval methods
    //Method for getting the maximum value
    public static double getMax(double[] inputArray) {
        double maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    // Method for getting the minimum value
    public static double getMin(double[] inputArray) {
        double minValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] < minValue) {
                minValue = inputArray[i];
            }
        }
        return minValue;
    }

}
