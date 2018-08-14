package levels;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadLevelSets.
 *
 * @author Daniel Greenspan.
 */
public class ReadLevelSets {
    /**
     * fromReader.
     * @param reader - the IO reader.
     * @return a list of levelSetInfo objects.
     */
     public static List<LevelSetInfo> fromReader(java.io.Reader reader) {
        List<LevelSetInfo> sets = new ArrayList<>();
        String key = null;
        String message = null;
        String path = null;
        try {
            LineNumberReader r = new LineNumberReader(reader);
            String line = r.readLine();
            while (line != null) {
                if (r.getLineNumber() % 2 != 0) {
                    String[] temp = line.split(":");
                    key = temp[0];
                    message = temp[1];
                } else {
                    path = line;
                }
                if (key != null && message != null && path != null) {
                    sets.add(new LevelSetInfo(key, message, path));
                    key = null;
                    message = null;
                    path = null;
                }
                line = r.readLine();
            }
        } catch (IOException e) {
            e.getCause();
        }
        return sets;
    }
}
