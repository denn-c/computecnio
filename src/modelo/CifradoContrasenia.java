package modelo;

public class CifradoContrasenia {
    

    public static String obtenerHash(String texto, String hashTipo) {
        try {
            java.security.MessageDigest ResumenDelMensaje = java.security.MessageDigest.getInstance(hashTipo);
            byte[] array = ResumenDelMensaje.digest(texto.getBytes());
            StringBuilder GeneradorDeCadenas = new StringBuilder();
            for (byte b : array) {
                GeneradorDeCadenas.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return GeneradorDeCadenas.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
        return null;
    }

    public static String md5(String texto) {
        return CifradoContrasenia.obtenerHash(texto, "MD5");
    }

    public static String sha1(String texto) {
        return CifradoContrasenia.obtenerHash(texto, "SHA1");
    }
}
