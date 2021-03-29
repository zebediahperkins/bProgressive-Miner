package scripts.helpers;

public class StructuresHelper {
    public static boolean existsInArray(Object object, Object[] array) {
        for (Object item : array) {
            if (item.equals(object))
                return true;
        }
        return false;
    }
}
