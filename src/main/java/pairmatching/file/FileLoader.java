package pairmatching.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileLoader<T> {

    protected final String path;

    public FileLoader(String path) {
        this.path = path;
    }

    public List<T> read() {
        try {
            BufferedReader br = createBufferedReader();
            List<T> results = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;  // 더 이상 읽을 라인이 없을 경우 while 문을 빠져나간다.
                }
                results.add(bindLine(line));
            }
            br.close();
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private BufferedReader createBufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        return br;
    }

    protected abstract T bindLine(String line);
}
