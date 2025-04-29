public enum Alphabet {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ñ, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public static char getCharFromPosition(int position) {
        if (position < 0 || position >= Alphabet.values().length) {
            throw new IllegalArgumentException("La posición está fuera de rango.");
        }
        return Alphabet.values()[position].name().charAt(0);
    }
}


