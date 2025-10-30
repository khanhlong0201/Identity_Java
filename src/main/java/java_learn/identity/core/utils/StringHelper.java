package java_learn.identity.core.utils;

/*class khong dc phep instance va hong cos subclass */
public final class StringHelper {
  private StringHelper() {}

  public static boolean isNullOrEmpty(final String str) {
    return str == null || str.isEmpty();
  }

  public static boolean isNullOrBlank(final String str) {
    return str == null || str.isBlank();
  }

  public static boolean hasText(final String str) {
    return str != null && !str.isEmpty() && !str.isBlank();
  }
}
